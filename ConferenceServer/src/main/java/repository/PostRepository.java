package repository;

import domain.Post;
import server.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brendan on 7/25/2016.
 */
public class PostRepository {
    private static PostRepository instance;
    private Database database;

    private static final String FIND_POST_BY_ID = "SELECT * FROM Post WHERE id = ?";
    private static final String FIND_ALL_POSTS = "SELECT * FROM Post";

    protected PostRepository() {}

    public Post findPostById(int postId) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement ps = conn.prepareStatement(FIND_POST_BY_ID);
        ps.setInt(1, postId);

        Post post = new Post();
        post.setId(1);
        post.setTextLength(12);
        post.setText("HEllo!");

        return null;
    }

    public List<Post> findAllPosts() throws SQLException {
        //Connection conn = database.getConnection();
        //ResultSet rs = conn.prepareStatement(FIND_ALL_POSTS).executeQuery();

        Post post1 = new Post();
        post1.setId(1);
        post1.setTextLength(12);
        post1.setText("HEllo!");


        Post post2 = new Post();
        post2.setId(2);
        post2.setTextLength(24);
        post2.setText("asdfasflo!");

        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);

        return posts;
    }

    public static PostRepository getInstance() {
        if(instance == null) {
            instance = new PostRepository();
        }

        return instance;
    }

    public Database getDatabase() {
        return database;
    }
    public void setDatabase(Database database) {
        this.database = database;
    }
}
