package com.autonomic.poc.couchbase.job;

import com.autonomic.poc.couchbase.domain.Weather;
import com.autonomic.poc.couchbase.domain.repository.WeatherRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherJobs {

  private final WeatherRepository weatherRepository;

  @Scheduled(fixedRate = 1)
  public void createMetrics() {
    log.info("Writing");
    Weather weather = new Weather();
    weather.setId(UUID.randomUUID());
    weather.setTemperature(Math.random() * 100);
    weather.setHumidity(Math.random() * 100);
    weather.setTimestamp(Instant.now().toEpochMilli());
    weatherRepository.save(weather)
        .doOnError(error -> log.error(error.getMessage()))
        .subscribe();

  }

}
