package com.genesiis.campus.entity;

//20161227 JH c39-add-course-provider CourseProviderPrefixDAO.java created

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.util.ConnectionManager;

public class CourseProviderPrefixDAO implements ICrud {
	static org.apache.log4j.Logger log = Logger
			.getLogger(CourseProviderPrefixDAO.class.getName());

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
	 * findById() method used to get the get course provider details using the prefix.
	 * @param 
	 */
	public Collection findById(Object code) throws SQLException, Exception {
		String query = " SELECT * FROM [CAMPUS].[COURSEPROVIDER] WHERE UNIQUEPREFIX = ? ";

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		final Collection<Collection<String>> prefixCollection = new ArrayList<Collection<String>>(); 
		ResultSet rs = null;

		try {
			final CourseProvider courseProvider = (CourseProvider) code;
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			String prefix = courseProvider.getUniquePrefix();
			preparedStatement.setString(1, courseProvider.getUniquePrefix());

			rs = preparedStatement.executeQuery();

				while (rs.next()) {
					final ArrayList<String> singleProviderList = new ArrayList<String>();

					singleProviderList.add(rs.getString("CODE"));
					log.info(">>>>>>>>>>>>>> number of elements " + rs.getString("CODE"));
					//singleProviderList.add(rs.getString("UNIQUEPREFIX"));
				//	singleProviderList.add(rs.getString("SHORTNAME"));

					final Collection<String> singleProviderColleciton = singleProviderList;
					prefixCollection.add(singleProviderColleciton);
				}


		} catch (SQLException sqlException) {

			log.error("finById method SQLException " + sqlException);
			throw sqlException;

		} catch (Exception exception) {

			log.error("findById method Exception " + exception.toString());
			throw exception;

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return prefixCollection;
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
