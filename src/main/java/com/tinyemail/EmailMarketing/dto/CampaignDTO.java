package com.tinyemail.EmailMarketing.dto;

import com.tinyemail.EmailMarketing.model.Subscriber;

import java.util.HashSet;
import java.util.Set;

public class CampaignDTO {
    private String name;
    private String subject;
    private String emailBody;

    private Set<Subscriber> subscribers;

    public CampaignDTO(String name, String subject, String emailBody, Set<Subscriber> subscribers) {
        this.name = name;
        this.subject = subject;
        this.emailBody = emailBody;
        this.subscribers = subscribers;
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

    public Set<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }
}

