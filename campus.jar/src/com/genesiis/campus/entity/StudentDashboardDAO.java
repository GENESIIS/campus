package com.genesiis.campus.entity;

//20161203 MM c25-student-create-dashboard-MP-mm INIT - Initialised file
//20161204 MM c25-student-create-dashboard-MP-mm Implemented findById() 
//20161205 MM c25-student-create-dashboard-MP-mm Converting the query to use advanced Transact SQL constructs 
//				such as variables, conditions etc. to ensure that multiple queries that have to run based on 
//				the availability/non-availability of results for student's provided data (such as Town and 
//				interests) are run at once on the DB, and not from the Java code when each previous query 
//				fails to return the adequate number of results.
//20161205 MM c25-student-create-dashboard-MP-mm Modified query so that it falls back to discount student's
//				specified Town or Interests when checking for recommended programmes.
//20161214 MM c25-student-create-dashboard-MP-mm Modified recommended-programmes-related query to use table 
//				variables, if constructs etc. so that it falls back to discount student's specified Interests, 
//				if not Town, if adequate number of matching programmes is not found matching the interests and 
//				town of the student.
//20161214 MM c25-student-create-dashboard-MP-mm Modified query composing code to fix errors in the 
//				generated query
//20161216 MM c25-student-create-dashboard-MP-mm Resolved the TransactSQL issue of table variable being out 
//				of scope when sp_executesql provided the dynamic sql query 
//20161220 MM c25-student-create-dashboard-MP-mm Resolved issue of SQLException (stating that the SQL statement 
//				did not return a result set) 


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
			String queryResolved = "DECLARE @sqlString nvarchar (3000); "
					+ "SET @sqlString = 'DECLARE @neededNumOfResults int, @numResults int; "
					+ "SET @neededNumOfResults = 10; "
					+ "SET NOCOUNT ON; "
					+ "DECLARE @TempProgrammesBasedOnInterestsAndTown TABLE ("
					+ "[CODE] [int],"
					+ "[NAME] [varchar](100),"
					+ "[EMAIL] [varchar](255),"
					+ "[IMAGE] [varchar](100),"
					+ "[DESCRIPTION] [text],"
					+ "[DURATION] [float],"
					+ "[ENTRYREQUIREMENTS] [varchar](2000),"
					+ "[COUNSELORNAME] [varchar](35),"
					+ "[COUNSELORPHONE] [varchar](15),"
					+ "[DISPLAYSTARTDATE] [date],"
					+ "[EXPIRYDATE] [date],"
					+ "[PROGRAMMESTATUS] [tinyint],"
					+ "[COURSEPROVIDER] [int],"
					+ "[MAJOR] [int],"
					+ "[CATEGORY] [int],"
					+ "[LEVEL] [int],"
					+ "[CLASSTYPE] [int],"
					+ "[CRTON] [date],"
					+ "[CRTBY] [varchar](20),"
					+ "[MODON] [date],"
					+ "[MODBY] [varchar](20)); "
					+ "INSERT INTO @TempProgrammesBasedOnInterestsAndTown "
					+ "SELECT TOP (@neededNumOfResults) p.* "
					+ "FROM [CAMPUS].[STUDENTINTEREST] si "
					+ "JOIN [CAMPUS].[INTEREST] i ON (i.CODE = si.INTEREST AND si.STUDENT = 1) "
					+ "JOIN [CAMPUS].[MAJORINTEREST] mi ON (i.CODE = mi.INTEREST) "
					+ "JOIN [CAMPUS].[MAJOR] m ON (m.CODE = mi.MAJOR) "
					+ "JOIN [CAMPUS].[PROGRAMME] p ON (m.CODE = p.MAJOR) "
					+ "JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME) "
					+ "JOIN [CAMPUS].[TOWN] t ON (t.CODE = pt.TOWN) "
					+ "JOIN [CAMPUS].[STUDENT] s ON (t.CODE = s.TOWN and s.CODE = 1); "
					+ "SELECT DISTINCT @numResults = COUNT(*) "
					+ "FROM @TempProgrammesBasedOnInterestsAndTown GROUP BY CODE; "
					+ "SET @neededNumOfResults = @neededNumOfResults - @numResults; "
					+ "IF (@neededNumOfResults > 0) "
					+ "BEGIN "
					+ "INSERT INTO @TempProgrammesBasedOnInterestsAndTown "
					+ "SELECT TOP (@neededNumOfResults) p.* "
					+ "FROM [CAMPUS].[STUDENTINTEREST] si "
					+ "JOIN [CAMPUS].[INTEREST] i ON (i.CODE = si.INTEREST AND si.STUDENT = 1) "
					+ "JOIN [CAMPUS].[MAJORINTEREST] mi ON (i.CODE = mi.INTEREST) "
					+ "JOIN [CAMPUS].[MAJOR] m ON (m.CODE = mi.MAJOR) "
					+ "JOIN [CAMPUS].[PROGRAMME] p ON (m.CODE = p.MAJOR) "
					+ "JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME) "
					+ "JOIN [CAMPUS].[TOWN] t ON (t.CODE = pt.TOWN); "
					+ "SELECT DISTINCT @numResults = COUNT(*) "
					+ "FROM @TempProgrammesBasedOnInterestsAndTown "
					+ "GROUP BY CODE; "
					+ "SET @neededNumOfResults = @neededNumOfResults - @numResults; "
					+ "END "
					+ "IF (@neededNumOfResults > 0) "
					+ "BEGIN "
					+ "INSERT INTO @TempProgrammesBasedOnInterestsAndTown "
					+ "SELECT TOP (@neededNumOfResults) p.* "
					+ "FROM [CAMPUS].[PROGRAMME] p "
					+ "JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME) "
					+ "JOIN [CAMPUS].[TOWN] t ON (t.CODE = pt.TOWN) "
					+ "JOIN [CAMPUS].[STUDENT] s ON (t.CODE = s.TOWN and s.CODE = 1); "
					+ "SELECT DISTINCT @numResults = COUNT(*) "
					+ "FROM @TempProgrammesBasedOnInterestsAndTown GROUP BY CODE; "
					+ "SET @neededNumOfResults = @neededNumOfResults - @numResults; "
					+ "END; "
					+ "SELECT * FROM @TempProgrammesBasedOnInterestsAndTown "
					+ "WHERE CODE = ("
					+ "SELECT DISTINCT CODE FROM @TempProgrammesBasedOnInterestsAndTown GROUP BY CODE"
					+ ");'; "
					+ "EXECUTE sp_executesql @sqlString;";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(queryResolved);
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
