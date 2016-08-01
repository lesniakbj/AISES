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
        $('#login-create').on('click', AISES.LoginController.handleCreateLogin);
    },
    
    handleLogin: function() {
        // First pass normalization
        var us = $('#login-username').val().trim();
        var pas = $('#login-password').val().trim();
        
        if(AISES.Utils.isEmpty(us) || AISES.Utils.isEmpty(pas)) {
            alert('Username and Password cannot be empty! Please try again.');
            return;
        }
        
        // Is this correct?
        facebookConnectPlugin.login(["public_profile", "email"], AISES.LoginController.loginSuccess, 
                                                                AISES.LoginController.loginFailure);
    },
    
    loginSuccess: function(userData) {
        console.log(JSON.stringify(userData));
        AISES.Routes.routeTo(AISES.Routes.HOME);
    },
    
    loginFailure: function(error) {
        console.log("Error logging in! " + error);
    },
    
    handleCreateLogin: function() {
        return;
    }
};