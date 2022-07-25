package com.autonomic.poc.couchbase.config;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class CouchbaseProperties {

    @Value("${spring.couchbase.host}")
    private String connectionString;
    @Value("${spring.couchbase.bucket.name}")
    private String bucketName;
    @Value("${spring.couchbase.bucket.password}")
    private String bucketPassword;

    @Value("${spring.couchbase.bucket.username}")
    private String username;

    @Value("${spring.couchbase.port}")
    private int port;

}