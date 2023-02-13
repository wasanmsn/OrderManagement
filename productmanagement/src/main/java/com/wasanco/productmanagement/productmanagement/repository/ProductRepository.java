package com.wasanco.productmanagement.productmanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wasanco.productmanagement.productmanagement.entity.Product;

public interface ProductRepository extends JpaRepository<Product,UUID> {
    
}
