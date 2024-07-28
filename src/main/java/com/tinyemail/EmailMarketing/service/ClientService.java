package com.tinyemail.EmailMarketing.service;

import com.tinyemail.EmailMarketing.model.Client;
import com.tinyemail.EmailMarketing.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
    }
}

