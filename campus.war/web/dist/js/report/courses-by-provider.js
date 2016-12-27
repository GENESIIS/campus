//20161219 DJ CAM-51-report-courses-by-course-provider-MP-dj Ajax controls for courses by course provider report generation
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
	
	$('#searchList').on('click', function(event) {
		loadResultSet(event);
	});	
	$('#clearParam').on('click', function(event) {
		clearParameters(event);
	});		
});


function getProviderSearchData(response){
	$('#resultSetDiv').hide();	
	var htmlstr="";
	$.each(response.result, function(index, value) {
		if(value!=null && value.length>0){
			htmlstr += '<option value="' + value[0] + '">' + value[1] + '</option>';
		}		
	});		
	$('#providerName').html(htmlstr);	
} 

function loadResultSet(event){	
	var districtName = $('#providerlist').val();
	var cpCode= $('#providerlist').val();
	var startDate= $('#startdate').val();
	var endDate= $('#enddate').val();
	var statusValue=$('input:radio[name=status]:checked').val();
	
	$.ajax({
		url:'../../ReportController',
		data:{
			CCO:'REPORT_COURSES_BY_COURSE_PROVIDER',
			cProviderCode:cpCode,
			startDate:startDate,
			endDate:endDate,
			statusValue:statusValue			
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
	var coursesListTable = $("#table");
	coursesListTable.find('tr').remove();
	 var cCode='Course Code';
	var cName='Course Name';
	var cDes='Description';
	var cStatus='Status';
	var cStartDate='Display Start Date';
	var cExDate='Expiry Date';
	var headertr = '<tr>' ;
	headertr += '<td> # </td>';
	headertr += '<td>' + cCode  + '</td>';
	headertr += '<td>' + cName  + '</td>'; 
	headertr += '<td>' + cDes  + '</td>'; 
	headertr += '<td>' + cStatus  + '</td>'; 
	headertr += '<td>' + cStartDate  + '</td>'; 
	headertr += '<td>' + cExDate  + '</td>'; 
	coursesListTable.append(headertr);
	var count=1;
	$.each(response.coursesResultList, function(index, value) {	
		$('#resultSetDiv').show();
	if(value!=null && value.length>0){ 
		var code = value[0].toString();
		var name = value[1].toString();
		var des = value[2].toString();
		var cStatus = value[3].toString();		
		var sDate = value[4].toString();
		var eDate = value[5].toString();
		
		var tr = '<tr>' ;
		tr += '<td>' + count++  + '</td>';
		tr += '<td>' + code  + '</td>';
		tr += '<td>' + name  + '</td>';
		tr += '<td>' + des  + '</td>';
		tr += '<td>' + cStatus  + '</td>';
		tr += '<td>' + sDate  + '</td>';
		tr += '<td>' + eDate  + '</td>';
		coursesListTable.append(tr);
		
	 }
	});
}

function clearParameters(event){
	$('#providerlist').val("");		
	$('input:radio[name=status]').prop('checked', false);
	$('#startdate').val(""); 
	$('#enddate').val(""); 	
}
