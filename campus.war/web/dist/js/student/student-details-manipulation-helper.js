/**
 * 20170214 PN CAM-28: INIT student-details-manipulation-helper.js file to contain all data manipulation functions for student profile JSP page. all the methods are previously located and copied from student-details-helper.js file.
 * 20170305 PN CAM-150: isCountryEmpty() method and getTownDetails() method implemented to perform town details selection datalist.
 * 20170306 PN CAM-150: clearPersonalDetailsForm() method modified to clear error spans. modified the JQuery which takes the gender value from the radio. added a code to close model on successful details update, in addStudentPersonalDetails() method.
 * 20170306 PN CAM-150: validateStudentPersonalDetails() method modified to validate empty date of birth.
 * 20170309 PN CAM-150: addStudentPersonalDetails() method and clearPersonalDetailsForm() method modified to display AddressLine1, AddressLine2, AddressLine3 separately.
 * 20170309 PN CAM-150: populatePersonalDataformElements() method implementation modified by adding validation to check if the student details collection is updated, empty or null.
 * 20170310 PN CAM-150: addStudentPersonalDetails() method modified by adding if-else condition.
 */



/**
 * This method generates a Json object to pass into tag-it input.
 * @param response
 * @returns
 */
function createJsonObj(response) {
	var inputValues = "";
	var startBrc = "[";
	var endBrc = "]";
	var elements = "";

	$.each(response, function(index, value) {
		var result = '{ "value": ' + parseInt(value[0]) + ', "text": "'
				+ value[1] + '" , "continent": "A" },';
		elements = elements.concat(result);
	});
	startBrc = startBrc.concat(elements.slice(0, -1));
	endBrc = startBrc.concat(endBrc);
	inputValues = endBrc;
	return inputValues;
}

/**
 * Get data and sent to CmdAddHigherEducationData.java.
 * @returns
 */
