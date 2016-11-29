package com.genesiis.campus.entity;

//20161025 CM c13-Display course details INIT ProgrammeDAO.java
//20161025 CM c13-Display course details Modified findById() method
//20161026 CM c13-Display course details Modified findById() method
//20161027 CM c13-Display course details Change query according to new DDL
//20161101 CM c13-Display course details Change findById() sql query 
//20161110 CM c13-Display-course-details Formatted code 
//20161115 CM c13-Display-course-details Removed toString() method calling in string query variable.
//20161115 CM c13-Display-course-details Removed unused variable
//20161115 CM c13-Display-course-details Removed duration calculation methods and moved to validator class
//20161116 CM c13-Display-course-details Modified findById() method
//20161029 PN c11-criteria-based-filter-search implemented getAll() method for retrieve existing details
//20161102 PN c11-criteria-based-filter-search implementing findById() method to retrieve data according to the criteria.
//20161103 PN c11-criteria-based-filter-search modified getAll() method and findById() method by changing the SQL query.
//20161115 PN c1-campus-landing-page added functional comments to the methods. formatted the error logs.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.Validator;
import com.genesiis.campus.util.IQueryBuilder;
import com.genesiis.campus.util.QueryBuildingHelper;
import java.util.Map;

public class ProgrammeDAO implements ICrud {

	static Logger log = Logger.getLogger(ProgrammeDAO.class.getName());

	private int years;
	private int months;
	private int weeks;
	private int days;

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
	 * Search Programme details(Programme, level, major) and course provider
	 * details relevant to the programme
	 * 
	 * @author Chathuri
	 * @param Object
	 *            : programme object of Object type
	 * @return Collection<Collection<String>> of Collection
	 */

	@Override
	public Collection<Collection<String>> findById(Object code)	throws SQLException, Exception {
		final Collection<Collection<String>> programmeDetails = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			final Programme programme = (Programme) code;
			final Validator validator=new Validator();
			
			conn = ConnectionManager.getConnection();

			String query = "SELECT p.NAME,p.DESCRIPTION,p.DURATION,p.ENTRYREQUIREMENTS,p.COUNSELORNAME,p.COUNSELORPHONE,  c.NAME, c.UNIQUEPREFIX,p.IMAGE,l.NAME,m.NAME,p.EMAIL,c.ACCOUNTTYPE from CAMPUS.PROGRAMME p"
					+ " inner join CAMPUS.COURSEPROVIDER c on p.COURSEPROVIDER=c.CODE inner join CAMPUS.LEVEL l on p.level=l.code inner join CAMPUS.MAJOR m on m.code=p.major where p.CODE=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, programme.getCode());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {

				final ArrayList<String> singleprogrameDetails = new ArrayList<String>();
				
				singleprogrameDetails.add(rs.getString(1));// Programme name
				singleprogrameDetails.add(rs.getString(2));// Description
				singleprogrameDetails.add(rs.getString(3));// Duration
				singleprogrameDetails.add(rs.getString(4));// Entry requirements
				singleprogrameDetails.add(rs.getString(5));// Counselor Name
				singleprogrameDetails.add(rs.getString(6));// counselorPhone
				singleprogrameDetails.add(rs.getString(7));// Course provider
															// Name
				int accountType = Integer.parseInt(rs.getString(13));
				if (accountType == 1) {
					singleprogrameDetails.add(rs.getString(8) + ".campus.lk");// Course
																				// provider
																				// mini
																				// Web
																				// link
				} else {
					singleprogrameDetails.add("#");// Course provider Web link
				}

				singleprogrameDetails.add(rs.getString(9));// Image
				singleprogrameDetails.add(rs.getString(10));// Level Name
				singleprogrameDetails.add(rs.getString(11));// Major Name
				
				//Calculate years/Months/weeks/dates count
				years=validator.calculateYears(rs.getString(3));
				months=validator.calculateMonths();
				weeks=validator.calculateWeeks();
				days=validator.calculateDates();
				
				singleprogrameDetails.add(String.valueOf(years));// years
				singleprogrameDetails.add(String.valueOf(months));// months
				singleprogrameDetails.add(String.valueOf(weeks));// weeks
				singleprogrameDetails.add(String.valueOf(days));// days
				singleprogrameDetails.add(rs.getString(12));

				final Collection<String> singleProgrammeCollection = singleprogrameDetails;
				
				programmeDetails.add(singleProgrammeCollection);

			}
		} catch (Exception exception) {
			log.error("findById(Object code):  exception"
					+ exception.toString());
			throw exception;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return programmeDetails;
	}


	/**
	 * @author pabodha
	 * @return Collection<Collection<String>>: contains all the available
	 *         programs in DB.
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		final Collection<Collection<String>> allProgrammeList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = " SELECT p.[CODE], p.[NAME], p.[IMAGE], p.[DESCRIPTION], p.[DISPLAYSTARTDATE], cp.[NAME] as [PROVIDER], cp.[UNIQUEPREFIX] "
					+ "FROM [CAMPUS].[PROGRAMME] p "
					+ "JOIN [CAMPUS].[COURSEPROVIDER] cp ON cp.CODE = p.COURSEPROVIDER "
					+ "WHERE p.PROGRAMMESTATUS = 1";

			stmt = conn.prepareStatement(query);
			final ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleProgrammeList = new ArrayList<String>();
				singleProgrammeList.add(rs.getString("CODE"));
				singleProgrammeList.add(rs.getString("NAME"));
				singleProgrammeList.add(rs.getString("DESCRIPTION"));
				singleProgrammeList.add(rs.getString("DISPLAYSTARTDATE"));
				singleProgrammeList.add(rs.getString("PROVIDER"));
				singleProgrammeList.add(rs.getString("UNIQUEPREFIX"));
				singleProgrammeList.add(rs.getString("IMAGE"));

				final Collection<String> singleProgrammeCollection = singleProgrammeList;
				allProgrammeList.add(singleProgrammeCollection);
			}
		} catch (SQLException sqlException) {
			log.error("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("getAll(): E " + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allProgrammeList;
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
