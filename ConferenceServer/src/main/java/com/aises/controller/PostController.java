package com.aises.controller;

import com.aises.domain.Post;
import com.aises.server.Routes;
import com.aises.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Spark;
import com.aises.utils.JSONUtils;

import java.util.List;

/**
 * Created by Brendan on 7/25/2016.
 */
public class PostController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private static PostService postService;

    static {
        postService = PostService.getInstance();
    }

    public PostController() {
        logger.debug("Creating a controller for Posts");

        Spark.before(Routes.POST_FILTER, (req, resp) -> checkPostAuthorization(req, resp));

        Spark.post(Routes.POST_NEW, (req, resp) -> createNewPost(req, resp), JSONUtils.JSON());

        Spark.get(Routes.POST_ALL, (req, resp) -> getAllPosts(req, resp), JSONUtils.JSON());
        Spark.get(Routes.POST_RANGE, (req, resp) -> getPostRange(req, resp), JSONUtils.JSON());
        Spark.get(Routes.POST_FIND, (req, resp) -> getPost(req, resp), JSONUtils.JSON());

        Spark.after(Routes.POST_FILTER, (req, resp) -> addAjaxHeader(req, resp));
    }

    private void checkPostAuthorization(Request req, Response resp) {
        logger.debug("Checking user credentials before retrieving or sending posts");
        // Check session / cookies for information
    }

    private Post createNewPost(Request req, Response resp) {
        logger.debug("Creating new post");
        // From the request (body I believe) we will want
        // to get the JSON object that represents a new post.
        // Mainly, the text (and any attachments and metadata).
        Post post = (Post)JSONUtils.fromJSON(req.body(), Post.class);
        return postService.addNewPost(post);
    }

    private Post getPost(Request req, Response resp) {
        logger.debug("Getting single post");

        int postNumber = Integer.parseInt(req.params(":id"));
        return postService.getPost(postNumber);
    }
    private List<Post> getAllPosts(Request req, Response resp) {
        logger.debug("Getting all posts");

        return postService.getAllPosts();
    }
    private List<Post> getPostRange(Request req, Response resp) {
        logger.debug("Getting a range of posts");

        String low = req.queryParams("low");
        String high = req.queryParams("high");
        return postService.getPostRange(Integer.parseInt(low), Integer.parseInt(high));
    }

    private void addAjaxHeader(Request req, Response resp) {
        resp.header("Content-Type", "application/json");
    }
}
