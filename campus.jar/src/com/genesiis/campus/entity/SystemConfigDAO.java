package com.genesiis.campus.entity;
// 20161026 Dn c10-contacting-us-page created the initial version of SystemConfigDAO.java
//20161026 Dn c10-contacting-us-page findById(Object object,Connection conn) created
//20170109 DN CAM47 refactor the method findById(Object object,Connection con) to include finally block
import com.genesiis.campus.util.DaoHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.Collection;

public class SystemConfigDAO implements ICrud {
	
	static Logger log = Logger.getLogger(SystemConfigDAO.class.getName());

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
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		
		String[]  sysConfigCode = (String[])object;		
		
		StringBuilder queryBuilder = new StringBuilder("SELECT [CODE] ,[SYSTEMCONFIGCODE] ,[DESCRIPTION] ,[VALUE1] ");
		queryBuilder.append(" ,[VALUE2] ,[VALUE3] ,[SORTKEY] ,[CRTON] ,[CRTBY] ");
		queryBuilder.append(",[MODON] ,[MODBY] ");
		queryBuilder.append("FROM [CAMPUS].[SYSTEMCONFIG] ");
		queryBuilder.append(" WHERE [SYSTEMCONFIGCODE] IN ( ");
		
		for (int counter = 0; counter < sysConfigCode.length; counter++) {
			if (counter < sysConfigCode.length - 1) {
				queryBuilder.append("'"+sysConfigCode[counter] +"'"+ ", ");
			} else {
				queryBuilder.append("'"+sysConfigCode[counter] + "'"+" );");
			}
		}
		
		
		String findSql =queryBuilder.toString();		
		final Collection<Collection<String>> outerCollection = new ArrayList<Collection<String>>();
		PreparedStatement prstmtFind =null;
		ResultSet resultSet =null;
		try{
			
			 prstmtFind = conn.prepareStatement(findSql);
			 resultSet = prstmtFind.executeQuery();
			
			while(resultSet.next()){
				final ArrayList<String> systemConfigurations = new ArrayList<String>();				
				
				systemConfigurations.add(resultSet.getString("VALUE1")); // only the Email needed   
				systemConfigurations.add(resultSet.getString("VALUE2"));
				systemConfigurations.add(resultSet.getString("VALUE3"));
				outerCollection.add(systemConfigurations);
				
			}			
			
		} catch (SQLTimeoutException sqlto) {
			log.error("findById(Object object,Connection conn):SQLTimeoutException :" +sqlto.toString());
			throw sqlto;
		} catch (SQLException sqle){
			log.error("findById(Object object,Connection conn):SQLException :" +sqle.toString());
			throw sqle;	
		} finally{
			
			DaoHelper.cleanup(null, prstmtFind, resultSet); // connection will be closed from where it has been created
		}
		
		return outerCollection;
	}
	
	

}
