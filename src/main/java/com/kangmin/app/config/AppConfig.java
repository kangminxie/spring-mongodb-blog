package com.kangmin.app.config;

import com.kangmin.app.model.EnvNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${application.env.node:DEV}")
    private String envNode;

    @Bean
    public EnvNode envNode() {
        return EnvNode.valueOf(envNode);
    }
}
