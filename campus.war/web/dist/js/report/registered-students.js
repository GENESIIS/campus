//DJ 20170102 c53-report-registered-students-MP-dj Ajax controls for registered students generation

$(document).ready(function() {
	/*
	 * This ajax call load the initial search interface for registered student report generation.
	 */
	$.ajax({
		url : '../../ReportController',
		data : {
			CCO : 'SEARCH_VIEW_REGISTERED_STUDENTS'			
		},
		datatype : "json",
		success : function(response) {
			getStudentSearchData(response);
		},
		error : function(jqXHR, exception) {
			errorCodeGeneration(jqXHR, exception);
		}
	});		
	
	/*
	 * trigger Search button click. 
	 */
	$('#searchList').on('click', function(event) {
		loadResultSet(event);
	});
	
	/*
	 * trigger Clear button click. 
	 */
	$('#clearParam').on('click', function(event) {
		clearParameters(event);
	});
	
});

/*
 * This method getStudentSearchData() populate data for  initial search interface for registered student report generation.
 */
function getStudentSearchData(response){
	$('#resultPanel').hide();
	$('input:radio[name="studentStatus"]').filter('[value="ACTIVE"]').attr('checked', true);

	var htmlstr="";	
	$.each(response.districtList, function(index, value) {		
		if(value!=null && value.length>0){
			if(value!=null && value.length>0){		
				htmlstr += '<option val="' + value[0] + '">' + value[2] + '</option>';
			}
		}		
	});		
	$('#districtName').html(htmlstr);	
}

/*
 * This method loadResultSet() identify input parameters for report search.
 */
function loadResultSet(event) {
	var districtName = $('#districtlist').val();
	var districtCode = 0;

	var option = $('#districtName').find('option');
	for (var i = 0; i < option.length; i++) {
		$('#districtName').find('option')[i].outerHTML;
		if (option[i].text == districtName) {
			districtCode = option[i].attributes[0].value;
			break;
		}
	}

	//var fromDate = $('#start').val();
	//var toDate = $('#end').val();
	var studentStatus = $('input:radio[name=studentStatus]:checked').val();
		
	var startDate = new Date(document.getElementById("startdate").value);
	var toDate = new Date(document.getElementById("enddate").value);
	
	
   /*var regex=new RegExp("([0-9]{4}[-](0[1-9]|1[0-2])[-]([0-2]{1}[0-9]{1}|3[0-1]{1})|([0-2]{1}[0-9]{1}|3[0-1]{1})[-](0[1-9]|1[0-2])[-][0-9]{4})");
	
	var fromDate= $('#fromDate').val();    
    if(!regex.test(fromDate)){
    	$('#errorFromDate').text("Please enter valid From Date");
		document.getElementById('errorFromDate').style.color = "red";
		return false;
    }else{
    	$('#errorFromDate').text("");
    }
    
    
    var toDate= $('#toDate').val();
    if(!regex.test(toDate)){
    	$('#errorToDate').text("Please enter valid To Date");
		document.getElementById('errorToDate').style.color = "red";
		return false;
    }else{
    	$('#errorToDate').text("");
    }  */
	$('#errorToDate').text("");
	if (startDate> toDate) {		
		$('#errorToDate').text("Invalid Date Range! From Date cannot be after To Date!");
		document.getElementById('errorToDate').style.color = "red";
		return false;
	}
	
	var tempDate=new Date(document.getElementById("startdate").value);
	
	tempDate.setDate(tempDate.getDate()+ 30);

	 if(Date.parse(toDate)>Date.parse(tempDate)){
		 $('#errorToDate').text("Invalid Date Range!Date range should be within 30 days");
			document.getElementById('errorToDate').style.color = "red";
			return false;
	 }
	

	$.ajax({
		url : '../../ReportController',
		data : {
			CCO : 'REPORT_REGISTERED_STUDENTS',
			startDate : startDate,
			endDate : toDate,
			studentStatus : studentStatus,
			districtCode :districtCode
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
/**
 * This method populateResultTable() manipulate search data list to a display table.
 */
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
			var code = value[0];
			var name = value[1];
			var interest = value[2];
			var townName = value[3];
			var cStatus = value[4];
			var crtOn = value[5];
			var lastLoggedIn = value[6];	
			
			var tr = '<tr>';
			tr += '<td>' + totalResultCount + '</td>';
			tr += '<td>' + code  + '</td>';
			tr += '<td>' + name  + '</td>';
			tr += '<td>' + interest  + '</td>';
			tr += '<td>' + townName  + '</td>';
			tr += '<td>' + cStatus  + '</td>';	
			tr += '<td>' + crtOn  + '</td>';
			tr += '<td>' + lastLoggedIn  + '</td>';
					
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
 * This method clearParameters() clear load form.
 */
function clearParameters(event){	
	$('#resultPanel').hide();
	$('input:radio[name="studentStatus"]').filter('[value="ACTIVE"]').prop('checked', true);
	$('#start').val(""); 
	$('#end').val(""); 
	$('#districtlist').val("");	
}

/**
 * This method errorCodeGeneration() manipulate with errors.
 */
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