package com.tinyemail.EmailMarketing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinyemail.EmailMarketing.model.Client;
import com.tinyemail.EmailMarketing.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    public void testCreateClient() throws Exception {
        Client client = new Client();
        client.setName("Client A");

        when(clientService.createClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Client A"));

        verify(clientService, times(1)).createClient(any(Client.class));
    }
}