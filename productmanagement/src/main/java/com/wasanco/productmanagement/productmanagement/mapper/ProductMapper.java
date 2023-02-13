package com.wasanco.productmanagement.productmanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.wasanco.productmanagement.productmanagement.dto.ProductDto;
import com.wasanco.productmanagement.productmanagement.entity.Product;

@Named("ProductMapper")
@Mapper(componentModel="spring")
public interface ProductMapper {
    Product productDtoToProduct(ProductDto productDto);
    @Mappings({
        @Mapping(target="orderDetail",ignore=true)
    })
    ProductDto productToProductDto(Product product);

    List<ProductDto> listProductToProductDto(List<Product> product);
}