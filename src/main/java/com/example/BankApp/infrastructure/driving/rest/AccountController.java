package com.example.BankApp.infrastructure.driving.rest;

import com.example.BankApp.domain.model.Account;
import com.example.BankApp.domain.usecase.CreateAccount;
import com.example.BankApp.domain.usecase.ListAccounts;
import com.example.BankApp.infrastructure.driving.rest.dto.AccountDTO;
import com.example.BankApp.infrastructure.driving.rest.dto.CreateAccountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients/{clientId}/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final ListAccounts listAccounts;
    private final CreateAccount createAccount;

    @GetMapping
    public List<AccountDTO> listAccounts(@PathVariable("id") String clientId) {
        return listAccounts.execute(clientId)
                .stream()
                .map(a -> new AccountDTO(
                        a.getId(),
                        a.getName(),
                        a.getType(),
                        a.getBalance()
                ))
                .collect(Collectors.toList());  // ← .collect() au lieu de .toList()
    }

    @PostMapping
    public AccountDTO createAccount(
            @PathVariable("id") String clientId,
            @RequestBody CreateAccountRequest request
    ) {
        var account = createAccount.execute(
                clientId,
                request.name(),
                request.type(),
                request.balance()  // ← Doit être BigDecimal
        );
        return new AccountDTO(
                account.getId(),
                account.getName(),
                account.getType(),
                account.getBalance()
        );
    }
}
