package com.tinyemail.EmailMarketing.service;

import com.tinyemail.EmailMarketing.dto.CampaignDTO;
import com.tinyemail.EmailMarketing.model.Campaign;
import com.tinyemail.EmailMarketing.model.CampaignStatus;
import com.tinyemail.EmailMarketing.model.Client;
import com.tinyemail.EmailMarketing.model.Subscriber;
import com.tinyemail.EmailMarketing.repository.CampaignRepository;
import com.tinyemail.EmailMarketing.repository.ClientRepository;
import com.tinyemail.EmailMarketing.util.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private MockEmailService mockEmailService;

    public Campaign createCampaign(Long clientId, Campaign campaign) {
        campaign.setStatus(CampaignStatus.DRAFT);

        // Find and set the client
        Optional<Client> client = clientRepo.findById(clientId);
        if (client.isEmpty()) {
            throw new RuntimeException("Please pass a valid client!");
        }
        campaign.setClient(client.get());

        // Process subscribers
        Set<Subscriber> finalSubscribers = subscriberService.processSubscribers(campaign.getSubscribers());

        // Associate the subscribers with the campaign
        campaign.setSubscribers(finalSubscribers);

        // Save and return the campaign
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
