
/**
 * file - hra.medicalhistory.js
 * 
 * @auther -@TR
 * @git branch used - hra-15-add-medical-history-details-tr
 * @created on- 2016-05-25
 */

//@TR - Employee > new employee > more details > add Medical history details 
function loadAddMedicalHIstoryDetails() {
	$("#modelrest").load("employeeDetails/addMedicalHistory.jsp");
}

// @TR - Employee > edit employee > more details > edit medical history details 
function loadEditMedicalHIstoryDetails() {
	$("#modelrestedit").load("editEmployeeDetails/editMedicalHistory.jsp");
}


// ready function
 $(document).on('ready', function() {
	 $('#upload').prop('disabled', true); 
 });
 
 
// number cheker
function isNumber(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	}
	return true;
}

function isEmpty(value) {
	return (value == null || value.length === 0);
}

function isValidImage() {
	if( document.getElementById("avatar").files.length == 0 ){
		return true;
	}
}

/*******************************************************************************
 * START ADD MEDICAL HISTORY thumeera Get data and sent to
 * EmployeeController.java MedicalHistory > new MedicalHistory > more details >
 * add new in MedicalHistory > SAVE
 ******************************************************************************/

function addMedicalhistorydetails() {

	var noError = true;
	var message = "";

	var employeeId = $("#employeeId").val();
	var employeeAilment = $("#employeeAilment").val();
	var ailmentDescription = $("#ailmentDescription").val();

	var ehReferencemodby = $("#ehReferencemodby").val();

	var hasEmptyemployeeid = isEmpty(employeeId);
	var hasEmptyemployeeailment = isEmpty(employeeAilment);
	var hasEmptyailmentDescription = isEmpty(ailmentDescription);

	var hasEmptyehReferencemodby = isEmpty(ehReferencemodby);

	
	if (hasEmptyemployeeid) {
		message = "Employee id cannot be empty";
		noError = false;
		alert(message);
	} else if (hasEmptyemployeeailment) {
		message = "Ailment cannot be empty";
		noError = false;
		alert(message);
	} else if (hasEmptyailmentDescription) {
		message = "Ailment Description cannot be empty";
		noError = false;
		alert(message);
	} else if (hasEmptyehReferencemodby) {
		message = "User authentication fails";
		noError = false;
		alert(message);
	}

	if (noError) {
		var formData = {
			"medicalHistoryemployeeid" : employeeId,
			"medicalHistoryailment" : employeeAilment,
			"medicalHistorydescription" : ailmentDescription,
			"medicalHistorymodby" : ehReferencemodby,
			"medicalHistorycrtby" : ehReferencemodby,
		};

		$.ajax({
			type : "POST",
			url : 'EmployerController',
			data : {
				jsonData : JSON.stringify(formData),
				task : "AMH"
			},
			dataType : "JSON",
			success : function(data) {
				
				alert(data);
				$('#save').prop('disabled', true); 
				$('#upload').prop('disabled', false); 
				
				$('#employeeAilment').attr('disabled',true);
				$('#ailmentDescription').attr('disabled',true);
 
				
				if (data == "Details added successfully.") {
					//clearMedicalHisory();
				}
			},
			error : function(e) {
				alert("Error " + e);
				console.log(e);
			}
		});
	}

}

/*******************************************************************************
 * END ADD MEDICAL HISTORY
 ******************************************************************************/

