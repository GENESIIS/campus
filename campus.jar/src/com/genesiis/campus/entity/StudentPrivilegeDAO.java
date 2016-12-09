package com.genesiis.campus.entity;

//20161209 AS C19-student-login-without-using-third-party-application-test-as  StudentPrivilegeDAO created.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;

public class StudentPrivilegeDAO implements ICrud {
	static java.util.logging.Logger log = Logger
			.getLogger(StudentPrivilegeDAO.class.getName());

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
		Collection<Collection<String>> privilegeCollection = new ArrayList<Collection<String>>();
		Connection conn = null;
		Collection<Collection<String>> privilegeList = new ArrayList<Collection<String>>();
		PreparedStatement preparedStatement = null;
		String query = "SELECT ";

		try {

		} catch (Exception e) {
			log.info("findById Exception : " + e);
			throw e;
		}
		return null;
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
