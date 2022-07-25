package com.autonomic.poc.couchbase.domain;

import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.util.Converter;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.ValueConverter;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Document
@Getter
@Setter
@Scope("metrics")
@Collection("weather")
public class Weather {

  @Id
  @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
  private UUID id;

  private double temperature;
  private double humidity;
  @Field
  private Long timestamp;

}
