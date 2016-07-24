var AIES = AIES || {};

AIES.Template = {
    loadTemplate: function(route, callback) {
        switch(route) {
            case AIES.Routes.LOGIN:
                AIES.Template.readTemplate('templates/login.tmpl', callback);
                break;
            case AIES.Routes.HOME:
                AIES.Template.readTemplate('templates/home.tmpl', callback);
                break;
        }
    },
    
    readTemplate: function(route, callback) {
        var rawFile = new XMLHttpRequest();
        
        rawFile.onreadystatechange = function(c) {
            if (rawFile.readyState == 4 && rawFile.status == 200) {
                if(typeof callback === 'function') {
                    callback(rawFile.responseText);
                }
            }
        }

        rawFile.open("GET", route, true);
        rawFile.send(null);
    }
};