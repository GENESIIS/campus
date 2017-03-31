package com.genesiis.campus.entity;

//20161122 JH c39-add-course-provider CourseProviderDAO created
//20170324 CW c157-add-tutor-employment-details-cw getAll method created
//20170326 CW c157-add-tutor-employment-details-cw modified getAll method to get details correctly
//20170326 CW c157-add-tutor-employment-details-cw modified getAll method to get string values correctly
//20170328 CW c157-add-tutor-employment-details-cw added getTutorSelectedFCP
//20170328 CW c157-add-tutor-employment-details-cw added UNIQUEPREFIX to getAll method
				// add trim to the string values in getTutorSelectedFCP method
//20170330 CW c157-add-tutor-employment-details-cw modified getTutorSelectedFCP method to query the code in EMPLOYMENT table
//20170331 CW c157-add-tutor-employment-details-cw add getFCPListForTutorToSelect method

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * FeaturedCourseProviderDAO class used to handle basic operations of the featured
 * course provider
 * @author JH
 *
 */
public class FeaturedCourseProviderDAO implements ICrud {
	
	static Logger log = Logger.getLogger(FeaturedCourseProviderDAO.class.getName());

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

	/**
	 * Returns all the Featured Course Provider Details list
	 * @author Chinthaka 
	 * @return Returns the Featured Course Provider Details in Database from a collection of collection
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		
		final Collection<Collection<String>> allFeaturedCourseProviderList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
					
		StringBuilder queryBuilder = new StringBuilder("SELECT [CODE], [UNIQUEPREFIX], [SHORTNAME], [NAME], [DESCRIPTION], [GENERALEMAIL], ");
		queryBuilder.append("[COURSEINQUIRYEMAIL], [LANDPHONECOUNTRYCODE], [LANDPHONEAREACODE], [LANDPHONENO], ");
		queryBuilder.append("[LANDPHONE2NO], [FAXNO], [MOBILEPHONECOUNTRYCODE], [MOBILEPHONENETWORKCODE], [MOBILEPHONENO], ");		
		queryBuilder.append("[HEADERIMAGEPATH], [LOGOIMAGEPATH], [SPECIALITY], [WEBLINK], [FACEBOOKURL], [TWITTERURL], ");
		queryBuilder.append("[MYSPACEURL], [LINKEDINURL], [INSTAGRAMURL], [VIBERNUMBER], [WHATSAPPNUMBER], [EXPIRATIONDATE], ");
		queryBuilder.append("[ADDRESS1], [ADDRESS2], [ADDRESS3], [ACCOUNTTYPE], [HEADOFFICETOWN], [ISTUTORRELATED], ");
		queryBuilder.append("[ISADMINALLOWED], [COURSEPROVIDERSTATUS], [COURSEPROVIDERTYPE], [PRINCIPAL], [TUTOR], [CRTON], ");
		queryBuilder.append("[CRTBY], [MODON], [MODBY] FROM [CAMPUS].[COURSEPROVIDER] WHERE [ACCOUNTTYPE] = 1 ORDER BY [NAME]");
			
		try {
			
			conn = ConnectionManager.getConnection();						
			stmt = conn.prepareStatement(queryBuilder.toString());
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleFeaturedCourseProviderList = new ArrayList<String>();		
												
				singleFeaturedCourseProviderList.add(rs.getString("CODE"));
				singleFeaturedCourseProviderList.add(rs.getString("UNIQUEPREFIX"));
				singleFeaturedCourseProviderList.add(rs.getString("SHORTNAME"));
				singleFeaturedCourseProviderList.add(rs.getString("NAME"));
				singleFeaturedCourseProviderList.add(rs.getString("DESCRIPTION"));
				singleFeaturedCourseProviderList.add(rs.getString("GENERALEMAIL"));
				singleFeaturedCourseProviderList.add(rs.getString("COURSEINQUIRYEMAIL"));
				singleFeaturedCourseProviderList.add(rs.getString("LANDPHONECOUNTRYCODE"));
				singleFeaturedCourseProviderList.add(rs.getString("LANDPHONEAREACODE"));
				singleFeaturedCourseProviderList.add(rs.getString("LANDPHONENO"));
				singleFeaturedCourseProviderList.add(rs.getString("LANDPHONE2NO"));
				singleFeaturedCourseProviderList.add(rs.getString("FAXNO"));
				singleFeaturedCourseProviderList.add(rs.getString("MOBILEPHONECOUNTRYCODE"));
				singleFeaturedCourseProviderList.add(rs.getString("MOBILEPHONENETWORKCODE"));
				singleFeaturedCourseProviderList.add(rs.getString("MOBILEPHONENO"));
				singleFeaturedCourseProviderList.add(rs.getString("HEADERIMAGEPATH"));
				singleFeaturedCourseProviderList.add(rs.getString("LOGOIMAGEPATH"));
				singleFeaturedCourseProviderList.add(rs.getString("SPECIALITY"));
				singleFeaturedCourseProviderList.add(rs.getString("WEBLINK"));
				singleFeaturedCourseProviderList.add(rs.getString("FACEBOOKURL"));
				singleFeaturedCourseProviderList.add(rs.getString("TWITTERURL"));
				singleFeaturedCourseProviderList.add(rs.getString("MYSPACEURL"));
				singleFeaturedCourseProviderList.add(rs.getString("LINKEDINURL"));
				singleFeaturedCourseProviderList.add(rs.getString("INSTAGRAMURL"));
				singleFeaturedCourseProviderList.add(rs.getString("VIBERNUMBER"));
				singleFeaturedCourseProviderList.add(rs.getString("WHATSAPPNUMBER"));
				singleFeaturedCourseProviderList.add(rs.getString("EXPIRATIONDATE"));
				singleFeaturedCourseProviderList.add(rs.getString("ADDRESS1"));
				singleFeaturedCourseProviderList.add(rs.getString("ADDRESS2"));				
				singleFeaturedCourseProviderList.add(rs.getString("ADDRESS3"));
				singleFeaturedCourseProviderList.add(rs.getString("ACCOUNTTYPE"));
				singleFeaturedCourseProviderList.add(rs.getString("HEADOFFICETOWN"));
				singleFeaturedCourseProviderList.add(rs.getString("ISTUTORRELATED"));
				singleFeaturedCourseProviderList.add(rs.getString("ISADMINALLOWED"));
				singleFeaturedCourseProviderList.add(rs.getString("COURSEPROVIDERSTATUS"));				
				singleFeaturedCourseProviderList.add(rs.getString("COURSEPROVIDERTYPE"));
				singleFeaturedCourseProviderList.add(rs.getString("PRINCIPAL"));
				singleFeaturedCourseProviderList.add(rs.getString("TUTOR"));
				singleFeaturedCourseProviderList.add(rs.getString("CRTON"));
				singleFeaturedCourseProviderList.add(rs.getString("CRTBY"));				
				singleFeaturedCourseProviderList.add(rs.getString("MODON"));
				singleFeaturedCourseProviderList.add(rs.getString("MODBY"));

				allFeaturedCourseProviderList.add(singleFeaturedCourseProviderList);
			}		
		} catch (SQLException sqlException) {
			log.info("getAll(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll(): Exception " + e.toString());
			throw e;
		} finally {			
			DaoHelper.cleanup(conn, stmt, rs);
		}
		return allFeaturedCourseProviderList;

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
	
	/**
	 * Returns all the tutor selected Featured Course Provider Details list
	 * @author CW
	 * @return Returns the tutor selected Featured Course Provider Details in Database from a collection of collection
	 */
	public static Collection<Collection<String>> getTutorSelectedFCP(String tutorCode) throws SQLException, Exception {

		final Collection<Collection<String>> allTutorSelectedFCPList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String query = "SELECT CP.CODE, CP.SHORTNAME, CP.NAME, CP.SPECIALITY, CP.ADDRESS1 + CP.ADDRESS2 + CP.ADDRESS3 ADDRESS, "
				+ "EMP.CODE EMPLOYERCODE FROM CAMPUS.COURSEPROVIDER CP	"
				+ "INNER JOIN CAMPUS.EMPLOYMENT EMP ON CP.CODE = EMP.COURSEPROVIDER "
				+ "WHERE CP.CODE IN (SELECT EMP.COURSEPROVIDER FROM CAMPUS.EMPLOYMENT EMP WHERE EMP.TUTOR = ?)";
		
		try {			
			conn = ConnectionManager.getConnection();						
			stmt = conn.prepareStatement(query);
			stmt.setString(1, tutorCode);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleTutorSelectedFCPList = new ArrayList<String>();		
												
				singleTutorSelectedFCPList.add((rs.getString("CODE")).trim());
				singleTutorSelectedFCPList.add((rs.getString("SHORTNAME")).trim());
				singleTutorSelectedFCPList.add((rs.getString("NAME")).trim());
				singleTutorSelectedFCPList.add((rs.getString("SPECIALITY")).trim());
				singleTutorSelectedFCPList.add((rs.getString("ADDRESS")).trim());
				singleTutorSelectedFCPList.add((rs.getString("EMPLOYERCODE")).trim());
				singleTutorSelectedFCPList.add(tutorCode);
								
				allTutorSelectedFCPList.add(singleTutorSelectedFCPList);
			}		
		} catch (SQLException sqlException) {
			log.info("getTutorSelectedFCP(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getTutorSelectedFCP(): Exception " + e.toString());
			throw e;
		} finally {			
			DaoHelper.cleanup(conn, stmt, rs);
		}
		return allTutorSelectedFCPList;
	}

	/**
	 * Returns the Featured Course Provider list which tutor does not added earlier
	 * @author Chinthaka 
	 * @return Returns the code & name of Featured Course Provider Details in Database from a collection of collection
	 */
	public Collection<Collection<String>> getFCPListForTutorToSelect(String tutorCode) throws SQLException, Exception {
		
		final Collection<Collection<String>> allFeaturedCourseProviderList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
					
		StringBuilder queryBuilder = new StringBuilder("SELECT CP.CODE CODE, CP.NAME NAME FROM CAMPUS.COURSEPROVIDER CP ");
		queryBuilder.append("WHERE CP.ACCOUNTTYPE = 1 ");
		queryBuilder.append("AND CP.CODE NOT IN (SELECT EMP.COURSEPROVIDER FROM CAMPUS.EMPLOYMENT EMP WHERE EMP.TUTOR = ? )");		
		queryBuilder.append("ORDER BY NAME");
			
		try {
			
			conn = ConnectionManager.getConnection();						
			stmt = conn.prepareStatement(queryBuilder.toString());
			stmt.setString(1, tutorCode);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleFeaturedCourseProviderList = new ArrayList<String>();		
												
				singleFeaturedCourseProviderList.add(rs.getString("CODE"));
				singleFeaturedCourseProviderList.add(rs.getString("NAME"));

				allFeaturedCourseProviderList.add(singleFeaturedCourseProviderList);
			}		
		} catch (SQLException sqlException) {
			log.info("getAll(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll(): Exception " + e.toString());
			throw e;
		} finally {			
			DaoHelper.cleanup(conn, stmt, rs);
		}
		return allFeaturedCourseProviderList;

	}
}
