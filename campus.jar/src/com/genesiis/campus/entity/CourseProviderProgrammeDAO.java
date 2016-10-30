package com.genesiis.campus.entity;

//20161025 JH c7-list-higher-education-courses data access object class InstituteDAO.java created
//20161025 JH c7-list-higher-education-courses implement unimplemented methods
//20161026 JH c7-higher-education-landing-page InstituteDAO.java renamed as CourseProviderDAO.java
//20161026 JH c7-higher-education-landing-page findById(Object code) method coding 
//20161027 JH c7-higher-education-landing-page findById(Object code) method modified
//20161028 JH c7-higher-education-landing-page findById(Object code) query modified 
//20161028 JH c7-higher-education-landing-page CourseProviderDAO.java renamed as CourseProviderProgrammeDAO.java
//20161030 JH c7-higher-education-landing-page findById method modified : sql exception fixed

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.Programme;
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
		PreparedStatement preparedStatement2 = null;

		int status = 0;
		String returnMessage = "";
		final Collection<Collection<String>> courseProviderCollection = new ArrayList<Collection<String>>();

		
		/**
		 * query1 used to query the database to retrieve data based on
		 * several criteria. 1. select programme stat for each programme for
		 * given category 
		 * 2. programmes that were expired within a year from the
		 * current date is considered 
		 * 3. get only top 5 course providers
		 */
		// String getAllQuery =
		// " SELECT TOP 5 p.COURSEPROVIDER , COUNT(*) FROM [CAMPUS].[PROGRAMME] p "
		// +
		// " INNER JOIN [CAMPUS].[PROGRAMMESTAT] ps ON p.CODE = ps.PROGRAMME AND p.CATEGORY = ?"
		// + " AND p.EXPIRYDATE > ? "
		// +
		// " INNER JOIN [CAMPUS].[COURSEPROVIDER] cp on cp.CODE = p.COURSEPROVIDER AND cp.COURSEPROVIDERSTATUS = 1 "
		// + " GROUP BY p.COURSEPROVIDER ORDER BY  COUNT(*) DESC  ";

		
		/**
		 * query1 returns top 5 course provider codes  with high program stat 
		 * under the given category
		 */
		String query1 = " SELECT TOP 5 p.COURSEPROVIDER , COUNT(*) FROM [CAMPUS].[PROGRAMME] p "
				+ " INNER JOIN [CAMPUS].[PROGRAMMESTAT] ps ON p.CODE = ps.PROGRAMME AND p.CATEGORY = ?"
				+ " AND p.EXPIRYDATE > ? AND PROGRAMMESTATUS = 1"
				+ " GROUP BY p.COURSEPROVIDER ORDER BY  COUNT(*) DESC  ";

		/**
		 * query2 used to get course provider details that 
		 * are listed from the query1
		 */
		String query2 = "SELECT * FROM  [CAMPUS].[COURSEPROVIDER]  WHERE COURSEPROVIDERSTATUS = 1"
				+ "AND CODE = ?";

		try {

			final Programme programme = (Programme) code;
			conn = ConnectionManager.getConnection();
			
			int type = programme.getLevel();
			String  level = String.valueOf(type);
			
	
			preparedStatement = conn.prepareStatement(query1);

			preparedStatement.setInt(1, programme.getCategory());
			preparedStatement.setDate(2, programme.getExpiryDate());

			final ResultSet rs = preparedStatement.executeQuery();

			if (rs != null) {

				while (rs.next()) {
					log.info("comes here");
					int courseProvider = rs.getInt("COURSEPROVIDER");
					
					log.info(courseProvider);
					preparedStatement2 = conn.prepareStatement(query2);
					preparedStatement2.setInt(1, courseProvider);
					
					final ResultSet rs2 = preparedStatement2.executeQuery();
					if(rs2.next()){
						
						final ArrayList<String> singleCourseProviderList = new ArrayList<String>();
						
						 singleCourseProviderList.add(rs2.getString("CODE"));
						 singleCourseProviderList.add(rs2.getString("UNIQUEPREFIX"));
						 singleCourseProviderList.add(rs2.getString("NAME"));
						 singleCourseProviderList.add(rs2.getString("GENERALEMAIL"));
						 singleCourseProviderList.add(rs2.getString("COURSEINQUIRYEMAIL"));
						 singleCourseProviderList.add(rs2.getString("LANDPHONECOUNTRYCODE"));
						 singleCourseProviderList.add(rs2.getString("LANDPHONECOUNTRYCODE"));
						 singleCourseProviderList.add(rs2.getString("LANDPHONEAREACODE"));
						 singleCourseProviderList.add(rs2.getString("LANDPHONENO"));
						 singleCourseProviderList.add(rs2.getString("LANDPHONE2NO"));
						 singleCourseProviderList.add(rs2.getString("MOBILEPHONECOUNTRYCODE"));
						 singleCourseProviderList.add(rs2.getString("MOBILEPHONENETWORKCODE"));
						 singleCourseProviderList.add(rs2.getString("MOBILEPHONENO"));
						 singleCourseProviderList.add(rs2.getString("HEADERIMAGEPATH"));
						 singleCourseProviderList.add(rs2.getString("LOGOIMAGEPATH"));
						 singleCourseProviderList.add(rs2.getString("SPECIALITY"));
						 singleCourseProviderList.add(rs2.getString("WEBLINK"));
						 singleCourseProviderList.add(rs2.getString("FACEBOOKURL"));
						 singleCourseProviderList.add(rs2.getString("TWITTERURL"));
						 singleCourseProviderList.add(rs2.getString("MYSPACEURL"));
						 singleCourseProviderList.add(rs2.getString("LINKEDINURL"));
						 singleCourseProviderList.add(rs2.getString("INSTAGRAMURL"));
						 singleCourseProviderList.add(rs2.getString("VIBERNUMBER"));
						 singleCourseProviderList.add(rs2.getString("WHATSAPPNUMBER"));
						 singleCourseProviderList.add(rs2.getString("EXPIRATIONDATE"));
						 singleCourseProviderList.add(rs2.getString("STREETNO"));
						 singleCourseProviderList.add(rs2.getString("STREETNAME"));
						 singleCourseProviderList.add(rs2.getString("ACCOUNTTYPE"));
						 singleCourseProviderList.add(rs2.getString("ISTUTORRELATED"));
						 singleCourseProviderList.add(rs2.getString("ISADMINALLOWED"));
						 singleCourseProviderList.add(rs2.getString("COURSEPROVIDERSTATUS"));
						 singleCourseProviderList.add(rs2.getString("COURSEPROVIDERTYPE"));
						 singleCourseProviderList.add(rs2.getString("PRINCIPAL"));
						 singleCourseProviderList.add(rs2.getString("TUTOR"));
						 singleCourseProviderList.add(rs2.getString("CRTON"));
						 singleCourseProviderList.add(rs2.getString("CRTBY"));
						 singleCourseProviderList.add(rs2.getString("MODON"));
						 singleCourseProviderList.add(rs2.getString("MODBY"));

						final Collection<String> singleCourseProviderCollection = singleCourseProviderList;
						courseProviderCollection
								.add(singleCourseProviderCollection);
					}


				}

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
