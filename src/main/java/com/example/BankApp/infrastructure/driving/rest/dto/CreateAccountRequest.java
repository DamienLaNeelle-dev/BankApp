package com.example.BankApp.infrastructure.driving.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Requête de création de compte bancaire")
public record CreateAccountRequest(

        @Schema(description = "Nom du compte", example = "Compte courant")
        String name,

        @Schema(description = "Type de compte", example = "Courant")
        String type,

        @Schema(description = "Solde initial", example = "1500.00")
        BigDecimal balance
) {}

