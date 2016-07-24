var AIES = AIES || {};

AIES.Utils = {
    byID: function(id) {
        return document.getElementById(id);
    },
    
    byClass: function(clazz) {
        return document.getElementsByClassName(clazz);
    }
};