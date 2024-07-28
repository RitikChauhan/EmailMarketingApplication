package com.tinyemail.EmailMarketing.service;

import com.tinyemail.EmailMarketing.model.Client;
import com.tinyemail.EmailMarketing.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest {
    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    public ClientServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateClient() {
        Client client = new Client();
        client.setName("Client A");

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client createdClient = clientService.createClient(client);

        assertNotNull(createdClient);
        assertEquals("Client A", createdClient.getName());
        verify(clientRepository, times(1)).save(any(Client.class));
    }
}

