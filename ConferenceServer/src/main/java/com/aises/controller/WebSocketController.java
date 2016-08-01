package com.aises.controller;

import com.aises.controller.interfaces.Controller;
import com.aises.server.Routes;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Brendan on 7/31/2016.
 *
 * A Controller that will handle WebSocket Connections,
 * allowing the application to have push notifications.
 */
@WebSocket
public class WebSocketController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    public WebSocketController() {
        logger.debug("Creating a controller for WebSocket Notifications");
    }

    @OnWebSocketConnect
    public void connected(Session sess) throws Exception {
        sessions.add(sess);
        logger.debug("Session created from: {}", sess);
        sess.getRemote().sendString("Connected to Notifications!");
    }

    @OnWebSocketClose
    public void closed(Session sess, int statusCode, String reason) {
        sessions.remove(sess);
        logger.debug("Session destroyed: {}", sess);
    }

    @OnWebSocketMessage
    public void message(Session user, String message) {
    }

    public static void sendMessage(String message) throws IOException {
        for(Session sess : sessions) {
            sess.getRemote().sendString(message);
        }
    }
}
