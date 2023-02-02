package com.wasanco.ordermanagement.dto.request;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDetailRequestDto {
    @NotBlank(message = "Product name is blank")
    private String productName;
    @NotNull(message = "Quantity not null")
    @Min(1)
    private int quantity;
    @Column(precision=10, scale=2)
    @DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer = 10, fraction = 2, message = "Digits out of bound {10,2}")
    private BigDecimal price;
}
