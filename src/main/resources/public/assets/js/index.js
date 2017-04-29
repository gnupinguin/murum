$(document).ready(function(){
    $(".like").click(function(){
        console.log('!!!');

        $.ajax({url: "/rest/like",
            type: 'POST',
            data: {
                id: USER_ID,
                action: true,
                author: 'qwerty'
            },
            success: function(data) {
                console.log(data);
            },
            error: function (e) {
                console.log(e);
            }
        });
    });
});
