var AIES = AIES || {};

AIES.Template = {
    readTemplate: function(templateName, params) {
        var rawFile = new XMLHttpRequest();
        rawFile.open("GET", AIES.App.deviceRoot + templateName, false);
        rawFile.onreadystatechange = function() {
            AIES.Template.parseTemplate(rawFile.responseText, params);
        }
        rawFile.send(null);
    },
    
    parseTemplate: function(template, params) {
        var rendered = Mustache.render(template, params);
        document.getElementById('main-content').innerHTML = rendered;
    }
};