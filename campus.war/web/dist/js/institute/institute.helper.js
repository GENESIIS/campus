/**
 * //20161027 AS C8-inquiry-form-for-course institute.helper.js created.
 * //20161027 AS C8-inquiry-form-for-course sendCourseInquiry() method data
 * validation and data send as json object. //20161103 AS
 * C8-inquiry-form-for-course sendCourseInquiry() method data var included .
 */
var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "/validation/validation.js";

function sendCourseInquiry() {
	alert("hiii");
	var fullName = $("#fullname").val();
	var email = $("#email").val();
	var countryCode = $("#countryCode").val();
	var areaCode = $("#areaCode").val();
	var telephoneNumber = $("#telNum").val();
	var inquiryTitle = $("#inquiryTitle").val();
	var inquiry = $("#inquiry").val();
	var student = $("#student").val();
	var programmeCode = $("#programmeCode").val();
	var recapture = $("#g-recaptcha-response").val();
	
	alert(fullName + email + countryCode + areaCode + telephoneNumber
			+ inquiryTitle + inquiry + student + programmeCode);
	alert("okkk" + fullName + email + countryCode + areaCode + telephoneNumber
			+ inquiry + inquiryTitle + student + programmeCode);

	var fullNametb = isempty(fullName);
	var emailtb = isValidEmailFormat(email);
	var countryCodetb = isempty(countryCode);
	var areaCodetb = isempty(areaCode);
	var telephoneNumbertb = isValidPhoneNumber(telephoneNumber);
	var inquiryTitletb = isempty(inquiryTitle);
	var inquirytb = isempty(inquiry);

	if (fullNametb == false) {
		document.getElementById('fullNametbError').innerHTML = "  ** Invalid Name.";
		return false;
	}  if (emailtb == false) {
		document.getElementById('emailtbError').innerHTML = "   ** Email can not be Empty.";
		return false;
	}  if (countryCodetb == false) {
		document.getElementById('countryCodetbError').innerHTML = "  ** Country  Code can not be Empty.";
		return false;
	}  if (areaCodetb == false) {
		document.getElementById('areaCodetbError').innerHTML = "  ** Area Code  cannot be Empty.";
		return false;
	}  if (telephoneNumbertb == false) {
		document.getElementById('telephoneNumbertbError').innerHTML = "  **  Telephone can not be Empty.";
		return false;
	}  if (inquiryTitletb == false) {
		document.getElementById('inquiryTitletbError').innerHTML = "  ** Inquiry  Title can not be Empty.";
		return false;
	}  if (inquirytb == false) {
		document.getElementById('inquirytbError').innerHTML = "  ** inquiry cannot  be Empty.";
		return false;
	}

	if ((email != null) && (inquiry != null) && (inquiryTitle != null)
			&& (fullName != null)) {

		var jsonData = {
			"studentName" : fullName,
			"studentEmail" : email,
			"telephoneCountryCode" : countryCode,
			"telephoneAreaCode" : areaCode,
			"telephone" : telephoneNumber,
			"inquiryTitle" : inquiryTitle,
			"inquiry" : inquiry,
			"student" : student,
			"programme" : programmeCode

		};
		$.ajax({

			type : "POST",
			url : '../../../../InstituteController',
			data : {
				jsonData : JSON.stringify(jsonData),
				CCO : "SCI",
				recapture: recapture
			},
			dataType : "json",
			success : function(response) {
				alert(response.result);
				document.getElementById('messsage').innerHTML = response.result;
				//if (response == "Inquiry Send successfylly") {
					resetInquiryLabels();
				//}
			},
			error : function(e) {
				 alert("Error " + e);
				console.log(e);
			}
		});
	}

}

/**
 * isFieldFilled() generate a alert if the passing in flag is false else the
 * method acts void
 * 
 * @param flag
 *            expression that evaluates to a boolean
 * @param elementName
 *            string to be append to the produced message
 */

function isFieldFilled(flag, elementName) {
	if (!flag) {
		alert(elementName + "must be filled out Correctly");
	}
}

/**
 * 
 * @param fieldValue
 *            it is the value of a document element
 * @returns true if has content else false
 */
function isempty(fieldValue) {
	// alert("hi");
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
 * 
 * @param phoneNumber
 *            it's the value of phone number field phone number should be 10
 *            character long.amd start from 0 method does not test for starting
 *            sequence of the phone number
 * @returns {Boolean}
 */
function isValidPhoneNumber(phoneNumber) {
	var phonenumberPattern = /^0\d{9}$/mg;
	return isPatternMatch(phonenumberPattern, phoneNumber);
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

function resetInquiryLabels() {

	$("#fullname").val("");
	$("#email").val("");
	$("#countryCode").val("");
	$("#areaCode").val("");
	$("#telNum").val("");
	$("#inquiryTitle").val("");
	$("#inquiry").val("");
	

}
