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

function normalValdation(){
	var providerName = $("#providerName").val();
}

/**
 * created to validate course provider details before submit
 */
function vaidateCourseProviderDeatils(form){
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
		document.getElementById('errorAboutMe').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorAboutMe').focus();
		flag = false;
	}
	if (!isempty(specialFeatures)) {
		document.getElementById('errorSpecialFeatures').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorSpecialFeatures').focus();
		flag = false;
	}
	if (!isempty(generalEmail)) {
		document.getElementById('errorGeneralEmail').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorGeneralEmail').focus();
		flag = false;
	}
	if (!isempty(inquiryMail)) {
		document.getElementById('errorInquiryMail').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorInquiryMail').focus();
		flag = false;
	}
	if (!isempty(areaCode)) {
		document.getElementById('errorAreaCode').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorAreaCode').focus();
		flag = false;
	}
	if (!isempty(land1)) {
		document.getElementById('errorLand1').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorLand1').focus();
		flag = false;
	}
	if (!isempty(land2)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(fax)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(networkCode)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(mobile)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(address1)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(address2)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(address3)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(country)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(townList)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(webLink)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(facebook)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(linkdedIn)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(twitter)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(instagram)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(mySpace)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(whatsapp)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(viber)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(expirationDate)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(providerStatus)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(providerPrivateName)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(providerEmail)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(providerUsername)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(providerPassword)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(cProviderPassword)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
	if (!isempty(accountStatus)) {
		document.getElementById('errorPrefix').innerHTML = "**Give a course provider Unique name.";
		document.getElementById('errorPrefix').focus();
		flag = false;
	}
return flag;
}