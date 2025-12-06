package com.example.BankApp.domain.port.driven;

import com.example.BankApp.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByUsername(String username);
    Optional<Client> findByEmail(String email);
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}