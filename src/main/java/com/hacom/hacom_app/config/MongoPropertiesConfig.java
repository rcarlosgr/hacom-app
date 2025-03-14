package com.hacom.hacom_app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mongodb")
@Getter
@Setter
public class MongoPropertiesConfig {
    private String uri;
    private String database;
}
