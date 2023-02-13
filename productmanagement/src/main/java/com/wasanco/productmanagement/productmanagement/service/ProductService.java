package com.wasanco.productmanagement.productmanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wasanco.productmanagement.productmanagement.dto.ProductDto;
import com.wasanco.productmanagement.productmanagement.entity.Product;
import com.wasanco.productmanagement.productmanagement.mapper.ProductMapper;
import com.wasanco.productmanagement.productmanagement.repository.ProductRepository;
import com.wasanco.productmanagement.productmanagement.service.serviceinterface.IProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductService implements IProductService{
    
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductMapper mapper;
    @Override
    public ProductDto addProducts(ProductDto productDto){
        Product product = mapper.productDtoToProduct(productDto);    
        return mapper.productToProductDto(repository.save(product));
    }
    @Override
    public List<ProductDto> getProducts() {
        return mapper.listProductToProductDto(repository.findAll());
        
    }

    @Transactional
    @Override
    public ProductDto updateProduct(ProductDto productDto) throws Exception{
        Product product = mapper.productDtoToProduct(productDto);
        return mapper.productToProductDto(repository.save(product));
    }
    @Override
    public ProductDto getProductById(UUID id) {
        
        return mapper.productToProductDto(repository.findById(id).orElse(null));
    }
}
