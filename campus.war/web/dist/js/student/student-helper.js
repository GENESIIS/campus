//20161125 PN this file contains all the functions to handle the student
// details. implemented getAjaxData(response), displayDetails() methods.
// implemented addEducationDetails(), clearSchoolEducationForm() methods.
//20161126 PN c26-add-student-details: implemented validateForm() and modified code in addEducationDetails() method.
//20161128 PN c26-add-student-details: implemented addProfessionalExpForm() method, validateProfessionalExpForm() method and clearProfessionalExpForm() method.
// 			PN c26-add-student-details: professional details form dropdown details populate using db values.
//			PN c26-add-student-details: addProfessionalExpForm() implementation completed.
//20161129 PN c26-add-student-details: addProfessionalExpForm() method modified.
//20161204 PN c26-add-student-details: set countryCode according to the selected country, as prefixed into the phone number.
//20161205 PN c26-add-student-details: validateStudentPersonalDetails() implementation completed.
//          PN c26-add-student-details: validateStudentPersonalDetails(), addStudentPersonalDetails() and clearPersonalDetailsForm() methods modified.
//20161206 PN CAM-26 add-student-details: This JavaScript is to populate tagitUI with DB values.
//		   PN CAM-26 add-student-details: implemented addSkillDetails(),addInterestsDetails() and createObject() methods.
//20161214 PN CAM-28: getStudentData(response) method modified to load student personal details on load.
//20161214 PN CAM-28: completed front UI population with db values, Student Higher education details form.
//20161215 PN CAM-28: implemented addHigherEducationDetails() method and validateHigherEducationForm() done.
//20161215 PN CAM-28: implemented clearSchoolEducationForm() method.
//20161216 PN CAM-28: implementing datatables using DB values. -WIP

var extStudentSkills = [];
var extStudentInterests = [];

$(document).ready(function() {
	displayDetails();
});

function displayDetails() {
	$.ajax({
		url : '../../StudentController',
		data : {
			CCO : 'GSD'
		},
		dataType : "json",
		success : function(response) {
			//alert(response);
			getStudentData(response);
		},
		error : function(response) {
			alert("Error: "+response);
		}
	});
}

/**
 * This method populate data into UI elements on page loading.
 * @param response
 * @returns
 */
