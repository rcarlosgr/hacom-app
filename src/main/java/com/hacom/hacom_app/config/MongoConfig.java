package com.hacom.hacom_app.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

@Configuration
@RequiredArgsConstructor
public class MongoConfig extends AbstractReactiveMongoConfiguration {
    private final MongoPropertiesConfig mongoPropertiesConfig;

    @Override
    protected String getDatabaseName() {
        return mongoPropertiesConfig.getDatabase();
    }

    @Bean
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(mongoPropertiesConfig.getUri());
    }
}

