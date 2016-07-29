package controller;

import domain.Post;
import service.PostService;
import spark.Request;
import spark.Response;
import spark.Spark;
import utils.JSONUtils;

import java.util.List;

/**
 * Created by Brendan on 7/25/2016.
 */
public class PostController implements Controller {
    private static PostService postService;

    static {
        postService = PostService.getInstance();
    }

    public PostController() {
        Spark.before("/post/*", (req, resp) -> checkPostAuthorization(req, resp));

        Spark.get("/post/all", (req, resp) -> getAllPosts(req, resp), JSONUtils.JSON());
        Spark.get("/post/:id", (req, resp) -> getPost(req, resp), JSONUtils.JSON());

        //Spark.post("/post/new", (req, resp) -> addNewPost(req, resp));
    }

    private void checkPostAuthorization(Request req, Response resp) {
        System.out.println("Checking user credentials before retrieving or sending posts");
        // Check session / cookies for information
    }

    private Post getPost(Request req, Response resp) {
        int postNumber = Integer.parseInt(req.params(":id"));
        return postService.getPost(postNumber);
    }

    private List<Post> getAllPosts(Request req, Response resp) {
        return postService.getAllPosts();
    }
}