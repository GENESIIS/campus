package com.genesiis.campus.command;
//20170426 AS c155-admin-logout-function-as CmdAdminLogout class created

import com.genesiis.campus.entity.AdminLoginDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentLoginDAO;
import com.genesiis.campus.entity.model.Admin;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpSession;

public class CmdAdminLogout implements ICommand{
	static Logger log = Logger.getLogger(CmdAdminLogout.class.getName());
	
	private Admin adminData;
	String pageURL = SystemConfig.ADMIN_LOGIN_PAGE.getValue1();
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = SystemMessage.LOGOUTUNSUCCESSFULL.message();
		try {
			
			HttpSession curentSession = helper.getRequest().getSession(false);

			String currentSessionUser = (String) curentSession
					.getAttribute("currentSessionUsername");
			if (currentSessionUser != null) {
				curentSession.removeAttribute("user");
				curentSession.removeAttribute("userCode");
				curentSession.removeAttribute("currentUserData");
				curentSession.invalidate();

				String gsonData = helper.getParameter("jsonData");
				adminData = getAdmindetails(gsonData);
				
				Date loginTime = new Date();

				java.util.Date utilDate = new java.util.Date();
				java.sql.Date loginDate = new java.sql.Date(utilDate.getTime());
				
				int status = AdminLoginDAO.logoutDataUpdate(adminData);

				if (status > 0) {
					message = SystemMessage.LOGOUTSUCCESSFULL.message();

				} else {
					message = SystemMessage.LOGOUTUNSUCCESSFULL.message();
				}

			} else {
				pageURL = "http://www.campus.dev:8080/dist/partials/error/error-content.jsp";

			}
			
			
		} catch (Exception e) {
			log.error("CmdAdminLogout():  Exception" + e.toString());
			throw e;
		}
		helper.setAttribute("message", message);
		helper.setAttribute("pageURL", pageURL);
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
