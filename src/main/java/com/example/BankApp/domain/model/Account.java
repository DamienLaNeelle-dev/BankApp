package com.example.BankApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private String id;
    private String clientId;
    private String name;
    private String type;
    private BigDecimal balance;  // ← BigDecimal

    // Constructeur pour créer un nouveau compte (sans ID)
    public Account(String clientId, String name, String type, BigDecimal balance) {
        this.id = UUID.randomUUID().toString();
        this.clientId = clientId;
        this.name = name;
        this.type = type;
        this.balance = balance;
    }
}