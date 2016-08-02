package com.aises.repository;

import com.aises.repository.interfaces.Repository;
import com.aises.server.Database;
import com.aises.utils.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Brendan on 8/1/2016.
 *
 * A Repository for manipulating user tables
 */
public class UserRepository implements Repository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    private static UserRepository instance;
    private final Database database;

    //private static final String DROP_USER_TABLES = ResourceLoader.loadFile("sql/user/admin/clean-user-tables.sql");
    private static final String CREATE_IF_NEEDED = ResourceLoader.loadFile("sql/user/admin/create-user-tables.sql");
    private static final String SOCIAL_MEDIA_ID_EXISTS = ResourceLoader.loadFile("sql/user/query/social-login-id-exists.sql");


    private UserRepository(Database database) {
        logger.debug("Creating a repository for Users");
        this.database = database;
        try {
            Connection con = this.database.getConnection();

            logger.debug("Attempting to create tables");
            PreparedStatement createTables = con.prepareStatement(CREATE_IF_NEEDED);
            createTables.execute();
        } catch (SQLException e) {
            logger.error("Error creating required tables for Users!", e);
        }
    }

    public static UserRepository getInstance() {
        if(instance == null) {
            instance = new UserRepository(null);
        }

        return instance;
    }
    public static UserRepository getInstance(@SuppressWarnings("SameParameterValue") Database database) {
        if(instance == null) {
            instance = new UserRepository(database);
        }

        return instance;
    }

    public boolean socialMediaIdExists(String socialId, String type) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement ps = conn.prepareStatement(SOCIAL_MEDIA_ID_EXISTS);
        ps.setString(1, socialId);
        ps.setString(2, type);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("total") > 0;
    }

    public boolean createNewUser(String socialId) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement ps = conn.prepareStatement(SOCIAL_MEDIA_ID_EXISTS);
        ps.setString(1, socialId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("total") > 0;
    }
}
