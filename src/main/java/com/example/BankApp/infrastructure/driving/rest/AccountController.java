package com.example.BankApp.infrastructure.driving.rest;

import com.example.BankApp.domain.usecase.CreateAccount;
import com.example.BankApp.domain.usecase.ListAccounts;
import com.example.BankApp.infrastructure.driving.rest.dto.AccountDTO;
import com.example.BankApp.infrastructure.driving.rest.dto.CreateAccountRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Accounts", description = "Gestion des comptes bancaires")
@RequestMapping("/clients/{ClientId}/accounts")
public class AccountController {
    private final CreateAccount createAccount;
    private final ListAccounts listAccounts;

    public AccountController(CreateAccount createAccount, ListAccounts listAccounts) {
        this.createAccount = createAccount;
        this.listAccounts = listAccounts;
    }

    @PostMapping
    @Operation(summary = "Créer un compte", description = "Créer un nouveau compte bancaire pour un client")
    @ApiResponse(responseCode = "201", description = "Compte créé avec succès")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    public AccountDTO createAccount(@PathVariable String clientId, @RequestBody CreateAccountRequest request) {
        var account = createAccount.execute(clientId, request.name(), request.type(), request.balance());
        return new AccountDTO(account.getId(), account.getName(), account.getType(), account.getBalance());
    }

    @GetMapping
    @Operation(
            summary = "Lister les comptes d’un client",
            description = "Retourne tous les comptes bancaires d’un client."
    )
    @ApiResponse(responseCode = "200", description = "Liste des comptes")
    public List<AccountDTO> listAccounts(@PathVariable String clientId) {
        return listAccounts.execute(clientId)
                .stream()
                .map(a -> new AccountDTO(a.getId(), a.getName(), a.getType(), a.getBalance()))
                .collect(Collectors.toList());
    }
}
