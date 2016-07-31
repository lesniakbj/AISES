var AISES = AISES || {};

/**
 * Created by Brendan on 7/23/2016.
 */
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