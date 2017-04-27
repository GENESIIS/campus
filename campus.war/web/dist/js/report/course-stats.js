
/*20170418 c54-report-course-stats-MP-dj Initiated course-stats.js
 *20170418 c54-report-course-stats-MP-dj Initiated getCourseStatsSearchData and Identify selected course provider action.
 *20170421 c54-report-course-stats-MP-dj Identified the selected course provider code for listing programmes.
 *20170421 c54-report-course-stats-MP-dj Implement listProgrammes() method and initiate search button click action.
 *20170425 c54-report-course-stats-MP-dj Implement populateCourseStatReportTable() and populate data.
 *20170425 c54-report-course-stats-MP-dj Implement clearParameters()-Clear form.
 *20170425 c54-report-course-stats-MP-dj validate the from date and to date when picking the calandar.fromdate<today and todate>fromdate
 *20170425 c54-report-course-stats-MP-dj create loadResultSet() and modularize the implementation-Validate date for 30 days of period.
 *20170425 c54-report-course-stats-MP-dj validate course provider as a mandatory field, and clear hidden keys in form clearing.
 *20170426 c54-report-course-stats-MP-dj 'displayErrorMessage' implementation to set the error message on provider selection-back end validation
 **/

$(document).ready(function() {
	/*
	 * This ajax call load the initial search interface for course stats report generation.
	 */
	$.ajax({
		url : '../../ReportController',
		data : {
			CCO : 'SEARCH_VIEW_COURSE_STATS'			
		},
		datatype : "json",
		success : function(response) {
			getCourseStatsSearchData(response);
			
			
		},
		error : function(jqXHR, exception) {
			errorCodeGeneration(jqXHR, exception);
		}
	});
	
	
	
	/*
	 * Identify selected course provider and load programmes. 
	 */

	$('#providerlist').on('change', function(event) {		
		$('#errorProviderList').text("");		
	    var value=event.target.value;
	    var providerCode = $('#providerName [value="' + value + '"]').data('provider');
	    	    
	    $('#selectedProvider').val(providerCode);	    
		
		$.ajax({
			url : '../../ReportController',
			data : {
				CCO : 'LIST_COURSE_PROVIDER_WISE_PROGRAMME',
				providerCode : providerCode
			},
			dataType : "json",
			success : function(response) {
				listProgrammes(response);
			},
			error : function(jqXHR, exception) {
				errorCodeGeneration(jqXHR, exception);
			}
		});
	});
	
	
	/*
	 * Identify selected programme and set the value to a hidden variable. 
	 */

	$('#programmelist').on('change', function(event) {		
				
	    var value=event.target.value;
	    var programmeCode = $('#programmeName [value="' + value + '"]').data('programme');	    	    
	    $('#selectedProgramme').val(programmeCode);
	});
	
	
	/*
	 * 
	 */
	
	$('#searchList').on('click ', function(event) {			
		loadResultSet(event);		
	});
	
	
	/*
	 * trigger Clear button click. 
	 */
	$('#clearParam').on('click', function(event) {
		clearParameters(event);
	});
	
	
	/*
	 * validate fromDate< current date
	 */	
	$('#startdate').on('click', function(event) {	
		var today=getCurrentDate();
		document.getElementById("startdate").setAttribute("max", today);
	});
	
	/*
	 * validate toDate>from date
	 */	
	$('#enddate').on('click', function(event) {		
		var fromDate= $('#startdate').val();		
		document.getElementById("enddate").setAttribute("min", fromDate);	
		var today=getCurrentDate();
		document.getElementById("enddate").setAttribute("max", today);	
		 
	});
});

/*
 * This method getCourseStatsSearchData() populate data for  initial search interface for course stats report generation.
 */
function getCourseStatsSearchData(response){	
	$('#resultSetDiv').hide();
	var dataList = $("#providerName");
	dataList.empty();
	
	$.each(response.courseProviderList, function(index, value) {		
		if(value != null && value.length > 0){				
			var opt = $("<option></option>").attr({"data-provider": value[0], "value" :value[1] });
			dataList.append(opt);
		}				
	});	
}


/*
 * This method listProgrammes()  data for  initial search interface for course stats report generation.
 */

function listProgrammes(response){	
	$('#resultSetDiv').hide();	
	var dataList = $("#programmeName");
	dataList.empty();	
	$.each(response.programmeList, function(index, value) {		
		if(value != null && value.length > 0){				
			var opt = $("<option></option>").attr({"data-programme": value[0], "value" :value[1] });
			dataList.append(opt);
		}				
	});
	
}


