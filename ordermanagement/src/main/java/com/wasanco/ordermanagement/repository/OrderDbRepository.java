package com.wasanco.ordermanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wasanco.ordermanagement.entity.OrderDb;



public interface OrderDbRepository extends JpaRepository<OrderDb,UUID> {
    public List<OrderDb> findByIdOrderByCreateAtDesc(UUID Id);
    public List<OrderDb> findByIdOrderByCreateAtAsc(UUID Id);

    @Query(
        value = "SELECT o.id,o.create_at,o.total_amount FROM order_db as o INNER JOIN order_detail as d ON d.order_id = o.id INNER JOIN product as p ON d.product_id = p.id WHERE p.id = :id ORDER BY o.create_at ASC",nativeQuery = true)
    public List<OrderDb> findByProductIdOrderByCreateAtAsc(@Param("id") String id);

    @Query(
        value = "SELECT o.id,o.create_at,o.total_amount FROM order_db as o INNER JOIN order_detail as d ON d.order_id = o.id INNER JOIN product as p ON d.product_id = p.id WHERE p.id = :id ORDER BY o.create_at DESC",nativeQuery = true)
    public List<OrderDb> findByProductIdOrderByCreateAtDesc(@Param("id") String id);
    
}
