package com.genesiis.campus.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

//20161230 JH c39-add-course-provider CourseProviderTypeDAO.java created

public class CourseProviderTypeDAO implements ICrud{

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
		Connection userTypeConnection = null;
		PreparedStatement prepaire = null;
		ResultSet userCode = null;
		
		try{
			
			String courseProviderTypes = "SELECT * FROM [CAMPUS].[COURSEPROVIDERTYPE] ";			
		}catch (SQLException sqle) {
			log.error("findById(): SQLException " + sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("findById(): Wxcepption" + exp.toString());
			throw exp;
		} finally{
			if (prepaire != null)
				prepaire.close();
		}
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
