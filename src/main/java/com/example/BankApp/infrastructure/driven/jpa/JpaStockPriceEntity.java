package com.example.BankApp.infrastructure.driven.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_price")
@Getter @Setter
public class JpaStockPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private BigDecimal price;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