function addHigherEducationDetails() {
	if (validateHigherEducationForm()) {
		var code = $('#highedu-id').val();
		var instituteofStudy = $('#instituteofStudy').val();
		var affiliatedInstitute = $('#affiliatedInstitute').val();
		var areaofstudy = $('#areaofstudy').val();
		var award = $('#award').val();
		var studentId = $('#studentId').val();
		var gpa = $('#gpa').val();
		var heCommencedOn = $('#heCommencedOn').val();
		var heCompletedOn = $('#heCompletedOn').val();
		var heMedium = $('#heMedium').val();
		var country = getSelectedData('heCountry', 'heCountryList');
		var heDescription = $('#heDescription').val();

		var jsonData = {
			"code" : code,
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

		$
				.ajax({
					type : "POST",
					url : '../../../StudentController',
					data : {
						jsonData : JSON.stringify(jsonData),
						CCO : "AHE"
					},
					dataType : "json",
					success : function(response) {
						if (response.saveChangesHigherEduStatus) {
							if (response.saveChangesHigherEduStatus === "Unsuccessful.") {
								//$("#saveChangesHigherEduStatus").addClass("alert alert-danger").text(response.saveChangesHigherEduStatus).fadeIn();
							} else if (response.saveChangesHigherEduStatus === "Invalid Information") {
								//$("#saveChangesHigherEduStatus").addClass("alert alert-danger").text("Invalid Information.").fadeIn();
							}
							//clearSchoolEducationForm();
							//$("#saveChangesHigherEduStatus").addClass("alert alert-success").text(response.saveChangesHigherEduStatus).fadeIn();
							
							
							var higherEdutbl = $('#higherEdutbl').DataTable();
							higherEdutbl.clear().draw();
							var highereducount = 0;
							var higheredu = $('#li-std-higheredu');
							higheredu.find('li').remove();
							$.each(
											response.result,
											function(index, value) {
												if (highereducount < 2) {
													higheredu
															.append('<li>'
																	+ value[3]
																			.toString()
																	+ '-'
																	+ value[4]
																			.toString()
																	+ ' <span class="drop-at"> at </span> '
																	+ value[1]
																			.toString()
																	+ ' <br><span class="drop-time"> Duration: '
																	+ value[6]
																			.toString()
																	+ ' to '
																	+ value[7]
																			.toString()
																	+ '</span></li>');
												}

												highereducount++;

												higherEdutbl.row
														.add(
																[
																		value[0]
																				.toString(),
																		value[1]
																				.toString()
																				+ "<br/>"
																				+ value[2]
																						.toString(),
																		value[3]
																				.toString()
																				+ "<br/>"
																				+ value[4]
																						.toString(),
																		value[9]
																				.toString()
																				+ "<br/>"
																				+ "IndexNo: "
																				+ value[8]
																						.toString(),
																		value[11]
																				.toString(),
																		value[5]
																				.toString(),
																		value[6]
																				.toString()
																				+ "<br/>"
																				+ value[7]
																						.toString(),
																		value[10]
																				.toString(),
																		'<button type="button" class="btn btn-info editstpe"><span class="glyphicon glyphicon-edit"></span></button>' ])
														.draw(false);
											});
							$("#saveChangesHigherEduStatus").fadeOut();
						}
					},
					error : function(e) {
						alert("Error " + e);
						$("#saveChangesHigherEduStatus").addClass(
								"alert alert-warning").text(e).fadeIn();
					}
				});
	}
}

/**
 * Validate higher education details form.
 * @returns
 */
function validateHigherEducationForm() {
	isDropdownSelected(isemptyDropdown(("instituteofStudy")),
			"Institute of Study", "instituteofStudyError");
	isDropdownSelected(isemptyDropdown(("studentId")), "Student ID",
			"studentIdError");
	isDropdownSelected(isemptyDropdown(("heCommencedOn")), "Commenced on date",
			"heCommencedOnError");
	isDropdownSelected(isemptyDropdown(("heCompletedOn")),
			"Completion on date", "heCompletedOnError");

	isDropdownSelected(isemptyDropdown('areaofstudy'), "Area of study",
			"areaofstudyError");
	isDropdownSelected(isemptyDropdown('award'), "Award", "awardError");

	if (($('#instituteofStudyError').text() != '')
			|| ($('#studentIdError').text() != '')
			|| ($('#heCommencedOnError').text() != '')
			|| ($('#heCompletedOnError').text() != '')
			|| ($('#areaofstudyError').text() != '')
			|| ($('#awardError').text() != '')) {
		return false;
	} else {
		return true;
	}
}

/**
 * This is to clear HigherEducation form filled data
 * @returns
 */
function clearHigherEducationForm() {
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
 * This method is to save student SchoolEducationData.
 * @returns
 */
function addEducationDetails() {
	if (validateForm()) {
		var schoolGrade = $('#sseQualification').val();
		var major = $('#sseStream').val();
		var result = $('#sseResult').val();
		var medium = $('#sseMedium').val();
		var indexNo = $('#sseIndexNo').val();
		var schoolName = $('#sseSchool').val();
		var achievedOn = $('#sseAchievedon').val();
		var country = getSelectedData('sseCountry', 'sseCountryList');
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
					url : '../../../StudentController',
					data : {
						jsonData : JSON.stringify(jsonData),
						CCO : "ASD"
					},
					dataType : "json",
					success : function(data) {
						if (data.saveChangesStatus) {
							if (data.saveChangesStatus === "Unsuccessful.") {
								//$("#saveChangesStatus").addClass("alert alert-danger").text(data.saveChangesStatus).fadeIn();
								alert(data.saveChangesStatus);
							} else if (data.saveChangesStatus === "Invalid Information") {
								//$("#saveChangesStatus").addClass("alert alert-danger").text("Invalid Information.").fadeIn();
								alert(data.saveChangesStatus);
							}
							//$("#saveChangesStatus").addClass("alert alert-success").text(data.saveChangesStatus).fadeIn();
							alert(data.saveChangesStatus);
							var schooledu = $('#li-std-schooledu');
							schooledu.find('li').remove();
							schooledu.append('<li>'
									+ $("#sseQualification option:selected")
											.text()
									+ ' <span class="drop-at"> at </span> '
									+ schoolName
									+ ' <br><span class="drop-time">'
									+ achievedOn + '</span></li>');
							//$("#saveChangesStatus").fadeOut();
						}
					},
					error : function(e) {
						alert("Error " + e);
						//$("#saveChangesStatus").addClass("alert alert-warning").text(e).fadeIn();
					}
				});
	}
}

