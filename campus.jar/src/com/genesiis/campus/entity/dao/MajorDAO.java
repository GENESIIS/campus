package com.genesiis.campus.entity.dao;

//DJ 20161115 c17-provider-criteria-based-filter-search-MP-dj created MajorDAO.java
//DJ 20161115 c17-provider-criteria-based-filter-search-MP-dj Implement getAll()
//DJ 20161125 c17-provider-criteria-based-filter-search-MP-dj Implement findMajorsByMajorCodes() method

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class MajorDAO implements ICrud{
	static org.apache.log4j.Logger log = Logger.getLogger(MajorDAO.class.getName());

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
	 * Get all major list details
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
		final Collection<Collection<String>> allMajorList=new ArrayList<Collection<String>>();
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME],[DESCRIPTION] FROM [CAMPUS].[MAJOR] WHERE [ISACTIVE] = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleMajorList = new ArrayList<String>();
				singleMajorList.add(rs.getString("CODE"));
				singleMajorList.add(rs.getString("NAME"));
				singleMajorList.add(rs.getString("DESCRIPTION"));

				final Collection<String> singleMajorCollection = singleMajorList;
				allMajorList.add(singleMajorCollection);
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
		
		return allMajorList;
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
	 * Get all major list details by major code set
	 * @param majorCodeSet
	 * @author DJ
	 * @return Collection 
	 */

	public Collection<Collection<String>> findMajorsByMajorCodes(Set<Integer> majorCodeSet)throws SQLException,Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Collection<Collection<String>> allMajorList=new ArrayList<Collection<String>>();
		try {
			conn = ConnectionManager.getConnection();
			final StringBuilder sb =new StringBuilder(" SELECT MAJOR.[CODE] AS MAJORCODE,MAJOR.[NAME] AS MAJORNAME FROM [CAMPUS].[MAJOR] MAJOR ");
			sb.append(" WHERE MAJOR.ISACTIVE = ? AND MAJOR.CODE IN( " );
			boolean doneOne = false;
			for (Integer code : majorCodeSet) {
				if (doneOne) {
					sb.append(", ");
				}
				sb.append(code);
				doneOne = true;
			}
			sb.append(")" );

			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleMajorList = new ArrayList<String>();
				singleMajorList.add(rs.getString("MAJORCODE"));
				singleMajorList.add(rs.getString("MAJORNAME"));
				final Collection<String> singleMajorCollection = singleMajorList;
				allMajorList.add(singleMajorCollection);
			}
		} catch (SQLException sqlException) {
			log.info("findMajorsByMajorCodes() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findMajorsByMajorCodes() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		
		return allMajorList;
	}	

}
