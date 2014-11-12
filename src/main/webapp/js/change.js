$(function($) {
		var val = $("#obFlag:checked").val();
		if(val == "true") {
			$("#sendStopFlagBox").show();
		} else {
			$("#sendStopFlagBox").hide();
		}
		
		$("#obFlag").click(function() {
			var val = $("#obFlag:checked").val();
			if(val == "true") {
				$("#sendStopFlagBox").show();
			} else {
				$("#sendStopFlagBox").hide();
			}
		});
	});