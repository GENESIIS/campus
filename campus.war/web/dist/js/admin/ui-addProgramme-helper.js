/**
 * 20170202 DJ c138-add-basic-programme-MP-dj  Load  view form for Program details
 * 20170202 DJ c138-add-basic-programme-MP-dj  Initiated populateProgrammeAddView() method
 */

$(document).ready(function() {
	
	alert("Ready");
	
	$.ajax({
		url : '../../AdminController',
		data : {
			CCO : 'LIST_PROGRAMME_ADD_VIEW'			
		},
		dataType : "json",
		success : function(response) {			
			populateProgrammeAddView(response);
		},
		error : function(jqXHR, exception) {			
			errorCodeGeneration(jqXHR, exception);
		}
	});
	
});

function populateProgrammeAddView(response) {
		
	var htmlstr = "";
	$.each(response.result, function(index, value) {
		if (value != null && value.length > 0) {
			htmlstr += '<option val="' + value[0] + '">' + value[1]
					+ '</option>';
		}

	});
	$('#providerName').html(htmlstr);
	
	var htmlCategoryStr = "";
	$.each(response.allCategories, function(index, value) {
		if (value != null && value.length > 0) {
			htmlCategoryStr += '<option val="' + value[0] + '">' + value[1]
					+ '</option>';
		}

	});
	$('#categoryName').html(htmlCategoryStr);
	
	var htmlMajorStr = "";
	$.each(response.allMajors, function(index, value) {
		if (value != null && value.length > 0) {
			htmlMajorStr += '<option val="' + value[0] + '">' + value[1]
					+ '</option>';
		}

	});
	$('#majorName').html(htmlMajorStr);
	
	var htmlLevelStr = "";
	$.each(response.allLevels, function(index, value) {
		if (value != null && value.length > 0) {
			htmlLevelStr += '<option val="' + value[0] + '">' + value[1]
			+ '</option>';
		}
		
	});
	$('#levelName').html(htmlLevelStr);
	
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