package com.genesiis.campus.entity;

//20161206 PN c26-add-student-details INIT InterestDAO.java. Implemented geAll() method.
//20161228 PN CAM-26: Removed final modifier from the ResultSet variables. Added close statement for the ResultSet with in the finally statement. 
//         PN CAM-26: Added connection.rollback() statements for the catch close.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.ApplicationStatus;

public class InterestDAO implements ICrud{
	static Logger log = Logger.getLogger(InterestDAO.class.getName());
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		final Collection<Collection<String>> allInterestList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int isActive = ApplicationStatus.ACTIVE.getStatusValue();
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME],[DESCRIPTION] FROM [CAMPUS].[INTEREST] WHERE [ISACTIVE] = ?;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, isActive);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleinterestList = new ArrayList<String>();
				singleinterestList.add(rs.getString("CODE"));
				singleinterestList.add(rs.getString("NAME"));
				singleinterestList.add(rs.getString("DESCRIPTION"));

				final Collection<String> singleInterestCollection = singleinterestList;
				allInterestList.add(singleInterestCollection);
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
		return allInterestList;
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
