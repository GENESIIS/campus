/**
 * 20170108 JH c39-add-course-provider course-provider-validator.js created
 * 
 */

window.prefixFlag = true;
window.usernameFlag = true;

/**
 * @author JH
 * @param fieldValue
 *            it is the value of a document element
 * @returns true if has content else false
 */
function isempty(fieldValue) {
	return ((fieldValue.trim() == "") || (fieldValue == null)) ? false : true;
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
 * @author JH
 * @param parameter
 * @param length
 * @returns boolean true if length is valid else falses
 */
function isValidLength(parameter, length) {
	return (parameter > length) ? false : true;
}

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
		if (selectedUsername.length < 5) {// check whether the username has
											// less than 5 characters
			document.getElementById('errorUsername').innerHTML = "**Username should have atleast 5 characters.";
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
								}

								return flag;

							}
						},
					});
		}
	}
}

function providerPrefixValidation() {

	var selectedPrefix = document.getElementById('uniquePrefix').value;
	document.getElementById('errorUniquePrefix').value = "";
	document.getElementById('prefixMessage').value = "";
	var flag = false;

	if (!isempty(selectedPrefix)) {

		document.getElementById('prefixMessage').innerHTML = "";
		document.getElementById('errorUniquePrefix').innerHTML = "**Give a unique name.";
		document.getElementById('uniquePrefix').focus();
		return false;
	} else if (selectedPrefix.length < 2) {

		document.getElementById('prefixMessage').value = "";
		document.getElementById('errorUniquePrefix').innerHTML = "Unique prefix is too small";
		return false;
	} else if (selectedPrefix.length > 20) {

		document.getElementById('prefixMessage').innerHTML = "";
		document.getElementById('errorUniquePrefix').innerHTML = "Unique prefix is too large";
		document.getElementById('prefixMessage').value = "";
		return false;
	} else {
		document.getElementById('errorUniquePrefix').innerHTML = "";
		document.getElementById('prefixMessage').innerHTML = "";

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
	var selectedDate = new Date(expirationDate);

	if (publishProgram === 0) {
		if (currentDate > selectedDate) {
			document.getElementById('errorExpiration').innerHTML = "**Invlid date (Date should be greater than today's date.";
			document.getElementById('expirationDate').focus();
			flag = false;
		}
		if (expirationDate === "" || expirationDate === null) {
			document.getElementById('errorExpiration').innerHTML = "**Select the expiration date.";
			document.getElementById('expirationDate').focus();
			flag = false;
		}
	}

	if (!isempty(courseProvider)) {
		document.getElementById('errorCourseProvider').innerHTML = "**Select a course provider type.";
		document.getElementById('courseProvider').focus();
		flag = false;
	}
	if (!isempty(providerName) || !isValidLength(providerName, 200)) {
		document.getElementById('errorProviderName').innerHTML = "**Provider name is empty or too long.";
		document.getElementById('providerName').focus();
		flag = false;
	}
	if (!isempty(uniquePrefix) || !isValidLength(uniquePrefix, 20)) {
		document.getElementById('errorUniquePrefix').innerHTML = "**Empty or too long unique name.";
		document.getElementById('uniquePrefix').focus();
		flag = false;
	}
	if (!isValidLength(shortName, 20)) {
		document.getElementById('errorShortName').innerHTML = "**Short name is too long.";
		document.getElementById('shortName').focus();
		flag = false;
	}
	if (!isempty(aboutMe)) {
		document.getElementById('errorAboutMe').innerHTML = "**Give a breif description.";
		document.getElementById('aboutMe').focus();
		flag = false;
	}
	if (isempty(specialFeatures) && !isValidLength(specialFeatures, 100)) {
		document.getElementById('errorSpecialFeatures').innerHTML = "**Only 100 characters allowed.";
		document.getElementById('specialFeatures').focus();
		flag = false;
	}
	if (!isempty(generalEmail) || !isValidLength(generalEmail, 255)) {
		document.getElementById('errorGeneralEmail').innerHTML = "**General email field is empty or too long.";
		document.getElementById('generalEmail').focus();
		flag = false;
	}
	if (!isValidEmailFormat(generalEmail)) {
		document.getElementById('errorGeneralEmail').innerHTML = "**Invalid email.";
		document.getElementById('generalEmail').focus();
		flag = false;
	}
	if (!isempty(inquiryMail) || !isValidLength(inquiryMail, 255)) {
		document.getElementById('errorInquiryMail').innerHTML = "**Empty or too long inquiry mail.";
		document.getElementById('errorInquiryMail').focus();
		flag = false;
	}
	if (!isValidEmailFormat(inquiryMail)) {
		document.getElementById('errorInquiryMail').innerHTML = "**Invalid email.";
		document.getElementById('inquiryMail').focus();
		flag = false;
	}
	if (!isempty(land1)) {
		document.getElementById('errorLand1').innerHTML = "**Land phone number can't be empty.";
		document.getElementById('land1').focus();
		flag = false;
	}
	if (!isPatternMatch(integerPattern, land1)) {
		document.getElementById('errorLand1').innerHTML = "**Invalid Land phone number.";
		document.getElementById('land1').focus();
		flag = false;
	}
	if (isempty(fax) && !isPatternMatch(integerPattern, fax)) {
		document.getElementById('errorFax').innerHTML = "**Fax number is not valid.";
		document.getElementById('fax').focus();
		flag = false;
	}
	if (isempty(land2) && !isPatternMatch(integerPattern, land2)) {
		document.getElementById('errorLand2').innerHTML = "**Land phone number 2 is not valid.";
		document.getElementById('land2').focus();
		flag = false;
	}
	if (isempty(areaCode) && !isPatternMatch(integerPattern, areaCode)) {
		document.getElementById('errorAreaCode').innerHTML = "**Area code is invalid. Only numbers allowed.(Ex:11, 31, 81)";
		document.getElementById('areaCode').focus();
		document.getElementById('errorLand1').innerHTML = "**Area code is invalid.";
		flag = false;
	}
	if (!isempty(areaCode)) {
		document.getElementById('errorAreaCode').innerHTML = "**Area code is empty.(Ex:11, 31, 81)";
		document.getElementById('areaCode').focus();
		flag = false;
	}
	if (!isempty(networkCode) || !isPatternMatch(integerPattern, networkCode)) {
		document.getElementById('errorNetworkCode').innerHTML = "**Give a valid network code.(ex:77,72,71....) ";
		document.getElementById('errorNetworkCode').focus();
		flag = false;
	}
	if (!isempty(mobile) || !isPatternMatch(integerPattern, mobile)) {
		document.getElementById('errorMobile').innerHTML = "**Give a valid mobile phone number.";
		document.getElementById('errorNetworkCode').innerHTML = "**Network code is not valid. ";
		document.getElementById('errorMobile').focus();
		flag = false;
	}
	if (!isempty(address1)) {
		document.getElementById('errorAddress1').innerHTML = "**Give your permenant address.";
		document.getElementById('errorAddress1').focus();
		flag = false;
	}
	if (!isempty(country)) {
		document.getElementById('errorSelectedCountry').innerHTML = "**Select your country.";
		document.getElementById('selectedCountry').focus();
		flag = false;
	}
	if (!isempty(townList) && !isempty(country)) {
		document.getElementById('errorSelectedTown').innerHTML = "**First select your country.";
		document.getElementById('errorSelectedTown').focus();
		flag = false;
	}
	if (!isempty(townList) && isempty(country)) {
		document.getElementById('errorSelectedTown').innerHTML = "**Select your town";
		document.getElementById('errorSelectedTown').focus();
		flag = false;
	}
	if (isempty(webLink) && !ValidURL(webLink)) {
		document.getElementById('errorWebLink').innerHTML = "**Give a valid web link URL.";
		document.getElementById('webLink').focus();
		flag = false;
	}
	if (isempty(facebook) && !ValidURL(facebook)) {
		document.getElementById('errorFacebook').innerHTML = "**Give a valid facebook URL.";
		document.getElementById('facebook').focus();
		flag = false;
	}
	if (isempty(linkdedIn) && !ValidURL(linkdedIn)) {
		document.getElementById('errorLinkedIn').innerHTML = "**Give a valid LinkedIn URL.";
		document.getElementById('linkdedIn').focus();
		flag = false;
	}
	if (isempty(twitter) && !ValidURL(twitter)) {
		document.getElementById('errorTwitter').innerHTML = "**Give a valid Twitter URL.";
		document.getElementById('twitter').focus();
		flag = false;
	}
	if (isempty(instagram) && !ValidURL(instagram)) {
		document.getElementById('errorInstagram').innerHTML = "**Give a valid Instagram URL.";
		document.getElementById('instagram').focus();
		flag = false;
	}
	if (isempty(mySpace) && !ValidURL(mySpace)) {
		document.getElementById('errorInstagram').innerHTML = "**Select the account status.";
		document.getElementById('instagram').focus();
		flag = false;
	}
	// if (!isempty(whatsapp)) {
	// document.getElementById('errorWhatsapp').innerHTML = "**Give a valid
	// whatsapp
	// number.";
	// document.getElementById('whatsapp').focus();
	// flag = false;
	// }
	// if (!isempty(viber)) {
	// document.getElementById('errorViber').innerHTML = "**Give a valid viber
	// number.";
	// document.getElementById('viber').focus();
	// flag = false;
	// }

	if (!isempty(providerType)) {
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

	return flag;
}