package com.example.BankApp.domain.model;

import com.github.f4b6a3.ulid.UlidCreator;

public record Client(
        String id,
        String firstName,
        String lastName
) {
    public Client (String firstName, String lastName) {
        this(UlidCreator.getUlid().toString(),
                firstName, lastName);
    }
}
