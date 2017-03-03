/** 
 * //20161027 AS C8-inquiry-form-for-course validation.js  created.
 * //20161108 CM c9-make-inquiry-for-institute added validateInstituteInquiryFileds() function
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
 * //20170227 CW c37-tutor-update-tutor-profile-cw add Password & confirm Password from old CAM-38
 * //20170302 CW c37-tutor-update-tutor-profile-cw modified validateTutorModifications method to validateTutorModificationsByTutor
 * 				// - add isValidFirstname, isValidMiddlename, isValidLastname, isValidMobileCountryCode, isValidMobileNetworkCode, isValidMobileNumber, 
 * 				//	isValidLandCountryCode, isValidLandAreaCode, isValidAddress1, isValidWeblink, isValidFacebook, isValidLinkedin, isValidTwitter, 
 * 				//	isValidInstagram, isValidMyspace, isValidWhatsapp, isValidViber, isValidEmail, isValidUsername, isValidPassword, isValidLandNumber, methods using 
 * 				//	the validations in validateTutorModificationsByTutor method & modified validateTutorModificationsByTutor() to use new methods
 * //20170303 CW c37-tutor-update-tutor-profile-cw add isHavingOnlySpaces method & modified isempty method to use isHavingOnlySpaces method, 
 * 				// 	modified the methods to set fields null if they space in isValidFirstname, isValidMiddlename, isValidLastname, isValidMobileCountryCode, 
 * 				//	isValidMobileNetworkCode, isValidMobileNumber, isValidLandCountryCode, isValidLandAreaCode, isValidLandNumber, isValidAddress1, isValidWeblink, 
 * 				//	isValidFacebook, isValidLinkedin, isValidTwitter, isValidInstagram, isValidMyspace, isValidWhatsapp, isValidViber, isValidEmail, isValidUsername 
 * //20170303 CW c37-tutor-update-tutor-profile-cw modified isValidMiddlename, isValidMobileCountryCode, isValidMobileNetworkCode, isValidMobileNumber, 
 * 					isValidLandCountryCode, isValidLandAreaCode, isValidLandNumber, isValidAddress1, isValidWeblink, isValidFacebook, isValidLinkedin, 
 * 					isValidTwitter, isValidInstagram, isValidMyspace, isValidWhatsapp, isValidViber, isValidEmail, isValidUsername methods to reset when space value entered
 * //20170303 CW c37-tutor-update-tutor-profile-cw passwordOld renamed to passwordFromDb in validateTutorModificationsByTutor(), modified isValidPassword() validate password fields using Validator.isValidPassword()
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
function validateTutorModificationsByTutor() {

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
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	var confirmPassword = $("#confirmPassword" + "").val();
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
	var passwordFromDb = $("#passwordFromDb").val();
	var countrynameOld = $("#countrynameOld").val();
	var townOld = $("#townOld").val();
	var tutorstatusOld = $("#tutorstatusOld").val();
	
	var flag = true;
	var isModified = false;
	
	if(firstname != firstnameOld){
		isModified = true;
		flag = isValidFirstname(firstname);
	}
	
	if(middlename != middlenameOld){		
		isModified = true;
		flag = isValidMiddlename(middlename);
	}	

	if(lastname != lastnameOld){		
		isModified = true;
		flag = isValidLastname(lastname);
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
		flag = isValidMobileCountryCode(mobileCountryCode); 
	}

	if(mobileNetworkCode != mobilephonenetworkcodeOld){		
		isModified = true;
		flag = isValidMobileNetworkCode(mobileNetworkCode); 
	}

	if(mobileNumber != mobilephonenumberOld){		
		isModified = true;		
		flag = isValidMobileNumber(mobileNumber); 
	}

	if(landCountryCode != landphonecountrycodeOld){		
		isModified = true;
		flag = isValidLandCountryCode(landCountryCode);
	}

	if(landAreaCode != landphoneareacodeOld){		
		isModified = true;
		flag = isValidLandAreaCode(landAreaCode);	
	}

	if(landNumber != landphonenumberOld){		
		isModified = true;		
		flag = isValidLandNumber(landNumber);
	}

	if(address1 != address1Old){		
		isModified = true;		
		flag = isValidAddress1(address1);
	}

	if(weblink != weblinkOld){		
		isModified = true;		
		flag = isValidWeblink(weblink);
	}
	
	if(facebook != facebookurlOld){		
		isModified = true;		
		flag = isValidFacebook(facebook);
	}
	
	if(linkedin != linkedinurlOld){		
		isModified = true;
		flag = isValidLinkedin(linkedin);
	}
	
	if(twitter != twitterurlOld){		
		isModified = true;		
		flag = isValidTwitter(twitter);
	}
	
	if(instagram != instagramurlOld){	
		isModified = true;				
		flag = isValidInstagram(instagram);
	}
	
	if(myspace != myspaceurlOld){		
		isModified = true;			
		flag = isValidMyspace(myspace);
	}		
	
	if(whatsapp != whatsappnumberOld){		
		isModified = true;
		flag = isValidWhatsapp(whatsapp);
	}
	
	if(viber != vibernumberOld){		
		isModified = true;
		flag = isValidViber(viber);
	}

	if(tutorStatus != tutorstatusOld){
		isModified = true;
	}
	
	if(emailOld != email){		
		isModified = true;	
		flag = isValidEmail(email);
	}
	
	if(username != usernameOld){
		isModified = true;
		flag = isValidUsername(username);
	}
	
	if(!isempty(oldPassword) && !isempty(newPassword) && !isempty(confirmPassword)){	
		isModified = true;
		flag = isValidPassword(passwordFromDb, oldPassword, newPassword, confirmPassword);
	}	
	
	if (isModified == false){
		document.getElementById('message').innerHTML = "**You haven't done any changes.";
		flag = false;
	}

	return (flag);
}

/**
 * Validate first name field of the tutor for errors
 * @author CW
 * @param firstname
 */
