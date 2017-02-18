package com.genesiis.campus.validation;

//20170208 DJ c138-add-basic-programme-MP-dj Add FormValidator.java
//20170209 DJ c138-add-basic-programme-MP-dj Add validateProgrammeDetails() for back end form validations

import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class FormValidator {
	
	static Logger log = Logger.getLogger(FormValidator.class.getName());	
	
	public static boolean validateProgrammeDetails(IDataHelper helper)
			throws Exception {

		boolean isValid = true;

		try {
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
			
			// TODO: kept for future developments DJ
			/*
			 * if
			 * (!UtilityHelper.isNotEmpty(helper.getParameter("selectedClassType"
			 * ))) { helper.setAttribute("errorCourseProvider",
			 * "Please select a classe type"); isValid = false; }
			 */
			
			String courseName = helper.getParameter("courseName");
			if (!UtilityHelper.isNotEmpty(courseName) || courseName.length() > 100) {
				helper.setAttribute("errorCourseName",
						"Course name is empty or too long!");
				isValid = false;
			}
			
			// TODO: kept for future developments DJ
			/*
			 * if
			 * (!UtilityHelper.isNotEmpty(helper.getParameter("courseDescription"
			 * ))) { helper.setAttribute("errorCourseProvider",
			 * "Please add course discription"); isValid = false; }
			 */
			
			if (!UtilityHelper.isNotEmpty(helper.getParameter("courseDuration"))) {
				helper.setAttribute("errorCourseDuration",
						"Please add course duratioin");
				isValid = false;
			}

			String commencementDate = helper.getParameter("commencementDate");
			String expirationDate = helper.getParameter("expirationDate");

			if (!UtilityHelper.isNotEmpty(commencementDate)	|| !UtilityHelper.isThisDateValid(commencementDate)) {
				helper.setAttribute("errorcommencementDate",
						"Please enter valid commencement Date! ");
				isValid = false;
			}

			if (!UtilityHelper.isNotEmpty(expirationDate) || !UtilityHelper.isThisDateValid(expirationDate)) {
				helper.setAttribute("errorexpirationDate",
						"Please enter valid expiration Date!");
				isValid = false;
			}

			if (UtilityHelper.isNotEmpty(commencementDate)
					&& UtilityHelper.isThisDateValid(commencementDate)
					&& UtilityHelper.isNotEmpty(expirationDate)
					&& UtilityHelper.isThisDateValid(expirationDate)) {

				boolean isFromDateAfterTodate = UtilityHelper.isFromDateAfterTodate(commencementDate, expirationDate);

				if (isFromDateAfterTodate) {
					helper.setAttribute(
							"errorexpirationDate",
							"Invalid Date Range! Commencement Date cannot be after Expiration Date!");
					isValid = false;
				}

			}

			// TODO: kept for future developments DJ
			/*
			 * if
			 * (!UtilityHelper.isNotEmpty(helper.getParameter("courseStatus")))
			 * { helper.setAttribute("errorCourseProvider",
			 * "Please select programme status"); isValid = false; }
			 */
			
			String counselorName = helper.getParameter("counselorName");
			if (!UtilityHelper.isNotEmpty(counselorName) || counselorName.length() > 35) {
				helper.setAttribute("errorcounselorName",
						"Counselor name is empty or too long!");
				isValid = false;
			}

			String counselorTel = helper.getParameter("counselorTel");

			if (!UtilityHelper.isNotEmpty(counselorTel)	|| counselorTel.length() > 15) {
				helper.setAttribute("errorcounselorTel",
						"Counselor telephone is empty or too long!");
				isValid = false;
			} else if (!UtilityHelper.isPatternMatch(counselorTel, integerPattern)) {
				helper.setAttribute("errorcounselorTel",
						"Please insert valid format of counselor telephone!");
				isValid = false;
			}

			String counselorEmail = helper.getParameter("counselorEmail");

			if (!UtilityHelper.isNotEmpty(counselorEmail)) {
				helper.setAttribute("errorcounselorEmail",	"Please add counselor email address");
				isValid = false;
			} else if (!UtilityHelper.isValidEmailFormat(counselorEmail)) {
				helper.setAttribute("errorcounselorEmail",	"Invalid email format 8888!");
				isValid = false;
			}

		} catch (Exception e) {
			log.error("validateProgrammeDetails() : Exception " + e.toString());
			throw e;
		}
		return isValid;
	}
}