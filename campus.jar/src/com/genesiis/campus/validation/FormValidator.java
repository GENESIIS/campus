package com.genesiis.campus.validation;

//20170208 DJ c138-add-basic-programme-MP-dj Add FormValidator.java
//20170209 DJ c138-add-basic-programme-MP-dj Add validateProgrammeDetails() for back end form validations

import com.genesiis.campus.util.IDataHelper;

import java.util.ArrayList;

public class FormValidator {	
	
	public static boolean  validateProgrammeDetails(IDataHelper helper) {
		
		boolean isValid = true;				
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
		if (!UtilityHelper.isNotEmpty(helper.getParameter("courseName"))) {
			helper.setAttribute("errorCourseName",
					"Please add Course name");
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
		if (!UtilityHelper.isNotEmpty(helper.getParameter("commencementDate"))) {
			helper.setAttribute("errorcommencementDate",
					"Please add course commencement date");
			isValid = false;
		}
		if (!UtilityHelper.isNotEmpty(helper.getParameter("expirationDate"))) {
			helper.setAttribute("errorexpirationDate",
					"Please add course expiration date");
			isValid = false;
		}
		//TODO: kept for future developments DJ
		/*if (!UtilityHelper.isNotEmpty(helper.getParameter("courseStatus"))) {
			helper.setAttribute("errorCourseProvider",
					"Please select programme status");
			isValid = false;
		}*/
		if (!UtilityHelper.isNotEmpty(helper.getParameter("counselorName"))) {
			helper.setAttribute("errorcounselorName",
					"Please add counselor name");
			isValid = false;
		}
		
		String counselorTel=helper.getParameter("counselorTel");
		if (!UtilityHelper.isNotEmpty(counselorTel)) {
			helper.setAttribute("errorcounselorTel",
					"Please add counselor phone");
			isValid = false;
		}else{
			//matches numbers only
			String regexStr = "^[0-9]*$";
			if (counselorTel.matches(regexStr)){
				isValid = true;
			}else{
				isValid = false;
			}
			
		}
		if (!UtilityHelper.isNotEmpty(helper.getParameter("counselorEmail"))) {
			helper.setAttribute("errorcounselorEmail",
					"Please add counselor email address");
			isValid = false;
		}		
		return isValid;
	}


}
