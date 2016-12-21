package com.genesiis.campus.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

public class TutorUserName implements ICrud {

	static Logger log = Logger.getLogger(TutorDAO.class.getName());
	
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
	
	/**
	 * Returns the username in Database for the given Tutor Code
	 * 
	 * @author Chathuri, Chinthaka
	 * 
	 * @return Returns the username from a collection of collection
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)	throws SQLException, Exception {
		
		final Collection<Collection<String>> allTutorList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Tutor tutor = (Tutor) code; 
			conn = ConnectionManager.getConnection();
			String query = "SELECT [USERNAME] FROM [CAMPUS].[TUTOR] WHERE USERNAME=?";

			stmt = conn.prepareStatement(query);
			stmt.setString(1, tutor.getUsername());
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleTutorList = new ArrayList<String>();
				singleTutorList.add(rs.getString("USERNAME"));
				allTutorList.add(singleTutorList);
			}
		} catch (ClassCastException cce) {
			log.info("findById(): ClassCastException " + cce.toString());
			throw cce;
		} catch (SQLException sqlException) {
			log.info("findById(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById(): Exception " + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		return allTutorList;

	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
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
