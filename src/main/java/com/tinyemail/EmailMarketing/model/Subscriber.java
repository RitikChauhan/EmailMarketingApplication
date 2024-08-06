package com.tinyemail.EmailMarketing.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Subscriber {
    @Id
    private Long id;
    private String email;

    public Subscriber(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Subscriber(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

