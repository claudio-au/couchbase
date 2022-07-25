package com.autonomic.poc.couchbase.domain.repository;

import com.autonomic.poc.couchbase.domain.Weather;
import java.sql.Timestamp;
import java.util.UUID;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface WeatherRepository extends ReactiveCrudRepository<Weather, UUID> {

  @Query("#{#n1ql.selectEntity} "
      + "WHERE timestamp BETWEEN $1 AND $2 "
      + "ORDER BY timestamp "
      + "LIMIT 100 ")
  Flux<Weather> findByDateRange(Timestamp start, Timestamp end);
}
