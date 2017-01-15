/**
 * 20170108 JH c39-add-course-provider course-provider-validator.js created
 * 
 */

    window.prefixFlag = true;
    window.usernameFlag =true;
    
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

function providerUsernameValidation() {
	
	$("#errorUsername").html = "";

	var selectedUsername = document.getElementById('providerUsername').value;
	var userEmail = document.getElementById('providerEmail').value;

	if (!isempty(selectedUsername)) {
		document.getElementById('errorUsername').innerHTML = "**Username is empty.";
		document.getElementById('providerUsername').focus();
		return false;
	} else {
		 if(selectedUsername.length <5){//check whether the username has less than 5 characters
				document.getElementById('errorUsername').innerHTML = "**Username should have atleast 5 characters.";
				return false;
			}else{

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
							document.getElementById('errorUsername').innerHTML = response.userMessage;
							window.usernameFlag == response.validationFlag;
							alert(window.usernameFlag);
							
//							if(window.usernameFlag == true){
//								return true;
//								} else {
//									return false;
//								}
							return usernameFlag;

							}
						},
					});
		}
	}
}

function providerPrefixValidation() {
	
	var selectedPrefix = document.getElementById('uniquePrefix').value;
	document.getElementById('errorUniquePrefix').value = "";

	if (!isempty(selectedPrefix)) {
		document.getElementById('errorUniquePrefix').innerHTML = "**Give a unique name.";
		document.getElementById('uniquePrefix').focus();
		return false;
	} else if (selectedPrefix.length < 2) {
			document.getElementById('errorUniquePrefix').innerHTML = "Unique prefix is too small";
			return false;
	} else if (selectedPrefix.length > 6) {
		document.getElementById('errorUniquePrefix').innerHTML = "Unique prefix is too large";
		return false;
} else {
		document.getElementById('errorUniquePrefix').innerHTML = "";

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

							document.getElementById('errorUniquePrefix').innerHTML = response.userMessage;
							window.prefixFlag == response.validationFlag;
alert(window.prefixFlag);
					return prefixFlag;

							}
						},
					});
		}
	
}

/**
 * created to validate course provider details before submit
 */
function vaidateCourseProviderDeatils(form) {

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
	var providerStatus = $("#providerStatus").val();
	var providerPrivateName = $("#providerPrivateName").val();
	var providerEmail = $("#providerEmail").val();
	var providerUsername = $("#providerUsername").val();
	var providerPassword = $("#providerPassword").val();
	var cProviderPassword = $("#cProviderPassword").val();
	var accountStatus = $("#accountStatus").val();
	var accountDescription = $("#accountDescription").val();

	var flag = true;
	
	if (!isempty(courseProvider)) {
		document.getElementById('errorCourseProvider').innerHTML = "**Select a course provider type.";
		document.getElementById('courseProvider').focus();
		flag = false;
	}
	if (!isempty(providerName)) {
		document.getElementById('errorProviderName').innerHTML = "**Give a course provider name.";
		document.getElementById('providerName').focus();
		flag = false;
	}
	if (!isempty(uniquePrefix)) {
		document.getElementById('errorUniquePrefix').innerHTML = "**Give course provider Unique name.";
		document.getElementById('uniquePrefix').focus();
		flag = false;
	}
	if (!isempty(shortName)) {
		document.getElementById('errorShortName').innerHTML = "**Give a short name.";
		document.getElementById('shortName').focus();
		flag = false;
	}
	if (!isempty(aboutMe)) {
		document.getElementById('errorAboutMe').innerHTML = "**Give a breif description.";
		document.getElementById('aboutMe').focus();
		flag = false;
	}
	if (!isempty(specialFeatures)) {
		document.getElementById('errorSpecialFeatures').innerHTML = "**Give special features.";
		document.getElementById('specialFeatures').focus();
		flag = false;
	}
	if (!isempty(generalEmail)) {
		document.getElementById('errorGeneralEmail').innerHTML = "**General email field can't be empty.";
		document.getElementById('generalEmail').focus();
		flag = false;
	}
	if (!isValidEmailFormat(generalEmail)) {
		document.getElementById('errorGeneralEmail').innerHTML = "**Invalid email.";
		document.getElementById('generalEmail').focus();
		flag = false;
	}
	if (!isempty(inquiryMail)) {
		document.getElementById('errorInquiryMail').innerHTML = "**Give an inquiry email.";
		document.getElementById('errorInquiryMail').focus();
		flag = false;
	}
	if (!isValidEmailFormat(inquiryMail)) {
		document.getElementById('errorInquiryMail').innerHTML = "**Invalid email.";
		document.getElementById('inquiryMail').focus();
		flag = false;
	}
	if (!isempty(areaCode)) {
		document.getElementById('errorAreaCode').innerHTML = "**Give the area code.(Ex:11, 31, 81)";
		document.getElementById('areaCode').focus();
		flag = false;
	}
	if (!isempty(land1)) {
		document.getElementById('errorLand1').innerHTML = "**Land phone number can't be empty.";
		document.getElementById('land1').focus();
		flag = false;
	}
	// if (!isempty(land2)) {
	// document.getElementById('errorLand2').innerHTML = "**Give a course
	// provider Unique name.";
	// document.getElementById('errorLand2').focus();
	// flag = false;
	// }
	// if (!isempty(fax)) {
	// document.getElementById('errorPrefix').innerHTML = "**Give a course
	// provider Unique name.";
	// document.getElementById('errorPrefix').focus();
	// flag = false;
	// }
	if (!isempty(networkCode)) {
		document.getElementById('errorNetworkCode').innerHTML = "**Give the network code.(ex:77,72,71....) ";
		document.getElementById('errorNetworkCode').focus();
		flag = false;
	}
	if (!isempty(mobile)) {
		document.getElementById('errorMobile').innerHTML = "**Give your mobile phone number.";
		document.getElementById('errorMobile').focus();
		flag = false;
	}
	if (!isempty(address1)) {
		document.getElementById('errorAddress1').innerHTML = "**Give your permenant address.";
		document.getElementById('errorAddress1').focus();
		flag = false;
	}
	// if (!isempty(address2)) {
	// document.getElementById('errorPrefix').innerHTML = "**Give a course
	// provider Unique name.";
	// document.getElementById('errorPrefix').focus();
	// flag = false;
	// }
	// if (!isempty(address3)) {
	// document.getElementById('errorPrefix').innerHTML = "**Give a course
	// provider Unique name.";
	// document.getElementById('errorPrefix').focus();
	// flag = false;
	// }
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
		document.getElementById('errorMyspace').innerHTML = "**Give a valid MySpace URL.";
		document.getElementById('mySpace').focus();
		flag = false;
	}
