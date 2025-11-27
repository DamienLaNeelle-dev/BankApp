package com.example.BankApp.infrastructure.driving.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Détails d'un compte bancaire")
public record AccountDTO(

        @Schema(description = "Identifiant du compte", example = "9e7a38f4-1a32-43d1-b3f9-5b0de984e9d7")
        String id,

        @Schema(description = "Nom du compte", example = "Livret A")
        String name,

        @Schema(description = "Type de compte", example = "Livret A")
        String type,

        @Schema(description = "Solde du compte", example = "2500.00")
        BigDecimal balance
) {}
