package com.genesiis.campus.command;

//20161026 CM c9-make inquiry for institute INIT CmdSendInstituteInquiry.java
//20161027 CM c9-make inquiry for institute Modified execute() method

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InstituteInquiryDAO;
import com.genesiis.campus.entity.model.InstituteInquiry;
import com.genesiis.campus.util.IDataHelper;
import com.google.gson.Gson;

public class CmdSendInstituteInquiry implements ICommand{

	static Logger log = Logger.getLogger(CmdSendInstituteInquiry.class.getName());
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		 String gsonData=helper.getParameter("jsonData");
		 
		InstituteInquiry data=getInstituteInquirydetails(gsonData);
		
		InstituteInquiryDAO inquiryDAO=new InstituteInquiryDAO();
		int result=inquiryDAO.add(data);
		return view;
	}

	public Object extractFromJason(String gsonData) {
		Gson gson = new Gson();
		String message = "";
		InstituteInquiry instituteInquiry=null;
		try {
			 instituteInquiry = gson.fromJson(gsonData, InstituteInquiry.class);
		
		} catch (Exception exception) {
			log.error("extractFromJason(): " + exception.toString());
			throw exception;
		}
		return instituteInquiry;
	}


	private InstituteInquiry getInstituteInquirydetails(String data) {
	
		InstituteInquiry instituteInquiry = (InstituteInquiry) extractFromJason(data);

		return instituteInquiry;
	}
}
