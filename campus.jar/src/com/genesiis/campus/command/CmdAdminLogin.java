package com.genesiis.campus.command;
import java.net.URL;
//20170314 AS c23-admin-login-logout-function-as CmdAdminLogin class created 
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.AdminLoginDAO;
import com.genesiis.campus.entity.ICrud;
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
	String path;
	String message;
	private Admin adminData;
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		HttpSession session;
		
		String domain = new URL(helper.getRequestURL()).getHost();
		
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
					adminData = LoginValidator.dataSeparator(adminData);
					
					ICrud adminLoginDAO = new AdminLoginDAO();
					dataCollection = adminLoginDAO.findById(adminData);
					
					for (Collection<String> collection : dataCollection) {
						Object[] array = collection.toArray();
						message = (String) array[0];

					}
					if (message.equalsIgnoreCase(SystemMessage.VALIDUSER.message())) {
						
					}else{
						if (message.equalsIgnoreCase(SystemMessage.LOGGINATTEMPT3.message())) {
							message = SystemMessage.LOGGINATTEMPT3.message();
							path = SystemConfig.ADMIN_LOGIN_PAGE.getValue3();
							pageURL = domain+""+path;
						} else if(message.equalsIgnoreCase(SystemMessage.LOGGINATTEMPT2.message())){
							message = SystemMessage.LOGGINATTEMPT2.message();
							path = SystemConfig.ADMIN_LOGIN_PAGE.getValue2();
							pageURL = domain+""+path;
							
						}else if(message.equalsIgnoreCase(SystemMessage.LOGGINATTEMPT1.message())){
							message = SystemMessage.LOGGINATTEMPT1.message();
							
						}
					}
					
					
				}else{
					message = SystemMessage.LOGINUNSUCCESSFULL.message();
					path = SystemConfig.ADMIN_LOGIN_PAGE.getValue1();
				}
				
			}else{
				path = SystemConfig.ADMIN_LANDING_PAGE.getValue1();
			}
			
			helper.setAttribute("message", message);
			helper.setAttribute("pageURL", pageURL);
			view.setCollection(dataCollection);
			
		} catch (SQLException e) {
			log.error("execute(IDataHelper helper, IView view):  SQLException"
					+ e.toString());
			throw e;
		} catch (Exception e) {
			log.error("execute(IDataHelper helper, IView view):  Exception"
					+ e.toString());
			throw e;
		}
		
		return view;
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
