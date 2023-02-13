package com.wasanco.orderdetail.orderdetailmanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wasanco.orderdetail.orderdetailmanagement.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,UUID>,OrderDetailRepositoryCustom {
    
}
