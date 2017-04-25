/**
 *20161027 AS C8-inquiry-form-for-course validation.js  created.
 *20161027 DN c10-contacting-us-page amended isValidEmailFormat(),isValidPhoneNumber() method
 *20161109 DN c10-contacting-us-page-MP validateForm() refactor the method
 *20161109 DN c10-contacting-us-page-MP refactor clearField() the method
 *20161116 DN c10-contacting-us-page-MP changed the method isFieldFilled() to write content to inner HTML
 *20161116 DN c10-contacting-us-page-MP included clearField() method 
 *20161122 DN c10-contacting-us-page-MP isValidPhoneNumber() regular expression changed to 
 *           cater more phone number styles
 * 20161123 DN c10-contacting-us-page-MP isValidPhoneNumber() regular expression changed to 
 *cater more phone number styles with spaces in between.
 *20161123 DN c10-contacting-us-page-MP  changed the regular expression to accept only +(2 digit)(9-digit)
 *20161128 DN c10-contacting-us-page-MP isempty() changed to validate any field submitting spaces.
 *20161202 DN C18-student-signup-without-using-third-party-application-test-dn add isStringHasValiCharsAndLength() method
 *20170118 DN C18-student-signup-without-using-third-party-application-test-dn isStringHasValiCharsAndLength() signature has been changed it takes
 *           a field value and a regular expression as the argument
 *20170118 DN C18-student-signup-without-using-third-party-application-test-dn isStringHasValiCharsAndLength() changed the method header comment.
 *20170207 DN C18-student-signup-without-using-third-party-application-test-dn isStringHasValiCharsAndLength() has been modified
 *				 in such a way that the testable string is process by removing any spaces within 
 *20170227 DN c131-admin-manage-banner-upload-banner-image-dn urlTest() implemented for url validations
 *20170228 DN c131-admin-manage-banner-upload-banner-image-dn  changed urlTest() method.
 *20170303 DN c131-admin-manage-banner-upload-banner-image-dn shift the compareDates() method from
 *            /campus.war/web/dist/js/banner/uploadBanner.js
 *            compareDates() refactored to prevent an error
 *20170306 DN c131-admin-manage-banner-upload-banner-image-dn changed the method isValidEmailFormat to enclose the regular expression with ^ and $ 
 *			 negate the !isPatternMatch(urltestingPattern,urlSplitedArray[1]) in urlTest() method.	
 *20170315 DN c81-admin-manage-banner-add-and-view-banner-dn isDate() method has been implemented. compareDates() doc commented re structured 
 *20170321 DN c131-admin-manage-banner-upload-banner-image-dn urlTest() method an the comment amended. add \/{0,1} to the end of regex.
 *20170405 DN c83-admin-manage-banner-update-banner-info-dn isStringHasValiCharsAndLength() has modified to change the OR(|) test of 'testableInput' to AND(&&).
 *20170425 DN c88-admin-manage-advertiser-add-new-advertiser-dn. The method ajaxCallErorMessage(response,error,errorThrown) is implemented 
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
 * @param fieldValue it is the value of a document element
 * method avoid if a string with spaces are passed in
 * @returns true if has content else false
 */
function isempty(fieldValue) {

	return ((fieldValue.trim() == "") || (fieldValue == null)) ? false : true;
}

/**
 * isValidEmailFormat method validate a email address
 * 
 * @returns boolean if testing email address is a valid one then returns true
 *          else return false
 */
function isValidEmailFormat(email) {
	var emailAddress = email;
	var pattern = /^([\w-\.]+)@((?:[\w]+\.)+)([a-zA-Z]{2,4})$/g;
	return isPatternMatch(pattern, emailAddress);
}

/**
 * isValidPhoneNumber validates if the phone numbers are in correct format<br>
 * +94(9-digit number) eg. +94123456789,+94 123 456 789,<br>
 * 0094(9-digit number)e.g 0094123456789 0094 123 456 789 <br>
 * 777453052 (without the leading zero including spaces within the number<br>
 * 0777453052 (with leading  zero and 9 digit number)<br>
 * <b> if the State rule changes in a such a way that <i>the number of digits </i>for local telephone number to be changed<b>
 * then validation fails
 * @author dushantha DN
 * @param numberToValidate Telephone number to be validated
 * @return boolean if passes true else false
 */
