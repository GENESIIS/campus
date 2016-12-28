package com.genesiis.campus.entity;

//20161206 PN c26-add-student-details INIT StudentSkillDAO.java. Implemented geAll() method.
//		   PN c26-add-student-details INIT StudentSkillDAO.java. Implemented add(object,conn) and delete(object,conn) method.
//20161228 PN CAM-26: Removed final modifier from the ResultSet variables. Added close statement for the ResultSet with in the finally statement. 
//		   PN CAM-26: Added connection.rollback() statements for the catch close.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.StudentSkill;
import com.genesiis.campus.util.ConnectionManager;

public class StudentSkillDAO implements ICrud{
	static Logger log = Logger.getLogger(StudentSkillDAO.class.getName());
	
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
		final Collection<Collection<String>> studentSkillList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			int studentCode = (Integer) code;
			conn = ConnectionManager.getConnection();
			String query = "SELECT K.[CODE],K.[NAME],K.[DESCRIPTION] "
					+ "FROM [CAMPUS].[STUDENTSKILL] SK "
					+ "INNER JOIN [CAMPUS].[SKILL] K ON "
					+ "SK.[SKILL] = K.[CODE] WHERE SK.[STUDENT] = ?;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentCode);
			rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				final ArrayList<String> singleSkill = new ArrayList<String>();
				singleSkill.add(rs.getString("CODE"));
				singleSkill.add(rs.getString("NAME"));
				singleSkill.add(rs.getString("DESCRIPTION"));

				final Collection<String> singleLevelCollection = singleSkill;
				studentSkillList.add(singleLevelCollection);
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
		return studentSkillList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {	
		PreparedStatement preparedStatement = null;
		Connection connection = conn;

		String query = "INSERT INTO [CAMPUS].[STUDENTSKILL] ([STUDENT], [SKILL], [CRTON], [CRTBY]) "
				+ "VALUES(?, ?, (getdate()), ?);";

		int result = -1;

		try {
			StudentSkill data = (StudentSkill) object;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, data.getStudent());
			preparedStatement.setInt(2, data.getSkill());
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
		
		PreparedStatement preparedStatement = null;
		Connection connection = conn;

		String query = "DELETE FROM  [CAMPUS].[STUDENTSKILL] WHERE [STUDENT] = ? AND [SKILL] = ?;";

		int result = -1;

		try {
			StudentSkill data = (StudentSkill) object;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, data.getStudent());
			preparedStatement.setInt(2, data.getSkill());	
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
