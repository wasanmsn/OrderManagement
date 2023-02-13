package com.wasanco.ordermanagement.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wasanco.ordermanagement.entity.OrderDb;
import com.wasanco.ordermanagement.entity.OrderDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class OrderDbRepositoryImpl implements OrderDbRepositoryCustom {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderDb> filterByProductIdOrderByCreateAt(String id, Boolean isASC){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderDb> cq = cb.createQuery(OrderDb.class);

        Root<OrderDb> order = cq.from(OrderDb.class);
        List<Predicate> predicates = new ArrayList<>();

        Join<OrderDb,OrderDetail> orderDetail = order.join("orderDetail");
        predicates.add(cb.equal(orderDetail.get("product"), UUID.fromString(id)));
        cq.where(predicates.toArray(new Predicate[] {}))
        .orderBy(isASC ? cb.asc(order.get("createAt")) : cb.desc(order.get("createAt")));

        return entityManager.createQuery(cq).getResultList();
        // return (List<OrderDb>) entityManager.createNativeQuery("""
        //         SELECT o.id,o.create_at,o.total_amount,o.status 
        //         FROM order_db as o INNER 
        //         JOIN order_detail as d ON d.order_id = o.id 
        //         INNER JOIN product as p ON d.product_id = p.id 
        //         WHERE p.id = ?1 ORDER BY o.create_at 
        //             """+(isASC?"ASC":"DESC"),OrderDb.class).setParameter(1, id).getResultList();

    }
    
}
