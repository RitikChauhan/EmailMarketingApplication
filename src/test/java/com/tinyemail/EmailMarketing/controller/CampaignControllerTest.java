package com.tinyemail.EmailMarketing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinyemail.EmailMarketing.model.Campaign;
import com.tinyemail.EmailMarketing.service.CampaignService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateCampaign() throws Exception {
        Campaign campaign = new Campaign();
        campaign.setName("Campaign A");

        when(campaignService.createCampaign(anyLong(), any(Campaign.class))).thenReturn(campaign);

        mockMvc.perform(post("/campaigns/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(campaign)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Campaign A"));

        verify(campaignService, times(1)).createCampaign(anyLong(), any(Campaign.class));
    }
}