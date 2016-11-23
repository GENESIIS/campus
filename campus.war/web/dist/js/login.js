/**
 * 20161122 AS C19-student-login-without-using-third-party-application-test-as login.js class created.
 */
var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "/dist/js/institute/validation/validation.js";

function studentLogin(){
	
	var username = $("#email").val();
	var pass = $("#password").val();
	
	var usernametb = isempty(username);
	var passtb = isempty(pass);
	
	if (usernametb == false) {
		document.getElementById('fullNametbError').innerHTML = "  ** Email can not be Empty.";
		flag = false;
	}  if (passtb == false) {
		document.getElementById('emailtbError').innerHTML = "   ** Password can not be Empty.";
		flag = false;
	}
	
	if((username != null) && (pass != null)){
		
		var jsonData = {
				"username" : username,
				"password" : pass
		};
		$.ajax({
			type : "POST",
			url : '../../LoginController',
			data : {
				jsonData : JSON.stringify(jsonData),
				CCO : "SLOG"
				
			},
			dataType : "json",
			success : function(response) {

				document.getElementById('messsage').innerHTML = response.result;
				
			},
			error : function(e) {
				 alert("Error " + e);
				console.log(e);
			}
			
		});
		
	}
}