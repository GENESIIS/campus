package com.genesiis.campus.util;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.genesiis.campus.validation.SystemMessage;


public class SessionCounterListener implements HttpSessionListener{
	static Logger log = Logger.getLogger(SessionCounterListener.class.getName());
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		log.info("Created"+event);
		event.getSession().setMaxInactiveInterval(1 * 60);
		
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		log.info("Destroyed : " +event);
		event.getSession().setAttribute("message",SystemMessage.SESSIONEXPIRED.message());
	}

}
