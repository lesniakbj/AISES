package com.aises.controller;

import com.aises.controller.interfaces.Controller;
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
        Spark.post(Routes.NOTIFICATIONS_NEW, (req, resp) -> sendNotification(req), JSONUtils.JSON());
    }

    private String sendNotification(Request req) {
        try {
            WebSocketController.sendMessage(req.body());
            return "OK";
        } catch (IOException e) {
            logger.error("Unable to send notification message!", e);
            return "FAIL";
        }
    }
}
