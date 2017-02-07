/**
 * 20170202 DJ c138-add-basic-programme-MP-dj  Load  view form for Program details
 * 20170202 DJ c138-add-basic-programme-MP-dj  Initiated populateProgrammeAddView() method
 * 20170206 DJ c138-add-basic-programme-MP-dj  Initiated isEmpty(),isValidEmailFormat() methods.
 */

$(document).ready(function() {
	
	
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
	
	
	/*
	 * Add program button-event handler
	 */
	$('#addProgramme').click(function(event){		
		addProgramme(event);		
	});
	
	/*
	 * validate toDate>from date
	 */	
	$('#toDate').on('click', function(event) {		
		var fromDate= $('#fromDate').val();		
		document.getElementById("toDate").setAttribute("min", fromDate);	
	});
	
	
	/*
	 * clear button-event handler
	 */
	$('#clearParam').click(function(event){		
		clearParameters(event);
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
	
	var htmlClassTypeStr = "";
	$.each(response.allClassTypes, function(index, value) {
		if (value != null && value.length > 0) {
			htmlClassTypeStr += '<option val="' + value[0] + '">' + value[1]
			+ '</option>';
		}
		
	});
	$('#classTypeName').html(htmlClassTypeStr);
	
}

/**
 * This method addProgramme() for adding a course to the system.
 */
function addProgramme(){
	
	var email= $('#email').val();			
	if (!isEmpty(email) || !isValidEmailFormat(email)) {
		document.getElementById('errorEmail').innerHTML = "Invalid email format";
	}	
	
    var regex=new RegExp("([0-9]{4}[-](0[1-9]|1[0-2])[-]([0-2]{1}[0-9]{1}|3[0-1]{1})|([0-2]{1}[0-9]{1}|3[0-1]{1})[-](0[1-9]|1[0-2])[-][0-9]{4})");
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
    }    
   
	if (fromDate> toDate) {		
		$('#errorToDate').text("Invalid Date Range! From Date cannot be after To Date!");
		document.getElementById('errorToDate').style.color = "red";
		return false;
	}
	
	//After pass the validations developer able to add input data to database.
	$.ajax({
		url : '../../AdminController',
		data : {
			CCO : 'ADD_PROGRAMME_DETAILS'			
		},
		dataType : "json",
		success : function(response) {			
			alert("Programme add fucntionality will be implemented by another issue");
		},
		error : function(jqXHR, exception) {			
			errorCodeGeneration(jqXHR, exception);
		}
	});
	
}

/**
 * This method clearParameters() clear load form.
 */
function clearParameters(event){
	$('#fromDate').val(" "); 
	$('#toDate').val(" ");	
	$('#errorFromDate').text("");
	$('#errorToDate').text("");	
		
}

/**
 * @author DJ
 * @param fieldValue;  it is the value of a document element
 * @returns true if has content else false- string values.
 */
function isEmpty(fieldValue) {
	return ((fieldValue.trim() == "") || (fieldValue == null)) ? false : true;
}

/**
 * isValidEmailFormat - validate a email address
 * 
 * @returns boolean if  email address is a valid, returns true
 *          else return false
 */
function isValidEmailFormat(email) {				
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    if (reg.test(email) == false) 
    {            
        return false;
    }
    return true;		
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