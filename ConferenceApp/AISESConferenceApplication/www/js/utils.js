var AISES = AISES || {};

/**
 * Created by Brendan on 7/23/2016.
 */
AISES.Utils = {
    isEmpty: function(item) {
        return (typeof item === 'undefined' || !item || item === '');
    },
    
    createCookie: function(name, value, days) {
        var expires;
        if(days) {
            var date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "; expires=" + date.toGMTString();
        }
        else {
            expires = "";
        }
        document.cookie = name + "=" + value + expires + "; path=/";
    }
};