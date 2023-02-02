package com.wasanco.ordermanagement.service;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wasanco.ordermanagement.dto.AccountDto;
import com.wasanco.ordermanagement.entity.Account;

import com.wasanco.ordermanagement.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    AccountRepository repository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public String createUsername(AccountDto accountDto) {
        Account account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        repository.save(account);
        return "Account created";

    }

    public boolean checkPassword(String id,String username,String password){
        Account account = repository.findById(UUID.fromString(id)).orElse(null);
        if(account == null) return false;
        if(passwordEncoder.matches(password, account.getPassword())){
            return true;
        }
        return false;
    }
}
