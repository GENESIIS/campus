package com.genesiis.campus.entity;

//20161203 MM c25-student-create-dashboard-MP-mm INIT - Initialised file
//20161204 MM c25-student-create-dashboard-MP-mm Implemented findById() 
//20161205 MM c25-student-create-dashboard-MP-mm Converting the query to use advanced Transact SQL constructs 
//				such as variables, conditions etc. to ensure that multiple queries that have to run based on 
//				the availability/non-availability of results for student's provided data (such as Town and 
//				interests) are run at once on the DB, and not from the Java code when each previous query 
//				fails to return the adequate number of results.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.command.CmdListStudentDashboardDetails;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;

import org.apache.log4j.Logger;

public class StudentDashboardDAO implements ICrud {

	static Logger Log = Logger.getLogger(StudentDashboardDAO.class.getName());

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

		final Collection<Collection<String>> programmeDetailsCollectionList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Student student = (Student) code;
			int studentCode = student.getCode();

//			// TODO convert this to a StringBuidler
//			String query = "DECLARE @neededNumOfResults int, @numResults int; "
//					+ "SET @neededNumOfResults = 10;"
//					+ "SELECT @numResults = COUNT(*) "
//					+ "FROM [CAMPUS].[STUDENT];"
//					+ "SET @neededNumOfResults = @neededNumOfResults - @numResults; "
//					+ "IF(@numResults < 1) "
//					+ "BEGIN "
//					+ "PRINT 'NO RESULTS' "
//					+ "END "
//					+ "ELSE "
//					+ "BEGIN "
//					+ "SELECT * FROM [CAMPUS].[STUDENT]"
//					+ "END "
//					+ "PRINT 'neededNumOfResults : ' + CONVERT(@neededNumOfResults) + ', numResults:' + CONVERT(@numResults)";
			
			String query = "DECLARE @neededNumOfResults int, @numResults int; "
					+ "SET @neededNumOfResults = 10;"
					+ "SELECT TOP @neededNumOfResults @numResults = COUNT(*) "
					+ "FROM [CAMPUS].[STUDENTINTEREST] si "
					+ "JOIN [CAMPUS].[INTEREST] i ON (i.CODE = si.INTEREST AND si.STUDENT = 1) "
					+ "JOIN [CAMPUS].[MAJORINTEREST] mi ON (i.CODE = mi.INTEREST) "
					+ "JOIN [CAMPUS].[MAJOR] m ON (m.CODE = mi.MAJOR) "
					+ "JOIN [CAMPUS].[PROGRAMME] p ON (m.CODE = p.MAJOR) "
					+ "JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME) "
					+ "JOIN [CAMPUS].[TOWN] t ON (t.CODE = pt.TOWN) "
					+ "JOIN [CAMPUS].[STUDENT] s ON (t.CODE = s.TOWN and s.CODE = 1);"
					+ "SET @neededNumOfResults = @neededNumOfResults - @numResults; "
					+ "IF (@numResults < 1) "
					+ "BEGIN "
					+ ""
					+ "END";
			
			
//					+ "PRINT 'neededNumOfResults : ' + CONVERT(@neededNumOfResults) + ', numResults:' + CONVERT(@numResults)";
			
//			String query = "DECLARE @numResults int; "
//					+ "SET @numResults = SELECT COUNT(*) "
//					+ "FROM [CAMPUS].[STUDENTINTEREST] si "
//					+ "JOIN [CAMPUS].[INTEREST] i ON (i.CODE = si.INTEREST AND si.STUDENT = ?) "
//					+ "JOIN [CAMPUS].[MAJOR] m ON (m.CODE = i.MAJOR) "
//					+ "JOIN [CAMPUS].[PROGRAMME] p ON (m.CODE = p.MAJOR) "
//					+ "JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (pt.CODE = p.PROGRAMMETOWN) "
//					+ "JOIN [CAMPUS].[TOWN] t ON (t.CODE = pt.TOWN) "
//					+ "JOIN [CAMPUS].[STUDENT] s ON (t.CODE = s.TOWN and s.CODE = ?)";
			
//			String oldQuery = "SELECT s.*, "
//					+ "pe.CODE AS EXPERIENCECODE, pe.DESIGNATION, pe.ORGANIZATION, "
//					+ "pe.COMMENCEDON AS EXPERIENCECOMMENCEDON, pe.COMPLETIONON AS EXPERIENCECOMPLETIONON, "
//					+ "se.CODE AS SCHOOLEDUCATIONCODE, se.SCHOOL, se.ACHIVEDON AS ACHIEVEDON, "
//					+ "se.SCHOOLGRADE, sg.TITLE, sg.LEVEL AS SCHOOLGRADELEVEL, " 
//					+ "l.NAME AS LEVELNAME, "
//					+ "he.CODE AS HIGHEREDUCATIONCODE, he.INSTITUTE AS HIGHEREDUCATIONINSTITUTE, he.AFFINSTITUTE, "
//					+ "he.LEVEL AS HIGHEREDUCATIONLEVELCODE, he.AWARD AS HIGHEREDUCATIONAWARDCODE, "
//					+ "he.MAJOR AS HIGHEREDUCATIONMAJORCODE, he.COMMENCEDON AS HIGHEREDUCATIONCOMMENCEDON, "
//					+ "he.COMPLETIONON AS HIGHEREDUCATIONCOMPLETIONON, "
//					+ "a.SHORTTITLE, a.LONGTITLE, "
//					+ "ss.CODE AS STUDENTSKILLCODE, ss.RATING, ss.SKILL AS SKILLCODE, sk.NAME AS SKILLNAME "
//					+ "FROM [CAMPUS].[STUDENT] s "
//					+ "JOIN [CAMPUS].[PROFESSIONALEXPERIENCE] pe ON (s.CODE = pe.STUDENT AND s.CODE = ?) "
//					+ "JOIN [CAMPUS].[SCHOOLEDUCATION] se ON (s.CODE = se.STUDENT) "
//					+ "JOIN [CAMPUS].[SCHOOLGRADE] sg ON (sg.CODE = se.SCHOOLGRADE) "
//					+ "JOIN [CAMPUS].[LEVEL] l ON (l.CODE = sg.LEVEL) "
//					+ "JOIN [CAMPUS].[HIGHERDUCATION] he ON (s.CODE = he.STUDENT) "
//					+ "JOIN [CAMPUS].[AWARD] a ON (a.CODE = he.AWARD) "
//					+ "JOIN [CAMPUS].[STUDENTSKILL] ss ON (s.CODE = ss.STUDENT) "
//					+ "JOIN [CAMPUS].[SKILL] sk ON (sk.CODE = ss.SKILL)";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, studentCode);
			ps.setInt(2, studentCode);
			ResultSet rs = ps.executeQuery();

