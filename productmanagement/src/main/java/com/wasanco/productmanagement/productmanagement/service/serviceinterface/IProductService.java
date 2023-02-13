package com.wasanco.productmanagement.productmanagement.service.serviceinterface;

import java.util.List;
import java.util.UUID;

import com.wasanco.productmanagement.productmanagement.dto.ProductDto;

public interface IProductService {
    public ProductDto addProducts(ProductDto productDto);
    public List<ProductDto> getProducts();
    public ProductDto updateProduct(ProductDto productDto) throws Exception;
    public ProductDto getProductById(UUID id);
}
