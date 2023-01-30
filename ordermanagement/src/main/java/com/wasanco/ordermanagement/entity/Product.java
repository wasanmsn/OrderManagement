package com.wasanco.ordermanagement.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
@JsonIgnoreProperties("orderDetail")
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @Column(name = "id",  updatable = false, nullable = false)
    private UUID id;
    private String name;
    @Digits(integer = 10, fraction = 2, message = "Digits out of bound {10,2}")
    private BigDecimal price;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderDetail> orderDetail;
}
