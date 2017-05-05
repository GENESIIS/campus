package com.genesiis.campus.util;

//20170119 AS CAM-20 SessionCounterListener class created. 
//20170120 AS CAM-20 Session timeout Interval set to 1hour. 
//20170124 AS CAM-20 SetAplicationScoop to current session and remove from the destroy event.
//20170125 AS CAM-20 unwanted loggers removed.
//20170227 AS CAM-21 Session attribute added final modifier 
//20170228 AS CAM-21 removed Session attribute in destroyed method 
//20170427 AS CAM-155-admin-logout-function-as- sessionDestroyed() implementation changed.
//20170428 AS CAM-155-admin-logout-function-as- sessionDestroyed() implementation modified.
import com.genesiis.campus.entity.AdminLoginDAO;
import com.genesiis.campus.entity.model.Admin;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UserType;

import org.apache.log4j.Logger;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounterListener implements HttpSessionListener {
	static Logger log = Logger.getLogger(SessionCounterListener.class.getName());

	ServletContext serveltContext = null;
	static int totalSession = 0, currentSession = 0;
	String message = "Message";

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		totalSession++;
		currentSession++;
		final HttpSession session = event.getSession();
		serveltContext = event.getSession().getServletContext();
		event.getSession().setMaxInactiveInterval(60 * 10); // session
															// expiration time
		serveltContext.setAttribute("totalUsers", totalSession);
		serveltContext.setAttribute("curentSession", currentSession);
	
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		try {

			message = SystemMessage.SESSIONEXPIRED.message();
		
			Object userType = event.getSession().getAttribute("userTypeString");
			if (userType instanceof String) {
			int userLoginHistoryCode = (Integer)event.getSession().getAttribute("userLoginHistoryCode");
				if (((String) userType).equalsIgnoreCase(UserType.ADMIN.getUserType())) {

					Admin adminData = new Admin();
					adminData.setLoginHistoryModBy("system");
					adminData.setLoginHistoryCode(userLoginHistoryCode);
					AdminLoginDAO.logoutDataUpdate(adminData);

					event.getSession().removeAttribute("user");
					event.getSession().removeAttribute("userCode");
					event.getSession().removeAttribute("currentUserData");
					event.getSession().removeAttribute("currentSessionUsername");
					event.getSession().removeAttribute("userTypeString");
					event.getSession().removeAttribute("userLoginHistoryCode");
					event.getSession().invalidate();
					

				} else if (((String) userType).equalsIgnoreCase(UserType.STUDENT.getUserType())) {
					

				} else if (((String) userType).equalsIgnoreCase(UserType.TUTOR.getUserType())) {

				} else if (((String) userType).equalsIgnoreCase(UserType.FEATURED_COURSE_PROVIDER.getUserType())) {

				} else if (((String) userType).equalsIgnoreCase(UserType.SUPER_ADMIN.getUserType())) {
					
					Admin adminData = new Admin();
					adminData.setLoginHistoryModBy("system");
					adminData.setLoginHistoryCode(userLoginHistoryCode);
					AdminLoginDAO.logoutDataUpdate(adminData);

					event.getSession().removeAttribute("user");
					event.getSession().removeAttribute("userCode");
					event.getSession().removeAttribute("currentUserData");
					event.getSession().removeAttribute("currentSessionUsername");
					event.getSession().removeAttribute("userTypeString");
					event.getSession().invalidate();

				} else if (((String) userType).equalsIgnoreCase(UserType.COMPANY_DEO.getUserType())) {
					
					Admin adminData = new Admin();
					adminData.setLoginHistoryModBy("system");
					adminData.setLoginHistoryCode(userLoginHistoryCode);
					AdminLoginDAO.logoutDataUpdate(adminData);

					event.getSession().removeAttribute("user");
					event.getSession().removeAttribute("userCode");
					event.getSession().removeAttribute("currentUserData");
					event.getSession().removeAttribute("currentSessionUsername");
					event.getSession().removeAttribute("userTypeString");
					event.getSession().invalidate();

				}

			}

			event.getSession().removeAttribute("user");
			event.getSession().removeAttribute("userCode");
			event.getSession().removeAttribute("currentUserData");
			event.getSession().invalidate();
			currentSession--;
			serveltContext.setAttribute("curentSession", currentSession);
			serveltContext.setAttribute("message", message);

		} catch (SQLException e) {
			log.error("sessionDestroyed(HttpSessionEvent event) :  SQLException"
					+ e.toString());
			
		} catch (Exception e) {
			log.error("sessionDestroyed(HttpSessionEvent event) :  Exception" + e.toString());
		}
	}

}
