package com.tinyemail.EmailMarketing.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    private String name;
    private String subject;
    @Enumerated(EnumType.STRING)
    private CampaignStatus status;
    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToMany
    @JoinTable(
            name = "campaign_subscribers",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    private Set<Subscriber> subscribers = new HashSet<>();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public CampaignStatus getStatus() {
        return status;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }
}

