package com.wasanco.ordermanagement.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonValue;
import com.wasanco.ordermanagement.Constants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "order_db")
public class OrderDb {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private UUID id;
    private Timestamp createAt;
    private BigDecimal totalAmount;
    @Column(name = "status", columnDefinition = "VARCHAR(12) default 'ACTIVE'")
    private String status = Constants.ACTIVE;


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    // @ElementCollection
    // @JdbcTypeCode(java.sql.Types.VARCHAR)
    // private List<UUID> orderDetail;
    // @Transient
    private List<OrderDetail> orderDetail;

}
