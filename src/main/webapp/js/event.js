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
	this.isValid = function() { 
		return $('form')[0].checkValidity(); 
	};
}