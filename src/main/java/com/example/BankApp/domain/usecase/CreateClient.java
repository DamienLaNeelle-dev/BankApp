package com.example.BankApp.domain.usecase;

import com.example.BankApp.domain.exception.ClientAlreadyExistsException;
import com.example.BankApp.domain.model.Client;
import com.example.BankApp.domain.port.ClientRepository;

public class CreateClient {

    private final ClientRepository repository;

    public CreateClient(ClientRepository repository) {
        this.repository = repository;
    }

    public Client handle(String firstName, String lastName) {
        if (repository.existsBy(firstName, lastName)) {
            throw new ClientAlreadyExistsException(firstName, lastName);
        }

        Client client = new Client(firstName, lastName);

        return repository.add(client);
    }
}