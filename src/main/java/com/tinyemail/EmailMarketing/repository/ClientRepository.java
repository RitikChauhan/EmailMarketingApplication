package com.tinyemail.EmailMarketing.repository;

import com.tinyemail.EmailMarketing.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

