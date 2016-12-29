package com.genesiis.campus.entity;

//DJ 20161206 c51-report-courses-by-course-provider-MP-dj created CourseProviderDAO.java
//DJ 20161229 c51-report-courses-by-course-provider-MP-dj Implement findById() method 

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CourseProviderDAO  implements ICrud{
	
	static org.apache.log4j.Logger log = Logger.getLogger(CourseProviderDAO.class.getName());
	
	/**
	 * Get all types of active course providers
	 * @param 
	 * @author DJ
	 * @return Collection 
	 */

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet =null;
		Collection<Collection<String>> allProviderList = new ArrayList<Collection<String>>();

		/*try {
			conn = ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder("SELECT PROV.CODE AS CPCODE , PROV.UNIQUEPREFIX  FROM [CAMPUS].COURSEPROVIDER PROV WHERE PROV.COURSEPROVIDERSTATUS = ? ");

			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			resultSet= stmt.executeQuery();
			allProviderList=getCourseProviderResultSet(resultSet, allProviderList);

		} catch (SQLException sqlException) {
			log.info("findById() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}*/
		return allProviderList;
	}

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
	
	/**
	 * Get Course Providers.If  CourseProviderStatus is set to particular status result set could return according to the set status.	 * 
	 * @param CourseProvider DTO
	 * @author DJ
	 * @return Collection 
	 */
	@Override
	public Collection<Collection<String>> findById(Object provider)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Collection<Collection<String>> allProviderList = new ArrayList<Collection<String>>();

		try {
			 CourseProvider courseProvider = new CourseProvider();
			if (UtilityHelper.isNotEmptyObject(provider)) {
				courseProvider = (CourseProvider) provider;
			}
			conn = ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder("SELECT PROV.CODE AS CPCODE , PROV.UNIQUEPREFIX AS CPUNIQUEPREFIX, PROV.COURSEPROVIDERSTATUS AS CPSTATUS  FROM [CAMPUS].COURSEPROVIDER PROV WHERE 1=1");
			if (courseProvider.getCourseProviderStatus() > 0) {
				sb.append(" AND PROV.COURSEPROVIDERSTATUS = ");
				sb.append(courseProvider.getCourseProviderStatus());
			}

			stmt = conn.prepareStatement(sb.toString());

			resultSet = stmt.executeQuery();
			allProviderList = getCourseProviderResultSet(resultSet,allProviderList);

		} catch (SQLException sqlException) {
			log.info("findById() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return allProviderList;
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
	
	/**
	 * @param rs
	 * @param allProviderList
	 * @author DJ
	 * @return Collection
	 * @throws SQLException
	 */
	private  Collection<Collection<String>> getCourseProviderResultSet(ResultSet rs, Collection<Collection<String>> allProviderList)throws SQLException ,Exception{
		while (rs.next()) {				
			final ArrayList<String> singleProvider = new ArrayList<String>();
			singleProvider.add(rs.getString("CPCODE"));				
			singleProvider.add(rs.getString("CPUNIQUEPREFIX"));			
			singleProvider.add(rs.getString("CPSTATUS"));			
			allProviderList.add(singleProvider);
		}
		return allProviderList;
		
	}



}
