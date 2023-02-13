package com.wasanco.orderdetail.orderdetailmanagement.controller;

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

import com.wasanco.orderdetail.orderdetailmanagement.dto.OrderDetailDto;
import com.wasanco.orderdetail.orderdetailmanagement.dto.request.OrderDetailProductRequestDto;
import com.wasanco.orderdetail.orderdetailmanagement.dto.request.OrderDetailRequestDto;
import com.wasanco.orderdetail.orderdetailmanagement.exceptions.NotFoundException;
import com.wasanco.orderdetail.orderdetailmanagement.service.OrderDetailService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class OrderDetailController {
    @Autowired
    private OrderDetailService service;

    @GetMapping("/order-details/")
    public List<OrderDetailDto> getOrderDetails() {
        return service.getOrderDetails();
        
    }

    @GetMapping("/order-details")
    public List<OrderDetailDto> getOrderDetailsByOrderId(@RequestParam(name = "order-id") UUID orderId){
        return service.getOrderDetailsByOrderId(orderId);
    }

    @PostMapping("/order-details/{id}")
    public OrderDetailDto addOrderDetail(@PathVariable UUID id,@Valid @RequestBody OrderDetailProductRequestDto orderDetailDto){
        return service.addOrderDetail(orderDetailDto,id);
    }

    @DeleteMapping("/order-details/{id}")
    public void removeOrderDetail(@PathVariable UUID id){
            Boolean isExist = service.deleteOrderDetail(id);
            if(!isExist){
                throw new NotFoundException("Order Detail not found "+id.toString());
            }

    }

    @PutMapping("/order-details/")
    public OrderDetailDto updateOrderDetail(@Valid @RequestBody OrderDetailRequestDto orderDetail) throws Exception {
        return service.updateOrderDetail(orderDetail);
    }

    @DeleteMapping("/order-details")
    public void deleteOrderDetailByOrderId(@RequestParam(name = "order-id") UUID orderId){
        Boolean isExist = service.deleteOrderDetailByOrderId(orderId);
        if(!isExist){
            throw new NotFoundException("Order Detail not found "+orderId.toString());
        }

    }
}
