package com.genesiis.campus.command;
//20170314 AS c23-admin-login-logout-function-as CmdAdminLogin class created
//20170316 AS c23-admin-login-logout-function-as CmdAdminLogin class coding WIP
//20170330 AS c23-admin-login-logout-function-as login attempts handled
//20170331 AS c23-admin-login-logout-function-as setAdminLoginDetails() method coded.


import java.net.URL;
 
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.AdminLoginDAO;
import com.genesiis.campus.entity.AdminPrivilegeDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Admin;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.CookieHandler;
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
	private static int max =0;
	Collection<String> privilegeList = null;
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		HttpSession session;
		//time been this code commented 
		//String domain = new URL(helper.getRequestURL()).getHost(); 
		
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
						//Cookies handling 
						if (rememberMe == true) {
							helper.setAttribute("admin", adminData);
							CookieHandler.addCookie(helper.getResponse(),
									"userIdendificationKey", adminData.getUserKey(),
									2592000);
						}
						//session variable setting 
						session = helper.getSession(true);
						String sessionId = session.getId();
						adminData.setLastLoggedInSessionid(sessionId);
						session.setAttribute("currentSessionUser",adminData.getUsername());
						session.setAttribute("user", adminData.getName());
						session.setAttribute("userCode", adminData.getCode());
						setAdminLoginDetails(adminData, helper);
						int status = AdminLoginDAO.loginDataUpdate(adminData);
						if(status >0){
							//admin privacy privilege list
							//CAM-154 doing the admin privilege handling 
							AdminPrivilegeDAO adminPrivilegeDAO = new AdminPrivilegeDAO();
							privilegeList = (ArrayList<String>) adminPrivilegeDAO.adminPrivileges(adminData);
							dataCollection.add(privilegeList);
						}else{
							
						}
						session.setAttribute("currentUserData", dataCollection);
						message = SystemMessage.LOGGEDSUCCESSFULL.message();
						path = SystemConfig.ADMIN_LANDING_PAGE.getValue1();
						pageURL = path;
	
					}else{
						//Logging attempts handling 
						for (max = max; max <= 3; max++) {
							
							if (max == 3) {
								message = SystemMessage.LOGGINATTEMPT3.message();
								path = SystemConfig.ADMIN_LOGIN_PAGE.getValue3();
								pageURL = path;
								max++;
								break;
							} else if (max == 2) {
								message = SystemMessage.LOGGINATTEMPT2.message();
								path = SystemConfig.ADMIN_LOGIN_PAGE.getValue2();
								pageURL = path;
								max++;
								break;
							} else if (max == 1) {
								message = SystemMessage.LOGGINATTEMPT1.message();
								path = SystemConfig.ADMIN_LOGIN_PAGE.getValue1();
								pageURL = path;
								max++;
								break;
							} else {
								path = SystemConfig.ADMIN_LOGIN_PAGE.getValue1();
								pageURL = path;
								max++;
								break;
							}
						}
					}
					
				}else{
					message = SystemMessage.LOGINUNSUCCESSFULL.message();
					path = SystemConfig.ADMIN_LOGIN_PAGE.getValue1();
				}
				
			}else{
				path = SystemConfig.ADMIN_LANDING_PAGE.getValue1(); //if user already logged, redirect to admin Landing page
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
	 * Admin login details maintain.
	 * 
	 * @param  Admin object
	 * @param helper
	 * @return admin object
	 */

	private Admin setAdminLoginDetails(Admin object, IDataHelper helper) {

		try {

			Date loginTime = new Date();

			java.util.Date utilDate = new java.util.Date();
			java.sql.Date loginDate = new java.sql.Date(utilDate.getTime());

			object.setLastLoggedInDate(loginDate.toString());
			object.setLastLoggedInTime(new Timestamp(loginTime.getTime())
					.toString());

			String browser = helper.getHeader("User-Agent");
			String[] output = browser.split("/");
			object.setLastLoggedInUserAgent(output[0]);
			object.setLastLoggedInIpAddress(helper.getRemoteAddress());
		} catch (Exception e) {
			log.error("setAdminLoginDetails(Admin object, IDataHelper helper):  Exception"
					+ e.toString());
			throw e;
		}
		return object;
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
