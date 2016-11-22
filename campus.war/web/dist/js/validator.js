/**
 * 
 * //20161027 AS C8-inquiry-form-for-course validation.js  created.
 * //20161108 CM  c9-make-inquiry-for-institute added validateInstituteInquiryFileds() function
 * //20161108 CM c9-make-inquiry-for-institute Modified validateInstituteInquiryFileds() function
 * //20161115 CM c9-make-inquiry-for-institute added clearField(elementId) function
 * //20161122 CM c36-add-tutor-information 
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
 * isValidEmailFormat method validate a email address
 * 
 * @returns boolean if testing email address is a valid one then returns true
 *          else return false
 */
function isValidEmailFormat(email) {
	var emailAddress = email;
	var pattern = /([\w-\.]+)@((?:[\w]+\.)+)([a-zA-Z]{2,4})/g;
	return isPatternMatch(pattern, emailAddress);
}

/**
 * Validate URL
 * @author Chathuri
 * @param str
 */
function ValidURL(str) {
	var regex = /(http|https):\/\/(\w+:{0,1}\w*)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%!\-\/]))?/;
	return regex.test(str);
	
}

function validateTutorFileds() {
	var firstname = $("#firstName").val();
	var middlename = $("#middleName").val();
	var lastname = $("#lastName").val();
	var male = $("#radioMale").val();
	var female = $("#radioFemale").val();
	var experience = $("#experience").val();
	var aboutMe = $("#aboutMe").val();
	var mobileNumber = $("#mobileNumber").val();
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

	var flag = true;

	if (!isempty(firstname)) {
		document.getElementById('firstNameError').innerHTML = "**First name cannot be empty.";
		document.getElementById('firstName').focus();
		flag = false;
	}
	if (firstname.length>10) {
		document.getElementById('firstNameError').innerHTML = "**Exceed max length.";
		document.getElementById('firstName').focus();
		flag = false;
	}
	if (!isempty(lastname)) {
		document.getElementById('lastNameError').innerHTML = "**Last Name cannot be empty.";
		document.getElementById('lastName').focus();
		flag = false;
	}
	if (!isempty(mobileNumber)) {
		document.getElementById('mobileError').innerHTML = "**Mobile cannot be empty.";
		document.getElementById('mobileNumber').focus();
		flag = false;
	}
	if (!isempty(landNumber)) {
		document.getElementById('landError').innerHTML = "**Landphone number cannot be empty.";
		document.getElementById('landNumber').focus();
		flag = false;
	}
	if (!isempty(address1)) {
		document.getElementById('confirmPasswordError').innerHTML = "**Please confirm your password";
		document.getElementById('confirmPassword').focus();
		flag = false;
	}
	if (!isempty(address2)) {
		document.getElementById('confirmPasswordError').innerHTML = "**Please confirm ypur password";
		document.getElementById('confirmPassword').focus();
		flag = false;
	}
	if (!isempty(address3)) {
		document.getElementById('confirmPasswordError').innerHTML = "**Please confirm ypur password";
		document.getElementById('confirmPassword').focus();
		flag = false;
	}
	if (!isempty(username)) {
		document.getElementById('usernameError').innerHTML = "**Username cannot be empty.";
		document.getElementById('username').focus();
		flag = false;
	}
	if (!isempty(password)) {
		document.getElementById('passwordError').innerHTML = "**Password cannot be empty.";
		document.getElementById('password').focus();
		flag = false;
	}
	if (!isempty(confirmPassword)) {
		document.getElementById('confirmPasswordError').innerHTML = "**Please confirm ypur password";
		document.getElementById('confirmPassword').focus();
		flag = false;
	}
	if (!ValidURL(weblink)) {
		document.getElementById('weblinkError').innerHTML = "**Please Enter correct weblink";
		document.getElementById('weblink').focus();
		flag = false;
	}
	if (!ValidURL(facebook)) {
		document.getElementById('facebookError').innerHTML = "**Please Enter correct Facebook link";
		document.getElementById('facebook').focus();
		flag = false;
	}
	if (!ValidURL(linkedin)) {
		document.getElementById('linkedInError').innerHTML = "**Please Enter correct LinkedIn link";
		document.getElementById('linkedin').focus();
		flag = false;
	}
	if (!ValidURL(twitter)) {
		document.getElementById('twitterError').innerHTML = "**Please Enter correct Twitter link";
		document.getElementById('twitter').focus();
		flag = false;
	}
	if (!ValidURL(instagram)) {
		document.getElementById('instagramError').innerHTML = "**Please Enter correct Instagram link";
		document.getElementById('instagram').focus();
		flag = false;
	}
	if (!ValidURL(myspace)) {
		document.getElementById('mySpaceError').innerHTML = "**Please Enter correct Myspace link";
		document.getElementById('myspace').focus();
		flag = false;
	}
	
	
	return (flag);
}