function isValidFirstname(firstname) {
	var flag = true;
	
	if (!isempty(firstname)) {
		firstName.value = '';
		document.getElementById('firstNameError').innerHTML = "**First name cannot be empty.";
		document.getElementById('firstName').focus();
		flag = false;
		
	}else if(!(/^[A-Za-z\s]+$/.test(firstname))){
		
		document.getElementById('firstNameError').innerHTML = "**First name can have only Characters & Spaces.";
		document.getElementById('firstName').focus();
		flag = false;
	}
	
	if (firstname.length > 20) {
		document.getElementById('firstNameError').innerHTML = "**Max length exceeded.";
		document.getElementById('firstName').focus();
		flag = false;
	}

	return flag;
}

/**
 * Validate middle name field of the tutor for errors
 * @author CW
 * @param middlename
 */
function isValidMiddlename(middlename) {
	var flag = true;
	
	if (isempty(middlename)){
		if(!(/^[A-Za-z\s\u002D]+$/.test(middlename))){			
			document.getElementById('middleNameError').innerHTML = "**Middle name can have only Characters & Spaces.";
			document.getElementById('middleName').focus();
			flag = false;
		}
	}
	
	if (isHavingOnlySpaces(middlename)) {
		middleName.value = '';			
	}
	
	if (middlename.length > 20) {
		document.getElementById('middleNameError').innerHTML = "**Max length exceeded.";
		document.getElementById('middleName').focus();
		flag = false;
	}

	return flag;
}

/**
 * Validate last name field of the tutor for errors
 * @author CW
 * @param lastname
 */
function isValidLastname(lastname) {
	var flag = true;
	
	if (!isempty(lastname)) {
		lastName.value = '';		
		document.getElementById('lastNameError').innerHTML = "**Last Name cannot be empty.";
		document.getElementById('lastName').focus();
		flag = false;
		
	}else if(!(/^[A-Za-z\s]+$/.test(lastname))){
		
		document.getElementById('lastNameError').innerHTML = "**Last Name can have only Characters & Spaces.";
		document.getElementById('lastName').focus();
		flag = false;
	}
	
	if (lastname.length > 20) {
		document.getElementById('lastNameError').innerHTML = "**Max length exceeded.";
		document.getElementById('lastName').focus();
		flag = false;
	}

	return flag;
}

/**
 * Validate Mobile Country Code field of the tutor for errors
 * @author CW
 * @param mobileCountryCode
 */
