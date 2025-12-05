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
@RequestMapping("/api/clients/{id}/accounts")
public class AccountController {

    private final CreateAccount createAccount;
    private final ListAccounts listAccounts;

    public AccountController(CreateAccount createAccount, ListAccounts listAccounts) {
        this.createAccount = createAccount;
        this.listAccounts = listAccounts;
    }

    @PostMapping
    @Operation(summary = "Créer un compte")
    @ApiResponse(responseCode = "201")
    public AccountDTO createAccount(
            @PathVariable("id") String clientId,   // ← correction ici
            @RequestBody CreateAccountRequest request
    ) {
        var account = createAccount.execute(clientId, request.name(), request.type(), request.balance());
        return new AccountDTO(account.getId(), account.getName(), account.getType(), account.getBalance());
    }

    @GetMapping
    @Operation(summary = "Lister les comptes d’un client")
    @ApiResponse(responseCode = "200")
    public List<AccountDTO> listAccounts(
            @PathVariable("id") String clientId    // ← correction ici aussi
    ) {
        return listAccounts.execute(clientId)
                .stream()
                .map(a -> new AccountDTO(a.getId(), a.getName(), a.getType(), a.getBalance()))
                .toList();
    }
}