function getStudentData(response) {
	var t = $('#example').DataTable(); 
	t.clear().draw();
	$.each(response.stdExpCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		counter++;
		t.row.add( [
		            data[1].toString(),
		            data[2].toString(),
					data[3].toString()
		        ] ).draw( false );
	});
	
	$.each(response.studentCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		//alert(data);
		$('#sFullName').val(data[4]);
		$('#sMiddleName').val(data[5]);
		$('#sLastName').val(data[6]);
		$('#sBirthDate').val(data[7]);
		$('input[gender]:checked').val();
		$('#sEmail').val(data[9]);
		$('#sCountryCode').val();
		$('#sHomeNumber').val(data[13]);
		$('#sMobileNumber').val(data[16]);
		$('#sAddress').val();
		$('#sFacebookUrl').val(data[18]);
		$('#stwitterUrl').val(data[19]);
		$('#smySpace').val(data[20]);
		$('#sLinkedInUrl').val(data[21]);
		$('#sInstergramUrl').val(data[22]);
		$('#sViber').val(data[23]);	
		$('#sWhatsApp').val(data[24]);
		$('#sAboutMe').val(data[17]);
		$('#sTownCode').val(data[28]);	
	});
	
	// Set Scheme details
	var sseStream = $("#sseStream");
	sseStream.find('option').remove();
	$('<option>').val("").text("--Select One--").appendTo(sseStream);
	$.each(response.majorCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(sseStream);
	});
	
	// Set area of study details
	var areaofstudy = $("#areaofstudy");
	areaofstudy.find('option').remove();
	$('<option>').val("").text("--Select One--").appendTo(areaofstudy);
	$.each(response.majorCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(areaofstudy);
	});
	
	// Set Qualification details
 	var sseQualification = $("#sseQualification");
 	sseQualification.find('option').remove();
 	$('<option>').val("").text("--Select One--").appendTo(sseQualification);
	$.each(response.schoolGradeCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(sseQualification);
	});
	
	// Set award details
 	var award = $("#award");
 	award.find('option').remove();
 	$('<option>').val("").text("--Select One--").appendTo(award);
	$.each(response.schoolGradeCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(award);
	});
	
	// Set heCountryList details
 	var heCountryList = $("#heCountryList");
 	heCountryList.find('option').remove();
	$.each(response.country2Collection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(y).text(x).appendTo(heCountryList);
	});
	
	// Set Country details
 	var sseCountry = $("#sseCountryList");
 	sseCountry.find('option').remove();
	$.each(response.country2Collection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(y).text(x).appendTo(sseCountry);
	});
	
	// Set medium details
	//alert("response.mediumCollection"+response.mediumCollection);
 	var sseMedium = $("#sseMedium");
 	sseMedium.find('option').remove();
 	$('<option>').val("").text("--Select One--").appendTo(sseMedium);
	$.each(response.mediumCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(sseMedium);
	});
	
	// Set heMedium details
 	var heMedium = $("#heMedium");
 	heMedium.find('option').remove();
 	$('<option>').val("").text("--Select One--").appendTo(heMedium);
	$.each(response.mediumCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(heMedium);
	});
	
	// Set Industry of the Organization
 	var industryoftheOrganization = $("#industryoftheOrganization");
 	industryoftheOrganization.find('option').remove();
 	$('<option>').val("").text("--Select One--").appendTo(industryoftheOrganization);
	$.each(response.majorCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(industryoftheOrganization);
	});
	
	// Set Job Category
 	var jobCategory = $("#jobCategory");
 	jobCategory.find('option').remove();
 	$('<option>').val("").text("--Select One--").appendTo(jobCategory);
	$.each(response.majorCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(jobCategory);
	});
	
	// Set Job Category
 	var award = $("#award");
 	award.find('option').remove();
 	$('<option>').val("").text("--Select One--").appendTo(award);
	$.each(response.awardCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(award);
	});
	
	if(response.result) {
        $.each(response.result, function(index, value) {
        	var res = value.toString();
    		var data = res.split(",");
        	$('#sseQualification').val(data[2]);
        	$('#sseStream').val(data[3]);
        	$('#sseResult').val(data[5]);
        	$('#sseMedium').val(data[10]);
        	$('#sseIndexNo').val(data[6]);
        	$('#sseSchool').val(data[7]);
        	$('#sseAchievedon').val(data[8]);
        	$('#sseCountry').val(data[4]);
        	$('#sseDescription').val(data[9]);       	
        });
    }
	
	// Set Qualification details
 	var sCountry = $("#sCountry");
 	sCountry.find('option').remove();
 	$('<option>').val("").text("--Select One--").appendTo(sCountry);
	$.each(response.country2Collection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(sCountry);
	});
	
	// Set Country details
 	var sCountryList = $("#sCountryList");
 	sCountryList.find('option').remove();
	$.each(response.country2Collection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('#sCountryList').append("<option data-value='" + x + "'>"+y+"</option>");
	});
	
	//Get existing skill values.
	//alert(response.skillCollection);
	var inputValues = createJsonObj(response.skillCollection);
	//alert(inputValues);
	$('#studentSkills').tagsinput({
		  itemValue: 'value',
		  itemText: 'text',
		  typeahead: {
		    source: function(query) {
		      return $.parseJSON(inputValues);
		    }
		  }
	});
	
	//Get skills assigned with Student
	//alert(response.stskillCollection);
	$.each(response.stskillCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		extStudentSkills.push(parseInt(data[0]));
		$('#studentSkills').tagsinput('add', { "value": parseInt(data[0]) , "text": data[1] , "continent": "A" });
	});
	
	//Get existing interest values.
	//alert(response.interestCollection);
	var inputValues2 = createJsonObj(response.interestCollection);
	//alert(inputValues2);
	$('#studentInterests').tagsinput({
		  itemValue: 'value',
		  itemText: 'text',
		  typeahead: {
		    source: function(query) {
		      return $.parseJSON(inputValues2);
		    }
		  }
	});
	
	//Get interest assigned with Student
	//alert(response.stinterestCollection);
	$.each(response.stinterestCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		extStudentInterests.push(parseInt(data[0]));
		$('#studentInterests').tagsinput('add', { "value": parseInt(data[0]) , "text": data[1] , "continent": "A" });
	});	
	
	//Set country code prefixed to the phone numbers.
	$("#sCountry").on('input', function () {
	    var val = this.value;
        var dValue = $('#sCountryList option').filter(function() {
            return this.value === val;
        }).data('value');
        	var msg = dValue;
        	if(msg){
        		$("span[class='input-group-addon']").text("+("+msg+")");
        		$('#sCountryCode').val(msg);
        		getTownDetails(msg);
        	}
	});
	
	//Set town code to pass into servlet.
	$("#sTown").on('input', function () {
	    var val = this.value;
        var dValue = $('#sTownList option').filter(function() {
            return this.value === val;
        }).data('value');
        	var msg = dValue;
        	if(msg){
        		$('#sTownCode').val(msg);
        	}
	});
	
	//Disable typing '0' at the beginning of the phone number text box. Already prefixed with country code. 
	$("input[class='phoneNum']").on('input propertychange paste', function (e) {
	    var reg = /^0+/gi;
			if (this.value.match(reg)) {
				this.value = this.value.replace(reg, '');
			}
	});
}

