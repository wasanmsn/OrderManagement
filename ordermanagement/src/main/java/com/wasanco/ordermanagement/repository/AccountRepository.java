package com.wasanco.ordermanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wasanco.ordermanagement.entity.Account;

public interface AccountRepository extends JpaRepository<Account,UUID> {
    
}
