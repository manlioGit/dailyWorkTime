var createCalendar = function (page, eventData) {
	var dayOffColor = '#f8f8f8';
	
	return {
		header: {left: 'prev today', center: 'title', right: 'next'},
		firstDay: 1,
		fixedWeekCount: false,
		eventSources: [ 
		                { url: '/events/holiday', backgroundColor: dayOffColor, textColor: 'black',  borderColor: dayOffColor } 
	                  ],
		selectable: true,
		editable: false,
		eventLimit: true,
		eventAfterRender: function(event, element, view) {
			if(event.action == "background") {
				$("td[data-date='" + event.start.format("YYYY-MM-DD") + "']").css("background-color", dayOffColor);
			}
		},
		eventAfterAllRender : function(view){
			$("td[class~='fc-sat'],td[class~='fc-sun']").css("background-color", dayOffColor);
		},
		select: function (start, end) {
			
			if(end.diff(start, 'days') <= 1){
				page.modal("single");
			} 
			else {
				page.modal("period", false);
			}
			
			eventData.start = start;
			eventData.end = end;
		},
		getDate: function() {
			return $("#calendar").fullCalendar('getDate');
		},
		eventMouseover: function (event, jsEvent) {
			
			$(this).popover({
	            title: event.name,
	            placement: 'right',
	            trigger: 'hover',
	            content: '<p>start: ' + event.start.format("DD-MM-YYYY hh:mm") + '<br/> end: ' + event.end.format("DD-MM-YYYY hh:mm") + '</p><br/><a href="">delete?</a>',
	            container: $(this).css( "color", "black" ),
                delay: { show: 200, hide: 100 },
                animation: true,
	            html: true
	        });
	       
	       return false;
		},
		eventMouseout: function( event, jsEvent, view ) { 
			$(this).popover('hide');
		},
		move: function (howMuch){
	    	$('[data-date="' + createCalendar().getDate().format("YYYY-MM-DD") + '"]').removeClass("highlight-select");
	    	
	    	$("#calendar").fullCalendar( 'incrementDate', howMuch );
	    	
	    	$('[data-date="' + createCalendar().getDate().format("YYYY-MM-DD") + '"]').addClass("highlight-select");
		}
	}
};
