package com.kangmin.app.dao;

import com.kangmin.app.model.Account;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Optional;

// bean will be created in ManualDaoConfig
// https://www.baeldung.com/spring-data-mongodb-tutorial
// https://www.baeldung.com/spring-boot-embedded-mongodb
// https://www.bezkoder.com/spring-boot-mongodb-crud/
// https://stackoverflow.com/questions/38288258/spring-boot-with-mongotemplate
// https://www.appsdeveloperblog.com/spring-boot-and-mongotemplate-tutorial-with-mongodb/
public class AccountTemplateDao {

    private final MongoTemplate mongoTemplate;

    public AccountTemplateDao(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Optional<Account> findByUsername(final String username) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        final List<Account> matches = mongoTemplate.find(query, Account.class);
        if (matches.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(matches.get(0));
    }

    public Optional<Account> updateAccountDisplayName(final Account account) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("username").is(account.getUsername()));
        final Update update = new Update();
        update.set("name", account.getDisplayName());
        return Optional.ofNullable(mongoTemplate.findAndModify(query, update, Account.class));
    }
}
