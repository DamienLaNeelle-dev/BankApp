package com.example.BankApp.domain.exception;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String firstName, String lastName) {
        super("Un client  " + firstName + " " + lastName + " existe déjà");
    }
}