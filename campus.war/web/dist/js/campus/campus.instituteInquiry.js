/**
 * 20161027 CM c9-make inquiry for institute created campus.instituteInquiry.js class.
 */

/*******************************************************************************
 * To Add data 
 ******************************************************************************/
// Get data and sent to InstituteController.java.
function addInstituteInquiry() {
	var fullname = $("#fullname").val();
	var email = $("#email").val();
	var areaCode = $("#areaCode").val();
	var telephoneNumber = $("#telNum").val();
	var inquiryTitle = $("#inquiryTitle").val();
	var inquiry= $("#inquiry").val();
	var instituteCode = $("#instituteCode").val();
	
	
	var jsonData = {
			
		"fullname" : fullname,
		"email" : email,
		"areaCode" : areaCode,
		"telephoneNumber" : telephoneNumber,
		"inquiryTitle" :inquiryTitle,
		"inquiry" :inquiry,
		"instituteCode" :instituteCode
	};
	if ((fullname == "") || (email == "")|| (areaCode == "") || (telephoneNumber=="") || (inquiryTitle=="") || (inquiry=="")) {
		alert("Please fill the Empty fields.");
		
	} else {
		
		$.ajax({
			
			type : "POST",
			url : 'InstituteController',
			data : {
				jsonData : JSON.stringify(jsonData),
				task : "ALT"
			},
			dataType : "json",
			success : function(data) {
				alert(data);
				if (data == "Inquiry send successfully.") {
					clearLeaveTypeform();
				}
			},
			error : function(e) {
				alert("Error " + e);
				console.log(e);
			}
		});
	}
}