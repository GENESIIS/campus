package com.genesiis.campus.util;

//DJ 20161115 c6-list-available-institutes-on-the-view Initiate cleanup method to clean open resources 

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**The class {@code DaoHelper} is a form utility helper class.Created for the purpose of DAO level utility functions. * 
* @author dumani DJ     
*/
public class DaoHelper {
	
	static Logger log = Logger.getLogger(DaoHelper.class.getName());
	
	 /**
   * Closes down resources.
   * @param conn Connection 
   * @param rs Statement
   * @param statement ResultSet
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
