package com.wasanco.user.accountmanagement.service;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wasanco.user.accountmanagement.dto.AccountDto;
import com.wasanco.user.accountmanagement.entity.Account;

import com.wasanco.user.accountmanagement.repository.AccountRepository;
import com.wasanco.user.accountmanagement.service.serviceinterface.IAccountService;

@Service
public class AccountService implements IAccountService {
    @Autowired
    AccountRepository repository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public String createUsername(AccountDto accountDto) {
        Account account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        repository.save(account);
        return "Account created";

    }
    @Override
    public boolean checkPassword(String id,String username,String password){
        Account account = repository.findById(UUID.fromString(id)).orElse(null);
        if(account == null) return false;
        System.out.print(account);
        if(passwordEncoder.matches(password, account.getPassword()) && account.getUsername().equals(username)){
            
            return true;
        }
        return false;
    }
}
