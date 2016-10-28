package com.genesiis.campus.command;
//20161027 AS C8-inquiry-form-for-course CmdSendCourseInquiry class created.
import java.sql.SQLException;

import com.genesiis.campus.entity.CourseInquiryDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.StudentProgrammeInquiry;
import com.genesiis.campus.util.IDataHelper;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

public class CmdSendCourseInquiry implements ICommand{

	static Logger log = Logger.getLogger(CmdSendCourseInquiry.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = "Unsuccessfull";
		String gsonData = helper.getParameter("jsonData");
		StudentProgrammeInquiry data = getInstituteInquirydetails(gsonData);

		CourseInquiryDAO inquiryDAO = new CourseInquiryDAO();
		int result = inquiryDAO.add(data);
		if (result > 0) {
			message = "Inquiry Send successfylly";

		}

		helper.setAttribute("message", message);
		return view;
		
	
	}

	/**
	 * extract data fromm json object and assign to StudentProgrammeInquiry object 
	 * @author anuradha
	 * @param gsonData
	 * @return StudentProgrammeInquiry object 
	 */
	
	private StudentProgrammeInquiry getInstituteInquirydetails(String gsonData) {
		StudentProgrammeInquiry courseInquiry = (StudentProgrammeInquiry) extractFromJason(gsonData);
		
		return courseInquiry;
	}

	public Object extractFromJason(String gsonData) {
		Gson gson = new Gson();
		String message = "";
		StudentProgrammeInquiry courseInquiry = null;
		try {
			courseInquiry = gson.fromJson(gsonData, StudentProgrammeInquiry.class);

		} catch (Exception exception) {
			log.error("extractFromJason(): " + exception.toString());
			throw exception;
		}
		return courseInquiry;
	}
	
	

}
