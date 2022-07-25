package com.autonomic.poc.couchbase;

import com.autonomic.poc.couchbase.domain.Weather;
import com.autonomic.poc.couchbase.domain.repository.WeatherRepository;
import java.sql.Timestamp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@SpringBootApplication
//@EnableScheduling
@RestController
@RequiredArgsConstructor
@Slf4j
public class CouchbaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouchbaseApplication.class, args);
	}

	private final WeatherRepository weatherRepository;

	@GetMapping("/query")
	public Flux<Weather> get(){
		long time = System.currentTimeMillis();
		/*
		* Fri Jul 22 2022 13:00:00 GMT-0600
		* Fri Jul 22 2022 14:00:00 GMT-0600
		* */
		Flux<Weather> response = this.weatherRepository
				.findByDateRange(new Timestamp(1658516400000l), new Timestamp(1658520000000l));

		return response;
	}
}
