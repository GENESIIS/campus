package com.genesiis.campus.entity;

//20161209 AS C19-student-login-without-using-third-party-application-test-as  StudentPrivilegeDAO created.
//20161214 AS C19-student-login-without-using-third-party-application-test-as added studentPrivilege method . 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;

public class StudentPrivilegeDAO implements ICrud {
	static Logger log = Logger.getLogger(StudentPrivilegeDAO.class.getName());

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
	 * Select all Interfaces and button actions student have.
	 * 
	 * @param student
	 *            object
	 * @return studentPrivilegeCollection
	 * @throws SQLException
	 * @throws Exception
	 */

	public Collection<String> studentPrivilege(Object code)
			throws SQLException, Exception {

		Collection<String> studentPrivilegeCollection = new ArrayList<String>();
		Connection conn = null;
		
		ArrayList<String> privilegeList = null;
		PreparedStatement preparedStatement = null;
		String query = "SELECT USERTYPE.NAME, USERTYPE.USERTYPESTRING, USERTYPE.DESCRIPTION, INTERFACE.TITLE, INTERFACE.DESCRIPTION, INTERFACE.URL , BUTTONACTION.ACTION, BUTTONACTION.DESCRIPTION FROM CAMPUS.STUDENT INNER JOIN CAMPUS.USERTYPE ON STUDENT.USERTYPE = USERTYPE.CODE INNER JOIN CAMPUS.PRIVILEGE ON PRIVILEGE.USERTYPE = USERTYPE.CODE INNER JOIN CAMPUS.INTERFACE ON PRIVILEGE.INTERFACE = INTERFACE.CODE INNER JOIN CAMPUS.BUTTONACTION ON BUTTONACTION.INTERFACE = INTERFACE.CODE WHERE USERTYPE.CODE = ?";
		ResultSet rs = null;
		try {
			final Student student = (Student) code;
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, student.getUserType());

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				privilegeList = new ArrayList<String>();
				privilegeList.add(rs.getString(1));
				privilegeList.add(rs.getString(2));
				privilegeList.add(rs.getString(3));
				privilegeList.add(rs.getString(4));
				privilegeList.add(rs.getString(5));
				privilegeList.add(rs.getString(6));
				privilegeList.add(rs.getString(7));
				privilegeList.add(rs.getString(8));

				studentPrivilegeCollection = privilegeList;

			}

		} catch (SQLException e) {
			log.info("findById SQLException : " + e);
			throw e;

		} catch (Exception e) {
			log.info("findById Exception : " + e);
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		return studentPrivilegeCollection;
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

	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
