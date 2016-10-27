/**
 * 20161027 CM c9-make inquiry for institute created campus.instituteInquiry.js class.
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
				 * if (data == "Inquiry send successfully.") {
				 *  }
				 */
			},
			error : function(e) {
				alert("Error " + e);
				console.log(e);
			}
		});
	}
}