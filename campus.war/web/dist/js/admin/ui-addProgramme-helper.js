/**
 * 20170202 DJ c138-add-basic-programme-MP-dj  Load  view form for Program details
 * 20170202 DJ c138-add-basic-programme-MP-dj  Initiated populateProgrammeAddView() method
 * 20170206 DJ c138-add-basic-programme-MP-dj  Initiated isEmpty(),isValidEmailFormat() methods.
 * 20170207 DJ c138-add-basic-programme-MP-dj  clearParameters() method implementation.
 * 20170213 DJ c138-add-basic-programme-MP-dj  remove error message of input fields on focusout.
 * 20170213 DJ c138-add-basic-programme-MP-dj  Implemented clearErrorMessage() common method.
 * 20170214 DJ c138-add-basic-programme-MP-dj  Implemented validateFormData() to validate input data.
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
	 * remove error message of commencement date on focusout
	 */		
	$('#commencement-date').on('focusout', function(event) {	
		clearErrorMessage(".block-course-commencement");
	});	
	
	
	/*
	 * remove error message of expiration date on focusout
	 */		
	$('#expiration-date').on('focusout', function(event) {	
		clearErrorMessage(".block-course-expiration");
	});
	
	/*
	 * remove error message of class type on focusout
	 */		
	$('#classTypelist').on('focusout', function(event) {	
		clearErrorMessage(".block-class-type");
	});		
	
});

/*
 * Clear error messages in mandatory input fields on focusout
*/
function clearErrorMessage(blockName){	
	$("#programmeForm").addClass("error-form");
	$(blockName).removeClass("err-block");
	$(blockName).addClass("success-block");	
}


/*
 * Populate program insertion view 
*/
function populateProgrammeAddView(response) {
	
//Loading course provider drop down list
	if (response.result !== undefined && response.result && response.result.length > 0) {
		var htmlstr = "";
		$.each(response.result, function(index, value) {
			if (value != null && value.length > 0) {
				htmlstr += '<option val="' + value[0] + '">' + value[1]
						+ '</option>';
			}

		});
		$('#providerName').html(htmlstr);
	} else {
		$("#programmeForm").addClass("error-form");
		$(".block-course-provider").addClass("err-block");
		$('.block-course-provider .err-msg').text("No course provider master data!");
	}	
	

	//Loading category drop down list
	if (response.allCategories !== undefined && response.allCategories	&& response.allCategories.length > 0) {
		var htmlCategoryStr = "";
		$.each(response.allCategories, function(index, value) {
			if (value != null && value.length > 0) {
				htmlCategoryStr += '<option val="' + value[0] + '">' + value[1]
						+ '</option>';
			}

		});
		$('#categoryName').html(htmlCategoryStr);
	} else {
		$("#programmeForm").addClass("error-form");
		$(".block-course-category").addClass("err-block");
		$('.block-course-category .err-msg').text("No course category master data!");
	}
	

	// Loading major drop down list
	if (response.allMajors !== undefined && response.allMajors	&& response.allMajors.length > 0) {
		var htmlMajorStr = "";
		$.each(response.allMajors, function(index, value) {
			if (value != null && value.length > 0) {
				htmlMajorStr += '<option val="' + value[0] + '">' + value[1]
						+ '</option>';
			}
		});

		$('#majorName').html(htmlMajorStr);

	} else {
		$("#programmeForm").addClass("error-form");
		$(".block-course-major").addClass("err-block");
		$('.block-course-major .err-msg').text("No course major master data!");
	}
	
	

		// Loading level drop down list
	if (response.allLevels !== undefined && response.allLevels	&& response.allLevels.length > 0) {
		var htmlLevelStr = "";
		$.each(response.allLevels, function(index, value) {
			if (value != null && value.length > 0) {
				htmlLevelStr += '<option val="' + value[0] + '">' + value[1]
						+ '</option>';
			}

		});
		$('#levelName').html(htmlLevelStr);
	} else {
		$("#programmeForm").addClass("error-form");
		$(".block-course-level").addClass("err-block");
		$('.block-course-level .err-msg').text("No course level master data!");
	}
	
	

	// Loading class type drop down list
	if (isNotEmptyCollection(response.allClassTypes )) {
		var htmlClassTypeStr = "";
		$.each(response.allClassTypes, function(index, value) {
			if (value != null && value.length > 0) {
				htmlClassTypeStr += '<option val="' + value[0] + '">'
						+ value[1] + '</option>';
			}
		});
		$('#classTypeName').html(htmlClassTypeStr);
	} else {
		$("#programmeForm").addClass("error-form");
		$(".block-class-type").addClass("err-block");
		$('.block-class-type .err-msg').text("No class type master data!");
	}
	
}

