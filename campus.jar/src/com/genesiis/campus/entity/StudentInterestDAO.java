package com.genesiis.campus.entity;

//20161206 PN c26-add-student-details INIT StudentInterestDAO.java. Implemented geAll() method.
//PN c26-add-student-details INIT StudentSkillDAO.java. Implemented add(object,conn) and delete(object,conn) method.
//20160103 PN CAM-28: added JDBC property closing statements to the finally block.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;
import com.genesiis.campus.entity.model.StudentInterest;
import com.genesiis.campus.entity.model.StudentSkill;
import com.genesiis.campus.util.ConnectionManager;

public class StudentInterestDAO implements ICrud{
	static Logger log = Logger.getLogger(StudentInterestDAO.class.getName());
	
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
		int studentCode = (Integer) code;
		final Collection<Collection<String>> studentInterestList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT I.[CODE],I.[NAME],I.[DESCRIPTION] "
					+ "FROM [CAMPUS].[STUDENTINTEREST] SI "
					+ "INNER JOIN [CAMPUS].[INTEREST] I ON "
					+ "SI.[INTEREST] = I.[CODE] WHERE SI.[STUDENT] = ?;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentCode);
			rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				final ArrayList<String> singleInterest = new ArrayList<String>();
				singleInterest.add(rs.getString("CODE"));
				singleInterest.add(rs.getString("NAME"));
				singleInterest.add(rs.getString("DESCRIPTION"));

				final Collection<String> singleInterestCollection = singleInterest;
				studentInterestList.add(singleInterestCollection);
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
		return studentInterestList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {
		StudentInterest data = (StudentInterest) object;
		PreparedStatement preparedStatement = null;
		Connection connection = conn;

		String query = "INSERT INTO [CAMPUS].[STUDENTINTEREST] ([STUDENT], [INTEREST], [CRTON], [CRTBY]) "
				+ "VALUES(?, ?, (getdate()), ?);";

		int result = -1;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, data.getStudent());
			preparedStatement.setInt(2, data.getInterest());
			preparedStatement.setString(3, data.getCrtBy());
			
			result = preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			log.error("add(): SQLE: " + sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			log.error("add(): E: " + ex.toString());
			throw ex;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return result;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException, Exception {
		StudentInterest data = (StudentInterest) object;
		PreparedStatement preparedStatement = null;
		Connection connection = conn;

		String query = "DELETE FROM  [CAMPUS].[STUDENTINTEREST] WHERE [STUDENT] = ? AND [INTEREST] = ?;";

		int result = -1;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, data.getStudent());
			preparedStatement.setInt(2, data.getInterest());
			
		
			result = preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			log.error("add(): SQLE: " + sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			log.error("add(): E: " + ex.toString());
			throw ex;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return result;
	}

}