/**
 * This method generates a Json object to pass into tag-it input.
 * @param response
 * @returns
 */
function createJsonObj(response){
	var inputValues = "";
	var startBrc = "[";
	var endBrc = "]";
	var elements = "";
	
	$.each(response, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var result = '{ "value": '+parseInt(data[0])+ ', "text": "' +data[1]+ '" , "continent": "A" },';
		elements = elements.concat(result);
	});
	startBrc = startBrc.concat(elements.slice(0, -1));
	endBrc = startBrc.concat(endBrc);
	inputValues = endBrc;
	return inputValues;
}



/**
 * Get town details according to the given country code
 * @param country
 * @returns
 */
function getTownDetails(country){	
	//var country = getSelectedData('sCountry','sCountryList');	
	$.ajax({
		url : '../../StudentController',
		data : {
			country : country,
			CCO : 'GTD'
		},
		dataType : "json",
		success : function(response) {
			// Set Country details
			$("#sTown").val("");
		 	var sTownList = $("#sTownList");
		 	sTownList.find('option').remove();
			$.each(response.result, function(index, value) {
				var res = value.toString();
				var data = res.split(",");
				var x = data[0].toString();
				var y = data[1].toString();
				$('#sTownList').append("<option data-value='" + x + "'>"+y+"</option>");
			});
		},
		error : function(response) {
			alert("Error: "+response);
		}
	});
}

/**
 * Get data and sent to CmdAddHigherEducationData.java.
 * @returns
 */
function addHigherEducationDetails() {
	if(validateHigherEducationForm()){
	var instituteofStudy = $('#instituteofStudy').val();
	var affiliatedInstitute = $('#affiliatedInstitute').val();
	var areaofstudy = $('#areaofstudy').val();
	var award = $('#award').val();
	var studentId = $('#studentId').val();
	var gpa = $('#gpa').val();
	var heCommencedOn = $('#heCommencedOn').val();
	var heCompletedOn = $('#heCompletedOn').val();
	var heMedium = $('#heMedium').val();
	var country = getSelectedData('heCountry','heCountryList');
	var heDescription = $('#heDescription').val();

	var jsonData = {
		"institute" : instituteofStudy,
		"affiliatedInstitute" : affiliatedInstitute,
		"major" : areaofstudy,
		"award" : award,
		"studentId" : studentId,
		"result" : gpa,
		"commencedOn" : heCommencedOn,
		"CompletedOn" : heCompletedOn,
		"medium" : heMedium,
		"country" : country,
		"description" : heDescription
	};

	$.ajax({
		type : "POST",
		url : '../../StudentController',
		data : {
			jsonData : JSON.stringify(jsonData),
			CCO : "AHE"
		},
		dataType : "json",
		success : function(data) {			
			if(data.saveChangesHigherEduStatus){	
					if(data.saveChangesHigherEduStatus === "Unsuccessful."){
						$("#saveChangesHigherEduStatus").addClass("alert alert-danger").text(data.saveChangesHigherEduStatus).show();
					}else if(data.saveChangesHigherEduStatus === "Invalid Information"){
						$("#saveChangesHigherEduStatus").addClass("alert alert-danger").text("Invalid Information.").show();
					}
				clearSchoolEducationForm();	
				$("#saveChangesHigherEduStatus").addClass("alert alert-success").text(data.saveChangesHigherEduStatus).show();
			}
		},
		error : function(e) {
			alert("Error " + e);
			$("#saveChangesHigherEduStatus").addClass("alert alert-warning").text(e).show();
		}
	});
	}
}

/**
 * Validate higher education details form.
 * @returns
 */
