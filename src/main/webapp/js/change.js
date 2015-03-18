$(function($) {
	// OB宣言時におけるメール停止項目
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

	// 作品提出における入力フォーム切り替え
	// var submitFileTypeValFarst = $('.submitProductFileType:checked').val();
	// if (submitFileTypeValFarst == 2) {
	// 	 	$('#programForm').show();
	// 	 	$('#imageForm').hide();
	// 	 	$('#dtmForm').hide();
	// 	 } else if (submitFileTypeValFarst == 1) {
	// 	 	$('#programForm').hide();
	// 	 	$('#imageForm').show();
	// 	 	$('#dtmForm').hide();
	// 	 } else if (submitFileTypeValFarst == 3) {
	// 	 	$('#programForm').hide();
	// 	 	$('#imageForm').hide();
	// 	 	$('#dtmForm').show();
	// 	 } else{
	// 	 	$('#programForm').hide();
	// 	 	$('#imageForm').hide();
	// 	 	$('#dtmForm').hide();
	// 	 };
	
	// $(".submitProductFileType").click(function(){
	// 	 var submitFileTypeVal = $(this).val();
	// 	 $('.submitProductFileTypeHidden').val(submitFileTypeVal);
	// 	 if (submitFileTypeVal == 2) {
	// 	 	$('#programForm').show();
	// 	 	$('#imageForm').hide();
	// 	 	$('#dtmForm').hide();
	// 	 } else if (submitFileTypeVal == 1) {
	// 	 	$('#programForm').hide();
	// 	 	$('#imageForm').show();
	// 	 	$('#dtmForm').hide();
	// 	 } else if (submitFileTypeVal == 3) {
	// 	 	$('#programForm').hide();
	// 	 	$('#imageForm').hide();
	// 	 	$('#dtmForm').show();
	// 	 };
	// });
});