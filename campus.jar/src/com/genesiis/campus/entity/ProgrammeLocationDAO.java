package com.genesiis.campus.entity;

//20161104 CM c13-Display course details INIT ProgrammeLocationDAO.java
//20161104 CM c13-Display course details Modified findById() method

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;

public class ProgrammeLocationDAO implements ICrud {

	static Logger log = Logger.getLogger(ProgrammeLocationDAO.class.getName());
	
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
	 * Search Programme location details.
	 * @author Chathuri
	 * @param Object
	 *            : programme object of Object type
	 * @return Collection<Collection<String>> of Collection
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		final Collection<Collection<String>> locationDetails = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			final Programme programme = (Programme) code;
			
			conn = ConnectionManager.getConnection();

			String query = "SELECT p.NAME, t.NAME , d.NAME FROM CAMPUS.PROGRAMME p inner join CAMPUS .PROGRAMMETOWN g "
					+ "on p.CODE=g.PROGRAMME inner join CAMPUS.TOWN t on t.CODE=g.town inner join CAMPUS.DISTRICT d on t.DISTRICT=d.CODE where p.CODE=? and g.ISACTIVE=1";
			preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setInt(1, programme.getCode());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleLocationDetails = new ArrayList<String>();

				singleLocationDetails.add(rs.getString(2));
				singleLocationDetails.add(rs.getString(3));
				locationDetails.add(singleLocationDetails);
				
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
		return locationDetails;
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
