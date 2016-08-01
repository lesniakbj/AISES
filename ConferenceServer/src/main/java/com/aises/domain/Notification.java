package com.aises.domain;

import com.aises.domain.enums.NotificationEvent;
import com.aises.domain.enums.NotificationType;

/**
 * Created by Brendan on 8/1/2016.
 */
public class Notification {
    private NotificationType type;
    private NotificationEvent event;
    private String adminMessage;

    public void setType(NotificationType type) {
        this.type = type;
    }
    public void setEvent(NotificationEvent event) {
        this.event = event;
    }
    public void setAdminMessage(String adminMessage) {
        this.adminMessage = adminMessage;
    }
}
