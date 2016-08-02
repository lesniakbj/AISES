package com.aises.controller;

import com.aises.controller.interfaces.Controller;
import com.aises.domain.User;
import com.aises.server.Routes;
import com.aises.service.LoginService;
import com.aises.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Spark;

/**
 * Created by Brendan on 8/1/2016.
 *
 * Created to handle interfacing our systems users
 * with social media logins
 */
public class LoginController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private static final LoginService loginService;

    static {
        loginService = LoginService.getInstance();
    }

    public LoginController() {
        logger.debug("Creating a controller for Logins");
        Spark.before(Routes.LOGIN, (req, resp) -> checkUserLogin(req));
        Spark.post(Routes.LOGIN, (req, resp) -> checkLogin(req, resp), JSONUtils.JSON());
        Spark.after(Routes.LOGIN, (req, resp) -> JSONUtils.addAjaxHeader(resp));
    }

    private void checkUserLogin(Request req) {
        logger.debug("Checking user credentials!");
    }

    private boolean checkLogin(Request req, Response resp) {
        logger.debug("Logging in user");

        User user = (User) JSONUtils.fromJSON(req.body(), User.class);
        String type = req.queryParams("social-type");
        logger.debug("Type requested: {}", type);
        return loginService.checkExistingSocialLogin(user, type);
    }
}
