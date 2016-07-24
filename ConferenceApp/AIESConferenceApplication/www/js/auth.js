var AIES = AIES || {};

AIES.Auth = {
    monitorForm: function(user, pass, button) {
        AIES.Utils.byID(button).onclick = function() {
            console.log(AIES.Utils.byID(user).value);
            console.log(AIES.Utils.byID(pass).value);
        }
    }
};