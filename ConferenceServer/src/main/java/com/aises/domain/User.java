package com.aises.domain;

/**
 * Created by Brendan on 8/1/2016.
 */
public class User {
    private int id;
    private int socialMediaId;
    private String firstName;
    private String lastName;

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
}
