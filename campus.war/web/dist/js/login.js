/**
 * 20161122 AS C19-student-login-without-using-third-party-application-test-as
 * login.js class created. 20161206 AS
 * 
 * C19-student-login-without-using-third-party-application-test-as added
 * remember checkbox.
 * 
 * CAM-21 AS logout-popup window added to after successful logout.
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

	// filed validation error messages handling
	if (!(usernametb)) {
		document.getElementById('emailtbError').innerHTML = "  ** Email can not be Empty.";
		flag = false;
		return false;
	}
	if (!(passtb)) {
		document.getElementById('passtbError').innerHTML = "   ** Password can not be Empty.";
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
						CCO : "SLOG"

					},
					dataType : "json",
					success : function(response) {

						if (response['message'] === "valid Username and Password.") {
							window.location.href = response['pageURL'];
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

// reset error message labels 
function resetLoginLabels() {

	$("#errorMesssage").text("");
}

function studentLogout() {
	var userId = $("#userCode").val();
	if (userId != null) {
		var jsonData = {
			"code" : userId

		};
		$.ajax({
			type : "POST",
			url : '/LoginController',
			data : {
				jsonData : JSON.stringify(jsonData),
				CCO : "SLGOUT"

			},
			dataType : "json",
			success : function(response) {
				$(window).scrollTop(0);
				$('#logout-popup').modal('show');
				 
				setTimeout( function(){
					window.location.href = response['pageURL']; //this name may have to change depend on actual location of the page "Student Login or public index page"
					}, 5000);
				
				
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

//forget password function
function forgotPassword() {
	var userEmail = $("#verifiemail").val();
	
	var emailempty = isempty(userEmail);
	var valEmail = isValidEmailFormat(userEmail);
	
	//email filed validation error messages handling
	if (!(emailempty)) {
		document.getElementById('emailtbError').innerHTML = "  ** Email can not be Empty.";
		flag = false;
		return false;
	}
	if (!(valEmail)) {
		document.getElementById('emailtbError').innerHTML = "  ** Please Enter valid email address.";
		flag = false;
		return false;
	}
	
	if (userEmail != null) {
		var jsonData = {
			"email" : userEmail
			
		};
		
		$.ajax({
			type : "POST",
			url : '/LoginController',
			data : {
				jsonData : JSON.stringify(jsonData),
				CCO : "EMAILV"

			},
			dataType : "json",
			success : function(response) {
				
				
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
