package com.genesiis.campus.command;

//20161026 CM c9-make-inquiry-for-institute INIT CmdSendInstituteInquiry.java
//20161027 CM c9-make-inquiry-for-institute Modified execute() method
//20161027 CM c9-make-inquiry-for-institute Add method comment
//20161027 CM c9-make-inquiry-for-institute Validate request parameters

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InstituteInquiryDAO;
import com.genesiis.campus.entity.model.InstituteInquiry;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;
import com.google.gson.Gson;

public class CmdSendInstituteInquiry implements ICommand {

	static Logger log = Logger.getLogger(CmdSendInstituteInquiry.class
			.getName());

	/**
	 * @author Chathuri
	 * @param helepr
	 *            IDataHelper object of Object type view IView object of object
	 *            type
	 * @return View object to servlet
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = "";

		try {
			final InstituteInquiry instituteInquiry = new InstituteInquiry();

			// String gsonData = helper.getParameter("jsonData");
			String validateResult = Validator.validateInquiry(helper);
			if (validateResult.equalsIgnoreCase("True")) {

				String fullname = helper.getParameter("fullname");
				String email = helper.getParameter("email");
				String countryCode = helper.getParameter("countryCode");
				String areaCode = helper.getParameter("areaCode");
				String telNo = helper.getParameter("telNum");
				String inquiryTitle = helper.getParameter("inquiryTitle");
				String inquiry = helper.getParameter("inquiry");
				int studentCode = Integer.parseInt(helper
						.getParameter("studentCode"));
				int corseProviderCode = Integer.parseInt(helper
						.getParameter("courseProviderCode"));

				// InstituteInquiry data = getInstituteInquirydetails(gsonData);

				instituteInquiry.setStudentName(fullname);
				instituteInquiry.setStudentEmail(email);
				instituteInquiry.setTelephoneCountryCode(countryCode);
				instituteInquiry.setTelephoneAreaCode(areaCode);
				instituteInquiry.setTelNo(telNo);
				instituteInquiry.setInquiryTitle(inquiryTitle);
				instituteInquiry.setInquiryText(inquiry);
				instituteInquiry.setStudent(studentCode);
				instituteInquiry.setCourseProvider(corseProviderCode);

				InstituteInquiryDAO inquiryDAO = new InstituteInquiryDAO();

				int result = inquiryDAO.add(instituteInquiry);
				if (result > 0) {
					message = SystemMessage.INQUIRYSENT.message();

				} else {

					message = SystemMessage.ERROR.message();
				}
			} else {
				message = validateResult;

			}

		} catch (Exception exception) {
			log.error("execute() : " + exception);
			message = SystemMessage.ERROR.message();
			throw exception;
		} finally {
			helper.setAttribute("message", message);

		}
		return view;
	}

	// public Object extractFromJason(String gsonData) {
	// Gson gson = new Gson();
	// String message = "";
	// InstituteInquiry instituteInquiry = null;
	// try {
	// instituteInquiry = gson.fromJson(gsonData, InstituteInquiry.class);
	//
	// } catch (Exception exception) {
	// log.error("extractFromJason(): " + exception.toString());
	// throw exception;
	// }
	// return instituteInquiry;
	// }
	//
	// /**
	// * Exctract data from json object and assign it to InstituteInquiry object
	// *
	// * @author Chathuri
	// * @param data
	// * String type
	// * @return InstituteInquery object
	// */
	// private InstituteInquiry getInstituteInquirydetails(String data) {
	//
	// InstituteInquiry instituteInquiry = (InstituteInquiry)
	// extractFromJason(data);
	//
	// return instituteInquiry;
	// }

}
