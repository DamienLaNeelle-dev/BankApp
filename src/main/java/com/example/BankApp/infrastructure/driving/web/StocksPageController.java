package com.example.BankApp.infrastructure.driving.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StocksPageController {

    @GetMapping("/stocks")
    public String stocksPage() {
        return "stocks";
    }
}
