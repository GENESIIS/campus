package com.genesiis.campus.entity;

//20161208 JH c39-add-course-provider UsernameDAO.java created
//20161209 JH c39-add-course-provider findById method coding 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.validation.UserType;

public class FeaturedProviderUsernameDAO implements ICrud {
	static org.apache.log4j.Logger log = Logger
			.getLogger(FeaturedProviderUsernameDAO.class.getName());

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

		final CourseProviderAccount courseProviderAccount = (CourseProviderAccount) code;
		String query = " SELECT * FROM [CAMPUS].[COURSEPROVIDERACCOUNT] WHERE USERNAME = ? AND USERTYPE = ? AND ISACTIVE = ? ";

		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			final Collection<Collection<String>> usernameCollection = null;

			preparedStatement = conn.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					final ArrayList<String> singleAccountList = new ArrayList<String>();

					singleAccountList.add(rs.getString("CODE"));
					singleAccountList.add(rs.getString("NAME"));
					singleAccountList.add(rs.getString("USERNAME"));
					singleAccountList.add(rs.getString("PASSWORD"));
					singleAccountList.add(rs.getString("EMAIL"));
					singleAccountList.add(rs.getString("DESCRIPTION"));
					singleAccountList.add(rs.getString("COURSEPROVIDER"));
				}

			}

		} catch (SQLException sqlException) {

			log.error("finById method SQLException " + sqlException);
			throw sqlException;

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
