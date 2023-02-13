package com.wasanco.orderdetail.orderdetailmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.wasanco.orderdetail.orderdetailmanagement.dto.OrderDto;
import com.wasanco.orderdetail.orderdetailmanagement.dto.request.OrderRequestDto;

@Named("OrderMapper")
@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderRequestDto orderDtoToOrderRequestDto(OrderDto orderDto);
    OrderDto orderRequestDtoToOrderDto(OrderRequestDto orderRequestDto);
}
