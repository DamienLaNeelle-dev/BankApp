package com.example.BankApp.infrastructure.driving.rest;
import com.example.BankApp.domain.usecase.CreateClient;
import com.example.BankApp.domain.usecase.ListClients;
import com.example.BankApp.infrastructure.driving.rest.dto.ClientDTO;
import com.example.BankApp.infrastructure.driving.rest.dto.CreateClientRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clients")

public class ClientController {

    private final ListClients listClients;
    private final CreateClient createClient;

    public ClientController(ListClients listClients, CreateClient createClient) {
        this.listClients = listClients;
        this.createClient = createClient;
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return listClients.findAll()
                .stream()
                .map(client -> new ClientDTO(client.id(), client.firstName(), client.lastName()))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO create(
            @Valid @RequestBody CreateClientRequest request
    ) {
        var client = createClient.handle(request.firstName(), request.lastName());
        return new ClientDTO(client.id(), client.firstName(), client.lastName());
    }
}