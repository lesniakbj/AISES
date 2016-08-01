package com.aises.domain.enums;

/**
 * Created by Brendan on 8/1/2016.
 *
 * Types of notifications classes that can be sent.
 */
public enum NotificationType {
    POST("POST"),
    @SuppressWarnings("unused")CALENDAR("CALENDAR"),
    ADMIN("ADMIN");

    private final String name;

    NotificationType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
