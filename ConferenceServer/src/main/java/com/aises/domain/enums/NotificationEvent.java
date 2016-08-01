package com.aises.domain.enums;

/**
 * Created by Brendan on 8/1/2016.
 *
 * Types of notification events that can be sent
 */
public enum NotificationEvent {
    NEW("NEW"),
    @SuppressWarnings("unused")UPDATE("UPDATE"),
    @SuppressWarnings("unused")DELETE("DELETE");

    private final String name;

    NotificationEvent(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
