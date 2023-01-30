package com.wasanco.ordermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wasanco.ordermanagement.dto.ProductDto;
import com.wasanco.ordermanagement.entity.Product;
import com.wasanco.ordermanagement.mapper.ProductMapper;
import com.wasanco.ordermanagement.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductMapper mapper;

    public ProductDto addProducts(ProductDto productDto){
        Product product = mapper.productDtoToProduct(productDto);    
        return mapper.productToProductDto(repository.save(product));
    }

    public List<ProductDto> getProducts() {
        return mapper.listProductToProductDto(repository.findAll());
        
    }
}
