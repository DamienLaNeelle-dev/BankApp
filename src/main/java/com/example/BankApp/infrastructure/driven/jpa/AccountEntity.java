package com.example.BankApp.infrastructure.driven.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "account_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "client_id", length = 36, nullable = false)
    private String clientId;  // ← String clientId, pas Client

    @Column(nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String type;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;
}