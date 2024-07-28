package com.tinyemail.EmailMarketing.util;

import com.tinyemail.EmailMarketing.model.Campaign;
import org.springframework.stereotype.Service;

@Service
public class MockEmailService {
    public void sendEmail(Campaign campaign) {
        // Simulate sending email by printing to console
        System.out.println("Sending email campaign: " + campaign.getName());
        // Email sending logic would go here
    }
}
