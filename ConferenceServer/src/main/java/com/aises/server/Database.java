package com.aises.server;

import com.aises.utils.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Brendan on 7/25/2016.
 *
 * The database object to which we are connecting to.
 * Manages the connection to the database.
 */
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    private static Properties properties;

    private static final String DB_DRIVER_NAME = "org.postgresql.Driver";
    private static final String CONNECTION_TYPE = "jdbc:postgresql";

    private static Database instance = null;
    private Connection connection;

    private Database() {
        logger.debug("Getting connection to database");

        try {
            properties = ResourceLoader.loadProperties("db.properties");

            String host = properties.getProperty("host");
            int port = Integer.parseInt(properties.getProperty("port"));
            String dbName = properties.getProperty("db_name");
            String dbPass = properties.getProperty("db_password");

            Class.forName(DB_DRIVER_NAME);
            connection = DriverManager.getConnection(CONNECTION_TYPE + "://" + host + ":" + port + "/" + dbName, "postgres", dbPass);
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Unable to load properties or connect to the database!", e);
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
