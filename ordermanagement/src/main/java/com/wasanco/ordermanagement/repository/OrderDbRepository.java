package com.wasanco.ordermanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


import com.wasanco.ordermanagement.entity.OrderDb;



public interface OrderDbRepository extends JpaRepository<OrderDb,UUID>,OrderDbRepositoryCustom {
    public List<OrderDb> findByIdOrderByCreateAtDesc(UUID Id);
    public List<OrderDb> findByIdOrderByCreateAtAsc(UUID Id);

    
}
