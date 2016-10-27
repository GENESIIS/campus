package com.genesiis.campus.command;

//20161026 CM c9-make inquiry for institute INIT CmdSendInstituteInquiry.java
//20161027 CM c9-make inquiry for institute Modified execute() method
//20161027 CM c9-make inquiry for institute Add method comment

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InstituteInquiryDAO;
import com.genesiis.campus.entity.model.InstituteInquiry;
import com.genesiis.campus.util.IDataHelper;
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
		String message = "Unsuccessfull";
		String gsonData = helper.getParameter("jsonData");
		Gson gson = new Gson();
		InstituteInquiry data = getInstituteInquirydetails(gsonData);

		InstituteInquiryDAO inquiryDAO = new InstituteInquiryDAO();
		int result = inquiryDAO.add(data);
		if (result > 0) {
			message = "Inquiry Send successfylly";

		}

		helper.setAttribute("result", gson.toJson(message));
		return view;
	}

	public Object extractFromJason(String gsonData) {
		Gson gson = new Gson();
		String message = "";
		InstituteInquiry instituteInquiry = null;
		try {
			instituteInquiry = gson.fromJson(gsonData, InstituteInquiry.class);

		} catch (Exception exception) {
			log.error("extractFromJason(): " + exception.toString());
			throw exception;
		}
		return instituteInquiry;
	}

	/**
	 * Exctract data from json object and assign it to InstituteInquiry object
	 * 
	 * @author Chathuri
	 * @param data
	 *            String type
	 * @return InstituteInquery object
	 */
	private InstituteInquiry getInstituteInquirydetails(String data) {

		InstituteInquiry instituteInquiry = (InstituteInquiry) extractFromJason(data);

		return instituteInquiry;
	}
}
