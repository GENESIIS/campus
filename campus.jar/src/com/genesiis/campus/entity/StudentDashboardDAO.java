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
//20161220 MM c25-student-create-dashboard-MP-mm Modified query to eliminate duplicate records when retrieving 
//				data from the table variable   
//20161226 MM c25-student-create-dashboard-MP-mm Modified query to to eliminate in-situ records already selected
//				into the result set and placed in the table variable, and to consider the relevant DSD, DISTRICT, 
//				PROVINCE and COUNTRY2 of the student when the adequate number of programmes is not found with 
//				student's interests and the exact town.
//20161227 MM c25-student-create-dashboard-MP-mm Removed unnecessary code and troubleshooted issue that was 
//				preventing results from being returned and assigned to programmeDetailsCollectionList
//20161227 MM c25-student-create-dashboard-MP-mm Modified query so the CourseProvider's Name and ShortName are 
//				 included in the results
//20161229 MM c25-student-create-dashboard-MP-mm Modified code in findById(Object) so that the studentCode and 
//				the number of programmes to fetch are taken dynamically
//20161229 MM c25-student-create-dashboard-MP-mm Modified query so at least 10 institutes are fetched 
//				which makes the programme list to be greater than 10 
//20170105 MM c25-student-create-dashboard-MP-mm Added JavaDoc comment for the findByID(Object) method; 
//				converted string building query to use a StringBuilder object  
//20160111 MM c25-student-dashboard-MP-mm Modified code in 
//				retrieveProgrammesFromResultSet(ResultSet, Collection<Collection<String>>) 
//				to remove statement that assigned an ArrayList to a Collection, before which that 
//				collection was passed as the parameter to the "add()" method of another Collection. 
//				Now directly passing the ArrayList object itself to the "add()" method of the Collection.

