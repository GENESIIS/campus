package com.genesiis.campus.util;

//DJ 20161115 c6-list-available-institutes-on-the-view Initiate cleanup method to clean open resources 
//20170117 JH c39-add-course-provider added closeConnection(Connection), closeStatement(Statement), closeResultSet(ResultSet) methods 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoHelper {

	/**
	 * Closes down resources
	 * 
	 * @param conn
	 * @param rs
	 * @param statement
	 * @author DJ
	 */

	public static void cleanup(Connection conn, Statement statement,
			ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ignore) {
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException ignore) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ignore) {
			}
		}
	}

	/**
	 * closeConnection method to close the Connection
	 * @param conn
	 * @author JH
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {

			try {
				conn.close();
			} catch (SQLException ignore) {
			}

		}
	}
	
	
	/**
	 * closeStatement method, closes the statement passed as the parameter
	 * @param statement
	 * @author JH
	 */
	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException ignore) {
			}
		}
	}
	
	
	/**
	 * closeResultSet method, closes the result set
	 * @param result
	 */
	public static void closeResultSet(ResultSet result){
		if (result != null) {
			try {
				result.close();
			} catch (SQLException ignore) {
			}
		}
	}

}