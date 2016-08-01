var AISES = AISES || {};

/**
 * Created by Brendan on 7/23/2016.
 */
AISES.History = {
    routeHistory: [],
    
    pushRoute: function(route) {
        return AISES.History.routeHistory.push(route);
    },
    
    popRoute: function(route) {
        $('#dim-wrapper').hide();
        
        if(AISES.History.routeHistory.length == 0) {
            console.log("Supposed to exit");
            navigator.app.exitApp();
        }
        
        return AISES.History.routeHistory.pop(route);
    }
}