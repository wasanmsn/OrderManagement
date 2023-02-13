package com.wasanco.ordermanagement.service.serviceinterface;

import java.util.List;
import java.util.UUID;

import com.wasanco.ordermanagement.dto.OrderDto;
import com.wasanco.ordermanagement.dto.request.OrderRequestDto;

public interface IOrderDbService {
    List<OrderDto> getOrderByIdOrderByCreateAtAsc(UUID id);
    List<OrderDto> getOrderByIdOrderByCreateAtDesc(UUID id);
    List<OrderDto> getOrders();
    List<OrderDto> getOrdersByProductIdOrderByCreateAtOrderBy(UUID id,Boolean isASC);
    OrderDto createOrder(OrderRequestDto orderDto);
    Boolean deleteOrder(UUID id);
    OrderDto updateOrder(OrderRequestDto order) throws Exception;
    OrderDto getOrderById(UUID id);
}
