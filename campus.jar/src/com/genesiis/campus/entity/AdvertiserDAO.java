package com.genesiis.campus.entity;

/*
 * 20170203 DN c131-admin-manage-banner-upload-banner-image-dn created the initial class stub
 * 20170221 DN c131-admin-manage-banner-upload-banner-image-dn created getAll()
 */

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AdvertiserDAO implements ICrud {
	static Logger log = Logger.getLogger(AdvertiserDAO.class
			.getName());

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

	/**
	 * getAll() method bears the responsibility of extracting all the course providers
	 * who are featured and status been 1
	 * 
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		// Extract all the course providers who are featured providers and status is one
				StringBuilder CourseProvidersSQL =new StringBuilder("SELECT [CODE] ,[NAME]  FROM [CAMPUS].[ADVERTISER]");
				CourseProvidersSQL.append("WHERE [ISACTIVE] = 1");		
				String allCourseProviderSQL = CourseProvidersSQL.toString();
				Connection courseProviderConnection =null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				
				try{
					courseProviderConnection =  ConnectionManager.getConnection();
					preparedStatement =courseProviderConnection.prepareStatement(allCourseProviderSQL);
					resultSet = preparedStatement.executeQuery();
					final Collection<Collection<String>> outerWrapper = new ArrayList<Collection<String>>();
					
					while(resultSet.next()){
						final ArrayList<String>courseProviderRecord = new ArrayList<String>();
						courseProviderRecord.add(resultSet.getString("CODE"));
						courseProviderRecord.add(resultSet.getString("NAME"));
						outerWrapper.add(courseProviderRecord);
					}
					
					return outerWrapper;
					
				}catch (SQLException sqle){
					log.error("getAll() :SQLException"+sqle.toString());
					throw sqle;
				} catch (Exception exp) {
					log.error("getAll() :Exception"+exp.toString());
					throw exp;
				} finally{
					DaoHelper.cleanup(courseProviderConnection, preparedStatement, resultSet);
				}
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
		// TODO Auto-generated method stub
		return null;
	}

}