function validateHigherEducationForm(){
	isDropdownSelected(isemptyDropdown(("instituteofStudy")),"Institute of Study","instituteofStudyError");
	isDropdownSelected(isemptyDropdown(("studentId")),"Student ID","studentIdError");
	isDropdownSelected(isemptyDropdown(("heCommencedOn")),"Commenced on date","heCommencedOnError");
	isDropdownSelected(isemptyDropdown(("heCompletedOn")),"Completion on date","heCompletedOnError");
	
	isDropdownSelected(isemptyDropdown('areaofstudy'),"Area of study","areaofstudyError");
	isDropdownSelected(isemptyDropdown('award'),"Award","awardError");

	if(($('#instituteofStudyError').text() != '')||($('#studentIdError').text() != '')||($('#heCommencedOnError').text() != '')||
	($('#heCompletedOnError').text() != '')||($('#areaofstudyError').text() != '')||($('#awardError').text() != '')){
		return false;
	}else{
		return true;
	}
}


function clearSchoolEducationForm(){
	$('#instituteofStudy').val("");
	$('#affiliatedInstitute').val("");
	$('#areaofstudy').val("");
	$('#award').val("");
	$('#studentId').val("");
	$('#gpa').val("");
	$('#heCommencedOn').val("");
	$('#heCompletedOn').val("");
	$('#heMedium').val("");
	$('#heCountry').val("");
	$('#heDescription').val("");
}


/**
 * Get data and sent to CmdAddSchoolEducationData.java.
 * @returns
 */
function addEducationDetails() {
	if(validateForm()){
	var schoolGrade = $('#sseQualification').val();
	var major = $('#sseStream').val();
	var result = $('#sseResult').val();
	var medium = $('#sseMedium').val();
	var indexNo = $('#sseIndexNo').val();
	var schoolName = $('#sseSchool').val();
	var achievedOn = $('#sseAchievedon').val();
	var country = getSelectedData('sseCountry','sseCountryList');
	var description = $('#sseDescription').val();

	var jsonData = {
		"schoolGrade" : schoolGrade,
		"major" : major,
		"result" : result,
		"medium" : medium,
		"indexNo" : indexNo,
		"schoolName" : schoolName,
		"achievedOn" : achievedOn,
		"country" : country,
		"description" : description
	};

	$.ajax({
		type : "POST",
		url : '../../StudentController',
		data : {
			jsonData : JSON.stringify(jsonData),
			CCO : "ASD"
		},
		dataType : "json",
		success : function(data) {			
			if(data.saveChangesStatus){	
					if(data.saveChangesStatus === "Unsuccessful."){
						$("#saveChangesStatus").addClass("alert alert-danger").text(data.saveChangesStatus).show();
					}else if(data.saveChangesStatus === "Invalid Information"){
						$("#saveChangesStatus").addClass("alert alert-danger").text("Invalid Information.").show();
					}
				clearSchoolEducationForm();	
				$("#saveChangesStatus").addClass("alert alert-success").text(data.saveChangesStatus).show();
			}
		},
		error : function(e) {
			alert("Error " + e);
			$("#saveChangesStatus").addClass("alert alert-warning").text(e).show();
		}
	});
	}
}

/**
 * This is to clear form filled data
 * @returns void
 */
function clearSchoolEducationForm() {
	$('#sseQualification').get(0).selectedIndex = 0;
	$('#sseStream').get(0).selectedIndex = 0;
	$('#sseResult').get(0).selectedIndex = 0;
	$('#sseMedium').get(0).selectedIndex = 0;
	$('#sseIndexNo').val("");
	$('#sseSchool').val("");
	$('#sseAchievedon').val("");
	$('#sseCountry').val("");
	$('#sseDescription').val("");
	$("#saveChangesStatus").hide();
}

/**
 * Validate School education details form.
 * @returns
 */
