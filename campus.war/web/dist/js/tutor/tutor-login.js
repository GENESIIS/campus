/**
 * 20170505 AS c99-tutor-login-function-as tutor-login.js created
 * 20170505 AS c99-tutor-login-function-as TutorLogin() function and login validation done 
 * 
 */

var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "/dist/js/institute/validation/validation.js";


function TutorLogin() {
	
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

	if ((usernametb) && (passtb)) {

		var jsonData = {
			"userKey" : username,
			"password" : password,
			"remember" : remember
		};
		$
				.ajax({
					type : "POST",
					url : '../../../LoginController',
					data : {
						jsonData : JSON.stringify(jsonData),
						CCO : "TUTORLOG"

					},
					dataType : "json",
					success : function(response) {

						if(response['message'] === "Logged successfull") {
							window.location.href = response['pageURL'];
						} else if(response['message'] === "User Already Logged In"){
					
						} else {
							
						document.getElementById('adminErrorMess').innerHTML = response['message'];
						window.history.pushState('Tutor-Login', 'Title', response['pageURL']);
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