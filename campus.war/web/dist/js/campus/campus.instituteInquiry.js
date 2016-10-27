/**
 * 20161027 CM c9-make inquiry for institute created campus.instituteInquiry.js class.
 */

/*******************************************************************************
 * To Add data
 ******************************************************************************/
// Get data and sent to InstituteController.java.
function addInstituteInquiry() {
	alert("hi0");
	var fullname = $("#fullnamee").val();
	alert("Fullname: " + fullname);
	var email = $("#email").val();
	alert("email: " + email);
	var countryCode = $("#countryCode").val();
	alert("Country Code: " + countryCode);
	var areaCode = $("#areaCode").val();
	alert("areaCode: " + areaCode);
	var telephoneNumber = $("#telNum").val();
	alert("telephone: " + telephoneNumber);
	var inquiryTitle = $("#inquiryTitle").val();
	alert("intuiryTitle : " + inquiryTitle);
	var inquiry = $("#inquiry").val();
	alert("inquiy: " + inquiry);
	var courseProvider = $("#courseProviderCode").text();
	alert("courseProvider: " + courseProvider);
	var student = $("#studentCode").text();
	alert("studentCode: " + student);
	alert(fullname + " - " + email + " - " + countryCode + " - " + areaCode
			+ " - " + telephoneNumber + " - " + inquiryTitle + " - " + inquiry
			+ " - " + courseProvider + " - " + student);

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
			|| (telephoneNumber == "") || (inquiryTitle == "")
			|| (inquiry == "")) {
		alert("Please fill the Empty fields.");

	} else {
		alert("ok");
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
				/*if (data == "Inquiry send successfully.") {
				
				}*/
			},
			error : function(e) {
				alert("Error " + e);
				console.log(e);
			}
		});
	}
}