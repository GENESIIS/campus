/**
 * 20170202 DJ c138-add-basic-programme-MP-dj  Load  view form for Program details
 * 20170202 DJ c138-add-basic-programme-MP-dj  Initiated populateProgrammeAddView() method
 * 20170206 DJ c138-add-basic-programme-MP-dj  Initiated isEmpty(),isValidEmailFormat() methods.
 * 20170207 DJ c138-add-basic-programme-MP-dj  clearParameters() method implementation.
 * 20170213 DJ c138-add-basic-programme-MP-dj  remove error message of input fields on focusout.
 * 20170213 DJ c138-add-basic-programme-MP-dj  Implemented clearErrorMessage() common method.
 */

$(document).ready(function() {
	
	
	$.ajax({
		url : '../../AdminController',
		type: 'POST',
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
	/*$('#addProgramme').click(function(event){		
		addProgramme(event);		
	});*/	

	/*
	 * validate toDate>from date
	 */	
	$('#expiration-date').on('click', function(event) {		
		var fromDate= $('#commencement-date').val();		
		document.getElementById("expiration-date").setAttribute("min", fromDate);	
	});
	
	/*
	 * remove error message of category field on focusout
	 */		
	$('#categoryList').on('focusout', function(event) {		
		clearErrorMessage(".block-course-category");
	});
	/*
	 * remove error message of major field on focusout
	 */		
	$('#majorList').on('focusout', function(event) {		
		clearErrorMessage(".block-course-major");
	});
	
	/*
	 * remove error message of level field on focusout
	 */		
	$('#levelList').on('focusout', function(event) {	
	
		clearErrorMessage(".block-course-level");
	});
	
	/*
	 * remove error message of provider field on focusout
	 */		
	$('#providerList').on('focusout', function(event) {		
		clearErrorMessage(".block-course-provider");
	});
	
	
	/*
	 * remove error message of course name field on focusout
	 */	
	$('#course-name').on('focusout', function(event) {		
		clearErrorMessage(".block-course-name");
	});
	
	/*
	 * remove error message of course duration on focusout
	 */		
	$('#course-duration').on('focusout', function(event) {			
		clearErrorMessage(".block-course-duration");
	});
	
	/*
	 * remove error message of counselor name on focusout
	 */		
	$('#counselor-name').on('focusout', function(event) {			
		clearErrorMessage(".block-counselor-name");
	});
	
	/*
	 * remove error message of counselor telephone on focusout
	 */		
	$('#counselor-tel').on('focusout', function(event) {		
		clearErrorMessage(".block-counselor-tel");
	});	
	
	/*
	 * remove error message of counselor email on focusout
	 */		
	$('#counselor-email').on('focusout', function(event) {	
		clearErrorMessage(".block-counselor-email");	
	});		
	
	/*
	 * clear button-event handler
	 */
	$('#clearParam').click(function(event){		
		clearParameters(event);
	});	
});

//Clear error messages in mandatory input fields on focusout
function clearErrorMessage(blockName){	
	$("#programmeForm").addClass("error-form");
	$(blockName).removeClass("err-block");
	$(blockName).addClass("success-block");	
}

function populateProgrammeAddView(response) {
		
	var htmlstr = "";
	$.each(response.result, function(index, value) {
		if (value != null && value.length > 0) {
			htmlstr += '<option val="' + value[0] + '">' + value[1]
					+ '</option>';
		}

	});
	$('#providerName').html(htmlstr);
	
	
/*	
	var singleTypeElement = '';

	singleTypeElement += '<select id="selectedProviderType" name="selectedProviderType" ><option value="">--Default--</option>';
	//if (providerTypeCollection !== undefined & providerTypeCollection !== null) {
		$.each(response.result, function(index, value) {
			singleTypeElement += '<option val="' + value[0] + '">';
			singleTypeElement += value[1];
			singleTypeElement += '</option>';

		});
	//}
	singleTypeElement += '';
	var providerTypeNames = $("#providerName");
	providerTypeNames.html(singleTypeElement);*/
	
	
	
	//$$$$$$$$$$$$$$$$$$$$$$$$
	
	
	
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
function addProgrammeDetails(){	
	var courseDuration =$('#course-duration').val();	
	var counselorName =$('#counselor-name').val();
	var counselorTel =$('#counselor-tel').val();
	var counselorEmail =$('#counselor-email').val();
	var courseName=$('#course-name').val();
	

	if (courseDuration == 'undefined' || !courseDuration && courseDuration == "") {
		$("#programmeForm").addClass("error-form");
		$(".block-course-duration").addClass("err-block");
		$('.block-course-duration .err-msg').text("Please insert course duration !");
	}
	if (courseName == 'undefined' || !courseName && courseName == "") {
		$("#programmeForm").addClass("error-form");
		$(".block-course-name").addClass("err-block");
		$('.block-course-name .err-msg').text("Please insert course Name!");
	}
	if (counselorName == 'undefined' || !counselorName && counselorName == "") {
		$("#programmeForm").addClass("error-form");
		$(".block-counselor-name").addClass("err-block");
		$('.block-counselor-name .err-msg').text("Please insert counselor name!");
	}
	if (counselorTel == 'undefined' || !counselorTel && counselorTel == "") {
		$("#programmeForm").addClass("error-form");
		$(".block-counselor-tel").addClass("err-block");
		$('.block-counselor-tel .err-msg').text("Please insert counselor telephone!");
	}
	if (counselorEmail == 'undefined' || !counselorEmail && counselorEmail == "") {
		$("#programmeForm").addClass("error-form");
		$(".block-counselor-email").addClass("err-block");
		$('.block-counselor-email .err-msg').text("Please insert counselor Email address!");
	}else if(!isValidEmailFormat(counselorEmail)){
		$("#programmeForm").addClass("error-form");
		$(".block-counselor-email").addClass("err-block");
		$('.block-counselor-email .err-msg').text("Invalid email format!");	
	}
	
	
	
	//Validate Course Provider selection
	var providerName = $('#providerList').val();	
	var providerCode=0;
	var option=$('#providerName').find('option');
	for(var i=0; i<option.length;i++){
		$('#providerName').find('option')[i].outerHTML;
		if(option[i].text ==providerName){				
			providerCode=option[i].attributes[0].value;
			break;
		}
	}	
	if(providerCode==0){
		$("#programmeForm").addClass("error-form");
		$(".block-course-provider").addClass("err-block");
		$('.block-course-provider .err-msg').text("Please select a course provider!");	
	}else{		
		$("#selectedProvider").val(providerCode);
	}
	
	
	//Validate Category selection
	var categoryName = $('#categoryList').val();
	var categoryCode=0;
	var option=$('#categoryName').find('option');
	for(var i=0; i<option.length;i++){
		$('#categoryName').find('option')[i].outerHTML;
		if(option[i].text ==categoryName){				
			categoryCode=option[i].attributes[0].value;
			break;
		}
	}	
	if(categoryCode==0){
		$("#programmeForm").addClass("error-form");
		$(".block-course-category").addClass("err-block");
		$('.block-course-category .err-msg').text("Please select a course category!");	
	}else{		
		$("#selectedCategory").val(categoryCode);
	}
	
	//Validate Major selection
	var majorName = $('#majorList').val();
	var majorCode=0;
	var option=$('#majorName').find('option');
	for(var i=0; i<option.length;i++){
		$('#majorName').find('option')[i].outerHTML;
		if(option[i].text ==majorName){				
			majorCode=option[i].attributes[0].value;
			break;
		}
	}	
	if(majorCode==0){
		$("#programmeForm").addClass("error-form");
		$(".block-course-major").addClass("err-block");
		$('.block-course-major .err-msg').text("Please select a course major!");	
	}else{		
		$("#selectedMajor").val(majorCode);
	}
	
	//Validate Level selection
	var levelName = $('#levelList').val();
	var levelCode=0;
	var option=$('#levelName').find('option');
	for(var i=0; i<option.length;i++){
		$('#levelName').find('option')[i].outerHTML;
		if(option[i].text ==levelName){				
			levelCode=option[i].attributes[0].value;
			break;
		}
	}	
	if(levelCode==0){
		$("#programmeForm").addClass("error-form");
		$(".block-course-level").addClass("err-block");
		$('.block-course-level .err-msg').text("Please select a course level!");	
	}else{		
		$("#selectedLevel").val(levelCode);
	}
	
	//Validate Class type selection
	var classTypeName = $('#classTypelist').val();
	var classTypeCode=0;
	var option=$('#classTypeName').find('option');
	for(var i=0; i<option.length;i++){
		$('#classTypeName').find('option')[i].outerHTML;
		if(option[i].text ==classTypeName){				
			classTypeCode=option[i].attributes[0].value;
			break;
		}
	}	
	if(classTypeCode>0){				
		$("#selectedClassType").val(classTypeCode);
	}
		
	
	var email= $('#email').val();			
	/*if (!isEmpty(email) || !isValidEmailFormat(email)) {
		document.getElementById('errorEmail').innerHTML = "Invalid email format";
		document.getElementById('errorEmail').style.color = "red";
	}*/	
	
   /* var regex=new RegExp("([0-9]{4}[-](0[1-9]|1[0-2])[-]([0-2]{1}[0-9]{1}|3[0-1]{1})|([0-2]{1}[0-9]{1}|3[0-1]{1})[-](0[1-9]|1[0-2])[-][0-9]{4})");
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
	}*/
	var fromDate= $("#commencement-date").val();
    var toDate= $("#expiration-date").val();
     
	if(fromDate > toDate){	
		$("#programmeForm").addClass("error-form");
		$(".block-course-commencement, .block-course-expiration").addClass("err-block");
		//$('.block-course-commencement .err-msg').text("Invalid Date Range! From Date cannot be after To Date!");	
		$('.block-course-expiration .err-msg').text("Invalid Date Range! From Date cannot be after To Date!");		
	}
	
	//After pass the validations developer able to add input data to database.
	
	var form = $('#programmeForm');
	var formData = $(form).serialize();
	
	$.ajax({
		url : '../../AdminController',
		type: 'POST',
		data :formData ,
			
		dataType : "json",
		async : false,
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
	$('#providerList').val(""); 
	$('#courseName').val(" "); 
	$('#courseDetails').val(" "); 
	$('#email').val(" "); 
	$('#courseDuration').val(" "); 
	$('#categoryList').val(""); 
	$('#majorList').val(""); 
	$('#levelList').val(""); 
	$('#classTypelist').val(""); 
	$('#counselorName').val(" "); 
	$('#counselorPhone').val(" "); 
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