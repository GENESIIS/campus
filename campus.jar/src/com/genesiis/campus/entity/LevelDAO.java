package com.genesiis.campus.entity;

//20161029 PN c11-criteria-based-filter-search implemented getAll() method for retrieve existing details
//		   PN c11-criteria-based-filter-search implemented findById() method. 
//20161101 PN c11-criteria-based-filter-search modified getAll() method SQL query.
//20161228 PN CAM-26: Removed final modifier from the ResultSet variables. Added close statement for the ResultSet with in the finally statement. 
//		   PN CAM-26: Added connection.rollback() statements for the catch close.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;

public class LevelDAO implements ICrud {
	static Logger log = Logger.getLogger(LevelDAO.class.getName());
	
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
		int categoryCode = (Integer) code;
		final Collection<Collection<String>> allLevelList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT DISTINCT l.CODE,l.NAME,l.DESCRIPTION FROM [CAMPUS].[LEVEL] l JOIN [CAMPUS].[PROGRAMME] p ON l.CODE = p.LEVEL JOIN [CAMPUS].[CATEGORY] m ON m.CODE = p.CATEGORY WHERE m.CODE = ? AND m.ISACTIVE = 1;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, categoryCode);
			rs = stmt.executeQuery();			
			
			while (rs.next()) {
				final ArrayList<String> singleLevelList = new ArrayList<String>();
				singleLevelList.add(rs.getString("CODE"));
				singleLevelList.add(rs.getString("NAME"));
				singleLevelList.add(rs.getString("DESCRIPTION"));

				final Collection<String> singleLevelCollection = singleLevelList;
				allLevelList.add(singleLevelCollection);
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
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
		return allLevelList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		final Collection<Collection<String>> allLevelList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME],[DESCRIPTION] FROM [CAMPUS].[LEVEL] WHERE [ISACTIVE] = 1;";

			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleLevelList = new ArrayList<String>();
				singleLevelList.add(rs.getString("CODE"));
				singleLevelList.add(rs.getString("NAME"));
				singleLevelList.add(rs.getString("DESCRIPTION"));

				final Collection<String> singleLevelCollection = singleLevelList;
				allLevelList.add(singleLevelCollection);
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
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
		return allLevelList;
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
