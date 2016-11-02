package com.genesiis.campus.entity;

//20161025 CM c13-Display course details INIT ProgrammeDAO.java
//20161025 CM c13-Display course details Modified findById() method
//20161026 CM c13-Display course details Modified findById() method
//20161027 CM c13-Display course details Change query according to new DDL
//20161101 CM c13-Display course details Change findById() sql query 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.genesiis.campus.command.CmdViewProgramme;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;

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
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		final Collection<Collection<String>> programmeDetails = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			final Programme programme = (Programme) code;

			conn = ConnectionManager.getConnection();

			String query = "SELECT p.NAME,p.DESCRIPTION,p.DURATION,p.ENTRYREQUIREMENTS,p.COUNSELORNAME,p.COUNSELORPHONE,  c.NAME, c.WEBLINK,p.IMAGE,l.NAME,m.NAME,p.EMAIL from CAMPUS.PROGRAMME p"
					+ " inner join CAMPUS.COURSEPROVIDER c on p.COURSEPROVIDER=c.CODE inner join CAMPUS.LEVEL l on p.level=l.code inner join CAMPUS.MAJOR m on m.code=p.major where p.CODE=?";
			preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setInt(1, programme.getCode());
			ResultSet rs = preparedStatement.executeQuery();
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			if (rs.next()) {

				final ArrayList<String> singleprogrameDetails = new ArrayList<String>();
				calculateYears(rs.getString(3));
				singleprogrameDetails.add(rs.getString(1));// Programme name
				singleprogrameDetails.add(rs.getString(2));// Description
				singleprogrameDetails.add(rs.getString(3));// Duration
				singleprogrameDetails.add(rs.getString(4));// Entry requirements
				singleprogrameDetails.add(rs.getString(5));// Counselor Name
				singleprogrameDetails.add(rs.getString(6));// counselorPhone
				singleprogrameDetails.add(rs.getString(7));// Course provider
															// Name
				singleprogrameDetails.add(rs.getString(8));// Course provider
															// Web link
				singleprogrameDetails.add(rs.getString(9));// Image
				singleprogrameDetails.add(rs.getString(10));// Level Name
				singleprogrameDetails.add(rs.getString(11));// Major Name
				singleprogrameDetails.add(String.valueOf(years));//years
				singleprogrameDetails.add(String.valueOf(months));//months
				singleprogrameDetails.add(String.valueOf(weeks));//weeks
				singleprogrameDetails.add(String.valueOf(days));//days
				singleprogrameDetails.add(rs.getString(12));
				
				programmeDetails.add(singleprogrameDetails);

			}
		} catch (Exception exception) {
			log.error("findById(Object code):  exception"
					+ exception.toString());
			throw exception;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return programmeDetails;
	}

	/**
	 * Calculate number of years in the Duration
	 * 
	 * @author Chathuri
	 * @param duration
	 * @return void
	 */
	public void calculateYears(String duration) {
		try {

			float inputAsFloat = Float.parseFloat(duration);

			// the input is an integral day count, with a possible fractional
			// part representing time as a fraction of one day
			int totalDays = (int) inputAsFloat;

			// ignores leap years
			years = (int) totalDays / 365;
			totalDays %= 365;

			calculateMonth(totalDays);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Calculate number of months in the Duration
	 * 
	 * @author Chathuri
	 * @param duration
	 * @return void
	 */
	public void calculateMonth(int totalDays) {
		try {

			// assumes all months have 30 days
			months = (int) totalDays / 30;
			totalDays %= 30;

			calculateWeeks(totalDays);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Calculate number of weeks in the Duration
	 * 
	 * @author Chathuri
	 * @param duration
	 * @return void
	 */
	public void calculateWeeks(int totalDays) {
		try {
			weeks = (int) totalDays / 7;
			totalDays %= 7;
			calculateDates(totalDays);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Calculate number of days in the Duration
	 * 
	 * @author Chathuri
	 * @param duration
	 * @return void
	 */
	public void calculateDates(int totalDays) {
		try {
			days = (int) totalDays;

		} catch (Exception e) {
			// TODO: handle exception
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
