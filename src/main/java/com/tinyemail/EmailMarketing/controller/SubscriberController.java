package com.tinyemail.EmailMarketing.controller;

import com.tinyemail.EmailMarketing.model.Subscriber;
import com.tinyemail.EmailMarketing.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {

    @Autowired
    SubscriberService subscriberService;

    @PostMapping
    public ResponseEntity<Subscriber> createSubscriber(@RequestBody Subscriber subscriber)
    {
        return ResponseEntity.ok(subscriberService.saveSubscriber(subscriber));
    }

}
