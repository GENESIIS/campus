/**
 * 20170108 JH c39-add-course-provider course-provider-validator.js created
 * 
 */

//20170209 JH c39-add-course-provider validation methods for expiration date changed
//20170221 JH c141-add-course-provider-issue-improvements removed commented front end validation part, added method comments,removed unwanted codes
//20170223 JH c141-add-course-provider-issue-improvements providerUsernameValidation():checked for username>100 and error messages changed
//20170226 JH c141-add-course-provider-issue-improvements isValidMinMaxLength(): created to validate both min and max values of a parameter, added method comments
//20170227 JH c141-add-course-provider-issue-improvements validateFormURL(): modified to validate URL maximum length

window.prefixFlag = true;
window.usernameFlag = true;

/**
 * @author JH
 * @param fieldValue
 *            it is the value of a document element
 * @returns true if has content else false. (used to validate string values)
 */
function isempty(fieldValue) {
	return ((fieldValue.trim() == "") || (fieldValue == null)) ? false : true;
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
function validateFormURL(url, errorElementId, foucsElementId){
	var flag = true;
	if (!ValidURL(url)) {
		var message = "**Invalid URL.";		
		document.getElementById(errorElementId ).innerHTML = message;
		document.getElementById(foucsElementId).focus();
		flag = false;
	}
	
	if(!isValidMinMaxLength(url, 0,  255)){
		var message = "**URL is too long.";	
		document.getElementById(errorElementId ).innerHTML = message;
		document.getElementById(foucsElementId).focus();
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
	parameter = parameter.trim();
	return ((parameter > max) || (parameter < min)) ? false : true;
}


/**
 * providerUsernameValidation() used to validate the course provider username
 * @returns {Boolean}
 * @author JH
 */
function providerUsernameValidation() {

	document.getElementById('errorUsername').innerHTML = "";
	document.getElementById('usernameMessage').innerHTML = "";

	var flag = false;
	var integerPattern = /^[0-9]+$/;

	var selectedUsername = document.getElementById('providerUsername').value;
	var userEmail = document.getElementById('providerEmail').value;

	if (!isempty(selectedUsername)
			|| isPatternMatch(integerPattern, selectedUsername)) {
		document.getElementById('errorUsername').innerHTML = "**Username is empty or can't contain only numbers.";
		document.getElementById('providerUsername').focus();
		return false;
	} else {
		if (selectedUsername.length < 5 || selectedUsername.length >100) {// check whether the username has
											// less than 5 characters
			document.getElementById('errorUsername').innerHTML = "**Username is too small or has exceeded the max length. It must have min 5 and max 100 characters.";
			return false;
		} else {
			document.getElementById('errorUsername').innerHTML = "";

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
									document.getElementById('usernameMessage').innerHTML = response.userMessage;
								}
								if (response['validationFlag'] === 0) {
									flag = false;
									document.getElementById('errorUsername').innerHTML = response.userMessage;
									document.getElementById('providerUsername').focus();
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

	var selectedPrefix = document.getElementById('uniquePrefix').value;
	document.getElementById('errorUniquePrefix').innerHTML = "";
	document.getElementById('prefixMessage').innerHTML = "";
	var flag = false;

	if (!isempty(selectedPrefix)) {

		document.getElementById('prefixMessage').innerHTML = "";
		document.getElementById('errorUniquePrefix').innerHTML = "**Give a unique name.";
		document.getElementById('uniquePrefix').focus();
		return false;
	} else if (selectedPrefix.length < 2) {

		document.getElementById('errorUniquePrefix').innerHTML = "Unique prefix is too small";
		return false;
	} else if (selectedPrefix.length > 20) {

		document.getElementById('errorUniquePrefix').innerHTML = "Unique prefix is too large";
		document.getElementById('prefixMessage').value = "";
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
								document.getElementById('prefixMessage').innerHTML = response.userMessage;
								flag = true;
							}
							if (response['validationFlag'] === 0) {
								document.getElementById('errorUniquePrefix').innerHTML = response.userMessage;
								document.getElementById('uniquePrefix').focus();
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
	var areaCode = $("#areaCode").val();
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
	var publishProgram = $('input[name=publishProgram]:checked').val();
	var currentDate = new Date();
	var date = currentDate.getFullYear()+'-'+(currentDate.getMonth()+1)+'-'+currentDate.getDate();
	var selectedDate = 	$('#expirationDate').val();
	var accountContactNumber = $("#providerContactNumber").val();

		if (date > selectedDate) {
			document.getElementById('errorExpiration').innerHTML = "**Invlid date (Date should be greater than today's date.";
			document.getElementById('expirationDate').focus();
			flag = false;
		}
		if (selectedDate === "" || selectedDate===null) {
			document.getElementById('errorExpiration').innerHTML = "**Select the expiration date.";
			document.getElementById('expirationDate').focus();
			flag = false;
		}

	if (!isempty(courseProvider)) {
		document.getElementById('errorCourseProvider').innerHTML = "**Select a course provider type.";
		document.getElementById('courseProvider').focus();
		flag = false;
	}
	if (!isValidMinMaxLength(providerName, 1, 200)) {
		document.getElementById('errorProviderName').innerHTML = "**Provider name is empty or too long.";
		document.getElementById('providerName').focus();
		flag = false;
	}
	if (!isValidMinMaxLength(uniquePrefix, 2, 20)) {
		document.getElementById('errorUniquePrefix').innerHTML = "**Invalid unique name. Requires 2 to 20 characters";
		document.getElementById('uniquePrefix').focus();
		flag = false;
	}
	if (!isValidMinMaxLength(shortName, 0,  20)) {
		document.getElementById('errorShortName').innerHTML = "**Short name is too long.";
		document.getElementById('shortName').focus();
		flag = false;
	}
	if (!isempty(aboutMe)) {
		document.getElementById('errorAboutMe').innerHTML = "**Give a breif description.";
		document.getElementById('aboutMe').focus();
		flag = false;
	}
	if (!isValidMinMaxLength(specialFeatures, 0, 100)) {
		document.getElementById('errorSpecialFeatures').innerHTML = "**Only 100 characters allowed.";
		document.getElementById('specialFeatures').focus();
		flag = false;
	}
	if (!isValidMinMaxLength(generalEmail, 1,  255)) {
		document.getElementById('errorGeneralEmail').innerHTML = "**General email field is empty or too long.";
		document.getElementById('generalEmail').focus();
		flag = false;
	}
	if (!isValidEmailFormat(generalEmail)) {
		document.getElementById('errorGeneralEmail').innerHTML = "**Invalid email.";
		document.getElementById('generalEmail').focus();
		flag = false;
	}
	if (!isValidMinMaxLength(inquiryMail, 1, 255)) {
		document.getElementById('errorInquiryMail').innerHTML = "**Empty or too long inquiry mail.";
		document.getElementById('inquiryMail').focus();
		flag = false;
	}
	if (!isValidEmailFormat(inquiryMail)) {
		document.getElementById('errorInquiryMail').innerHTML = "**Invalid email.";
		document.getElementById('inquiryMail').focus();
		flag = false;
	}
	if (!isValidMinMaxLength(land1, 1, 20)) {
		document.getElementById('errorLand1').innerHTML = "**Land phone number can't be empty (maximum 20 characters).";
		document.getElementById('land1').focus();
		flag = false;
	}
	if (!isPatternMatch(integerPattern, land1)) {
		document.getElementById('errorLand1').innerHTML = "**Invalid Land phone number.";
		document.getElementById('land1').focus();
		flag = false;
	}
	if (!isValidMinMaxLength(fax, 0, 20) || !isPatternMatch(integerPattern, fax)) {
		document.getElementById('errorFax').innerHTML = "**Fax number invalid or exceed the length (maximum 20 characters)";
		document.getElementById('fax').focus();
		flag = false;
	}
	if (!isValidMinMaxLength(land2, 0, 20) || !isPatternMatch(integerPattern, land2)) {
		document.getElementById('errorLand2').innerHTML = "**Land phone number 2 is not valid or exceed the length (maximum 20 chracters).";
		document.getElementById('land2').focus();
		flag = false;
	}
	if (!isValidMinMaxLength(areaCode, 0, 20) && !isPatternMatch(integerPattern, areaCode)) {
		document.getElementById('errorAreaCode').innerHTML = "**Area code is invalid or exceed the length (maximum 20 chracters). ";
		document.getElementById('areaCode').focus();
		document.getElementById('errorLand1').innerHTML = "**Area code is invalid.";
		flag = false;
	}
	if (!isValidMinMaxLength(networkCode, 1, 20) || !isPatternMatch(integerPattern, networkCode)) {
		document.getElementById('errorNetworkCode').innerHTML = "**Invalid or too long network code. Maximum 20 characters.";
		document.getElementById('networkCode').focus();
		flag = false;
	}
	if (!isValidMinMaxLength(mobile, 1, 20) || !isPatternMatch(integerPattern, mobile)) {
		document.getElementById('errorLastMobileNumber').innerHTML = "**Give a valid mobile phone number.";
		document.getElementById('errorNetworkCode').innerHTML = "**Network code is not valid. ";
		document.getElementById('mobile').focus();
		flag = false;
	}
	if (!isValidMinMaxLength(address1, 1, 50)) {
		document.getElementById('errorAddress1').innerHTML = "**Give your permenant address. (50 characters allowed)";
		document.getElementById('errorAddress1').focus();
		flag = false;
	}
	if (!isEmptyValue(country)) {
		document.getElementById('errorSelectedCountry').innerHTML = "**Select your country.";
		document.getElementById('selectedCountry').focus();
		flag = false;
	}
	if (!isEmptyValue(townList) && !isempty(country)) {
		document.getElementById('errorSelectedTown').innerHTML = "**First select your country.";
		document.getElementById('errorSelectedTown').focus();
		flag = false;
	}
	if (!isEmptyValue(townList) && isEmptyValue(country)) {
		document.getElementById('errorSelectedTown').innerHTML = "**Select your town";
		document.getElementById('errorSelectedTown').focus();
		flag = false;
	}
	if(!validateFormURL(webLink, $('#errorWebLink').attr('id'), $('#webLink').attr('id'))){
		flag = false;
	}
	if(!validateFormURL(facebook, $('#errorFacebook').attr('id'), $('#facebook').attr('id'))){
		flag = false;
	}
	if(!validateFormURL(facebook, $('#errorLinkedIn').attr('id'), $('#linkdedIn').attr('id'))){
		flag = false;
	}
	if(!validateFormURL(facebook, $('#errorTwitter').attr('id'), $('#twitter').attr('id'))){
		flag = false;
	}
	if(!validateFormURL(facebook, $('#errorInstagram').attr('id'), $('#instagram').attr('id'))){
		flag = false;
	}
	if(!validateFormURL(facebook, $('#errorMyspace').attr('id'), $('#mySpace').attr('id'))){
		flag = false;
	}
	 if (isempty(whatsapp) && !isPatternMatch(integerPattern, whatsapp) ) {
	 document.getElementById('errorWhatsapp').innerHTML = "**Invalid whatsapp number.";
	 document.getElementById('whatsapp').focus();
	 flag = false;
	 }
	 if (isempty(viber) && !isPatternMatch(integerPattern, whatsapp)) {
	 document.getElementById('errorViber').innerHTML = "**Invalid viber number.";
	 document.getElementById('viber').focus();
	 flag = false;
	 }
	if (!isEmptyValue(providerType)) {
		document.getElementById('errorProviderType').innerHTML = "**Select course provider type.";
		document.getElementById('selectedProviderType').focus();
		flag = false;
	}
	if (providerStatus === null || providerStatus === undefined) {
		document.getElementById('errorProviderStatus').innerHTML = "**Select the course provider status.";
		document.getElementById('providerStatus').focus();
		flag = false;
	}
	
	if (!isempty(providerPrivateName)
			|| !isValidLength(providerPrivateName, 100)) {
		document.getElementById('errorPrivateName').innerHTML = "**Personal name is empty or too long.";
		document.getElementById('providerPrivateName').focus();
		flag = false;
	}
	if (!isempty(providerEmail) || !isValidEmailFormat(providerEmail)) {
		document.getElementById('errorPrivateEmail').innerHTML = "**Give a private contact email of the course provider.";
		document.getElementById('providerEmail').focus();
		flag = false;
	}
	if (isPatternMatch(integerPattern, providerUsername)) {
		document.getElementById('errorUsername').innerHTML = "** Username can't contain only numbers.";
		document.getElementById('providerUsername').focus();
		flag = false;
	}
	if (!isempty(providerUsername) || !isValidLength(providerUsername, 100)) {
		document.getElementById('errorUsername').innerHTML = "** Username is empty or too long.";
		document.getElementById('providerUsername').focus();
		flag = false;
	}
	if (!isempty(providerPassword) || !isempty(cProviderPassword)) {
		document.getElementById('errorProviderPassword').innerHTML = "**Password field(s) is empty.";
		document.getElementById('providerPassword').focus();
		flag = false;
	}
	if (isempty(providerPassword) && providerPassword.length < 6) {
		document.getElementById('errorProviderPassword').innerHTML = "**Password is weak.";
		document.getElementById('providerPassword').focus();
		flag = false;
	}
	if ((isempty(providerPassword) || providerPassword.length < 6)
			&& !isempty(cProviderPassword)
			&& (providerPassword != cProviderPassword)) {
		document.getElementById('errorCProviderPassword').innerHTML = "**Confirm password does not match.";
		document.getElementById('cProviderPassword').focus();
		flag = false;
	}
	if (cProviderPassword.length > 100) {
		document.getElementById('errorProviderPassword').innerHTML = "**Password is too long.";
		document.getElementById('providerPassword').focus();
		flag = false;
	}
	if (accountStatus === null || accountStatus === undefined) {
		document.getElementById('errorStatus').innerHTML = "**Select the account status.";
		document.getElementById('accountStatus').focus();
		flag = false;
	}
	if (!isValidLength(accountDescription, 4000)) {
		document.getElementById('errorAccountDescription').innerHTML = "**Description is too long.";
		document.getElementById('accountDescription').focus();
		flag = false;
	}
	if (!isempty(accountContactNumber) || !isPatternMatch(integerPattern, accountContactNumber)) {
		document.getElementById('errorContactNumber').innerHTML = "**Invlaid or empty contact number.";
		document.getElementById('providerContactNumber').focus();
		flag = false;
	}

	return flag;
}