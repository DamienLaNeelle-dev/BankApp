package com.example.BankApp.infrastructure.driving;

import com.example.BankApp.infrastructure.driven.jpa.JpaStockPriceEntity;
import com.example.BankApp.infrastructure.driven.jpa.JpaStockPriceRepository;
import com.example.BankApp.infrastructure.driving.rest.dto.CreateStockPriceRequest;
import com.example.BankApp.infrastructure.driving.rest.dto.StockPriceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockPriceController {

    private final JpaStockPriceRepository repository;

    public StockPriceController(JpaStockPriceRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<StockPriceDTO> list() {
        return repository.findAll()
                .stream()
                .map(stock -> new StockPriceDTO(
                        stock.getId(),
                        stock.getSymbol(),
                        stock.getPrice(),
                        stock.getLastUpdated()
                ))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockPriceDTO create(@RequestBody CreateStockPriceRequest request) {

        JpaStockPriceEntity entity = new JpaStockPriceEntity();
        entity.setSymbol(request.symbol());
        entity.setPrice(BigDecimal.valueOf(request.price())); // conversion double -> BigDecimal
        entity.setLastUpdated(LocalDateTime.now());

        entity = repository.save(entity);

        return new StockPriceDTO(
                entity.getId(),
                entity.getSymbol(),
                entity.getPrice(),
                entity.getLastUpdated()
        );
    }
}
