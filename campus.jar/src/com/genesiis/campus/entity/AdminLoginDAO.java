package com.genesiis.campus.entity;

//20170314 AS c23-admin-login-logout-function-as - AdminLoginDAO created 
//20170314 AS c23-admin-login-logout-function-as - findById() method coding completed
//20170331 AS c23-admin-login-logout-function-as - loginDataUpdate() method coding WIP

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

	/**
	 * login data updating
	 * 
	 * @author anuradha
	 * @param admin object
	 * @return int
	 * @throws SQLException
	 * @throws Exception
	 */

	public static int loginDataUpdate(Object object) throws SQLException, Exception {
		Connection conn = null;
		String query = "UPDATE CAMPUS.LOGINHISTORY SET USERAGENT=?, SESSIONID=?, LOGGEDINDATE=?, LOGGEDINTIME=?, IPADDRESS=? WHERE ADMIN=? ";
		PreparedStatement ps = null;
		int rowInserted = -1;

		try {
			Admin adminData = (Admin) object;
			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, adminData.getLastLoggedInUserAgent());
			ps.setString(2, adminData.getLastLoggedInSessionid());
			ps.setString(3, adminData.getLastLoggedInDate());
			ps.setString(4, adminData.getLastLoggedInTime());
			ps.setString(5, adminData.getLastLoggedInIpAddress());
			ps.setInt(6, adminData.getAdminCode());
			//ps.setInt(7, ApplicationStatus.ACTIVE.getStatusValue());
			rowInserted = ps.executeUpdate();

			if (rowInserted > 0) {
				rowInserted = 1;
			} else {
				rowInserted = 0;
			}

		} catch (SQLException e) {
			log.info("loginDataUpdate(): SQLexception" + e.toString());
			throw e;
		} catch (Exception ex) {
			log.info("loginDataUpdate(): Exception" + ex.toString());
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
	public Collection<Collection<String>> findById(Object object) throws SQLException, Exception {
		String encryptPassword = null;
		Collection<Collection<String>> dataBundel = new ArrayList<Collection<String>>();
		Collection<String> messageCollection = null;
		ArrayList<String> singleMessageList = null;

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String message = SystemMessage.NOTREGISTERD.message();

		ResultSet rs = null;
		String query = "SELECT ADMIN.CODE, ADMIN.NAME, ADMIN.USERNAME, ADMIN.PASSWORD, ADMIN.EMAIL, ADMIN.ISACTIVE, ADMIN.USERTYPE, USERTYPE.USERTYPESTRING FROM CAMPUS.ADMIN INNER JOIN CAMPUS.USERTYPE ON CAMPUS.ADMIN.USERTYPE = CAMPUS.USERTYPE.CODE WHERE USERNAME=? COLLATE Latin1_General_CS_AS AND ISACTIVE = ? OR EMAIL =?  AND ISACTIVE = ?";
		String code = "";
		String name = "";
		String userName = "";
		String password = "";
		String email = "";
		String userType = "";
		String userTypeString = "";
		boolean passwordMatch = false;
		try {
			final Admin admin = (Admin) object;
			Encryptable passwordEncryptor = new TripleDesEncryptor(admin.getPassword().trim());
			encryptPassword = passwordEncryptor.encryptSensitiveDataToString().trim();

			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, admin.getUsername());
			preparedStatement.setString(2, Integer.toString(ApplicationStatus.ACTIVE.getStatusValue()));
			preparedStatement.setString(3, admin.getEmail());
			preparedStatement.setString(4, Integer.toString(ApplicationStatus.ACTIVE.getStatusValue()));
			rs = preparedStatement.executeQuery();
			boolean check = rs.next();

			if (check) {
				password = rs.getString("PASSWORD");
				passwordMatch = encryptPassword.equals(password);

				code = rs.getString("CODE");
				name = rs.getString("NAME");
				userName = rs.getString("USERNAME");
				email = rs.getString("EMAIL");
				userType = rs.getString("USERTYPE");
				userTypeString = rs.getString("USERTYPESTRING");
				password = rs.getString("PASSWORD");

				if (check && passwordMatch) {
					admin.setAdminCode(Integer.parseInt(code));
					admin.setName(name);
					admin.setUsername(userName);
					admin.setEmail(email);
					admin.setUserType(userType);
					admin.setValid(true);

					final ArrayList<String> singleAdmin = new ArrayList<String>();
					final Collection<String> adminDatabundel = singleAdmin;

					singleAdmin.add(code);
					singleAdmin.add(name);
					singleAdmin.add(userName);
					singleAdmin.add(email);
					singleAdmin.add(userType);
					singleAdmin.add(userTypeString);

					dataBundel.add(adminDatabundel);
					message = SystemMessage.VALIDUSER.message();

				} else {
					message = SystemMessage.INVALIDPASSWORD.message();
					admin.setValid(false);
				}

			} else {
				message = SystemMessage.INVALIDUSERNAME.message();
				admin.setValid(false);

			}
		} catch (SQLException exception) {
			log.error("findById(Object code):  SQLexception" + exception.toString());
			throw exception;
		} catch (Exception exception) {
			log.error("findById(Object code):  exception" + exception.toString());
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
		log.info(message);
		singleMessageList = new ArrayList<String>();
		singleMessageList.add(message);

		messageCollection = (Collection<String>) singleMessageList;
		dataBundel.add(messageCollection);

		return dataBundel;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
