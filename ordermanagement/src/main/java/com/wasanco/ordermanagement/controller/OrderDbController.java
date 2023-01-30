package com.wasanco.ordermanagement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wasanco.ordermanagement.dto.OrderDto;

import com.wasanco.ordermanagement.service.OrderDbService;

@RestController
public class OrderDbController {
    @Autowired
    private OrderDbService service;

    @GetMapping("/orders/")
    public List<OrderDto> getOrders() {
        return service.getOrders();
        
    }
    @GetMapping("/orders/{id}")
    public List<OrderDto> getOrderById(@PathVariable UUID id,@RequestParam String sort){
        if(sort.equalsIgnoreCase("ASC")){
            return service.getOrderByIdOrderByCreateAtAsc(id);
        }
        return service.getOrderByIdOrderByCreateAtDesc(id);

    }
    @GetMapping("/orders")
    public List<OrderDto> getOrderByProductId(@RequestParam(name = "product-id") UUID productId,@RequestParam(name="order-by") String orderAsc){
        if(orderAsc.equalsIgnoreCase("ASC")){
            return service.getOrdersByProductIdOrderByCreateAtAsc(productId);
        }
        return service.getOrdersByProductIdOrderByCreateAtDesc(productId);

    }
}
