var AISES = AISES || {};

/**
 * Created by Brendan on 7/23/2016.
 */
AISES.App = {
    init: function() {
        // First we need to configure determine what 
        // our device capabilities are, and where we 
        // were installed to.
        AISES.Config.configureDeviceType();
        AISES.Config.configureResourceRoot();
        AISES.Config.configureDataServer();
        
        // Then we can initialize the main datastore,
        // currently only supporting WebSQL or LocalStorage.
        AISES.DataStore.initDataStore();
        
        // Then we need to route to the login screen
        AISES.History.pushRoute(AISES.Routes.LOGIN);
        AISES.Routes.routeTo(AISES.Routes.LOGIN);
    },
    
    handleBackButton: function() {
        AISES.Routes.routeTo(AISES.History.popRoute());
    }
};

document.addEventListener("app.Ready", AISES.App.init, false);
document.addEventListener("backbutton", AISES.App.handleBackButton, false);