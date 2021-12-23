package com.kangmin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BlogApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
