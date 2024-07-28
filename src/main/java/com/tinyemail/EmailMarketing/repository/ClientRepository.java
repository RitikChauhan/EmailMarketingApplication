package com.tinyemail.EmailMarketing.repository;

import com.tinyemail.EmailMarketing.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

