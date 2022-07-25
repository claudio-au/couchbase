package com.autonomic.poc.couchbase.config;

import com.autonomic.poc.couchbase.domain.Weather;
import java.util.Arrays;
import org.springframework.core.convert.converter.Converter;
import java.sql.Timestamp;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.couchbase.CouchbaseClientFactory;
import org.springframework.data.couchbase.SimpleCouchbaseClientFactory;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.convert.CouchbaseCustomConversions;
import org.springframework.data.couchbase.core.convert.MappingCouchbaseConverter;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;

@Configuration
@EnableReactiveCouchbaseRepositories("com.autonomic.poc.couchbase.domain.repository")
public class ReactiveCouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    private CouchbaseProperties couchbaseProperties;

    public ReactiveCouchbaseConfiguration(CouchbaseProperties couchbaseProperties) {
        this.couchbaseProperties = couchbaseProperties;
    }

    @Override
    public String getConnectionString() {
        return this.couchbaseProperties.getConnectionString();
    }

    @Override
    public String getUserName() {
        return this.couchbaseProperties.getUsername();
    }

    @Override
    public String getPassword() {
        return this.couchbaseProperties.getBucketPassword();
    }

    @Override
    public String getBucketName() {
        return this.couchbaseProperties.getBucketName();
    }

}