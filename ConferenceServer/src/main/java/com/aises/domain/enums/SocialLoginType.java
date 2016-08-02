package com.aises.domain.enums;

/**
 * Created by Brendan on 8/1/2016.
 */
public enum SocialLoginType {
    FACEBOOK(1, "Facebook"),
    GOOGLE(2, "Google");

    private final int id;
    private final String name;

    SocialLoginType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        return name;
    }
}
