package com.genesiis.campus.entity;
//20161128 PN c26-add-student-details: INIT ProfessionalExperienceDAO.java class.

//		   PN c26-add-student-details: implemented add() method.
//20161129 PN c26-add-student-details: modified SQL query inside add() method.
//20161208 PN CAM-26 : add-student-details: implemented add(object,Connection) method
//20161215 PN CAM-28 : add-student-details: implemented findById() method
//20161216 PN CAM-28 : re-implemented findById() method
//20161220 PN CAM-28: implemented delete(Object object, Connection conn) method.
//20160103 PN CAM-28: added JDBC property closing statements to the finally block.
//20170103 PN CAM-28: changed the findById(Object object) method by giving condition to check different types of the 'object' parameter.
//20170105 PN CAM-28: edit user information: modified DAO method coding modified with improved connection property management.
//20170105 PN CAM-28: update(Object object, Connection conn) DAO method implemented.
//20170106 PN CAM-28: improved Connection property handeling inside finally{} block. 
//20170106 PN CAM-28: SQL query modified to takeISACTIVE status from ApplicationStatus ENUM. 
//20170106 PN CAM-28: Object casting code moved into try{} block in applicable methods().
//20170110 PN CAM-28: SQL query modified inside DAO methods. 
//20170118 PN CAM-28: modified findByIdMethod() method by removing different DB connection to select JOB INDUSTRY and JOB CATEGORY. existing SQL query modified to a JOIN to select details using the same DB connection.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.command.CmdAddProfessionalExpDetails;
import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Major;
import com.genesiis.campus.entity.model.ProfessionalExperience;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.ApplicationStatus;

public class ProfessionalExperienceDAO implements ICrud {
	static Logger log = Logger.getLogger(ProfessionalExperienceDAO.class.getName());

	@Override
	public int add(Object object) throws SQLException, Exception {
		PreparedStatement preparedStatement = null;
		Connection connection = null;

		String query = "INSERT INTO [CAMPUS].[PROFESSIONALEXPERIENCE] ([ORGANIZATION], [STUDENT], [INDUSTRY],"
				+ " [JOBCATEGORY], [DESIGNATION], [COMMENCEDON], [COMPLETIONON], [DESCRIPTION],[CRTBY]) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?,?);";

		int result = -1;

		try {
			ProfessionalExperience data = (ProfessionalExperience) object;
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(6, data.getOrganization());
			preparedStatement.setInt(1, data.getStudent());
			preparedStatement.setInt(3, data.getIndustry());
			preparedStatement.setInt(2, data.getJobCategoty());
			preparedStatement.setString(7, data.getDesignation());
			preparedStatement.setDate(8, data.getCommencedOn());
			preparedStatement.setDate(9, data.getCompletionOn());
			preparedStatement.setString(10, data.getDescription());
			preparedStatement.setString(10, data.getCrtBy());

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
		int isActive = ApplicationStatus.ACTIVE.getStatusValue();
		try {
			int studentCode = (Integer) code;
			conn = ConnectionManager.getConnection();
			String query = "SELECT PE.[CODE], [ORGANIZATION], [STUDENT], MJ1.[NAME] AS [INDUSTRY], MJ2.[NAME] AS [JOBCATEGORY], "
					+ "[DESIGNATION], [COMMENCEDON], [COMPLETIONON], PE.[DESCRIPTION] "
					+ "FROM [CAMPUS].[PROFESSIONALEXPERIENCE] PE "
					+ "JOIN [CAMPUS].[MAJOR] MJ1 ON PE.[JOBCATEGORY] = MJ1.[CODE] "
					+ "JOIN [CAMPUS].[MAJOR] MJ2 ON PE.[INDUSTRY] = MJ2.[CODE] "
					+ "WHERE [STUDENT] = ? AND PE.[ISACTIVE] = ? AND MJ1.[ISACTIVE] = ? AND MJ2.[ISACTIVE] = ?;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentCode);
			stmt.setInt(2, isActive);
			stmt.setInt(3, isActive);
			stmt.setInt(4, isActive);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singlehigherEduList = new ArrayList<String>();
				singlehigherEduList.add(rs.getString("CODE"));
				singlehigherEduList.add(rs.getString("ORGANIZATION"));
				singlehigherEduList.add(rs.getString("INDUSTRY"));
				singlehigherEduList.add(rs.getString("DESIGNATION"));
				singlehigherEduList.add(rs.getString("JOBCATEGORY"));
				singlehigherEduList.add(rs.getString("COMMENCEDON"));
				singlehigherEduList.add(rs.getString("COMPLETIONON"));
				singlehigherEduList.add(rs.getString("DESCRIPTION"));

				final Collection<String> singlehigherEduCollection = singlehigherEduList;
				allhigherEduList.add(singlehigherEduCollection);
			}
		} catch (SQLException sqlException) {
			log.error("findById(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
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

		String query = "INSERT INTO [CAMPUS].[PROFESSIONALEXPERIENCE] ([ORGANIZATION], [STUDENT], [INDUSTRY],"
				+ " [JOBCATEGORY], [DESIGNATION], [COMMENCEDON], [COMPLETIONON], [DESCRIPTION],[CRTBY]) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?,?);";

		int result = -1;

		try {
			ProfessionalExperience data = (ProfessionalExperience) object;
			connection = conn;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, data.getOrganization());
			preparedStatement.setInt(2, data.getStudent());
			preparedStatement.setInt(3, data.getIndustry());
			preparedStatement.setInt(4, data.getJobCategoty());
			preparedStatement.setString(5, data.getDesignation());
			preparedStatement.setDate(6, data.getCommencedOn());
			preparedStatement.setDate(7, data.getCompletionOn());
			preparedStatement.setString(8, data.getDescription());
			preparedStatement.setString(9, data.getCrtBy());

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

		String query = "UPDATE [CAMPUS].[PROFESSIONALEXPERIENCE] SET [ORGANIZATION] = ? ,[STUDENT] = ? ,[INDUSTRY] = ? ,[JOBCATEGORY] = ? ,"
				+ "[DESIGNATION] = ? ,[COMMENCEDON] = ? ,[COMPLETIONON] = ? ,[DESCRIPTION] = ? , [MODBY] = ? WHERE [CODE] = ?;";

		int result = -1;

		try {
			ProfessionalExperience data = (ProfessionalExperience) object;
			connection = conn;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, data.getOrganization());
			preparedStatement.setInt(2, data.getStudent());
			preparedStatement.setInt(3, data.getIndustry());
			preparedStatement.setInt(4, data.getJobCategoty());
			preparedStatement.setString(5, data.getDesignation());
			preparedStatement.setDate(6, data.getCommencedOn());
			preparedStatement.setDate(7, data.getCompletionOn());
			preparedStatement.setString(8, data.getDescription());
			preparedStatement.setString(9, data.getModBy());
			preparedStatement.setInt(10, data.getCode());

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

		String deleteSQL = "DELETE FROM [CAMPUS].[PROFESSIONALEXPERIENCE] WHERE [CODE] = ?;";

		try {
			ProfessionalExperience data = (ProfessionalExperience) object;
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
