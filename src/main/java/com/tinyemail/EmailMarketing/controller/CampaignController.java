package com.tinyemail.EmailMarketing.controller;

import com.tinyemail.EmailMarketing.dto.CampaignDTO;
import com.tinyemail.EmailMarketing.model.Campaign;
import com.tinyemail.EmailMarketing.model.CampaignStatus;
import com.tinyemail.EmailMarketing.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    @PostMapping("/clients/{clientId}")
    public ResponseEntity<Campaign> createCampaign(@PathVariable Long clientId, @RequestBody CampaignDTO campaignDTO) {
        // Convert DTO to domain model
        Campaign campaign = new Campaign();
        campaign.setName(campaignDTO.getName());
        campaign.setSubject(campaignDTO.getSubject());
        campaign.setBody(campaignDTO.getEmailBody());

        return ResponseEntity.ok(campaignService.createCampaign(clientId, campaign));
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<Iterable<Campaign>> listCampaigns(@PathVariable Long clientId) {
        return ResponseEntity.ok(campaignService.listCampaigns(clientId));
    }

    @PostMapping("/{campaignId}/send")
    public ResponseEntity<Campaign> sendCampaign(@PathVariable Long campaignId) {
        Campaign campaign = campaignService.sendCampaign(campaignId);
        campaign.setStatus(CampaignStatus.SENT); // Update status to "Sent" before returning
        return ResponseEntity.ok(campaign);
    }
}

