package com.aises.service;

import com.aises.domain.Post;
import com.aises.service.interfaces.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aises.repository.PostRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Brendan on 7/25/2016.
 *
 * A service layer that interfaces the Controller
 * to the Repositories.
 */
public class PostService implements Service {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    private static PostService instance;
    private static final PostRepository postRepository;

    static {
        postRepository = PostRepository.getInstance();
    }

    private PostService() {
        logger.debug("Creating a service for Posts");
    }

    public Post addNewPost(Post newPost) {
        try {
            int nextId = postRepository.getNextPostId();

            // Recreate the post here to ensure it is set the
            // the correct values before saving.
            newPost.setId(nextId);
            newPost.setUserId(newPost.getUserId());
            newPost.setText(newPost.getText());
            newPost.setLength(newPost.getText().length());
            newPost.setDateCreated(LocalDateTime.now());

            logger.debug("Attempting to add new post: {}", newPost);
            if(postRepository.createNewPost(newPost)) {
                return newPost;
            }
        } catch (SQLException e) {
            logger.error("Unable to add a new post!", e);
        }
        return null;
    }

    public Post getPost(int postId) {
        if(postId < 0) {
            logger.error("Can not get a post with ID less than 0!");
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
    public List<Post> getPostRange(int low, int high) {
        if(low < 0 || high < 0) {
            logger.error("Can not get a post with ID less than 0!");
            return null;
        }

        try {
            return postRepository.findPostRange(low, high);
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
