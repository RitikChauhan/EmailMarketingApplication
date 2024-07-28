package com.tinyemail.EmailMarketing.util;

import com.tinyemail.EmailMarketing.model.Campaign;
import org.springframework.stereotype.Service;

@Service
public class MockEmailService {
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email to " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}
