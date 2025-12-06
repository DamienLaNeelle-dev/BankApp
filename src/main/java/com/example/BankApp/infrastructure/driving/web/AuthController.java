package com.example.BankApp.infrastructure.driving.web;

import com.example.BankApp.domain.model.Client;
import com.example.BankApp.domain.port.driven.ClientRepository;
import com.example.BankApp.infrastructure.driving.rest.dto.AuthResponse;
import com.example.BankApp.infrastructure.driving.rest.dto.LoginRequest;
import com.example.BankApp.infrastructure.driving.rest.dto.RegisterRequest;
import com.example.BankApp.infrastructure.driving.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.username(),
                            request.password()
                    )
            );

            String jwt = jwtService.generateToken(request.username());
            return ResponseEntity.ok(new AuthResponse(jwt));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Identifiants incorrects"));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Client> me(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Client client = (Client) userDetails;
        return ResponseEntity.ok(client);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            // Vérifier si l'utilisateur existe déjà
            if (clientRepository.findByUsername(request.username()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("error", "Ce nom d'utilisateur existe déjà"));
            }

            if (clientRepository.findByEmail(request.email()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("error", "Cet email est déjà utilisé"));
            }

            // Créer le nouveau client
            Client client = Client.builder()
                    .firstName(request.firstName())
                    .lastName(request.lastName())
                    .username(request.username())
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .role("USER")
                    .enabled(true)
                    .build();

            clientRepository.save(client);

            // Générer le JWT
            String jwt = jwtService.generateToken(client.getUsername());
            return ResponseEntity.ok(new AuthResponse(jwt));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur lors de l'inscription: " + e.getMessage()));
        }
    }
}