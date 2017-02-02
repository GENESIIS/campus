/**
 * 20170202 DJ c138-add-basic-programme-MP-dj  Load  view form for Program details
 */

$(document).ready(function() {
	
	$.ajax({
		url : '../../AdminController',
		data : {
			CCO : 'LIST_PROGRAMME_ADD_VIEW'			
		},
		dataType : "json",
		success : function(response) {			
			// getAjaxData(response);
		},
		error : function(jqXHR, exception) {			
			var msg = '';
			if (jqXHR.status === 0) {
	            msg = 'Not connect.\n Verify Network.';
	        } else if (jqXHR.status == 404) {
	            msg = 'Requested page not found. [404]';
	        } else if (jqXHR.status == 500) {
	            msg = 'Internal Server Error [500].';
	        }  else if (exception === 'timeout') {
	            msg = 'Time out error.';
	        } else {
	            msg = 'Internal error is occurred. Please try again.';
	        }          
	        alert(msg);
		}
	});
	
});