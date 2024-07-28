package com.tinyemail.EmailMarketing.controller;

import com.tinyemail.EmailMarketing.model.Campaign;
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
    public ResponseEntity<Campaign> createCampaign(@PathVariable Long clientId, @RequestBody Campaign campaign) {
        return ResponseEntity.ok(campaignService.createCampaign(clientId, campaign));
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<Iterable<Campaign>> listCampaigns(@PathVariable Long clientId) {
        return ResponseEntity.ok(campaignService.listCampaigns(clientId));
    }

    @PostMapping("/{campaignId}/send")
    public ResponseEntity<Campaign> sendCampaign(@PathVariable Long campaignId) {
        return ResponseEntity.ok(campaignService.sendCampaign(campaignId));
    }
}

