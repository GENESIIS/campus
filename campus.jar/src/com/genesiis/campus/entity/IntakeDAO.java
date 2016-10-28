package com.genesiis.campus.entity;

//20161025 CM c13-Display course details INIT IntakeDAO.java
//20161025 CM c13-Display course details Modified findById() method
//20161027 CM c13-Display course details Change query according to new DDL
//20161028 CM c13-Display course details  Created  method comment.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;



public class IntakeDAO implements ICrud {

	static Logger log = Logger.getLogger(IntakeDAO.class.getName());
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

	/**
	 * Search Intake  details and relevant to the programme
	 * 
	 * @author Chathuri
	 * @param Object
	 *            :  programme object of Object type
	 * @return Collection<Collection<String>> of Collection
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
	
			final Collection<Collection<String>> intakeDetails = new ArrayList<Collection<String>>();
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			try {
				final Programme programme = (Programme) code;
				
				conn = ConnectionManager.getConnection();

				String query = "SELECT * FROM [CAMPUS].[INTAKE] WHERE PROGRAMME = ?";
				preparedStatement = conn.prepareStatement(query.toString());
				preparedStatement.setInt(1, programme.getCode());
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					final ArrayList<String> singleIntakeDetails = new ArrayList<String>();

					singleIntakeDetails.add(rs.getString("name"));
					singleIntakeDetails.add(rs.getString("description"));
					singleIntakeDetails.add(rs.getString("openingDate"));
					singleIntakeDetails.add(rs.getString("closingDate"));
					singleIntakeDetails.add(rs.getString("commencementDate"));
					singleIntakeDetails.add(rs.getString("fee"));
					intakeDetails.add(singleIntakeDetails);
					

					
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
			return intakeDetails;
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
