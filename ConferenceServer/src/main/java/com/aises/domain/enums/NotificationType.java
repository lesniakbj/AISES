package com.aises.domain.enums;

/**
 * Created by Brendan on 8/1/2016.
 */
public enum NotificationType {
    POST("POST"),
    CALENDAR("CALENDAR"),
    ADMIN("ADMIN");

    private final String name;

    private NotificationType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
