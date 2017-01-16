package com.genesiis.campus.entity;

//20161208 JH c39-add-course-provider UsernameDAO.java created
//20161209 JH c39-add-course-provider findById method coding 
//20161209 JH c39-add-course-provider class name renamed to CourseProviderUsernameDAO.java
//20161222 JH c39-add-course-provider findById method modified: added missing parameters
//20161223 JH c39-add-course-provider findById method query modified
//20161227 JH c39-add-course-provider findById method query modified
//20161228 JH c39-add-course-provider findById method modifie: sql exception fixed
//20170117 JH c39-add-course-provider implemented DaoHelper class to close resources

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

public class CourseProviderUsernameDAO implements ICrud {
	static org.apache.log4j.Logger log = Logger
			.getLogger(CourseProviderUsernameDAO.class.getName());

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
	 * provider username or email
	 */
	public Collection findById(Object code) throws SQLException, Exception {

		String query = " SELECT * FROM [CAMPUS].[COURSEPROVIDERACCOUNT] WHERE USERNAME = ? OR EMAIL = ? ";

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		Collection<Collection<String>> usernameCollection = new ArrayList<Collection<String>>();
		ResultSet rs = null;

		try {
			final CourseProviderAccount courseProviderAccount = (CourseProviderAccount) code;
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			String username = courseProviderAccount.getUsername();
			String email = courseProviderAccount.getEmail();
			preparedStatement.setString(1, courseProviderAccount.getUsername());
			preparedStatement.setString(2, courseProviderAccount.getEmail());

			rs = preparedStatement.executeQuery();

				while(rs.next()){
					
					final ArrayList<String> singleAccountList = new ArrayList<String>();
					
					singleAccountList.add(rs.getString("CODE"));
					singleAccountList.add(rs.getString("NAME"));
					singleAccountList.add(rs.getString("USERNAME"));
					singleAccountList.add(rs.getString("PASSWORD"));
					singleAccountList.add(rs.getString("EMAIL"));
					singleAccountList.add(rs.getString("DESCRIPTION"));
					singleAccountList.add(rs.getString("COURSEPROVIDER"));
					
					final Collection<String> singleAccountColleciton = singleAccountList;
					usernameCollection.add(singleAccountColleciton);
				}

		} catch (SQLException sqlException) {

			log.error("finById method SQLException " + sqlException);
			throw sqlException;

		} catch (Exception exception) {

			log.error("findById method Exception " + exception.toString());
			throw exception;

		} finally {
			DaoHelper.cleanup(conn, preparedStatement, rs);

		}
		return usernameCollection;
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
