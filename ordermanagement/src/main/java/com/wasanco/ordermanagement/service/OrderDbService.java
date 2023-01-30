package com.wasanco.ordermanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wasanco.ordermanagement.dto.OrderDto;
import com.wasanco.ordermanagement.entity.OrderDb;
import com.wasanco.ordermanagement.mapper.OrderMapper;
import com.wasanco.ordermanagement.repository.OrderDbRepository;
@Service
public class OrderDbService {
    @Autowired
    private OrderDbRepository repository;
    @Autowired
    private OrderMapper mapper;



    public List<OrderDto> getOrderByIdOrderByCreateAtAsc(UUID id) {
        List<OrderDb> order = repository.findByIdOrderByCreateAtAsc(id);
        return mapper.listOrderDbToOrderDto(order);
    }

    public List<OrderDto> getOrderByIdOrderByCreateAtDesc(UUID id) {
        List<OrderDb> order = repository.findByIdOrderByCreateAtDesc(id);
        return mapper.listOrderDbToOrderDto(order);
    }

    public List<OrderDto> getOrders() {
        return mapper.listOrderDbToOrderDto(repository.findAll());
    }

    public List<OrderDto> getOrdersByProductIdOrderByCreateAtAsc(UUID id){
        List<OrderDb> order = repository.findByProductIdOrderByCreateAtAsc(id.toString());
        return mapper.listOrderDbToOrderDto(order);
    }

    public List<OrderDto> getOrdersByProductIdOrderByCreateAtDesc(UUID id){
        List<OrderDb> order = repository.findByProductIdOrderByCreateAtDesc(id.toString());
        return mapper.listOrderDbToOrderDto(order);
    }

    public OrderDto createOrder(OrderDto orderDto){
        return mapper.orderDbToOrderDto(repository.save(mapper.orderDtoToOrderDb(orderDto)));
         
    }
}
