package com.tinyemail.EmailMarketing.repository;

import com.tinyemail.EmailMarketing.model.Campaign;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Iterable<Campaign> findByClientId(Long clientId);
}
