$(document).ready(function() {
	  
	var dayOffColor = '#f8f8f8';

	var eventData = {};
	var calendarHeader = {left: 'prev today', center: 'title', right: 'next'};
	
	var eventSources = [ { url: '/events/holiday', color: dayOffColor, textColor: 'black',  borderColor: 'black' } ];
	
	$('#timeForm').on('shown.bs.modal', function () {
  		$('#timeIn').focus();
	});
	
	$(document).on('click', '.btn.btn-default.btn-circle', function () {
	    $('#timeForm').remove();
	});
	
	$(document).on('click', '#switch', function () {
		var kind = $(this).attr("data-next")
	    modal(kind);
	});

	function modal(kind){
		$.post('/pages/modal/' + kind, function( data ) {
	    	$('#timeForm').remove();
	    	$( "body" ).append( data );
			$("#timeForm").modal('show');
		});
	}
	
	function dayRender(date, cell){
		
		var classValue = $(cell).attr('class');
		if(classValue.indexOf("fc-sat") > 0 || classValue.indexOf("fc-sun") > 0){
			$(cell).css("background-color", dayOffColor);
		}
	}
	
	function select(start, end) {
		
		if(start.isSame(end.clone().subtract(1,'days'))){
			modal("single");

			eventData.title = "work shift";
			eventData.color = "green";
		} 
		else {
			modal("period");
			
			eventData.title = $("option:selected").text();
			eventData.color = "red";
		}
		
		eventData.start = start;
		eventData.end = end;
	}
	
	$('.fa-check').parent().on('click keyup', function(e) {
		if(e.type == "click" || e.which == 13){
			
			$.post('/events/create', JSON.stringify(eventData), function(){
				$('#calendar').fullCalendar('renderEvent', eventData, true); 
			});

			$('#calendar').fullCalendar('unselect');

			$('#eventForm').modal('hide');
			$('#timeForm').modal('hide');
		}
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
	
	function currentDay(){
		return  $("#calendar").fullCalendar('getDate');
	}
	
	function move(howMuch){
    	$('[data-date="' + currentDay().format("YYYY-MM-DD") + '"]').removeClass("highlight-select");
    	
    	$("#calendar").fullCalendar( 'incrementDate', howMuch );
    	
    	$('[data-date="' + currentDay().format("YYYY-MM-DD") + '"]').addClass("highlight-select");
	}
	
	function partial(f, h){
		var args = [].slice.call(arguments, 1);
		return function() {
			f.apply(this, args.concat([].slice.call(arguments, 0)));
		}
	}
	
	$(document).keydown(function(e) {
	
		var map = {
			37: partial(move, { days:  -1 }),
			38: partial(move, { weeks: -1 }),
			39: partial(move, { days:   1 }),
			40: partial(move, { weeks:  1 }),
			13: function () {
				var start = currentDay();
				var end = start.clone().add(1,'days');
				$("#calendar").fullCalendar('select', start, end);
			}
		}
		
		var key = e.which;
		if(key in map) {
			map[key]();
			e.preventDefault(); 
		}
	});
});

