package com.genesiis.campus.command;
//20170314 AS c23-admin-login-logout-function-as CmdAdminLogin class created 
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Admin;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.LoginValidator;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.SystemMessage;
import com.google.gson.Gson;


public class CmdAdminLogin implements ICommand{
	static Logger log = Logger.getLogger(CmdAdminLogin.class.getName());
	
	private Collection<Collection<String>> dataCollection = null;
	String pageURL;
	String message;
	private Admin adminData;
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		log.info("hit here");
		HttpSession session;
		try {
			message = SystemMessage.LOGINUNSUCCESSFULL.message();
			session = helper.getRequest().getSession(false);
			String currentSessionUser = (String) session.getAttribute("currentSessionUser");
			//check the user already logged or not 
			if (currentSessionUser == null) {

				String gsonData = helper.getParameter("jsonData");
				adminData = getAdmindetails(gsonData);
				
				String validateResult = LoginValidator.validateLogin(adminData);
				boolean rememberMe = adminData.isRemember();
				if (validateResult.equalsIgnoreCase("True")) {
					
					
					
				}else{
					message = SystemMessage.LOGINUNSUCCESSFULL.message();
				}
				
			}else{
				pageURL = SystemConfig.ADMIN_LANDING_PAGE.getValue1();
			}
			
			
		} catch (SQLException e) {
			log.error("execute(IDataHelper helper, IView view):  SQLException"
					+ e.toString());
			throw e;
		} catch (Exception e) {
			log.error("execute(IDataHelper helper, IView view):  Exception"
					+ e.toString());
			throw e;
		}
		
		return null;
	}
	
	
	
	/**
	 * extract data from json object and assign to Admin
	 * object
	 * 
	 * @author anuradha
	 * @param gsonData
	 * @return Admin object
	 */

	private Admin getAdmindetails(String gsonData) {
		Admin admin = (Admin) extractFromJason(gsonData);

		return admin;
	}

	public Object extractFromJason(String gsonData) {
		Gson gson = new Gson();
		String message = "";
		Admin admin = null;
		try {
			admin = gson.fromJson(gsonData, Admin.class);

		} catch (Exception exception) {
			log.error("extractFromJason(): " + exception.toString());
			throw exception;
		}
		return admin;
	}

}
