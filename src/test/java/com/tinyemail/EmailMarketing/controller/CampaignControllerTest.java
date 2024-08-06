package com.tinyemail.EmailMarketing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinyemail.EmailMarketing.dto.CampaignDTO;
import com.tinyemail.EmailMarketing.model.Campaign;
import com.tinyemail.EmailMarketing.model.Subscriber;
import com.tinyemail.EmailMarketing.service.CampaignService;
import com.tinyemail.EmailMarketing.util.MockEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
public class CampaignControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CampaignService campaignService;

    @MockBean
    private MockEmailService mockEmailService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateCampaign() throws Exception {
        Set<Subscriber> subscribers = new HashSet<>();
        subscribers.add(new Subscriber(1L, "ritik@gmail.com"));

        CampaignDTO campaignDTO = new CampaignDTO("Campaign A", "Subject A", "Email body A", subscribers);

        Campaign campaign = new Campaign();
        campaign.setName(campaignDTO.getName());
        campaign.setSubject(campaignDTO.getSubject());
        campaign.setBody(campaignDTO.getEmailBody());

        when(campaignService.createCampaign(anyLong(), any(Campaign.class))).thenReturn(campaign);

        mockMvc.perform(post("/campaigns/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(campaignDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Campaign A"));

        verify(campaignService, times(1)).createCampaign(anyLong(), any(Campaign.class));
    }

    @Test
    public void testListCampaigns() throws Exception {
        // Prepare mock data
        Campaign campaign1 = new Campaign();
        campaign1.setName("Campaign A");
        campaign1.setSubject("Subject A");
        campaign1.setBody("Email body A");

        Campaign campaign2 = new Campaign();
        campaign2.setName("Campaign B");
        campaign2.setSubject("Subject B");
        campaign2.setBody("Email body B");

        List<Campaign> campaigns = Arrays.asList(campaign1, campaign2);

        // Mock the service call
        when(campaignService.listCampaigns(anyLong())).thenReturn(campaigns);

        // Perform the request and verify the response
        mockMvc.perform(get("/campaigns/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Campaign A"))
                .andExpect(jsonPath("$[1].name").value("Campaign B"));

        verify(campaignService, times(1)).listCampaigns(anyLong());
    }

    @Test
    public void testSendCampaign() throws Exception {
        Campaign campaign = new Campaign();
        campaign.setName("Campaign A");
        campaign.setSubject("Subject A");
        campaign.setBody("Email body A");
        campaign.setSubscribers(new HashSet<>(Arrays.asList(new Subscriber(1L, "test1@example.com"), new Subscriber(2L, "test2@example.com"))));

        when(campaignService.sendCampaign(anyLong())).thenReturn(campaign);

        mockMvc.perform(post("/campaigns/1/send")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Campaign A"))
                .andExpect(jsonPath("$.status").value("SENT"));

        verify(campaignService, times(1)).sendCampaign(anyLong());
    }
}