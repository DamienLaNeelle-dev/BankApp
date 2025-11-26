package com.example.BankApp.domain.port;

import com.example.BankApp.domain.model.Client;
import java.util.Optional;
import java.util.List;

public interface ClientRepository {
    List<Client> findAll();
    Client add(Client client);
    Optional<Client> findById(String id);
    boolean existsBy(String firstName, String lastName);
}