function validateForm(){
	isDropdownSelected(isemptyDropdown(("sseIndexNo")),"IndexNO","sseIndexNoError");
	isDropdownSelected(isemptyDropdown(("sseSchool")),"School name","sseSchoolError");
	isDropdownSelected(isemptyDropdown(("sseAchievedon")),"Achived on date","sseAchievedonError");
	
	isDropdownSelected(isemptyDropdown('sseQualification'),"Qualification","sseQualificationError");
	isDropdownSelected(isemptyDropdown('sseStream'),"Stream","sseStreamError");
	isDropdownSelected(isemptyDropdown('sseResult'),"Result","sseResultError");
	isDropdownSelected(isemptyDropdown('sseMedium'),"Medium","sseMediumError");

	if(($('#sseIndexNoError').text() != '')||($('#sseSchoolError').text() != '')||($('#sseAchievedonError').text() != '')||
	($('#sseQualificationError').text() != '')||($('#sseStreamError').text() != '')||($('#sseResultError').text() != '')||
	($('#sseMediumError').text() != '')){
		return false;
	}else{
		return true;
	}
}

/**
 * This is to clear form filled data Professional Education.
 * @returns void
 */
function clearProfessionalExpForm() {
	$('#industryoftheOrganization').get(0).selectedIndex = 0;
	$('#jobCategory').get(0).selectedIndex = 0;
	$('#organization').val("");
	$('#designation').val("");
	$('#commencedOn').val("");
	$('#completionOn').val("");
	$('#jobDescription').val("");
	$("#pesaveChangesStatus").hide();
}

/**
 * Validate ProfessionalExp details form.
 * @returns
 */
function validateProfessionalExpForm(){
	isDropdownSelected(isemptyDropdown(("organization")),"Organization","organizationError");
	isDropdownSelected(isemptyDropdown(("designation")),"Designation","designationError");
	isDropdownSelected(isemptyDropdown(("commencedOn")),"Commenced on date","commencedOnError");
	isDropdownSelected(isemptyDropdown(("completionOn")),"Completion on date","completionOnError");
	
	isDropdownSelected(isemptyDropdown('industryoftheOrganization'),"Industry of the Organization","industryoftheOrganizationError");
	isDropdownSelected(isemptyDropdown('jobCategory'),"Job Category","jobCategoryError");

	if(($('#organizationError').text() != '')||($('#designationError').text() != '')||($('#commencedOnError').text() != '')||
	($('#completionOnError').text() != '')||($('#industryoftheOrganizationError').text() != '')||($('#jobCategoryError').text() != '')){
		return false;
	}else{
		return true;
	}
}

/**
 * Get data and sent to CmdProfessionalExpDetails.java.
 * @returns
 */
function addProfessionalExpForm() {
	if(validateProfessionalExpForm()){
		var industry = $('#industryoftheOrganization').val();
		var jobCategoty = $('#jobCategory').val();
		var organization = $('#organization').val();
		var designation = $('#designation').val();
		var commencedOn = $('#commencedOn').val();
		var completionOn = $('#completionOn').val();
		var description = $('#jobDescription').val();

	var jsonData = {
			"industry" : industry,
			"jobCategoty" : jobCategoty,
			"organization" : organization,
			"designation" : designation,
			"commencedOn" : commencedOn,
			"completionOn" : completionOn,
			"description" : description
	};
	
	$.ajax({
		type : "POST",
		url : '../../StudentController',
		data : {
			jsonData : JSON.stringify(jsonData),
			CCO : "APE"
		},
		dataType : "json",
		success : function(data) {			
			if(data.pesaveChangesStatus){	
					if(data.pesaveChangesStatus === "Unsuccessful."){
						$("#pesaveChangesStatus").addClass("alert alert-danger").text(data.pesaveChangesStatus).show();
					}else if(data.pesaveChangesStatus === "Invalid Information"){
						$("#pesaveChangesStatus").addClass("alert alert-danger").text("Invalid Information.").show();
					}
				clearProfessionalExpForm();	
				$("#pesaveChangesStatus").addClass("alert alert-success").text(data.pesaveChangesStatus).show();
			}
		},
		error : function(e) {
			alert("Error " + e);
			$("#pesaveChangesStatus").addClass("alert alert-warning").text(e).show();
		}
	});
	
	
}
}

/**
 * Get data and sent to CmdAddStudentPersonlDetails.java. 
 * @returns
 */
