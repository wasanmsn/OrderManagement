package com.wasanco.ordermanagement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wasanco.ordermanagement.dto.OrderDto;
import com.wasanco.ordermanagement.dto.request.OrderRequestDto;
import com.wasanco.ordermanagement.exceptions.NotFoundException;
import com.wasanco.ordermanagement.service.OrderDbService;

@CrossOrigin(origins = "http://localhost:8084")
@RestController
public class OrderDbController {
    @Autowired
    private OrderDbService service;

    @GetMapping("/orders/")
    public List<OrderDto> getOrders() {
        return service.getOrders();
        
    }
    @GetMapping("/orders/{id}")
    public OrderDto getOrderById(@PathVariable UUID id){
        return service.getOrderById(id);

    }
    @GetMapping("/orders")
    public List<OrderDto> getOrderByProductId(@RequestParam(name = "product-id") UUID productId,@RequestParam(name="order-by") Boolean orderAsc){
        return service.getOrdersByProductIdOrderByCreateAtOrderBy(productId,orderAsc);

    }

    @DeleteMapping("/orders/{id}")
    public void removeOrderDetail(@PathVariable UUID id){
            Boolean isExist = service.deleteOrder(id);
            if(!isExist){
                throw new NotFoundException("Order Detail not found "+id.toString());
            }

    }

    @PutMapping("/orders/")
    public OrderDto updateOrder(@RequestBody OrderRequestDto order) throws Exception{
        return service.updateOrder(order);
    }

    @PostMapping("/orders/")
    public OrderDto createOrder(@RequestBody OrderRequestDto order) {
        return service.createOrder(order);
    }
    
}