function isValidMobileCountryCode(mobileCountryCode) {
	var flag = true;
	
	if (!isempty(mobileCountryCode)) {
		document.getElementById('mobileCountryCode').value = '';
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

	return flag;
}

/**
 * Validate Mobile Network Code field of the tutor for errors
 * @author CW
 * @param mobileNetworkCode
 */
function isValidMobileNetworkCode(mobileNetworkCode) {
	var flag = true;	
		
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

	return flag;
}

/**
 * Validate Mobile Number field of the tutor for errors
 * @author CW
 * @param mobileNumber
 */
function isValidMobileNumber(mobileNumber) {
	var flag = true;	
	
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

	return flag;
}

/**
 * Validate Land Country Code field of the tutor for errors
 * @author CW
 * @param landCountryCode
 */
function isValidLandCountryCode(landCountryCode) {
	var flag = true;	
		
	if (!isempty(landCountryCode)) {
		document.getElementById('landCountryCode').value = '';
		document.getElementById('landError').innerHTML = "**Country Code cannot be empty.";
		document.getElementById('landCountryCode').focus();
		flag = false;
	}

	if (landCountryCode.length > 5) {
		document.getElementById('landError').innerHTML = "**Max length exceeded.";
		document.getElementById('landCountryCode').focus();
		flag = false;
	}

	if (isNaN(landCountryCode)) {
		document.getElementById('landError').innerHTML = "**Invalid Country Code";
		document.getElementById('landCountryCode').focus();
		flag = false;
	}	
	return flag;
}

/**
 * Validate Land Area Code field of the tutor for errors
 * @author CW
 * @param landAreaCode
 */
function isValidLandAreaCode(landAreaCode) {
	var flag = true;	
		
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
	return flag;
}

/**
 * Validate LandNumber field of the tutor for errors
 * @author CW
 * @param landNumber
 */
function isValidLandNumber(landNumber) {
	var flag = true;	
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
	return flag;
}

/**
 * Validate Address1 field of the tutor for errors
 * @author CW
 * @param address1
 */
function isValidAddress1(address1) {
	var flag = true;	
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
	return flag;
}

/**
 * Validate Weblink field of the tutor for errors
 * @author CW
 * @param weblink
 */
function isValidWeblink(weblink) {
	var flag = true;	
	if (isempty(weblink)) {
		if (!ValidURL(weblink) && (weblink != "-")) {
			document.getElementById('weblinkError').innerHTML = "**Please Enter correct weblink";
			document.getElementById('weblink').focus();
			flag = false;
		}
	}else{
		document.getElementById('weblink').value = '';
	}

	if (weblink.length > 200) {
		document.getElementById('weblinkError').innerHTML = "**Max length exceeded";
		document.getElementById('weblink').focus();
		flag = false;
	}
	return flag;
}

/**
 * Validate Facebook field of the tutor for errors
 * @author CW
 * @param facebook
 */
function isValidFacebook(facebook) {
	var flag = true;	
	if (isempty(facebook)) {
		if (!ValidURL(facebook) && (facebook != "-")) {
			document.getElementById('facebookError').innerHTML = "**Please Enter correct Facebook link";
			document.getElementById('facebook').focus();
			flag = false;
		}
	}else{
		document.getElementById('facebook').value = '';
	}

	if (facebook.length > 200) {
		document.getElementById('facebookError').innerHTML = "**Max length exceeded";
		document.getElementById('facebook').focus();
		flag = false;
	}
	return flag;
}

/**
 * Validate Linkedin field of the tutor for errors
 * @author CW
 * @param linkedin
 */
function isValidLinkedin(linkedin) {
	var flag = true;	
	if (isempty(linkedin)) {
		if (!ValidURL(linkedin) && (linkedin != "-")) {
			document.getElementById('linkedInError').innerHTML = "**Please Enter correct LinkedIn link";
			document.getElementById('linkedin').focus();
			flag = false;
		}
	}else{
		document.getElementById('linkedin').value = '';
	}

	if (linkedin.length > 100) {
		document.getElementById('linkedInError').innerHTML = "**Max length exceeded";
		document.getElementById('linkedin').focus();
		flag = false;
	}
	return flag;
}

/**
 * Validate Twitter field of the tutor for errors
 * @author CW
 * @param twitter
 */
function isValidTwitter(twitter) {
	var flag = true;	
	if (isempty(twitter)) {
		if (!ValidURL(twitter) && (twitter != "-")) {
			document.getElementById('twitterError').innerHTML = "**Please Enter correct Twitter link";
			document.getElementById('twitter').focus();
			flag = false;
		}
	}else{
		document.getElementById('twitter').value = '';
	}

	if (twitter.length > 100) {
		document.getElementById('twitterError').innerHTML = "**Max length exceeded";
		document.getElementById('twitter').focus();
		flag = false;
	}
	return flag;
}

/**
 * Validate Instagram field of the tutor for errors
 * @author CW
 * @param instagram
 */
function isValidInstagram(instagram) {
	var flag = true;
	if (isempty(instagram)) {
		if (!ValidURL(instagram) && (instagram != "-")) {
			document.getElementById('instagramError').innerHTML = "**Please Enter correct Instagram link";
			document.getElementById('instagram').focus();
			flag = false;
		}
	}else{
		document.getElementById('instagram').value = '';
	}

	if (instagram.length > 100) {
		document.getElementById('instagramError').innerHTML = "**Max length exceeded";
		document.getElementById('instagram').focus();
		flag = false;
	}
	return flag;
}

/**
 * Validate Myspace field of the tutor for errors
 * @author CW
 * @param myspace
 */
function isValidMyspace(myspace) {
	var flag = true;	
	if (isempty(myspace)) {
		if (!ValidURL(myspace) && (myspace != "-")) {
			document.getElementById('mySpaceError').innerHTML = "**Please Enter correct Myspace link";
			document.getElementById('myspace').focus();
			flag = false;
		}
	}else{
		document.getElementById('myspace').value = '';
	}

	if (myspace.length > 100) {
		document.getElementById('mySpaceError').innerHTML = "**Max length exceeded";
		document.getElementById('myspace').focus();
		flag = false;
	}
	return flag;
}

/**
 * Validate Whatsapp field of the tutor for errors
 * @author CW
 * @param whatsapp
 */
function isValidWhatsapp(whatsapp) {
	var flag = true;			
	if (isempty(whatsapp)) {
		if (isNaN(whatsapp)) {
			document.getElementById('whatsappError').innerHTML = "**Invalid whatsapp number";
			document.getElementById('whatsapp').focus();
			flag = false;
		}
	}else{
		document.getElementById('whatsapp').value = '';
	}
		
	if (isempty(whatsapp)) {
		if (whatsapp.length > 20) {
			document.getElementById('whatsappError').innerHTML = "**Max length exceeded";
			document.getElementById('whatsapp').focus();
			flag = false;
		}
	}
	return flag;
}

/**
 * Validate Viber field of the tutor for errors
 * @author CW
 * @param viber
 */
function isValidViber(viber) {
	var flag = true;			
		if (isempty(viber)) {
			if (isNaN(viber)) {
				document.getElementById('viberError').innerHTML = "**Invalid Viber number";
				document.getElementById('viber').focus();
				flag = false;
			}
		}else{
			document.getElementById('viber').value = '';
		}
	
		if (isempty(viber)) {
			if (viber.length > 20) {
				document.getElementById('viberError').innerHTML = "**Max length exceeded";
				document.getElementById('viber').focus();
				flag = false;
			}
		}
	return flag;
}

/**
 * Validate Email field of the tutor for errors
 * @author CW
 * @param email
 */
function isValidEmail(email) {
	var flag = true;	
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

	if (weblink.length > 255) {
		document.getElementById('weblinkError').innerHTML = "**Max length exceeded";
		document.getElementById('weblink').focus();
		flag = false;
	}
	
	var emailExist = ValidateEmail(email);
	if (emailExist.message == '0') {
		document.getElementById('emailError').innerHTML = "**Email entered Already exists.";
		document.getElementById('email').focus();
		flag = false;
	}
	return flag;
}

/**
 * Validate Username field of the tutor for errors
 * @author CW
 * @param username
 */
function isValidUsername(username) {
	var flag = true;			
	if (!isempty(username)) {
		document.getElementById('username').value = '';
		document.getElementById('usernameError').innerHTML = "**Username cannot be empty.";
		document.getElementById('username').focus();
		flag = false;
	}
		
	if (username.length > 20) {
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
	return flag;
}

/**
 * Validate Password field of the tutor for errors
 * @author CW
 * @param password
 */
function isValidPassword(passwordFromDb, oldPassword, newPassword, confirmPassword) {
	var flag = true;				
	
	//var validPasswords = ValidatePasswords(passwordFromDb, oldPassword, newPassword, confirmPassword)
	
	var resp = null;
	$.ajax({
		url : '/TutorController',
		method : 'POST',
		async : false,
		data : {
			CCO : 'CHECK_PASSWORDS',
			passwordFromDb : passwordFromDb,
			oldPassword : oldPassword,
			newPassword : newPassword,
			confirmPassword : confirmPassword
		},
		dataType : "json",
		success : function(response) {
			resp = response;
		},
		error : function(response) {
			flag = response;
		}
	});

	return flag;
	
	
	
	/*if (!isempty(password)) {
		document.getElementById('passwordError').innerHTML = "**Password cannot be empty.";
		document.getElementById('password').focus();
		flag = false;
	}
	
	if (password.length > 20) {
		document.getElementById('passwordError').innerHTML = "**Password Max length exceeded.";
		document.getElementById('password').focus();
		flag = false;
	}
	
	if (password.length < 6) {
		document.getElementById('passwordError').innerHTML = "**Password should have at least 6 characters.";
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
	}*/
	//return flag;
}

/**
 * Validate Password field of the tutor for errors
 * @author CW
 * @param passwordFromDb, oldPassword, newPassword, confirmPassword
 */
/*function ValidatePasswords(passwordFromDb, oldPassword, newPassword, confirmPassword) {
	var resp = null;
	$.ajax({
		url : '/TutorController',
		method : 'POST',
		async : false,
		data : {
			CCO : 'CHECK_PASSWORDS',
			passwordFromDb : passwordFromDb,
			oldPassword : oldPassword,
			newPassword : newPassword,
			confirmPassword : confirmPassword
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