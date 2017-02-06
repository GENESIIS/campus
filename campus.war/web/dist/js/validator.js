/**
 * 
 * //20161027 AS C8-inquiry-form-for-course validation.js  created.
 * //20161108 CM  c9-make-inquiry-for-institute added validateInstituteInquiryFileds() function
 * //20161108 CM c9-make-inquiry-for-institute Modified validateInstituteInquiryFileds() function
 * //20161115 CM c9-make-inquiry-for-institute added clearField(elementId) function
 * //20161122 CM c36-add-tutor-information 
 */

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
 * isValidEmailFormat method validate a email address
 * 
 * @returns boolean if testing email address is a valid one then returns true
 *          else return false
 */
function isValidEmailFormat(email) {
	var emailAddress = email;
	var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	return isPatternMatch(pattern, emailAddress);
}
/**
 * Validate URL
 * 
 * @author Chathuri
 * @param str
 */
function ValidURL(str) {
	var regex = /(http|https):\/\/(\w+:{0,1}\w*)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%!\-\/]))?/;
	return regex.test(str);

}

/**
 * validateCourseProvider method used to validate input fields 
 * when creating a new course provider.
 */
function validateCourseProvider(){
	var 
}