package com.wasanco.productmanagement.productmanagement.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ProductDto {
    private UUID id;
    @NotBlank
    private String name;
    @Column(precision=10, scale=2)
    @DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer = 10, fraction = 2, message = "Digits out of bound {10,2}")
    private BigDecimal price;

    @JsonIgnore
    private List<UUID> orderDetail;
}
