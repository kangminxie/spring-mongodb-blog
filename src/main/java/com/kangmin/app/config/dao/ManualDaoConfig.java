package com.kangmin.app.config.dao;

import com.kangmin.app.dao.AccountTemplateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class ManualDaoConfig {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ManualDaoConfig(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Bean
    public AccountTemplateDao accountManualDao() {
        return new AccountTemplateDao(mongoTemplate);
    }
}
