package com.wasanco.user.accountmanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wasanco.user.accountmanagement.entity.Account;

public interface AccountRepository extends JpaRepository<Account,UUID> {
    
}