function addStudentPersonalDetails(){	
	if(validateStudentPersonalDetails()){
		var firstName = $('#sFullName').val();
		var middleName = $('#sMiddleName').val();
		var lastName = $('#sLastName').val();
		var dateOfBirth = $('#sBirthDate').val();
		var description = $('#sAboutMe').val();
		var mobilePhoneNo = $('#sMobileNumber').val();
		var landPhoneNo = $('#sHomeNumber').val();
		var address1 = $('#sAddress').val();
		var town = $('#sTownCode').val();
		var email = $('#sEmail').val();
		var facebookUrl = $('#sFacebookUrl').val();
		var twitterUrl = $('#stwitterUrl').val();
		var linkedInUrl = $('#sLinkedInUrl').val();
		var instagramUrl = $('#sInstergramUrl').val();
		var mySpaceUrl = $('#smySpace').val();
		var whatsAppNumber = $('#sWhatsApp').val();
		var viberNumber = $('#sViber').val();	
		var landPhoneCountryCode = $('#sCountryCode').val();
		var gender = $('input[gender]:checked').val();
		
		var jsonData = {
				"firstName" : firstName,
				"middleName" : middleName,
				"lastName" : lastName,
				"dateOfBirth" : dateOfBirth,
				"description" : description,
				"mobilePhoneNo" : mobilePhoneNo,
				"landPhoneNo" : landPhoneNo,
				"address1" : address1,
				"town" : town,
				"email" : email,
				"facebookUrl" : facebookUrl,
				"twitterUrl" : twitterUrl,
				"linkedInUrl" : linkedInUrl,
				"instagramUrl" : instagramUrl,
				"mySpaceUrl" : mySpaceUrl,
				"whatsAppNumber" : whatsAppNumber,
				"viberNumber" : viberNumber,
				"landPhoneCountryCode" : landPhoneCountryCode,
				"gender" : gender
		};
		
		$.ajax({
			type : "POST",
			url : '../../StudentController',
			data : {
				jsonData : JSON.stringify(jsonData),
				CCO : "APD"
			},
			dataType : "json",
			success : function(data) {			
				alert(data);
				if(data.studentPersonalStatus){	
						if(data.studentPersonalStatus === "Unsuccessful."){
							$("#studentPersonalStatus").addClass("alert alert-danger").text(data.pesaveChangesStatus).show();
						}else if(data.studentPersonalStatus === "Invalid Information"){
							$("#studentPersonalStatus").addClass("alert alert-danger").text("Invalid Information.").show();
						}
					clearPersonalDetailsForm();	
					$("#studentPersonalStatus").addClass("alert alert-success").text(data.studentPersonalStatus).show();
				}
			},
			error : function(e) {
				alert("Error " + e);
				$("#studentPersonalStatus").addClass("alert alert-warning").text(e).show();
			}
		});
	}
}

function validateStudentPersonalDetails(){
	isDropdownSelected(isemptyDropdown(("sFullName")),"First Name","sFullNameError");
	isDropdownSelected(isemptyDropdown(("sLastName")),"Last Name","sLastNameError");
	isDropdownSelected(isemptyDropdown(("sMobileNumber")),"Mobile Number","sMobileNumberError");
	isDropdownSelected(isemptyDropdown(("sTown")),"Town","sTownError");
	isDropdownSelected(isemptyDropdown(("sCountry")),"Country","sCountryError");
	isDropdownSelected(isemptyDropdown(("sEmail")),"Email","sEmailError");
	
	if($('#sBirthDate').val()){
		isPastfromNow("sBirthDate", "sBirthDateError");
	}
	
	if(($('#sFullNameError').text() != '')||($('#sLastNameError').text() != '')||($('#sBirthDateError').text() != '')||
	($('#sTownError').text() != '')||($('#sCountryError').text() != '')||($('#sEmailError').text() != '')){
		if($('#sBirthDate').val()){
			if($('#sBirthDateError') != ""){
				return false;
			}
		}
			return false;
	}else{
			return true;
	}
}

function clearPersonalDetailsForm(){
	$('#sFullName').val("");
	$('#sMiddleName').val("");
	$('#sLastName').val("");
	$('#sBirthDate').val("");
	$('#sAboutMe').val("");
	$('#sCountry').val("");
	$('#sTown').val("");
	$('#sAddress').val("");
	$('#countryCodePrefix').text("");
	$('#sMobileNumber').val("");
	$('#sHomeNumber').val("");
	$('#sOtherNumber').val("");
	$('#sEmail').val("");
	$('#sFacebookUrl').val("");
	$('#stwitterUrl').val("");
	$('#sLinkedInUrl').val("");
	$('#sInstergramUrl').val("");
	$('#smySpace').val("");
	$('#sWhatsApp').val("");
	$('#sViber').val("");
	$('#sTownCode').val("");
	$('#sCountryCode').val("");
	$("#studentPersonalStatus").hide();
}

