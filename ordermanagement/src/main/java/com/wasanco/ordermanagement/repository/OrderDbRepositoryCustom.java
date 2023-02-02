package com.wasanco.ordermanagement.repository;

import java.util.List;

import com.wasanco.ordermanagement.entity.OrderDb;

public interface OrderDbRepositoryCustom {
    public List<OrderDb> filterByProductIdOrderByCreateAt(String id,Boolean isASC);
}
