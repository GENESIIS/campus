
/*20170418 c54-report-course-stats-MP-dj Initiated course-stats.js*/

$(document).ready(function() {
	/*
	 * This ajax call load the initial search interface for course stats report generation.
	 */
	$.ajax({
		url : '../../ReportController',
		data : {
			CCO : 'REPORT_COURSE_STATS'			
		},
		datatype : "json",
		success : function(response) {
			alert("success");
		},
		error : function(jqXHR, exception) {
			alert("error");
		}
	});
});