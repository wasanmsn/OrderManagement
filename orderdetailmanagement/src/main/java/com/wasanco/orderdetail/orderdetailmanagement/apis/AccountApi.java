package com.wasanco.orderdetail.orderdetailmanagement.apis;

import org.springframework.web.reactive.function.client.WebClient;

public class AccountApi {
    private WebClient accountRepository = WebClient.create("http://localhost:8085");
    

    public Boolean checkUsername(String id,String username, String password) {
        return accountRepository.get().uri("/accounts/"+id+"?username="+username+"&password="+password).retrieve().bodyToMono(Boolean.class).block();
    }
}
