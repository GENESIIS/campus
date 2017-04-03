/**
 * 20170108 JH c39-add-course-provider course-provider-validator.js created
 * 
 */

//20170209 JH c39-add-course-provider validation methods for expiration date changed
//20170221 JH c141-add-course-provider-issue-improvements removed commented front end validation part, added method comments,removed unwanted codes
//20170223 JH c141-add-course-provider-issue-improvements providerUsernameValidation():checked for username>100 and error messages changed
//20170226 JH c141-add-course-provider-issue-improvements isValidMinMaxLength(): created to validate both min and max values of a parameter, added method comments
//20170227 JH c141-add-course-provider-issue-improvements validateFormURL(): modified to validate URL maximum length
//20170228 JH c141-add-course-provider-issue-improvements isValidMinMaxLength() modified, front end validation method changed due to one off course provider implementation
//20170323 JH c141-ui-for-add-course-provider modified providerPrefixValidation() method 
//20170324 JH c141-ui-for-add-course-provider providerUsernameValidation() method, validateFormURL(),  changes wip
//20170327 JH c141-ui-for-add-course-provider validateFormURL changes wip, removed commented codes in vaidateCourseProviderDeatils() method 
//20170328 JH c141-ui-for-add-course-provider vaidateCourseProviderDeatils() method code refactoring wip, validateFormURL():error handling changed to add style classes and
//				to accept min max length for url length validation as parameters 
//20170329 JH c141-ui-for-add-course-provider display hint messages on page load for phone number fields, vaidateCourseProviderDeatils() modified to do phone number validation wip
//20170330 JH c141-ui-for-add-course-provider isempty() method modified to fix an error
//20170403 JH c141-ui-for-add-course-provider providerUsernameValidation() method modified to use common min max length validations, vaidateCourseProviderDeatils() front end validation changes

window.prefixFlag = true;
window.usernameFlag = true;

/**
 * @author JH
 * @param fieldValue
 *            it is the value of a document element
 * @returns true if has content else false. (used to validate string values)
 */
function isempty(fieldValue) {
	return (($.trim(fieldValue) == "") || (fieldValue == null)) ? false : true;
}


/**
 * @author JH
 * @param fieldValue
 * @returns true if no value selected. else false
 */
