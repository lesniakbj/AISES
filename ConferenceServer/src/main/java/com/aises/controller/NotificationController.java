package com.aises.controller;

import com.aises.controller.interfaces.Controller;
import com.aises.domain.Notification;
import com.aises.domain.enums.NotificationEvent;
import com.aises.domain.enums.NotificationType;
import com.aises.server.Routes;
import com.aises.utils.JSONUtils;
import org.eclipse.jetty.server.session.JDBCSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.IOException;

/**
 * Created by Brendan on 8/1/2016.
 */
public class NotificationController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    public NotificationController() {
        logger.debug("Creating a controller for Notifications");
        Spark.before(Routes.NOTIFICATIONS_ADMIN, (req, resp) -> checkAdminAuthorization());
        Spark.post(Routes.NOTIFICATIONS_ADMIN, (req, resp) -> sendAdminNotification(req), JSONUtils.JSON());
        Spark.after(Routes.POST_FILTER, (req, resp) -> JSONUtils.addAjaxHeader(resp));
    }

    private void checkAdminAuthorization() {
        logger.debug("Checking admin authorization before sending notification");
    }

    // This API is used to send messages from the admin
    private String sendAdminNotification(Request req) {
        logger.debug("Sending new notification! {}", req.body());
        try {
            Notification ntf = new Notification();
            ntf.setType(NotificationType.ADMIN);
            ntf.setEvent(NotificationEvent.NEW);
            ntf.setAdminMessage(req.body());
            return WebSocketController.sendMessageToAll(JSONUtils.toJSON(ntf));
        } catch (IOException e) {
            logger.error("Unable to send notification message!", e);
            return "ERROR";
        }
    }

    // This API is used internally to have other controllers send
    // Notifications
    public static String sendNotification(Notification ntf) {
        logger.debug("Sending new notification! {}", ntf);
        try {
            return WebSocketController.sendMessageToAll(JSONUtils.toJSON(ntf));
        } catch (IOException e) {
            logger.error("Unable to send notification message!", e);
            return "ERROR";
        }
    }
}
