var AIES = AIES || {};

AIES.LoginController = {
    loadLogin: function() {
        console.log("Loading login resources and templates");
        
        // Get the template and attach any data and events to it
        AIES.Template.loadTemplate(AIES.Routes.LOGIN, AIES.LoginController.initLogin);
    },
    
    initLogin: function(template) {
        // Parse the template to the hidden Div
        AIES.Utils.fillDiv('hidden-content', template);
        
        // Populate any fields in the hidden content, if needed... None here...
        // Then replicate to the visible field...
        AIES.Utils.fillDiv('main-content', AIES.Utils.byID('hidden-content').innerHTML);
        AIES.Utils.clearDiv('hidden-content');
        
        // Attach any events here...
        AIES.Utils.byID('login-submit').addEventListener('click', AIES.LoginController.handleLogin);
        AIES.Utils.byID('login-create').addEventListener('click', AIES.LoginController.handleCreateLogin);
    },
    
    handleLogin: function() {
        var us = AIES.Utils.byID('login-username').value;
        var pas = AIES.Utils.byID('login-password').value;
        
        if(AIES.Utils.isEmpty(us) || AIES.Utils.isEmpty(pas)) {
            alert('Username and Password cannot be empty! Please try again.');
            return;
        }
        
        console.log('Attempting to login using [' + us + '|' + pas + ']');
        AIES.Routes.routeTo(AIES.Routes.HOME);
    },
    
    handleCreateLogin: function() {
        console.log('Creating new Login... Getting Template...');
        return;
    }
};