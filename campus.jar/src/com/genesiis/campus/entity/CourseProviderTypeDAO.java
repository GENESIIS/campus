package com.genesiis.campus.entity;

//20161230 JH c39-add-course-provider CourseProviderTypeDAO.java created
//20161231 JH c39-add-course-provider getAll() method modified
//20170105 JH c39-add-course-provider getAll() method modified: implement ApplicatonSatatus enum class
//20170117 JH c39-add-course-provider implemented DaoHelper class to close resources
//20170201 JH c39-add-course-provider arranged imports according to the style guide
//20140221 JH c141-add-course-provider-issue-improvements added doc comments and changed logger statements

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Handles basic operations of course provider types
 * @author JH
 *
 */
public class CourseProviderTypeDAO implements ICrud {
	static Logger log = Logger.getLogger(CourseProviderTypeDAO.class.getName());

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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * getAll() method returns all the course provider records with active status
	 * @return collection of course provider types
	 * @author JH
	 */
	public Collection getAll() throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;		
		Collection<Collection<String>> providerTypeCollection = new ArrayList<Collection<String>>();

		try {

			String courseProviderTypes = "SELECT * FROM [CAMPUS].[COURSEPROVIDERTYPE] WHERE ISACTIVE = ? ";
			
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(courseProviderTypes);
			preparedStatement.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				final Collection<String> singleProviderType = new ArrayList<String>();
				
				singleProviderType.add(resultSet.getString("CODE"));
				singleProviderType.add(resultSet.getString("NAME"));
				singleProviderType.add(resultSet.getString("DESCRIPTION"));
				
				providerTypeCollection.add(singleProviderType);				
			}
			
		} catch (SQLException sqle) {
			log.error("getAll(): SQLException " + sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("getAll(): Exception" + exp.toString());
			throw exp;
		} finally {
			DaoHelper.cleanup(conn, preparedStatement, resultSet);
		}
		
		return providerTypeCollection;
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
