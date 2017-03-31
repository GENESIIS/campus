package com.genesiis.campus.util;
//DJ 20161115 c6-list-available-institutes-on-the-view Initiate cleanup method to clean open resources 
//20170117 JH c39-add-course-provider added closeConnection(Connection), closeStatement(Statement), closeResultSet(ResultSet) methods
//20170201 JH c39-add-course-provider arranged imports according to the style guide
//20170201 JH c39-add-course-provider code modified to throw SQL Exception
//20170208 DN c131-admin-manage-banner-upload-banner-image-dn add error logs and throw statements in catch blocks 

import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoHelper {
	static Logger log = Logger.getLogger(DaoHelper.class.getName());
	 /**
   * Closes down resources
   * @param conn
   * @param rs
   * @param statement
   * @author DJ     
   */
	
	public static void cleanup(Connection conn, Statement statement, ResultSet rs) throws SQLException
  {      
      if (rs != null)
      {
          try
          {
              rs.close();
          }
          catch (SQLException ignore){
        	log.error("cleanup():SQLException "+ ignore.toString()); 
        	throw ignore;
          }
      }
      if (statement != null)
      {
          try
          {                
              statement.close();
          }
          catch (SQLException ignore){
        	  log.error("cleanup():SQLException "+ ignore.toString());  
        	  throw ignore;
          }
      }
      if (conn != null)
      {
          try
          {               
              conn.close();
          }
          catch (SQLException ignore){
        	  log.error("cleanup():SQLException "+ ignore.toString());
        	  throw ignore;
          }
      }       
  }

	/**
	 * closeConnection method to close the Connection
	 * @param conn
	 * @author JH
	 */
	public static void closeConnection(Connection conn) throws SQLException {
		if (conn != null) {

			try {
				conn.close();
			} catch (SQLException ignore) {
				log.error("closeConnection(Connection conn):SQLException "+ ignore.toString());
				 throw ignore;
			}

		}
	}
	
	
	/**
	 * closeStatement method, closes the statement passed as the parameter
	 * @param statement
	 * @author JH
	 */
	public static void closeStatement(Statement statement) throws SQLException{
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException ignore) {
				log.error("closeStatement(Statement statement):SQLException "+ ignore.toString());
				 throw ignore;
			}
		}
	}
	
	
	/**
	 * closeResultSet method, closes the result set
	 * @param result
	 */
	public static void closeResultSet(ResultSet result)throws SQLException{
		if (result != null) {
			try {
				result.close();
			} catch (SQLException ignore) {
				log.error("closeResultSet(ResultSet result):SQLException "+ ignore.toString());
				throw ignore;
			}
		}
	}
	
	
	
	
}