/**
 * This method is to save student interests into the DB.
 * @returns
 */
function addInterestsDetails() {
	var values = $("#studentInterests").val();
	var prevalues = extStudentInterests;
	
	$.ajax({
		type : "POST",
		url : '../../StudentController',
		data : {
			oldStudentInterests : prevalues.toString(),
			newStudentInterests : values,
			CCO : "ASI"
		},
		dataType : "json",
		success : function(data) {			
			extStudentInterests = [];
			$.each(response.result, function(index, value) {
				var res = value.toString();
				var data = res.split(",");
				extStudentInterests.push(parseInt(data[0]));
				$('#studentInterests').tagsinput('add', { "value": parseInt(data[0]) , "text": data[1] , "continent": "A" });
			});
			
//			if(data.studentPersonalStatus){	
//					if(data.studentPersonalStatus === "Unsuccessful."){
//						$("#studentPersonalStatus").addClass("alert alert-danger").text(data.pesaveChangesStatus).show();
//					}else if(data.studentPersonalStatus === "Invalid Information"){
//						$("#studentPersonalStatus").addClass("alert alert-danger").text("Invalid Information.").show();
//					}
//				clearPersonalDetailsForm();	
//				$("#studentPersonalStatus").addClass("alert alert-success").text(data.studentPersonalStatus).show();
//			}
		},
		error : function(e) {
			alert("Error " + e);
		}
	});
}


/**
 * This method generates a Json object to pass into tag-it input.
 * @param response
 * @returns
 */
function createJsonObj(response){
	var inputValues = "";
	var startBrc = "[";
	var endBrc = "]";
	var elements = "";
	
	$.each(response, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var result = '{ "value": '+parseInt(data[0])+ ', "text": "' +data[1]+ '" , "continent": "A" },';
		elements = elements.concat(result);
	});
	startBrc = startBrc.concat(elements.slice(0, -1));
	endBrc = startBrc.concat(endBrc);
	inputValues = endBrc;
	return inputValues;
}


/**
 * This method is to save student skills into the DB.
 * @returns
 */
function addSkillDetails() {
	var values = $("#studentSkills").val();
	var prevalues = extStudentSkills;
	
	$.ajax({
		type : "POST",
		url : '../../StudentController',
		data : {
			oldStudentSkills : prevalues.toString(),
			newStudentSkills : values,
			CCO : "ASS"
		},
		dataType : "json",
		success : function(data) {			
			extStudentSkills = [];
			$.each(response.result, function(index, value) {
				var res = value.toString();
				var data = res.split(",");
				extStudentSkills.push(parseInt(data[0]));
				$('.example_objects_as_tags > > input').tagsinput('add', { "value": parseInt(data[0]) , "text": data[1] , "continent": "A" });
			});
			
//			if(data.studentPersonalStatus){	
//					if(data.studentPersonalStatus === "Unsuccessful."){
//						$("#studentPersonalStatus").addClass("alert alert-danger").text(data.pesaveChangesStatus).show();
//					}else if(data.studentPersonalStatus === "Invalid Information"){
//						$("#studentPersonalStatus").addClass("alert alert-danger").text("Invalid Information.").show();
//					}
//				clearPersonalDetailsForm();	
//				$("#studentPersonalStatus").addClass("alert alert-success").text(data.studentPersonalStatus).show();
//			}
		},
		error : function(e) {
			alert("Error " + e);
		}
	});
}



/**
 * All the required functions for Professional Education. 
 * @param table
 * @returns
 */
//
//Updates "Select all" control in a data table
//
function updateDataTableSelectAllCtrl(table){
var $table             = table.table().node();
var $chkbox_all        = $('tbody input[type="checkbox"]', $table);
var $chkbox_checked    = $('tbody input[type="checkbox"]:checked', $table);
var chkbox_select_all  = $('thead input[name="select_all"]', $table).get(0);

//If none of the checkboxes are checked
if($chkbox_checked.length === 0){
 chkbox_select_all.checked = false;
 if('indeterminate' in chkbox_select_all){
    chkbox_select_all.indeterminate = false;
 }

//If all of the checkboxes are checked
} else if ($chkbox_checked.length === $chkbox_all.length){
 chkbox_select_all.checked = true;
 if('indeterminate' in chkbox_select_all){
    chkbox_select_all.indeterminate = false;
 }

//If some of the checkboxes are checked
} else {
 chkbox_select_all.checked = true;
 if('indeterminate' in chkbox_select_all){
    chkbox_select_all.indeterminate = true;
 }
}
}

