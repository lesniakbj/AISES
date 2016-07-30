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

    private static final String DROP_POST_TABLE = "DROP TABLE Post";
    private static final String CREATE_POST_TABLE = "CREATE TABLE IF NOT EXISTS Post(post_id SERIAL NOT NULL PRIMARY KEY, text varchar(1024) NULL, length int NULL, data_created TIMESTAMP WITHOUT TIME ZONE);";
    private static final String GET_MAX_ID = "SELECT MAX(post_id) AS max_id FROM Post";

    private static final String INSERT_NEW_POST = "INSERT INTO Post (text, length) VALUES(?, ?)";

    private static final String FIND_POST_BY_ID = "SELECT * FROM Post WHERE id = ?";
    private static final String FIND_ALL_POSTS = "SELECT * FROM Post";

    protected PostRepository(Database database) {
        if(database != null) {
            this.database = database;
        }

        Connection con = database.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(DROP_POST_TABLE);
            PreparedStatement ps2 = con.prepareStatement(CREATE_POST_TABLE);
            ps.execute();
            ps2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNextId() throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_MAX_ID);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int maxId = rs.getInt("max_id");
        return maxId;
    }

    public boolean createNewPost(Post post) throws SQLException {
        System.out.println("Adding a new post!");
        Connection conn = database.getConnection();
        PreparedStatement ps = conn.prepareStatement(INSERT_NEW_POST);
        ps.setString(1, post.getText());
        ps.setInt(2, post.getLength());
        ps.executeUpdate();

        return true;
    }

    public Post findPostById(int postId) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement ps = conn.prepareStatement(FIND_POST_BY_ID);
        ps.setInt(1, postId);

        Post post = new Post();
        post.setId(1);
        post.setLength(12);
        post.setText("HEllo!");

        return null;
    }
    public List<Post> findAllPosts() throws SQLException {
        Connection conn = database.getConnection();
        ResultSet rs = conn.prepareStatement(FIND_ALL_POSTS).executeQuery();

        List<Post> posts = new ArrayList<>();
        while(rs.next()) {
            Post post = new Post();
            post.setId(rs.getInt("post_id"));
            post.setText(rs.getString("text"));
            post.setLength(rs.getInt("length"));
            posts.add(post);
        }
        return posts;
    }

    public static PostRepository getInstance() {
        if(instance == null) {
            instance = new PostRepository(null);
        }

        return instance;
    }
    public static PostRepository getInstance(Database database) {
        if(instance == null) {
            instance = new PostRepository(database);
        }

        return instance;
    }

}
