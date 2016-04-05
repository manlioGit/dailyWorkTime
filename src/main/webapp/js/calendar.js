function Calendar(modal) {
	
	var dayOffColor = '#f8f8f8';
	
	function popOver(event){
		return 'start: ' + event.start.format("DD-MM-YYYY HH:mm") + '<br/>' + 
		       'end:   ' + event.end.format("DD-MM-YYYY HH:mm") + '<br/>' + 
		       '<a href="">delete</a> <a href="">cancel</a>';
	}
	
	var thisRef = this;

	this.header = {left: 'prev today', center: 'title', right: 'next'};
	this.firstDay = 1;
	this.fixedWeekCount = false;
	this.eventSources = [ 
	                { url: '/events/holiday', backgroundColor: dayOffColor, textColor: 'black',  borderColor: dayOffColor } 
                  ];
	this.selectable = true;
	this.editable = false;
	this.eventLimit = true;
	this.timeFormat = 'H(:mm)';
	this.displayEventEnd = true;
	this.eventClick = function (event, jsEvent) {
		$(this).popover({
            title: event.name,
            placement: 'right',
            trigger: 'manual',
            content: popOver(event),
            container: '#calendar',
            delay: { show: 0, hide: 100 },
            animation: true,
            html: true
        }).popover('toggle');
	};
	this.eventAfterRender = function(event, element, view) {
		if(event.action == "background") {
			$("td[data-date='" + event.start.format("YYYY-MM-DD") + "']").css("background-color", dayOffColor);
		}
	};
	this.eventAfterAllRender  = function(view){
		$("td[class~='fc-sat'],td[class~='fc-sun']").css("background-color", dayOffColor);
	};
	this.select = function (start, end) {
		
		$('.popover').popover('hide');
		
		thisRef.start = start;

		if(end.diff(start, 'days') <= 1){
			modal("single");
			thisRef.end = start;
		} 
		else {
			modal("period", false);
			thisRef.end = end;
		}
	};
	this.getDate = function() {
		return $("#calendar").fullCalendar('getDate');
	};
	this.move = function (howMuch){
    	$('[data-date="' + thisRef.getDate().format("YYYY-MM-DD") + '"]').removeClass("highlight-select");
    	
    	$("#calendar").fullCalendar( 'incrementDate', howMuch );
    	
    	$('[data-date="' + thisRef.getDate().format("YYYY-MM-DD") + '"]').addClass("highlight-select");
	};
}


