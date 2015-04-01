$(function() {
        $('#calendar').fullCalendar({
        	events: {
        		url: '${f:url('/api/fulCalenderApi/getJson')}',
				error: function() {
					$('#script-warning').show();
				}
			},
			timeFormat: "H:mm",
			
			// クリックされたイベントの編集画面に遷移する
			eventClick: function(calEvent, jsEvent, view) {
				location.href = '../partyDetail/detail/' + calEvent.id;
			},
        });
        
    });