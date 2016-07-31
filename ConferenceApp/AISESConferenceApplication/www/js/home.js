var AISES = AISES || {};

AISES.HomeController = {
    loadedPosts: null,
    
    loadHome: function() {
        // Get the template and attach any data and events to it
        AISES.Template.loadTemplate(AISES.Routes.HOME, AISES.HomeController.initHome);
    },
    
    initHome: function(template) {
        $('#main-content').html(template);
        
        AISES.HomeController.loadPosts();
    },
    
    loadPosts: function() {
        console.log(AISES.Config.getDataServer() + '/post/all');
        
        $.get({
            url: AISES.Config.getDataServer() + '/post/all',
            dataType: 'json',
            beforeSend: function() { $('#loader').show(); },
            success: function(data) { AISES.HomeController.loadAllPosts(data); },
            complete: function(){ $('#loader').hide(); },
            error: function(errorObj) { alert('Unable to retrieve posts! ' + errorObj.status + ' - ' + errorObj.statusText); }
        });
    },
    
    loadAllPosts: function(data) {
        console.log("Adding content");
        AISES.HomeController.loadedPosts = data;
        AISES.Template.loadTemplate(AISES.Routes.Templates.POST, AISES.HomeController.loadPostContainer);
    },
    
    loadPostContainer: function(template) {
        console.log("Adding post content");       
        for(var postNum in AISES.HomeController.loadedPosts) {
            var post = AISES.HomeController.loadedPosts[postNum];
            postData = {
                id: post.id,
                text: post.text,
                length: post.length,
                date: AISES.HomeController.getPostDate(post)
            }
            var out = Mustache.render(template, postData);
            $('#loaded-posts').prepend(out);
        }
    },
    
    getPostDate: function(post) {
        return post.dateCreated.date.month + '/' + post.dateCreated.date.day + '/' + post.dateCreated.date.year + ' ' + post.dateCreated.time.hour + ':' + post.dateCreated.time.minute + ':' + post.dateCreated.time.second;
    }
};