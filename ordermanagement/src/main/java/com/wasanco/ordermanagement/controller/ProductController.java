package com.wasanco.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wasanco.ordermanagement.dto.ProductDto;
import com.wasanco.ordermanagement.entity.Product;
import com.wasanco.ordermanagement.service.ProductService;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/products/")
    public List<ProductDto> getProducts() {
        return service.getProducts();
        
    }
    @PostMapping("/products/")
    public ProductDto addProduct(@RequestBody ProductDto product){
        return service.addProducts(product);
    }
    
}
