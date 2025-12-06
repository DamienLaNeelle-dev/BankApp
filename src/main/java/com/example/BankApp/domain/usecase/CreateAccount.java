package com.example.BankApp.domain.usecase;

import com.example.BankApp.domain.model.Account;
import com.example.BankApp.domain.service.AccountService;

import java.math.BigDecimal;

public class CreateAccount {

    private final AccountService accountService;

    public CreateAccount(AccountService accountService) {
        this.accountService = accountService;
    }

    public Account execute(String clientId, String name, String type, BigDecimal balance) {  // ← BigDecimal
        return accountService.createAccount(clientId, name, type, balance);
    }
}