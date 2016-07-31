package com.aises.server;

import com.aises.controller.Controller;
import com.aises.controller.PostController;
import com.aises.controller.UploadController;
import com.aises.repository.PostRepository;
import com.aises.repository.Repository;
import spark.Spark;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Brendan on 7/24/2016.
 */
public class ConferenceServer {
    private static ConferenceServer instance;
    private static Database database;

    private static final int LISTEN_PORT = 5570;
    private static final int MAX_THREADS = 8;

    private List<Controller> controllers;
    private List<Repository> repositories;

    static {
        database = Database.getInstance();
    }

    protected ConferenceServer() {
        Spark.port(LISTEN_PORT);
        Spark.threadPool(MAX_THREADS);
        Spark.staticFiles.externalLocation("uploads");
        // Debug info
        // RouteOverview.enableRouteOverview("/routes/help");
        controllers = new ArrayList<>();
        repositories = new ArrayList<>();
    }

    public static ConferenceServer getInstance() {
        if(instance == null) {
            instance = new ConferenceServer();
        }

        return instance;
    }

    public void configureRepositories() {
        repositories.add(PostRepository.getInstance(database));
    }

    public void configureControllers() {
        controllers.add(new PostController());
        controllers.add(new UploadController());
    }

    public void run() {
        Spark.init();
    }
}
