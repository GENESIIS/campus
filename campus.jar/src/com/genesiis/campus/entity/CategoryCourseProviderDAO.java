package com.genesiis.campus.entity;

//20161027 MM c5-corporate-training-landing-page INIT CourseProviderDAO.java 
//				and implemented findById()
//20161028 MM c5-corporate-training-landing-page Modified findById() method to use 
// 				Programme object to retrieve argument (categoryCode) to use for search
//20161028 MM c5-corporate-training-landing-page Modified findById() method to correct
//				SQL query so only distinct results are shown
//20161028 MM c5-corporate-training-landing-page Modified findById() method to incorporate
//the fetching of courseProviders that have the most popular programmes of the provided category

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;

public class CourseProviderCategoryProgrammeDAO implements ICrud {
	static Logger Log = Logger.getLogger(CategoryProgrammeDAO.class.getName());

	@Override
	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		
		final Collection<Collection<String>> courseProviderList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Programme programme = (Programme) code;
			int categoryCode = programme.getCategory();
			boolean areProvidersWithPopularProgrammes = (programme.getLevel() == 1) ? true : false; 
			
			String query = "";
			conn = ConnectionManager.getConnection();
			
			if (areProvidersWithPopularProgrammes) {				
				query = "SELECT cp1.* FROM ("
						+ "SELECT TOP 5 p.COURSEPROVIDER, count(*) as COUNT "
						+ "FROM [CAMPUS].[PROGRAMME] p "
						+ "INNER JOIN [CAMPUS].[PROGRAMMESTAT] ps ON (p.CODE = ps.PROGRAMME AND p.CATEGORY = ?) "
						+ "INNER JOIN [CAMPUS].[COURSEPROVIDER] cp ON (cp.CODE = p.COURSEPROVIDER) "
						+ "GROUP BY p.COURSEPROVIDER ORDER BY COUNT DESC"
						+ ") "
						+ "a JOIN [CAMPUS].[COURSEPROVIDER] cp1 ON (cp1.CODE = a.COURSEPROVIDER)";

				stmt = conn.prepareStatement(query);
				stmt.setInt(1, categoryCode);	
				
			} else {				
				query = "SELECT cp1.*, t.CODE AS TOWNCODE, "
						+ "t.NAME AS TOWNNAME FROM (" 
						+ "SELECT TOP 10 NEWID() as dummy, a.COURSEPROVIDER FROM "
						+ "("
						+ "SELECT DISTINCT p.COURSEPROVIDER "
						+ "FROM [CAMPUS].[PROGRAMME] p "
						+ "JOIN [CAMPUS].[CATEGORY] c ON (p.CATEGORY = c.CODE AND c.CODE = ?) "
						+ "JOIN [CAMPUS].[COURSEPROVIDER] cp ON (cp.CODE = p.COURSEPROVIDER AND COURSEPROVIDERSTATUS = ?)"
						+ ") a"
						+ ") b "
						+ "JOIN [CAMPUS].[COURSEPROVIDER] cp1 ON (b.COURSEPROVIDER = cp1.CODE) "	
						+ "JOIN [CAMPUS].[TOWN] t ON (cp1.HEADOFFICETOWN = t.CODE)";	

				stmt = conn.prepareStatement(query);
				stmt.setInt(1, categoryCode);		
				stmt.setInt(2, 1);		
			}
			
			final ResultSet rs = stmt.executeQuery();

			retrieveCourseProvidersFromResultSet(rs, courseProviderList, areProvidersWithPopularProgrammes);

		} catch (ClassCastException cce) {
			Log.info("findById(Object): ClassCastException: " + cce.toString());
			
			throw new IllegalArgumentException("The argument passed is not of expected type (CourseProvider)!");
		} catch (SQLException sqle) {
			Log.info("findById(Object): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			Log.info("findById(Object): Exception: " + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return courseProviderList;
	}
	
	private void retrieveCourseProvidersFromResultSet(ResultSet rs, 
			Collection<Collection<String>> courseProviderList, 
			boolean arePopularProgrammes) throws SQLException {

		while (rs.next()) {
			final ArrayList<String> singleCourseProvider = new ArrayList<String>();
			singleCourseProvider.add(rs.getString("CODE"));
			singleCourseProvider.add(rs.getString("SHORTNAME"));
			singleCourseProvider.add(rs.getString("NAME"));
			singleCourseProvider.add(rs.getString("GENERALEMAIL"));
			singleCourseProvider.add(rs.getString("COURSEINQUIRYEMAIL"));
			singleCourseProvider.add(rs.getString("HEADERIMAGEPATH"));
			singleCourseProvider.add(rs.getString("LOGOIMAGEPATH"));
			singleCourseProvider.add(rs.getString("DESCRIPTION"));
			singleCourseProvider.add(rs.getString("UNIQUEPREFIX"));
			singleCourseProvider.add(rs.getString("SPECIALITY"));
			singleCourseProvider.add(rs.getString("WEBLINK"));
			singleCourseProvider.add(rs.getString("FACEBOOKURL"));
			singleCourseProvider.add(rs.getString("TWITTERURL"));
			singleCourseProvider.add(rs.getString("MYSPACEURL"));
			singleCourseProvider.add(rs.getString("LINKEDINURL"));
			singleCourseProvider.add(rs.getString("INSTAGRAMURL"));
			singleCourseProvider.add(rs.getString("VIBERNUMBER"));
			singleCourseProvider.add(rs.getString("WHATSAPPNUMBER"));
			singleCourseProvider.add(rs.getString("MYSPACEURL"));
			if (!arePopularProgrammes) {
				singleCourseProvider.add(rs.getString("TOWNCODE"));
				singleCourseProvider.add(rs.getString("TOWNNAME"));
			}
			final Collection<String> singleCourseProviderCollection = singleCourseProvider;
			courseProviderList.add(singleCourseProviderCollection);
		}
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
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

}
