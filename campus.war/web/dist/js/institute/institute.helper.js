/**
 * //20161027 AS C8-inquiry-form-for-course institute.helper.js  created.
 * //20161027 AS C8-inquiry-form-for-course sendCourseInquiry() method data validation and data send as json object.
 */ 
 
 
 function sendCourseInquiry() {
	 alert("lol");
	var fullName = $("#fullname").val();
	var email = $("#email").val();
	var countryCode = $("#countryCode").val();
	var areaCode = $("#areaCode").val();
	var telephoneNumber = $("#telNum").val();
	var inquiryTitle = $("#inquiryTitle").val();
	var inquiry = $("#inquiry").val();
	
	var fullNametb = isEmptyfield(fullName);
	var emailtb = isEmptyfield(email);
	var countryCodetb = isEmptyfield(countryCode);
	var areaCodetb = isEmptyfield(areaCode);
	var telephoneNumbertb = isEmptyfield(telephoneNumber);
	var inquiryTitletb = isEmptyfield(inquiryTitle);
	var inquirytb = isEmptyfield(inquiry);

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
	
	if ((employeeIdtb == true) && (relationshiptb == true)
			&& (relationDateofbirthtb == true) && (relationNametb == true)) {
		var jsonData = {
			"fullName" : fullName,
			"email" : email,
			"countryCode" : countryCode,
			"areaCode" : areaCode,
			"telephoneNumber" : telephoneNumber,
			"inquiryTitle" : inquiryTitle,
			"inquiry" : inquiry
		};

		$.ajax({
			type : "POST",
			url : 'InstituteController',
			data : {
				jsonData : JSON.stringify(jsonData),
				task : "SCI"
			},
			dataType : "json",
			success : function(data) {
				alert(data);
				if (data == "Details added successfully.") {
					clearFamilydetails();
				}
			},
			error : function(e) {
				alert("Error " + e);
				console.log(e);
			}
		});
	}
}
 
 