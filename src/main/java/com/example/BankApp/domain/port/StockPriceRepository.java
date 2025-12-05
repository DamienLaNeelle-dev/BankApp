package com.example.BankApp.domain.port;

import com.example.BankApp.domain.model.StockPrice;
import java.util.List;

public interface StockPriceRepository {
    List<StockPrice> findAll();
}
