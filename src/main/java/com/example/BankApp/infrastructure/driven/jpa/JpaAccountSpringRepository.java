
package com.example.BankApp.infrastructure.driven.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaAccountSpringRepository extends JpaRepository<AccountEntity, String> {
    List<AccountEntity> findByClientId(String clientId);
}