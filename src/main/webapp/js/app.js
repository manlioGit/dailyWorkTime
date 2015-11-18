$(document).ready(function() {
	  
	var dayOffColor = '#f8f8f8';

	var eventData = {};
	var calendarHeader = {left: 'prev today', center: 'title', right: 'next'};
	
	var eventSources = [ { url: '/events/holiday', color: dayOffColor, textColor: 'black',  borderColor: 'black' } ];
	
	$('#timeForm').on('shown.bs.modal', function () {
  		$('#timeIn').focus();
	});

	$('#toTimeFormBtn').click(function () {
		$('#eventForm').modal('hide');
		$('#timeForm').modal('show');
	});
	
	$('#toEventForm').click(function () {
		$('#timeForm').modal('hide');
		$('#eventForm').modal('show');
	});

	function dayRender(date, cell){
		
		var classValue = $(cell).attr('class');
		if(classValue.contains("fc-sat") || classValue.contains("fc-sun")){
			$(cell).css("background-color", dayOffColor);
		}
	}
	
	function select(start, end) {
		
		if(start.isSame(end.clone().subtract(1,'days'))){
			$("#timeForm").modal('show');
			$("#toTimeFormBtn").show();
		} 
		else {
			$("#eventForm").modal('show');
			$("#toTimeFormBtn").hide();
		}
		
		eventData.start = start;
		eventData.end = end;
	}
	
	$('.fa-check').parent().on('click', function() {

		var title = $("option:selected").text();
		if (title) {
			
			eventData.title = title;
			eventData.color = "green";
			$.post('/events/create', JSON.stringify(eventData), function(){
				$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
			}, 'json');
		}
		$('#calendar').fullCalendar('unselect');

		$('#eventForm').modal('hide');
		$('#timeForm').modal('hide');
	});
	
	var sels = function(){
		return $('.highlight-select')
	}
	
	$(document).keydown(function(e) {
		var current = sels()
	    switch(e.which) {
	        case 37: // left
	        	var mom = $("#calendar").fullCalendar('getDate').format("YYYY-MM-DD")
	        	$('[data-date="' + mom + '"]').removeClass("highlight-select")
	        	
	        	$("#calendar").fullCalendar( 'incrementDate', { days: -1 } )
	        	
	        	var mom = $("#calendar").fullCalendar('getDate').format("YYYY-MM-DD")
	        	$('[data-date="' + mom + '"]').addClass("highlight-select")
	        	
	        break;

	        case 38: // up
	        	var mom = $("#calendar").fullCalendar('getDate').format("YYYY-MM-DD")
	        	$('[data-date="' + mom + '"]').removeClass("highlight-select")
	        	
	        	$("#calendar").fullCalendar( 'incrementDate', { weeks: -1 } )
	        	
	        	var mom = $("#calendar").fullCalendar('getDate').format("YYYY-MM-DD")
	        	$('[data-date="' + mom + '"]').addClass("highlight-select")
	        break;

	        case 39: // right
	        	var mom = $("#calendar").fullCalendar('getDate').format("YYYY-MM-DD")
	        	$('[data-date="' + mom + '"]').removeClass("highlight-select")
	        	
	        	$("#calendar").fullCalendar( 'incrementDate', { days:1 } )
	        	
	        	var mom = $("#calendar").fullCalendar('getDate').format("YYYY-MM-DD")
	        	$('[data-date="' + mom + '"]').addClass("highlight-select")
	        break;

	        case 40: // down
	        	var mom = $("#calendar").fullCalendar('getDate').format("YYYY-MM-DD")
	        	$('[data-date="' + mom + '"]').removeClass("highlight-select")
	        	
	        	$("#calendar").fullCalendar( 'incrementDate', { weeks: 1 } )
	        	
	        	var mom = $("#calendar").fullCalendar('getDate').format("YYYY-MM-DD")
	        	$('[data-date="' + mom + '"]').addClass("highlight-select")
	        break;
	        	
	        case 13: //Enter
	        	alert($("#calendar").fullCalendar('getDate').format("YYYY-MM-DD"))
	        break;

	        default: return; // exit this handler for other keys
	    }
	    e.preventDefault(); // prevent the default action (scroll / move caret)
	});
	
	var fullCalendar = {
			header: calendarHeader,
			firstDay: 1,
			fixedWeekCount: false,
			dayRender: dayRender,
	    	select: select,
	    	eventSources: eventSources,
		    selectable: true,
			editable: true,
			eventLimit: true
		}
	
	$("#calendar").fullCalendar(fullCalendar);

	$('.fc-state-highlight').addClass("highlight-select")
});
