var AIES = AIES || {};

AIES.DataStore = {
    type: null,
    
    initDataStore: function() {
        var datastore = window.openDatabase('AIES', '0.0.1', 'AIES App Database', 1024 * 1024 * 10);
        AIES.DataStore.type = "WEBSQL";
    }
};