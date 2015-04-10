$(document).ready(function() {
	  
	var holidays = ['20150310', '20150319', '20150408'];

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

	$("#submitBtn").on('click', function() {

		var title = $("option:selected").text();
		if (title) {
			var eventData = {
				title: title,
				start: moment($("#start").val()),
				end: moment($("#end").val()),
				color: "green"
			};
			$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
		}
		$('#calendar').fullCalendar('unselect');

		$('#eventForm').modal('hide');
		$('#timeForm').modal('hide');
	});

	$("#calendar").fullCalendar({
		header: {
			left: 'prev today',
			center: 'title',
			right: 'next'
		},
		firstDay: 1,
		fixedWeekCount: false,
		dayRender: function (date, cell) {
			var classValue = $(cell).attr('class');
			if(classValue.contains("fc-sat") || classValue.contains("fc-sun")){
				$(cell).css("background-color", "#f8f8f8");
			}
		
			if($.inArray(date.format('YYYYMMDD'), holidays) >= 0){
				$(cell).css("background-color", "#f8f8f8");
				$(cell).text("Easter");
			}
    		},
	    	selectable: true,
		select: function(start, end) {
			var compare = end.clone();
			if(start.isSame(compare.subtract(1,'days'))){
				$("#timeForm").modal('show');
				$("#toTimeFormBtn").show();
				$("#start").val(start);
				$("#end").val(end);
			
			} 
			else {
				$("#eventForm").modal('show');
				$("#toTimeFormBtn").hide();
				$("#start").val(start);
				$("#end").val(end);
			}
		},
		editable: true,
		eventLimit: true
	});
});
