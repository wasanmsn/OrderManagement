package com.wasanco.orderdetail.orderdetailmanagement.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wasanco.orderdetail.orderdetailmanagement.entity.OrderDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class OrderDetailRepositoryImpl implements OrderDetailRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderDetail> findOrderDetailByOrderId(UUID id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderDetail> cq = cb.createQuery(OrderDetail.class);

        Root<OrderDetail> orderDetail = cq.from(OrderDetail.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(orderDetail.get("order"), id));
        cq.where(predicates.toArray(new Predicate[]{}));
        


        return entityManager.createQuery(cq).getResultList();
    }


    
}
