var AISES = AISES || {};

/**
 * Created by Brendan on 7/23/2016.
 */
AISES.HomeController = {
    loadedPosts: null,
    
    loadHome: function() {
        // Get the template and attach any data and events to it
        AISES.Template.loadTemplate(AISES.Routes.HOME, AISES.HomeController.initHome);
    },
    
    initHome: function(template) {
        $('#main-content').html(template);
        
        $('#new-post-button').on('click', AISES.HomeController.addNewPost);
        $('#load-all-posts').on('click', AISES.HomeController.loadPosts);
        
        AISES.HomeController.loadPosts();
    },
    
    addNewPost: function() {
        console.log("Adding new post");
        
        var post = {
            'text': $('#new-post-text').val()
        };
        
        if(post.text.length < 4) {
            alert("Please create a longer message");
            return;
        }
        
        $('#new-post-text').val('');
        $.post({
            url: AISES.Config.getDataServer() + '/post/new',
            data: JSON.stringify(post),
            dataType: 'json',
            beforeSend: function() { $('#dim-wrapper').show(); },
            success: function(data) { AISES.HomeController.loadPosts(); },
            complete: function(){ $('#dim-wrapper').hide(); },
            error: function(errorObj) { alert('Unable to add a new post! ' + errorObj.status + ' - ' + errorObj.statusText); }            
        });
    },
    
    loadPosts: function() {
        console.log(AISES.Config.getDataServer() + '/post/all');
        
        $.get({
            url: AISES.Config.getDataServer() + '/post/all',
            dataType: 'json',
            beforeSend: function() { $('#dim-wrapper').show(); },
            success: function(data) { AISES.HomeController.loadAllPosts(data); },
            complete: function(){ $('#dim-wrapper').hide(); },
            error: function(errorObj) { alert('Unable to retrieve posts! ' + errorObj.status + ' - ' + errorObj.statusText); }
        });
    },
    
    loadAllPosts: function(data) {
        console.log("Adding content");
        AISES.HomeController.loadedPosts = data;
        AISES.Template.loadTemplate(AISES.Routes.Templates.POST, AISES.HomeController.loadAllPostsDOM);
    },
    
    loadAllPostsDOM: function(template) {
        console.log("Adding post content");    
        $('#loaded-posts').empty();
        for(var postNum in AISES.HomeController.loadedPosts) {
            var post = AISES.HomeController.loadedPosts[postNum];
            var postData = {
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