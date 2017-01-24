package com.genesiis.campus.util;
//20170119 CAM-20 SessionCounterListener class created. 
//20170120 CAM-20 Session timeout Interval set to 1hour. 
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.genesiis.campus.validation.SystemMessage;


public class SessionCounterListener implements HttpSessionListener{
	static Logger log = Logger.getLogger(SessionCounterListener.class.getName());
	
	ServletContext serveltContext = null;
	static int totalSession=0, currentSession=0;  
	 String message = "abcd";
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		log.info("Created"+event);
		totalSession ++;
		currentSession ++;
		
		serveltContext = event.getSession().getServletContext();
		event.getSession().setMaxInactiveInterval(2 * 60); //session expiration time 
		serveltContext.setAttribute("totalUsers", totalSession);
		serveltContext.setAttribute("curentSession", currentSession);
		
		log.info("Created "+totalSession  +"Curent user "+currentSession );
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		log.info("Destroyed : " +event);
		message = SystemMessage.SESSIONEXPIRED.message();
		event.getSession().invalidate();
		currentSession --;
		serveltContext.setAttribute("curentSession", currentSession);
		serveltContext.setAttribute("message",message);
		
		log.info("Destroyed currentSession : " +currentSession);
	}

}
