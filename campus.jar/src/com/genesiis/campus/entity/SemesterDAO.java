package com.genesiis.campus.entity;

//20161025 CM c13-Display course details INIT SemesterDAO.java
//20161025 CM c13-Display course details Modified findById() method

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;

public class SemesterDAO implements ICrud{

	static Logger log = Logger.getLogger(SemesterDAO.class.getName());
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
		final Collection<Collection<String>> semesterDetails = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			final Programme programme = (Programme) code;
			
			conn = ConnectionManager.getConnection();

			String query = "SELECT * FROM [CAMPUS].[SEMESTER] WHERE PROGRAMME = ?";
			preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setInt(1, programme.getCode());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleSemeterDetail = new ArrayList<String>();
				final ArrayList<String> singleModule = new ArrayList<String>();
			// default semester value name as "default"
				
				singleSemeterDetail.add(rs.getString("name"));
				singleSemeterDetail.add(rs.getString("yearNo"));
				singleSemeterDetail.add(rs.getString("semesterNo"));
				singleSemeterDetail.add(rs.getString("description"));
				semesterDetails.add(singleSemeterDetail);
				

				
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
		return semesterDetails;
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