//$(document).ready(function (){
function populateTable(src){	
	
//Array holding selected row IDs
var rows_selected = [];
//displayDetails();
	
var tbd = src;

var table = $('#example').DataTable({
//'ajax': 'https://api.myjson.com/bins/1us28',
 'columnDefs': [{
    'targets': 0,
    'searchable':false,
    'orderable':false,
    'width':'1%',
    'className': 'dt-body-center',
    'render': function (data, type, full, meta){
        return '<input type="checkbox">';
    }
 }],
 'order': [1, 'asc'],
 'rowCallback': function(row, data, dataIndex){
    // Get row ID
    var rowId = data[0];
    //alert("data"+data);
    // If row ID is in the list of selected row IDs
    if($.inArray(rowId, rows_selected) !== -1){
       $(row).find('input[type="checkbox"]').prop('checked', true);
       $(row).addClass('selected');
    }
 }
});

//Handle click on checkbox
$('#example tbody').on('click', 'input[type="checkbox"]', function(e){
 var $row = $(this).closest('tr');

 // Get row data
 var data = table.row($row).data();

 // Get row ID
 var rowId = data[0];

 // Determine whether row ID is in the list of selected row IDs 
 var index = $.inArray(rowId, rows_selected);

 // If checkbox is checked and row ID is not in list of selected row IDs
 if(this.checked && index === -1){
    rows_selected.push(rowId);

 // Otherwise, if checkbox is not checked and row ID is in list of selected row IDs
 } else if (!this.checked && index !== -1){
    rows_selected.splice(index, 1);
 }

 if(this.checked){
    $row.addClass('selected');
 } else {
    $row.removeClass('selected');
 }

 // Update state of "Select all" control
 updateDataTableSelectAllCtrl(table);

 // Prevent click event from propagating to parent
 e.stopPropagation();
});


/* Formatting function for row details - modify as you need */
/*function format ( d ) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<td>Full name:</td>'+
            '<td>'+d.name+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Extension number:</td>'+
            '<td>'+d.extn+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Extra info:</td>'+
            '<td>And any further details here (images etc)...</td>'+
        '</tr>'+
    '</table>';
}

//Array to track the ids of the details displayed rows
var detailRows = [];

$('#example tbody').on( 'click', 'tr table.details-control', function () {
    var tr = $(this).closest('tr');
    var row = table.row( tr );
    var idx = $.inArray( tr.attr('id'), detailRows );

    if ( row.child.isShown() ) {
        tr.removeClass( 'details' );
        row.child.hide();

        // Remove from the 'open' array
        detailRows.splice( idx, 1 );
    }
    else {
        tr.addClass( 'details' );
        row.child( format( row.data() ) ).show();

        // Add to the 'open' array
        if ( idx === -1 ) {
            detailRows.push( tr.attr('id') );
        }
    }
} );
*/


//Handle click on table cells with checkboxes
$('#example').on('click', 'tbody td, thead th:first-child', function(e){
 $(this).parent().find('input[type="checkbox"]').trigger('click');
});

//Handle click on "Select all" control
$('thead input[name="select_all"]', table.table().container()).on('click', function(e){
 if(this.checked){
    $('#example tbody input[type="checkbox"]:not(:checked)').trigger('click');
 } else {
    $('#example tbody input[type="checkbox"]:checked').trigger('click');
 }

 // Prevent click event from propagating to parent
 e.stopPropagation();
});

//Handle table draw event
table.on('draw', function(){
 // Update state of "Select all" control
 updateDataTableSelectAllCtrl(table);
});

//Handle form submission event 
$('#frm-example').on('submit', function(e){
 var form = this;

 // Iterate over all selected checkboxes
 $.each(rows_selected, function(index, rowId){
    // Create a hidden element 
    $(form).append(
        $('<input>')
           .attr('type', 'hidden')
           .attr('name', 'id[]')
           .val(rowId)
    );
    table.row('.selected').remove().draw( false );
 });

 // FOR DEMONSTRATION ONLY     
 
 // Output form data to a console     
 //$('#example-console').text($(form).serialize());
 //alert($(form).serialize());
 //console.log("Form submission", $(form).serialize());
  
 // Remove added elements
 $('input[name="id\[\]"]', form).remove();
  
 // Prevent actual form submission
 e.preventDefault();
});
}
