/**
 * 
 * //20161027 AS C8-inquiry-form-for-course validation.js  created.
 * //20161108 CM  c9-make-inquiry-for-institute added validateInstituteInquiryFileds() function
 * //20161108 CM c9-make-inquiry-for-institute Modified validateInstituteInquiryFileds() function
 * //20161115 CM c9-make-inquiry-for-institute added clearField(elementId) function
 * //20161122 CM c36-add-tutor-information 
 * //20161222 CW c38-view-update-tutor-profile added validateTutorModifications() method. 
 */

/**
 * 
 * @param fieldValue
 *            it is the value of a document element
 * @returns true if has content else false
 */
function isempty(fieldValue) {
	return ((fieldValue == "") || (fieldValue == null)) ? false : true;
}

/**
 * @param regularExpression
 *            pattern
 * @param source
 *            content to act as the source to be matched against the pattern
 * @returns boolean if matches true else false
 */
function isPatternMatch(regularExpression, source) {
	return regularExpression.test(source);

}

/**
 * isValidEmailFormat method validate a email address
 * 
 * @returns boolean if testing email address is a valid one then returns true
 *          else return false
 */
function isValidEmailFormat(email) {
	var emailAddress = email;
	var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	return isPatternMatch(pattern, emailAddress);
}
/**
 * Validate URL
 * 
 * @author Chathuri, Chinthaka
 * @param str
 */
function ValidURL(str) {
	var regex = /(http|https):\/\/(\w+:{0,1}\w*)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%!\-\/]))?/;
	return regex.test(str);

}


