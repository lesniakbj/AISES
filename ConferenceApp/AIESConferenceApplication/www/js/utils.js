var AIES = AIES || {};

AIES.Utils = {
    byID: function(id) {
        return document.getElementById(id);
    },
    
    byClass: function(clazz) {
        return document.getElementsByClassName(clazz);
    },
    
    isEmpty: function(item) {
        return (typeof item === 'undefined' || !item || item === '');
    },
    
    fillDiv: function(divID, content) {
        AIES.Utils.byID(divID).innerHTML = content;
    },
    
    clearDiv: function(divID) {
        AIES.Utils.byID(divID).innerHTML = '';
    }
};