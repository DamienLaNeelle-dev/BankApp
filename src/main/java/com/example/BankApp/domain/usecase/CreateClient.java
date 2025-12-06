package com.example.BankApp.domain.usecase;

import com.example.BankApp.domain.model.Client;
import com.example.BankApp.domain.port.driven.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateClient {

    private final ClientRepository repository;

    public Client execute(String firstName, String lastName) {

        if (repository.existsByFirstNameAndLastName(firstName, lastName)) {
            throw new IllegalArgumentException("Client déjà existant");
        }

        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);

        return repository.save(client);
    }
}
