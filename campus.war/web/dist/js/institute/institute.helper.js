/**
 * //20161027 AS C8-inquiry-form-for-course institute.helper.js created.
 * //20161027 AS C8-inquiry-form-for-course sendCourseInquiry() method data
 * validation and data send as json object. //20161103 AS
 * C8-inquiry-form-for-course sendCourseInquiry() method data var included .
 */
var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "../../../../dist/js/institute/validation/validation.js";

function sendCourseInquiry() {
	
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
	
	
	var fullNametb = isempty(fullName);
	var emailtb = isValidEmailFormat(email);
	var emailtb1 = isempty(email);
//	var countryCodetb = isempty(countryCode);
//	var areaCodetb = isempty(areaCode);
//	var telephoneNumbertb = isValidPhoneNumber(telephoneNumber);
	var inquiryTitletb = isempty(inquiryTitle);
	var inquirytb = isempty(inquiry);
	
	var flag =true;
	
	if (fullNametb == false) {
		document.getElementById('fullNametbError').innerHTML = "  ** Invalid Name.";
		//flag = false;
		return !flag;
	}else  if (emailtb == false) {
		document.getElementById('emailtbError').innerHTML = "   **Please enter valid Email Address.";
		//flag = false;
		return !flag;
	}else if(emailtb1 == false){
		document.getElementById('emailtbError').innerHTML = "   ** Email Address  cannot be Empty.";
		flag = false;
//	}  if (countryCodetb == false) {
//		document.getElementById('countryCodetbError').innerHTML = "  ** Country  Code can not be Empty.";
//		flag = false;
//	}  if (areaCodetb == false) {
//		document.getElementById('areaCodetbError').innerHTML = "  ** Area Code  cannot be Empty.";
//		flag = false;
//	}  if (telephoneNumbertb == false) {
//		document.getElementById('telephoneNumbertbError').innerHTML = "  **  Telephone can not be Empty.";
//		flag = false;
	} else if (inquiryTitletb == false) {
		document.getElementById('inquiryTitletbError').innerHTML = "  ** Inquiry  Title can not be Empty.";
		//flag = false;
		return !flag;
	} else if (inquirytb == false) {
		document.getElementById('inquirytbError').innerHTML = "  ** inquiry cannot  be Empty.";
		//flag = false;
		return !flag;
	} else if(recapture.length == 0){
		document.getElementById('captchaError').innerHTML = "**Please verify You're not a robot.";
		//flag = false;
		return !flag;
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
				
				document.getElementById('messsage').innerHTML = response.result;
				
					resetInquiryLabels();
				
			},
			error : function(e) {
				 alert("Error " + e);
				console.log(e);
			}
		});
	}

}



function resetInquiryLabels() {

	$("#fullname").val("");
	$("#email").val("");
//	$("#countryCode").val("");
//	$("#areaCode").val("");
//	$("#telNum").val("");
	$("#inquiryTitle").val("");
	$("#inquiry").val("");
	
	$("#fullNametbError").text("");
	$("#emailtbError").text("");
//	$("#countryCodetbError").text("");
//	$("#areaCodetbError").text("");
//	$("#telephoneNumbertbError").text("");
	$("#inquiryTitletbError").text("");
	$("#inquirytbError").text("");

}
