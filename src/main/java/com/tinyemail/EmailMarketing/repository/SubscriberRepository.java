package com.tinyemail.EmailMarketing.repository;

import com.tinyemail.EmailMarketing.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
}

