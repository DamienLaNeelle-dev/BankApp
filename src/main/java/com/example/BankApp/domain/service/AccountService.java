package com.example.BankApp.domain.service;

import com.example.BankApp.domain.model.Account;
import com.example.BankApp.domain.port.driven.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String clientId, String name, String type, BigDecimal balance) {
        Account account = new Account(clientId, name, type, balance);  // ← BigDecimal, pas double
        return accountRepository.save(account);
    }

    public List<Account> getAccountsByClientId(String clientId) {
        return accountRepository.findByClientId(clientId);
    }
}