package com.example.BankApp.infrastructure.driven.jdbc;

import com.example.BankApp.domain.model.Client;
import com.example.BankApp.domain.port.ClientRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcClientRepository implements ClientRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final RowMapper<Client> CLIENT_MAPPER = (rs, rowNum) ->
            new Client(
                    rs.getString("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName")
            );

    public JdbcClientRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Client> findAll() {
        return jdbcTemplate.query(
                "SELECT id, \"firstName\", \"lastName\" FROM client",
                CLIENT_MAPPER
        );
    }

    @Override
    public boolean existsBy(String firstName, String lastName) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM client WHERE lower(\"firstName\") = lower(:firstName) AND lower(\"lastName\") = lower(:lastName)",
                Map.of("firstName", firstName, "lastName", lastName),
                Integer.class
        );
        return count != null && count > 0;
    }

    @Override
    public Client add(Client client) {
        jdbcTemplate.update(
                "INSERT INTO client (id, \"firstName\", \"lastName\") VALUES (:id, :firstName, :lastName)",
                Map.of(
                        "id", client.id(),
                        "firstName", client.firstName(),
                        "lastName", client.lastName()
                )
        );
        return client;
    }

    @Override
    public Optional<Client> findById(String id) {
        try {
            Client client = jdbcTemplate.queryForObject(
                    "SELECT id, \"firstName\", \"lastName\" FROM client WHERE id = :id",
                    Map.of("id", id),
                    CLIENT_MAPPER
            );
            return Optional.of(client);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}

