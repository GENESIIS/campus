package com.genesiis.campus.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;

import com.genesiis.campus.util.ConnectionManager;

public class UserTypeDAO implements ICrud {
	
	static Logger log = Logger.getLogger(TownDAO.class.getName());

	@Override
	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	/**
	 * Returns the code of the user type for given name
	 * 
	 * @author CW
	 * @param  name : String
	 * @return int value containing the code of the user type for given name 
	 * @throws SQLException, Exception
	 */
	public int getCode(String name) throws SQLException, Exception {
		int code = 0;
		ResultSet res = null;		
		PreparedStatement preparedStatement = null;
		Connection con = ConnectionManager.getConnection();

		try{
			
			String sql = "SELECT CODE FROM [CAMPUS].[USERTYPE] WHERE [NAME] =? ";
	
			preparedStatement  = con.prepareStatement(sql);
			preparedStatement.setString(1, name);// set the name parameter to the prepared statement
			
			res= preparedStatement.executeQuery();
			
			while (res.next()) {
				code = res.getInt("CODE");
			} 
		} catch (SQLException sqle) {
			log.info("getCode() : SQLException :"+ sqle.getErrorCode()+ ": "+sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			log.info("getCode() : Exception :"+ ex.toString());
		} finally {			
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return code;
	}

}
