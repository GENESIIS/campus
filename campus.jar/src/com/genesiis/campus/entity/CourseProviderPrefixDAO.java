package com.genesiis.campus.entity;

//20161227 JH c39-add-course-provider CourseProviderPrefixDAO.java created

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.util.ConnectionManager;

public class CourseProviderPrefixDAO implements ICrud {

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

	public Collection findById(Object code) throws SQLException, Exception {
		final CourseProviderAccount courseProviderAccount = (CourseProviderAccount) code;
		String query = " SELECT * FROM [CAMPUS].[COURSEPROVIDERACCOUNT] WHERE USERNAME = ? OR EMAIL = ? ";

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		final Collection<Collection<String>> usernameCollection = null;
		
		try {
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, courseProviderAccount.getUsername());
			preparedStatement.setString(2,courseProviderAccount.getEmail());
			
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				while (rs.next()) {
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
