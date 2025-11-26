package com.example.BankApp.infrastructure.driving.rest.dto;
import java.time.Instant;

public record ErrorResponse (
        int status,
        String error,
        String message,
        Instant timestamp
) {}