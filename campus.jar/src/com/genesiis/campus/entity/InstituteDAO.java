package com.genesiis.campus.entity;

//20161101 PN c11-criteria-based-filter-search implemented findById() method. 
//20161102 PN c11-criteria-based-filter-search modified the sql query in findById() method.
//		   PN c11-criteria-based-filter-search implemented getAll() method.
//20161103 PN c11-criteria-based-filter-search modified SQL query inside getAll() and findById() methods

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;

public class InstituteDAO implements ICrud{
	static Logger log = Logger.getLogger(InstituteDAO.class.getName());
	
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
		int categoryCode = (Integer) code;
		final Collection<Collection<String>> allInstituteList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT DISTINCT l.CODE,l.NAME,l.UNIQUEPREFIX FROM [CAMPUS].[COURSEPROVIDER] l JOIN [CAMPUS].[PROGRAMME] p ON l.CODE = p.COURSEPROVIDER JOIN [CAMPUS].[CATEGORY] m ON m.CODE = p.CATEGORY WHERE m.CODE = ? AND l.COURSEPROVIDERSTATUS = 1;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, categoryCode);
			final ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				final ArrayList<String> singleInstituteList = new ArrayList<String>();
				singleInstituteList.add(rs.getString("CODE"));
				singleInstituteList.add(rs.getString("NAME"));
				singleInstituteList.add(rs.getString("UNIQUEPREFIX"));

				final Collection<String> singleLevelCollection = singleInstituteList;
				allInstituteList.add(singleLevelCollection);
			}
		} catch (SQLException sqlException) {
			log.error("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("getAll(): E " + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allInstituteList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		final Collection<Collection<String>> allInstituteList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT DISTINCT CODE,NAME,UNIQUEPREFIX FROM [CAMPUS].[COURSEPROVIDER] WHERE COURSEPROVIDERSTATUS = 1;";

			stmt = conn.prepareStatement(query);
			final ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				final ArrayList<String> singleInstituteList = new ArrayList<String>();
				singleInstituteList.add(rs.getString("CODE"));
				singleInstituteList.add(rs.getString("NAME"));
				singleInstituteList.add(rs.getString("UNIQUEPREFIX"));

				final Collection<String> singleLevelCollection = singleInstituteList;
				allInstituteList.add(singleLevelCollection);
			}
		} catch (SQLException sqlException) {
			log.error("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("getAll(): E " + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allInstituteList;
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

}
