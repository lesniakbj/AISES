package controller;

import domain.Post;
import server.Routes;
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
        Spark.before(Routes.POST_FILTER, (req, resp) -> checkPostAuthorization(req, resp));

        Spark.post(Routes.POST_NEW, (req, resp) -> ajaxResponse(req, resp, Routes.POST_NEW), JSONUtils.JSON());

        Spark.get(Routes.POST_ALL, (req, resp) -> ajaxResponse(req, resp, Routes.POST_ALL), JSONUtils.JSON());
        Spark.get(Routes.POST_FIND, (req, resp) -> ajaxResponse(req, resp, Routes.POST_FIND), JSONUtils.JSON());
    }

    @org.jetbrains.annotations.Nullable
    private Object ajaxResponse(Request req, Response resp, String route) {
        resp.header("Content-Type", "application/json");
        switch(route) {
            case Routes.POST_ALL:
                return getAllPosts(req, resp);
            case Routes.POST_FIND:
                return getPost(req, resp);
            case Routes.POST_NEW:
                return createNewPost(req, resp);
        }

        return null;
    }

    private void checkPostAuthorization(Request req, Response resp) {
        System.out.println("Checking user credentials before retrieving or sending posts");
        // Check session / cookies for information
    }

    private Post createNewPost(Request req, Response resp) {
        // From the request (body I believe) we will want
        // to get the JSON object that represents a new post.
        // Mainly, the text (and any attachments and metadata).
        Post post = (Post)JSONUtils.fromJSON(req.body(), Post.class);
        return postService.addNewPost(post);
    }

    private Post getPost(Request req, Response resp) {
        int postNumber = Integer.parseInt(req.params(":id"));
        return postService.getPost(postNumber);
    }
    private List<Post> getAllPosts(Request req, Response resp) {
        return postService.getAllPosts();
    }
}
