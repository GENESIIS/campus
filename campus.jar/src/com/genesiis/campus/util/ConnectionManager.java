package com.genesiis.campus.util;

//20170405 PN CAM-137: INIT ConnectionManager class to handle the db connection in a different way.

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class ConnectionManager {

	Context context;
	
	private static final String DB_JNDI_NAME = "java:/xenoDatabase"; 
	private static DataSource dataSource;
	private volatile static ConnectionManager theManager;
	static Logger logger = Logger.getLogger(ConnectionManager.class.getName());
	
	// private constructor.  As per singleton pattern, this class cannot be 'newed up' (instantiated) directly
	// must create an instance through the static getInstance method
	// this constructor initialises a datasource using a jndi lookup
	private ConnectionManager() throws ConnectionManagerException {
		
		
		try {
			this.context = new InitialContext(); 							
			dataSource = (DataSource) this.context.lookup(DB_JNDI_NAME); 	
																			
			if (dataSource == null) {
				String eMessage = "ConnectionManager(): null dataSource for: "+this.DB_JNDI_NAME; 
				logger.error(eMessage);
				throw new ConnectionManagerException(eMessage);
			}
		} catch (Exception e) {
			String eMessage = "ConnectionManager(): exception: "+this.DB_JNDI_NAME+ " : " + e.toString();
			logger.error(eMessage);
			e.printStackTrace();
			throw new ConnectionManagerException(e);
		}

	}

	/**
	 * 
	 * @return
	 * @throws ConnectionManagerException
	 * @Description implement singleton pattern.
	 * cc3431
	 */
	public static ConnectionManager getInstance()
			throws ConnectionManagerException {
		try {
			// call the constructor to create an instance having a datasource
			if (ConnectionManager.theManager == null) {			// check for null
				synchronized (ConnectionManager.class) {		// get a lock
					if(theManager == null){						// and check for null again
						theManager = new ConnectionManager();
					}	
				}
			}
		} catch (Exception e) {
			throw new ConnectionManagerException(e);
		}

		return theManager;
	}

	
	public static Connection getConnection() {
		Connection c = null;

		try {
			c = ConnectionManager.dataSource.getConnection(); 

		} catch (Exception e) {
			logger.error("getConnection()", e); 
			c = null;
		}
		return c;
	}
}
