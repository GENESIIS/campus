/**
 *20161027 AS C8-inquiry-form-for-course validation.js  created.
 *20161027 DN c10-contacting-us-page amended isValidEmailFormat(),isValidPhoneNumber() method
 *20161109 DN c10-contacting-us-page-MP validateForm() refactor the method
 *20161109 DN c10-contacting-us-page-MP refactor clearField() the method
 *20161116 DN c10-contacting-us-page-MP changed the method isFieldFilled() to write content to inner HTML
 *20161116 DN c10-contacting-us-page-MP included clearField() method 
 *20161122 DN c10-contacting-us-page-MP isValidPhoneNumber() regular expression changed to 
 * cater more phone number styles
 * 20161123 DN c10-contacting-us-page-MP isValidPhoneNumber() regular expression changed to 
 *cater more phone number styles with spaces in between.
 *20161123 DN c10-contacting-us-page-MP  changed the regular expression to accept only +(2 digit)(9-digit)
 *20161125 PN (c26-add-student-details: implemented isNumber(evt) method.
 *20161126 PN c26-add-student-details: implemented isDropdownSelected(), isemptyDropdown() and getSelectedData() method.
 *20161129 PN c26-add-student-details: implemented checkDateRange() method to validate date range
 */ 

 

/**
 * isHumanTestPassed(0 is a primary function that 
 * matches the entered value against a hard coded 
 * value.
 * @returns boolean true if the human test passes
 * else false
 */

function isHumanTestPassed(){	
	var response = grecaptcha.getResponse();
	return(response.length!=0)?true:false;
	
	
}

/**
 * isFieldFilled() generate a alert if the passing in 
 * flag is false else the method acts void
 * @param flag expression that evaluates to a boolean
 * @param elementName  string to be append to the produced message
 */

function isFieldFilled(flag, elementName, errorLabelId){		
	if(!flag){	
		document.getElementById(errorLabelId).innerHTML = elementName + " Must Be filled Out Correctly!";		
	}
	return flag;
}

/**
 * 
 * @param fieldValue
 *            it is the value of a document element
 * @returns true if has content else false
 */
function isempty(fieldValue) {

	return ((fieldValue == "") || (fieldValue == null)) ? false : true;
}

/**
 * isValidEmailFormat method validate a email address
 * 
 * @returns boolean if testing email address is a valid one then returns true
 *          else return false
 */
function isValidEmailFormat(email) {
	var emailAddress = email;
	var pattern = /([\w-\.]+)@((?:[\w]+\.)+)([a-zA-Z]{2,4})/g;
	return isPatternMatch(pattern, emailAddress);
}

/**
 * isValidPhoneNumber validates if the phone numbers are in correct format
 * +94(9-digit number) eg. +94123456789,+94 123 456 789,
 * 0094(9-digit number)e.g 0094123456789 0094 123 456 789 
 * 777453052 (without the leading zero including spaces within the number
 * 0777453052 (with leading  zero and 9 digit number)
 * <b> if the State rule changes in a such a way that <i>the number of digits </i>for local telephone number to be changed<b>
 * then validation fails
 * @author dushantha DN
 * @param numberToValidate Telephone number to be validated
 * @return boolean if passes true else false
 */
//function isValidPhoneNumber(phoneNumber) {
//	var isValidPhoneNumber = false;
//	if((phoneNumber!=null) | (phoneNumber!="")){
//		var phonenumberPattern = /^(00\d{2}|\+\d{2}|0)?\d{9}$/mg;
//		isValidPhoneNumber = isPatternMatch(phonenumberPattern, phoneNumber.replace(/\s+/g, ""));
//	}
//	return isValidPhoneNumber;
//}

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
 * @param clearField 
 * @param elementId the id of the HTML element
 */

function clearField(elementId){	
	 $(document).find('#' + elementId).text('');
}


/**
 * isValidEmailFormat method validate a email address
 * @returns boolean if testing email address is a valid
 * one then returns true else return false
 */
function validEmailFormat(){	
	var emailAddress = document.forms["contactUsForm"]["emailAddress"].value;	
	var pattern =/([\w-\.]+)@((?:[\w]+\.)+)([a-zA-Z]{2,4})/g;	
	return isPatternMatch(pattern,emailAddress);
}

/**
 * This method is to set dropdown value to default value.
 * @param selectElement
 * @returns
 */
function resetSelectElement(selectElement) {
	selecElement.selectedIndex = 0; // first option is selected, or
	// -1 for no option selected
}

/**
 * This method allows to type numbers only on text field
 * @param evt
 * @returns
 */
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

/**
 * This method is to get selected checkbox values
 * @param checkboxList
 * @returns
 */
function getValueUsingParentTag(checkboxList){
	var chkArray = [];
	
	/* look for all checkboes that have a parent id called 'checkboxlist' attached to it and check if it was checked */
	$(checkboxList).each(function() {
		chkArray.push($(this).val());
	});
	
	/* we join the array separated by the comma */
	var selected;
	selected = chkArray.join(',');
	
	return selected;
}

/**
 * This method is to get selected data from datalist
 * @param listname
 * @param elementName
 * @returns {String}
 */
function getSelectedData(listname, elementName) {
	var selectedValue = "";
	var val = document.getElementById(listname);

	if (val != null) {
		var data = val.value;
		var opts = document.getElementById(elementName).childNodes;
		for (var i = 0; i < opts.length; i++) {
			if (opts[i].value === data) {
				selectedValue = opts[i].text;
				break;
			}
		}
	}
	return selectedValue;
}

/**
 * 
 * @param fieldValue
 *            it is the value of a document element
 * @returns true if has content else false
 */
function isemptyDropdown(fieldValue) {

	return $('#'+fieldValue+'').val() === '' ? false : true;
}

/**
 * isFieldFilled() generate a alert if the passing in 
 * flag is false else the method acts void
 * @param flag expression that evaluates to a boolean
 * @param elementName  string to be append to the produced message
 */

function isDropdownSelected(flag, elementName, errorLabelId){		
	if(!flag){	
		document.getElementById(errorLabelId).innerHTML = elementName + " Can not be Empty.!";		
	}
	return flag;
}

// checkDateRange - Checks to ensure that the values entered are dates and
// are of a valid range. By this, the dates must be no more than the
// built-in number of days appart.
function checkDateRange(start, end, startDateErrLabelId, endDateErrLabelId) {
	// Parse the entries
	
	
	var startDate = Date.parse(document.getElementById(start).value);
	var endDate = Date.parse(document.getElementById(end).value);
	// Make sure they are valid
	if (isNaN(startDate)) {
		document.getElementById(startDateErrLabelId).innerHTML = "The commenced date provided is not valid, please enter a valid date.";
		return false;
	}
	if (isNaN(endDate)) {
		document.getElementById(endDateErrLabelId).innerHTML = "The completed date provided is not valid, please enter a valid date.";
		return false;
	}
	// Check the date range, 86400000 is the number of milliseconds in one day
	var difference = (endDate - startDate) / (86400000 * 7);
	if (difference < 0) {
		//alert("The start date must come before the end date.");
		document.getElementById(startDateErrLabelId).innerHTML = "The commenced date must come before the completed date.";
		return false;
	}
//	if (difference <= 1) {
//		alert("The range must be at least seven days apart.");
//		return false;
//	}
	return true;
}

