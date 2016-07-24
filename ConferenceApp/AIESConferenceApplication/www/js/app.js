var AIES = AIES || {};

AIES.App = {
    deviceType: null,
    deviceRoot: null,
    datastoreAvailable: false,
    
    init: function() {
        AIES.Config.configureConsoleOutput();
        AIES.Config.configureDeviceType();
        AIES.Config.configureResourceRoot();
        
        AIES.DataStore.initDataStore();
        
        AIES.App.routeOnInitialLoad();
    },
    
    routeOnInitialLoad: function() {
        AIES.Template.readTemplate('www/templates/login.tmpl');
        AIES.Auth.monitorForm('login-username', 'login-password', 'login-submit');
    },
};

document.addEventListener("app.Ready", AIES.App.init, false);