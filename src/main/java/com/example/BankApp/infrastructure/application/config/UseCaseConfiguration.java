package com.example.BankApp.infrastructure.application.config;

import com.example.BankApp.domain.port.ClientRepository;
import com.example.BankApp.domain.usecase.CreateClient;
import com.example.BankApp.domain.usecase.ListClients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public ListClients listClients(ClientRepository clientRepository) {
        return new ListClients(clientRepository);
    }

    @Bean
    public CreateClient createClient(ClientRepository clientRepository) {
        return new CreateClient(clientRepository);
    }


}