function isEmptyValue(fieldValue){
	return ((fieldValue== "") || (fieldValue == null)) ? false : true;
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
 * validateFormURL created to validate URL's and set error messages 
 * @author JH
 * @param url
 * @param errorElementId
 * @param foucsElementId
 * @returns {Boolean}
 */
function validateFormURL(url, errorElementId, foucsElementId, min, max){
	var flag = true;
	
	
	if(!isValidMinMaxLength(url, min,  max)){
		var message2 = "URL is empty or too long.";	
		setErrorMessage(foucsElementId, errorElementId, message2);
		flag = false;
	}else if (isempty(url) && !ValidURL(url)) {
		var message1 = "Invalid URL.";		
		setErrorMessage(foucsElementId, errorElementId, message1);
		flag = false;
	}
	return flag;
}


/**
 * @author JH
 * @param parameter
 * @param length
 * @returns boolean true if length is valid else false
 */
function isValidLength(parameter, length) {
	return (parameter > length) ? false : true;
}


/**
 * isValidMinMaxLength method used to validate parameter
 * @param parameter
 * @param min
 * @param max
 * @returns boolean true if the length is valid, else return false
 * @author JH
 */
function isValidMinMaxLength(parameter, min, max) {
	parameter = $.trim(parameter);
	return ((parameter.length > max) || (parameter.length < min)) ? false : true;
}


/**
 * providerUsernameValidation() used to validate the course provider username
 * @returns {Boolean}
 * @author JH
 */
function providerUsernameValidation() {

	clearToolTip('#usernameDiv');
	
	var flag = false;
	var integerPattern = /^[0-9]+$/; //pattern to validate for numbers

//	var selectedUsername = document.getElementById('providerUsername').value;
	var selectedUsername = $("#providerUsername").val();
	var userEmail = document.getElementById('providerEmail').value;
	var message = "Error";
	
	if (!isempty(selectedUsername)
			|| isPatternMatch(integerPattern, selectedUsername)) {

		message = "Username is empty or can't contain only numbers.";

		setErrorMessage('#usernameDiv', '#errorUsername', message);
		return false;
	} else {
		if (!isValidMinMaxLength(selectedUsername, 5, 100)) {// check whether the username has
											// less than 5 characters
			message = "Username is too small or has exceeded the max length. It must have min 5 and max 100 characters.";
			setErrorMessage('#usernameDiv', '#errorUsername', message);
			return false;
		} else {

			$
					.ajax({
						url : '/AdminController',
						method : 'POST',
						data : {
							'CCO' : 'COURSE_PROVIDER_VALIDATION',
							'action' : 'COURSE_PROVIDER_USERNAME_VALIDATION',
							'username' : selectedUsername,
							'email' : userEmail
						},
						dataType : "json",
						async : false,
						success : function(response) {

							if (response !== undefined && response !== null) {
								window.usernameFlag == response.validationFlag;

								if (response['validationFlag'] === 1) {
									flag = true;
									message = response.userMessage;
									setSuccessMessage('#usernameDiv', '#errorUsername', message);
								}
								if (response['validationFlag'] === 0) {
									flag = false;
									message = response.userMessage;
									setErrorMessage('#usernameDiv', '#errorUsername', message);
								}

								return flag;

							}
						},
					});
		}
	}

}

/**
 * providerPrefixValidation() used to validate the course provider unique prefix
 * @returns {Boolean}
 * @author JH
 */
function providerPrefixValidation() {

	var selectedPrefix = $("#uniquePrefix").val();
	
	clearToolTip('#uniquePrefixDiv');
	var flag = false;
	var message = null;

	if (!isempty(selectedPrefix)) {

		message = "Give a unique name.";
		setErrorMessage('#uniquePrefixDiv', '#errorUniquePrefix', message);
		return false;
		
	} else if (selectedPrefix.length < 2) {

		setErrorMessage('#uniquePrefixDiv', '#errorUniquePrefix', "Unique prefix is too small");
		
		return false;
	} else if (selectedPrefix.length > 20) {

		setErrorMessage('#uniquePrefixDiv', '#errorUniquePrefix', "Unique prefix is too large");
		return false;
	} else {

		$
				.ajax({
					url : '/AdminController',
					method : 'POST',
					data : {
						'CCO' : 'COURSE_PROVIDER_VALIDATION',
						'action' : 'COURSE_PROVIDER_PREFIX_VALIDATION',
						'prefix' : selectedPrefix
					},
					dataType : "json",
					async : false,
					success : function(response) {

						if (response !== undefined && response !== null) {

							window.prefixFlag == response.validationFlag;

							if (response['validationFlag'] === 1) {
								setSuccessMessage('#uniquePrefixDiv', '#errorUniquePrefix', response.userMessage);
								flag = true;
							}
							if (response['validationFlag'] === 0) {
								setErrorMessage('#uniquePrefixDiv', '#errorUniquePrefix', response.userMessage);
								flag = false;
							}

							return flag;
						}
					},
				});
	}

}

/**
 * created to validate course provider details before submit	
 * @author JH
 */
function vaidateCourseProviderDeatils(form) {

	var integerPattern = /^[0-9]+$/;
	var flag = true;

	var courseProvider = $("#courseProvider").val();
	var providerName = $("#providerName").val();
	var shortName = $("#shortName").val();
	var uniquePrefix = $("#uniquePrefix").val();
	var aboutMe = $("#aboutMe").val();
	var specialFeatures = $("#specialFeatures").val();
	var generalEmail = $("#generalEmail").val();
	var inquiryMail = $("#inquiryMail").val();
	var areaCode1 = $("#areaCode1").val();
	var areaCode2 = $("#areaCode2").val();
	var areaCode3 = $("#areaCode3").val();
	var land1 = $("#land1").val();
	var land2 = $("#land2").val();
	var fax = $("#fax").val();
	var networkCode = $("#networkCode").val();
	var mobile = $("#mobile").val();
	var address1 = $("#address1").val();
	var address2 = $("#address2").val();
	var address3 = $("#address3").val();
	var country = $("#selectedCountry").val();
	var townList = $("#selectedTown").val();
	var webLink = $("#webLink").val();
	var facebook = $("#facebook").val();
	var linkdedIn = $("#linkdedIn").val();
	var twitter = $("#twitter").val();
	var instagram = $("#instagram").val();
	var mySpace = $("#mySpace").val();
	var whatsapp = $("#whatsapp").val();
	var viber = $("#viber").val();
	var expirationDate = $("#expirationDate").val();
	var providerType = $("#selectedProviderType").val();
	var providerStatus = $('input[name=providerStatus]:checked').val();
	var providerPrivateName = $("#providerPrivateName").val();
	var providerEmail = $("#providerEmail").val();
	var providerUsername = $("#providerUsername").val();
	var providerPassword = $("#providerPassword").val();
	var cProviderPassword = $("#cProviderPassword").val();
	var accountStatus = $('input[name=accountStatus]:checked').val();
	var accountDescription = $("#accountDescription").val();
	
	var accountContactNumber = $("#providerContactNumber").val();

	if (!isempty(courseProvider)) {		
		setErrorMessage('#accountTypeDiv', '#errorAccountTypes' , "Select a course provider type.");
		flag = false;
	}

	if (!isValidMinMaxLength(providerName, 1, 200)) {
		setErrorMessage('#providerNameDiv', '#errorProviderName', "Provider name is empty or too long.");
		flag = false;
	}
	if (!isValidMinMaxLength(uniquePrefix, 2, 20)) {
		setErrorMessage('#uniquePrefixDiv', '#errorCourseProvider', "Invalid unique name. Requires 2 to 20 characters.");
		flag = false;
	}
	if (!isValidMinMaxLength(shortName, 0,  20)) {
		setErrorMessage('#shortNameDiv', '#errorShortName', "Short name should be less than 20 characters.");
		flag = false;
	}
	if (!isempty(aboutMe)) {
		setErrorMessage('#aboutMeDiv', '#errorAboutMe', "Give a breif description.");
		flag = false;
	}
	if (!isValidMinMaxLength(specialFeatures, 0, 100)) {
		setErrorMessage('#specialFeaturesDiv', '#errorSpecialFeatures', "Only 100 characters allowed.");
		flag = false;
	}
	if (!isValidEmailFormat(generalEmail)) {
		setErrorMessage('#generalEmailDiv', '#errorGeneralEmail', "Invalid email.");
		flag = false;
	}
	if (!isValidMinMaxLength(generalEmail, 1,  255)) {
		setErrorMessage('#generalEmailDiv', '#errorGeneralEmail', "General email field is empty or too long.");
		flag = false;
	}
	if (!isValidEmailFormat(inquiryMail)) {
		setErrorMessage('#inquiryMailDiv', '#errorInquiryMail', "Invalid email.");
		flag = false;
	}
	if (!isValidMinMaxLength(inquiryMail, 1, 255)) {
		setErrorMessage('#inquiryMailDiv', '#errorInquiryMail', "Empty or too long inquiry mail.");
		flag = false;
	}
	if (!isValidMinMaxLength(land1, 1, 20)) {
		setErrorMessage('#land1Div', '#land1Div', "Land phone number can't be empty (maximum 20 characters).");
		flag = false;
	}
	if (isempty(courseProvider) && !isPatternMatch(integerPattern, land1)) {
		setErrorMessage('#land1Div', '#errorLand1', "Invalid Land phone number.");
		flag = false;
	}
	if (!isValidMinMaxLength(fax, 0, 20) || !isPatternMatch(integerPattern, fax)) {
		setErrorMessage('#faxDiv', '#errorFax', "Fax number invalid or exceed the length (maximum 20 characters).");
		flag = false;
	}
	if (!isValidMinMaxLength(land2, 0, 20) || !isPatternMatch(integerPattern, land2)) {
		setErrorMessage('#land2Div', '#errorLand2', "Land phone number 2 is not valid or exceed the length (maximum 20 characters).");
		flag = false;
	}
	if (!isValidMinMaxLength(areaCode1, 0, 20) && !isPatternMatch(integerPattern, areaCode1)) {
		setErrorMessage('#land1Div', '#errorLand1', "Area code is invalid or exceed the length (maximum 20 chracters). ");
		flag = false;
	}
	if (!isValidMinMaxLength(areaCode2, 0, 20) && !isPatternMatch(integerPattern, areaCode2)) {
		setErrorMessage('#land2Div', '#errorLand2', "Area code is invalid or exceed the length (maximum 20 chracters). ");
		flag = false;
	}
	if (!isValidMinMaxLength(mobile, 1, 20) || !isPatternMatch(integerPattern, mobile)) {
		setErrorMessage('#mobileDiv', '#errorMobile', "Give a valid mobile phone number.");
		document.getElementById('mobile').focus();
		flag = false;
	}
	if (!isValidMinMaxLength(networkCode, 1, 20) || !isPatternMatch(integerPattern, networkCode)) {
		setErrorMessage('#mobileDiv', '#errorMobile', "Invalid or too long network code. Maximum 20 characters.");
		flag = false;
	}
	if (!isValidMinMaxLength(address1, 1, 50)) {
		setErrorMessage('#address1Div', '#errorAddress1', "Address is empty or too long ( sholud beless than 50 characters).");
		flag = false;
	}
	if (!isValidMinMaxLength(address2, 0, 50)) {
		setErrorMessage('#address2Div', '#errorAddress2', "Address line should be less than 50 characters.");
		flag = false;
	}
	if (!isValidMinMaxLength(address3, 0, 50)) {
		setErrorMessage('#address3Div', '#errorAddress3', "Address line should be less than 50 characters.");
		flag = false;
	}
	if (!isEmptyValue(country)) {
		setErrorMessage('#country-List', '#errorSelectedCountry', "Please select a country.");
		flag = false;
	}
	if (!isEmptyValue(townList) && !isempty(country)) {
		setErrorMessage('#town-List', '#errorSelectedTown', "First select your country.");
		flag = false;
	}
	if (!isEmptyValue(townList) && isEmptyValue(country)) {
		setErrorMessage('#town-List', errorCourseProvider, "Select your town.");
		flag = false;	
	}

	flag  = validateFormURL(webLink, '#errorWebLink', '#webLinkDiv', 1, 255);
	flag  = validateFormURL(facebook, '#errorFacebook', '#facebookDiv', 0, 255);
	flag  = validateFormURL(linkdedIn, '#errorLinkedIn', '#linkdedInDiv', 0, 255);
	flag = validateFormURL(twitter, '#errorTwitter', '#twitterDiv', 0, 255);
	flag =  validateFormURL(instagram, '#errorInstagram', '#instagramDiv', 0, 255);
	flag = validateFormURL(mySpace, '#errorMyspace', '#mySpaceDiv', 0, 255);

	 if (isempty(whatsapp) && !isPatternMatch(integerPattern, whatsapp) ) {
		setErrorMessage('#whatsappDiv', '#errorWhatsapp', "Invalid whatsapp number.");
		flag = false;
	 }
	 if (!isValidMinMaxLength(whatsapp, 0, 20)) {
		 setErrorMessage('#whatsappDiv', '#errorWhatsapp', "Whatsapp number is too long (maximum 20 characters).");
			flag = false;
	 }
	 if (isempty(viber) && !isPatternMatch(integerPattern, viber)) {;
			setErrorMessage('#viberDiv', '#errorViber', "Invalid viber number.");
		flag = false;
	 }
	 if (!isValidMinMaxLength(viber, 0, 20)) {
			setErrorMessage('#viberDiv', '#errorViber', "Viber number is too long (maximum 20 characters).");
		flag = false;
	}
	if (!isEmptyValue(providerType)) {
		setErrorMessage('#providerTypeList', '#errorProviderType', "Select course provider type.");
		flag = false;
	}
	if (providerStatus === null || providerStatus === undefined) {
		setErrorMessage('#providerStatusDiv', '#errorProviderStatus', "Select the course provider status.");
		flag = false;
	}
	
	if (!isValidMinMaxLength(providerPrivateName, 1, 100)) {
		setErrorMessage('#providerPrivateNameDiv', '#errorPrivateName', "Personal name is empty or too long.");
		flag = false;
	} 
	 if (!isValidEmailFormat(providerEmail)) {
			setErrorMessage('#providerEmailDiv', '#errorPrivateEmail', "Private email is invalid.");
			flag = false;
		}
	if (!isValidMinMaxLength(providerEmail, 1, 255)) {
		setErrorMessage('#providerEmailDiv', '#errorPrivateEmail', "Private email address is required. Maximum 255 charaters allowed.");
		flag = false;
	}
	if (isPatternMatch(integerPattern, providerUsername)) {
		setErrorMessage('#providerUsernameDiv', '#errorUsername', "Username can't contain only numbers.");
		flag = false;
	}
	if (!isValidMinMaxLength(providerUsername, 5, 100)) {
		setErrorMessage('#providerUsernameDiv', '#errorUsername', "Username is too small or has exceeded the max length. It must have min 5 and max 100 characters.");
		flag = false;
	}
	if (!isValidMinMaxLength(providerPassword, 6, 100)) {
		setErrorMessage('#providerPasswordDiv', '#errorProviderPassword', "Password is weak.");
		flag = false;
	}
	if (!isempty(providerPassword) || !isempty(cProviderPassword)) {
		setErrorMessage('#providerPasswordDiv', '#errorProviderPassword', "Empty password field(s).");
		flag = false;
	}

// if ((isempty(providerPassword) || providerPassword.length < 6)
// && !isempty(cProviderPassword)
// && (providerPassword != cProviderPassword)) {
// document.getElementById('errorCProviderPassword').innerHTML = "**Confirm
// password does not match.";
// document.getElementById('cProviderPassword').focus();
// flag = false;
// }
// if (cProviderPassword.length > 100) {
// document.getElementById('errorProviderPassword').innerHTML = "**Password is
// too long.";
// document.getElementById('providerPassword').focus();
// flag = false;
// }
	if (accountStatus === null || accountStatus === undefined) {
		setErrorMessage('#accountStatusDiv', '#errorStatus', "Select the account status.");
		flag = false;
	}
	if (!isValidLength(accountDescription, 4000)) {
		setErrorMessage('#accountDescriptionDiv', '#errorAccountDescription', "Description is too long.");
		flag = false;
	}
	if (!isValidMinMaxLength(accountContactNumber, 1, 20) || !isPatternMatch(integerPattern, accountContactNumber)) {
		setErrorMessage('#providerContactNumberDiv', '#errorContactNumber', "Invlaid or empty contact number.");
		flag = false;
	}

	return flag;
}