/**
 * This method addProgramme() for adding a course to the system.
 */
function addProgrammeDetails(){	
	//Front-end validations
	var isValidationSuccess=validateFormData();	
	//After pass the validations developer able to add input data to database.
	if (isValidationSuccess === true) {
		var form = $('#programmeForm');
		var formData = $(form).serialize();
		
		$.ajax({
			url : '../../AdminController',
			type: 'POST',
			data :formData ,				
			dataType : "json",
			async : false,
			success : function(response) {					
					if (response.successMessage === "success") {
						alert("Added Programme details dispay will be implemented by another issue.In order to test added data please check the database");
					}else{
						displayBackEndValidations(response);
					}				
			},
			error : function(jqXHR, exception) {			
				errorCodeGeneration(jqXHR, exception);
			}
		});	
		
	}	
}

/*
 * Method displayBackEndValidations() is to display server side validation messages.
*/
function displayBackEndValidations(response){
	
	if(isEmpty(response.errorCategory)){
		generateServerValidationMessage(response.errorCategory,".block-course-category");		
	}
	
	if(isEmpty(response.errorMajor)){		
		generateServerValidationMessage(response.errorMajor,".block-course-major");					
	}
	
	if(isEmpty(response.errorLevel)){		
		generateServerValidationMessage(response.errorLevel,".block-course-level");				
	}
	
	if(isEmpty(response.errorCourseProvider)){
		generateServerValidationMessage(response.errorCourseProvider,".block-course-provider");					
	}
	
	if(isEmpty(response.errorCourseName)){
		generateServerValidationMessage(response.errorCourseName,".block-course-name");					
	}
	
	if(isEmpty(response.errorcounselorName)){
		generateServerValidationMessage(response.errorcounselorName,".block-counselor-name");					
	}
	
	if(isEmpty(response.errorcounselorTel)){
		generateServerValidationMessage(response.errorcounselorTel,".block-counselor-tel");				
	}
	
	if(isEmpty(response.errorcounselorEmail)){
	
		generateServerValidationMessage(response.errorcounselorEmail,".block-counselor-email");			
	}
	if(isEmpty(response.errorCourseDuration)){
		generateServerValidationMessage(response.errorCourseDuration,".block-course-duration");			
	}
	
	if(isEmpty(response.errorcommencementDate)){
		generateServerValidationMessage(response.errorcommencementDate,".block-course-commencement");				
	}
	
	if(isEmpty(response.errorexpirationDate)){
		generateServerValidationMessage(response.errorexpirationDate,".block-course-expiration");			
	}

}

/**
 * This method generateServerValidationMessage() for displaying back end validation messages.
 */
function generateServerValidationMessage(message,block){
	$("#programmeForm").addClass("error-form");
	$(block).addClass("err-block");
	$(block).find(".err-msg").html(message);	
}

/**
 * This method validateFormData() for validating input data -front end validations.
 */
