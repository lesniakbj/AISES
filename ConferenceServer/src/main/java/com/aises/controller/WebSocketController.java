package com.aises.controller;

import com.aises.controller.interfaces.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 7/31/2016.
 *
 * A Controller that will handle WebSocket Connections,
 * allowing the application to have push notifications.
 */
public class WebSocketController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    public WebSocketController() {
        logger.debug("Creating a controller for Posts");
    }
}
