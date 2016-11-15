package com.genesiis.campus.entity;

//20161027 CM c13-Display-course-details INIT ModuleDAO.java
//20161027 CM c13-Display-course-details Modified findById() method.
//20161028 CM c13-Display-course-details Modified execute() method. Created  method comment.
//20161102 CM c13-Display-course-details Modified execute() method. 
//20161115 CM c13-Display-course-details Removed toString() method calling in string query variable.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;

public class ModuleDAO implements ICrud {

	static Logger log = Logger.getLogger(ModuleDAO.class.getName());

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
	 * Search Module details and relevant to the programme and the semesters
	 * 
	 * @author Chathuri
	 * @param Object
	 *            : programme object of Object type
	 * @return Collection<Collection<String>> of Collection
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		final Collection<Collection<String>> programmeDetails = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			final Programme programme = (Programme) code;

			conn = ConnectionManager.getConnection();

			String query = "Select s.NAME,s.CODE, m.NAME,m.DESCRIPTION,m.INTERNALCODEOFMODULE,m.CREDITVALUE, m.TUTOREDBY,t.FIRSTNAME, t.LASTNAME,m.ISTUTORRELATED "
					+ "from CAMPUS.PROGRAMME p inner join CAMPUS.SEMESTER s on s.programme = p.code "
					+ "inner join CAMPUS.MODULE m on m.semester = s.code inner join CAMPUS.TUTOR t on t.CODE=m.TUTOR where p.CODE=? and m.ISACTIVE='1'";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, programme.getCode());
			ResultSet rs = preparedStatement.executeQuery();
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			while (rs.next()) {

				final ArrayList<String> singleModuleDetails = new ArrayList<String>();
				singleModuleDetails.add(rs.getString(1));// Semester Name
				singleModuleDetails.add(rs.getString(2));// Code
				singleModuleDetails.add(rs.getString(3));// Module Name
				singleModuleDetails.add(rs.getString(4));// Description
				singleModuleDetails.add(rs.getString(5));// Internal code of
															// module
				singleModuleDetails.add(rs.getString(6));// credit value

				int isTutorRelated = Integer.parseInt(rs.getString(10));// IsTutorRelated
				if (isTutorRelated == 1) {
					singleModuleDetails.add(rs.getString(8) + " "
							+ rs.getString(9));// Tutored First name + Last Name

				} else {

					singleModuleDetails.add(rs.getString(7));// Tutored by
				}

				programmeDetails.add(singleModuleDetails);

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
