/**rememberremember
 * 20161122 AS C19-student-login-without-using-third-party-application-test-as login.js class created.
 */
var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "/dist/js/institute/validation/validation.js";

function studentLogin(){
	
	var username = $("#email").val();
	var password = $("#password").val();
	var remember = $("#remember").val();
	
	var usernametb = isempty(username);
	var passtb = isempty(password);
	
	if (usernametb == false) {
		document.getElementById('fullNametbError').innerHTML = "  ** Email can not be Empty.";
		flag = false;
	}  if (passtb == false) {
		document.getElementById('emailtbError').innerHTML = "   ** Password can not be Empty.";
		flag = false;
	}
	
	if((username != null) && (password != null)){
		alert(username +"-----"+password +remember)
		var jsonData = {
				"userKey" : username,
				"password" : password,
				"remember" : remember
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