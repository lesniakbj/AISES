package com.aises.controller;

import com.aises.controller.interfaces.Controller;
import com.aises.domain.SocialLogin;
import com.aises.domain.User;
import com.aises.domain.enums.SocialLoginType;
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
        Spark.before(Routes.LOGIN_MASK, (req, resp) -> checkUserLogin(req));
        Spark.post(Routes.LOGIN, (req, resp) -> checkIfUserExists(req, resp), JSONUtils.JSON());
        Spark.post(Routes.LOGIN_NEW, (req, resp) -> createNewLogin(req, resp), JSONUtils.JSON());
        Spark.after(Routes.LOGIN_MASK, (req, resp) -> JSONUtils.addAjaxHeader(resp));
    }

    private void checkUserLogin(Request req) {
        logger.debug("Checking user credentials!");
    }

    private SocialLogin checkIfUserExists(Request req, Response resp) {
        logger.debug("Logging in user");

        SocialLogin socialLogin = (SocialLogin) JSONUtils.fromJSON(req.body(), SocialLogin.class);
        socialLogin.setProvider(SocialLoginType.valueOf(req.queryParams("provider").toUpperCase().trim()));

        boolean loginExists = loginService.checkExistingSocialLogin(socialLogin);
        if(loginExists) {
            logger.debug("Existing login found");
            return socialLogin;
        }

        socialLogin.setSocialMediaId("-1");
        return socialLogin;
    }

    private User createNewLogin(Request req, Response resp) {
        logger.debug("Creating new login for user");

        User user = (User) JSONUtils.fromJSON(req.body(), User.class);
        logger.debug("User to create login for: {}", user);
        return loginService.createNewUser(user);
    }
}
