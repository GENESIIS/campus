package com.genesiis.campus.entity;

//20161029 PN c11-criteria-based-filter-search implemented getAll() method for retrieve existing details
//		   PN c11-criteria-based-filter-search implemented findById() method. 
//20161102 PN c11-criteria-based-filter-search modified SQL query inside getAll() method.
//20160103 PN CAM-28: added JDBC property closing statements to the finally block.
//20170105 PN CAM-28: edit user information: modified DAO method coding modified with improved connection property management.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Major;
import com.genesiis.campus.util.ConnectionManager;

public class StdProfMajorDAO implements ICrud {
	static Logger log = Logger.getLogger(StdProfMajorDAO.class.getName());

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
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		int categoryCode = 0;
		String query = "";

		if (code instanceof Integer) {
			categoryCode = (Integer) code;
			query = "SELECT DISTINCT m.CODE, m.NAME, m.DESCRIPTION " + "FROM [CAMPUS].[MAJOR] m "
					+ "JOIN [CAMPUS].[PROGRAMME] p ON m.CODE = p.MAJOR "
					+ "JOIN [CAMPUS].[CATEGORY] c ON c.CODE = p.CATEGORY " + "WHERE c.CODE = ? AND c.ISACTIVE = 1;";
		} else if (code instanceof Major) {
			Major major = (Major) code;
			categoryCode = major.getCode();
			query = "SELECT m.CODE, m.NAME, m.DESCRIPTION FROM [CAMPUS].[MAJOR] m WHERE m.CODE = ? AND m.ISACTIVE = 1;";
		}

		final Collection<Collection<String>> allMajorList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, categoryCode);
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
			conn.rollback();
			log.error("findById(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			conn.rollback();
			log.error("findById(): E " + e.toString());
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allMajorList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		final Collection<Collection<String>> allMajorList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME],[DESCRIPTION] FROM [CAMPUS].[MAJOR] WHERE [ISACTIVE] = 1;";

			stmt = conn.prepareStatement(query);
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
			conn.rollback();
			log.error("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			conn.rollback();
			log.error("getAll(): E " + e.toString());
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allMajorList;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}