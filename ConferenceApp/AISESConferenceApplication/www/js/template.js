var AISES = AISES || {};

AISES.Template = {
    loadTemplate: function(route, callback) {
        AISES.Template.readTemplate(route, callback);
    },
    
    readTemplate: function(route, callback) {
        $.get({
            url: route,
            success: callback,
            dataType: 'html'
        });
    }
};