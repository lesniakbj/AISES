var AISES = AISES || {};

/**
 * Created by Brendan on 7/23/2016.
 */
AISES.DataStore = {
    type: null,
    isAvailable: null,
    
    initDataStore: function() {
        var datastore = window.localStorage;
        AISES.DataStore.type = "LOCALSTORE";
        AISES.DataStore.isAvailable = true;
        
        datastore.setItem('Test', 'This is a new test value!');
        
        /*
        var datastore = window.openDatabase('AISES', '0.0.1', 'AISES App Database', 1024 * 1024 * 10);
        AISES.DataStore.type = "WEBSQL";
        AISES.DataStore.isAvailable = true;
        */
    }
};