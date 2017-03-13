package com.genesiis.campus.util;
//DJ 20161115 c6-list-available-institutes-on-the-view Initiate cleanup method to clean open resources 
//20170130 CW c36-add-tutor-information re-organise the import statements.
//20170307 CW c147-tutor-reset-password-cw class copied from c37-tutor-update-tutor-profile-cw.

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

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

}