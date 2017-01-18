package com.genesiis.campus.entity;

//20170117 CW c36-add-tutor-information INIT TutorEmailDAO.java
//20170117 CW c36-add-tutor-information modified findById()

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.command.CmdCheckEmail;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import org.apache.log4j.Logger;

public class TutorEmailDAO implements ICrud {
	static Logger log = Logger.getLogger(TutorEmailDAO.class.getName());

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

		final Collection<Collection<String>> allTutorEmailList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Tutor tutor = (Tutor) code; 
			conn = ConnectionManager.getConnection();
			String query = "SELECT [EMAIL] FROM [CAMPUS].[TUTOR] WHERE EMAIL=?";

			stmt = conn.prepareStatement(query);
			stmt.setString(1, tutor.getEmailAddress());
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleTutorEmailList = new ArrayList<String>();
				singleTutorEmailList.add(rs.getString("USERNAME"));
				allTutorEmailList.add(singleTutorEmailList);
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
		return allTutorEmailList;

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