/**
 * This is to clear SchoolEducation form filled data
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
function validateForm() {
	isDropdownSelected(isemptyDropdown(("sseIndexNo")), "IndexNO",
			"sseIndexNoError");
	isDropdownSelected(isemptyDropdown(("sseSchool")), "School name",
			"sseSchoolError");
	isDropdownSelected(isemptyDropdown(("sseAchievedon")), "Achived on date",
			"sseAchievedonError");

	isDropdownSelected(isemptyDropdown('sseQualification'), "Qualification",
			"sseQualificationError");
	isDropdownSelected(isemptyDropdown('sseStream'), "Stream", "sseStreamError");
	isDropdownSelected(isemptyDropdown('sseResult'), "Result", "sseResultError");
	isDropdownSelected(isemptyDropdown('sseMedium'), "Medium", "sseMediumError");

	if (($('#sseIndexNoError').text() != '')
			|| ($('#sseSchoolError').text() != '')
			|| ($('#sseAchievedonError').text() != '')
			|| ($('#sseQualificationError').text() != '')
			|| ($('#sseStreamError').text() != '')
			|| ($('#sseResultError').text() != '')
			|| ($('#sseMediumError').text() != '')) {
		return false;
	} else {
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
function validateProfessionalExpForm() {
	isDropdownSelected(isemptyDropdown(("organization")), "Organization",
			"organizationError");
	isDropdownSelected(isemptyDropdown(("designation")), "Designation",
			"designationError");
	isDropdownSelected(isemptyDropdown(("commencedOn")), "Commenced on date",
			"commencedOnError");
	isDropdownSelected(isemptyDropdown(("completionOn")), "Completion on date",
			"completionOnError");

	isDropdownSelected(isemptyDropdown('industryoftheOrganization'),
			"Industry of the Organization", "industryoftheOrganizationError");
	isDropdownSelected(isemptyDropdown('jobCategory'), "Job Category",
			"jobCategoryError");

	if (($('#organizationError').text() != '')
			|| ($('#designationError').text() != '')
			|| ($('#commencedOnError').text() != '')
			|| ($('#completionOnError').text() != '')
			|| ($('#industryoftheOrganizationError').text() != '')
			|| ($('#jobCategoryError').text() != '')) {
		return false;
	} else {
		return true;
	}
}

/**
 * This method is to save student ProfessionalExpDetails.
 * @returns
 */
function addProfessionalExpForm() {
	if (validateProfessionalExpForm()) {
		var code = $('#proexp-id').val();
		var industry = $('#industryoftheOrganization').val();
		var jobCategoty = $('#jobCategory').val();
		var organization = $('#organization').val();
		var designation = $('#designation').val();
		var commencedOn = $('#commencedOn').val();
		var completionOn = $('#completionOn').val();
		var description = $('#jobDescription').val();

		var jsonData = {
			"code" : code,
			"industry" : industry,
			"jobCategoty" : jobCategoty,
			"organization" : organization,
			"designation" : designation,
			"commencedOn" : commencedOn,
			"completionOn" : completionOn,
			"description" : description
		};

		$
				.ajax({
					type : "POST",
					url : '../../../StudentController',
					data : {
						jsonData : JSON.stringify(jsonData),
						CCO : "APE"
					},
					dataType : "json",
					success : function(response) {
						if (response.pesaveChangesStatus) {
							if (response.pesaveChangesStatus === "Unsuccessful.") {
								//$("#pesaveChangesStatus").addClass("alert alert-danger").text(response.pesaveChangesStatus).fadeIn();
								alert(response.pesaveChangesStatus);
								return;
							} else if (response.pesaveChangesStatus === "Invalid Information") {
								//$("#pesaveChangesStatus").addClass("alert alert-danger").text("Invalid Information.").fadeIn();
								alert(response.pesaveChangesStatus);
								return;
							}
							//clearProfessionalExpForm();
							//$("#pesaveChangesStatus").addClass("alert alert-success").text(response.pesaveChangesStatus).fadeIn();														
							var table = $('#example').DataTable();
							table.clear().draw();

							var experienceducount = 0;
							var experience = $('#li-std-experience');
							experience.find('li').remove();

							$
							.each(
									response.result,
									function(index, value) {
										if (experienceducount < 2) {
												experience.append('<li>'+value[2].toString()+' <span class="drop-at">at</span> '+value[1].toString()+' <br><span class="drop-time"> Duration: '+value[5].toString()+'-'+value[6].toString()+'</span></li>');
											}

										experienceducount++;
															
										table.row
												.add(
														[
																value[0].toString(),
																value[1].toString(),
																value[2].toString(),
																value[3].toString(),
																value[4].toString(),
																value[5].toString() + "<br/>"
																		+ value[6].toString(),
																		value[7].toString(),
																'<button type="button" class="btn btn-info editstpe"><span class="glyphicon glyphicon-edit"></span></button>' ])
												.draw(false);
									});
										
							//$("#pesaveChangesStatus").fadeOut();
							alert(response.pesaveChangesStatus);
						}
					},
					error : function(e) {
						alert("Error " + e);
						//$("#pesaveChangesStatus").addClass("alert alert-warning").text(e).fadeIn();
					}
				});

	}
}

/**
 * This method is to save student personal details.
 * @returns
 */
