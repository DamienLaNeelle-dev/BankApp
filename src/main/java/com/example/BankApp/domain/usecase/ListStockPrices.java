package com.example.BankApp.domain.usecase;

import com.example.BankApp.domain.model.StockPrice;
import com.example.BankApp.domain.port.StockPriceRepository;
import java.util.List;

public class ListStockPrices {

    private final StockPriceRepository repository;

    public ListStockPrices(StockPriceRepository repository) {
        this.repository = repository;
    }

    public List<StockPrice> findAll() {
        return repository.findAll();
    }
}
