package com.wasanco.user.accountmanagement.service.serviceinterface;

import com.wasanco.user.accountmanagement.dto.AccountDto;

public interface IAccountService {
    String createUsername(AccountDto accountDto);
    boolean checkPassword(String id,String username,String password);
}
