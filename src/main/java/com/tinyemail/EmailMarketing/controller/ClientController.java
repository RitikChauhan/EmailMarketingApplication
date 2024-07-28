package com.tinyemail.EmailMarketing.controller;

import com.tinyemail.EmailMarketing.model.Client;
import com.tinyemail.EmailMarketing.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.createClient(client));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClient(id));
    }
}

