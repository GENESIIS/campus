package com.genesiis.campus.entity;

//20161025 JH c7-list-higher-education-courses data access object class InstituteDAO.java created
//20161025 JH c7-list-higher-education-courses implement unimplemented methods
//20161026 JH c7-higher-education-landing-page InstituteDAO.java renamed as CourseProviderDAO.java
//20161026 JH c7-higher-education-landing-page findById(Object code) method coding 
//20161027 JH c7-higher-education-landing-page findById(Object code) method modified
//20161028 JH c7-higher-education-landing-page findById(Object code) query modified 
//20161028 JH c7-higher-education-landing-page CourseProviderDAO.java renamed as CourseProviderProgrammeDAO.java

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.util.ConnectionManager;

public class CourseProviderProgrammeDAO implements ICrud {

	static org.apache.log4j.Logger log = Logger
			.getLogger(CourseProviderProgrammeDAO.class.getName());

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
	 * findById(Object code) is used to get all of the course providers belongs
	 * to the given course provider type and where they are active. Ex: course
	 * category = higher education, status = 1
	 * 
	 * @param Object
	 *            code
	 * @return collection of type String
	 * @author JH
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {

		Connection conn = null;
		PreparedStatement preparedStatement = null;

		int status = 0;
		String returnMessage = "";
		final Collection<Collection<String>> courseProviderCollection = new ArrayList<Collection<String>>();

		/**
		 * getAllQuery used to query the database to retrieve data based on
		 * several criteria. 1. select programme stat for each programme for
		 * given category 2. programmes that were expired within a year from the
		 * current date is considered 3.
		 */
		String getAllQuery = " SELECT p.COURSEPROVIDER , COUNT(*) FROM [CAMPUS].[PROGRAMME] p "
				+ " INNER JOIN [CAMPUS].[PROGRAMMESTAT] ps ON p.CODE = ps.PROGRAMME AND p.CATEGORY = ?"
				+ " AND p.EXPIRYDATE > GETDATE() "
				+ " INNER JOIN [CAMPUS].[COURSEPROVIDER] cp on cp.CODE = p.COURSEPROVIDER AND cp.COURSEPROVIDERSTATUS = 1 "
				+ " GROUP BY p.COURSEPROVIDER ORDER BY  count(*) DESC  ";

		try {

			final CourseProvider courseProvider = (CourseProvider) code;
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(getAllQuery);

			preparedStatement.setInt(1, courseProvider.getCourseProviderType());
			preparedStatement.setInt(2,
					courseProvider.getCourseProviderStatus());

			final ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleCourseProviderList = new ArrayList<String>();

				log.info(rs.getString("CODE"));
				singleCourseProviderList.add(rs.getString("CODE"));
				singleCourseProviderList.add(rs.getString("UNIQUEPREFIX"));
				singleCourseProviderList.add(rs.getString("NAME"));
				singleCourseProviderList.add(rs.getString("GENERALEMAIL"));
				singleCourseProviderList
						.add(rs.getString("COURSEINQUIRYEMAIL"));
				singleCourseProviderList.add(rs
						.getString("LANDPHONECOUNTRYCODE"));
				singleCourseProviderList.add(rs
						.getString("LANDPHONECOUNTRYCODE"));
				singleCourseProviderList.add(rs.getString("LANDPHONEAREACODE"));
				singleCourseProviderList.add(rs.getString("LANDPHONENO"));
				singleCourseProviderList.add(rs.getString("LANDPHONE2NO"));
				singleCourseProviderList.add(rs
						.getString("MOBILEPHONECOUNTRYCODE"));
				singleCourseProviderList.add(rs
						.getString("MOBILEPHONENETWORKCODE"));
				singleCourseProviderList.add(rs.getString("MOBILEPHONENO"));
				singleCourseProviderList.add(rs.getString("HEADERIMAGEPATH"));
				singleCourseProviderList.add(rs.getString("LOGOIMAGEPATH"));
				singleCourseProviderList.add(rs.getString("SPECIALITY"));
				singleCourseProviderList.add(rs.getString("WEBLINK"));
				singleCourseProviderList.add(rs.getString("FACEBOOKURL"));
				singleCourseProviderList.add(rs.getString("TWITTERURL"));
				singleCourseProviderList.add(rs.getString("MYSPACEURL"));
				singleCourseProviderList.add(rs.getString("LINKEDINURL"));
				singleCourseProviderList.add(rs.getString("INSTAGRAMURL"));
				singleCourseProviderList.add(rs.getString("VIBERNUMBER"));
				singleCourseProviderList.add(rs.getString("WHATSAPPNUMBER"));
				singleCourseProviderList.add(rs.getString("EXPIRATIONDATE"));
				singleCourseProviderList.add(rs.getString("STREETNO"));
				singleCourseProviderList.add(rs.getString("STREETNAME"));
				singleCourseProviderList.add(rs.getString("ACCOUNTTYPE"));
				singleCourseProviderList.add(rs.getString("ISTUTORRELATED"));
				singleCourseProviderList.add(rs.getString("ISADMINALLOWED"));
				singleCourseProviderList.add(rs
						.getString("COURSEPROVIDERSTATUS"));
				singleCourseProviderList
						.add(rs.getString("COURSEPROVIDERTYPE"));
				singleCourseProviderList.add(rs.getString("PRINCIPAL"));
				singleCourseProviderList.add(rs.getString("TUTOR"));
				singleCourseProviderList.add(rs.getString("CRTON"));
				singleCourseProviderList.add(rs.getString("CRTBY"));
				singleCourseProviderList.add(rs.getString("MODON"));
				singleCourseProviderList.add(rs.getString("MODBY"));

				final Collection<String> singleCourseProviderCollection = singleCourseProviderList;
				courseProviderCollection.add(singleCourseProviderCollection);

			}

		} catch (SQLException exception) {
			log.error("findById(Object code) sql exception"
					+ exception.toString());
			throw exception;

		} catch (Exception exception) {
			log.error("findById(Object code) " + exception.toString());
			throw exception;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			conn.close();

		}
		return null;
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
