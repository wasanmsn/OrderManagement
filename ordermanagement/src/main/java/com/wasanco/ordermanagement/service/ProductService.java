package com.wasanco.ordermanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wasanco.ordermanagement.dto.ProductDto;
import com.wasanco.ordermanagement.entity.Product;
import com.wasanco.ordermanagement.exceptions.NotFoundException;
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

    public ProductDto updateProduct(ProductDto productDto) throws Exception{
        Product product = repository.findById(productDto.getId()).orElse(null);
        if(product == null){
            throw new NotFoundException("Product not found. "+productDto.getId());
        }
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        repository.save(product);
        return mapper.productToProductDto(product);
    }
}
