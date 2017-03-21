package com.genesiis.campus.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Admin;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemMessage;


public class AdminLoginDAO implements ICrud{
	static Logger log = Logger.getLogger(AdminLoginDAO.class.getName());
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
		String encryptPassword = null;
		Collection<Collection<String>> dataCollection = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String message = SystemMessage.NOTREGISTERD.message();

		ResultSet rs = null;
		String query = "SELECT CODE, USERNAME, PASSWORD, EMAIL, ISACTIVE, USERTYPE FROM CAMPUS.ADMIN  WHERE USERNAME=? AND ISACTIVE = ? OR EMAIL =? AND ISACTIVE = ? "; 
		
		try {
		final Admin admin = (Admin) code;
			Encryptable passwordEncryptor = new TripleDesEncryptor(admin
					.getPassword().trim());
			encryptPassword = passwordEncryptor.encryptSensitiveDataToString()
					.trim();

			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, admin.getUsername());
			preparedStatement
			.setString(2, Integer.toString(ApplicationStatus.ACTIVE
					.getStatusValue()));
			preparedStatement.setString(3, admin.getEmail());
			preparedStatement
					.setString(4, Integer.toString(ApplicationStatus.ACTIVE
							.getStatusValue()));
			rs = preparedStatement.executeQuery();
			boolean check = rs.next();
			
		}catch (SQLException exception) {
				log.error("findById(Object code):  SQLexception"
						+ exception.toString());
				throw exception;
			} catch (Exception exception) {
				log.error("findById(Object code):  exception"
						+ exception.toString());
				throw exception;
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
