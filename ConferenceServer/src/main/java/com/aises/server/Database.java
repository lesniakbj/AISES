package com.aises.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Brendan on 7/25/2016.
 */
public class Database {
    private static Properties properties = new Properties();

    private static final String DB_DRIVER_NAME = "org.postgresql.Driver";
    private static final String CONNECTION_TYPE = "jdbc:postgresql";

    private static String HOST;
    private static int PORT;
    private static String DB_NAME;
    private static String DB_PASS;

    private static Database instance = null;
    private Connection connection;

    protected Database() {
        try {
            InputStream input = Database.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(input);
            HOST = properties.getProperty("host");
            PORT = Integer.parseInt(properties.getProperty("port"));
            DB_NAME = properties.getProperty("db_name");
            DB_PASS = properties.getProperty("db_password");

            Class.forName(DB_DRIVER_NAME);
            connection = DriverManager.getConnection(CONNECTION_TYPE + "://" + HOST + ":" + PORT + "/" + DB_NAME, "postgres", DB_PASS);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
