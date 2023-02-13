package com.wasanco.productmanagement.productmanagement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wasanco.productmanagement.productmanagement.dto.ProductDto;
import com.wasanco.productmanagement.productmanagement.service.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        return service.getProducts();
        
    }
    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable UUID id) {
        return service.getProductById(id);
        
    }
    @PostMapping("/products")
    public ProductDto addProduct(@Valid @RequestBody ProductDto product){
        return service.addProducts(product);
    }

    @PutMapping("/proudcts/")
    public @ResponseBody ProductDto updateProduct(@Valid @RequestBody ProductDto productDto) throws Exception{
        return service.updateProduct(productDto);
    }

    @PutMapping("/productsDEMO/")
    public @ResponseBody ProductDto updateProductDEMO(@Valid @RequestBody ProductDto productDto) throws Exception{
        return service.updateProduct(productDto);
    }

    
    
}
