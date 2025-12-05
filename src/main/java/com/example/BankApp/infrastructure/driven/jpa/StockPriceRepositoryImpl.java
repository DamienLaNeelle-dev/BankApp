package com.example.BankApp.infrastructure.driven.jpa;

import com.example.BankApp.domain.model.StockPrice;
import com.example.BankApp.domain.port.StockPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StockPriceRepositoryImpl implements StockPriceRepository {

    private final JpaStockPriceRepository jpa;

    @Override
    public List<StockPrice> findAll() {
        return jpa.findAll().stream()
                .map(e -> new StockPrice(
                        e.getId(),
                        e.getSymbol(),
                        e.getPrice(),
                        e.getLastUpdated()
                ))
                .toList();
    }
}
