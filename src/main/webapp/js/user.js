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
	
	if($( "input[type='password']").length > 1){
		$( "input[type='password']:last").attr({
			  "data-match": "#pwdToMatch",
			  "data-match-error": "Whoops, these don't match"
			}
		);
	}
});