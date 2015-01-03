$(function($) {
    	var val = $("input[name='activeOrOb']:checked").val();
		if(val == '2') {
			$(".clubListCheck").hide("");
		} else {
			$(".clubListCheck").show();
		}
		var val = $("input[name='allOrClub']:checked").val();
		if(val == '1') {
			$("#selectClubDiv").hide();
		} else {
			$("#selectClubDiv").show();
		}
		
    	$(".activeOrOb").click(function() {
    		var val = $("input[name='activeOrOb']:checked").val();
    		if(val == '2') {
    			$(".clubListCheck").hide("normal");
    		} else {
    			$(".clubListCheck").show("normal");
    		}
    	});
    	$(".allOrClub").click(function() {
    		var val = $("input[name='allOrClub']:checked").val();
    		if(val == '1') {
    			$("#selectClubDiv").hide("normal");
    		} else {
    			$("#selectClubDiv").show("normal");
    		}
    	});
    });