package com.wasanco.ordermanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Order detail not found.")
public class OrderDetailNotFoundException extends RuntimeException {
    public OrderDetailNotFoundException(String message){
        super(message);
    }
}
