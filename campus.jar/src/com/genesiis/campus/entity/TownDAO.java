package com.genesiis.campus.entity;

//20161029 PN c11-criteria-based-filter-search implemented getAll() method for retrieve existing details
//20161205 PN c26-add-student-details: implemented findById() method for retrieve towns for given country code.
//20170201 JH c39-add-course-provider arranged imports according to the style guide

import com.genesiis.campus.util.ConnectionManager;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class TownDAO implements ICrud {
	static Logger log = Logger.getLogger(TownDAO.class.getName());

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
		int countryCode = (Integer) code;
		final Collection<Collection<String>> allTownList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME],[DISTRICT] FROM [CAMPUS].[TOWN] WHERE [COUNTRY] = ?;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, countryCode);
			final ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleTownList = new ArrayList<String>();
				singleTownList.add(rs.getString("CODE"));
				singleTownList.add(rs.getString("NAME"));
				singleTownList.add(rs.getString("DISTRICT"));

				final Collection<String> singleTownCollection = singleTownList;
				allTownList.add(singleTownCollection);
			}
		} catch (SQLException sqlException) {
			log.info("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll(): E " + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return allTownList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		final Collection<Collection<String>> allTownList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME],[DESCRIPTION],[IMAGE],[ISACTIVE] FROM [CAMPUS].[Town] WHERE [ISACTIVE] = 1;";

			stmt = conn.prepareStatement(query);
			final ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleTownList = new ArrayList<String>();
				singleTownList.add(rs.getString("CODE"));
				singleTownList.add(rs.getString("NAME"));
				singleTownList.add(rs.getString("DESCRIPTION"));

				final Collection<String> singleTownCollection = singleTownList;
				allTownList.add(singleTownCollection);
			}
		} catch (SQLException sqlException) {
			log.info("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll(): E " + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allTownList;
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