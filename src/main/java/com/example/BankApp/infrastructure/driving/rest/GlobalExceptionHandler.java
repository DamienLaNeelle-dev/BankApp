package com.example.BankApp.infrastructure.driving.rest;

import com.example.BankApp.domain.exception.ClientAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import java.time.Instant;
import java.util.stream.Collectors;

import com.example.BankApp.infrastructure.driving.rest.dto.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j

public class GlobalExceptionHandler {
    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleClientAlreadyExists(
            ClientAlreadyExistsException ex,
            HttpServletRequest request
    ) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                Instant.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                message,
                Instant.now()
        );

        return ResponseEntity.badRequest().body(error);
    }
}