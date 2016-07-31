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

import java.util.Collections;
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

        Spark.before(Routes.POST_FILTER, this::checkPostAuthorization);

        Spark.post(Routes.POST_NEW, this::createNewPost, JSONUtils.JSON());

        Spark.get(Routes.POST_ALL, this::getAllPosts, JSONUtils.JSON());
        Spark.get(Routes.POST_FIND, this::getPostFromQuery, JSONUtils.JSON());

        Spark.after(Routes.POST_FILTER, this::addAjaxHeader);
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

    private List<Post> getPostFromQuery(Request req, Response resp) {
        logger.debug("Interpreting post query: {}", req.queryString());
        if(req.queryParams().contains("id")) {
            return getSinglePost(req, resp);
        }

        if(req.queryParams().contains("low") && req.queryParams().contains("high")) {
            return getPostRange(req, resp);
        }

        return null;
    }

    private List<Post> getSinglePost(Request req, Response resp) {
        logger.debug("Getting a single post");
        int postNumber = Integer.parseInt(req.queryParams("id"));
        return Collections.singletonList(postService.getPost(postNumber));
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
