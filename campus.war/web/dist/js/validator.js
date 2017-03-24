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
 * //20170305 CW c37-tutor-update-tutor-profile-cw modified validateTutorModificationsByTutor() to add password related validations & remove un wanted commented lines
 * //20170307 CW c37-tutor-update-tutor-profile-cw modified isValidEmail() method & fix a minor bug 
 * //20170316 CW c37-tutor-update-tutor-profile-cw modified field max lengths to match database field max size
 * //20170322 CW c37-tutor-update-tutor-profile-cw add getFlagVal method & modified validateTutorModificationsByTutor method to fix flag updating error
 * 				// modified validateTutorModificationsByTutor method to fix town validation errors
 * //20170323 CW c37-tutor-update-tutor-profile-cw modified validateTutorModificationsByTutor method & add address2, address3 field space removal validations
 * //20170324 CW c37-tutor-update-tutor-profile-cw modified validateTutorModificationsByTutor method & fix password validation error
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
 * Update & returns the flag value validate with flagTemp
 * @author cw
 * @param flag
 * @param flagTemp
 * @returns
 */
function getFlagVal(flag, flagTemp){
	if(flag == true){
		flag = flagTemp;
	}
	return flag;
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
	var flagTemp = true;
	var isModified = false;
	
	if(firstname != firstnameOld){
		isModified = true;
		flagTemp = isValidFirstname(firstname);
		flag = getFlagVal(flag, flagTemp);
	}
	
	if(middlename != middlenameOld){		
		isModified = true;
		flagTemp = isValidMiddlename(middlename);
		flag = getFlagVal(flag, flagTemp);
	}	

	if(lastname != lastnameOld){		
		isModified = true;
		flagTemp = isValidLastname(lastname);
		flag = getFlagVal(flag, flagTemp);
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
		if((country == countrynameOld) && (country != "--- Select Country ---")){
			if ((town != townOld) && (town != "--- Select Country before select the town ---") && (town != "--- Select town ---")) {
				isModified = true;
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
		if ((town != townOld) && (town != "--- Select Country before select the town ---") && (town != "--- Select town ---")) {
			isModified = true;
		}
	}

	if(mobileCountryCode != mobilephonecountrycodeOld){		
		isModified = true;
		flagTemp = isValidMobileCountryCode(mobileCountryCode); 
		flag = getFlagVal(flag, flagTemp);
	}

	if(mobileNetworkCode != mobilephonenetworkcodeOld){		
		isModified = true;
		flagTemp = isValidMobileNetworkCode(mobileNetworkCode); 
		flag = getFlagVal(flag, flagTemp);
	}

	if(mobileNumber != mobilephonenumberOld){		
		isModified = true;		
		flagTemp = isValidMobileNumber(mobileNumber); 
		flag = getFlagVal(flag, flagTemp);
	}

	if(landCountryCode != landphonecountrycodeOld){		
		isModified = true;
		flagTemp = isValidLandCountryCode(landCountryCode);
		flag = getFlagVal(flag, flagTemp);
	}

	if(landAreaCode != landphoneareacodeOld){		
		isModified = true;
		flagTemp = isValidLandAreaCode(landAreaCode);	
		flag = getFlagVal(flag, flagTemp);
	}

	if(landNumber != landphonenumberOld){		
		isModified = true;		
		flagTemp = isValidLandNumber(landNumber);
		flag = getFlagVal(flag, flagTemp);
	}

	if(address1 != address1Old){		
		isModified = true;		
		flagTemp = isValidAddress1(address1);
		flag = getFlagVal(flag, flagTemp);
	}
	
	if (isHavingOnlySpaces(address2)) {
		document.getElementById('address2').value = '';	
	}

	if (isHavingOnlySpaces(address3)) {
		document.getElementById('address3').value = '';
	}
	
	if(weblink != weblinkOld){		
		isModified = true;		
		flagTemp = isValidWeblink(weblink);
		flag = getFlagVal(flag, flagTemp);
	}
	
	if(facebook != facebookurlOld){		
		isModified = true;		
		flagTemp = isValidFacebook(facebook);
		flag = getFlagVal(flag, flagTemp);
	}
	
	if(linkedin != linkedinurlOld){		
		isModified = true;
		flagTemp = isValidLinkedin(linkedin);
		flag = getFlagVal(flag, flagTemp);
	}
	
	if(twitter != twitterurlOld){		
		isModified = true;		
		flagTemp = isValidTwitter(twitter);
		flag = getFlagVal(flag, flagTemp);
	}
	
	if(instagram != instagramurlOld){	
		isModified = true;				
		flagTemp = isValidInstagram(instagram);
		flag = getFlagVal(flag, flagTemp);
	}
	
	if(myspace != myspaceurlOld){		
		isModified = true;			
		flagTemp = isValidMyspace(myspace);
		flag = getFlagVal(flag, flagTemp);
	}		
	
	if(whatsapp != whatsappnumberOld){		
		isModified = true;
		flagTemp = isValidWhatsapp(whatsapp);
		flag = getFlagVal(flag, flagTemp);
	}
	
	if(viber != vibernumberOld){		
		isModified = true;
		flagTemp = isValidViber(viber);
		flag = getFlagVal(flag, flagTemp);
	}

	if(tutorStatus != tutorstatusOld){
		isModified = true;
	}
	
	if(emailOld != email){		
		isModified = true;	
		flagTemp = isValidEmail(email);
		flag = getFlagVal(flag, flagTemp);
	}
	
	if(username != usernameOld){
		isModified = true;
		flagTemp = isValidUsername(username);
		flag = getFlagVal(flag, flagTemp);
	}

	if(isempty(oldPassword)){ // old password has content
		isModified = true;
		if(isempty(newPassword)){ // new password has content
			if(isempty(confirmPassword)){ // confirm password has content
				var valid = isValidPassword(passwordFromDb, oldPassword, newPassword, confirmPassword); 

				if(valid.message == 'FALSE'){
					if(valid.oldPasswordError != null){
						document.getElementById('oldPasswordError').innerHTML = " ** " + valid.oldPasswordError;						
						document.getElementById('oldPassword').value = '';						
						document.getElementById('oldPassword').focus();
					}

					if(valid.newPasswordError != null){
						document.getElementById('newPasswordError').innerHTML = " ** " + valid.newPasswordError;
						document.getElementById('newPassword').focus();
					}

					if(valid.confirmPasswordError != null){
						document.getElementById('confirmPasswordError').innerHTML = " ** " + valid.confirmPasswordError;
						document.getElementById('confirmPassword').focus();
					}
					flag = false; 
				}			
			}else{ // confirm password empty
				flag = false; 
				document.getElementById('confirmPasswordError').innerHTML = " ** Please enter confirm password";
			}
		}else{ // new password empty
			flag = false; 
			document.getElementById('newPasswordError').innerHTML = " ** Please enter password";
			if(!isempty(confirmPassword)){ // confirm password empty
				document.getElementById('confirmPasswordError').innerHTML = " ** Please enter confirm password";			
			}                               
		}
	}else{
		if(isempty(newPassword)){
			isModified = true;
			flag = false; 
			document.getElementById('newPasswordError').innerHTML = " ** Please enter old password";
		}
		if(isempty(confirmPassword)){
			isModified = true;
			flag = false; 
			document.getElementById('confirmPasswordError').innerHTML = " ** Please enter old password";			
		}
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
	
	if (firstname.length > 35) {
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
	
	if (middlename.length > 35) {
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
	
	if (lastname.length > 35) {
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

	if (landCountryCode.length > 10) {
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

	if (landNumber.length > 15) {
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
	return flag;
}

/**
 * Validate Password field of the tutor for errors
 * @author CW
 * @param password
 */
function isValidPassword(passwordFromDb, oldPassword, newPassword, confirmPassword) {
	var flag = true;				
	
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