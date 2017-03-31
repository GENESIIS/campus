package com.genesiis.campus.entity;


//20161122 JH c39-add-course-provider CourseProviderDAO created
//20161123 JH c39-add-course-provider add method coding wip
//20161128 JH c39-add-course-provider add method coding wip
//20161129 JH c39-add-course-provider add method coding wip
//20161129 JH c39-add-course-provider CourseProviderDAO class renamed as FeaturedCourseProviderDAO
//20161202 JH c39-add-course-provider add method modified
//20161208 JH c39-add-course-provider error fixed and code ReFactored
//20161209 JH c39-add-course-provider findById method modified: removed query used to get userType
//20161219 JH c39-add-course-provider code review modifications: use generics 
//20161219 JH c39-add-course-provider fixed error in prepared statement
//20161229 JH c39-add-course-provider added queries to insert data into course provider town table
//20170102 JH c39-add-course-provider code modified to fix number format exception in course provider town entity
//20170103 JH c39-add-course-provider town query changed due to course provider town table changes
//20170117 JH c39-add-course-provider implemented DaoHelper class to close resources
//20170118 JH c39-add-course-provider qa modifications: fixed description not added in course provider account
//20170125 JH c39-add-course-provider assign course provider address details from course provider entity for preparedStatement3
//20170201 JH c39-add-course-provider arranged imports according to the style guide
//20170202 JH c39-add-course-provider query string account modified: select user type code selected inside the insert query
//20170202 JH c39-add-course-provider removed repeating statements used to set parameters to preparedStatement
//20170203 JH c39-add-course-provider mx fixes: modified the error log statement
//20170208 DN c131-admin-manage-banner-upload-banner-image-dn created getAll()

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.AccountType;
import com.genesiis.campus.validation.UserType;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FeaturedCourseProviderDAO implements ICrud {

	static Logger log = Logger.getLogger(FeaturedCourseProviderDAO.class
			.getName());

	/**
	 * add method used to create a new featured course provider record.
	 * 
	 * @author JH
	 * @param object
	 *            type of hash map
	 * @return int to indicate success or fail
	 */
	@Override
	public int add(Object object) throws SQLException, Exception {
		
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
		StringBuilder CourseProvidersSQL =new StringBuilder("SELECT [CODE] ,[SHORTNAME]  FROM [CAMPUS].[COURSEPROVIDER]");
		CourseProvidersSQL.append("WHERE COURSEPROVIDERSTATUS IN (1) AND ACCOUNTTYPE = 1");		
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
				courseProviderRecord.add(resultSet.getString("SHORTNAME"));
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