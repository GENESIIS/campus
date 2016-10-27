package com.genesiis.campus.entity;

//20161027 MM c5-corporate-training-landing-page INIT CourseProviderDAO.java 
//				and implemented findById()

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

public class CourseProviderDAO implements ICrud {
	static Logger Log = Logger.getLogger(CorporateProgrammeDAO.class.getName());

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
			CourseProvider courseProvider = (CourseProvider) code;
			int categoryCode = courseProvider.getCourseProviderType();
			
			String query = "SELECT * FROM [CAMPUS].[COURSEPROVIDER] WHERE COURSEPROVIDERTYPE = ?";

			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, categoryCode);
			final ResultSet rs = stmt.executeQuery();

			retrieveCourseProvidersFromResultSet(rs, courseProviderList);

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
	
	private void retrieveCourseProvidersFromResultSet(ResultSet rs, Collection<Collection<String>> deptList) throws SQLException {

		while (rs.next()) {
			final ArrayList<String> singleCourseProvider = new ArrayList<String>();
			singleCourseProvider.add(rs.getString("CODE"));
			singleCourseProvider.add(rs.getString("NAME"));
			singleCourseProvider.add(rs.getString("GENERALEMAIL"));
			singleCourseProvider.add(rs.getString("COURSEINQUIRYEMAIL"));
			singleCourseProvider.add(rs.getString("HEADERIMAGEPATH"));
			singleCourseProvider.add(rs.getString("LOGOIMAGEPATH"));
			singleCourseProvider.add(rs.getString("ISACTIVE"));
			singleCourseProvider.add(rs.getString("DESCRIPTION"));
			singleCourseProvider.add(rs.getString("UNIQUEPREFIX"));
			singleCourseProvider.add(rs.getString("SPECIALITY"));
			singleCourseProvider.add(rs.getString("WEBLINK"));
			singleCourseProvider.add(rs.getString("FACEBOOKURL"));
			singleCourseProvider.add(rs.getString("TWITTERURL"));
			singleCourseProvider.add(rs.getString("PROGRAMMESTATUS"));
			singleCourseProvider.add(rs.getString("MYSPACEURL"));
			singleCourseProvider.add(rs.getString("LINKEDINURL"));
			singleCourseProvider.add(rs.getString("INSTAGRAMURL"));
			singleCourseProvider.add(rs.getString("VIBERNUMBER"));
			singleCourseProvider.add(rs.getString("WHATSAPPNUMBER"));
			singleCourseProvider.add(rs.getString("EXPIRATIONDATE"));
			singleCourseProvider.add(rs.getString("MYSPACEURL"));
			final Collection<String> singleCourseProviderCollection = singleCourseProvider;
			deptList.add(singleCourseProviderCollection);
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
