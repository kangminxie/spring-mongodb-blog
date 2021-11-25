package com.kangmin.app.dao;

import com.kangmin.app.model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class AccountMongoDaoTest {

    @Autowired
    private AccountMongoDao accountMongoDao;

    @Test
    void TestFindByUsername() {
        final String testUsername = "testUser2021-10-26";
        final Account test1 = Account.builder()
            .id("id-test-001")
            .username(testUsername)
            .password("password")
            .email("dev@test.com")
            .displayName("Developer Admin1")
            .build();
        accountMongoDao.save(test1);

        final Optional<Account> optional = accountMongoDao.findByUsername(testUsername);
        assert optional.isPresent();
        final Account dbAccount = optional.get();
        assert dbAccount.getUsername().equals(testUsername);
    }
}
