package com.wasanco.ordermanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.wasanco.ordermanagement.dto.OrderDetailDto;
import com.wasanco.ordermanagement.dto.OrderDto;
import com.wasanco.ordermanagement.dto.ProductDto;
import com.wasanco.ordermanagement.dto.request.OrderRequestDto;
import com.wasanco.ordermanagement.entity.OrderDb;
import com.wasanco.ordermanagement.entity.OrderDetail;
import com.wasanco.ordermanagement.entity.Product;


@Named("OrderMapper")
@Mapper(componentModel = "spring")
public interface OrderMapper {
    
    @Mapping(source = "totalAmnt",target = "totalAmount")
    OrderDb orderDtoToOrderDb(OrderDto orderDto);
    @Mapping(source = "totalAmount",target = "totalAmnt")
    OrderDto orderDbToOrderDto(OrderDb orderDb);

    List<OrderDto> listOrderDbToOrderDto(List<OrderDb> orderDbs);

    @Mappings({
        @Mapping(target="order",ignore=true)
    })
    OrderDetailDto orderDetailToOrderDetailDto(OrderDetail orderDetail);
    @Mappings({
        @Mapping(target="orderDetail",ignore=true)
    })
    ProductDto productToProductDto(Product product);

    @Mapping(source = "totalAmount",target = "totalAmnt")
    OrderRequestDto orderDbToOrderRequestDto(OrderDb order);






}
