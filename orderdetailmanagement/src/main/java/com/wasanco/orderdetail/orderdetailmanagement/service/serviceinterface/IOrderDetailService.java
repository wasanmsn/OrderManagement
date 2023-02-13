package com.wasanco.orderdetail.orderdetailmanagement.service.serviceinterface;

import java.util.List;
import java.util.UUID;

import com.wasanco.orderdetail.orderdetailmanagement.dto.OrderDetailDto;
import com.wasanco.orderdetail.orderdetailmanagement.dto.request.OrderDetailProductRequestDto;
import com.wasanco.orderdetail.orderdetailmanagement.dto.request.OrderDetailRequestDto;

public interface IOrderDetailService {
    List<OrderDetailDto> getOrderDetails() ;
    OrderDetailDto addOrderDetail(OrderDetailProductRequestDto orderDetailDto,UUID orderId);
    boolean deleteOrderDetail(UUID id);
    OrderDetailDto updateOrderDetail(OrderDetailRequestDto orderDetailDto);
    List<OrderDetailDto> getOrderDetailsByOrderId(UUID id);
    boolean deleteOrderDetailByOrderId(UUID id);
    
}
