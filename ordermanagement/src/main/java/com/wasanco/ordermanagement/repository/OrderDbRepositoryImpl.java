package com.wasanco.ordermanagement.repository;

import java.util.List;

import com.wasanco.ordermanagement.entity.OrderDb;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class OrderDbRepositoryImpl implements OrderDbRepositoryCustom {
    
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings ( "unchecked" )
    @Override
    public List<OrderDb> filterByProductIdOrderByCreateAt(String id, Boolean isASC){
        return (List<OrderDb>) entityManager.createNativeQuery("""
                SELECT o.id,o.create_at,o.total_amount,o.status 
                FROM order_db as o INNER 
                JOIN order_detail as d ON d.order_id = o.id 
                INNER JOIN product as p ON d.product_id = p.id 
                WHERE p.id = ?1 ORDER BY o.create_at 
                    """+(isASC?"ASC":"DESC"),OrderDb.class).setParameter(1, id).getResultList();
    }
    
}
