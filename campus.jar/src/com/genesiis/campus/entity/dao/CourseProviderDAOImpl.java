package com.genesiis.campus.entity.dao;

//DJ 20170420 c54-report-course-stats-MP-dj Initiated CourseProviderDAOImpl.java , getLightAllCourseProviders and getCourseProviderResultSet methods.

import com.genesiis.campus.entity.CourseProviderICrud;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**The class  {@code CourseProviderDAOImpl} is a form of DAO class.
 * The Interface {@code CourseProviderDAOImpl} has precise control over course provider dao level manipulations. 
 *  @author dumani DJ   
 */
public class CourseProviderDAOImpl implements CourseProviderICrud{
	
	static Logger log = Logger.getLogger(CourseProviderDAOImpl.class.getName());


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
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Get all course provider light collection
	 * @param providerSearchDTO
	 * @author DJ
	 * @return Collection of provider list
	 */
	@Override
	public Collection<Collection<String>> getLightAllCourseProviders(CourseProviderSearchDTO providerSearchDTO) throws SQLException,
			Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet =null;
		Collection<Collection<String>> allProviderList = new ArrayList<Collection<String>>();

		try {
			conn = ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder("SELECT PROV.CODE AS CPCODE , PROV.NAME AS CPNAME  FROM [CAMPUS].COURSEPROVIDER PROV WHERE PROV.COURSEPROVIDERSTATUS = ? ");

			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, providerSearchDTO.getCourseProviderStatus());
			resultSet= stmt.executeQuery();
			allProviderList=getCourseProviderResultSet(resultSet, allProviderList);

		} catch (SQLException sqlException) {
			log.info("getLightAllCourseProviders() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getLightAllCourseProviders() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return allProviderList;
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
			singleProvider.add(rs.getString("CPNAME"));			
			allProviderList.add(singleProvider);
		}
		return allProviderList;		
	}
}
