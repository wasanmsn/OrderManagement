package com.wasanco.ordermanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wasanco.ordermanagement.dto.OrderDetailDto;
import com.wasanco.ordermanagement.exceptions.OrderDetailNotFoundException;
import com.wasanco.ordermanagement.service.OrderDetailService;

import jakarta.validation.Valid;

@RestController
public class OrderDetailController {
    @Autowired
    private OrderDetailService service;

    @GetMapping("/order-details/")
    public List<OrderDetailDto> getOrderDetails() {
        return service.getOrderDetails();
        
    }

    @PostMapping("/orders/{id}/order-details")
    public OrderDetailDto addOrderDetail(@PathVariable UUID id,@Valid @RequestBody OrderDetailDto orderDetailDto){
        return service.addOrderDetail(orderDetailDto,id);
    }

    @DeleteMapping("/order-details")
    public void removeOrderDetail(@RequestParam UUID id){
            Boolean isExist = service.deleteOrderDetail(id);
            if(!isExist){
                throw new OrderDetailNotFoundException("Order Detail not found "+id.toString());
            }

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderDetailNotFoundException.class)
    public String handleNotFoundExceptions(
        OrderDetailNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
    MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
