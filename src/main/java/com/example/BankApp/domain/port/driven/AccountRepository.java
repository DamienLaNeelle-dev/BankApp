package com.example.BankApp.domain.port.driven;

import com.example.BankApp.domain.model.Account;
import java.util.List;

public interface AccountRepository {
    Account save(Account account);
    List<Account> findByClientId(String clientId);
}