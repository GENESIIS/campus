/**
 * //20170307 CW c147-tutor-reset-password-cw tutor-login.js created.
 * //20170308 CW c147-tutor-reset-password-cw modified forgotPassword, isempty method calling
 * //20170308 CW c147-tutor-reset-password-cw modified forgotPassword, ValidateEmail method calling
 * //20170308 CW c147-tutor-reset-password-cw add ajax call from c22
 * //20170313 CW c148-tutor-verify-hashcode-reset-password-cw removed ValidateEmail method & add verifyCode method
 * //20170314 CW c148-tutor-verify-hashcode-reset-password-cw changedPassword, validatePasswordResetData, 
 * 						convertPassWordToString, clearAllFields, passwordAndConfirmPassword methods added.
 */

/**
 * @param fieldValue - it is the value of a document element
 * @returns true if has content else false
 */
function isempty(fieldValue) {
	return ((fieldValue == "") || (fieldValue == null) || isHavingOnlySpaces(fieldValue)) ? false : true;
}

/**
 * isHavingOnlySpaces method validate a string value to have only one or more spaces
 * @author Chinthaka
 * @returns boolean : returns true if given string value is having only spaces
 */
function isHavingOnlySpaces(strValue) {
	
	if (!strValue.replace(/\s/g, '').length) {
	    // string only contained whitespace (ie. spaces, tabs or line breaks)
		return true;
	}
	return false;
}

/**
 * isValidEmailFormat method validate a email address
 * @returns boolean if testing email address is a valid one then returns true
 *          else return false
 */
function isValidEmailFormat(email) {
	var emailAddress = email;
	var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	return isPatternMatch(pattern, emailAddress);
}

/**
 * This function will validate the entered email of the tutor
 * @author Chinthaka
 */
