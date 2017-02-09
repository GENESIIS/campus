package com.genesiis.campus.validation;

import com.genesiis.campus.util.IDataHelper;

import java.util.ArrayList;

//20170208 DJ c138-add-basic-programme-MP-dj Add FormValidator.java

public class FormValidator {
	
	
	public static ArrayList<String>  validateProgrammeDetails(IDataHelper helper) {
		
		boolean isValid = true;
		ArrayList<String> errorString = new ArrayList<String>();

		if (!UtilityHelper.isNotEmpty(helper.getParameter("categoryName"))) {
			helper.setAttribute("errorCourseProvider",
					"Please select the course provider type");
			isValid = false;
		}
		if (!UtilityHelper.isNotEmpty(helper.getParameter("categoryName"))) {
			helper.setAttribute("errorCourseProvider",
					"Please select the course provider type");
			isValid = false;
		}
		/*majorName
		levelName
		courseName
		providerName
		classTypeName*/
		return errorString;
	}


}
