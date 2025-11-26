package com.example.BankApp.domain.usecase;

import com.example.BankApp.domain.model.Client;
import com.example.BankApp.domain.port.ClientRepository;
import java.util.List;

public class ListClients {

    private final ClientRepository repository;

    public ListClients(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> findAll() {
        return repository.findAll();
    }
}