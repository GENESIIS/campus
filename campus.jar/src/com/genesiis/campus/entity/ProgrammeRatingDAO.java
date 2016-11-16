package com.genesiis.campus.entity;

//20161111 CM c13-Display-course-details INIT ProgrammeRatingDAO.java
//20161111 CM c13-Display-course-details Modified findById().
//20161115 CM c13-Display-course-details Removed toString() method calling in string query variable.
//20161116 CM c13-Display-course-details Modified findById() method

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;


public class ProgrammeRatingDAO implements ICrud {

	static Logger log = Logger.getLogger(ProgrammeRatingDAO.class.getName());
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
		final Collection<Collection<String>> programmeRating = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			final Programme programme = (Programme) code;

			conn = ConnectionManager.getConnection();

			String query = "SELECT RATINGVALUE,COUNT(RATINGVALUE) FROM CAMPUS.RATING WHERE PROGRAMME=? GROUP BY RATINGVALUE ";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, programme.getCode());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleProgrammrRating = new ArrayList<String>();

				singleProgrammrRating.add(rs.getString("RATINGVALUE"));//rating value
				singleProgrammrRating.add(rs.getString(2));//rating count
				
				final Collection<String> singleoProgrammeRatingCollection = singleProgrammrRating;
				
				programmeRating.add(singleoProgrammeRatingCollection);

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
		return programmeRating;
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
