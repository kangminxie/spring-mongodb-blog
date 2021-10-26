package com.kangmin.app.controller.rest;

import com.kangmin.app.dao.AccountMongoDao;
import com.kangmin.app.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {

    private final AccountMongoDao accountMongoDao;

    public AccountRestController(final AccountMongoDao accountMongoDao) {
        this.accountMongoDao = accountMongoDao;
    }

    @GetMapping("")
    public List<Account> getAll() {
        return accountMongoDao.findAll();
    }
}
