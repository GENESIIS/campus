package com.genesiis.campus.entity;

//20161025 CM c13-Display course details INIT ProgrammeDAO.java
//20161025 CM c13-Display course details Modified findById() method
//20161026 CM c13-Display course details Modified findById() method
//20161027 CM c13-Display course details Change query according to new DDL

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.genesiis.campus.command.CmdViewProgramme;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;

public class ProgrammeDAO implements ICrud {

	static Logger log = Logger.getLogger(ProgrammeDAO.class.getName());

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
		final Collection<Collection<String>> programmeDetails = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			final Programme programme = (Programme) code;

			conn = ConnectionManager.getConnection();

			String query = "SELECT p.NAME,p.DESCRIPTION,p.DURATION,p.ENTRYREQUIREMENTS,p.COUNSELORNAME,p.COUNSELORPHONE, c.NAME, c.WEBLINK "
					+ "from CAMPUS.PROGRAMME p inner join CAMPUS.COURSEPROVIDER c on p.COURSEPROVIDER=c.CODE where p.CODE=?";
			preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setInt(1, programme.getCode());
			ResultSet rs = preparedStatement.executeQuery();
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			if (rs.next()) {

				final ArrayList<String> singleprogrameDetails = new ArrayList<String>();
				singleprogrameDetails.add(rs.getString(1));// Programme name
				singleprogrameDetails.add(rs.getString(2));// Description
				singleprogrameDetails.add(rs.getString(3));// Duration
				singleprogrameDetails.add(rs.getString(4));// Entry requirements
				singleprogrameDetails.add(rs.getString(5));// Counselor Name
				singleprogrameDetails.add(rs.getString(6));// counselorPhone
				singleprogrameDetails.add(rs.getString(7));// Course provider Name
				singleprogrameDetails.add(rs.getString(8));// Course provider Web link
				
				programmeDetails.add(singleprogrameDetails);

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
		return programmeDetails;
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
