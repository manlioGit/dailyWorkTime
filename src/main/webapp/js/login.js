$(document).ready(function() {
	$( "div.login-panel > * a" ).click(function(ev) {
			ev.preventDefault();
			//$.post( "./", {username: $("input[name='username']").val(), pswd:$("input[name='password']").val() } );
			$("form").submit();
		}
	);
});