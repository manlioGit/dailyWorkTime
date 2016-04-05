$(document).ready(function() {
	
	function modal(kind, switchButton){
		$.post('/pages/modal/' + kind + '/' + switchButton, function( data ) {
	    	$('#timeForm').remove();
	    	$( "body" ).append( data );
			$("#timeForm").modal('show');
			$("#timeForm").validator();
		});
	}
	
	function Event(attr, calendar) {
		
		function appendHour(time, selector){
			var value = $(selector).val();
			if(value != undefined) {
				return moment(time.format("YYYY-MM-DD") + " " + value, "YYYY-MM-DD HH:mm" );
			} else {
				return time;
			}
		}
		
		this.title = $( attr ).text();
		this.color = $( attr ).attr('data-color');
		this.start = appendHour(calendar.start, 'input[name=timeIn]');
		this.end = appendHour(calendar.end, 'input[name=timeOut]');
		this.isValid = function(){
			
		};
	}
	
	var c = new Calendar(modal);
	
	$("#calendar").fullCalendar(c);

	$(document).on('click', '#switch', function () {
		var kind = $(this).attr("data-next")
	    modal(kind);
	});
	
	$(document).on('shown.bs.modal', function () {
		  $('input[name="timeIn"]').focus();
	});
	
	$(document).on('hidden.bs.modal', function () {
	    $('#timeForm').remove();
	});
	
	$(document).on('click keyup', '[data-submit="modal"]', function(e) {
		
		if (!e.isDefaultPrevented()) {
			if(e.type == "click" || e.which == 13){
				
				var eventData = new Event("option:selected", c);
				
				$.post('/events/create', JSON.stringify(eventData), function(){
					$('#calendar').fullCalendar('renderEvent', eventData, true); 
				});
	
				$('#calendar').fullCalendar('unselect');
	
				$('#timeForm').remove();
			}
		}
	});
	 
	$(document).on('keydown', function(e) {
		
		if($('#timeForm').length == 0) {
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
		}
	});
});
