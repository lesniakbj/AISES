package server;

import controller.PostController;
import repository.PostRepository;
import spark.Spark;


/**
 * Created by Brendan on 7/24/2016.
 */
public class ConferenceServer {
    private static ConferenceServer instance;
    private static Database database;

    static {
        database = Database.getInstance();
    }

    protected ConferenceServer() {
        Spark.port(5570);
        Spark.threadPool(8);
        Spark.init();
    }

    public static ConferenceServer getInstance() {
        if(instance == null) {
            instance = new ConferenceServer();
        }

        return instance;
    }

    public void configureRepositories() {
        PostRepository postRepository = PostRepository.getInstance();
        postRepository.setDatabase(database);
    }

    public void configureControllers() {
        PostController postController = new PostController();
    }
}
