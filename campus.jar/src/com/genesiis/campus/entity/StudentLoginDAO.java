package com.genesiis.campus.entity;

//20161123 AS C19-student-login-without-using-third-party-application-test-as StudentLoginDAO class created.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.command.CmdStudentLogin;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;

public class StudentLoginDAO implements ICrud {

	static Logger log = Logger.getLogger(StudentLoginDAO.class.getName());

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
		Collection<Collection<String>> dataCollection = new ArrayList<Collection<String>>();
		Connection conn = null;
		String stringPassword = null;
		String encryptPassword = null;
		String encryptedPasswordDb = null;
		String decryptedPassword = null;
		
		PreparedStatement preparedStatement = null;
		String message = "Sorry, you are not a registered user! Please sign up first";
		final Student student = (Student) data;
		
		String query = "";
		try {
		log.info(student.getEmail() + ""+ student.getPassword());
		Encryptable passwordEncryptor = new TripleDesEncryptor(student.getPassword());	
		encryptPassword = passwordEncryptor.encryptSensitiveDataToString();
		log.info(encryptPassword);
		conn = ConnectionManager.getConnection();
		preparedStatement = conn.prepareStatement(query);
		//preparedStatement.setString(1, student.getUserName());
					
					
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

	public static Student studentLogin(Student bean) {

		return bean;
	}

}
