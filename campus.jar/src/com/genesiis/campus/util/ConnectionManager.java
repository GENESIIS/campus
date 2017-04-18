package com.genesiis.campus.util;

//20170405 PN CAM-137: INIT ConnectionManager class to handle the db connection in a different way.
//					   	getConnection() method changed by calling getInstance() method.
//20170418 PN CAM-137: ConnectionManagerException inner class added to the ConnectionManager class.
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
			throw new ConnectionManagerException(e);
		}

	}

	/**
	 * Implementing singleton pattern.
	 * @return ConnectionManager
	 * @throws ConnectionManagerException
	 */
	private static ConnectionManager getInstance()
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

	/**
	 * Method to get db connection.
	 * @return Connection
	 */
	public static Connection getConnection() {
		Connection connection = null;

		try {
			connection = ConnectionManager.getInstance().dataSource.getConnection(); 

		} catch (Exception e) {
			logger.error("getConnection()", e); 
			connection = null;
		}
		return connection;
	}
	
	
	/**
	 * RuntimeException represent defects in the program (bugs) – often invalid
	 * arguments passed to a non-private method. RuntimeExceptionas can be
	 * IllegalArgumentException, NullPointerException, or IllegalStateException
	 * RuntimeException, a method is not obliged to establish a policy for the
	 * unchecked exceptions thrown by its implementation (and they almost always
	 * do not do so) 
	 * 
	 * ConnectionManagerException is customized exception class,
	 * which specifically state the exceptions thrown from, managing DB source
	 * connection.
	 * 
	 * @author pabodha
	 *
	 */
	private static class ConnectionManagerException extends RuntimeException {
		private static final long serialVersionUID = 1L; 

		public ConnectionManagerException() {
		}

		public ConnectionManagerException(String s) {
			super(s);
		}

		public ConnectionManagerException(Exception e) {
			super(e.getMessage());
		}

	}

}
