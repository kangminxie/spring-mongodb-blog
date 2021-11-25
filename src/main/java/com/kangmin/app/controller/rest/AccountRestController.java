package com.kangmin.app.controller.rest;

import com.kangmin.app.dao.AccountTemplateDao;
import com.kangmin.app.dao.AccountMongoDao;
import com.kangmin.app.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {

    private final AccountMongoDao accountMongoDao;
    private final AccountTemplateDao accountTemplateDao;

    public AccountRestController(
        final AccountMongoDao accountMongoDao,
        final AccountTemplateDao accountTemplateDao
    ) {
        this.accountMongoDao = accountMongoDao;
        this.accountTemplateDao = accountTemplateDao;
    }

    @GetMapping("")
    public List<Account> getAll() {
        return accountMongoDao.findAll();
    }

    @GetMapping("/{username}")
    public Account getByUsername(final @PathVariable String username) {
        return accountTemplateDao.findByUsername(username).orElse(null);
    }
}
