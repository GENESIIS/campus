package com.genesiis.campus.entity;

//20161208 JH c39-add-course-provider UsernameDAO.java created
//20161209 JH c39-add-course-provider findById method coding 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

public class UsernameDAO implements ICrud {
	static org.apache.log4j.Logger log = Logger.getLogger(UsernameDAO.class
			.getName());

	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * findById method used to get the course provider details using the course
	 * provider
	 */
	public Collection findById(Object code) throws SQLException, Exception {

		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			final Collection<Collection<String>> usernameCollection = null;

			String query = "SELECT * FROM ";

		} catch (Exception exception) {
			log.error("findById method Exception " + exception.toString());
			throw exception;
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return null;
	}

	public Collection getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int add(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
