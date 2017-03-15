/**
 * 20170314 AS c23-admin-login-logout-function-as - admin-login.js created 
 */

var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "/dist/js/institute/validation/validation.js";

function ALogin() {

	var username = $("#adminEmail").val();
	var password = $("#adminPassword").val();
	var remember = $("#adminRemember").prop('checked');
	
	var usernametb = isempty(username);
	var passtb = isempty(password);

	// filed validation error messages handling
	if (!(usernametb)) {
		document.getElementById('usernameError').innerHTML = "  ** Email can not be Empty.";
		flag = false;
		return false;
	}
	if (!(passtb)) {
		document.getElementById('passwordError').innerHTML = "   ** Password can not be Empty.";
		return false;
	}

	if ((username) && (password)) {

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
						CCO : "ALOG"

					},
					dataType : "json",
					success : function(response) {

						if (response['message'] === "valid Username and Password.") {
							window.location.href = response['pageURL'];
						}if(response['message'] === "User Already Logged In"){
							
						} else {
							document.getElementById('errorMesssage').innerHTML = response['message'];
						}

					},
					error : function(response,error,errorThrown) {
						alert("Error " + error);
						console.log(error);
						 var msg = '';
					      if (response.status === 0) {
					          msg = 'Not connect.\n Verify Network.';
					      } else if (response.status == 404) {
					          msg = 'Requested page not found. [404]';
					      } else if (response.status == 500) {
					          msg = 'Internal Server Error [500].';
					      } else if (error === 'parsererror') {
					          msg = 'Requested JSON parse failed.';
					      } else if (error === 'timeout') {
					          msg = 'Time out error.';
					      } else if (error === 'abort') {
					          msg = 'Ajax request aborted.';
					      } else {
					          msg = 'Uncaught Error.\n' + response.responseText;
					      }
					}

				});

	}
	
}