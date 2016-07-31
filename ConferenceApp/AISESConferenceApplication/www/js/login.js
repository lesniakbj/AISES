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
        
        /*
        $.get({
            url: 'AISES.Config.getDataServer() + /api/login',
            dataType: 'json',
            data: { 'User': us, 'Pass': pas },
            beforeSend: function() { $('#loader').show(); },
            success: function(data) { AISES.LoginController.loginSuccess(data); },
            complete: function(){ $('#loader').hide(); },
            error: function(errorObj) { alert('Unable to login! ' + errorObj.status + ' - ' + errorObj.statusText); }
        });
        */
        
        AISES.LoginController.loginSuccess(true);
    },
    
    loginSuccess: function(data) {
        // Save the auth token that we get so that
        // we can make later API requests. Also, we should
        // probably start a session with the server on login.
        console.log('Data: ' + JSON.stringify(data, null, 4));
        
        if(data == false) {
            alert('Unable to login! Error when attempting to validate credentials!');
        } else {
            // Once done...
            AISES.Utils.createCookie('LoginSession', data.sessionID);
            AISES.Routes.routeTo(AISES.Routes.HOME);
        }
    },
    
    handleCreateLogin: function() {
        return;
    }
};