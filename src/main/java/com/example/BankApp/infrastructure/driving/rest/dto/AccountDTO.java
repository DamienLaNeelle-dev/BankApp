package com.example.BankApp.infrastructure.driving.rest.dto;

import java.math.BigDecimal;

public record AccountDTO(
        String id,
        String name,
        String type,
        BigDecimal balance  // ← BigDecimal, pas double
) {}