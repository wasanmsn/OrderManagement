package com.wasanco.ordermanagement.dto;

import java.math.BigDecimal;
import java.util.List;



import lombok.Data;

@Data


public class OrderDto {
    private BigDecimal totalAmnt;

    private List<OrderDetailDto> orderDetail;
}
