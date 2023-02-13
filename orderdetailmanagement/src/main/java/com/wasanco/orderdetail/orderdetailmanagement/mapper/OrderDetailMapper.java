package com.wasanco.orderdetail.orderdetailmanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.wasanco.orderdetail.orderdetailmanagement.dto.OrderDetailDto;
import com.wasanco.orderdetail.orderdetailmanagement.dto.request.OrderDetailRequestDto;
import com.wasanco.orderdetail.orderdetailmanagement.entity.OrderDetail;

@Named("OrderDetailMapper")
@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    
    OrderDetail orderDetailDtoToOrderDetail(OrderDetailDto orderDetailDto);
    @Mappings({
        @Mapping(target="order",ignore=true)
    })
    OrderDetailDto orderDetailToOrderDetailDto(OrderDetail orderDetail);

    List<OrderDetailDto> listOrderDetailToDetailDto(List<OrderDetail> orderDetails);

    OrderDetail orderDetailRequestToOrderDetail (OrderDetailRequestDto orderDetailRequestDto);


}
