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

public class AdminLoginDAO implements ICrud {
	static Logger log = Logger.getLogger(AdminLoginDAO.class.getName());
	private static int MAX_ATTEMPTS =0;

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
	public Collection<Collection<String>> findById(Object object)
			throws SQLException, Exception {
		String encryptPassword = null;
		Collection<Collection<String>> dataBundel = new ArrayList<Collection<String>>();
		Collection<String> messageCollection = null;
		ArrayList<String> singleMessageList = null;
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String message = SystemMessage.NOTREGISTERD.message();

		ResultSet rs = null;
		String query = "SELECT CODE, NAME, USERNAME, PASSWORD, EMAIL, ISACTIVE, USERTYPE FROM CAMPUS.ADMIN  WHERE USERNAME=? AND ISACTIVE = ? OR EMAIL =? AND ISACTIVE = ? ";
		String code = "";
		String name = "";
		String userName = "";
		String password = "";
		String email = "";
		String userType = "";
		boolean passwordMatch = false;
		try {
			final Admin admin = (Admin) object;
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

			if (check) {
				password = rs.getString("PASSWORD");
				passwordMatch = encryptPassword.equals(password);
				log.info(password + "  " + passwordMatch);

				code = rs.getString("CODE");
				name = rs.getString("NAME");
				userName = rs.getString("USERNAME");
				email = rs.getString("EMAIL");
				userType = rs.getString("USERTYPE");
				password = rs.getString("PASSWORD");
			 if (check && passwordMatch) {
				admin.setAdminCode(Integer.parseInt(code));
				admin.setName(name);
				admin.setUsername(userName);
				admin.setEmail(email);
				admin.setUserType(userType);
				admin.setValid(true);
				log.info(name + "  " + email);

				final ArrayList<String> singleAdmin = new ArrayList<String>();
				final Collection<String> adminDatabundel = singleAdmin;

				singleAdmin.add(code);
				singleAdmin.add(name);
				singleAdmin.add(userName);
				singleAdmin.add(email);
				singleAdmin.add(userType);

				dataBundel.add(adminDatabundel);
				message = SystemMessage.VALIDUSER.message();
			 } else {
				 message = SystemMessage.INVALIDPASSWORD.message();
				 admin.setValid(false);
					

					for (MAX_ATTEMPTS=MAX_ATTEMPTS; MAX_ATTEMPTS < 3; MAX_ATTEMPTS++) {
						log.info("max attempts : " + MAX_ATTEMPTS);
						if (MAX_ATTEMPTS == 3) {
							message = SystemMessage.LOGGINATTEMPT3.message();	
							log.info(message);
						} if(MAX_ATTEMPTS==2){
							
						}else {
							break;
						}
					}
					// MAX_ATTEMPTS++;
					 log.info(message);
			 }
				
			} else {
				message = SystemMessage.INVALIDUSERNAME.message();
				admin.setValid(false);
				
				for (MAX_ATTEMPTS=MAX_ATTEMPTS; MAX_ATTEMPTS < 3; MAX_ATTEMPTS++) {
					log.info("max attempts : " + MAX_ATTEMPTS);
					if (MAX_ATTEMPTS == 3) {
						message = SystemMessage.LOGGINATTEMPT3.message();
						log.info(message);
					} else {
						break;
					}
				}
				//MAX_ATTEMPTS++;
				log.info(message);
			}

		} catch (SQLException exception) {
			log.error("findById(Object code):  SQLexception"
					+ exception.toString());
			throw exception;
		} catch (Exception exception) {
			log.error("findById(Object code):  exception"
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

		singleMessageList = new ArrayList<String>();
		singleMessageList.add(message);

		messageCollection = (Collection<String>) singleMessageList;

		dataBundel.add(messageCollection);
		
		return dataBundel;
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
