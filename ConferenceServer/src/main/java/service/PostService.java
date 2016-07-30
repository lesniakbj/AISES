package service;

import domain.Post;
import repository.PostRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Brendan on 7/25/2016.
 */
public class PostService {
    private static PostService instance;
    private static PostRepository postRepository;

    static {
        postRepository = PostRepository.getInstance();
    }

    protected PostService() {

    }

    public Post addNewPost(Post newPost) {
        try {
            int nextId = postRepository.getNextId();

            // Recreate the post here to ensure it is set the
            // the correct values before saving.
            newPost.setId(nextId);
            newPost.setText(newPost.getText());
            newPost.setLength(newPost.getText().length());

            if(postRepository.createNewPost(newPost)) {
                return newPost;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Post getPost(int postId) {
        if(postId < 0) {
            return null;
        }

        try {
            return postRepository.findPostById(postId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Post> getAllPosts() {
        try {
            return postRepository.findAllPosts();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PostService getInstance() {
        if(instance == null) {
            instance = new PostService();
        }
        return instance;
    }
}
