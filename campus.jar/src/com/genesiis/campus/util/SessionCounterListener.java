package com.genesiis.campus.util;
//20170119 AS CAM-20 SessionCounterListener class created. 
//20170120 AS CAM-20 Session timeout Interval set to 1hour. 
//20170124 AS CAM-20 SetAplicationScoop to current session and remove from the destroy event.
//20170125 AS CAM-20 unwanted loggers removed.
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounterListener implements HttpSessionListener{
	static Logger log = Logger.getLogger(SessionCounterListener.class.getName());
	
	ServletContext serveltContext = null;
	static int totalSession=0, currentSession=0;  
	 String message = "Message";
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		totalSession ++;
		currentSession ++;
		
		serveltContext = event.getSession().getServletContext();
		event.getSession().setMaxInactiveInterval(1 * 60); //session expiration time 
		serveltContext.setAttribute("totalUsers", totalSession);
		serveltContext.setAttribute("curentSession", currentSession);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		message = SystemMessage.SESSIONEXPIRED.message();
		event.getSession().removeAttribute("user");
		event.getSession().removeAttribute("userCode");
		event.getSession().removeAttribute("currentUserData");
		event.getSession().invalidate();
		currentSession --;
		serveltContext.setAttribute("curentSession", currentSession);
		serveltContext.setAttribute("message",message);
		
	}

}
