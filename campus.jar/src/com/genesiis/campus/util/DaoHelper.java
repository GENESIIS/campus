package com.genesiis.campus.util;

//DJ 20161115 c6-list-available-institutes-on-the-view Initiate cleanup method to clean open resources 

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoHelper {
	
	 /**
     * Closes down resources
     * @param conn
     * @param rs
     * @param statement
     * @author DJ     
     */
	
	public static void cleanup(Connection conn, Statement statement, ResultSet rs)
    {      
        if (rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException ignore){}
        }
        if (statement != null)
        {
            try
            {                
                statement.close();
            }
            catch (SQLException ignore){}
        }
        if (conn != null)
        {
            try
            {               
                conn.close();
            }
            catch (SQLException ignore){}
        }       
    }

}