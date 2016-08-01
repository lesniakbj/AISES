package com.aises.controller;

import com.aises.controller.interfaces.Controller;
import com.aises.domain.Notification;
import com.aises.domain.Post;
import com.aises.domain.enums.NotificationEvent;
import com.aises.domain.enums.NotificationType;
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
 *
 * A controller to handle the routes that create,
 * update, and delete posts from the system.
 */
public class PostController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private static final PostService postService;

    static {
        postService = PostService.getInstance();
    }

    public PostController() {
        logger.debug("Creating a controller for Posts");

        Spark.before(Routes.POST_FILTER, (req, resp) -> checkPostAuthorization());
        Spark.post(Routes.POST_NEW, (req, resp) -> createNewPost(req), JSONUtils.JSON());
        Spark.get(Routes.POST_ALL, (req, resp) -> getAllPosts(), JSONUtils.JSON());
        Spark.get(Routes.POST_FIND, (req, resp) -> getPostFromQuery(req), JSONUtils.JSON());
        Spark.after(Routes.POST_FILTER, (req, resp) -> JSONUtils.addAjaxHeader(resp));
    }

    private void checkPostAuthorization() {
        logger.debug("Checking user credentials before retrieving or sending posts");
        // Check session / cookies for information
    }

    private Post createNewPost(Request req) {
        logger.debug("Creating new post");
        // From the request (body I believe) we will want
        // to get the JSON object that represents a new post.
        // Mainly, the text (and any attachments and metadata).
        Post post = (Post)JSONUtils.fromJSON(req.body(), Post.class);
        logger.debug("Post created from request: {}", post);

        Post newPost = postService.addNewPost(post);
        if(newPost != null) {
            Notification ntf = new Notification();
            ntf.setType(NotificationType.POST);
            ntf.setEvent(NotificationEvent.NEW);
            NotificationController.sendNotification(ntf);
        }

        return newPost;
    }

    private List<Post> getPostFromQuery(Request req) {
        logger.debug("Interpreting post query: {}", req.queryString());
        if(req.queryParams().contains("id")) {
            return getSinglePost(req);
        }

        if(req.queryParams().contains("low") && req.queryParams().contains("high")) {
            return getPostRange(req);
        }

        return null;
    }
    private List<Post> getSinglePost(Request req) {
        logger.debug("Getting a single post");
        int postNumber = Integer.parseInt(req.queryParams("id"));
        return Collections.singletonList(postService.getPost(postNumber));
    }
    private List<Post> getAllPosts() {
        logger.debug("Getting all posts");

        return postService.getAllPosts();
    }
    private List<Post> getPostRange(Request req) {
        logger.debug("Getting a range of posts");

        String low = req.queryParams("low");
        String high = req.queryParams("high");
        return postService.getPostRange(Integer.parseInt(low), Integer.parseInt(high));
    }
}
