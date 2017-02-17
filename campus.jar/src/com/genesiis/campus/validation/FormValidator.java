package com.genesiis.campus.validation;

//20170208 DJ c138-add-basic-programme-MP-dj Add FormValidator.java
//20170209 DJ c138-add-basic-programme-MP-dj Add validateProgrammeDetails() for back end form validations

import com.genesiis.campus.util.IDataHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FormValidator {
	
	static String dateFormat="yyyy-MM-dd";
	
	public static boolean  validateProgrammeDetails(IDataHelper helper) {
		
		boolean isValid = true;	
		String integerPattern = "^[0-9]*$";
		
		if (!UtilityHelper.isNotEmpty(helper.getParameter("selectedCategory"))) {
			helper.setAttribute("errorCategory",
					"Please select a category type");
			isValid = false;
		}		
		if (!UtilityHelper.isNotEmpty(helper.getParameter("selectedMajor"))) {
			helper.setAttribute("errorMajor",
					"Please select a course major type");
			isValid = false;
		}		
		if (!UtilityHelper.isNotEmpty(helper.getParameter("selectedLevel"))) {
			helper.setAttribute("errorLevel",
					"Please select a course level type");
			isValid = false;
		}		
		if (!UtilityHelper.isNotEmpty(helper.getParameter("selectedProvider"))) {
			helper.setAttribute("errorCourseProvider",
					"Please select a course provider");
			isValid = false;
		}			
		//TODO: kept for future developments DJ
		/*if (!UtilityHelper.isNotEmpty(helper.getParameter("selectedClassType"))) {
			helper.setAttribute("errorCourseProvider",
					"Please select a classe type");
			isValid = false;
		}*/	
		String courseName = helper.getParameter("courseName");
		if (!UtilityHelper.isNotEmpty(courseName) || courseName.length() > 100) {
			helper.setAttribute("errorCourseName", "Course name is empty or too long!");
			isValid = false;
		}
		//TODO: kept for future developments DJ
		/*if (!UtilityHelper.isNotEmpty(helper.getParameter("courseDescription"))) {
			helper.setAttribute("errorCourseProvider",
					"Please add course discription");
			isValid = false;
		}*/
		if (!UtilityHelper.isNotEmpty(helper.getParameter("courseDuration"))) {
			helper.setAttribute("errorCourseDuration",
					"Please add course duratioin");
			isValid = false;
		}
		
		String commencementDate=helper.getParameter("commencementDate");
		String expirationDate=helper.getParameter("expirationDate");
		
		
		if (!UtilityHelper.isNotEmpty(commencementDate)) {
			helper.setAttribute("errorcommencementDate",
					"BACKEND-Please add course commencement date ");
			isValid = false;
		} else if (!isThisDateValid(commencementDate, dateFormat)) {
			helper.setAttribute("errorcommencementDate",
					"BACKEND Commencement date is invalid format");
			isValid = false;
		}
		

		if (!UtilityHelper.isNotEmpty(expirationDate)) {
			helper.setAttribute("errorexpirationDate",
					"BACKEND-Please add course expiration date");
			isValid = false;
		} else if (!isThisDateValid(expirationDate, dateFormat)) {
			helper.setAttribute("errorexpirationDate",
					"BACKEND Expiration date is invalid format");
			isValid = false;
		}
	
	
		if(UtilityHelper.isNotEmpty(commencementDate) && isThisDateValid(commencementDate,dateFormat) && UtilityHelper.isNotEmpty(expirationDate)&& isThisDateValid(expirationDate,dateFormat)){
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setLenient(false);

			try {
				//if not valid, it will throw ParseException
				Date commencement = sdf.parse(commencementDate);
				Date expiration = sdf.parse(expirationDate);
				
				if(expiration.getTime() > commencement.getTime()){
					helper.setAttribute("errorexpirationDate","BACKEND-Invalid Date Range! From Date cannot be after To Date!");
					isValid = false;
				}

			} catch (ParseException e) {				
				isValid = false;
			}
		}
		
		//TODO: kept for future developments DJ
		/*if (!UtilityHelper.isNotEmpty(helper.getParameter("courseStatus"))) {
			helper.setAttribute("errorCourseProvider",
					"Please select programme status");
			isValid = false;
		}*/
		String counselorName = helper.getParameter("counselorName");
		if (!UtilityHelper.isNotEmpty(counselorName) || counselorName.length() > 35) {
			helper.setAttribute("errorcounselorName","Counselor name is empty or too long!");
			isValid = false;
		}
		
		String counselorTel = helper.getParameter("counselorTel");
		
		if (!UtilityHelper.isNotEmpty(counselorTel)	|| counselorTel.length() > 15) {
			helper.setAttribute("errorcounselorTel","Counselor telephone is empty or too long!");
			isValid = false;
		} else if (!isPatternMatch(counselorTel, integerPattern)) {
			helper.setAttribute("errorcounselorTel","Please insert valid format of counselor telephone!");
			isValid = false;
		}
		
		String counselorEmail = helper.getParameter("counselorEmail");
		
		if (!UtilityHelper.isNotEmpty(counselorEmail)) {
			helper.setAttribute("errorcounselorEmail","Please add counselor email address");
			isValid = false;
		}else if(!isValidEmailFormat(counselorEmail)){
			helper.setAttribute("errorcounselorEmail","Invalid email format 8888!");
			isValid = false;
		}
		return isValid;
	}
	
	private static boolean isPatternMatch(String sourceString,String regularExpression) {
		return (sourceString.matches(regularExpression)?true:false);
	}

	
	private static boolean isValidEmailFormat(String email) {				
		String reg = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";		
		return isPatternMatch(email,reg);	    
	}
	
	private static boolean isThisDateValid(String dateToValidate, String dateFromat) {

		/*if(dateToValidate == null){
			return false;
		}*/

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);

		} catch (ParseException e) {
			
			System.out.println("&&&&&&&&&&&&&&&&&&" +e.toString());		
			
			return false;
		}

		return true;
	}

}