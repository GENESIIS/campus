
/*20170418 c54-report-course-stats-MP-dj Initiated course-stats.js
 *20170418 c54-report-course-stats-MP-dj Initiated getCourseStatsSearchData and Identify selected course provider action.
 *20170421 c54-report-course-stats-MP-dj Identified the selected course provider code for listing programmes.
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
});

/*
 * This method getCourseStatsSearchData() populate data for  initial search interface for course stats report generation.
 */
function getCourseStatsSearchData(response){
	$('#resultPanel').hide();	
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
	
	alert("listProgrammes");
	
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