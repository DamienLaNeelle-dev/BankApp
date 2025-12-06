package com.example.BankApp.infrastructure.application.config;

import com.example.BankApp.domain.port.StockPriceRepository;
import com.example.BankApp.domain.port.driven.AccountRepository;
import com.example.BankApp.domain.port.driven.ClientRepository;
import com.example.BankApp.domain.service.AccountService;
import com.example.BankApp.domain.usecase.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    // ----------------- Services -----------------
    @Bean
    public AccountService accountService(AccountRepository repository) {
        return new AccountService(repository);
    }

    // ----------------- Use Cases -----------------
    @Bean
    public CreateAccount createAccount(AccountService accountService) {
        return new CreateAccount(accountService);
    }

    @Bean
    public ListAccounts listAccounts(AccountRepository repository) {
        return new ListAccounts(repository);
    }

    @Bean
    public CreateClient createClient(ClientRepository repository) {
        return new CreateClient(repository);
    }

    @Bean
    public ListClients listClients(ClientRepository repository) {
        return new ListClients(repository);
    }

    @Bean
    public ListStockPrices listStockPrices(StockPriceRepository repository) {
        return new ListStockPrices(repository);
    }
}
