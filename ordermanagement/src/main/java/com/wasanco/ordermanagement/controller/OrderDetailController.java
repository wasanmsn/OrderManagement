package com.wasanco.ordermanagement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wasanco.ordermanagement.dto.OrderDetailDto;
import com.wasanco.ordermanagement.dto.request.OrderDetailRequestDto;
import com.wasanco.ordermanagement.exceptions.NotFoundException;
import com.wasanco.ordermanagement.service.OrderDetailService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class OrderDetailController {
    @Autowired
    private OrderDetailService service;

    @GetMapping("/order-details")
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
                throw new NotFoundException("Order Detail not found "+id.toString());
            }

    }

    @PutMapping(value="/order-details/{id}")
    public OrderDetailDto updateOrderDetail(@PathVariable UUID id, @Valid @RequestBody OrderDetailRequestDto orderDetail) throws Exception {
        return service.updateOrderDetail(orderDetail, id);
    }
}
