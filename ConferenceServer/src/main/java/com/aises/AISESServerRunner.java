package com.aises;

import com.aises.server.ConferenceServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 7/24/2016.
 */
public class AISESServerRunner {
    private static final Logger logger = LoggerFactory.getLogger(AISESServerRunner.class);
    public static void main(String[] args) {
        // There can only ever be one server and one database, so we
        // model them as singletons within the system. Keeps state in
        // a singular spot.
        ConferenceServer server = ConferenceServer.getInstance();
        server.configureRepositories();
        server.configureControllers();

        logger.debug("Starting Server");
        server.run();
    }
}