function validateFormData(){	

	var isValidationSucess = true;
	var courseDuration = $('#course-duration').val();
	var counselorName = $('#counselor-name').val();
	var counselorTel = $('#counselor-tel').val();
	var counselorEmail = $('#counselor-email').val();
	var courseName = $('#course-name').val();
	var integerPattern = /^[0-9]+$/;	

	if (isEmpty(courseDuration)) {
		$("#programmeForm").addClass("error-form");
		$(".block-course-duration").addClass("err-block");
		$('.block-course-duration .err-msg').text("Please insert course duration !");
		isValidationSucess = false;
	}
	
	if (isEmpty(courseName) || !isValidLength(counselorTel, 100)) {
		$("#programmeForm").addClass("error-form");
		$(".block-course-name").addClass("err-block");
		$('.block-course-name .err-msg').text("Course Name  is empty or too long!!");
		isValidationSucess = false;
	}
	
	if (isEmpty(counselorName) || !isValidLength(counselorName, 35)) {
		$("#programmeForm").addClass("error-form");
		$(".block-counselor-name").addClass("err-block");
		$('.block-counselor-name .err-msg').text("Counselor name is empty or too long!");
		isValidationSucess = false;
	}
	
	if (isEmpty(counselorTel) || !isValidLength(counselorTel, 15)) {
		$("#programmeForm").addClass("error-form");
		$(".block-counselor-tel").addClass("err-block");
		$('.block-counselor-tel .err-msg').text("Counselor telephone is empty or too long!");
		isValidationSucess = false;
	}else if(!isPatternMatch(integerPattern,counselorTel)){
		$("#programmeForm").addClass("error-form");
		$(".block-counselor-tel").addClass("err-block");
		$('.block-counselor-tel .err-msg').text("Please insert valid format of counselor telephone!");
		isValidationSucess = false;
	}	

	if (isEmpty(counselorEmail)) {
		$("#programmeForm").addClass("error-form");
		$(".block-counselor-email").addClass("err-block");
		$('.block-counselor-email .err-msg').text("Please insert counselor Email address!");
		isValidationSucess = false;
	}else if(!isValidEmailFormat(counselorEmail)){
		$("#programmeForm").addClass("error-form");
		$(".block-counselor-email").addClass("err-block");
		$('.block-counselor-email .err-msg').text("Invalid email format!");	
		isValidationSucess = false;
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
		isValidationSucess = false;
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
		isValidationSucess = false;
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
		isValidationSucess = false;
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
		isValidationSucess = false;
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
		
	/*//Validate email
	var email= $('#counselor-email').val();			
	if (isEmpty(email) || !isValidEmailFormat(email)) {
		$("#programmeForm").addClass("error-form");
		$(".block-counselor-email").addClass("err-block");
		$('.block-counselor-email .err-msg').text("Invalid email format!");
		isValidationSucess = false;		
	}*/ 

	//Validate dates
	var regex=new RegExp("([0-9]{4}[-](0[1-9]|1[0-2])[-]([0-2]{1}[0-9]{1}|3[0-1]{1})|([0-2]{1}[0-9]{1}|3[0-1]{1})[-](0[1-9]|1[0-2])[-][0-9]{4})");	
	var commencementDate= $("#commencement-date").val();
    var expirationDate= $("#expiration-date").val();   	
    
    if(!regex.test(commencementDate)){ 		
    	$("#programmeForm").addClass("error-form");
		$(".block-course-commencement").addClass("err-block");			
		$('.block-course-commencement .err-msg').text("Please enter valid commencement Date!");	
		isValidationSucess = false;
    }
    
    if(!regex.test(expirationDate)){
    	$("#programmeForm").addClass("error-form");
		$(".block-course-expiration").addClass("err-block");			
		$('.block-course-expiration .err-msg').text("Please enter valid expiration Date!");	
		isValidationSucess = false;
    }   
    
	if((regex.test(commencementDate) && regex.test(expirationDate)) && commencementDate > expirationDate){	
		$("#programmeForm").addClass("error-form");
		$(".block-course-expiration, .block-course-expiration").addClass("err-block");			
		$('.block-course-expiration .err-msg').text("Invalid Date Range! From Date cannot be after To Date!");	
		isValidationSucess = false;
	}	
	return isValidationSucess;	
}

/**
 * @param regularExpression
 *            pattern
 * @param source
 *            content to act as the source to be matched against the pattern
 * @returns boolean if matches true else false
 */
function isPatternMatch(regularExpression, source) {
	return regularExpression.test(source);

}

/**
 * @author DJ
 * @param fieldValue;  it is the value of a document element
 * @returns false if has content else true- string values.
 */
function isEmpty(fieldValue) {
	return ((fieldValue == "") || (fieldValue == null)) ?true  : false;
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
 * @author DJ
 * @param parameter
 * @param length
 * @returns boolean true if length is valid else falses
 */
function isValidLength(parameter, length) {
	return (parameter.length <= length) ? true : false;
}

/**
 * @author DJ
 * @param responseCollection Collection of objects  
 * @returns boolean true if collection is not empty else falses
 */
function isNotEmptyCollection(responseCollection){
	if(responseCollection !== undefined && responseCollection	&& responseCollection.length > 0){
		return true;
	}else {
		return false;
	}
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