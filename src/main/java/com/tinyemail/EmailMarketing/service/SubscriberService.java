package com.tinyemail.EmailMarketing.service;
import com.tinyemail.EmailMarketing.model.Subscriber;
import com.tinyemail.EmailMarketing.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubscriberService {
    @Autowired
    private SubscriberRepository subscriberRepository;

    public Subscriber saveSubscriber(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    public Set<Subscriber> processSubscribers(Set<Subscriber> incomingSubscribers) {
        // Extract emails from incoming subscribers
        Set<String> emails = incomingSubscribers.stream()
                .map(Subscriber::getEmail)
                .collect(Collectors.toSet());

        // Retrieve existing subscribers with these emails in one batch query
        List<Subscriber> existingSubscribers = subscriberRepository.findByEmailIn(emails);

        // Map existing subscribers by email
        Map<String, Subscriber> existingSubscriberMap = existingSubscribers.stream()
                .collect(Collectors.toMap(Subscriber::getEmail, subscriber -> subscriber));

        // Determine which incoming subscribers are not yet in the database
        Set<Subscriber> newSubscribers = incomingSubscribers.stream()
                .filter(subscriber -> !existingSubscriberMap.containsKey(subscriber.getEmail()))
                .collect(Collectors.toSet());

        // Save new subscribers in batch
        Set<Subscriber> savedSubscribers = new HashSet<>(subscriberRepository.saveAll(newSubscribers));

        // Combine existing and new subscribers
        Set<Subscriber> finalSubscribers = new HashSet<>(existingSubscribers);
        finalSubscribers.addAll(savedSubscribers);

        return finalSubscribers;
    }
}
