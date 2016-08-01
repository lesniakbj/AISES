package com.aises.domain;

import com.aises.domain.enums.NotificationEvent;
import com.aises.domain.enums.NotificationType;

/**
 * Created by Brendan on 8/1/2016.
 *
 * Created to handle sending notifications to the client through
 * a Web Socket. Sends an event type along with the event that actually
 * happened. An admin message can also be sent.
 */
public class Notification {
    private NotificationType type;
    private NotificationEvent event;
    private String adminMessage;

    @SuppressWarnings("unused")
    public NotificationType getType() {
        return type;
    }
    @SuppressWarnings("unused")
    public NotificationEvent getEvent() {
        return event;
    }
    @SuppressWarnings("unused")
    public String getAdminMessage() {
        return adminMessage;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
    @SuppressWarnings("SameParameterValue")
    public void setEvent(NotificationEvent event) {
        this.event = event;
    }
    public void setAdminMessage(String adminMessage) {
        this.adminMessage = adminMessage;
    }
}
