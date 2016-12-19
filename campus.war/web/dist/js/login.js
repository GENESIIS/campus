/**
 * 20161122 AS C19-student-login-without-using-third-party-application-test-as
 * login.js class created. 20161206 AS
 * C19-student-login-without-using-third-party-application-test-as added
 * remember checkbox.
 */
var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "/dist/js/institute/validation/validation.js";

function studentLogin() {

	var username = $("#email").val();
	var password = $("#password").val();
	var remember = $("#remember").prop('checked');

	var usernametb = isempty(username);
	var passtb = isempty(password);

	if (usernametb == false) {
		document.getElementById('emailtbError').innerHTML = "  ** Email can not be Empty.";
		flag = false;
		return false;
	}
	if (passtb == false) {
		document.getElementById('passtbError').innerHTML = "   ** Password can not be Empty.";
		return false;
	}

	if ((username != null) && (password != null)) {
	//	alert(username + "-----" + password + remember)
		var jsonData = {
			"userKey" : username,
			"password" : password,
			"remember" : remember
		};
		$
				.ajax({
					type : "POST",
					url : '../../LoginController',
					data : {
						jsonData : JSON.stringify(jsonData),
						CCO : "SLOG"

					},
					dataType : "json",
					success : function(response) {
						//alert(response['message']);
						
						//document.getElementById('messsage').innerHTML = response.result;
						
						if(response['message'] === "valid Username and Password."){
							 window.location.href ='/dist/partials/student/student-dashboard.jsp';
						}else{
							document.getElementById('errorMesssage').innerHTML = response['message'];
						}
					
						
					},
					error : function(e) {
						alert("Error " + e);
						console.log(e);
					}

				});

	}
}


function lastLoginDateTime( DateTimeString){
	
}
