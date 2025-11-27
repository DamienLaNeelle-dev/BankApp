package com.example.BankApp.infrastructure.driven.jpa;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Entity
@Table
public class AccountEntity {
    @Id
    private String id;

    private String clientId;
    private String name;
    private String type;
    private BigDecimal balance;

    public AccountEntity() {}
    public AccountEntity(String id, String clientId, String name, String type, BigDecimal balance) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.type = type;
        this.balance = balance;
    }

}
