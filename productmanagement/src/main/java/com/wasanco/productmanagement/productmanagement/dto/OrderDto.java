package com.wasanco.productmanagement.productmanagement.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data


public class OrderDto {
    private BigDecimal totalAmnt;
    private String status;

    private List<UUID> orderDetail;
    
}
