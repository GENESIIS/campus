/**
 * 20170108 JH c39-add-course-provider course-provider-validator.js created
 * 
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

function normalValdation() {
	var providerName = $("#providerName").val();
}

function providerUsernameValidation() {

	var selectedUsername = document.getElementById('providerUsername').value;
	var userEmail = document.getElementById('providerEmail').value;

	if (!isempty(selectedUsername)) {
		document.getElementById('errorUsername').innerHTML = "**Username is empty.";
		if(selectedUsername.length <6){
			document.getElementById('errorUsername').innerHTML = "**Username should have atleast 6 characters.";
		}
		document.getElementById('providerUsername').focus();
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
							document.getElementById('errorUsername').innerHTML = response.userMessage;
						}
					},
				});

	}
}

function providerPrefixValidation() {

	var selectedPrefix = document.getElementById('uniquePrefix').value;

	if (!isempty(selectedPrefix)) {
		document.getElementById('errorUniquePrefix').innerHTML = "**Give a unique name.";
		document.getElementById('uniquePrefix').focus();
	} else if (isempty(selectedPrefix)) {
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
							window.responseErrorPrefix = response.userMessage;

							document.getElementById('errorUniquePrefix').innerHTML = response.userMessage;
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
	var country = $("#country-List").val();
	var townList = $("#town-List").val();
	var webLink = $("#webLink").val();
	var facebook = $("#facebook").val();
	var linkdedIn = $("#linkdedIn").val();
	var twitter = $("#twitter").val();
	var instagram = $("#instagram").val();
	var mySpace = $("#mySpace").val();
	var whatsapp = $("#whatsapp").val();
	var viber = $("#viber").val();
	var expirationDate = $("#expirationDate").val();
	var providerType = $("#providerTypeList").val();
	var providerStatus = $("#providerStatus").val();
	var providerPrivateName = $("#providerPrivateName").val();
	var providerEmail = $("#providerEmail").val();
	var providerUsername = $("#providerUsername").val();
	var providerPassword = $("#providerPassword").val();
	var cProviderPassword = $("#cProviderPassword").val();
	var accountStatus = $("#accountStatus").val();
	var accountDescription = $("#accountDescription").val();

	var validateFlag = true;

	if (!isempty(courseProvider)) {
		document.getElementById('errorCourseProvider').innerHTML = "**Select a course provider type.";
		document.getElementById('errorCourseProvider').focus();
		flag = false;
	}
	if (!isempty(providerName)) {
		document.getElementById('errorProviderName').innerHTML = "**Give a course provider name.";
		document.getElementById('errorProviderName').focus();
		flag = false;
	}
	if (!isempty(uniquePrefix)) {
		document.getElementById('errorUniquePrefix').innerHTML = "**Give course provider Unique name.";
		document.getElementById('errorUniquePrefix').focus();
		flag = false;
	}
	if (!isempty(shortName)) {
		document.getElementById('errorShortName').innerHTML = "**Give a short name.";
		document.getElementById('errorShortName').focus();
		flag = false;
	}
	if (!isempty(aboutMe)) {
		document.getElementById('errorAboutMe').innerHTML = "**Give a breif description.";
		document.getElementById('errorAboutMe').focus();
		flag = false;
	}
	if (!isempty(specialFeatures)) {
		document.getElementById('errorSpecialFeatures').innerHTML = "**Give special features.";
		document.getElementById('errorSpecialFeatures').focus();
		flag = false;
	}
	if (!isempty(generalEmail)) {
		document.getElementById('errorGeneralEmail').innerHTML = "**General email field can't be empty.";
		document.getElementById('errorGeneralEmail').focus();
		flag = false;
	}
	if (!isempty(inquiryMail)) {
		document.getElementById('errorInquiryMail').innerHTML = "**Give an inquiry email.";
		document.getElementById('errorInquiryMail').focus();
		flag = false;
	}
	if (!isempty(areaCode)) {
		document.getElementById('errorAreaCode').innerHTML = "**Give an area code";
		document.getElementById('errorAreaCode').focus();
		flag = false;
	}
	if (!isempty(land1)) {
		document.getElementById('errorLand1').innerHTML = "**Land phone number can't be empty.";
		document.getElementById('errorLand1').focus();
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
		document.getElementById('errorNetworkCode').innerHTML = "**Give the network. ";
		document.getElementById('errorNetworkCode').focus();
		flag = false;
	}
	if (!isempty(mobile)) {
		document.getElementById('errorMobile').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorMobile').focus();
		flag = false;
	}
	if (!isempty(address1)) {
		document.getElementById('errorAddress1').innerHTML = "**Give a course provider Unique name.";
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
		document.getElementById('errorSelectedCountry').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorSelectedCountry').focus();
		flag = false;
	}
	if (!isempty(townList)) {
		document.getElementById('errorSelectedTown').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorSelectedTown').focus();
		flag = false;
	}
	if (!ValidURL(webLink)) {
		document.getElementById('errorWebLink').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorWebLink').focus();
		flag = false;
	}
	if (!ValidURL(facebook)) {
		document.getElementById('errorFacebook').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorFacebook').focus();
		flag = false;
	}
	if (!ValidURL(linkdedIn)) {
		document.getElementById('errorLinkedIn').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorLinkedIn').focus();
		flag = false;
	}
	if (!ValidURL(twitter)) {
		document.getElementById('errorTwitter').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorTwitter').focus();
		flag = false;
	}
	if (!ValidURL(instagram)) {
		document.getElementById('errorInstagram').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorInstagram').focus();
		flag = false;
	}
	if (!ValidURL(mySpace)) {
		document.getElementById('errorMyspace').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorMyspace').focus();
		flag = false;
	}
	if (!isempty(whatsapp)) {
		document.getElementById('errorWhatsapp').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorWhatsapp').focus();
		flag = false;
	}
	if (!isempty(viber)) {
		document.getElementById('errorViber').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorViber').focus();
		flag = false;
	}
	if (!isempty(expirationDate)) {
		document.getElementById('errorExpiration').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorExpiration').focus();
		flag = false;
	}
	if (!isempty(providerStatus)) {
		document.getElementById('errorProviderStatus').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorProviderStatus').focus();
		flag = false;
	}
	if (!isempty(providerPrivateName)) {
		document.getElementById('errorPrivateName').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrivateName').focus();
		flag = false;
	}
	if (!isempty(providerEmail)) {
		document.getElementById('errorProviderEmail').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorProviderEmail').focus();
		flag = false;
	}
	if (!isempty(providerUsername)) {
		document.getElementById('errorProviderUsername').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorProviderUsername').focus();
		flag = false;
	}
	if (!isempty(providerPassword)) {
		document.getElementById('errorProviderPassword').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorProviderPassword').focus();
		flag = false;
	}
	if (!isempty(cProviderPassword)) {
		document.getElementById('errorCProviderPassword').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorCProviderPassword').focus();
		flag = false;
	}
	if (!isempty(accountStatus)) {
		document.getElementById('errorAccountStatus').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorAccountStatus').focus();
		flag = false;
	}
	return flag;
}