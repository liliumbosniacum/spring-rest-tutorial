package com.lilium.tutorial.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends DistributedEntity {
    private String username;
    private String name;

    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