$(document).on("click", "#upload", function() {

	var noError = true;
	var message = "";

	var reportDescription = $("#reportDescription").val();
	var ehReferencemodby = $("#ehReferencemodby").val();
	var employeeId = $("#employeeId").val();
	
	// Getting the properties of file from file field
	var reportUpload = $("#avatar").prop("files")[0]; 
	
	// Creating object of FormData class 
	// and appending every attributes
	var formData = new FormData();
	formData.append("file", reportUpload);
	formData.append("reportDescription", reportDescription);
	formData.append("task", "AMR");
	formData.append("ehReferencemodby", ehReferencemodby);
	formData.append("employeeId", employeeId);

	var hasReportdescription = isEmpty(reportDescription);
	var hasReportupload = isValidImage();


	if (hasReportdescription) {
		message = "Report description cannot be empty";
		noError = false;
		alert(message);
	} 
	else if (hasReportupload) {
		message = "Please upload your file(s)";
		noError = false;
		alert(message);
	}

	if (noError) {

//		var formData2 = {
//			"reportDescription" : reportDescription,
//			"task" : "AMR",
//			"medicalHistorymodby" : ehReferencemodby,
//			"medicalHistorycrtby" : ehReferencemodby,
//			"employeeId" : employeeId,
//		};
		$.ajax({
			type : "POST",
			url : "EmployerController",
//			data : {
//				jsonData : JSON.stringify(formData),
//				task : "AMR",
//				reportUpload :reportUpload,
//			},
			data : formData, // Setting the data attribute of ajax with file_data
			cache : false,
			contentType : false,
			processData : false,
			dataType : "JSON",
			success : function(data) {
				alert(data);

				clearMedicalHisory();

				$('#save').prop('disabled', false); 
				$('#upload').prop('disabled', true);
				
				$('#employeeAilment').attr('disabled',false);
				$('#ailmentDescription').attr('disabled',false);
				
				if (data == "Details added successfully.") {
					//clearMedicalReports();
				}
			},
			error : function(e) {
				alert("Error " + e);
				console.log(e);
			}
		});
	}
});

//$(document).on("click", "#upload", function() {
//	var file_data = $("#avatar").prop("files")[0]; // Getting the properties of file from file field
//	var reportDescription = $("#reportDescription").val();
//	var employeeId = $("#employeeId").val();
//	var form_data = new FormData(); // Creating object of FormData class
//	form_data.append("file", file_data);
//	form_data.append("reportDescription", reportDescription);// Appending parameter named reportDescription with properties of file_field to form_data
//	form_data.append("employeeId", employeeId);// Appending parameter named file with properties of file_field to form_data
//	form_data.append("task", "AMR"); // Adding extra parameters to form_data
//	$.ajax({
//		url : "EmployeeController",
//		dataType : 'scrpt',
//		cache : false,
//		contentType : false,
//		processData : false,
//		data : form_data, // Setting the data attribute of ajax with file_data
//		type : 'post'
//	});
//});




/*******************************************************************************
 * Start Clear medical history data
 */

function clearMedicalHisory(){
	$('.fileinput').fileinput('reset');

	$("#employeeAilment").val("");
	$("#ailmentDescription").val("");

	$('#employeeAilment option').prop('selected', function() {
		return this.defaultSelected;
	});

	$("#reportDescription").val("");

	$('#upload').prop('disabled', true);

}

//View Medical History details
function viewMedicalDetails() {

	$.ajax({
		type : "POST",
		url : 'EmployerController',
		data : {
			jsonData : JSON.stringify("1"),
			inputValue : empEpf,
			task : "GMH"
		},
		dataType : "json",
		success : function(responseText) {
			// alert("ajax" + responseText);
			json = JSON.parse(responseText);
			// alert(responseText);
			// $("#employee_id").text(json.employeeEpf);
			
			$("#ailment").text(json.medicalHistoryailment);
			$("#ailmentDescription").text(json.medicalHistorydescription);
			$("#reportDescription").text(json.mdeicalReportDis);
			// $("#report").attr(json.MedicalReportPath);
			// document.getElementById("report").href="json.MedicalReportPath";
			$("#report").attr("href", "file:///" + json.MedicalReportPath);
			$("#imagereport").attr("src", "file:///" + json.MedicalReportPath);
		},
		error : function(e) {
			alert("Error " + e);
			console.log(e);
		}
	});
}
/*******************************************************************************
 * End Clear medical history data
 */
