package com.genesiis.campus.validation;

//20170208 DJ c138-add-basic-programme-MP-dj Add FormValidator.java
//20170209 DJ c138-add-basic-programme-MP-dj Add validateProgrammeDetails() for back end form validations

import com.genesiis.campus.util.IDataHelper;

import java.util.ArrayList;

public class FormValidator {	
	
	public static ArrayList<String>  validateProgrammeDetails(IDataHelper helper) {
		
		boolean isValid = true;
		ArrayList<String> errorString = new ArrayList<String>();

		if (!UtilityHelper.isNotEmpty(helper.getParameter("categoryName"))) {
			helper.setAttribute("errorCourseProvider",
					"Please select the category type");
			isValid = false;
		}		
		if (!UtilityHelper.isNotEmpty(helper.getParameter("majorName"))) {
			helper.setAttribute("errorCourseProvider",
					"Please select the category type");
			isValid = false;
		}		
		if (!UtilityHelper.isNotEmpty(helper.getParameter("levelName"))) {
			helper.setAttribute("errorCourseProvider",
					"Please select the category type");
			isValid = false;
		}		
		if (!UtilityHelper.isNotEmpty(helper.getParameter("providerName"))) {
			helper.setAttribute("errorCourseProvider",
					"Please select the category type");
			isValid = false;
		}		
		if (!UtilityHelper.isNotEmpty(helper.getParameter("classTypeName"))) {
			helper.setAttribute("errorCourseProvider",
					"Please select the category type");
			isValid = false;
		}		
		if (!UtilityHelper.isNotEmpty(helper.getParameter("courseName"))) {
			helper.setAttribute("errorCourseProvider",
					"Please add Course name");
			isValid = false;
		}
		if (!UtilityHelper.isNotEmpty(helper.getParameter("courseDescription"))) {
			helper.setAttribute("errorCourseProvider",
					"Please add course discription");
			isValid = false;
		}
		if (!UtilityHelper.isNotEmpty(helper.getParameter("courseDuration"))) {
			helper.setAttribute("errorCourseProvider",
					"Please add course duratioin");
			isValid = false;
		}
		if (!UtilityHelper.isNotEmpty(helper.getParameter("commencementDate"))) {
			helper.setAttribute("errorCourseProvider",
					"Please add course duratioin");
			isValid = false;
		}
		if (!UtilityHelper.isNotEmpty(helper.getParameter("expirationDate"))) {
			helper.setAttribute("errorCourseProvider",
					"Please add course duratioin");
			isValid = false;
		}
		if (!UtilityHelper.isNotEmpty(helper.getParameter("courseStatus"))) {
			helper.setAttribute("errorCourseProvider",
					"Please add course duratioin");
			isValid = false;
		}
		if (!UtilityHelper.isNotEmpty(helper.getParameter("counselorName"))) {
			helper.setAttribute("errorCourseProvider",
					"Please add course duratioin");
			isValid = false;
		}
		if (!UtilityHelper.isNotEmpty(helper.getParameter("counselorTel"))) {
			helper.setAttribute("errorCourseProvider",
					"Please add course duratioin");
			isValid = false;
		}
		if (!UtilityHelper.isNotEmpty(helper.getParameter("counselorEmail"))) {
			helper.setAttribute("errorCourseProvider",
					"Please add course duratioin");
			isValid = false;
		}		
		return errorString;
	}


}
