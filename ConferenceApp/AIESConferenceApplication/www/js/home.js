var AIES = AIES || {};

AIES.HomeController = {
    loadHome: function() {
        console.log('Loading Home view and resources');
        // Get the template and attach any data and events to it
        AIES.Template.loadTemplate(AIES.Routes.HOME, AIES.HomeController.initHome);
    },
    
    initHome: function(template) {
        // Parse the template to the hidden Div
        AIES.Utils.fillDiv('hidden-content', template);
        
        // Populate any fields in the hidden content, if needed...
        // Then replicate to the visible field...
        AIES.Utils.fillDiv('main-content', AIES.Utils.byID('hidden-content').innerHTML);
        AIES.Utils.clearDiv('hidden-content');
    },
};