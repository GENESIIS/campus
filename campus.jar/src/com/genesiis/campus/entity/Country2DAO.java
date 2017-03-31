package com.genesiis.campus.entity;
//20161125 PN c26-add-student-details: INIT the class and getAll() method implemented.
//			  c26-add-student-details: getAll() method SQL query modified.
//20161126 PN c26-add-student-details: findById() method implemented.
//20160103 PN CAM-28: added JDBC property closing statements to the finally block.
//20170105 PN CAM-28: edit user information: modified DAO method coding modified with improved connection property management.
//20170106 PN CAM-28: improved Connection property handeling inside finally{} block. 
//20170106 PN CAM-28: SQL query modified to takeISACTIVE status from ApplicationStatus ENUM. 
//20170106 PN CAM-28: Object casting code moved into try{} block in applicable methods().
//20170310 PN CAM-150: isCountryExists(int countryCode) method implemented to validate country value.
//20170312 PN CAM-150: SQL query modified in isCountryExists() method.
//20170313 PN CAM-150: isCountryExists() method renamed into isLocaleExists(). SQL query modified in it.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;
import com.genesiis.campus.util.ConnectionManager;

public class Country2DAO implements ICrud{
	static Logger log = Logger.getLogger(Country2DAO.class.getName());
	
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
		final Collection<Collection<String>> allCountryList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			int countryCode = (Integer) code;
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME] FROM [CAMPUS].[COUNTRY2] WHERE [CODE] NOT IN (-1) AND [CODE] = ?;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, countryCode);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleCountryList = new ArrayList<String>();
				singleCountryList.add(rs.getString("CODE"));
				singleCountryList.add(rs.getString("NAME"));

				final Collection<String> singleCountryCollection = singleCountryList;
				allCountryList.add(singleCountryCollection);
			}
		} catch (SQLException sqlException) {
			log.error("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
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
		return allCountryList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		final Collection<Collection<String>> allCountryList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME] FROM [CAMPUS].[COUNTRY2] WHERE [CODE] NOT IN (-1);";

			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleCountryList = new ArrayList<String>();
				singleCountryList.add(rs.getString("CODE"));
				singleCountryList.add(rs.getString("NAME"));

				final Collection<String> singleCountryCollection = singleCountryList;
				allCountryList.add(singleCountryCollection);
			}
		} catch (SQLException sqlException) {
			log.error("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
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
		return allCountryList;
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
	
	/**
	 * 	This method will check and validate if the country entered is a valid value.
	 * @param countryCode
	 * @return true; if SQL query has a record.
	 */
	public static boolean isLocaleExists(String countryName,String townName) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count = -1;
		
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT count(*) FROM [CAMPUS].[COUNTRY2] c INNER JOIN [CAMPUS].[TOWN] t ON t.[COUNTRY]=c.[CODE] WHERE c.[NAME]=? AND t.[NAME]=?;";

			stmt = conn.prepareStatement(query);
			stmt.setString(1, countryName);
			stmt.setString(2, townName);
			rs = stmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException sqlException) {
			log.error("isCountryExists(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("isCountryExists(): E " + e.toString());
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
		return (count > 0) ? true : false;
	}
}
