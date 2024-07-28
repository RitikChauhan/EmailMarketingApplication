package com.tinyemail.EmailMarketing.service;

import com.tinyemail.EmailMarketing.model.Campaign;
import com.tinyemail.EmailMarketing.model.CampaignStatus;
import com.tinyemail.EmailMarketing.repository.CampaignRepository;
import com.tinyemail.EmailMarketing.util.MockEmailService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CampaignServiceTest {
    @InjectMocks
    private CampaignService campaignService;

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private MockEmailService mockEmailService;

    public CampaignServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCampaign() {
        Campaign campaign = new Campaign();
        campaign.setName("Campaign A");
        campaign.setStatus(CampaignStatus.DRAFT);

        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        Campaign createdCampaign = campaignService.createCampaign(1L, campaign);

        assertNotNull(createdCampaign);
        assertEquals("Campaign A", createdCampaign.getName());
        assertEquals(CampaignStatus.DRAFT, createdCampaign.getStatus());
        verify(campaignRepository, times(1)).save(any(Campaign.class));
    }
}

