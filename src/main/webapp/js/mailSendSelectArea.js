$(function($) {
        var val = $("input[name='mailSendFlag']:checked").val();
        if(val == 'true') {
            $("#mailInput").show();
        } else {
            $("#mailInput").hide();
        }
        $(".mailSendFlag").click(function() {
            var val = $("input[name='mailSendFlag']:checked").val();
            if(val == 'true') {
                $("#mailInput").show();
            } else {
                $("#mailInput").hide();
            }
        });
    });