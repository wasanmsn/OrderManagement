package com.wasanco.orderdetail.orderdetailmanagement.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data

public class OrderRequestDto {
    private UUID id;
    private BigDecimal totalAmnt;
    
}