function addStudentPersonalDetails() {
	if (validateStudentPersonalDetails()) {
		var firstName = $('#sFullName').val();
		var middleName = $('#sMiddleName').val();
		var lastName = $('#sLastName').val();
		var dateOfBirth = $('#sBirthDate').val();
		var description = $('#sAboutMe').val();
		var mobilePhoneNo = $('#sMobileNumber').val();
		var landPhoneNo = $('#sHomeNumber').val();
		var address1 = $('#sAddressLine1').val();
		var address2 = $('#sAddressLine2').val();
		var address3 = $('#sAddressLine3').val();
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
		var gender = $("input[name='gender']:checked").val();

		var jsonData = {
			"firstName" : firstName,
			"middleName" : middleName,
			"lastName" : lastName,
			"dateOfBirth" : dateOfBirth,
			"description" : description,
			"mobilePhoneNo" : mobilePhoneNo,
			"landPhoneNo" : landPhoneNo,
			"address1" : address1,
			"address2" : address2,
			"address3" : address3,
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
					url : '../../../StudentController',
					data : {
						jsonData : JSON.stringify(jsonData),
						CCO : "APD"
					},
					dataType : "json",
					success : function(data) {
						if (data.studentPersonalStatus) {
							studentPersonalDataSet = data.result;
							if (data.studentPersonalStatus === "Details updated successfully." || data.studentPersonalStatus === "Details added successfully.") {
								$.each(studentPersonalDataSet, function(index, value) {
									$('#fullname-hedding').html(value[4] + ' ' + value[5] +' '+ value[6]);
									$('#td-value-username').html("<b>" + value[1] + "</b>");
									$('#td-value-fullname').html(value[4] + ' ' + value[5] + ' ' + value[6]);
									$('#td-value-birthday').html(value[7]);
									$('#td-value-gender').html(getGenderString(parseInt(value[8])));
									$('#td-value-email').html(value[9]);
									$('#td-value-country').html(value[30]);
									$('#td-value-town').html(value[31]);
									$('#td-value-address').html(value[25]+" "+value[26]+" "+value[27]);
									$('#td-value-fbprofile').html(value[18]);
									$('#td-value-mobileno').html(value[11] + '-' + value[16]);
									$('#td-value-aboutme').html(value[17]);
								});
								
								alert(data.studentPersonalStatus);
								$('#studentPersonalDetailsModal').modal('hide');
								return;
							} else {
								alert(data.studentPersonalStatus);
								return;
							}
						}
					},
					error : function(e) {
						alert("Error " + e);
					}
				});
	}
}

/**
 * This method is to validate Personal Details Form data. If all the details are valid returns True, else false.
 * @returns boolean
 */
function validateStudentPersonalDetails() {
	isDropdownSelected(isemptyDropdown(("sFullName")), "First Name",
			"sFullNameError");
	isDropdownSelected(isemptyDropdown(("sLastName")), "Last Name",
			"sLastNameError");
	isDropdownSelected(isemptyDropdown(("sMobileNumber")), "Mobile Number",
			"sMobileNumberError");
	isDropdownSelected(isemptyDropdown(("sTown")), "Town", "sTownError");
	isDropdownSelected(isemptyDropdown(("sCountry")), "Country",
			"sCountryError");
	isDropdownSelected(isemptyDropdown(("sEmail")), "Email", "sEmailError");
	
	if ($('#sBirthDate').val()) {
		isPastfromNow("sBirthDate", "sBirthDateError");
	}else{
		isDropdownSelected(isemptyDropdown(("sBirthDate")), "Birth Date", "sBirthDateError");
	}
	
	if(!isValidEmailFormat($('#sEmail').val())){
		document.getElementById("sEmailError").innerHTML = "Invalid email address.";
	}

	if (($('#sFullNameError').text() != '')
			|| ($('#sLastNameError').text() != '')
			|| ($('#sBirthDateError').text() != '')
			|| ($('#sTownError').text() != '')
			|| ($('#sCountryError').text() != '')
			|| ($('#sEmailError').text() != '')) {
		if ($('#sBirthDate').val()) {
			if ($('#sBirthDateError') != "") {
				return false;
			}
		}
		return false;
	} else {
		return true;
	}
}

/**
 * This method is to clear Personal Details Form
 * @returns
 */
