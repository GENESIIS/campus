package com.genesiis.campus.entity;

//20161209 DN CAm-18 student : signup : without using third party application 
//			  initial version of the UserTypeDAO.java created
//20161214 DN CAM:18 added userTypeString to the prepared statement in findById()
//20161222 DN CAMP:18 introduced methods for closing connection and creating the database Connection.
//20161229 JH c39-add-course-provider findById(): moved object type casting statement inside the try block, closed resource ResultSet
//20170201 JH c39-add-course-provider arranged imports according to the style guide

import com.genesiis.campus.util.ConnectionManager;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class UserTypeDAO implements ICrud {

	static Logger log = Logger.getLogger(UserTypeDAO.class.getName());

	@Override
	public int add(Object object) throws SQLException, Exception {

		return 0;
	}

	@Override
	public int update(Object object) throws SQLException, Exception {

		return 0;
	}

	@Override
	public int delete(Object object) throws SQLException, Exception {

		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		Connection userTypeConnection = null;
		PreparedStatement prepaire = null;
		ResultSet userCode = null;

		try {
			String userTypeString = (String) code;
			Collection<Collection<String>> outerWrapper = new ArrayList<Collection<String>>();
			userTypeConnection = createDatabaseConnection();

			StringBuilder getUserTypeSQL = new StringBuilder(
					"SELECT * FROM [CAMPUS].[USERTYPE] ");
			getUserTypeSQL
					.append(" WHERE USERTYPESTRING = ?");
			prepaire = userTypeConnection.prepareStatement(getUserTypeSQL
					.toString());
			prepaire.setString(1, userTypeString);
			userCode = prepaire.executeQuery();

			while (userCode.next()) {
				final Collection<String> singleUSerTypeList = new ArrayList<String>();
				singleUSerTypeList.add(userCode.getString("CODE"));
				singleUSerTypeList.add(userCode.getString("USERTYPESTRING"));
				outerWrapper.add(singleUSerTypeList);
			}
			return outerWrapper;
		} catch (SQLException sqle) {
			log.error("findById(): SQLException " + sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("findById(): Wxcepption" + exp.toString());
			throw exp;
		} finally {
			closeDataBaseConnection(userTypeConnection);
			if (prepaire != null)
				prepaire.close();
		}
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {

		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException,
			Exception {

		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException,
			Exception {

		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException,
			Exception {

		return 0;
	}


	/*
	 * createDatabaseConnection() establishes the database connection with the
	 * data repository
	 * 
	 * @author DN
	 * 
	 * @throw SQLException if the connection causes errors.
	 */
	private Connection createDatabaseConnection() throws SQLException {
		try {
			return ConnectionManager.getConnection();
		} catch (SQLException sqle) {
			log.error("add():SQLException :" + sqle.toString());
			throw sqle;
		}
	}

	/*
	 * this method closes the connection if the connection is not null and that
	 * connection has not been closed
	 */
	private void closeDataBaseConnection(Connection conn) throws SQLException {
		try {
			if ((conn != null) & (!conn.isClosed())) {
				conn.close();
			}
		} catch (SQLException sqle) {
			log.error("add():SQLException :" + sqle.toString());
			throw sqle;
		}
	}

}