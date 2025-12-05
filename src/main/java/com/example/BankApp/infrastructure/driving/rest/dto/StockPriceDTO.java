package com.example.BankApp.infrastructure.driving.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StockPriceDTO {
    private Long id;
    private String symbol;
    private BigDecimal price;
    private LocalDateTime lastUpdated;
}
