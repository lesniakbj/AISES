package com.aises.domain.enums;

/**
 * Created by Brendan on 8/1/2016.
 */
public enum NotificationEvent {
    NEW("NEW"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private final String name;

    private NotificationEvent(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
