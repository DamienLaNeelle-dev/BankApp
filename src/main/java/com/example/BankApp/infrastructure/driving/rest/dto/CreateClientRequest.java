package com.example.BankApp.infrastructure.driving.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateClientRequest (
        @NotBlank(message = "Le prÃ©nom est obligatoire")
        String firstName,

        @NotBlank(message = "Le nom est obligatoire")
        String lastName
) {}