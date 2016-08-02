package com.aises.repository;

import com.aises.domain.Post;
import com.aises.repository.interfaces.Repository;
import com.aises.server.Database;
import com.aises.utils.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brendan on 7/25/2016.
 *
 * A repository that can be used to interact with
 * the database for Post objects.
 */
public class PostRepository implements Repository {
    private static final Logger logger = LoggerFactory.getLogger(PostRepository.class);

    private static PostRepository instance;
    private final Database database;

    //private static final String DROP_POST_TABLES = ResourceLoader.loadFile("sql/post/admin/clean-post-tables.sql");
    private static final String CREATE_IF_NEEDED = ResourceLoader.loadFile("sql/post/admin/create-post-tables.sql");

    private static final String INSERT_NEW_POST = ResourceLoader.loadFile("sql/post/query/insert-new-post.sql");

    private static final String GET_MAX_POST_ID = ResourceLoader.loadFile("sql/post/query/get-max-post-id.sql");
    private static final String FIND_POST_BY_ID = ResourceLoader.loadFile("sql/post/query/get-post-by-id.sql");
    private static final String FIND_ALL_POSTS = ResourceLoader.loadFile("sql/post/query/get-all-posts.sql");
    private static final String FIND_POST_RANGE = ResourceLoader.loadFile("sql/post/query/get-posts-by-range.sql");

    private PostRepository(Database database) {
        logger.debug("Creating a repository for Posts");
        this.database = database;

        try {
            Connection con = this.database.getConnection();
            logger.debug("Creating post tables");
            PreparedStatement createTables = con.prepareStatement(CREATE_IF_NEEDED);
            createTables.execute();
        } catch (SQLException e) {
            logger.error("Error with post repository: {}", e);
        }
    }

    public int getNextPostId() throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_MAX_POST_ID);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("max_id");
    }

    public boolean createNewPost(Post post) throws SQLException {
        logger.debug("Adding a new post: {}", post);
        Connection conn = database.getConnection();
        PreparedStatement ps = conn.prepareStatement(INSERT_NEW_POST);
        ps.setString(1, post.getText());
        ps.setInt(2, post.getLength());
        ps.setTimestamp(3, Timestamp.valueOf(post.getDateCreated()));
        //TODO Associate to a user... And create correct schema

        int error = ps.executeUpdate();
        logger.debug("New post return code: {}", error);
        return error > 0;
    }

    public Post findPostById(int postId) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement ps = conn.prepareStatement(FIND_POST_BY_ID);
        ps.setInt(1, postId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return parseToPost(rs);
    }
    public List<Post> findAllPosts() throws SQLException {
        Connection conn = database.getConnection();
        ResultSet rs = conn.prepareStatement(FIND_ALL_POSTS).executeQuery();

        List<Post> posts = new ArrayList<>();
        while(rs.next()) {
            posts.add(parseToPost(rs));
        }
        return posts;
    }
    public List<Post> findPostRange(int low, int high) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement ps = conn.prepareStatement(FIND_POST_RANGE);
        ps.setInt(1, low);
        ps.setInt(2, high);
        ResultSet rs = ps.executeQuery();

        List<Post> posts = new ArrayList<>();
        while(rs.next()) {
            posts.add(parseToPost(rs));
        }
        return posts;
    }

    private static Post parseToPost(ResultSet result) throws SQLException {
        Post post = new Post();
        post.setId(result.getInt("post_id"));
        post.setText(result.getString("text"));
        post.setLength(result.getInt("length"));
        post.setDateCreated(result.getTimestamp("date_created").toLocalDateTime());
        return post;
    }

    public static PostRepository getInstance() {
        if(instance == null) {
            instance = new PostRepository(null);
        }

        return instance;
    }
    public static PostRepository getInstance(@SuppressWarnings("SameParameterValue") Database database) {
        if(instance == null) {
            instance = new PostRepository(database);
        }

        return instance;
    }
}
