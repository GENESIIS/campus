package com.genesiis.campus.entity;

//20161122 CM c36-add-tutor-information Modified getAll() method. 
//20161216 CW c36-add-tutor-details Modified getAll() method. 
//20161221 CW c36-add-tutor-details Modified getAll() method. 
//20161222 CW c38-view-update-tutor-profile added findTownByCode() method. 
//20170109 CW c36-add-tutor-details add findById() method from c18 - student : signup : without using third party application TownDAO class
//20170118 CW c38-view-update-tutor-profile - removed findTownByCode() method
//20170124 CW c36-add-tutor-details modified findById() method same as findById() in CountryDAO.java class according to the 201701201215 DJ crev modification request.
//20170207 CW c38-view-update-tutor-profile-re organize the import statements.
//20170209 CW c38-view-update-tutor-profile-re modified getAll() method query to order by sort
//20170216 CW c38-view-update-tutor-profile Add class comment & removed commented lines
//20170223 CW c36-add-tutor-information re-organise the import statements.

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * this class used to manage the town related data 
 * further it implements ICrud interface
 */
public class TownDAO implements ICrud{
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

	/*
	 * @author DN - taken from c18 - student : signup : without using third party application TownDAO class
	 * @see com.genesiis.campus.entity.ICrud#findById(java.lang.Object)
	 */
	@Override
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		int countryCode  = (Integer) code;
		final Collection<Collection<String>> allTownList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME],[DISTRICT] FROM [CAMPUS].[TOWN] WHERE [COUNTRY] = ?;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, countryCode);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleTownList = new ArrayList<String>();
				singleTownList.add(rs.getString("CODE"));
				singleTownList.add(rs.getString("NAME"));
				singleTownList.add(rs.getString("DISTRICT"));
				allTownList.add(singleTownList);
			}
		} catch (SQLException sqlException) {
			log.info("findById(Object): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById(Object): Exception " + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		
		return allTownList;
	}
	
	/**
	 * Returns all the town details in Database
	 * 
	 * @author Chathuri, Chinthaka
	 * 
	 * @return Returns all the town details from a collection of collection
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		
		final Collection<Collection<String>> allTownList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT [CODE],[NAME], [COUNTRY] FROM [CAMPUS].[Town] ORDER BY [SORT]";

			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleTownList = new ArrayList<String>();
				singleTownList.add(rs.getString("CODE"));
				singleTownList.add(rs.getString("NAME"));
				singleTownList.add(rs.getString("COUNTRY"));
				allTownList.add(singleTownList);
			}
		} catch (SQLException sqlException) {
			log.info("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll(): E " + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		return allTownList;
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