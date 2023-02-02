package com.wasanco.ordermanagement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wasanco.ordermanagement.dto.OrderDto;
import com.wasanco.ordermanagement.dto.request.OrderRequestDto;
import com.wasanco.ordermanagement.exceptions.NotFoundException;
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
    public List<OrderDto> getOrderByProductId(@RequestParam(name = "product-id") UUID productId,@RequestParam(name="order-by") Boolean orderAsc){
        return service.getOrdersByProductIdOrderByCreateAtOrderBy(productId,orderAsc);

    }

    @DeleteMapping("/orders")
    public void removeOrderDetail(@RequestParam UUID id){
            Boolean isExist = service.deleteOrder(id);
            if(!isExist){
                throw new NotFoundException("Order Detail not found "+id.toString());
            }

    }

    @PutMapping("/orders/{id}")
    public OrderDto updateOrder(@PathVariable UUID id,@RequestBody OrderRequestDto order) throws Exception{
        return service.updateOrder(order, id);
    }
    
}
