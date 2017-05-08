package com.genesiis.campus.command;
//20170505 AS c99-tutor-login-function-as- CmdTutorLogin sample page created
//20170508 AS c99-tutor-login-function-as-back end validation WIP
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Admin;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.LoginValidator;
import com.google.gson.Gson;

import org.apache.log4j.Logger;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpSession;

public class CmdTutorLogin implements ICommand{
	static Logger log = Logger.getLogger(CmdTutorLogin.class.getName());
	
	private Collection<Collection<String>> dataCollection = null;
	private String pageURL;
	private String path;
	private String message;
	private Tutor tutorData;
	private static int max =0;
	Collection<String> privilegeList = null;
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		HttpSession session;
		
		try {
			session = helper.getRequest().getSession(false);
			String currentSessionUser = (String) session.getAttribute("currentSessionUser");
			if(currentSessionUser == null){
				String gsonData = helper.getParameter("jsonData");
				tutorData = getTutordetails(gsonData);
				
//				String validateResult = LoginValidator.validateLogin(tutorData);
				
			}else{
				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return view;
	}
	
	/**
	 * extract data from json object and assign to tutor
	 * object
	 * 
	 * @author anuradha
	 * @param gsonData
	 * @return tutor object
	 */

	private Tutor getTutordetails(String gsonData) {
		Tutor tutor = (Tutor) extractFromJason(gsonData);

		return tutor;
	}

	public Object extractFromJason(String gsonData) {
		Gson gson = new Gson();
		String message = "";
		Tutor tutor = null;
		try {
			tutor = gson.fromJson(gsonData, Tutor.class);

		} catch (Exception exception) {
			log.error("extractFromJason(): " + exception.toString());
			throw exception;
		}
		return tutor;
	}

}
