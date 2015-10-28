$(document).ready(function() {
	$("a:first" ).click(function(ev) {
			ev.preventDefault();
			$("form").submit();
		}
	);
	
	$( "input[type='password']:first").attr({
		  id: "pwdToMatch"
		}
	);
	
	$( "input[type='password']:last").attr({
		  "data-match": "#pwdToMatch",
		  "data-match-error": "Whoops, these don't match"
		}
	);
});