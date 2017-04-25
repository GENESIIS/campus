
/*20170418 c54-report-course-stats-MP-dj Initiated course-stats.js
 *20170418 c54-report-course-stats-MP-dj Initiated getCourseStatsSearchData and Identify selected course provider action.
 *20170421 c54-report-course-stats-MP-dj Identified the selected course provider code for listing programmes.
 *20170421 c54-report-course-stats-MP-dj Implement listProgrammes() method and initiate search button click action.
 *20170425 c54-report-course-stats-MP-dj Implement populateCourseStatReportTable() and populate data.
 *20170425 c54-report-course-stats-MP-dj Implement clearParameters()-Clear form.
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
		
		alert("searchList");
		var programmeCode=$('#selectedProgramme').val();
		var providerCode=$('#selectedProvider').val();
		var startDate = $('#startdate').val();
		var endDate = $('#enddate').val();
		
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
		
	});
	
	
	/*
	 * trigger Clear button click. 
	 */
	$('#clearParam').on('click', function(event) {
		clearParameters(event);
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
 * This method generateCourseStatReport() generate course stats report generation.
 */
function populateCourseStatReportTable(response){
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
	$('#programmelist').val("");	
	$('#errorToDate').text("");	
}