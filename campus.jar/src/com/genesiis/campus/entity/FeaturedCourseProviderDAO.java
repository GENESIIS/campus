package com.genesiis.campus.entity;

//20161122 JH c39-add-course-provider CourseProviderDAO created
//20170324 CW c157-add-tutor-employment-details-cw getAll method created

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class FeaturedCourseProviderDAO implements ICrud {

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
		
		final Collection<Collection<String>> allFeaturedCourseProviderList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int countryCode = 0;
		double townCode = 0;
		String countryName = null;
		String townName = null;
					
		StringBuilder queryBuilder = new StringBuilder("SELECT [CODE], [UNIQUEPREFIX], [SHORTNAME], [NAME], [DESCRIPTION], [GENERALEMAIL], ");
		queryBuilder.append("[COURSEINQUIRYEMAIL], [LANDPHONECOUNTRYCODE], [LANDPHONEAREACODE], [LANDPHONENO], ");
		queryBuilder.append("[LANDPHONE2NO], [FAXNO], [MOBILEPHONECOUNTRYCODE], [MOBILEPHONENETWORKCODE], [MOBILEPHONENO], ");		
		queryBuilder.append("[HEADERIMAGEPATH], [LOGOIMAGEPATH], [SPECIALITY], [WEBLINK], [FACEBOOKURL], [TWITTERURL], ");
		queryBuilder.append("[MYSPACEURL], [LINKEDINURL], [INSTAGRAMURL], [VIBERNUMBER], [WHATSAPPNUMBER], [EXPIRATIONDATE], ");
		queryBuilder.append("[ADDRESS1], [ADDRESS2], [ADDRESS3], [ACCOUNTTYPE], [HEADOFFICETOWN], [ISTUTORRELATED], ");
		queryBuilder.append("[ISADMINALLOWED], [COURSEPROVIDERSTATUS], [COURSEPROVIDERTYPE], [PRINCIPAL], [TUTOR], [CRTON], ");
		queryBuilder.append("[CRTBY], [MODON], [MODBY] FROM [CAMPUS].[COURSEPROVIDER] WHERE [ACCOUNTTYPE] = 1 ORDER BY [NAME]");
			
		try {
			
			Tutor tutor = (Tutor) code; 
			conn = ConnectionManager.getConnection();			
			
			stmt = conn.prepareStatement(queryBuilder.toString());
			stmt.setInt(1, tutor.getCode());
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleFeaturedCourseProviderList = new ArrayList<String>();		
												
				singleFeaturedCourseProviderList.add(rs.getString("CODE"));
				singleFeaturedCourseProviderList.add(rs.getString("USERNAME"));
				singleFeaturedCourseProviderList.add(rs.getString("PASSWORD"));
				singleFeaturedCourseProviderList.add(rs.getString("FIRSTNAME"));
				singleFeaturedCourseProviderList.add(rs.getString("MIDDLENAME"));
				singleFeaturedCourseProviderList.add(rs.getString("LASTNAME"));
				singleFeaturedCourseProviderList.add(rs.getString("GENDER"));
				singleFeaturedCourseProviderList.add(rs.getString("EMAIL"));
				singleFeaturedCourseProviderList.add(rs.getString("LANDPHONECOUNTRYCODE"));
				singleFeaturedCourseProviderList.add(rs.getString("LANDPHONEAREACODE"));
				singleFeaturedCourseProviderList.add(rs.getString("LANDPHONENUMBER"));
				singleFeaturedCourseProviderList.add(rs.getString("MOBILEPHONECOUNTRYCODE"));
				singleFeaturedCourseProviderList.add(rs.getString("MOBILEPHONENETWORKCODE"));
				singleFeaturedCourseProviderList.add(rs.getString("MOBILEPHONENUMBER"));
				singleFeaturedCourseProviderList.add(rs.getString("DESCRIPTION").trim());
				singleFeaturedCourseProviderList.add(rs.getString("EXPERIENCE").trim());
				singleFeaturedCourseProviderList.add(rs.getString("WEBLINK"));
				singleFeaturedCourseProviderList.add(rs.getString("FACEBOOKURL"));
				singleFeaturedCourseProviderList.add(rs.getString("TWITTERURL"));
				singleFeaturedCourseProviderList.add(rs.getString("MYSPACEURL"));
				singleFeaturedCourseProviderList.add(rs.getString("LINKEDINURL"));
				singleFeaturedCourseProviderList.add(rs.getString("INSTAGRAMURL"));
				singleFeaturedCourseProviderList.add(rs.getString("VIBERNUMBER"));
				singleFeaturedCourseProviderList.add(rs.getString("WHATSAPPNUMBER"));
				singleFeaturedCourseProviderList.add(rs.getString("ADDRESS1"));
				singleFeaturedCourseProviderList.add(rs.getString("ADDRESS2"));
				singleFeaturedCourseProviderList.add(rs.getString("ADDRESS3"));
				singleFeaturedCourseProviderList.add(rs.getString("TOWNNAME"));
				singleFeaturedCourseProviderList.add(rs.getString("TOWN"));				
				singleFeaturedCourseProviderList.add(rs.getString("USERTYPE"));
				singleFeaturedCourseProviderList.add(rs.getString("COUNTRYNAME"));
				singleFeaturedCourseProviderList.add(rs.getString("ISAPPROVED"));
				singleFeaturedCourseProviderList.add(rs.getString("TUTORSTATUS"));

				allFeaturedCourseProviderList.add(singleFeaturedCourseProviderList);
			}
		} catch (ClassCastException cce) {
			log.error("findById(): ClassCastException " + cce.toString());
			throw cce;
		} catch (SQLException sqlException) {
			log.info("findById(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById(): Exception " + e.toString());
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

}