function clearPersonalDetailsForm() {
	$('#sFullName').val("");
	$('#sMiddleName').val("");
	$('#sLastName').val("");
	$('#sBirthDate').val("");
	$('#sAboutMe').val("");
	$('#sCountry').val("");
	$('#sTown').val("");
	$('#sAddressLine1').val("");
	$('#sAddressLine2').val("");
	$('#sAddressLine3').val("");
	$('.input-group-addon').text("");
	$('.error-msg').text("");
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
				url : '../../../StudentController',
				data : {
					oldStudentInterests : prevalues.toString(),
					newStudentInterests : values,
					CCO : "ASI"
				},
				dataType : "json",
				success : function(data) {
					extStudentInterests = [];
					$.each(data.result, function(index, value) {
						extStudentInterests.push(parseInt(value[0]));
						$('#studentInterests').tagsinput('add', {
							"value" : parseInt(value[0]),
							"text" : value[1],
							"continent" : "A"
						});
					});

					if (data.studentInterestSaveStatus) {
						if (data.studentInterestSaveStatus === "Unsuccessful.") {
							//$("#studentInterestSaveStatus").addClass("alert	 alert-danger").text(data.studentSkillSaveStatus).fadeIn();
							alert(data.studentSkillSaveStatus);
							return;
						} else if (data.studentPersonalStatus === "Invalid Information") {
							//$("#studentInterestSaveStatus").addClass("alert alert-danger").text("Invalid Information.").fadeIn();
							alert(data.studentSkillSaveStatus);
							return;
						}
						//$("#studentInterestSaveStatus").addClass("alert alert-success").text(data.studentInterestSaveStatus).fadeIn();
						//$("#studentInterestSaveStatus").addClass("alert alert-success").text(data.studentInterestSaveStatus).fadeOut();
						alert(data.studentSkillSaveStatus);
					}
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
function createJsonObj(response) {
	var inputValues = "";
	var startBrc = "[";
	var endBrc = "]";
	var elements = "";

	$.each(response, function(index, value) {
		var result = '{ "value": ' + parseInt(value[0]) + ', "text": "'
				+ value[1] + '" , "continent": "A" },';
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
				url : '../../../StudentController',
				data : {
					oldStudentSkills : prevalues.toString(),
					newStudentSkills : values,
					CCO : "ASK"
				},
				dataType : "json",
				success : function(data) {
					extStudentSkills = [];
					$.each(data.result, function(index, value) {
						extStudentSkills.push(parseInt(value[0]));
						$('.example_objects_as_tags > > input').tagsinput(
								'add', {
									"value" : parseInt(value[0]),
									"text" : value[1],
									"continent" : "A"
								});
					});

					if (data.studentSkillSaveStatus) {
						if (data.studentSkillSaveStatus === "Unsuccessful.") {
							//$("#studentSkillSaveStatus").addClass("alert alert-danger").text(data.studentSkillSaveStatus).fadeIn();
							alert(data.studentSkillSaveStatus);
							return;
						} else if (data.studentSkillSaveStatus === "Invalid Information") {
							//$("#studentSkillSaveStatus").addClass("alert alert-danger").text("Invalid Information.").fadeIn();
							alert(data.studentSkillSaveStatus);
							return;
						}
						//$("#studentSkillSaveStatus").addClass("alert alert-success").text(data.studentSkillSaveStatus).fadeIn();
						//$("#studentSkillSaveStatus").addClass("alert alert-success").text(data.studentSkillSaveStatus).fadeOut();
						alert(data.studentSkillSaveStatus);
					}
				},
				error : function(e) {
					alert("Error " + e);
				}
			});
}

//Below two methods are for maintain update functionality on, 'select all' condition. One single method with parameters giving an error so that method redundant in here.
/**
 * Updates "Select all" control in a data table (Experiences Table)
 * @param table
 * @returns
 */
function updateDataTableSelectAllCtrl(table) {
	var $table = table.table().node();
	var $chkbox_all = $('tbody input[type="checkbox"]', $table);
	var $chkbox_checked = $('tbody input[type="checkbox"]:checked', $table);
	var chkbox_select_all = $('thead input[name="select_all"]', $table).get(0);

	// If none of the checkboxes are checked
	if ($chkbox_checked.length === 0) {
		chkbox_select_all.checked = false;
		if ('indeterminate' in chkbox_select_all) {
			chkbox_select_all.indeterminate = false;
		}

		// If all of the checkboxes are checked
	} else if ($chkbox_checked.length === $chkbox_all.length) {
		chkbox_select_all.checked = true;
		if ('indeterminate' in chkbox_select_all) {
			chkbox_select_all.indeterminate = false;
		}

		// If some of the checkboxes are checked
	} else {
		chkbox_select_all.checked = true;
		if ('indeterminate' in chkbox_select_all) {
			chkbox_select_all.indeterminate = true;
		}
	}
}

/**
 * Updates "Select all" control in a data table Higher Education.
 * @param table
 * @returns
 */
function updatehigherEduDataTableSelectAllCtrl(table) {
	var $table = table.table().node();
	var $chkbox_all = $('tbody input[type="checkbox"]', $table);
	var $chkbox_checked = $('tbody input[type="checkbox"]:checked', $table);
	var chkbox_select_all = $('thead input[name="select_all"]', $table).get(0);

	// If none of the checkboxes are checked
	if ($chkbox_checked.length === 0) {
		chkbox_select_all.checked = false;
		if ('indeterminate' in chkbox_select_all) {
			chkbox_select_all.indeterminate = false;
		}

		// If all of the checkboxes are checked
	} else if ($chkbox_checked.length === $chkbox_all.length) {
		chkbox_select_all.checked = true;
		if ('indeterminate' in chkbox_select_all) {
			chkbox_select_all.indeterminate = false;
		}

		// If some of the checkboxes are checked
	} else {
		chkbox_select_all.checked = true;
		if ('indeterminate' in chkbox_select_all) {
			chkbox_select_all.indeterminate = true;
		}
	}
}
//---------------------------------------------------------------------//

/**
 * This method is to set the selected option in a drop-down list using
 * selectedIndex property.
 * @param selectObj
 * @param valueToSet
 * @returns
 */
function setSelectedValue(selectObj, valueToSet) {
	for (var i = 0; i < selectObj.options.length; i++) {
		if (selectObj.options[i].text == valueToSet) {
			selectObj.options[i].selected = true;
			return;
		}
	}
}

/**
 * This method is to set radio input value according to the DB values.
 * 
 * @param name
 * @param SelectdValue
 */
function radionButtonSelectedValueSet(name, SelectdValue) {
	$('input[name="' + name + '"][value="' + SelectdValue + '"]').prop(
			'checked', true);
}

/**
 * This is to get the string value of gender name.
 * @param val
 * @returns
 */
function getGenderString(val) {
	if (parseInt(val) == 1) {
		return "Male";
	} else if (parseInt(val) == 0) {
		return "Female";
	}
}

/**
 * Method is to load details on Professional details form on edit button click.
 * @param data
 * @returns
 */
function populateProfExpDetailsForm(data){
	$('#proexp-id').val(data[0]);
	$('#organization').val(data[1]);
	$('#designation').val(data[3]);

	var str = data[5];
	var res = str.split("<br/>");

	$('#commencedOn').val(res[0]);
	$('#completionOn').val(res[1]);
	$('#jobDescription').val(data[6]);

	// Get select object
	var industry = document
			.getElementById("industryoftheOrganization");
	// Set selected
	setSelectedValue(industry, data[2]);

	// Get select object
	var jobCategory = document.getElementById("jobCategory");
	// Set selected
	setSelectedValue(jobCategory, data[4]);
}

/**
 * Method is to load details on Higher education details form on edit button click.
 * @param data
 * @returns
 */
function populateHigherEduDetailsForm(data){
	$('#highedu-id').val(data[0]);
	var institute = data[1];
	var res1 = institute.split("<br/>");

	$('#instituteofStudy').val(res1[0]);
	$('#affiliatedInstitute').val(res1[1]);

	var certificate = data[2];
	var res2 = certificate.split("<br/>");

	var areaofstudy = document.getElementById("areaofstudy");
	setSelectedValue(areaofstudy, res2[1]);

	var award = document.getElementById("award");
	setSelectedValue(award, res2[0]);

	var result = data[3];
	var res3 = result.split("<br/>IndexNo:");

	$('#studentId').val(res3[1]);
	$('#gpa').val(res3[0]);

	var duration = data[6];
	var res4 = duration.split("<br/>");

	$('#heCommencedOn').val(res4[0]);
	$('#heCompletedOn').val(res4[1]);

	var heMedium = document.getElementById("heMedium");
	setSelectedValue(heMedium, data[4]);

	$('#heCountry').val(data[5]);
	$('#heDescription').val(data[7]);
}

/**
 * Method is to populate personal details form UI elements
 * @param response
 * @returns
 */
function populatePersonalDataformElements(response){
	// Set Country details
	var sCountry = $("#sCountry");
	sCountry.find('option').remove();
	$('<option>').val("").text("--Select One--").appendTo(sCountry);
	$.each(response.country2Collection, function(index, value) {
		var x = value[0].toString();
		var y = value[1].toString();
		$('<option>').val(x).text(y).appendTo(sCountry);
	});

	// Set Country details
	var sCountryList = $("#sCountryList");
	sCountryList.find('option').remove();
	$.each(response.country2Collection, function(index, value) {
		var x = value[0].toString();
		var y = value[1].toString();
		$('#sCountryList').append(
				"<option data-value='" + x + "'>" + y + "</option>");
	});

	// Set country code prefixed to the phone numbers.
	$("#sCountry").on('input', function() {
		var val = this.value;
		var dValue = $('#sCountryList option').filter(function() {
			return this.value === val;
		}).data('value');
		var msg = dValue;
		if (msg) {
			$("span[class='input-group-addon']").text("(" + msg + ")");
			$('#sCountryCode').val(msg);
			getTownDetails(msg);
		}
	});

	// Set town code to pass into servlet.
	$("#sTown").on('input', function() {
		var val = this.value;
		var dValue = $('#sTownList option').filter(function() {
			return this.value === val;
		}).data('value');
		var msg = dValue;
		if (msg) {
			$('#sTownCode').val(msg);
		}
	});
	
	var studentDetails = null;
	if((studentPersonalDataSet != null)&&(studentPersonalDataSet != '')&&(studentPersonalDataSet != undefined)){
		studentDetails = studentPersonalDataSet;
	}else{
		studentDetails = response.studentCollection;
	}
	$.each(studentDetails, function(index, value) {
		$('#fullname-hedding').html(value[4] + ' ' + value[5] +' '+ value[6]);
		$('#td-value-username').html("<b>" + value[1] + "</b>");
		$('#td-value-fullname').html(value[4] + ' ' + value[5] + ' ' + value[6]);
		$('#td-value-birthday').html(value[7]);
		$('#td-value-gender').html(getGenderString(parseInt(value[8])));
		$('#td-value-email').html(value[9]);
		$('#td-value-country').html(value[30]);
		$('#td-value-town').html(value[31]);
		$('#td-value-address').html(value[25]+" "+value[26]+" "+value[27]);
		$('#td-value-fbprofile').html(value[18]);
		$('#td-value-mobileno').html(value[11] + '-' + value[16]);
		$('#td-value-aboutme').html(value[17]);
		
		$('#sFullName').val(value[4]);
		$('#sMiddleName').val(value[5]);
		$('#sLastName').val(value[6]);
		$('#sBirthDate').val(value[7]);
		$('input[gender]:checked').val();
		$('#sEmail').val(value[9]);
		$('#sCountryCode').val(value[11]);
		$('#sHomeNumber').val(value[13]);
		$('#sMobileNumber').val(value[16]);
		$('#sAddressLine1').val(value[25]);
		$('#sAddressLine2').val(value[26]);
		$('#sAddressLine3').val(value[27]);
		$('#sFacebookUrl').val(value[18]);
		$('#stwitterUrl').val(value[19]);
		$('#smySpace').val(value[20]);
		$('#sLinkedInUrl').val(value[21]);
		$('#sInstergramUrl').val(value[22]);
		$('#sViber').val(value[23]);
		$('#sWhatsApp').val(value[24]);
		$('#sAboutMe').val(value[17]);
		$('#sTownCode').val(value[28]);
		$('#sCountry').val(value[30]);
		$('#sTown').val(value[31]);
		$("span[class='input-group-addon']").text("(" + value[11] + ")");

		if (parseInt(value[8]) == 1) {
			radionButtonSelectedValueSet("gender", "1");
		} else if (parseInt(value[8]) == 0) {
			radionButtonSelectedValueSet("gender", "0");
		}
	});
}

/**
 * Method is to populate School education details form UI elements
 * @param response
 * @returns
 */
function populateSchoolEduformElements(response){
	// Set Scheme details
	var sseStream = $("#sseStream");
	sseStream.find('option').remove();
	$('<option>').val("").text("--Select One--").appendTo(sseStream);
	$.each(response.majorCollection, function(index, value) {
		var x = value[0].toString();
		var y = value[1].toString();
		$('<option>').val(x).text(y).appendTo(sseStream);
	});
	
	// Set Qualification details
	var sseQualification = $("#sseQualification");
	sseQualification.find('option').remove();
	$('<option>').val("").text("--Select One--").appendTo(sseQualification);
	$.each(response.schoolGradeCollection, function(index, value) {
		var x = value[0].toString();
		var y = value[1].toString();
		$('<option>').val(x).text(y).appendTo(sseQualification);
	});
	
	// Set Country details
	var sseCountry = $("#sseCountryList");
	sseCountry.find('option').remove();
	$.each(response.country2Collection, function(index, value) {
		var x = value[0].toString();
		var y = value[1].toString();
		$('<option>').val(y).text(x).appendTo(sseCountry);
	});

	// Set medium details
	var sseMedium = $("#sseMedium");
	sseMedium.find('option').remove();
	$('<option>').val("").text("--Select One--").appendTo(sseMedium);
	$.each(response.mediumCollection, function(index, value) {
		var x = value[0].toString();
		var y = value[1].toString();
		$('<option>').val(x).text(y).appendTo(sseMedium);
	});
	
	if (response.result) {
		$.each(response.result, function(index, value) {
			$('#sseQualification').val(value[2]);
			$('#sseStream').val(value[3]);
			$('#sseResult').val(value[5]);
			$('#sseMedium').val(value[10]);
			$('#sseIndexNo').val(value[6]);
			$('#sseSchool').val(value[7]);
			$('#sseAchievedon').val(value[8]);
			$('#sseCountry').val(value[4]);
			$('#sseDescription').val(value[9]);

			var schooledu = $('#li-std-schooledu');
			schooledu.find('li').remove();
			schooledu.append('<li>'
					+ $("#sseQualification option:selected").text()
					+ ' <span class="drop-at"> at </span> ' + value[7]
					+ ' <br><span class="drop-time">' + value[8]
					+ '</span></li>');
		});
	}
}

/**
 * Method is to populate higher education details form UI elements
 * @param response
 * @returns
 */
function populateHigherEduformElements(response){
	// Set area of study details
	var areaofstudy = $("#areaofstudy");
	areaofstudy.find('option').remove();
	$('<option>').val("").text("--Select One--").appendTo(areaofstudy);
	$.each(response.majorCollection, function(index, value) {
		var x = value[0].toString();
		var y = value[1].toString();
		$('<option>').val(x).text(y).appendTo(areaofstudy);
	});
	
	// Set award details
	var award = $("#award");
	award.find('option').remove();
	$('<option>').val("").text("--Select One--").appendTo(award);
	$.each(response.schoolGradeCollection, function(index, value) {
		var x = value[0].toString();
		var y = value[1].toString();
		$('<option>').val(x).text(y).appendTo(award);
	});

	// Set heCountryList details
	var heCountryList = $("#heCountryList");
	heCountryList.find('option').remove();
	$.each(response.country2Collection, function(index, value) {
		var x = value[0].toString();
		var y = value[1].toString();
		$('<option>').val(y).text(x).appendTo(heCountryList);
	});
	
	// Set heMedium details
	var heMedium = $("#heMedium");
	heMedium.find('option').remove();
	$('<option>').val("").text("--Select One--").appendTo(heMedium);
	$.each(response.mediumCollection, function(index, value) {
		var x = value[0].toString();
		var y = value[1].toString();
		$('<option>').val(x).text(y).appendTo(heMedium);
	});
}

/**
 * Method is to populate professional experience details form UI elements
 * @param response
 * @returns
 */
function populateProfExpformElements(response){
	// Set Industry of the Organization
	var industryoftheOrganization = $("#industryoftheOrganization");
	industryoftheOrganization.find('option').remove();
	$('<option>').val("").text("--Select One--").appendTo(
			industryoftheOrganization);
	$.each(response.majorCollection, function(index, value) {
		var x = value[0].toString();
		var y = value[1].toString();
		$('<option>').val(x).text(y).appendTo(industryoftheOrganization);
	});

	// Set Job Category
	var jobCategory = $("#jobCategory");
	jobCategory.find('option').remove();
	$('<option>').val("").text("--Select One--").appendTo(jobCategory);
	$.each(response.majorCollection, function(index, value) {
		var x = value[0].toString();
		var y = value[1].toString();
		$('<option>').val(x).text(y).appendTo(jobCategory);
	});
}

/**
 * Method is to get respective town details according to the selected country.
 * @returns
 */
function getTownDetails() {
	var country = $('#sCountryCode').val();
	$.ajax({
		url : '../../../StudentController',
		data : {
			CCO : 'GTD',
			country: country
		},
		dataType : "json",
		success : function(response) {
			// Set Country details
			var sTown = $("#sTown");
			sTown.find('option').remove();
			$('<option>').val("").text("--Select One--").appendTo(sTown);
			$.each(response.result, function(index, value) {
				var x = value[0].toString();
				var y = value[1].toString();
				$('<option>').val(x).text(y).appendTo(sTown);
			});

			// Set Country details
			var sTownList = $("#sTownList");
			sTownList.find('option').remove();
			$.each(response.result, function(index, value) {
				var x = value[0].toString();
				var y = value[1].toString();
				$('#sTownList').append("<option data-value='" + x + "'>" + y + "</option>");
			});
		},
		error : function(response) {
			alert("Error: " + response);
		}
	});
}

/**
 * Chek if country selected before select a town.
 * @returns
 */
function isCountryEmpty() {
	isDropdownSelected(isemptyDropdown(("sCountry")),"Country","sTownError");
	
	if ($('#sTownError').text() != ''){
		var sTown = $("#sTown");
		sTown.find('option').remove();
		
		$('<option>').val("").text("--Select One--").appendTo(sTown);
		var sTownList = $("#sTownList");
		sTownList.find('option').remove();	
	}
}