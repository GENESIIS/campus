package com.genesiis.campus.util;
//20161102 AS c9-inquiry-form-for-institute-MP-cm changed logger statement ConnectionManager to MailServerManager
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.mail.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class MailServerManager {
	
	static Logger log = Logger.getLogger(MailServerManager.class.getName());

	private static final String SESSION_JNDI_NAME = "java:jboss/mail/Default";
	// The SESSION_JNDI_NAME is the property for mail session source name that the
	// application looking at runtime.
	private static Session session;

	// This static{} block runs first when running the ConnectionManager class
	// object.
	static {
		try {
			// From the Session name, we get the Session which we have
			// mentioned in campus.xml file.
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

	// This method only return the Connection type variable for other classed,
	// to access the Mail session through that.
	public static Session mailSession() throws SQLException {		
		return session;
		
	}
	

}
