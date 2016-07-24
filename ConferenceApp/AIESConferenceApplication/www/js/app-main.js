var AIES = AIES || {};

AIES.App = {
    init: function() {
        // First we need to configure determine what 
        // our device capabilities are, and where we 
        // were installed to.
        AIES.Config.configureDeviceType();
        AIES.Config.configureResourceRoot();
        
        // Then we can initialize the main datastore,
        // currently only supporting WebSQL or LocalStorage.
        AIES.DataStore.initDataStore();
        
        // Then we need to route to the login screen
        AIES.Routes.routeTo(AIES.Routes.LOGIN);
    }
};

document.addEventListener("app.Ready", AIES.App.init, false);