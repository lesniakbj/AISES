package com.aises.server;

import com.aises.controller.*;
import com.aises.controller.interfaces.Controller;
import com.aises.repository.PostRepository;
import com.aises.repository.UserRepository;
import com.aises.repository.interfaces.Repository;
import com.aises.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Brendan on 7/24/2016.
 *
 * The default server implementation. Sets up all
 * controllers, repositories and routes.
 */
public class ConferenceServer {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ConferenceServer.class);

    private static ConferenceServer instance;
    private static final Database database;

    private static final int LISTEN_PORT = 5570;
    private static final int MIN_THREADS = 4;
    private static final int MAX_THREADS = 16;
    private static final int THREAD_TIMEOUT = 5000;

    private List<Controller> controllers;
    private List<Repository> repositories;

    static {
        database = Database.getInstance();
    }

    private ConferenceServer() {
        controllers = new ArrayList<>();
        repositories = new ArrayList<>();

        Spark.port(LISTEN_PORT);
        Spark.threadPool(MAX_THREADS, MIN_THREADS, THREAD_TIMEOUT);
        Spark.staticFiles.externalLocation(UploadController.UPLOAD_DIRECTORY);
        Spark.webSocket(Routes.NOTIFICATIONS, WebSocketController.class);
        Spark.after(Routes.NOTIFICATIONS_FILTER, (req, resp) -> JSONUtils.addAjaxHeader(resp));
    }

    public static ConferenceServer getInstance() {
        if(instance == null) {
            instance = new ConferenceServer();
        }

        return instance;
    }

    public void configureRepositories() {
        repositories.add(UserRepository.getInstance(database));
        repositories.add(PostRepository.getInstance(database));
    }
    public void configureControllers() {
        // WebSocketController must be defined first
        controllers.add(new WebSocketController());

        // Then we can define all of the other normal controllers
        controllers.add(new LoginController());
        controllers.add(new UploadController());
        controllers.add(new PostController());
        controllers.add(new NotificationController());
    }

    public void run() {
        Spark.init();
    }
}
