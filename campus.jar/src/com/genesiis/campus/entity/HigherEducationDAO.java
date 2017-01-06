package com.genesiis.campus.entity;

//20161215 PN CAM-28: INIT HigherEducationDAO.java class and implemented add() method.
//20161216 PN CAM-28 : add-student-details: implemented findById() method
//20161220 PN CAM-28: implemented delete(Object object, Connection conn) method.
//20160103 PN CAM-28: added JDBC property closing statements to the finally block.
//20160104 PN CAM-28: modified the SQL query inside findById() method.
//20170105 PN CAM-28: edit user information: modified DAO method coding modified with improved connection property management.
//20170105 PN CAM-28: update(Object object, Connection conn) DAO method implemented.
//20170106 PN CAM-28: improved Connection property handeling inside finally{} block. 
//20170106 PN CAM-28: SQL query modified to takeISACTIVE status from ApplicationStatus ENUM. 
//20170106 PN CAM-28: Object casting code moved into try{} block in applicable methods().

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.HigherEducation;
import com.genesiis.campus.util.ConnectionManager;

public class HigherEducationDAO implements ICrud{
	static Logger log = Logger.getLogger(HigherEducationDAO.class.getName());
	@Override
	public int add(Object object) throws SQLException, Exception {	
		PreparedStatement preparedStatement = null;
		Connection connection = null;

		String query = "INSERT INTO [CAMPUS].[HIGHERDUCATION] ([INSTITUTE] ,[AFFINSTITUTE] ,[STUDENT] ,[LEVEL] ,[AWARD] ,"
				+ "[MAJOR] ,[COUNTRY] ,[COMMENCEDON] ,[COMPLETIONON] ,[STUDENTID] ,[RESULT] ,[DESCRIPTION] ,[MEDIUM] ,[CRTON] ,[CRTBY]) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,getDate(),?);";

		int result = -1;

		try {
			HigherEducation data = (HigherEducation) object;
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, data.getInstitute());
			preparedStatement.setString(2, data.getAffiliatedInstitute());
			preparedStatement.setInt(3, data.getStudent());
			preparedStatement.setInt(4, data.getLevel());
			preparedStatement.setInt(5, data.getAward());
			preparedStatement.setInt(6, data.getMajor());
			preparedStatement.setInt(7, data.getCountry());
			preparedStatement.setDate(8, data.getCommencedOn());
			preparedStatement.setDate(9, data.getCompletedOn());
			preparedStatement.setString(10, data.getStudentId());
			preparedStatement.setString(11, data.getResult());
			preparedStatement.setString(12, data.getDescription());
			preparedStatement.setInt(13, data.getMedium());
			preparedStatement.setString(14, data.getCrtBy());
			
			result = preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			connection.rollback();
			log.error("add(): SQLE: " + sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			connection.rollback();
			log.error("add(): E: " + ex.toString());
			throw ex;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return result;
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
		final Collection<Collection<String>> allhigherEduList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			int studentCode = (Integer) code;
			conn = ConnectionManager.getConnection();
			String query = "SELECT he.[CODE] ,[INSTITUTE] ,[AFFINSTITUTE] ,[STUDENT] ,le.[DESCRIPTION] AS [LEVEL], aw.[SHORTTITLE] AS [AWARD], mj.[NAME] AS [MAJOR],"
					+ " co.[NAME] AS [COUNTRY] ,[COMMENCEDON] ,[COMPLETIONON] ,[STUDENTID] ,[RESULT] ,he.[DESCRIPTION] AS [DESCRIPTION] , md.[DESCRIPTION] AS [MEDIUM] "
					+ "FROM [CAMPUS].[HIGHERDUCATION] he JOIN [CAMPUS].[LEVEL] le ON he.[LEVEL] = le.[CODE] "
					+ "JOIN [CAMPUS].[COUNTRY2] co ON he.[COUNTRY] = co.[CODE] "
					+ "JOIN [CAMPUS].[AWARD] aw ON he.[AWARD] = aw.[CODE] "
					+ "JOIN [CAMPUS].[MAJOR] mj ON he.[MAJOR] = mj.[CODE] "
					+ "JOIN [CAMPUS].[MEDIUM] md ON he.[MEDIUM] = md.[CODE] WHERE STUDENT = ?;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentCode);
			rs = stmt.executeQuery();	

			while (rs.next()) {
				final ArrayList<String> singlehigherEduList = new ArrayList<String>();
				singlehigherEduList.add(rs.getString("CODE"));
				singlehigherEduList.add(rs.getString("INSTITUTE"));
				singlehigherEduList.add(rs.getString("AFFINSTITUTE"));
				singlehigherEduList.add(rs.getString("AWARD"));
				singlehigherEduList.add(rs.getString("MAJOR"));
				singlehigherEduList.add(rs.getString("COUNTRY"));
				singlehigherEduList.add(rs.getString("COMMENCEDON"));
				singlehigherEduList.add(rs.getString("COMPLETIONON"));
				singlehigherEduList.add(rs.getString("STUDENTID"));
				singlehigherEduList.add(rs.getString("RESULT"));
				singlehigherEduList.add(rs.getString("DESCRIPTION"));
				singlehigherEduList.add(rs.getString("MEDIUM"));

				final Collection<String> singlehigherEduCollection = singlehigherEduList;
				allhigherEduList.add(singlehigherEduCollection);
			}
		} catch (SQLException sqlException) {
			conn.rollback();
			log.error("findById(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			conn.rollback();
			log.error("findById(): E " + e.toString());
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
		return allhigherEduList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {		
		PreparedStatement preparedStatement = null;
		Connection connection = null;

		String query = "INSERT INTO [CAMPUS].[HIGHERDUCATION] ([INSTITUTE] ,[AFFINSTITUTE] ,[STUDENT] ,[LEVEL] ,[AWARD] ,"
				+ "[MAJOR] ,[COUNTRY] ,[COMMENCEDON] ,[COMPLETIONON] ,[STUDENTID] ,[RESULT] ,[DESCRIPTION] ,[MEDIUM] ,[CRTON] ,[CRTBY]) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,getDate(),?);";

		int result = -1;

		try {
			HigherEducation data = (HigherEducation) object;
			connection = conn;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, data.getInstitute());
			preparedStatement.setString(2, data.getAffiliatedInstitute());
			preparedStatement.setInt(3, data.getStudent());
			preparedStatement.setInt(4, data.getLevel());
			preparedStatement.setInt(5, data.getAward());
			preparedStatement.setInt(6, data.getMajor());
			preparedStatement.setInt(7, data.getCountry());
			preparedStatement.setDate(8, data.getCommencedOn());
			preparedStatement.setDate(9, data.getCompletedOn());
			preparedStatement.setString(10, data.getStudentId());
			preparedStatement.setString(11, data.getResult());
			preparedStatement.setString(12, data.getDescription());
			preparedStatement.setInt(13, data.getMedium());
			preparedStatement.setString(14, data.getCrtBy());
			
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
		PreparedStatement preparedStatement = null;
		Connection connection = null;

		String query = "UPDATE [CAMPUS].[HIGHERDUCATION]  SET [INSTITUTE] = ? ,[AFFINSTITUTE] = ? ,[STUDENT] = ? ,[LEVEL] = ?  ,[AWARD] = ? ,[MAJOR] = ? ,"
				+ "[COUNTRY] = ? ,[COMMENCEDON] = ? ,[COMPLETIONON] = ? ,[STUDENTID] = ? ,[RESULT] = ? ,[DESCRIPTION] = ? ,[MEDIUM] = ? ,"
				+ ",[MODBY] = ? WHERE [CODE] = ?;";

		int result = -1;

		try {
			HigherEducation data = (HigherEducation) object;
			connection = conn;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, data.getInstitute());
			preparedStatement.setString(2, data.getAffiliatedInstitute());
			preparedStatement.setInt(3, data.getStudent());
			preparedStatement.setInt(4, data.getLevel());
			preparedStatement.setInt(5, data.getAward());
			preparedStatement.setInt(6, data.getMajor());
			preparedStatement.setInt(7, data.getCountry());
			preparedStatement.setDate(8, data.getCommencedOn());
			preparedStatement.setDate(9, data.getCompletedOn());
			preparedStatement.setString(10, data.getStudentId());
			preparedStatement.setString(11, data.getResult());
			preparedStatement.setString(12, data.getDescription());
			preparedStatement.setInt(13, data.getMedium());
			preparedStatement.setInt(14, data.getCode());
			
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
	public int delete(Object object, Connection conn) throws SQLException, Exception {						
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = -1;

		String deleteSQL = "DELETE FROM [CAMPUS].[HIGHERDUCATION] WHERE [CODE] = ?;";

		try {
			HigherEducation data = (HigherEducation) object;
			int studentCode = data.getCode();
			connection = conn;
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, studentCode);

			// execute delete SQL stetement
			result = preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			log.error("delete(): SQLE: " + sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			log.error("delete(): E: " + ex.toString());
			throw ex;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return result;
	}

}
