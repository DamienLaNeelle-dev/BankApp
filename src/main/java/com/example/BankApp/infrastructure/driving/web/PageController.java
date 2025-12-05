package com.example.BankApp.infrastructure.driving.web;

import com.example.BankApp.domain.port.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    private final ClientRepository clientRepository;

    public PageController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        var clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "index";
    }

    @GetMapping("/clients/{id}")
        public String accounts(@PathVariable String id, Model model){
        var optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            var client = optionalClient.get();
            model.addAttribute("clientId", id);
            model.addAttribute("firstName", client.firstName());
            model.addAttribute("lastName", client.lastName());
        } else{
            return "redirect:/";
        }
        return "accounts";
    }


}
