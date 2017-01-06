//20161219 DJ CAM-51-report-courses-by-course-provider-MP-dj Ajax controls for courses by course provider report generation

// allproviderListCollection contains all the course providers in all statuses
var allproviderListCollection=0;

$(document).ready(function() {		
	/*
	 * This ajax call load the initial search interface for courses by course provider report generation.
	 */
	$.ajax({
		url : '../../ReportController',
		data : {
			CCO : 'SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER'
		},
		dataType : "json",
		success : function(response) {
			getProviderSearchData(response);
		},
		error : function(jqXHR, exception) {			
			errorCodeGeneration(jqXHR, exception);
		}
	});
	
	/*
	 * Identify course provider status click.Providers list according to provider status selection. 
	 */
	
	$("input[name='providerStatus']").change(function(){		
		var statusValue=$('input:radio[name=providerStatus]:checked').val();
		var cpStatusCheck=0;
		if(statusValue=="ACTIVE"){
			cpStatusCheck=1;
		}else if(statusValue=="INACTIVE"){
			cpStatusCheck=2;
		}
		$('#providerlist').val("");	
		var htmlstr="";
		$.each(allproviderListCollection, function(index, value) {		
			if(value!=null && value.length>0){
				if(value[2] !=null && value[2]==cpStatusCheck){
				htmlstr += '<option val="' + value[0] + '">' + value[1] + '</option>';
				}
			}		
		});
		
		$('#providerName').html(htmlstr);
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
 * This method getProviderSearchData() populate data for  initial search interface for courses by course provider report generation.
 */
function getProviderSearchData(response){
	$('#resultSetDiv').hide();
	$('input:radio[name="providerStatus"]').filter('[value="ACTIVE"]').attr('checked', true);
	$('input:radio[name="courseStatus"]').filter('[value="ACTIVE"]').attr('checked', true);
	allproviderListCollection=response.courseProviderList;
	var htmlstr="";	
	$.each(response.courseProviderList, function(index, value) {		
		if(value!=null && value.length>0){
			if(value[2] !=null && value[2]==1){			
			htmlstr += '<option val="' + value[0] + '">' + value[1] + '</option>';
			}
		}		
	});		
	$('#providerName').html(htmlstr);	
} 


/*
 * This method loadResultSet() identify input parameters for report search.
 */
function loadResultSet(event) {

	var cProviderName = $('#providerlist').val();
	var providerCode = 0;

	var option = $('#providerName').find('option');
	for (var i = 0; i < option.length; i++) {
		$('#providerName').find('option')[i].outerHTML;
		if (option[i].text == cProviderName) {
			providerCode = option[i].attributes[0].value;
			break;
		}
	}

	var startDate = $('#startdate').val();
	var endDate = $('#enddate').val();
	var providerStatus = $('input:radio[name=providerStatus]:checked').val();
	var courseStatus = $('input:radio[name=courseStatus]:checked').val();

	if (!($('input:radio[name=providerStatus]:checked').length > 0)) {
		alert("you have to choose a provider status");
		return false;
	}
	if (!($('input:radio[name=courseStatus]:checked').length > 0)) {
		alert("you have to choose a programme status");
		return false;
	}

	$.ajax({
		url : '../../ReportController',
		data : {
			CCO : 'REPORT_COURSES_BY_COURSE_PROVIDER',
			cProviderCode : providerCode,
			startDate : startDate,
			endDate : endDate,
			providerStatus : providerStatus,
			courseStatus : courseStatus
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
function populateResultTable(response){
	$('#resultPanel').show();
	$('#resultSetDiv').hide();
	var programmeListTable = $("#tBody");
	programmeListTable.find('tr').remove();	
	var totalResultCount=0;	
	$.each(response.allProgrammeResultList, function(index, value) {
		$('#resultSetDiv').show();
	if(value!=null && value.length>0){ 
		totalResultCount ++;
		var code = value[0].toString();
		var name = value[1].toString();
		var des = value[2].toString();
		var cpName = value[3].toString();
		var cStatus = value[4].toString();		
		var sDate = value[5].toString();
		var eDate = value[6].toString();
		
		var tr = '<tr>' ;
		tr += '<td>' + totalResultCount + '</td>';
		tr += '<td>' + code  + '</td>';
		tr += '<td>' + name  + '</td>';
		tr += '<td>' + des  + '</td>';
		tr += '<td>' + cpName  + '</td>';
		tr += '<td>' + cStatus  + '</td>';
		tr += '<td>' + sDate  + '</td>';
		tr += '<td>' + eDate  + '</td>';
		programmeListTable.append(tr);		
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
	$('#providerlist').val("");	
	$('input:radio[name="providerStatus"]').filter('[value="ACTIVE"]').prop('checked', true);
	
	var htmlstr="";
	$.each(allproviderListCollection, function(index, value) {		
		if(value!=null && value.length>0){
			if(value[2] !=null && value[2]==1){
			htmlstr += '<option value="' + value[0] + '">' + value[1] + '</option>';
			}
		}		
	});	
	$('#providerName').html(htmlstr);
	
	$('input:radio[name="courseStatus"]').filter('[value="ACTIVE"]').prop('checked', true);
	$('#startdate').val(""); 
	$('#enddate').val(""); 	
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