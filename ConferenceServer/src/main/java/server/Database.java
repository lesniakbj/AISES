package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Brendan on 7/25/2016.
 */
public class Database {
    // TODO: MOVE THIS TO THE POM OR RESOURCE FILES
    private static final String DB_DRIVER_NAME = "org.postgresql.Driver";
    private static final String CONNECTION_TYPE = "jdbc:postgresql";
    private static final String HOST = "localhost";
    private static final int PORT = 6670;
    private static final String DB_NAME = "*********";
    private static final String DB_PASS = "**********";

    private static Database instance = null;
    private Connection connection;

    protected Database() {
        try {
            Class.forName(DB_DRIVER_NAME);
            connection = DriverManager.getConnection(CONNECTION_TYPE + "://" + HOST + ":" + PORT + "/" + DB_NAME, "postgres", DB_PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }

        return instance;
    }
}
