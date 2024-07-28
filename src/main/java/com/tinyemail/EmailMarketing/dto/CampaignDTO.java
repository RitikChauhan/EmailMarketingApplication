package com.tinyemail.EmailMarketing.dto;

public class CampaignDTO {
    private String name;
    private String subject;
    private String emailBody;

    // Default constructor
    public CampaignDTO() {}

    // Parameterized constructor
    public CampaignDTO(String name, String subject, String emailBody) {
        this.name = name;
        this.subject = subject;
        this.emailBody = emailBody;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }
}

