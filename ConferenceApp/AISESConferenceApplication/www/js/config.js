var AISES = AISES || {};

AISES.Config = { 
    deviceType: null,
    deviceRoot: null,
    
    configureDeviceType: function() {
        if(navigator.splashscreen && navigator.splashscreen.hide) {
            navigator.splashscreen.hide();
            AISES.Config.deviceType = 'CORDOVA';
        } else {
            AISES.Config.deviceType = 'HTML';
        }
    },
    
    configureResourceRoot: function() {
        var root = window.location.href;
        var pattern = /(\S*(?=www))/;      
        AISES.Config.deviceRoot = pattern.exec(root);
    }
};