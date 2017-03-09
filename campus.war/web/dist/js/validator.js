/**
 * 
 * //20161027 AS C8-inquiry-form-for-course validation.js  created.
 * //20161108 CM  c9-make-inquiry-for-institute added validateInstituteInquiryFileds() function
 * //20161108 CM c9-make-inquiry-for-institute Modified validateInstituteInquiryFileds() function
 * //20161115 CM c9-make-inquiry-for-institute added clearField(elementId) function
 * //20161122 CM c36-add-tutor-information 
 * //20170111 CW c36-add-tutor-details-Added Viber & WhatsApp validations - CW
 * //20170117 CW c36-add-tutor-details-Added ValidateEmail() method - cw
 * //20170124 CW c36-add-tutor-details modified validateTutorFileds() method - cw
 * //20170129 CW c36-add-tutor-details-cw- modified the places of ValidateUsername(), ValidateEmail() methods calling.
 * //20170129 CW c36-add-tutor-details-cw- modified ValidateUsername(), ValidateEmail() methods.
 * //20170209 CW c38-view-update-tutor-profile modified validateTutorFileds() method 
 * //20170214 CW c38-view-update-tutor-profile modified validateTutorFileds() method & renamed as validateTutorModifications()
 * //20170215 CW c38-view-update-tutor-profile modified validateTutorModifications() method
 * //20170215 CW c38-view-update-tutor-profile modified validateTutorModifications() method modify validations for tutor status & Town details
 * //20170225 CW c38-view-update-tutor-profile removed Password & confirm Password validations
 * //20170308 CW c38-view-update-tutor-profile add isHavingOnlySpaces method & modify isempty method to use isHavingOnlySpaces method
 * //20170309 CW c38-view-update-tutor-profile modified the validations & set the fields & make the max length as same as database column length
 * //20170309 CW c38-view-update-tutor-profile validate fields for spaces & if only space entered then assign null to those fields.
 */

/**
 * 
 * @param fieldValue
 *            it is the value of a document element
 * @returns true if has content else false
 */
function isempty(fieldValue) {
	return ((fieldValue == "") || (fieldValue == null) || isHavingOnlySpaces(fieldValue)) ? false : true;
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
 * @author Chathuri
 * @param str
 */
function ValidURL(str) {
	var regex = /(http|https):\/\/(\w+:{0,1}\w*)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%!\-\/]))?/;
	return regex.test(str);

}

