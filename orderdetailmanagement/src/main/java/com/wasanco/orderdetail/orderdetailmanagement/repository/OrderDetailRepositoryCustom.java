package com.wasanco.orderdetail.orderdetailmanagement.repository;

import java.util.List;
import java.util.UUID;

import com.wasanco.orderdetail.orderdetailmanagement.entity.OrderDetail;

public interface OrderDetailRepositoryCustom {
    public List<OrderDetail> findOrderDetailByOrderId(UUID id);
}
