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
        $('#login-submit-facebook').on('click', AISES.LoginController.handleLoginFacebook);
        $('#login-submit-google').on('click', AISES.LoginController.handleLoginGoogle);
    },
    
    handleLoginFacebook: function() {
        facebookConnectPlugin.login(["public_profile", "email"],
                                    AISES.LoginController.loginSuccess, 
                                    AISES.LoginController.loginFailure);
    },
    
    handleLoginGoogle: function() {
        alert("Unsupported at this time!");
    },
    
    loginSuccess: function(loginResponse) {
        AISES.Notifications.sendMessage(loginResponse.authResponse.accessToken, "Hello Message!")
        
        // Save any user data before we leave
        AISES.History.pushRoute(AISES.Routes.LOGIN);
        AISES.Routes.routeTo(AISES.Routes.HOME);
    },
    
    loginFailure: function(error) {
        alert("Unable to login, please try again!" + JSON.stringify(error));
        console.log(error);
        return;
    }
};