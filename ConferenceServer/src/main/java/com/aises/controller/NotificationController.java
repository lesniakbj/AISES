package com.aises.controller;

import com.aises.controller.interfaces.Controller;
import com.aises.domain.Notification;
import com.aises.domain.enums.NotificationEvent;
import com.aises.domain.enums.NotificationType;
import com.aises.server.Routes;
import com.aises.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Spark;

import java.io.IOException;

/**
 * Created by Brendan on 8/1/2016.
 */
public class NotificationController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    public NotificationController() {
        logger.debug("Creating a controller for Notifications");
        Spark.post(Routes.NOTIFICATIONS_NEW, (req, resp) -> sendOnPost(req), JSONUtils.JSON());
    }

    // This API is used to send messages from the admin
    private String sendOnPost(Request req) {
        logger.debug("Sending new notification! {}", req.body());
        try {
            Notification ntf = new Notification();
            ntf.setType(NotificationType.valueOf(req.body()));
            ntf.setEvent(NotificationEvent.valueOf("NEW"));

            WebSocketController.sendMessage(JSONUtils.toJSON(ntf));
            return "OK";
        } catch (IOException e) {
            logger.error("Unable to send notification message!", e);
            return "FAIL";
        }
    }

    // This API is used internally to have other controllers send
    // Notificaitons
    public static String sendNotification(Notification ntf) {
        logger.debug("Sending new notification! {}", ntf);
        try {
            WebSocketController.sendMessage(JSONUtils.toJSON(ntf));
            return "OK";
        } catch (IOException e) {
            logger.error("Unable to send notification message!", e);
            return "FAIL";
        }
    }
}
