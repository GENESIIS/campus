package com.genesiis.campus.util;

//20170405 PN CAM-137: INIT GenericConnectionManager abstract class.

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public abstract class GenericConnectionManager {
	Logger logger = Logger.getLogger(GenericConnectionManager.class.getName());
	/**
	 * Get the connection.
	 * @return Connection
	 */
	public abstract Connection getConnection();
	
	/**
	 * Release the connection.
	 * @param connection
	 */
	public void releaseConnection(Connection connection){
		try {
			if(connection != null & !connection.isClosed()){
				connection.close();
			}
		} catch (SQLException e) {
			logger.error("[" + getClass() + " : releaseConnection(Connection connection)] - Exception : " + e.getMessage());
		}
	}
}
