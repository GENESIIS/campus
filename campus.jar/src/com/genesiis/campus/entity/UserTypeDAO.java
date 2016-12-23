package com.genesiis.campus.entity;

//20161209 DN CAm-18 student : signup : without using third party application 
//			  initial version of the UserTypeDAO.java created
//20161214 DN CAM:18 added userTypeString to the prepared statement in findById()
//20161222 DN CAMP:18 introduced methods for closing connection and creating the database Connection.
//20161223 DN CAMP:18 add separate class DaoHelper.java of whose responsibility is to clean up the 
// database resources.and used cleanup() method.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

public class UserTypeDAO implements ICrud {
	
	static Logger log = Logger.getLogger(UserTypeDAO.class.getName());

	@Override
	public int add(Object object) throws SQLException, Exception {
		
		return 0;
	}

	@Override
	public int update(Object object) throws SQLException, Exception {
		
		return 0;
	}

	@Override
	public int delete(Object object) throws SQLException, Exception {
		
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
			Connection userTypeConnection = null;
			PreparedStatement prepaire =null;
			ResultSet userCode =null;
			String userTypeString  = (String) code;
		try{
			Collection<Collection<String>> outerWrapper = new ArrayList<Collection<String>>();
			 userTypeConnection = ConnectionManager.getConnection();
			
			StringBuilder getUserTypeSQL = new StringBuilder("SELECT * FROM [CAMPUS].[USERTYPE] ");
			getUserTypeSQL.append(" WHERE USERTYPESTRING = ? AND ISACTIVE=1 ; ");
			prepaire = userTypeConnection.prepareStatement(getUserTypeSQL.toString());
			prepaire.setString(1, userTypeString);
			userCode = prepaire.executeQuery();
			
			while(userCode.next()){
				final Collection<String> singleUSerTypeList = new ArrayList<String>();
				singleUSerTypeList.add(userCode.getString("CODE"));
				singleUSerTypeList.add(userCode.getString("USERTYPESTRING"));
				outerWrapper.add(singleUSerTypeList);
			}
			return outerWrapper;
		} catch (SQLException sqle){
			log.error("findById(): SQLException "+ sqle.toString());
			throw sqle;
		} catch(Exception exp){
			log.error("findById(): Wxcepption"+ exp.toString());
			throw exp;
		} finally{
			try{
				DaoHelper.cleanup(userTypeConnection, prepaire, userCode);
			} catch(SQLException sqle){
				log.error("findById(): SQLException in finally block "+ sqle.toString());
				throw sqle;}
			
		}
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException,
			Exception {
		
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException,
			Exception {
		
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException,
			Exception {
		
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		
		return null;
	}

}
