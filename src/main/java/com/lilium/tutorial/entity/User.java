package com.lilium.tutorial.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends NamedEntity {
    private String username;

    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
