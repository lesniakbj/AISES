var AISES = AISES || {};

/**
 * Created by Brendan on 7/23/2016.
 */
AISES.Routes = {
    INDEX: '/',
    LOGIN: 'templates/login.tmpl',
    HOME: 'templates/home.tmpl',
    
    routeTo: function(route) {
        switch(route){
            case AISES.Routes.LOGIN:
                AISES.LoginController.loadLogin();
                break;
            case AISES.Routes.HOME:
                AISES.HomeController.loadHome();
                break;
            default:
                break;
        }
    },
    
    Templates: {
        POST: 'templates/post.tmpl'
    }
}