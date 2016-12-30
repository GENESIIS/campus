//20161219 DJ CAM-51-report-courses-by-course-provider-MP-dj Ajax controls for courses by course provider report generation

// allproviderListCollection contains all the course providers in all statuses
var allproviderListCollection=0;

$(document).ready(function() {
	
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
	});
	
	//Identify course provider status click
	$("input[name='providerStatus']").change(function(){		
		var statusValue=$('input:radio[name=providerStatus]:checked').val();
		var cpStatusCheck=0;
		if(statusValue=="ACTIVE"){
			cpStatusCheck=1;
		}else if(statusValue=="INACTIVE"){
			cpStatusCheck=2;
		}
		var htmlstr="";
		$.each(allproviderListCollection, function(index, value) {		
			if(value!=null && value.length>0){
				if(value[2] !=null && value[2]==cpStatusCheck){
				htmlstr += '<option value="' + value[0] + '">' + value[1] + '</option>';
				}
			}		
		});
		
		$('#providerName').html(htmlstr);
	});
	
	$('#searchList').on('click', function(event) {
		loadResultSet(event);
	});	
	
	$('#clearParam').on('click', function(event) {
		clearParameters(event);
	});	
});


function getProviderSearchData(response){
	$('#resultSetDiv').hide();
	$('input:radio[name="providerStatus"]').filter('[value="ACTIVE"]').attr('checked', true);
	$('input:radio[name="courseStatus"]').filter('[value="ACTIVE"]').attr('checked', true);
	allproviderListCollection=response.courseProviderList;
	var htmlstr="";	
	$.each(response.courseProviderList, function(index, value) {		
		if(value!=null && value.length>0){
			if(value[2] !=null && value[2]==1){
			htmlstr += '<option value="' + value[0] + '">' + value[1] + '</option>';
			}
		}		
	});		
	$('#providerName').html(htmlstr);	
} 

function loadResultSet(event){	
	var cpCode= $('#providerlist').val();
	var startDate= $('#startdate').val();
	var endDate= $('#enddate').val();
	var providerStatus=$('input:radio[name=providerStatus]:checked').val();
	var courseStatus=$('input:radio[name=courseStatus]:checked').val();	
	
	
	if(!($('input:radio[name=providerStatus]:checked').length>0)){
		 alert("you have to choose a provider status");
		 return false;
	}
	if(!($('input:radio[name=courseStatus]:checked').length>0)){
		alert("you have to choose a programme status");	
		return false;
	}
	
	
	$.ajax({
		url:'../../ReportController',
		data:{
			CCO:'REPORT_COURSES_BY_COURSE_PROVIDER',
			cProviderCode:cpCode,
			startDate:startDate,
			endDate:endDate,
			providerStatus:providerStatus,			
			courseStatus:courseStatus			
		},
		datatype:"json",
		success : function(response) {				
			populateResultTable(response);
		},
		error : function(jqXHR, exception) {			
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
	});
}
function populateResultTable(response){
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

function clearParameters(event){
	$('#providerlist').val("");	
	$('input:radio[name="providerStatus"]').filter('[value="ACTIVE"]').attr('checked', true);
	$('input:radio[name="courseStatus"]').filter('[value="ACTIVE"]').attr('checked', true);
	$('#startdate').val(""); 
	$('#enddate').val(""); 	
}
