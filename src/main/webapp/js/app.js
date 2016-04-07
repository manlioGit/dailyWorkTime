$(document).ready(function() {
	
	function modal(kind, switchButton){
		$.post('/pages/modal/' + kind + '/' + switchButton, function( data ) {
			$('#timeForm').validator('destroy');
			$('#timeForm').remove();
	    	$( "body" ).append( data );
			$("#timeForm").modal('show');
			$("#timeForm").validator();
		});
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
		
		if(e.type == "click" || e.which == 13){
			
			var eventData = new Event("option:selected", c);
			if(eventData.isValid()) {
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
