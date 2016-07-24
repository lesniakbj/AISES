var AIES = AIES || {};

AIES.Config = { 
    defaultConsole: null,
    
    configureDeviceType: function() {
        console.log("Checking device type");
        if(navigator.splashscreen && navigator.splashscreen.hide) {
            navigator.splashscreen.hide();
            AIES.App.deviceType = 'CORDOVA';
        } else {
            AIES.App.deviceType = 'HTML';
        }
    },
    
    configureResourceRoot: function() {
        console.log("Checking resource root");
        var root = window.location.href;
        var pattern = /(\S*(?=www))/;      
        AIES.App.deviceRoot = pattern.exec(root);
    },
    
    configureConsoleOutput: function() {
        console.log("Configuring console");
        var logger = document.getElementById('log');
        AIES.Config.defaultConsole = console.log;
        
        console.log = function (message) {
            if (typeof message == 'object') {
                logger.innerHTML += (JSON && JSON.stringify ? JSON.stringify(message) : String(message)) + '<br>';
            } else {
                logger.innerHTML += message + '<br>';
            }
        }
    }
};