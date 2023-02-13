package com.wasanco.orderdetail.orderdetailmanagement.dto.request;


import com.wasanco.orderdetail.orderdetailmanagement.dto.OrderDetailDto;
import com.wasanco.orderdetail.orderdetailmanagement.dto.ProductDto;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class OrderDetailProductRequestDto {
    @Valid
    private OrderDetailDto orderDetail;
    @Valid
    private ProductDto product;
}
