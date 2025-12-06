package com.example.BankApp.infrastructure.driven.jpa;

import com.example.BankApp.domain.model.Account;
import com.example.BankApp.domain.port.driven.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaAccountRepository implements AccountRepository {

    private final JpaAccountSpringRepository jpaRepository;

    public JpaAccountRepository(JpaAccountSpringRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Account save(Account account) {
        AccountEntity entity = new AccountEntity(
                account.getId(),
                account.getClientId(),
                account.getName(),
                account.getType(),
                account.getBalance()
        );
        jpaRepository.save(entity);
        return account;
    }

    @Override
    public List<Account> findByClientId(String clientId) {
        return jpaRepository.findByClientId(clientId)
                .stream()
                .map(e -> new Account(
                        e.getId(),           // ← Constructeur complet (5 paramètres)
                        e.getClientId(),
                        e.getName(),
                        e.getType(),
                        e.getBalance()
                ))
                .collect(Collectors.toList());
    }
}