package com.genesiis.campus.entity;

//20161230 JH c39-add-course-provider CourseProviderTypeDAO.java created
//20161231 JH c39-add-course-provider getAll() method modified

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;

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

	public Collection getAll() throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;		
		Collection<Collection<String>> providerTypeCollection = new ArrayList<Collection<String>>();

		try {

			String courseProviderTypes = "SELECT * FROM [CAMPUS].[COURSEPROVIDERTYPE] WHERE ISACTIVE = 1 ";
			
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(courseProviderTypes);

			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				final Collection<String> singleProviderType = new ArrayList<String>();
				
				singleProviderType.add(resultSet.getString("CODE"));
				singleProviderType.add(resultSet.getString("NAME"));
				singleProviderType.add(resultSet.getString("DESCRIPTION"));
				
				providerTypeCollection.add(singleProviderType);				
			}
			
		} catch (SQLException sqle) {
			log.error("findById(): SQLException " + sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("findById(): Wxcepption" + exp.toString());
			throw exp;
		} finally {
			if(resultSet != null){
				resultSet.close();
			}if (preparedStatement != null)
				preparedStatement.close();
		}if(conn != null){
			conn.close();
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