function forgotPassword() {
	var userEmail = $("#verifyemail").val();

	// email field validation error messages handling
	if (!isempty(userEmail)) {
		jQuery('#emailveryMessage').addClass("fp-msg-error").html(' ** Email can not be Empty. ');
		flag = false;
		return false;
	}
	if (!(isValidEmailFormat(userEmail))) {
		jQuery('#emailveryMessage').addClass("fp-msg-error").html(' ** Please Enter valid email address. ');
		flag = false;
		return false;
	}
	
	if (userEmail.length > 255) {
		jQuery('#emailveryMessage').addClass("fp-msg-error").html(' ** Max length exceeded. ');
		flag = false;
		return false;
	}
	
	$.ajax({
		url : '/TutorController',
		method : 'POST',
		async : false,
		data : {
			CCO : 'TUTOR_EMAIL_VERIFICATION',
			email : userEmail
		},
		dataType : "json",
		success : function(response) {
			if(response['message']=== 'Mail successfully submited to your email, And verification code only valid 30 MINUTES. '){
			//	document.getElementById('emailveryMessage').innerHTML = response['message'];
				jQuery('#emailveryMessage').addClass("fp-msg-success").html(response['message']);
				setTimeout(function() {
					$('#verifications-popup').modal('show');
				}, 5000);
			}else{
			//	document.getElementById('emailveryMessage').innerHTML = response['message'];
				jQuery('#emailveryMessage').addClass("fp-msg-error").html(response['message']);
			}
		},
		error : function(response, error, errorThrown) {
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

/**
 * This function will validate the entered hash code with the database saved value
 * @author Chinthaka
 */
function verifyCode() {
	var code = $("#verifyCode").val();
	var email = $("#verifyemail").val();
	var codeEmpty = isempty(code);
	

	// code filed validation error messages handling
	if (!(codeEmpty)) {
	//	document.getElementById('verifyMesssage').innerHTML = "  ** Verify Code can not be Empty.";
		jQuery('#verifyMesssage').addClass("fp-msg-error").html('  ** Verify Code can not be Empty.');
		flag = false;
		return false;
	}
	
	if (code != null) {
/*		var jsonData = {
			"hashCode" : code,
			"email" : email
		};
*/
		$.ajax({
					type : "POST",
					url : '/TutorController',
					data : {
						hashCode : code,
						email : email,
						CCO : "TUTOR_HASH_VERIFICATION"
					},
					dataType : "json",
					success : function(response) {
						var counter = 0;
						if (response['errorMessage'] == "Your Varification code is invalid. Please try again ! "
								|| response['errorMessage'] == "Verification code has been Expired!") {

							//document.getElementById('verifyMesssage').innerHTML = response['errorMessage'];
							jQuery('#verifyMesssage').addClass("fp-msg-error").html(response['errorMessage']);

						} else {
							jQuery('#verifyMesssage').addClass("fp-msg-success").html(response['errorMessage']);
							setTimeout(function() {
								
								var firstName = "";
								var lastName = "";
								var email = "";
								var scode = "";
								var resultData = response.result;

								$.each(response.result, function(index, value) {
									var res = value.toString();
									var data = res.split(",");
									counter++;

									firstName = data[0].toString();
									lastName = data[1].toString();
									email = data[2].toString();
									scode = data[4].toString();
								});
								var encode = hashEncode(scode);
								// var decode = hashDecose(encode);
								// data binding to URL
								var pageURL = firstName + "&" + lastName + "&"
										+ email + "&" + encode;

								window.location.href = response['pageURL']
										+ "?uData&" + pageURL;
							}, 4000);
							
							
						}
					},
					error : function(response, error, errorThrown) {
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
							F
							msg = 'Ajax request aborted.';
						} else {
							msg = 'Uncaught Error.\n' + response.responseText;
						}
					}

				});
	}
}
// String encode to Hash code
function hashEncode(data) {
	// Define the string
	var string = data;

	// Encode the String
	var encodedString = btoa(string);
	
	return encodedString;
}
// String Hash code decode to Sting
function hashDecode(data) {
	// Decode the String
	var decodedString = atob(data);
	
	return decodedString;
}

//password changed ajax function
function changedPassword() {
	var code = $("#userTypeCode").val();
	var password = $("#passWord").val();
	var confirmpassword = $("#confrmpsw").val();
	var paaswordEmpty = isempty(password);
	var validation = validatePasswordResetData();
	var passvadidation = passwordAndConfirmPassword(password,confirmpassword);
	// code filed validation error messages handling
	if (!(paaswordEmpty)) {
	//	document.getElementById('emailtbError').innerHTML = "  ** Verify Code can not be Empty.";
		jQuery('#passWordError').addClass("fp-msg-error").html('  ** Verify Code can not be Empty.');
		flag = false;
		return false;
	}

	if (code != null && validation && passvadidation) {
/*		var jsonData = {
			"code" : code,
			"password" : password
		};*/

		$
				.ajax({
					type : "POST",
					url : '/TutorController',
					data : {
						//jsonData : JSON.stringify(jsonData),
						code : code,
						password : password,
						CCO : "TUTOR_RESET_PASSWORD"

					},
					dataType : "json",
					success : function(response) {
					
						if (response['message'] === "Password successfully changed.") {
							jQuery('#message').addClass("fp-msg-success").html(response['message']);
							setTimeout(function() {
								window.location.href = response['pageURL'];
							}, 4000);
						}
						else{
							jQuery('#message').addClass("fp-msg-error").html(response['message']);
						}
					},
					error : function(response, error, errorThrown) {
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

/**
 * validateSignUpWoThirdPartyPageEmbedData() validates all the current critical
 * fields placed on /dist/partials/signUpWoThirdParty.jsp page. It's the custom
 * field validator dedicated for the page above.
 * 
 * @author anuradha
 * @returns {Boolean}
 */
function validatePasswordResetData() {
	var validationPass = true;

	if (!(isFieldFilled(
			isStringHasValiCharsAndLength($('#passWord').val(),
					/^([a-zA-Z0-9]+)([a-zA-Z0-9_]+){7,}$/g),
			"Check Field Contains Invalid Characters Or Should Be > 7 Characters and ",
			"passWordError"))) {
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#passWord').val()), "Password Field",
			"passWordError"))) {
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#confrmpsw').val()),
			"Confirm Password Field", "confPassWordError"))) {
		return !validationPass;
	} else if (!(isFieldFilled(passwordAndConfirmPassword($('#passWord').val(),
			$('#confrmpsw').val()), "PassWords Does Not Match ,The Field(s)",
			"confPassWordError"))) {
		return !validationPass;
	}else if(!(isFieldFilled(passwordAndConfirmPassword($('#passWord').val(),$('#confrmpsw').val()),"PassWords Does Not Match ,The Field(s)","confPassWordError"))){
		return !validationPass;
	}
	return validationPass;

}

/**
 * convertPassWordToString() method displays the pass word to text
 * 
 * @author anuradha
 * @param checkboxId
 *            check box id which used to toggle the command
 * @param passWordElementId
 *            password field element id
 * @param confirmWordElementId
 *            confirming field element id
 * @returns void
 */

function convertPassWordToString(checkboxId, passWordElementId,
		confirmWordElementId) {
	var value = ($('#' + checkboxId).is(':checked')) ? "text" : "password";
	$('#' + passWordElementId).attr("type", value);
	$('#' + confirmWordElementId).attr("type", value);
}

/**
 * clearAllFields willclear all the data input text fields once it'scalled
 */
function clearAllFields() {

	$('#showpasscheckbox').prop('checked', false);
	var value = $('#showpasscheckbox').is(':checked') ? "text" : "password";
	$('#passWord').attr("type", value);
	$('#confrmpsw').attr("type", value);
}

/**
 * Here the method confirms if both fields contain logically
 * equal string values.
 * @author anuradha
 * @param password password field element id
 * @param reconfirmPassWord confirming field element id
 * @returns {Boolean} if the both fields are logically equal, then,
 * returns true else false.
 */
function passwordAndConfirmPassword(password, reconfirmPassWord){
	var passwordTrimed = password.trim();
	var isBothValueAreIdentical= false;
	if(passwordTrimed!=null|passwordTrimed !=""){
		isBothValueAreIdentical= (passwordTrimed === reconfirmPassWord.trim()) ;
	}
	return isBothValueAreIdentical;
}