// if (!isempty(whatsapp)) {
// document.getElementById('errorWhatsapp').innerHTML = "**Give a valid whatsapp
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
	if (!isempty(expirationDate)) {
		document.getElementById('errorExpiration').innerHTML = "**Expiration date is not valid.";
		document.getElementById('expirationDate').focus();
		flag = false;
	}
	if (!isempty(providerType)) {
		document.getElementById('errorProviderType').innerHTML = "**Select course provider type.";
		document.getElementById('selectedProviderType').focus();
		flag = false;
	}
	if (!$("#providerStatus").is(":checked")) {
		document.getElementById('errorProviderStatus').innerHTML = "**Select the course provider status.";
		document.getElementById('providerStatus').focus();
		flag = false;
	}
	if (!isempty(providerPrivateName)) {
		document.getElementById('errorPrivateName').innerHTML = "**Give a private contact name of the course provider.";
		document.getElementById('providerPrivateName').focus();
		flag = false;
	}
	if (!isempty(providerEmail)) {
		document.getElementById('errorPrivateEmail').innerHTML = "**Give a private contact email of the course provider.";
		document.getElementById('providerEmail').focus();
		flag = false;
	}
	if (!isempty(providerUsername)) {
		document.getElementById('errorProviderUsername').innerHTML = "**Give a username.";
		document.getElementById('errorUsername').focus();
		flag = false;
	}
	if (!isempty(providerPassword)) {
		document.getElementById('errorProviderPassword').innerHTML = "**Password is empty.";
		document.getElementById('providerPassword').focus();
		flag = false;
	}if (isempty(providerPassword) && providerPassword.length <6) {
		document.getElementById('errorProviderPassword').innerHTML = "**Password is weak.";
		document.getElementById('providerPassword').focus();
		flag = false;
	}
	if ((isempty(providerPassword) || providerPassword.length <6) && !isempty(cProviderPassword)) {
		document.getElementById('errorCProviderPassword').innerHTML = "**Confirm password is empty.";
		document.getElementById('cProviderPassword').focus();
		flag = false;
	}
	if((isempty(providerPassword) || providerPassword.length <6) && !isempty(cProviderPassword)
			&& (providerPassword != cProviderPassword)){
		document.getElementById('errorCProviderPassword').innerHTML = "**Confirm password does not match.";
		document.getElementById('cProviderPassword').focus();
		flag = false;
	}
//	if (!$("#accountStatus").is(":checked")) {
//		document.getElementById('errorAccountStatus').innerHTML = "**Select the account status.";
//		document.getElementById('accountStatus').focus();
//		flag = false;
//	}


	return flag;
}