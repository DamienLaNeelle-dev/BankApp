package com.example.BankApp.infrastructure.driving.rest.dto;

public record RegisterRequest(
        String username,
        String email,
        String password,
        String firstName,
        String lastName
) {}