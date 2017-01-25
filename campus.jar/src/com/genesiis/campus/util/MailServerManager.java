package com.genesiis.campus.util;
//20161101 DN c10-contacting-us-page create MailServerManager.java

import java.sql.SQLException;
import java.util.logging.Logger;

import javax.mail.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * MailServerManager class access data from server(e.g. jboss) 
 * configuration xml file and creates a Mail session.
 * @author DN
 * @since 20161101
 *
 */
public class MailServerManager { 
	
	static Logger log = Logger.getLogger(MailServerManager.class.getName());

	private static final String SESSION_JNDI_NAME = "java:jboss/mail/Default";
	// The SESSION_JNDI_NAME is the property for mail session source name that the
	// application looking at runtime.
	private static Session session;

	// This static{} block runs first when running the MailServerManager class
	// object.
	static {
		try {
			// From the Session name, we get the Session which we have
			// mentioned in server configuration file (e.g.campus.xml,xeno.xml)
			session = (Session) new InitialContext().lookup(SESSION_JNDI_NAME);			
		} catch (NullPointerException e) {
			log.info("NullPointerException: look up mail Session: " + e.toString());
		} catch (NamingException e) {
			e.printStackTrace();
			log.info("Naming Exection: look up mail Session: " + e.toString());
		} catch (ClassCastException e) {
			log.info("ClassCastException: casting mail Session = (Session) "
					+ e.toString());
		} finally {
			log.info("ConnectionManager static block execution over.");
		}
	}

	// This method only return the Session type variable for other classes,
	// to access the Mail session.
	public static Session mailSession() throws SQLException {		
		return session;
		
	}
	

}