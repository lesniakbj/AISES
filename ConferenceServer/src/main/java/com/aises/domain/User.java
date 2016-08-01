package com.aises.domain;

import java.time.LocalDateTime;

/**
 * Created by Brendan on 8/1/2016.
 */
public class User {
    private int id;
    private int socialMediaId;
    private String firstName;
    private String lastName;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    public int getId() {
        return id;
    }
    public int getSocialMediaId() {
        return socialMediaId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setSocialMediaId(int socialMediaId) {
        this.socialMediaId = socialMediaId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
