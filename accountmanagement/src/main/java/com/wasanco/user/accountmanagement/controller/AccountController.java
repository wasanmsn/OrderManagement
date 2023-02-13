package com.wasanco.user.accountmanagement.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wasanco.user.accountmanagement.dto.AccountDto;
import com.wasanco.user.accountmanagement.service.AccountService;

@RestController
public class AccountController {
    @Autowired
    AccountService service;

    @PostMapping("/accounts")
    public String createAccout(@RequestBody AccountDto accountDto) {
        return service.createUsername(accountDto);
    }

    @GetMapping("/accounts/{id}")
    public Boolean checkAccount(@PathVariable String id,@RequestParam(name = "username") String username,@RequestParam(name = "password") String password) {
        return service.checkPassword(id, username, password);
    }
}