/**
 * Validate the modifications done in the tutor details 
 * 
 * @author Chinthaka
 * @return boolean : Return false if any error found in the data entered
 */
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
	var country = $("#countryDetails :selected").text();
	var town = $("#townDetails :selected").text();
	var tutorStatus = $("#newtutorStatus :selected").text();
	
	var firstnameOld = $("#firstnameOld").val();
	var middlenameOld = $("#middlenameOld").val();
	var lastnameOld = $("#lastnameOld").val();
	var genderOld = $("#genderOld").val();
	var experienceOld = $("#experienceOld").val();
	var descriptionOld = $("#descriptionOld").val();
	var mobilephonecountrycodeOld = $("#mobilephonecountrycodeOld").val();
	var mobilephonenetworkcodeOld = $("#mobilephonenetworkcodeOld").val();
	var mobilephonenumberOld = $("#mobilephonenumberOld").val();
	var landphonecountrycodeOld = $("#landphonecountrycodeOld").val();
	var landphoneareacodeOld = $("#landphoneareacodeOld").val();
	var landphonenumberOld = $("#landphonenumberOld").val();
	var address1Old = $("#address1Old").val();
	var weblinkOld = $("#weblinkOld").val();
	var facebookurlOld = $("#facebookurlOld").val();
	var linkedinurlOld = $("#linkedinurlOld").val();
	var twitterurlOld = $("#twitterurlOld").val();
	var instagramurlOld = $("#instagramurlOld").val();
	var myspaceurlOld = $("#myspaceurlOld").val();
	var whatsappnumberOld = $("#whatsappnumberOld").val();
	var vibernumberOld = $("#vibernumberOld").val();
	var emailOld = $("#emailOld").val();
	var usernameOld = $("#usernameOld").val();
	var countrynameOld = $("#countrynameOld").val();
	var townOld = $("#townOld").val();
	var tutorstatusOld = $("#tutorstatusOld").val();
	
	var flag = true;
	var isModified = false;
	
	if(firstname != firstnameOld){
		
		isModified = true;
		
		if ((!isempty(firstname)) || firstname == " ") {
			document.getElementById('firstName').value = '';
			document.getElementById('firstNameError').innerHTML = "**First name cannot be empty.";
			document.getElementById('firstName').focus();
			flag = false;
			
		}else if(!(/^[A-Za-z\s]+$/.test(firstname))){
			
			document.getElementById('firstNameError').innerHTML = "**First name can have only Characters & Spaces.";
			document.getElementById('firstName').focus();
			flag = false;
		}
		
		if (firstname.length > 35) {
			document.getElementById('firstNameError').innerHTML = "**Max length exceeded.";
			document.getElementById('firstName').focus();
			flag = false;
		}
	}
	
	if(middlename != middlenameOld){
		
		isModified = true;
		
		if (isempty(middlename)){
			if(!(/^[A-Za-z\s\u002D]+$/.test(middlename))){				
				document.getElementById('middleNameError').innerHTML = "**Middle name can have only Characters & Spaces.";
				document.getElementById('middleName').focus();
				flag = false;
			}
		}
		
		if (middlename.length > 35) {
			document.getElementById('middleNameError').innerHTML = "**Max length exceeded.";
			document.getElementById('middleName').focus();
			flag = false;
		}
	}	

	if(lastname != lastnameOld){
		
		isModified = true;
		
		if ((!isempty(lastname)) || lastname == " ") {

			document.getElementById('lastName').value = '';
			document.getElementById('lastNameError').innerHTML = "**Last Name cannot be empty.";
			document.getElementById('lastName').focus();
			flag = false;
			
		}else if(!(/^[A-Za-z\s]+$/.test(lastname))){
			
			document.getElementById('lastNameError').innerHTML = "**Last Name can have only Characters & Spaces.";
			document.getElementById('lastName').focus();
			flag = false;
		}
		
		if (lastname.length > 35) {
			document.getElementById('lastNameError').innerHTML = "**Max length exceeded.";
			document.getElementById('lastName').focus();
			flag = false;
		}
	}

	if(experience != experienceOld){

		isModified = true;
		
		if(experience.length > 2147483600){
			document.getElementById('experienceError').innerHTML = "**Max length exceeded.";
			document.getElementById('experience').focus();
			flag = false;
		}
	}
	
	if(aboutMe != descriptionOld){

		isModified = true;
		
		if(experience.length > 2147483600){
			document.getElementById('aboutMeError').innerHTML = "**Max length exceeded.";
			document.getElementById('aboutMe').focus();
			flag = false;
		}
	}
	
	if(countrynameOld == ""){
		if (country == "--- Select Country ---") {
			document.getElementById('countryError').innerHTML = "**Please select country.";
			document.getElementById('countryDetails').focus();
			flag = false;
		} else{
			isModified = true;
		}
	} else{
		if ((country != countrynameOld) && (country != "--- Select Country ---")) {
			isModified = true;
			if(town == "--- Select town ---"){
				document.getElementById('townError').innerHTML = "**Please select Town.";
				document.getElementById('townDetails').focus();
				flag = false;
			}
		}
	}
		
	if(townOld == ""){
		if (town == "--- Select Country before select the town ---") {
			document.getElementById('townError').innerHTML = "**Please select Town.";
			document.getElementById('townDetails').focus();
			flag = false;
		} else{
			isModified = true;
		}
	} else{
		if ((town != townOld) && (town != "--- Select Country before select the town ---")) {
			isModified = true;
		}
	}

	if(mobileCountryCode != mobilephonecountrycodeOld){
		
		isModified = true;
		
		if (!isempty(mobileCountryCode)) {
			document.getElementById('mobileError').innerHTML = "**Country Code cannot be empty.";
			document.getElementById('countryError').innerHTML = "**Please Select Country Code";
			document.getElementById('mobileCountryCode').focus();
			flag = false;
		}

		if (mobileCountryCode.length > 10) {
			document.getElementById('mobileError').innerHTML = "**Max length exceeded.";
			document.getElementById('mobileCountryCode').focus();
			flag = false;
		}

		if (isNaN(mobileCountryCode)) {
			document.getElementById('mobileError').innerHTML = "**Invalid Country Code";
			document.getElementById('mobileCountryCode').focus();
			flag = false;
		}
	}

	if(mobileNetworkCode != mobilephonenetworkcodeOld){
		
		isModified = true;
		
		if (!isempty(mobileNetworkCode)) {
			document.getElementById('mobileNetworkCode').value = '';
			document.getElementById('mobileNetworkError').innerHTML = "**Mobile Network Code cannot be empty.";
			document.getElementById('mobileNetworkCode').focus();
			flag = false;
		}

		if (mobileNetworkCode.length > 10) {
			document.getElementById('mobileNetworkError').innerHTML = "**Max length exceeded.";
			document.getElementById('mobileNetworkCode').focus();
			flag = false;
		}

		if (isNaN(mobileNetworkCode)) {
			document.getElementById('mobileNetworkError').innerHTML = "**Invalid Mobile Network Code";
			document.getElementById('mobileNetworkCode').focus();
			flag = false;
		}
	}

	if(mobileNumber != mobilephonenumberOld){
		
		isModified = true;		

		if (!isempty(mobileNumber)) {
			document.getElementById('mobileNumber').value = '';
			document.getElementById('mobileNumberError').innerHTML = "**Mobile Number cannot be empty.";
			document.getElementById('mobileNumber').focus();
			flag = false;
		}

		if (mobileNumber.length > 15) {
			document.getElementById('mobileNumberError').innerHTML = "**Max length exceeded.";
			document.getElementById('mobileNumber').focus();
			flag = false;
		}
		
		if (isNaN(mobileNumber)) {
			document.getElementById('mobileNumberError').innerHTML = "**Invalid Mobile number";
			document.getElementById('mobileNumber').focus();
			flag = false;
		}
	}

	if(landCountryCode != landphonecountrycodeOld){
		
		isModified = true;
		
		if (!isempty(landCountryCode)) {
			document.getElementById('mobileCountryCode').value = '';
			document.getElementById('landError').innerHTML = "**Country Code cannot be empty.";
			document.getElementById('mobileCountryCode').focus();
			flag = false;
		}

		if (landCountryCode.length > 10) {
			document.getElementById('landError').innerHTML = "**Max length exceeded.";
			document.getElementById('mobileCountryCode').focus();
			flag = false;
		}

		if (isNaN(landCountryCode)) {
			document.getElementById('landError').innerHTML = "**Invalid Country Code";
			document.getElementById('mobileCountryCode').focus();
			flag = false;
		}	
	}

	if(landAreaCode != landphoneareacodeOld){
		
		isModified = true;
		
		if (!isempty(landAreaCode)) {
			document.getElementById('landAreaCode').value = '';
			document.getElementById('landAreaCodeError').innerHTML = "**Landphone Area code cannot be empty.";
			document.getElementById('landAreaCode').focus();
			flag = false;
		}

		if (landAreaCode.length > 10) {
			document.getElementById('landAreaCodeError').innerHTML = "**Max length exceeded.";
			document.getElementById('landAreaCode').focus();
			flag = false;
		}

		if (isNaN(landAreaCode)) {
			document.getElementById('landAreaCodeError').innerHTML = "**Invalid Landphone Area code";
			document.getElementById('landAreaCode').focus();
			flag = false;
		}	
	}

	if(landNumber != landphonenumberOld){
		
		isModified = true;		

		if (!isempty(landNumber)) {
			document.getElementById('landNumber').value = '';
			document.getElementById('landNumberError').innerHTML = "**Landphone number cannot be empty.";
			document.getElementById('landNumber').focus();
			flag = false;
		}

		if (landNumber.length > 10) {
			document.getElementById('landNumberError').innerHTML = "**Max length exceeded.";
			document.getElementById('landNumber').focus();
			flag = false;
		}

		if (isNaN(landNumber)) {
			document.getElementById('landNumberError').innerHTML = "**Invalid Phone number";
			document.getElementById('landNumber').focus();
			flag = false;
		}
	}

	if(address1 != address1Old){
		
		isModified = true;		

		if (!isempty(address1)) {
			document.getElementById('address1').value = '';
			document.getElementById('address1Error').innerHTML = "**Please Fill Address";
			document.getElementById('address1').focus();
			flag = false;
		}
		if (address1.length > 50) {
			document.getElementById('address1Error').innerHTML = "**Max length exceeded";
			document.getElementById('address1').focus();
			flag = false;
		}	
	}

	if(weblink != weblinkOld){
		
		isModified = true;		
		if (isempty(weblink)) {
			if (!ValidURL(weblink) && (weblink != "-")) {
				document.getElementById('weblinkError').innerHTML = "**Please Enter correct weblink";
				document.getElementById('weblink').focus();
				flag = false;
			}
		}

		if (weblink.length > 200) {
			document.getElementById('weblinkError').innerHTML = "**Max length exceeded";
			document.getElementById('weblink').focus();
			flag = false;
		}	
	}
	
	if(facebook != facebookurlOld){
		
		isModified = true;		
		if (isempty(facebook) && (facebook != "-")) {
			if (!ValidURL(facebook)) {
				document.getElementById('facebookError').innerHTML = "**Please Enter correct Facebook link";
				document.getElementById('facebook').focus();
				flag = false;
			}
		}

		if (facebook.length > 200) {
			document.getElementById('facebookError').innerHTML = "**Max length exceeded";
			document.getElementById('facebook').focus();
			flag = false;
		}
	}
	
	if(linkedin != linkedinurlOld){
		
		isModified = true;		
		if (isempty(linkedin) && (linkedin != "-")) {
			if (!ValidURL(linkedin)) {
				document.getElementById('linkedInError').innerHTML = "**Please Enter correct LinkedIn link";
				document.getElementById('linkedin').focus();
				flag = false;
			}
		}

		if (linkedin.length > 100) {
			document.getElementById('linkedInError').innerHTML = "**Max length exceeded";
			document.getElementById('linkedin').focus();
			flag = false;
		}
	}
	
	if(twitter != twitterurlOld){
		
		isModified = true;		
		if (isempty(twitter) && (twitter != "-")) {
			if (!ValidURL(twitter)) {
				document.getElementById('twitterError').innerHTML = "**Please Enter correct Twitter link";
				document.getElementById('twitter').focus();
				flag = false;
			}
		}

		if (twitter.length > 100) {
			document.getElementById('twitterError').innerHTML = "**Max length exceeded";
			document.getElementById('twitter').focus();
			flag = false;
		}
	}
	
	if(instagram != instagramurlOld){
		
		isModified = true;		
		if (isempty(instagram) && (instagram != "-")) {
			if (!ValidURL(instagram)) {
				document.getElementById('instagramError').innerHTML = "**Please Enter correct Instagram link";
				document.getElementById('instagram').focus();
				flag = false;
			}
		}

		if (instagram.length > 100) {
			document.getElementById('instagramError').innerHTML = "**Max length exceeded";
			document.getElementById('instagram').focus();
			flag = false;
		}
	}
	
	if(myspace != myspaceurlOld){
		
		isModified = true;		
		if (isempty(myspace) && (myspace != "-")) {
			if (!ValidURL(myspace)) {
				document.getElementById('mySpaceError').innerHTML = "**Please Enter correct Myspace link";
				document.getElementById('myspace').focus();
				flag = false;
			}
		}

		if (myspace.length > 100) {
			document.getElementById('mySpaceError').innerHTML = "**Max length exceeded";
			document.getElementById('myspace').focus();
			flag = false;
		}
	}		
	
	if(whatsapp != whatsappnumberOld){
		
		isModified = true;		
		if (isempty(whatsapp)) {
			if (isNaN(whatsapp)) {
				document.getElementById('whatsappError').innerHTML = "**Invalid whatsapp number";
				document.getElementById('whatsapp').focus();
				flag = false;
			}
		}
			
		if (isempty(whatsapp)) {
			if (whatsapp.length > 20) {
				document.getElementById('whatsappError').innerHTML = "**Max length exceeded";
				document.getElementById('whatsapp').focus();
				flag = false;
			}
		}
	}
	
	if(viber != vibernumberOld){
		
		isModified = true;		
		if (isempty(viber)) {
			if (isNaN(viber)) {
				document.getElementById('viberError').innerHTML = "**Invalid Viber number";
				document.getElementById('viber').focus();
				flag = false;
			}
		}
	
		if (isempty(viber)) {
			if (viber.length > 20) {
				document.getElementById('viberError').innerHTML = "**Max length exceeded";
				document.getElementById('viber').focus();
				flag = false;
			}
		}
	}

	if(tutorStatus != tutorstatusOld){
		isModified = true;
	}
	
	if(emailOld != email){
		
		isModified = true;		
		if (!isempty(email)) {
			document.getElementById('email').value = '';
			document.getElementById('emailError').innerHTML = "**Email cannot be empty.";
			document.getElementById('email').focus();
			flag = false;
		}
		
		if (!isValidEmailFormat(email)) {
			document.getElementById('emailError').innerHTML = "**Invalid Email.";
			document.getElementById('email').focus();
			flag = false;
		}
	
		if (email.length > 255) {
			document.getElementById('emailError').innerHTML = "**Max length exceeded";
			document.getElementById('email').focus();
			flag = false;
		}
		
		var emailExist = ValidateEmail(email);
		if (emailExist.message == '0') {
			document.getElementById('emailError').innerHTML = "**Email entered Already exists.";
			document.getElementById('email').focus();
			flag = false;
		}

	}
	
	if(username != usernameOld){
		
		isModified = true;		
		if (!isempty(username)) {
			document.getElementById('username').value = '';
			document.getElementById('usernameError').innerHTML = "**Username cannot be empty.";
			document.getElementById('username').focus();
			flag = false;
		}
			
		if (username.length > 100) {
			document.getElementById('usernameError').innerHTML = "**User Name Max length exceeded.";
			document.getElementById('username').focus();
			flag = false;
		}
				
		if (username.length < 6) {
			document.getElementById('usernameError').innerHTML = "**Poor Username.";
			document.getElementById('username').focus();
			flag = false;
		}	
		
		var usernameExist = ValidateUsername(username);
		if (usernameExist.message == '0') {
			document.getElementById('usernameError').innerHTML = "**Username Already exists.";
			document.getElementById('username').focus();
			flag = false;
		}	
	}
	
	if (isModified == false){
		document.getElementById('message').innerHTML = "**You haven't done any changes.";
		flag = false;
	}

	return (flag);
}

function ValidateUsername(username) {
	var resp = null;
	$.ajax({
		url : '/TutorController',
		method : 'POST',
		async : false,
		data : {
			CCO : 'CHECK_USERNAME',
			username : username
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

function clearField(elementId) {
	$(document).find('#' + elementId).text('');
	$(document).find('#message').text('');
}