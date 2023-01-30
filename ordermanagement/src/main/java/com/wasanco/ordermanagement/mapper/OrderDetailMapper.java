package com.wasanco.ordermanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.wasanco.ordermanagement.dto.OrderDetailDto;
import com.wasanco.ordermanagement.dto.ProductDto;
import com.wasanco.ordermanagement.entity.OrderDetail;
import com.wasanco.ordermanagement.entity.Product;

@Named("OrderDetailMapper")
@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetail orderDetailDtoToOrderDetail(OrderDetailDto orderDetailDto);
    @Mappings({
        @Mapping(target="order",ignore=true)
    })
    OrderDetailDto orderDetailToOrderDetailDto(OrderDetail orderDetail);

    List<OrderDetailDto> listOrderDetailToDetailDto(List<OrderDetail> orderDetails);

    @Mappings({
        @Mapping(target="orderDetail",ignore=true)
    })
    ProductDto productToProductDto(Product product);
}
