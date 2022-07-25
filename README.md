# Couchbase POC for TSS.

Couchbase is JSON database that excels in high volume transactions. 
Couchbase is an award-winning distributed NoSQL cloud database. It delivers unmatched versatility, performance, scalability, and financial value across cloud, on-premises, hybrid, distributed cloud, and edge computing deployments.

Current version: 7.1.1

https://www.couchbase.com/

## POC Goal:

Find a tool to replace Druid infrastructure.

This tool has to be able to process a high number of queries quickly.


## Scope of POC

This POC has to write and read data into couchbase and provide a way to user query the information.  
(https://github.com/claudio-au/couchbase/blob/main/src/main/java/com/autonomic/poc/couchbase/job/WeatherJobs.java)

This application is running using Java 11 and Spring Boot.
Also, It is using reactive library to Couchbase.

spring-boot-starter-data-couchbase-reactive

if you want to ingest data, please uncomment the annotation **@EnableScheduling** on CouchbaseApplication

## Data ingestion

To ingest data, it was created a job task that runs every 1 milliseconds
and save a measurement called weather into influxdb.
The structure of this measurement is defined on model Weather.java (https://github.com/claudio-au/couchbase/blob/main/src/main/java/com/autonomic/poc/couchbase/domain/Weather.java)

## Data Reading

By default, the application is querying data from a specific period (1 hour).
start: "Fri Jul 22 2022 13:00:00 GMT-0600", end: "Fri Jul 22 2022 14:00:00 GMT-0600"
Couchbase uses SQL syntax (SQL++) to run the queries.

To access the endpoint,
run the application and access the endpoint http://localhost:8081/query
It will run a pre-configured query (not aggregated).


## Performance
Couchbase showed a poor performance to query non-aggregated data.
Each request took 5s to return 602 records.

In a load test, running on local machine for 1minute for 10 virtual users (concurrent)
```shell
           /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: poc-get.js
     output: -

  scenarios: (100.00%) 1 scenario, 10 max VUs, 1m30s max duration (incl. graceful stop):
           * default: 10 looping VUs for 1m0s (gracefulStop: 30s)


running (1m25.1s), 00/10 VUs, 30 complete and 0 interrupted iterations
default ✓ [======================================] 10 VUs  1m0s

     ✓ response code was 200

     checks.........................: 100.00% ✓ 30       ✗ 0   
     data_received..................: 403 kB  4.7 kB/s
     data_sent......................: 2.6 kB  30 B/s
     http_req_blocked...............: avg=453.9µs  min=3µs    med=6.5µs  max=1.45ms p(90)=1.38ms  p(95)=1.38ms 
     http_req_connecting............: avg=131.33µs min=0s     med=0s     max=439µs  p(90)=411.8µs p(95)=430.2µs
   ✗ http_req_duration..............: avg=27.27s   min=26.65s med=27.18s max=28.71s p(90)=27.87s  p(95)=27.97s 
       { expected_response:true }...: avg=27.27s   min=26.65s med=27.18s max=28.71s p(90)=27.87s  p(95)=27.97s 
   ✓ http_req_failed................: 0.00%   ✓ 0        ✗ 30  
     http_req_receiving.............: avg=2.94ms   min=1.41ms med=2.43ms max=6.32ms p(90)=4.71ms  p(95)=5.01ms 
     http_req_sending...............: avg=25.76µs  min=10µs   med=24.5µs max=53µs   p(90)=39.7µs  p(95)=47.65µs
     http_req_tls_handshaking.......: avg=0s       min=0s     med=0s     max=0s     p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=27.27s   min=26.64s med=27.18s max=28.71s p(90)=27.87s  p(95)=27.97s 
     http_reqs......................: 30      0.352664/s
     iteration_duration.............: avg=28.27s   min=27.65s med=28.18s max=29.71s p(90)=28.87s  p(95)=28.97s 
     iterations.....................: 30      0.352664/s
     vus............................: 2       min=2      max=10
     vus_max........................: 10      min=10     max=10
```

None 4xx or 5xx status codes happened.

However, all requests took more than 2 seconds to run, failing on assertion for P(95)<2000.

**InfluxDB** POC https://github.com/claudio-au/couchbase, running on same scenario, was able to run only 10x more requests during the whole test.

