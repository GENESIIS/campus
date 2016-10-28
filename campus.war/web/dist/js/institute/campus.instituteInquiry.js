/**
 * 20161027 CM c9-make inquiry for institute created campus.instituteInquiry.js
 * class.
 */


/*******************************************************************************
 * To Add data. Get data and sent to InstituteController.java
 ******************************************************************************/
function addInstituteInquiry() {

	var fullname = $("#fullnamee").val();
	var email = $("#email").val();
	var countryCode = $("#countryCode").val();
	var areaCode = $("#areaCode").val();
	var telephoneNumber = $("#telNum").val();
	var inquiryTitle = $("#inquiryTitle").val();
	var inquiry = $("#inquiry").val();
	var courseProvider = $("#courseProviderCode").text();
	var student = $("#studentCode").text();
	alert(fullname + " - " + email + " - " + countryCode + " - " + areaCode
			+ " - " + telephoneNumber + " - " + inquiryTitle + " - " + inquiry
			+ " - " + courseProvider + " - " + student);

/*	var fullNametb = isEmptyfield(fullName);
	var emailtb = isEmptyfield(email);
	var countryCodetb = isEmptyfield(countryCode);
	var areaCodetb = isEmptyfield(areaCode);
	var telephoneNumbertb = isEmptyfield(telephoneNumber);
	var inquiryTitletb = isEmptyfield(inquiryTitle);
	var inquirytb = isEmptyfield(inquiry);
	var emailPatternCheck = isValidEmailFormat(email);

	if (fullNametb == false) {
		document.getElementById('fullNametbError').innerHTML = "** Invalid Name.";
	}
	if (emailtb == false) {
		document.getElementById('emailtbError').innerHTML = "** Email can not be Empty.";
	}
	if (countryCodetb == false) {
		document.getElementById('countryCodetbError').innerHTML = "** Country Code can not be Empty.";
	}
	if (areaCodetb == false) {
		document.getElementById('areaCodetbError').innerHTML = "** Area Code cannot be Empty.";
	}
	if (telephoneNumbertb == false) {
		document.getElementById('telephoneNumbertbError').innerHTML = "** Telephone can not be Empty.";
	}
	if (inquiryTitletb == false) {
		document.getElementById('inquiryTitletbError').innerHTML = "** Inquiry Title can not be Empty.";
	}
	if (inquirytb == false) {
		document.getElementById('inquirytbError').innerHTML = "** inquiry cannot be Empty.";
	}
	if (emailPatternCheck == false) {
		document.getElementById('emailtbError').innerHTML = "** Invalid Email.";
	}*/


var jsonData = {

	"studentName" : fullname,
	"studentEmail" : email,
	"telephoneCountryCode" : countryCode,
	"telephoneAreaCode" : areaCode,
	"telNo" : telephoneNumber,
	"inquiryTitle" : inquiryTitle,
	"inquiryText" : inquiry,
	"courseProvider" : courseProvider,
	"student" : student,
};

if ((fullname == "") || (email == "") || (areaCode == "")
		|| (telephoneNumber == "") || (inquiryTitle == "") || (inquiry == "")) {
	alert("Please fill the Empty fields.");

} else {

	$.ajax({

		type : "POST",
		url : 'InstituteController',
		data : {
			jsonData : JSON.stringify(jsonData),
			CCO : "SII"
		},
		dataType : "json",
		success : function(data) {
			alert(data);
			/*
			 * if (data == "Inquiry send successfully.") { }
			 */
		},
		error : function(e) {
			alert("Error " + e);
			console.log(e);
		}
	});

}
}