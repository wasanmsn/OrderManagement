package com.wasanco.ordermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wasanco.ordermanagement.dto.AccountDto;
import com.wasanco.ordermanagement.service.AccountService;

@RestController
public class AccountController {
    @Autowired
    AccountService service;

    @PostMapping("/accounts")
    public String createAccout(@RequestBody AccountDto accountDto) {
        return service.createUsername(accountDto);
    }
}
