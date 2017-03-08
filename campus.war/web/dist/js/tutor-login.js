/**
 * //20170307 CW c147-tutor-reset-password-cw tutor-login.js created.
 * //20170308 CW c147-tutor-reset-password-cw modified forgotPassword, isempty method calling
 * //20170308 CW c147-tutor-reset-password-cw modified forgotPassword, ValidateEmail method calling
 * //20170308 CW c147-tutor-reset-password-cw add ajax call from c22
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
	
/*	var emailExist = ValidateEmail(userEmail);
	if (emailExist.message == '1') {
		jQuery('#emailveryMessage').addClass("fp-msg-error").html(' ** Email entered does not exists.');
		flag = false;
		return false;
	}*/
}

/**
 * This function is used to validate the email field with the database values
 * @param email
 * @returns 1 if email does not exist in the database & returns 0 if email already exists in the database.
 */
function ValidateEmail(email) {
	var resp = null;
	$.ajax({
		url : '/TutorController',
		method : 'POST',
		async : false,
		data : {
			CCO : 'CHECK_EMAIL',
			email : email
		},
		dataType : "json",
		success : function(response) {
			resp = response;
		},
		error : function(response) {
			resp = response;
		}
	});

	return resp;
}
