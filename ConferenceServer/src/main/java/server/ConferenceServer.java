package server;

import controller.PostController;
import repository.PostRepository;
import spark.Spark;
import spark.route.RouteOverview;


/**
 * Created by Brendan on 7/24/2016.
 */
public class ConferenceServer {
    private static ConferenceServer instance;
    private static Database database;

    private static final int LISTEN_PORT = 5570;
    private static final int MAX_THREADS = 8;

    static {
        database = Database.getInstance();
    }

    protected ConferenceServer() {
        Spark.port(LISTEN_PORT);
        Spark.threadPool(MAX_THREADS);
        // Debug info
        // RouteOverview.enableRouteOverview("/routes/help");
    }

    public static ConferenceServer getInstance() {
        if(instance == null) {
            instance = new ConferenceServer();
        }

        return instance;
    }

    public void configureRepositories() {
        PostRepository postRepository = PostRepository.getInstance(database);
    }

    public void configureControllers() {
        PostController postController = new PostController();
    }

    public void run() {
        Spark.init();
    }
}
