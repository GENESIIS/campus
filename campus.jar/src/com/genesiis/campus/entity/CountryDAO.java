package com.genesiis.campus.entity;

//20161123 CM c36-add-tutor-information INIT CountryDAO 
//20161123 CM c36-add-tutor-information Modified getAll() method. 
//20161216 CW c36-add-tutor-details Modified getAll() method. 
//20161221 CW c36-add-tutor-details Modified getAll() method. 
//20161222 CW c38-view-update-tutor-profile added findCountryByCode() method. 
//20170109 CW c36-add-tutor-details add findById() method from c18 - student : signup : without using third party application Country2DAO class
//20170124 CW c36-add-tutor-details modified findById() method according to the 201701201215 DJ crev modification request.
//20170130 CW c36-add-tutor-information re-organize the import statements.
//20170207 CW c38-view-update-tutor-profile- modified findCountryByCode() method
//20170209 CW c38-view-update-tutor-profile-re modified getAll() method query to order by sort

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CountryDAO implements ICrud{

	private static final Collection<Collection<String>> NULL = null;
	static Logger log = Logger.getLogger(CountryDAO.class.getName());


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

/*
 * @author DN - taken from c18 - student : signup : without using third party application Country2DAO class
 * @see com.genesiis.campus.entity.ICrud#findById(java.lang.Object)
 */
	/**
	 * Returns the country Name in Database for given country code
	 * 
	 * @author Chinthaka
	 * 
	 * @param Country code as int value
	 * 
	 * @return Returns the country name as a String
	 */
	public String findCountryByCode(int code)
			throws SQLException, Exception {
/*		
		final Collection<String> allCountryList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String countryName = null;

		try {
						
			String query = "SELECT [NAME] FROM [CAMPUS].[COUNTRY2] WHERE CODE=?";
			
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, code);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				countryName = rs.getString("NAME");
			}
			
		} catch (SQLException sqlException) {
			log.info("findCountryByCode(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findCountryByCode(): Exception " + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}*/
		return "need to change com.genesiis.campus.entity.CountryDAO.findCountryByCode(int)";
		
	}

	@Override
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		int countryCode = (Integer) code;
		final Collection<Collection<String>> allCountryList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME] FROM [CAMPUS].[COUNTRY2] WHERE [CODE] NOT IN (-1) AND [CODE] = ?;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, countryCode);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleCountryList = new ArrayList<String>();
				singleCountryList.add(rs.getString("CODE"));
				singleCountryList.add(rs.getString("NAME"));
				allCountryList.add(singleCountryList);
			}
		} catch (SQLException sqlException) {
			log.info("findById(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById(): Exception " + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		return allCountryList;
}

	/**
	 * Returns all the country details in Database
	 * 
	 * @author Chathuri, Chinthaka
	 * 
	 * @return Returns all the country details from a collection of collection
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException,	Exception {
		
		final Collection<Collection<String>> allCountryList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			
			String query = "SELECT [DIALCODE],[NAME] FROM [CAMPUS].[COUNTRY2] ORDER BY [SORT]";
			
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleCountryList = new ArrayList<String>();
				singleCountryList.add(rs.getString("DIALCODE"));
				singleCountryList.add(rs.getString("NAME"));
				allCountryList.add(singleCountryList);
			}
		} catch (SQLException sqlException) {
			log.info("getAll(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll(): Exception " + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		return allCountryList;
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