function isValidPhoneNumber(phoneNumber) {
	var isValidPhoneNumber = false;
	if((phoneNumber!=null) | (phoneNumber!="")){
		var phonenumberPattern = /^(00\d{2}|\+\d{2}|0)?\d{9}$/mg;
		isValidPhoneNumber = isPatternMatch(phonenumberPattern, phoneNumber.replace(/\s+/g, ""));
	}
	return isValidPhoneNumber;
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
 * method tests if the supply string consists of alpha numeric characters which is
 * 62 case-sensitive characters (A-Z, a-z and 0-9)and "_" character in such a combination that
 * the string contains more than n number of characters defined by regular expression,
 * starts with an alphabetic contains any combination of 
 * alphanumeric and _. Further testableInput should not contains any special characters such as "@,#%$" etc.
 * Function removes any spaces within testableInput and trims it before performs the test
 * @author dushantha DN
 * @param testableInput the sting which is to tested to confirm if it abides the above precondition
 * @param regex regular expression against which the testableInput will be matched.
 * @returns boolean : true if conditions are met else false.
 */
function isStringHasValiCharsAndLength(testableInput, regex){
	var validCharAndLength= false;
	if(testableInput!="" && testableInput!=null){
		var testableRegularExpression =regex ;
		validCharAndLength= isPatternMatch(testableRegularExpression,testableInput.trim().replace(/\s+/g, '')); 
	}
	return validCharAndLength;
}


/**
 * this method test if the url entered is a correct one,
 * test is minimally conducted as the url validation involves complex
 * considerations.
 * this method tests bellow formats of URL with https/http/ftp<br>
 * e.g<br>
 * https://www.google.lk/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=regex*<br>
 * https://www.google.lk<br>
 * www.google.lk<br><br>
 * <b>NOTE</b>:<br>
 * <b>This method sometimes prompt error for valid urls hence should use with caution </b>
 * but this validation returns false for URLs which contains "/" at the end sooner after the domain is ended.<br>
 * Hence 'https://www.google.lk/' results an invalid url.<br>
 * method does not validate urls having ":" in between e.g http://www.campus.dev:8080<br>
 * 
 * @author dushantha DN
 * @param urlInputTextid : id of the element (input) where the url testable string is inserted
 */
function urlTest(urlInputTextid){
	var validUrl = false;
	var urlInputText = $('#'+urlInputTextid).val();	
	if(urlInputText===""|urlInputText===null){
		return validUrl;
	}
	//urlInputText.replace(/\s+/g, ""); // remove spaces between the context
	var urlPatern =/^(((http(s)?|ftp):\/\/www\.)|((http(s)?|ftp):\/\/))[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z_\/]{2,}?\b(\/([-a-zA-Z0-9‌​@:%_\+.~#=?&\*])+\/{0,1})?$/g;
	

	/* test for characters placed at the beginning
	 * e.g :!@%~
	 * \p{N} matches any kind of numeric character in any script
	 * \p{Sm} matches any mathematical symbol
	 * \p{Z} matches any kind of whitespace or invisible separator
	 * \p{P} matches any kind of punctuation character
	*/
	var urltestingPattern =/^[\p{N}\p{Sm}\p{Z}\p{P} ]+/g;
	var urlStartWWWPattern = /^(www\.)[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z‌​\/]{2,}?\b(\/([-a-zA-Z0-9‌​@:%_\+.~#=?&\*])+)?$/g;
	var urlSplitedArray=null;
	
		if( isPatternMatch(urlPatern,urlInputText)){ 
			urlSplitedArray = urlInputText.split('://');
			
			/*
			 *excluding for  https://56@genesiis.atlassian.net/secure/Dashboard.jspa?selectPageId=11900 
			 *pattern
			 */
			validUrl=!isPatternMatch(urltestingPattern,urlSplitedArray[1]);	
	  } else if(isPatternMatch(urlStartWWWPattern,urlInputText)){  // does url start with 
			urlSplitedArray = urlInputText.split('.');
			validUrl = !isPatternMatch(urltestingPattern,urlSplitedArray[1]);
	 } else {
			validUrl = isPatternMatch(urltestingPattern,urlInputText);
	}
	return validUrl;
}

/**
 * compareDates() accept two string dates which separated by given 
 * delimiter , compares those and returns 
 * date1 > date2 --> 1 <br>
 * date1 == date2 --> 0 <br> 
 * date1 < date2 --> -1 <br>
 * @author dushantha DN
 * @since 2017-03-15
 * @param firstDateString : the client code which uses the method <b>must<br>
 * 							guarantee</b> that the provided String is illegible <br>
 * 							and safe to convert to Date type.<br>
 * 							The first date string to be compare against the secondDateString
 * @param secondDateString :the client code which uses the method <b>must<br>
 * 							guarantee</b> that the provided String is illegible <br>
 * 							and safe to convert to Date type.<br>
 * @returns {Number} : int value
 * 					  date1 > date2 --> 1 <br>
 * 					  date1 == date2 --> 0 <br>
 *                    date1 < date2 --> -1 <br>
 */
function compareDates(firstDateString,secondDateString,delimeter){
	
	var dateSplitArray = firstDateString.split(delimeter); //2017-02-14
	var firstDate = new Date(dateSplitArray[0],(dateSplitArray[1]-1),dateSplitArray[2]);
	dateSplitArray = secondDateString.split(delimeter); 
	var secondDate = new Date(dateSplitArray[0],(dateSplitArray[1]-1),dateSplitArray[2]);
	if(firstDate.getTime()>secondDate.getTime()){
		return 1;
	}else if (firstDate.getTime()<secondDate.getTime()){
		return -1;
	} else {
		return 0;
	}
}

/**
 * isDate function accepts a String value which is to be
 * tested for a valid date.<br>
 * If the provided string is acceptable format of date then the method will<br>
 * return a truth else false<br>
 * @author dushantha DN
 * @param valueToBeTested : must be a string <br>
 * 						Allowable string values are as follows:<br>
 * 						'yyyy-MM-dd' or any valid date format.<br>
 * 						The delimiter can be any printable valid date separator.<br>
 * 						such as "-,/,*,!,@,#,?"
 * 						
 * @since 2017-03-15
 * @returns {Boolean} if the string in question can be safely converted to a date<br>
 * 					 the method returns a true else false
 */
function isDate(valueToBeTested){
	if(typeof valueToBeTested != 'string')
	       return false;
	if( new Date(valueToBeTested)=="Invalid Date"){
        return false;
     } else {
         return true;
     }
	
}

/**
 * ajaxCallErorMessage
 * @author DJ,DN re engineered 
 * @param response response that comes from the server
 * @error error  error message comes from the server when ajax call fails
 * @errorThrown actual error thrown once ajax response fails
 */
function ajaxCallErorMessage(response,error,errorThrown){
	  var msg = '';
  if (response.status === 0) {
      msg = 'The XMLHttpRequest client has been created, but the open() method hasn\'t been called yet.';
  }else{
  	msg = error +": "+errorThrown;
  }
 return msg;
}