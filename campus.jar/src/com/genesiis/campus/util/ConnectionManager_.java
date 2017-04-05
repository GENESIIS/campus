package com.genesiis.campus.util;

//**20161024 DN created SQLServerconectionManager.java***//
//20170405 PN CAM-137: renamed the class ConnectionManager into ConnectionManager_.

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
* ConnectionManager class manages a database connection 
* @author DN
* @since 20160713
* @version 1.0
*
*/

public class ConnectionManager_ {
	static Logger log = Logger.getLogger(ConnectionManager_.class.getName());

	private static final String DB_JNDI_NAME = "java:/xenoDatabase";
	// The DB_JNDI_NAME is the property for data source name that the
	// application looking at runtime.
	private static DataSource dataSource;

	// This static{} block runs first when running the ConnectionManager class
	// object.
	static {
		try {
			// From the datasource name, we get the datasource which we have
			// mentioned in xeno.xml file.
			dataSource = (DataSource) new InitialContext().lookup(DB_JNDI_NAME);
		} catch (NullPointerException e) {
			log.info("NullPointerException: look up dataSource: " + e);
		} catch (NamingException e) {
			e.printStackTrace();
			log.info("Naming Exection: look up dataSource: " + e);
		} catch (ClassCastException e) {
			log.info("ClassCastException: casting dataSource = (DataSource) "
					+ e);
		} finally {
			log.info("ConnectionManager static block execution over.");
		}
	}

	// This method only return the Connection type variable for other classed,
	// to access the dataSource through that.
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	
}