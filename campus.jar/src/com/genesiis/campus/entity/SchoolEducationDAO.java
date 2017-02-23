package com.genesiis.campus.entity;
//20161124 PN c26-add-student-details: INIT SchoolEducationDAO.java class.

//20161125 PN c26-add-student-details: implemented findByIdMethod().
//20161126 PN c26-add-student-details: modified findByIdMethod() method by setting country name to the return collection.
//20160103 PN CAM-28: added JDBC property closing statements to the finally block.
//20170105 PN CAM-28: edit user information: modified DAO method coding modified with improved connection property management.
//20170106 PN CAM-28: improved Connection property handeling inside finally{} block. 
//20170106 PN CAM-28: SQL query modified to takeISACTIVE status from ApplicationStatus ENUM. 
//20170106 PN CAM-28: Object casting code moved into try{} block in applicable methods().
//20170118 PN CAM-28: modified findByIdMethod() method by removing different DB connection to select country name. existing SQL query modified to a JOIN to select Country name using the same DB connection.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.SchoolEducation;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.ApplicationStatus;

public class SchoolEducationDAO implements ICrud {
	static Logger log = Logger.getLogger(SchoolEducationDAO.class.getName());

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
		final Collection<Collection<String>> allEducationList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int isActive = ApplicationStatus.ACTIVE.getStatusValue();
		try {
			int studentCode = (Integer) code;
			conn = ConnectionManager.getConnection();
			String query = "SELECT se.[CODE], [STUDENT], [SCHOOLGRADE], [MAJOR], co.[NAME] AS [COUNTRY] , "
					+ "[RESULT], [INDEXNO], [SCHOOL], [ACHIVEDON], [DESCRIPTION], [MEDIUM] "
					+ "FROM [CAMPUS].[SCHOOLEDUCATION] se " + "JOIN [CAMPUS].[COUNTRY2] co ON se.[COUNTRY] = co.[CODE] "
					+ "WHERE [STUDENT] = ? AND ISACTIVE = ?;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentCode);
			stmt.setInt(2, isActive);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleEducationList = new ArrayList<String>();
				singleEducationList.add(rs.getString("CODE"));
				singleEducationList.add(rs.getString("STUDENT"));
				singleEducationList.add(rs.getString("SCHOOLGRADE"));
				singleEducationList.add(rs.getString("MAJOR"));
				singleEducationList.add(rs.getString("COUNTRY"));
				singleEducationList.add(rs.getString("RESULT"));
				singleEducationList.add(rs.getString("INDEXNO"));
				singleEducationList.add(rs.getString("SCHOOL"));
				singleEducationList.add(rs.getString("ACHIVEDON"));
				singleEducationList.add(rs.getString("DESCRIPTION"));
				singleEducationList.add(rs.getString("MEDIUM"));

				final Collection<String> singleEducationCollection = singleEducationList;
				allEducationList.add(singleEducationCollection);
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
		return allEducationList;
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

		String query = "INSERT INTO [CAMPUS].[SCHOOLEDUCATION] ([STUDENT], [SCHOOLGRADE], [MAJOR], [COUNTRY], "
				+ "[RESULT], [INDEXNO], [SCHOOL], [ACHIVEDON], [DESCRIPTION], [CRTON], [CRTBY], [MEDIUM]) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,(getdate()), ?, ?);";
		int result = -1;

		try {
			SchoolEducation data = (SchoolEducation) object;
			connection = conn;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, data.getStudent());
			preparedStatement.setInt(2, data.getSchoolGrade());
			preparedStatement.setInt(3, data.getMajor());
			preparedStatement.setInt(4, data.getCountry());
			preparedStatement.setInt(5, data.getResult());
			preparedStatement.setString(6, data.getIndexNo());
			preparedStatement.setString(7, data.getSchoolName());
			preparedStatement.setDate(8, data.getAchievedOn());
			preparedStatement.setString(9, data.getDescription());
			preparedStatement.setString(10, data.getCrtBy());
			preparedStatement.setInt(11, data.getMedium());
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

		String query = "UPDATE [CAMPUS].[SCHOOLEDUCATION] SET "
				+ "[SCHOOLGRADE]=?, [MAJOR]=?, [COUNTRY]=?, [RESULT]=?, [INDEXNO]=?, [SCHOOL]=?, "
				+ "[ACHIVEDON]=?, [DESCRIPTION]=?, [MODON]=(getdate()), [MODBY]=?, [MEDIUM]=? " + "WHERE [STUDENT]=?;";
		int result = -1;

		try {
			SchoolEducation data = (SchoolEducation) object;
			connection = conn;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, data.getSchoolGrade());
			preparedStatement.setInt(2, data.getMajor());
			preparedStatement.setInt(3, data.getCountry());
			preparedStatement.setInt(4, data.getResult());
			preparedStatement.setString(5, data.getIndexNo());
			preparedStatement.setString(6, data.getSchoolName());
			preparedStatement.setDate(7, data.getAchievedOn());
			preparedStatement.setString(8, data.getDescription());
			preparedStatement.setString(9, data.getModBy());
			preparedStatement.setInt(10, data.getMedium());
			preparedStatement.setInt(11, data.getStudent());
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
		// TODO Auto-generated method stub
		return 0;
	}

}
