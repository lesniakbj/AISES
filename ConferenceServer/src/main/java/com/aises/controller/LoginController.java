package com.aises.controller;

import com.aises.controller.interfaces.Controller;
import com.aises.domain.User;
import com.aises.server.Routes;
import com.aises.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Spark;

/**
 * Created by Brendan on 8/1/2016.
 */
public class LoginController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController() {
        logger.debug("Creating a controller for Logins");
        Spark.post(Routes.LOGIN, (req, resp) -> checkUserLogin(req, resp), JSONUtils.JSON());
        Spark.after(Routes.LOGIN, (req, resp) -> JSONUtils.addAjaxHeader(resp));
    }

    private User checkUserLogin(Request req, Response resp) {
        logger.debug("Checking user credentials!");
        return null;
    }
}
