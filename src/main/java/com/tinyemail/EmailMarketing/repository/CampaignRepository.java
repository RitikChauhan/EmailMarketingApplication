package com.tinyemail.EmailMarketing.repository;

import com.tinyemail.EmailMarketing.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Iterable<Campaign> findByClientId(Long clientId);
}
