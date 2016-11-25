package com.genesiis.campus.entity;

//DJ 20161115 c17-provider-criteria-based-filter-search-MP-dj created CourseProviderTypeDAO.java
//DJ 20161115 c17-provider-criteria-based-filter-search-MP-dj Implement getAll()
//DJ 20161125 c17-provider-criteria-based-filter-search-MP-dj Implement findCPTypesByCodes() method

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

public class CourseProviderTypeDAO implements ICrud{
	
	static org.apache.log4j.Logger log = Logger.getLogger(CourseProviderTypeDAO.class.getName());

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
	 * Get all course provider type details
	 * @param 
	 * @author DJ
	 * @return Collection 
	 */

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Collection<Collection<String>> allCourseProviderTypeList=new ArrayList<Collection<String>>();
		try {
			conn=ConnectionManager.getConnection();
			String sql="SELECT CPTYPE.CODE AS CPTYPECODE , CPTYPE.NAME AS CPTYPENAME FROM [CAMPUS].COURSEPROVIDERTYPE CPTYPE WHERE CPTYPE.ISACTIVE=1 ";
			
			stmt=conn.prepareStatement(sql.toString());
			rs=stmt.executeQuery();
			
			while (rs.next()) {				
				final ArrayList<String> singleCPType = new ArrayList<String>();
				singleCPType.add(rs.getString("CPTYPECODE"));				
				singleCPType.add(rs.getString("CPTYPENAME"));				
				allCourseProviderTypeList.add(singleCPType);
			}
		} catch (SQLException sqlException) {
			log.info("getAll() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		
		return allCourseProviderTypeList;
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
	 * Get all course provider type details by course provider type codes
	 * @param cpTypeSet
	 * @author DJ
	 * @return Collection 
	 */
	public Collection<Collection<String>> findCPTypesByCodes(Set<Integer> cpTypeCodeSet) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final Collection<Collection<String>> courseProviderTypeList = new ArrayList<Collection<String>>();
		try {
			conn = ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder(" SELECT CPTYPE.CODE AS CPTYPECODE , CPTYPE.NAME AS CPTYPENAME FROM [CAMPUS].COURSEPROVIDERTYPE CPTYPE ");
			sb.append("	WHERE CPTYPE.ISACTIVE=1 AND CPTYPE.CODE IN (");
			boolean doneOne = false;
			for (Integer code : cpTypeCodeSet) {
				if (doneOne) {
					sb.append(", ");
				}
				sb.append(code);
				doneOne = true;
			}
			sb.append(" ) ");

			stmt = conn.prepareStatement(sb.toString());
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleCPType = new ArrayList<String>();
				singleCPType.add(rs.getString("CPTYPECODE"));
				singleCPType.add(rs.getString("CPTYPENAME"));
				courseProviderTypeList.add(singleCPType);
			}
		} catch (SQLException sqlException) {
			log.info("findByCPTypes() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findByCPTypes() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}

		return courseProviderTypeList;
	}

}
