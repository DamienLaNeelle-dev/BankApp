package com.example.BankApp.infrastructure.driven.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaAccountSpringRepository extends JpaRepository<AccountEntity, String> {
    List<AccountEntity> findByClientId(String clientId);
}