package com.kangmin.app.service.impl;

import com.kangmin.app.dao.AccountMongoDao;
import com.kangmin.app.model.Account;
import com.kangmin.app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountMongoDao accountDao;

    public AccountServiceImpl(final AccountMongoDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> getAll() {
        return accountDao.findAll().stream()
                .peek(each -> each.setPassword("******"))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Account> createAccount(final Account account) {
        if (accountDao.findByUsername(account.getUsername()).isPresent()) {
            return Optional.empty();
        }
        accountDao.save(account);
        return Optional.of(account);
    }

    @Override
    public Optional<Account> getAccountByUsername(final String username) {
        Optional<Account> usernameOpt = accountDao.findByUsername(username);
        if (usernameOpt.isPresent()) {
            return usernameOpt;
        }
        return accountDao.findByEmail(username);
    }

    @Override
    public Optional<Account> getAccountById(final String accountId) {
        return accountDao.findById(accountId);
    }

    @Override
    public boolean isUsernameExist(final String username) {
        return accountDao.existsByUsername(username);
    }

    @Override
    public boolean isEmailExist(final String email) {
        return accountDao.existsByEmail(email);
    }
}
