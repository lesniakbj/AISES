var AISES = AISES || {};

AISES.Config = { 
    dataProtocol: null,
    dataServer: null,
    dataPort: null,
    
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
    },
    
    configureDataServer: function() {
        AISES.Config.dataProtocol = "http://";
        AISES.Config.dataServer = "localhost";
        AISES.Config.dataPort = 5570;
    },
    
    getDataServer: function() {
        return this.dataProtocol + this.dataServer + ":" + this.dataPort;
    }
};