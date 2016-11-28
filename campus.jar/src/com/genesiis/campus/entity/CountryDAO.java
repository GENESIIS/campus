package com.genesiis.campus.entity;

//20161123 CM c36-add-tutor-information INIT CountryDAO 
//20161123 CM c36-add-tutor-information Modified getAll() method. 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;

public class CountryDAO implements ICrud{

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


	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns all the country details in Database
	 * 
	 * @author Chathuri, Chinthaka
	 * 
	 * @return Returns all the country details from a collection of collection
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		final Collection<Collection<String>> allCountryList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [DIALCODE],[NAME] FROM [CAMPUS].[COUNTRY2]";

			stmt = conn.prepareStatement(query);
			final ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleCountryList = new ArrayList<String>();
				singleCountryList.add(rs.getString("DIALCODE"));
				singleCountryList.add(rs.getString("NAME"));

				final Collection<String> singleCountryCollection = singleCountryList;
				allCountryList.add(singleCountryCollection);
			}
		} catch (SQLException sqlException) {
			log.info("getAll(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll(): Exception " + e.toString());
			throw e;
		} finally {
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
