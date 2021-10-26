package com.kangmin.app.config.dev;

import com.kangmin.app.dao.AccountMongoDao;
import com.kangmin.app.model.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StaticDataLoader implements CommandLineRunner {

    private final AccountMongoDao accountMongoDao;

    public StaticDataLoader(final AccountMongoDao accountMongoDao) {
        this.accountMongoDao = accountMongoDao;
    }

    private void createDevAccounts() {
        if (accountMongoDao.findAll().isEmpty()) {
            final Account sa = new Account(
                "id-0000",
                "sa",
                "Super Admin1",
                "sa@test.com",
                "password"
            );
            final Account dev = Account.builder()
                .id("id-0001")
                .username("dev")
                .password("password")
                .email("dev@test.com")
                .displayName("Developer Admin1")
                .build();
            final Account normal = Account.builder()
                .id("id-0002")
                .username("normal")
                .password("password")
                .email("normal@test.com")
                .displayName("Normal User1")
                .build();
            accountMongoDao.saveAll(Arrays.asList(sa, dev, normal));
        }
    }


    @Override
    public void run(final String... args) {
        createDevAccounts();

        // fetch all accounts
        System.out.println("Accounts found with findAll():");
        System.out.println("-------------------------------");
        for (final Account account : accountMongoDao.findAll()) {
            System.out.println(account);
        }

        // fetch an individual account
        System.out.println("\nAccount found with findById('id-0000'):");
        System.out.println("--------------------------------");
        System.out.println(accountMongoDao.findById("id-0000")
            .orElseThrow(() -> new RuntimeException("no account associated with id-0000")));
        System.out.println("\nAccounts found with findByDisplayName('Developer Admin'):");
        System.out.println("--------------------------------");
        for (Account account : accountMongoDao.findByDisplayName("Developer Admin1")) {
            System.out.println(account);
        }

        System.out.println("Done with account test:");
    }
}
