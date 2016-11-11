package com.genesiis.campus.entity;

//20161101 CM c13-Display course details INIT ClassTypeDAO.java
//20161101 CM c13-Display course details Modified findById() method.
//20161110 CM c13-Display-course-details Formatted code 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;

public class ClassTypeDAO implements ICrud {

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
	 * Search class type details and relevant to the programme.
	 * 
	 * @author Chathuri
	 * @param Object
	 *            : programme object of Object type
	 * @return Collection<Collection<String>> of Collection
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		final Collection<Collection<String>> classTypeDetails = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			final Programme programme = (Programme) code;

			conn = ConnectionManager.getConnection();

			String query = "SELECT c.NAME, c.MINPARTICIPANTS,c.MAXPARTICIPANTS,c.DESCRIPTION FROM CAMPUS.PROGRAMME p"
					+ " inner join CAMPUS.CLASSTYPE c on p.CLASSTYPE=c.CODE WHERE p.CODE = ? AND ISACTIVE='1'";
			preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setInt(1, programme.getCode());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleClassTypeDetails = new ArrayList<String>();

				singleClassTypeDetails.add(rs.getString("NAME"));
				singleClassTypeDetails.add(rs.getString("MINPARTICIPANTS"));
				singleClassTypeDetails.add(rs.getString("MAXPARTICIPANTS"));
				singleClassTypeDetails.add(rs.getString("DESCRIPTION"));
				classTypeDetails.add(singleClassTypeDetails);

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
		return classTypeDetails;
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
