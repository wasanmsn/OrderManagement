package com.wasanco.orderdetail.orderdetailmanagement.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wasanco.orderdetail.orderdetailmanagement.Constants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import lombok.Data;

@Data
@Entity
@Table(name="order_detail")
@JsonIgnoreProperties("order")
public class OrderDetail {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private UUID id;
    private String productName;
    private int quantity;
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;
    private BigDecimal amount;
    private Timestamp createAt;
    @Column(name = "status", columnDefinition = "VARCHAR(12) default 'ACTIVE'")
    private String status = Constants.ACTIVE;
     
    // @ManyToOne
    // @JoinColumn(name = "product_id")
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @Column(name = "product_id",columnDefinition = "VARCHAR(36)")
    private UUID product;

    // @ManyToOne
    // @JoinColumn(name = "order_id")
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @Column(name = "order_id",columnDefinition = "VARCHAR(36)")
    // private UUID order;
    private UUID order;


}
