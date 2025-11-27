package com.example.BankApp.domain.usecase;

import com.example.BankApp.domain.model.Account;
import com.example.BankApp.domain.port.driven.AccountRepository;

import java.math.BigDecimal;

public class CreateAccount {
    private final AccountRepository accountRepository;

    public CreateAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account execute(String clientId, String name, String type, BigDecimal balance) {
        Account account = new Account(clientId, name, type, balance);
        return accountRepository.save(account);
    }
}
