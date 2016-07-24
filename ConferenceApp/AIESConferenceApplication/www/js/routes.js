var AIES = AIES || {};

AIES.Routes = {
    INDEX: 'INDEX',
    LOGIN: 'LOGIN',
    HOME: 'HOME',
    
    routeTo: function(route) {
        switch(route){
            case AIES.Routes.LOGIN:
                AIES.LoginController.loadLogin();
                break;
            case AIES.Routes.HOME:
                AIES.HomeController.loadHome();
                break;
            default:
                break;
        }
    },
}