import server.ConferenceServer;

/**
 * Created by Brendan on 7/24/2016.
 */
public class AISESServerRunner {
    public static void main(String[] args) {
        // There can only ever be one server and one database, so we
        // model them as singletons within the system. Keeps state in
        // a singular spot.
        ConferenceServer server = ConferenceServer.getInstance();
        server.configureRepositories();
        server.configureControllers();
    }
}