/*
 * This method loadResultSet() identify input parameters for report search.
 */
function loadResultSet(event){
	var programmeCode=$('#selectedProgramme').val();
	var providerCode=$('#selectedProvider').val();
	var startDate = $('#startdate').val();
	var endDate = $('#enddate').val();
	
	if(providerCode === undefined || providerCode === ""  ||	providerCode === null){
		$('#errorProviderList').text("Please select a course provider!");
		document.getElementById('errorProviderList').style.color = "red";
		return false;
	}
	
	$('#errorToDate').text("");
	if (Date.parse(startDate)> Date.parse(endDate)) {		
		$('#errorToDate').text("Invalid Date Range! From Date cannot be after To Date!");
		document.getElementById('errorToDate').style.color = "red";
		return false;
	}	
	var tempDate=new Date(document.getElementById("startdate").value);	
	tempDate.setDate(tempDate.getDate()+ 30);

	 if(Date.parse(endDate)>tempDate){
		 $('#errorToDate').text("Invalid Date Range! Date range should be within 30 days");
			document.getElementById('errorToDate').style.color = "red";
			return false;
	 }	
	
	$.ajax({
		url : '../../ReportController',
		data : {
			CCO : 'REPORT_COURSE_STATS',
			startDate : startDate,
			endDate : endDate,
			providerCode : providerCode,
			programmeCode : programmeCode
		},
		dataType : "json",
		success : function(response) {
			populateCourseStatReportTable(response);
		},
		error : function(jqXHR, exception) {
			errorCodeGeneration(jqXHR, exception);
		}
	});
}

/*
 * This method generateCourseStatReport() generate course stats report generation.
 */
function populateCourseStatReportTable(response){
	
	$('#displayErrorMessage').show();
	var errorMessageList = $("#displayErrorMessage");
	errorMessageList.find('li').remove();
	$.each(response.message, function(index, value) {
		if (value != null && value.length > 0) {
			var msg = value.toString();
			errorMessageList.append("<li>"+msg+"</li>");
		}		
	});
	
	$('#resultPanel').show();
	$('#resultSetDiv').hide();
	var studentListTable = $("#tBody");
	studentListTable.find('tr').remove();
	var totalResultCount = 0;	
	
	$.each(response.courseStatList, function(index, value) {
		$('#resultSetDiv').show();
		if (value != null && value.length > 0) {
			totalResultCount ++;
			var name = value[0];
			var view = value[1];			
			var inquireCount = value[2];
			
			var tr = '<tr>';
			tr += '<td>' + totalResultCount + '</td>';
			tr += '<td>' + name  + '</td>';
			tr += '<td>' + view  + '</td>';			
			tr += '<td>' + inquireCount  + '</td>';
			
			studentListTable.append(tr);
		}
	});
	if (totalResultCount > 0) {
		$('#totalResultsCount').text("Result Count " +totalResultCount);
	} else {
		$('#totalResultsCount').text(
				"No results found for selected search criteria");
	}
}

/**
 * This method errorCodeGeneration() manipulate with errors.
 */
function errorCodeGeneration(jqXHR, exception){
	var msg = '';
	   if (jqXHR.status === 0) {
         msg = 'Not connect.\n Verify Network.';
     } else if (jqXHR.status == 404) {
         msg = 'Requested page not found. [404]';
     } else if (jqXHR.status == 500) {
         msg = 'Internal Server Error [500].';
     } else if (exception === 'timeout') {
         msg = 'Time out error.';
     }  else {
         msg = 'Uncaught Error.\n' + jqXHR.responseText;
     }	        
 alert(msg);	
}


/**
 * This method clearParameters() clear load form.
 */
function clearParameters(event){	
	$('#resultPanel').hide();	
	$('#startdate').val(""); 
	$('#enddate').val(""); 	
	$('#providerlist').val("");	
	$('#selectedProvider').val("");	
	$('#programmelist').val("");	
	$('#selectedProgramme').val("");	
	$('#errorToDate').text("");
	$('#errorProviderList').text("");	
	$("#programmeName").empty();
}

/**
 * This method getCurrentDate() get current date(format 2017-02-08).
 */
function getCurrentDate() {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; // January is 0!
	var yyyy = today.getFullYear();
	if (dd < 10) {
		dd = '0' + dd
	}
	if (mm < 10) {
		mm = '0' + mm
	}

	today = yyyy + '-' + mm + '-' + dd;
	return today;
}