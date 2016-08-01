package com.aises.server;

/**
 * Created by Brendan on 7/24/2016.
 *
 * All the routes that are used within the system.
 */
public class Routes {
    public static final String LOGIN = "/login";

    public static final String UPLOAD = "/upload";

    public static final String POST_FILTER = "/post/*";
    public static final String POST_NEW = "/post/new";
    public static final String POST_ALL = "/post/all";
    public static final String POST_FIND = "/post/find";

    public static final String NOTIFICATIONS_FILTER = "/notifications/*";
    public static final String NOTIFICATIONS = "/notifications";
    public static final String NOTIFICATIONS_ADMIN = "/notifications/new";
}
