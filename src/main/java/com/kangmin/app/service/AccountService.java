package com.kangmin.app.service;

import com.kangmin.app.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAll();

    Optional<Account> createAccount(final Account account);

    Optional<Account> getAccountByUsername(final String username);

    Optional<Account> getAccountById(final String accountId);

    boolean isUsernameExist(final String username);

    boolean isEmailExist(final String email);
}
