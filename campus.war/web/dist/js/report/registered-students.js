//DJ 20170102 c53-report-registered-students-MP-dj Ajax controls for registered students generation

$(document).ready(function() {

	$('#searchList').on('click', function(event) {
		loadResultSet(event);
	});

	$('#clearParam').on('click', function(event) {
		clearParameters(event);
	});

});

function loadResultSet(event) {

	var startDate = $('#startdate').val();
	var endDate = $('#enddate').val();
	var studentStatus = $('input:radio[name=studentStatus]:checked').val();

	$.ajax({
		url : '../../ReportController',
		data : {
			CCO : 'REPORT_REGISTERED_STUDENTS',
			startDate : startDate,
			endDate : endDate,
			studentStatus : studentStatus
		},
		datatype : "json",
		success : function(response) {
			populateResultTable(response);
		},
		error : function(jqXHR, exception) {
			errorCodeGeneration(jqXHR, exception);
		}
	});

}

function populateResultTable(response) {
	$('#resultPanel').show();
	$('#resultSetDiv').hide();
	var studentListTable = $("#tBody");
	studentListTable.find('tr').remove();
	var totalResultCount = 0;
	$.each(response.registeredStudentList, function(index, value) {
		$('#resultSetDiv').show();
		if (value != null && value.length > 0) {
			totalResultCount ++;
			var code = value[0].toString();
			var name = value[1].toString();
			var cStatus = value[2].toString();
			var crtOn = value[3].toString();
			var lastLoggedIn = value[4].toString();
			
			
			
			var tr = '<tr>' ;
			tr += '<td>' + totalResultCount + '</td>';
			tr += '<td>' + code  + '</td>';
			tr += '<td>' + name  + '</td>';
			tr += '<td>' + cStatus  + '</td>';	
			tr += '<td>' + crtOn  + '</td>';
			tr += '<td>' + lastLoggedIn  + '</td>';
					
			studentListTable.append(tr);	
		}
	});
}

function errorCodeGeneration(jqXHR, exception) {
	var msg = '';
	if (jqXHR.status === 0) {
		msg = 'Not connect.\n Verify Network.';
	} else if (jqXHR.status == 404) {
		msg = 'Requested page not found. [404]';
	} else if (jqXHR.status == 500) {
		msg = 'Internal Server Error [500].';
	} else if (exception === 'timeout') {
		msg = 'Time out error.';
	} else {
		msg = 'Uncaught Error.\n' + jqXHR.responseText;
	}
	alert(msg);
}