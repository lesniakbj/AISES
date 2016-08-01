var AISES = AISES || {};

/**
 * Created by Brendan on 7/30/2016.
 */
AISES.Notifications = { 
    connect: function() {
        var socket = new WebSocket(AISES.Config.getDataServer().replace("http://", "ws://") + "/notifications");
        console.log(JSON.stringify(socket));
        socket.onopen = function(event) {
            
        };
        
        socket.onmessage = function(event) {
            var msg = event.data;
            alert(msg);
        };
    }
}