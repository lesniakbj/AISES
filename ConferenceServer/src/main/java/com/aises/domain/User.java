package com.aises.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

/**
 * Created by Brendan on 8/1/2016.
 */
public class User {
    private int id;
    private SocialLogin socialMediaLogin;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    public int getId() {
        return id;
    }
    public SocialLogin getSocialMediaLogin() {
        return socialMediaLogin;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmailAddress() {
        return emailAddress;
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
    public void setSocialMediaLogin(SocialLogin socialMediaId) {
        this.socialMediaLogin = socialMediaId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
