var AISES = AISES || {};

/**
 * Created by Brendan on 7/23/2016.
 */
AISES.LoginController = {
    loadLogin: function() {
        AISES.Template.loadTemplate(AISES.Routes.LOGIN, AISES.LoginController.initLogin);
    },
    
    initLogin: function(template) {
        $('#main-content').html(template);
        
        // Attach any events here...
        $('#login-submit').on('click', AISES.LoginController.handleLogin);
    },
    
    handleLogin: function() {
        facebookConnectPlugin.login(["public_profile", "email"],
                                    AISES.LoginController.loginSuccess, 
                                    AISES.LoginController.loginFailure);
    },
    
    loginSuccess: function(userData) {
        // Save any user data before we leave
        AISES.Routes.routeTo(AISES.Routes.HOME);
    },
    
    loginFailure: function(error) {
        alert("Unable to login, please try again!");
        return;
    }
};