function validateTutorModifications() {
	var firstname = $("#firstName").val();
	var middlename = $("#middleName").val();
	var lastname = $("#lastName").val();
	var male = $("#radioMale").val();
	var female = $("#radioFemale").val();
	var experience = $("#experience").val();
	var aboutMe = $("#aboutMe").val();
	var mobileCountryCode = $("#mobileCountryCode").val();
	var mobileNetworkCode = $("#mobileNetworkCode").val();
	var mobileNumber = $("#mobileNumber").val();
	var landCountryCode = $("#landCountryCode").val();
	var landAreaCode = $("#landAreaCode").val();
	var landNumber = $("#landNumber").val();
	var address1 = $("#address1").val();
	var address2 = $("#address2").val();
	var address3 = $("#address3").val();
	var weblink = $("#weblink").val();
	var facebook = $("#facebook").val();
	var linkedin = $("#linkedin").val();
	var twitter = $("#twitter").val();
	var instagram = $("#instagram").val();
	var myspace = $("#myspace").val();
	var whatsapp = $("#whatsapp").val();
	var viber = $("#viber").val();
	var email = $("#email").val();
	var username = $("#username").val();
	var password = $("#password").val();
	var confirmPassword = $("#confirmPassword" + "").val();
	var country = $("#countryDetails :selected").text();
	var town = $("#townDetails :selected").text();

	var flag = true;

	if (!isempty(firstname)) {
		document.getElementById('firstNameError').innerHTML = "**First name cannot be empty.";
		document.getElementById('firstName').focus();
		flag = false;
	}
	
	if (firstname.length > 100) {
		document.getElementById('firstNameError').innerHTML = "**Max length exceeded.";
		document.getElementById('firstName').focus();
		flag = false;
	}
	
	if (middlename.length > 100) {
		document.getElementById('middleNameError').innerHTML = "**Max length exceeded.";
		document.getElementById('middleName').focus();
		flag = false;
	}
	
	if (!isempty(lastname)) {
		document.getElementById('lastNameError').innerHTML = "**Last Name cannot be empty.";
		document.getElementById('lastName').focus();
		flag = false;
	}
	
	if (lastname.length > 200) {
		document.getElementById('lastNameError').innerHTML = "**Max length exceeded.";
		document.getElementById('lastName').focus();
		flag = false;
	}
	
	if (!isempty(mobileCountryCode)) {
		document.getElementById('mobileError').innerHTML = "**Country Code cannot be empty.";
		document.getElementById('mobileCountryCode').focus();
		flag = false;
	}

	if (mobileCountryCode.length > 4) {
		document.getElementById('mobileError').innerHTML = "**Max length exceeded.";
		document.getElementById('mobileCountryCode').focus();
		flag = false;
	}

	if (isNaN(mobileCountryCode)) {
		document.getElementById('mobileError').innerHTML = "**Invalid Country Code";
		document.getElementById('mobileCountryCode').focus();
		flag = false;
	}
	

	if (!isempty(mobileNetworkCode)) {
		document.getElementById('mobileError').innerHTML = "**Mobile Network Code cannot be empty.";
		document.getElementById('mobileNetworkCode').focus();
		flag = false;
	}

	if (mobileNetworkCode.length > 10) {
		document.getElementById('mobileError').innerHTML = "**Max length exceeded.";
		document.getElementById('mobileNetworkCode').focus();
		flag = false;
	}

	if (isNaN(mobileNetworkCode)) {
		document.getElementById('mobileError').innerHTML = "**Invalid Mobile Network Code";
		document.getElementById('mobileNetworkCode').focus();
		flag = false;
	}
	

	if (!isempty(mobileNumber)) {
		document.getElementById('mobileError').innerHTML = "**Mobile Number cannot be empty.";
		document.getElementById('mobileNumber').focus();
		flag = false;
	}

	if (mobileNumber.length > 15) {
		document.getElementById('mobileError').innerHTML = "**Max length exceeded.";
		document.getElementById('mobileNumber').focus();
		flag = false;
	}

	if (isNaN(mobileNumber)) {
		document.getElementById('mobileError').innerHTML = "**Invalid Mobile number";
		document.getElementById('mobileNumber').focus();
		flag = false;
	}
	
	if (!isempty(landCountryCode)) {
		document.getElementById('landError').innerHTML = "**Landphone Country Code cannot be empty.";
		document.getElementById('landCountryCode').focus();
		flag = false;
	}

	if (landCountryCode.length > 4) {
		document.getElementById('landError').innerHTML = "**Max length exceeded.";
		document.getElementById('landCountryCode').focus();
		flag = false;
	}

	if (isNaN(landCountryCode)) {
		document.getElementById('landError').innerHTML = "**Invalid Landphone Country Code";
		document.getElementById('landCountryCode').focus();
		flag = false;
	}	
	
	if (!isempty(landAreaCode)) {
		document.getElementById('landError').innerHTML = "**Landphone Area code cannot be empty.";
		document.getElementById('landAreaCode').focus();
		flag = false;
	}

	if (landAreaCode.length > 10) {
		document.getElementById('landError').innerHTML = "**Max length exceeded.";
		document.getElementById('landAreaCode').focus();
		flag = false;
	}

	if (isNaN(landAreaCode)) {
		document.getElementById('landError').innerHTML = "**Invalid Landphone Area code";
		document.getElementById('landAreaCode').focus();
		flag = false;
	}	
	
	if (!isempty(landNumber)) {
		document.getElementById('landError').innerHTML = "**Landphone number cannot be empty.";
		document.getElementById('landNumber').focus();
		flag = false;
	}

	if (landNumber.length > 15) {
		document.getElementById('landError').innerHTML = "**Max length exceeded.";
		document.getElementById('landNumber').focus();
		flag = false;
	}

	if (isNaN(landNumber)) {
		document.getElementById('landError').innerHTML = "**Invalid Landphone number";
		document.getElementById('landNumber').focus();
		flag = false;
	}
/*	
	if (isempty(whatsapp)) {
		if (isNaN(whatsapp)) {
			document.getElementById('whatsappError').innerHTML = "**Invalid whatsapp number";
			document.getElementById('whatsapp').focus();
			flag = false;
		}
	}
	
	if (isempty(viber)) {
		if (isNaN(viber)) {
			document.getElementById('viberError').innerHTML = "**Invalid viber number";
			document.getElementById('viber').focus();
			flag = false;
		}
	}*/

/*	if (country == "DEFAULT") {
		document.getElementById('countryError').innerHTML = "**Please select country.";
		document.getElementById('countryDetails').focus();
		flag = false;
	}
	
	if (town == "DEFAULT") {
		document.getElementById('townError').innerHTML = "**Please select Town.";
		document.getElementById('townDetails').focus();
		flag = false;
	}*/

	if (!isempty(address1)) {
		document.getElementById('address1Error').innerHTML = "**Please Fill Address";
		document.getElementById('address1').focus();
		flag = false;
	}
	
	if (!isempty(email)) {
		document.getElementById('emailError').innerHTML = "**Email cannot be empty.";
		document.getElementById('email').focus();
		flag = false;
	}
	
	if (!isValidEmailFormat(email)) {
		document.getElementById('emailError').innerHTML = "**Invalid Email.";
		document.getElementById('email').focus();
		flag = false;
	}
	
	if (!isempty(username)) {
		document.getElementById('usernameError').innerHTML = "**Username cannot be empty.";
		document.getElementById('username').focus();
		flag = false;
	}
	
	if (username.length > 40) {
		document.getElementById('usernameError').innerHTML = "**Max length exceeded.";
		document.getElementById('username').focus();
		flag = false;
	}
	
	if (username.length < 6) {
		document.getElementById('usernameError').innerHTML = "**Poor Username.";
		document.getElementById('username').focus();
		flag = false;
	}
	
	if (!isempty(password)) {
		document.getElementById('passwordError').innerHTML = "**Password cannot be empty.";
		document.getElementById('password').focus();
		flag = false;
	}
	
	if (password.length > 10) {
		document.getElementById('passwordError').innerHTML = "**Max length exceeded.";
		document.getElementById('password').focus();
		flag = false;
	}
	
	if (!isempty(confirmPassword)) {
		document.getElementById('confirmPasswordError').innerHTML = "**Please confirm your password";
		document.getElementById('confirmPassword').focus();
		flag = false;
	}
	
	if (password != confirmPassword) {
		document.getElementById('confirmPasswordError').innerHTML = "**Password didn't match";
		document.getElementById('confirmPassword').focus();
		flag = false;
	}
/*
	if (isempty(weblink)) {
		if (!ValidURL(weblink)) {
			document.getElementById('weblinkError').innerHTML = "**Please Enter correct weblink";
			document.getElementById('weblink').focus();
			flag = false;
		}
	}
	if (isempty(facebook)) {
		if (!ValidURL(facebook)) {
			document.getElementById('facebookError').innerHTML = "**Please Enter correct Facebook link";
			document.getElementById('facebook').focus();
			flag = false;
		}
	}
	if (isempty(linkedin)) {
		if (!ValidURL(linkedin)) {
			document.getElementById('linkedInError').innerHTML = "**Please Enter correct LinkedIn link";
			document.getElementById('linkedin').focus();
			flag = false;
		}
	}
	if (isempty(twitter)) {
		if (!ValidURL(twitter)) {
			document.getElementById('twitterError').innerHTML = "**Please Enter correct Twitter link";
			document.getElementById('twitter').focus();
			flag = false;
		}
	}
	if (isempty(instagram)) {
		if (!ValidURL(instagram)) {
			document.getElementById('instagramError').innerHTML = "**Please Enter correct Instagram link";
			document.getElementById('instagram').focus();
			flag = false;
		}
	}
	if (isempty(myspace)) {
		if (!ValidURL(myspace)) {
			document.getElementById('mySpaceError').innerHTML = "**Please Enter correct Myspace link";
			document.getElementById('myspace').focus();
			flag = false;
		}
	}
	*/
/*
	var usernameExist = ValidateUsername(username);
	if (usernameExist.message == '0') {
		document.getElementById('usernameError').innerHTML = "**Username Already exists.";
		document.getElementById('username').focus();
		flag = false;
	}*/
	return (flag);
}

/*
function ValidateUsername(username) {
	var resp = null;
	$.ajax({
		url : '/TutorController',
		method : 'POST',
		async : false,
		data : {
			CCO : 'CHECK_USERNAME',
			USERNAME : username
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
*/

function clearField(elementId) {
	//alert(elementId);
	$(document).find('#' + elementId).text('');
	$(document).find('#message').text('');
}