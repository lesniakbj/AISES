var AISES = AISES || {};

/**
 * Created by Brendan on 7/30/2016.
 */
AISES.Notifications = { 
    socket: null,
    
    connect: function() {
        this.socket = new WebSocket(AISES.Config.getDataServer().replace("http://", "ws://") + "/notifications");
        console.log(this.socket);
        
        //this.socket.addEventListener('close', this.socketClose);
        this.socket.addEventListener('message', this.socketMessage);
        //this.socket.addEventListener('error', this.socketError);
    },
    
    socketMessage: function(event) {
        var messageObj = JSON.parse(event.data);
        console.log("Message Type: " + messageObj.type + ", Event: " + messageObj.event + ", Admin Message: " + messageObj.adminMessage);
        
        switch(messageObj.type){
            case "ADMIN":
                alert(messageObj.adminMessage);
                break;
            case "POST":
                AISES.Notifications.parsePostMessage(messageObj);
                break;
            case "CALENDAR":
                AISES.Notifications.parseCalendarMessage(messageObj);
                break;
            default:
                alert('Unknown event type!');
                break;
        }
        
    },
    
    sendMessage: function(tokenId, msg) {
        console.log(tokenId)
        
        var msgJson = {
            'userId': tokenId,
            'message': msg
        }
        
        this.socket.send(JSON.stringify(msgJson));
    },
    
    parsePostMessage: function(msg) {
        console.log("POST EVENT RECIEVED! " + msg);
    },
    
    parseCalendarMessage: function(msg) {
        console.log("CALENDAR EVENT RECIEVED! " + msg);  
    }
}