			retrieveProgrammesFromResultSet(rs, programmeDetailsCollectionList);

		} catch (ClassCastException cce) {
			Log.info("findById(Object): ClassCastException: " + cce.toString());
			throw new IllegalArgumentException(
					"The argument passed is not of expected type (Programme)!");
		} catch (SQLException sqle) {
			Log.info("findById(Object): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			Log.info("findById(Object): Exception: " + e.toString());
			throw e;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return programmeDetailsCollectionList;
	}

	private void retrieveProgrammesFromResultSet(ResultSet rs,
			Collection<Collection<String>> studentList) throws SQLException {

		while (rs.next()) {
			final ArrayList<String> singleProgramme = new ArrayList<String>();
			singleProgramme.add(rs.getString("CODE")); // 0
			singleProgramme.add(rs.getString("NAME")); // 1
			singleProgramme.add(rs.getString("EMAIL")); // 2
			singleProgramme.add(rs.getString("IMAGE")); // 3
			singleProgramme.add(rs.getString("DESCRIPTION")); // 4
			singleProgramme.add(rs.getString("DURATION")); // 5
			singleProgramme.add(rs.getString("ENTRYREQUIREMENTS")); // 6
			singleProgramme.add(rs.getString("COUNSELORNAME")); // 7
			singleProgramme.add(rs.getString("COUNSELORPHONE")); // 8
			singleProgramme.add(rs.getString("DISPLAYSTARTDATE")); // 9
			singleProgramme.add(rs.getString("EXPIRYDATE")); // 10
			singleProgramme.add(rs.getString("PROGRAMMESTATUS")); // 11
			singleProgramme.add(rs.getString("COURSEPROVIDER")); // 12
			singleProgramme.add(rs.getString("MAJOR")); // 13
			singleProgramme.add(rs.getString("CATEGORY")); // 14
			singleProgramme.add(rs.getString("LEVEL")); // 15
			singleProgramme.add(rs.getString("CLASSTYPE")); // 16
			final Collection<String> singleProgrammeCollection = singleProgramme;
			studentList.add(singleProgrammeCollection);
		}
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
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
