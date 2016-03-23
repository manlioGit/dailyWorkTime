$(document).ready(function() {
	
	var page = {
		modal: function (kind, switchButton){
			$.post('/pages/modal/' + kind + '/' + switchButton, function( data ) {
		    	$('#timeForm').remove();
		    	$( "body" ).append( data );
				$("#timeForm").modal('show');
				$("#timeForm").validator();
			});
		}
	}
	
	var eventData = {};
	var c = createCalendar(page, eventData);
	
	$("#calendar").fullCalendar(c);
	
	$(document).on('click', '[data-dismiss="modal"]', function () {
	    $('#timeForm').remove();
	});
	
	$(document).on('click', '#switch', function () {
		var kind = $(this).attr("data-next")
	    page.modal(kind);
	});
	
	$(document).on('click', '[data-submit="modal"]', function(e) {
		if(e.type == "click" || e.which == 13){
			
			eventData.title = $( "option:selected" ).text();
			eventData.color = $( "option:selected" ).attr('data-color');

			$.post('/events/create', JSON.stringify(eventData), function(){
				$('#calendar').fullCalendar('renderEvent', eventData, true); 
			});

			$('#calendar').fullCalendar('unselect');

			$('#timeForm').remove();
		}
	});
	 
	$(document).keydown(function(e) {
		var map = {
			37: c.move.bind(null, { days:  -1 }),
			38: c.move.bind(null, { weeks: -1 }),
			39: c.move.bind(null, { days:   1 }),
			40: c.move.bind(null, { weeks:  1 }),
			13: function () {
				var start = c.getDate();
				var end = start.clone();
				c.select(start, end);
			}
		}  
		
		var key = e.which;
		if(key in map) {
			map[key]();
			e.preventDefault(); 
		}
	});
});
