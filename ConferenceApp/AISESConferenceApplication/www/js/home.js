var AISES = AISES || {};

AISES.HomeController = {
    loadHome: function() {
        // Get the template and attach any data and events to it
        AISES.Template.loadTemplate(AISES.Routes.HOME, AISES.HomeController.initHome);
    },
    
    initHome: function(template) {
        $('#main-content').html(template);
    },
};