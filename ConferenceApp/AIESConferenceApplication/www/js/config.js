var AIES = AIES || {};

AIES.Config = { 
    deviceType: null,
    deviceRoot: null,
    
    configureDeviceType: function() {
        console.log("Checking device type");
        if(navigator.splashscreen && navigator.splashscreen.hide) {
            navigator.splashscreen.hide();
            AIES.Config.deviceType = 'CORDOVA';
        } else {
            AIES.Config.deviceType = 'HTML';
        }
    },
    
    configureResourceRoot: function() {
        console.log("Checking resource root");
        var root = window.location.href;
        var pattern = /(\S*(?=www))/;      
        AIES.Config.deviceRoot = pattern.exec(root);
    }
};