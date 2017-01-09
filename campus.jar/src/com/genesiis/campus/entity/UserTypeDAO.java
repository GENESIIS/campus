package com.genesiis.campus.entity;

//20161209 DN CAm-18 student : signup : without using third party application 
//			  initial version of the UserTypeDAO.java created

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;

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
			ResultSet userCode = null;
		try{
			String userTypeString  = (String) code;
			Collection<Collection<String>> outerWrapper = new ArrayList<Collection<String>>();
			 userTypeConnection = ConnectionManager.getConnection();
			
			StringBuilder getUserTypeSQL = new StringBuilder("SELECT * FROM [CAMPUS].[USERTYPE] ");
			getUserTypeSQL.append(" WHERE USERTYPESTRING = ? AND ISACTIVE=1 ; ");
			prepaire = userTypeConnection.prepareStatement(getUserTypeSQL.toString());
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
			log.error("findById(): Exception"+ exp.toString());
			throw exp;
		} finally{
			if (userCode != null) {
				userCode.close();
			}
			if (prepaire != null) {
				prepaire.close();
			}
			if (userTypeConnection != null) {
				userTypeConnection.close();
			}
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

	
	

}