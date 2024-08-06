package com.tinyemail.EmailMarketing.repository;

import com.tinyemail.EmailMarketing.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    List<Subscriber> findByEmailIn(Set<String> emails);
}

