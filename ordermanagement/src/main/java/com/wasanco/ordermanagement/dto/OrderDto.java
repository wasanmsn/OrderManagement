package com.wasanco.ordermanagement.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data


public class OrderDto {
    private UUID id;
    private BigDecimal totalAmnt;
    private String status;

    private List<OrderDetailDto> orderDetail;
    
}
