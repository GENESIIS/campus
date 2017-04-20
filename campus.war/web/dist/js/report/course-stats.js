
/*20170418 c54-report-course-stats-MP-dj Initiated course-stats.js
 *20170418 c54-report-course-stats-MP-dj Initiated getCourseStatsSearchData and Identify selected course provider action.
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

	$('#providerlist').bind('change', function(event) {		
		alert("providerlist****");
		var providerName = $('#providerlist').val();
		var providerCode = 0;
		
		$.ajax({
			url : '../../ReportController',
			data : {
				CCO : 'LIST_PROGRAMME_WISE_COURSE_PROVIDER',
				providerCode : providerCode
			},
			dataType : "json",
			success : function(response) {
				getCourseStats(response);
			},
			error : function(jqXHR, exception) {
				errorCodeGeneration(jqXHR, exception);
			}
		});
	});
});

/*
 * This method getCourseStatsSearchData() populate data for  initial search interface for course stats report generation.
 */
function getCourseStatsSearchData(response){
	$('#resultPanel').hide();
	//$('input:radio[name="studentStatus"]').filter('[value="ACTIVE"]').attr('checked', true);

	var htmlstr="";	
	$.each(response.courseProviderList, function(index, value) {		
		if(value!=null && value.length>0){
			if(value!=null && value.length>0){		
				htmlstr += '<option val="' + value[0] + '">' + value[1] + '</option>';
			}
		}		
	});		
	$('#providerName').html(htmlstr);	
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