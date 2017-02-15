package com.genesiis.campus.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.command.CmdPasswordChange;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;
//20170214 AS C22 password change function for Update method used 
public class SigningUpStudentDAO implements ICrud{
	static Logger log = Logger.getLogger(SigningUpStudentDAO.class.getName());
	@Override
	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object) throws SQLException, Exception {
		Connection conn = null;
		String query = "UPDATE CAMPUS.STUDENT SET PASSWORD = ?   WHERE CODE=? ";
		PreparedStatement ps = null;
		String encryptPassword = null;
		int rowInserted = -1;

		try {
			Student student = (Student) object;
			Encryptable passwordEncryptor = new TripleDesEncryptor(student
					.getPassword().trim());
			encryptPassword = passwordEncryptor.encryptSensitiveDataToString()
					.trim();
			
			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, encryptPassword);
			ps.setInt(2, student.getCode());
			
			rowInserted = ps.executeUpdate();

			if (rowInserted > 0) {
				rowInserted = 1;
			} else {
				rowInserted = 0;
			}
		} catch (SQLException e) {
			log.info("update(Object object): SQLexception" + e.toString());
			throw e;
		} catch (Exception ex) {
			log.info("update(Object object): Exception" + ex.toString());
			throw ex;
		} finally {

			if (ps != null) {
				ps.close();
			}

			if (conn != null) {
				conn.close();
			}

		}
		return rowInserted;
	}

	@Override
	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		// TODO Auto-generated method stub
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
