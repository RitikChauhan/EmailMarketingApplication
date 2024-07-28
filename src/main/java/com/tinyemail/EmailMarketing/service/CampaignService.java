package com.tinyemail.EmailMarketing.service;

import com.tinyemail.EmailMarketing.model.Campaign;
import com.tinyemail.EmailMarketing.model.CampaignStatus;
import com.tinyemail.EmailMarketing.model.Subscriber;
import com.tinyemail.EmailMarketing.repository.CampaignRepository;
import com.tinyemail.EmailMarketing.util.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private MockEmailService mockEmailService;

    public Campaign createCampaign(Long clientId, Campaign campaign) {
        campaign.setStatus(CampaignStatus.DRAFT);
        // Set client reference if needed
        return campaignRepository.save(campaign);
    }

    public Iterable<Campaign> listCampaigns(Long clientId) {
        return campaignRepository.findByClientId(clientId);
    }

    public Campaign sendCampaign(Long campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        campaign.setStatus(CampaignStatus.SENT);
        campaignRepository.save(campaign);

        // Send emails to all subscribers
        for (Subscriber subscriber : campaign.getSubscribers()) {
            mockEmailService.sendEmail(subscriber.getEmail(), campaign.getSubject(), campaign.getBody());
        }
        return campaign;
    }
}
