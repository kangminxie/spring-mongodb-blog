package com.kangmin.app.config.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MongoDbConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String connectionString;

    @Bean
    @Primary
    public MongoClient mongoClient() {
        return MongoClients.create(connectionString);
    }
}