import com.genesiis.campus.command.CmdListStudentDashboardDetails;
import com.genesiis.campus.entity.model.RecommendedProgrammesSearchDTO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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

	/**
	 * Selects the recommended programmes for a student. Programmes are searched for based on the following procedure until the 
	 * result set has programmes of 10 unique course providers.  
	 * 
	 * When programmes with 10 unique CourseProvider records are not obtained by a particular step, the next step is taken.
	 *	1. Select Programmes that match a Student's Interests AND the exact Town
	 *	2. Select Programmes that match a Student's Interests AND DSD
	 *	3. Select Programmes that match a Student's Interests AND District
	 *	4. Select Programmes that match a Student's Interests AND Province
	 *	5. Select Programmes that match a Student's Interests AND Country
	 *	6. Select Programmes that match only Interests of a student
	 *	7. Select Programmes that match only Town of a student
	 *	8. Select a random list of Programmes
	 * 
	 * @param An Object containing the code of the Student.
	 * @return An object of type Collection<Collection<String>> containing a list of recommended programmes for a student.
	 * 
	 * @author miyuru
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {

		final Collection<Collection<String>> programmeDetailsCollectionList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			RecommendedProgrammesSearchDTO recommenedProgrammesSearchDto = (RecommendedProgrammesSearchDTO) code;
			
			int studentCode = recommenedProgrammesSearchDto.getStudent();
			int numberOfProgrammesToFetch = recommenedProgrammesSearchDto.getNumberOfProgrammes();

			// TODO convert this to a StringBuidler
			String query = "DECLARE @sqlString nvarchar(MAX); "
					+ "SET @sqlString = 'DECLARE @neededNumOfResults int, @numResults int, @studentTownCode bigint, @studentDSDCode smallint, @studentDistrictCode smallint, @studentProvinceCode smallint, @studentCountryCode smallint; "
					+ "SET @neededNumOfResults = " + numberOfProgrammesToFetch +"; "
					+ "SET NOCOUNT ON; "
					+ "DECLARE @TempProgrammes TABLE ([CODE] [int],[NAME] [varchar](100),[EMAIL] [varchar](255),[IMAGE] [varchar](100),[DESCRIPTION] [text],[DURATION] [float],[ENTRYREQUIREMENTS] [varchar](2000),[COUNSELORNAME] [varchar](35),[COUNSELORPHONE] [varchar](15),[DISPLAYSTARTDATE] [date],[EXPIRYDATE] [date],[PROGRAMMESTATUS] [tinyint],[COURSEPROVIDER] [int],[MAJOR] [int],[CATEGORY] [int],[LEVEL] [int],[CLASSTYPE] [int],[CRTON] [date],[CRTBY] [varchar](20),[MODON] [date],[MODBY] [varchar](20),[STUDENTTOWNCODE] [bigint], [STUDENTDSDCODE] [smallint], [STUDENTDISTRICTCODE] [smallint], [STUDENTPROVINCECODE] [smallint], [STUDENTCOUNTRYCODE] [smallint], [COURSEPROVIDERSHORTNAME] [varchar](30), COURSEPROVIDERNAME [varchar](200)); "
					+ "INSERT INTO @TempProgrammes (CODE, NAME, EMAIL, IMAGE, DESCRIPTION, DURATION, ENTRYREQUIREMENTS, COUNSELORNAME, COUNSELORPHONE, DISPLAYSTARTDATE, EXPIRYDATE, PROGRAMMESTATUS, COURSEPROVIDER, MAJOR, CATEGORY, LEVEL, CLASSTYPE, CRTON, CRTBY, MODON, MODBY, STUDENTTOWNCODE, STUDENTDSDCODE, STUDENTDISTRICTCODE, STUDENTPROVINCECODE, STUDENTCOUNTRYCODE, COURSEPROVIDERSHORTNAME, COURSEPROVIDERNAME) "
					//+ "-- GET PROGRAMMES THAT MATCH INTERESTS AND TOWN OF A STUDENT "
					+ "SELECT DISTINCT TOP (@neededNumOfResults) p.CODE, p.NAME, p.EMAIL, p.IMAGE, CAST(p.DESCRIPTION AS VARCHAR(4000)) AS DESCRIPTION, p.DURATION, p.ENTRYREQUIREMENTS, p.COUNSELORNAME, p.COUNSELORPHONE, p.DISPLAYSTARTDATE, p.EXPIRYDATE, p.PROGRAMMESTATUS, p.COURSEPROVIDER, p.MAJOR, p.CATEGORY, p.LEVEL, p.CLASSTYPE, p.CRTON, p.CRTBY, p.MODON, p.MODBY, s.TOWN, t.DSD, t.DISTRICT, t.PROVINCE, t.COUNTRY, cp.SHORTNAME, cp.NAME FROM [CAMPUS].[STUDENTINTEREST] si JOIN [CAMPUS].[INTEREST] i ON (i.CODE = si.INTEREST AND si.STUDENT = " + studentCode + ") JOIN [CAMPUS].[MAJORINTEREST] mi ON (i.CODE = mi.INTEREST) JOIN [CAMPUS].[MAJOR] m ON (m.CODE = mi.MAJOR) JOIN [CAMPUS].[PROGRAMME] p ON (m.CODE = p.MAJOR) JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME) JOIN [CAMPUS].[COURSEPROVIDER] cp ON (cp.CODE = p.COURSEPROVIDER) RIGHT JOIN [CAMPUS].[TOWN] t ON (t.CODE = pt.TOWN) JOIN [CAMPUS].[STUDENT] s ON (t.CODE = s.TOWN AND s.CODE = " + studentCode + "); "
					+ "SELECT TOP 1 @studentTownCode = STUDENTTOWNCODE, @studentDSDCode = STUDENTDSDCODE, @studentDistrictCode = STUDENTDISTRICTCODE, @studentProvinceCode = STUDENTPROVINCECODE, @studentCountryCode = STUDENTCOUNTRYCODE FROM @TempProgrammes; "
					+ "SELECT @numResults = COUNT(DISTINCT COURSEPROVIDER) FROM @TempProgrammes; "
					+ "SET @neededNumOfResults = @neededNumOfResults - @numResults; "
					+ "IF (@neededNumOfResults > 0) "
					+ "BEGIN "
					+ "INSERT INTO @TempProgrammes (CODE, NAME, EMAIL, IMAGE, DESCRIPTION, DURATION, ENTRYREQUIREMENTS, COUNSELORNAME, COUNSELORPHONE, DISPLAYSTARTDATE, EXPIRYDATE, PROGRAMMESTATUS, COURSEPROVIDER, MAJOR, CATEGORY, LEVEL, CLASSTYPE, CRTON, CRTBY, MODON, MODBY, COURSEPROVIDERSHORTNAME, COURSEPROVIDERNAME) "
					//+ "-- GET PROGRAMMES THAT MATCH INTERESTS AND DSD OF A STUDENT "
					+ "SELECT DISTINCT TOP (@neededNumOfResults) p.CODE, p.NAME, p.EMAIL, p.IMAGE, CAST(p.DESCRIPTION AS VARCHAR(4000)) AS DESCRIPTION, p.DURATION, p.ENTRYREQUIREMENTS, p.COUNSELORNAME, p.COUNSELORPHONE, p.DISPLAYSTARTDATE, p.EXPIRYDATE, p.PROGRAMMESTATUS, p.COURSEPROVIDER, p.MAJOR, p.CATEGORY, p.LEVEL, p.CLASSTYPE, p.CRTON, p.CRTBY, p.MODON, p.MODBY, cp.SHORTNAME, cp.NAME FROM [CAMPUS].[STUDENTINTEREST] si JOIN [CAMPUS].[INTEREST] i ON (i.CODE = si.INTEREST AND si.STUDENT = " + studentCode + ") JOIN [CAMPUS].[MAJORINTEREST] mi ON (i.CODE = mi.INTEREST) JOIN [CAMPUS].[MAJOR] m ON (m.CODE = mi.MAJOR) JOIN [CAMPUS].[PROGRAMME] p ON (m.CODE = p.MAJOR) JOIN [CAMPUS].[COURSEPROVIDER] cp ON (cp.CODE = p.COURSEPROVIDER) JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME) JOIN [CAMPUS].[TOWN] t ON (t.CODE = pt.TOWN) JOIN [CAMPUS].[DSD] dsd ON (dsd.CODE = t.DSD AND dsd.CODE = @studentDSDCode) WHERE p.CODE NOT IN (SELECT CODE FROM @TempProgrammes); "
					+ "SELECT @numResults = COUNT(DISTINCT COURSEPROVIDER) FROM @TempProgrammes; "
					+ "SET @neededNumOfResults = @neededNumOfResults - @numResults; "
					+ "END "
					+ "IF (@neededNumOfResults > 0) "
					+ "BEGIN "
					+ "INSERT INTO @TempProgrammes (CODE, NAME, EMAIL, IMAGE, DESCRIPTION, DURATION, ENTRYREQUIREMENTS, COUNSELORNAME, COUNSELORPHONE, DISPLAYSTARTDATE, EXPIRYDATE, PROGRAMMESTATUS, COURSEPROVIDER, MAJOR, CATEGORY, LEVEL, CLASSTYPE, CRTON, CRTBY, MODON, MODBY, COURSEPROVIDERSHORTNAME, COURSEPROVIDERNAME) "
					//+ "-- GET PROGRAMMES THAT MATCH INTERESTS AND DISTRICT OF A STUDENT "
					+ "SELECT DISTINCT TOP (@neededNumOfResults) p.CODE, p.NAME, p.EMAIL, p.IMAGE, CAST(p.DESCRIPTION AS VARCHAR(4000)) AS DESCRIPTION, p.DURATION, p.ENTRYREQUIREMENTS, p.COUNSELORNAME, p.COUNSELORPHONE, p.DISPLAYSTARTDATE, p.EXPIRYDATE, p.PROGRAMMESTATUS, p.COURSEPROVIDER, p.MAJOR, p.CATEGORY, p.LEVEL, p.CLASSTYPE, p.CRTON, p.CRTBY, p.MODON, p.MODBY, cp.SHORTNAME, cp.NAME FROM [CAMPUS].[STUDENTINTEREST] si JOIN [CAMPUS].[INTEREST] i ON (i.CODE = si.INTEREST AND si.STUDENT = " + studentCode + ") JOIN [CAMPUS].[MAJORINTEREST] mi ON (i.CODE = mi.INTEREST) JOIN [CAMPUS].[MAJOR] m ON (m.CODE = mi.MAJOR) JOIN [CAMPUS].[PROGRAMME] p ON (m.CODE = p.MAJOR) JOIN [CAMPUS].[COURSEPROVIDER] cp ON (cp.CODE = p.COURSEPROVIDER) JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME) JOIN [CAMPUS].[TOWN] t ON (t.CODE = pt.TOWN) JOIN [CAMPUS].[DISTRICT] d ON (d.CODE = t.DISTRICT AND d.CODE = @studentDistrictCode) WHERE p.CODE NOT IN (SELECT CODE FROM @TempProgrammes); "
					+ "SELECT @numResults = COUNT(DISTINCT COURSEPROVIDER) FROM @TempProgrammes; "
					+ "SET @neededNumOfResults = @neededNumOfResults - @numResults; "
					+ "END "
					+ "IF (@neededNumOfResults > 0) "
					+ "BEGIN "
					+ "INSERT INTO @TempProgrammes (CODE, NAME, EMAIL, IMAGE, DESCRIPTION, DURATION, ENTRYREQUIREMENTS, COUNSELORNAME, COUNSELORPHONE, DISPLAYSTARTDATE, EXPIRYDATE, PROGRAMMESTATUS, COURSEPROVIDER, MAJOR, CATEGORY, LEVEL, CLASSTYPE, CRTON, CRTBY, MODON, MODBY, COURSEPROVIDERSHORTNAME, COURSEPROVIDERNAME) "
					//+ "-- GET PROGRAMMES THAT MATCH INTERESTS AND PROVINCE OF A STUDENT "
					+ "SELECT DISTINCT TOP (@neededNumOfResults) p.CODE, p.NAME, p.EMAIL, p.IMAGE, CAST(p.DESCRIPTION AS VARCHAR(4000)) AS DESCRIPTION, p.DURATION, p.ENTRYREQUIREMENTS, p.COUNSELORNAME, p.COUNSELORPHONE, p.DISPLAYSTARTDATE, p.EXPIRYDATE, p.PROGRAMMESTATUS, p.COURSEPROVIDER, p.MAJOR, p.CATEGORY, p.LEVEL, p.CLASSTYPE, p.CRTON, p.CRTBY, p.MODON, p.MODBY, cp.SHORTNAME, cp.NAME FROM [CAMPUS].[STUDENTINTEREST] si JOIN [CAMPUS].[INTEREST] i ON (i.CODE = si.INTEREST AND si.STUDENT = " + studentCode + ") JOIN [CAMPUS].[MAJORINTEREST] mi ON (i.CODE = mi.INTEREST) JOIN [CAMPUS].[MAJOR] m ON (m.CODE = mi.MAJOR) JOIN [CAMPUS].[PROGRAMME] p ON (m.CODE = p.MAJOR) JOIN [CAMPUS].[COURSEPROVIDER] cp ON (cp.CODE = p.COURSEPROVIDER) JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME) JOIN [CAMPUS].[TOWN] t ON (t.CODE = pt.TOWN) JOIN [CAMPUS].[PROVINCE] pr ON (pr.CODE = t.PROVINCE AND pr.CODE = @studentProvinceCode) WHERE p.CODE NOT IN (SELECT CODE FROM @TempProgrammes); "
					+ "SELECT @numResults = COUNT(DISTINCT COURSEPROVIDER) FROM @TempProgrammes; "
					+ "SET @neededNumOfResults = @neededNumOfResults - @numResults; "
					+ "END "
					+ "IF (@neededNumOfResults > 0) "
					+ "BEGIN "
					+ "INSERT INTO @TempProgrammes (CODE, NAME, EMAIL, IMAGE, DESCRIPTION, DURATION, ENTRYREQUIREMENTS, COUNSELORNAME, COUNSELORPHONE, DISPLAYSTARTDATE, EXPIRYDATE, PROGRAMMESTATUS, COURSEPROVIDER, MAJOR, CATEGORY, LEVEL, CLASSTYPE, CRTON, CRTBY, MODON, MODBY, COURSEPROVIDERSHORTNAME, COURSEPROVIDERNAME) "
					//+ "-- GET PROGRAMMES THAT MATCH INTERESTS AND COUNTRY OF A STUDENT "
					+ "SELECT DISTINCT TOP (@neededNumOfResults) p.CODE, p.NAME, p.EMAIL, p.IMAGE, CAST(p.DESCRIPTION AS VARCHAR(4000)) AS DESCRIPTION, p.DURATION, p.ENTRYREQUIREMENTS, p.COUNSELORNAME, p.COUNSELORPHONE, p.DISPLAYSTARTDATE, p.EXPIRYDATE, p.PROGRAMMESTATUS, p.COURSEPROVIDER, p.MAJOR, p.CATEGORY, p.LEVEL, p.CLASSTYPE, p.CRTON, p.CRTBY, p.MODON, p.MODBY, cp.SHORTNAME, cp.NAME FROM [CAMPUS].[STUDENTINTEREST] si JOIN [CAMPUS].[INTEREST] i ON (i.CODE = si.INTEREST AND si.STUDENT = " + studentCode + ") JOIN [CAMPUS].[MAJORINTEREST] mi ON (i.CODE = mi.INTEREST) JOIN [CAMPUS].[MAJOR] m ON (m.CODE = mi.MAJOR) JOIN [CAMPUS].[PROGRAMME] p ON (m.CODE = p.MAJOR) JOIN [CAMPUS].[COURSEPROVIDER] cp ON (cp.CODE = p.COURSEPROVIDER) JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME) JOIN [CAMPUS].[TOWN] t ON (t.CODE = pt.TOWN) JOIN [CAMPUS].[COUNTRY2] coun ON (coun.CODE = t.COUNTRY AND coun.CODE = @studentCountryCode) WHERE p.CODE NOT IN (SELECT CODE FROM @TempProgrammes); "
					+ "SELECT @numResults = COUNT(DISTINCT COURSEPROVIDER) FROM @TempProgrammes; "
					+ "SET @neededNumOfResults = @neededNumOfResults - @numResults; "
					+ "END "
					+ "IF (@neededNumOfResults > 0) "
					+ "BEGIN "
					+ "INSERT INTO @TempProgrammes (CODE, NAME, EMAIL, IMAGE, DESCRIPTION, DURATION, ENTRYREQUIREMENTS, COUNSELORNAME, COUNSELORPHONE, DISPLAYSTARTDATE, EXPIRYDATE, PROGRAMMESTATUS, COURSEPROVIDER, MAJOR, CATEGORY, LEVEL, CLASSTYPE, CRTON, CRTBY, MODON, MODBY, COURSEPROVIDERSHORTNAME, COURSEPROVIDERNAME) "
					//+ "-- GET PROGRAMMES THAT MATCH ONLY INTERESTS OF A STUDENT (DISREGARDING ANY TOWN-RELATED RELATIONSHIPS) "
					+ "SELECT DISTINCT TOP (@neededNumOfResults) p.CODE, p.NAME, p.EMAIL, p.IMAGE, CAST(p.DESCRIPTION AS VARCHAR(4000)) AS DESCRIPTION, p.DURATION, p.ENTRYREQUIREMENTS, p.COUNSELORNAME, p.COUNSELORPHONE, p.DISPLAYSTARTDATE, p.EXPIRYDATE, p.PROGRAMMESTATUS, p.COURSEPROVIDER, p.MAJOR, p.CATEGORY, p.LEVEL, p.CLASSTYPE, p.CRTON, p.CRTBY, p.MODON, p.MODBY, cp.SHORTNAME, cp.NAME FROM [CAMPUS].[STUDENTINTEREST] si JOIN [CAMPUS].[INTEREST] i ON (i.CODE = si.INTEREST AND si.STUDENT = " + studentCode + ") JOIN [CAMPUS].[MAJORINTEREST] mi ON (i.CODE = mi.INTEREST) JOIN [CAMPUS].[MAJOR] m ON (m.CODE = mi.MAJOR) JOIN [CAMPUS].[PROGRAMME] p ON (m.CODE = p.MAJOR) JOIN [CAMPUS].[COURSEPROVIDER] cp ON (cp.CODE = p.COURSEPROVIDER) JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME) JOIN [CAMPUS].[TOWN] t ON (t.CODE = pt.TOWN) WHERE p.CODE NOT IN (SELECT CODE FROM @TempProgrammes); "
					+ "SELECT @numResults = COUNT(DISTINCT COURSEPROVIDER) FROM @TempProgrammes; "
					+ "SET @neededNumOfResults = @neededNumOfResults - @numResults; "
					+ "END "
					+ "IF (@neededNumOfResults > 0) "
					+ "BEGIN "
					+ "INSERT INTO @TempProgrammes (CODE, NAME, EMAIL, IMAGE, DESCRIPTION, DURATION, ENTRYREQUIREMENTS, COUNSELORNAME, COUNSELORPHONE, DISPLAYSTARTDATE, EXPIRYDATE, PROGRAMMESTATUS, COURSEPROVIDER, MAJOR, CATEGORY, LEVEL, CLASSTYPE, CRTON, CRTBY, MODON, MODBY, COURSEPROVIDERSHORTNAME, COURSEPROVIDERNAME) "
					//+ "-- GET PROGRAMMES THAT MATCH ONLY TOWN OF A STUDENT (DISREGARDING ANY INTEREST-RELATED RELATIONSHIPS) "
					+ "SELECT DISTINCT TOP (@neededNumOfResults) p.CODE, p.NAME, p.EMAIL, p.IMAGE, CAST(p.DESCRIPTION AS VARCHAR(4000)) AS DESCRIPTION, p.DURATION, p.ENTRYREQUIREMENTS, p.COUNSELORNAME, p.COUNSELORPHONE, p.DISPLAYSTARTDATE, p.EXPIRYDATE, p.PROGRAMMESTATUS, p.COURSEPROVIDER, p.MAJOR, p.CATEGORY, p.LEVEL, p.CLASSTYPE, p.CRTON, p.CRTBY, p.MODON, p.MODBY, cp.SHORTNAME, cp.NAME FROM [CAMPUS].[PROGRAMME] p JOIN [CAMPUS].[COURSEPROVIDER] cp ON (cp.CODE = p.COURSEPROVIDER) JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME) JOIN [CAMPUS].[TOWN] t ON (t.CODE = pt.TOWN) JOIN [CAMPUS].[STUDENT] s ON (t.CODE = s.TOWN and s.CODE = " + studentCode + ") WHERE p.CODE NOT IN (SELECT CODE FROM @TempProgrammes); "
					+ "SELECT @numResults = COUNT(DISTINCT COURSEPROVIDER) FROM @TempProgrammes; "
					+ "SET @neededNumOfResults = @neededNumOfResults - @numResults; "
					+ "END "
					+ "IF (@neededNumOfResults > 0) "
					+ "BEGIN "
					+ "INSERT INTO @TempProgrammes (CODE, NAME, EMAIL, IMAGE, DESCRIPTION, DURATION, ENTRYREQUIREMENTS, COUNSELORNAME, COUNSELORPHONE, DISPLAYSTARTDATE, EXPIRYDATE, PROGRAMMESTATUS, COURSEPROVIDER, MAJOR, CATEGORY, LEVEL, CLASSTYPE, CRTON, CRTBY, MODON, MODBY, COURSEPROVIDERSHORTNAME, COURSEPROVIDERNAME) "
					//+ "-- GET A RANDOM SET OF PROGRAMMES THAT ARE NO ALREADY SELECTED (DISREGARDING ANY INTEREST- OR TOWN-RELATED RELATIONSHIPS) "
					+ "SELECT TOP (@neededNumOfResults) p.CODE, p.NAME, p.EMAIL, p.IMAGE, CAST(p.DESCRIPTION AS VARCHAR(4000)) AS DESCRIPTION, p.DURATION, p.ENTRYREQUIREMENTS, p.COUNSELORNAME, p.COUNSELORPHONE, p.DISPLAYSTARTDATE, p.EXPIRYDATE, p.PROGRAMMESTATUS, p.COURSEPROVIDER, p.MAJOR, p.CATEGORY, p.LEVEL, p.CLASSTYPE, p.CRTON, p.CRTBY, p.MODON, p.MODBY, cp.SHORTNAME, cp.NAME FROM [CAMPUS].[PROGRAMME] p JOIN [CAMPUS].[COURSEPROVIDER] cp ON (cp.CODE = p.COURSEPROVIDER) WHERE p.CODE NOT IN (SELECT CODE FROM @TempProgrammes) ORDER BY NEWID(); "
					+ "END "
					+ "SELECT CODE, NAME, EMAIL, IMAGE, CAST(DESCRIPTION AS VARCHAR(4000)) AS DESCRIPTION, DURATION, ENTRYREQUIREMENTS, COUNSELORNAME, COUNSELORPHONE, DISPLAYSTARTDATE, EXPIRYDATE, PROGRAMMESTATUS, COURSEPROVIDER, MAJOR, CATEGORY, LEVEL, CLASSTYPE, COURSEPROVIDERSHORTNAME, COURSEPROVIDERNAME FROM @TempProgrammes;'; "
					+ "EXECUTE sp_executesql @sqlString; ";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			retrieveProgrammesFromResultSet(rs, programmeDetailsCollectionList);

		} catch (ClassCastException cce) {
			Log.error("findById(Object): ClassCastException: " + cce.toString());
			throw new IllegalArgumentException(
					"The argument passed is not of expected type (RecommendedProgrammesSearchDTO)!");
		} catch (SQLException sqle) {
			Log.error("findById(Object): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			Log.error("findById(Object): Exception: " + e.toString());
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
			Collection<Collection<String>> programmeList) throws SQLException {

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
			singleProgramme.add(rs.getString("COURSEPROVIDERSHORTNAME")); // 17
			singleProgramme.add(rs.getString("COURSEPROVIDERNAME")); // 18
			programmeList.add(singleProgramme);
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
