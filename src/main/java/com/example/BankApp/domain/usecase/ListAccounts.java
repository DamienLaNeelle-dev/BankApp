package com.example.BankApp.domain.usecase;

import com.example.BankApp.domain.model.Account;
import com.example.BankApp.domain.port.driven.AccountRepository;

import java.util.List;

public class ListAccounts {
    private final AccountRepository accountRepository;

    public ListAccounts(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> execute(String clientId) {
        return accountRepository.findByClientId(clientId);
    }
}
