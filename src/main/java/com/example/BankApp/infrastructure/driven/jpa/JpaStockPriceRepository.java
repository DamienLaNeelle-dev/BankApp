package com.example.BankApp.infrastructure.driven.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStockPriceRepository extends JpaRepository<JpaStockPriceEntity, Long> {
}
