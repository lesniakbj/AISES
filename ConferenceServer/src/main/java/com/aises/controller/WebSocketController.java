package com.aises.controller;

import com.aises.controller.interfaces.Controller;
import com.aises.server.Routes;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eclipse.jetty.server.session.JDBCSessionManager;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Created by Brendan on 7/31/2016.
 *
 * A Controller that will handle WebSocket Connections,
 * allowing the application to have push notifications.
 */
@WebSocket
public class WebSocketController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    private static final Map<Session, String> sessions = new HashMap<>();

    public WebSocketController() {
        logger.debug("Creating a controller for WebSocket Notifications");
    }

    @OnWebSocketConnect
    public void connected(Session sess) throws Exception {
        sessions.put(sess, generateSessionName(sess));
        logger.debug("Added new session: {}", sessions.get(sess));
    }

    @OnWebSocketClose
    public void closed(Session sess, int statusCode, String reason) {
        logger.debug("Removing session: {}", sessions.get(sess));
        sessions.remove(sess);
    }

    @OnWebSocketMessage
    public void message(Session sess, String message) {
        logger.debug("Message from {}: {}", sessions.get(sess), message);
    }

    private static String generateSessionName(Session sess) {
        return "SESSION-" + sess.getRemoteAddress().toString();
    }

    public static void sendMessage(Session sess, String str) throws IOException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public static String sendMessageToAll(String str) throws IOException {
        List<Session> sessList = sessions.keySet()
                .stream()
                .filter(Session::isOpen)
                .collect(Collectors.toList());

        for(Session sess : sessList) {
            sess.getRemote().sendString(str);
        }

        return str;
    }
}
