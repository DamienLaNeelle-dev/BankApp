package com.example.BankApp.domain.model;

import java.time.LocalDateTime;

public record StockPrice(
        Long id,
        String symbol,
        java.math.BigDecimal price,
        LocalDateTime lastUpdated
) {}
