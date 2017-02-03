package com.genesiis.campus.entity;
//20170202 AS C22 StudentEmailVerificationDAO class created
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.command.CmdEmailVarification;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemMessage;

public class StudentEmailVerificationDAO implements ICrud {
	static Logger log = Logger.getLogger(StudentEmailVerificationDAO.class
			.getName());

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
	public Collection<Collection<String>> findById(Object data)
			throws SQLException, Exception {

		Collection<Collection<String>> emailCollection = new ArrayList<Collection<String>>();

		ArrayList<String> singleStudent = null;
		Collection<String> singleStudentCollection = null;

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query = "SELECT USERNAME, FIRSTNAME, LASTNAME, EMAIL FROM CAMPUS.STUDENT WHERE EMAIL =? AND ISACTIVE = ? ";
		String message = SystemMessage.INVALID_EMAIL.message();
		try {
			final Student student = (Student) data;
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, student.getEmail());
			preparedStatement.setString(2, Integer.toString(ApplicationStatus.ACTIVE.getStatusValue()));

			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				singleStudent = new ArrayList<String>();
				singleStudentCollection = singleStudent;

				singleStudent.add(rs.getString("FIRSTNAME"));
				singleStudent.add(rs.getString("LASTNAME"));
				singleStudent.add(rs.getString("EMAIL"));
				singleStudent.add(rs.getString("USERNAME"));
			} else {
				message = SystemMessage.INVALID_EMAIL.message();
				singleStudent = new ArrayList<String>();
				singleStudentCollection = singleStudent;
				singleStudent.add(message);

			}
		} catch (SQLException exception) {
			log.error("findById(Object data):  SQLexception"
					+ exception.toString());
			throw exception;
		} catch (Exception exception) {
			log.error("findById(Object data):  exception"
					+ exception.toString());
			throw exception;
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

		emailCollection.add(singleStudentCollection);
		return emailCollection;
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
