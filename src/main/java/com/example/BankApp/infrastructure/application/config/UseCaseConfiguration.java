package com.example.BankApp.infrastructure.application.config;

import com.example.BankApp.domain.port.ClientRepository;
import com.example.BankApp.domain.port.driven.AccountRepository;
import com.example.BankApp.domain.usecase.CreateAccount;
import com.example.BankApp.domain.usecase.CreateClient;
import com.example.BankApp.domain.usecase.ListAccounts;
import com.example.BankApp.domain.usecase.ListClients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public CreateAccount createAccount(AccountRepository repository) {
        return new CreateAccount(repository);
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
}
