package com.tinyemail.EmailMarketing.service;
import com.tinyemail.EmailMarketing.model.Subscriber;
import com.tinyemail.EmailMarketing.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {
    @Autowired
    private SubscriberRepository subscriberRepository;

    public Subscriber saveSubscriber(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }
}
