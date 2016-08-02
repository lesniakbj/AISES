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
        if(loginResponse.status == "connected") {
            AISES.LoginController.notifyLogin(loginResponse.authResponse.userID);
        }        
        // Save any user data before we leave
        AISES.History.pushRoute(AISES.Routes.LOGIN);
        AISES.Routes.routeTo(AISES.Routes.HOME);
    },
    
    loginFailure: function(error) {
        alert("Unable to login, please try again!" + JSON.stringify(error));
        console.log(error);
        return;
    },
    
    notifyLogin: function(userId) {
        var user = {
            'id': -1,
            'socialMediaId': userId
        };
        
        $.post({
            url: AISES.Config.getDataServer() + '/login?social-type=facebook',
            data: JSON.stringify(user),
            dataType: 'json',
            beforeSend: function() { $('#dim-wrapper').show(); },
            success: function(response) { AISES.LoginController.checkIfLoginExists(user, response) },
            complete: function(){ $('#dim-wrapper').hide(); },
            error: function(errorObj) { alert('Unable to verify login! ' + errorObj.status + ' - ' + errorObj.statusText); }            
        });
    },
    
    checkIfLoginExists: function(user, resp) {
        if(resp == false){
            facebookConnectPlugin.api(user.socialMediaId + "/?fields=email,first_name,last_name", ["public_profile", "email"], AISES.LoginController.facebookUserData);
        } else {
            // Load user data through db
        }
    },
    
    facebookUserData: function(data) {
        alert(JSON.stringify(data, null, 4));
    }
};