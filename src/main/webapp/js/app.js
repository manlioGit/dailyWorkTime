$(document).ready(function() {
	  
	var holidays = ['20150310', '20150319', '20150408'];

	var eventData = {};
	var calendarHeader = {left: 'prev today', center: 'title', right: 'next'};
	
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
			$(cell).css("background-color", "#f8f8f8");
		}
	
		if($.inArray(date.format('YYYYMMDD'), holidays) >= 0){
			$(cell).css("background-color", "#f8f8f8");
			$(cell).text("Easter");
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
	
	$("#submitBtn").on('click', function() {

		var title = $("option:selected").text();
		if (title) {
			
			eventData.title = title;
			eventData.color = "green";

			$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
		}
		$('#calendar').fullCalendar('unselect');

		$('#eventForm').modal('hide');
		$('#timeForm').modal('hide');
	});
	
	
	var fullCalendar = {
			header: calendarHeader,
			firstDay: 1,
			fixedWeekCount: false,
			dayRender: dayRender,
	    	select: select,
		    selectable: true,
			editable: true,
			eventLimit: true
		}
	
	$("#calendar").fullCalendar(fullCalendar);
});
