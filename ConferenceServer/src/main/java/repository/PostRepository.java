package repository;

import domain.Post;
import server.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brendan on 7/25/2016.
 */
public class PostRepository {
    private static PostRepository instance;
    private Database database;

    private static final String DROP_POST_TABLE = "DROP TABLE Post";
    private static final String CREATE_POST_TABLE = "CREATE TABLE IF NOT EXISTS Post(post_id SERIAL NOT NULL PRIMARY KEY, text varchar(1024) NULL, length int NULL, date_created TIMESTAMP WITHOUT TIME ZONE);";
    private static final String GET_MAX_ID = "SELECT MAX(post_id) AS max_id FROM Post";

    private static final String INSERT_NEW_POST = "INSERT INTO Post (text, length, date_created) VALUES(?, ?, ?)";

    private static final String FIND_POST_BY_ID = "SELECT * FROM Post WHERE post_id = ?";
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
        ps.setTimestamp(3, Timestamp.valueOf(post.getDateCreated()));
        ps.executeUpdate();
        return true;
    }

    public Post findPostById(int postId) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement ps = conn.prepareStatement(FIND_POST_BY_ID);
        ps.setInt(1, postId);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Post post = parseToPost(rs);
            return post;
        }

        return null;
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

    private static Post parseToPost(ResultSet result) throws SQLException {
        Post post = new Post();
        post.setId(result.getInt("post_id"));
        post.setText(result.getString("text"));
        post.setLength(result.getInt("length"));
        post.setDateCreated(result.getTimestamp("date_created").toLocalDateTime());
        return post;
    }
}
