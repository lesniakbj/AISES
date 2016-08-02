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
                                    AISES.LoginController.loginFacebookSuccess, 
                                    AISES.LoginController.loginFailure);
    },
    
    handleLoginGoogle: function() {
        alert("Unsupported at this time!");
    },
    
    loginFacebookSuccess: function(loginResponse) {
        if(loginResponse.status == "connected") {
            AISES.LoginController.notifyFacebookLogin(loginResponse.authResponse.userID);
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
    
    notifyFacebookLogin: function(userId) {
        var socialLogin = {
            'socialMediaId': userId
        };
        
        $.post({
            url: AISES.Config.getDataServer() + '/login?provider=facebook',
            data: JSON.stringify(socialLogin),
            dataType: 'json',
            beforeSend: function() { $('#dim-wrapper').show(); },
            success: function(response) { AISES.LoginController.checkIfLoginExists(response, socialLogin.socialMediaId) },
            complete: function(){ $('#dim-wrapper').hide(); },
            error: function(errorObj) { alert('Unable to verify login! ' + errorObj.status + ' - ' + errorObj.statusText); }            
        });
    },
    
    checkIfLoginExists: function(resp, loginId) {
        console.log(JSON.stringify(resp));
        if(resp.socialMediaId == "-1"){
            facebookConnectPlugin.api(user.socialMediaId + "/?fields=email,first_name,last_name", ["public_profile", "email"], AISES.LoginController.facebookUserData);
        } else {
            // Load user data through db
        }
    },
    
    facebookUserData: function() {
        alert(JSON.stringify(data, null, 4));
    }
};