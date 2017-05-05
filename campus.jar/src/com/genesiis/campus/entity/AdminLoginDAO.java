package com.genesiis.campus.entity;

//20170314 AS c23-admin-login-logout-function-as - AdminLoginDAO created 
//20170314 AS c23-admin-login-logout-function-as - findById() method coding completed
//20170331 AS c23-admin-login-logout-function-as - loginDataUpdate() method coding WIP
//20170403 AS c23-admin-login-logout-function-as - findById() sql query modification and userTypeString set to the dataColletction 
//20170404 AS c23-admin-login-logout-function-as - loginDataUpdate() sql query modified
//20170424 AS CAM-154-admin-privilege-handling-as - attempts database update and findById() modified to check from the logging attempts, user already blocked or not.
//20170425 AS CAM-154-admin-privilege-handling-as - attempts database update and findById() modified the logic. 
import com.genesiis.campus.entity.model.Admin;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UserType;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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
		String query = "INSERT INTO CAMPUS.LOGINHISTORY (USERAGENT, SESSIONID, LOGGEDINDATE, LOGGEDINTIME, IPADDRESS, AUTHENTICATEDBY, ADMIN, COURSEPROVIDER, TUTOR, ISACTIVE, CRTBY) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
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
			ps.setString(6, adminData.getUserTypeString());
			ps.setInt(7, adminData.getAdminCode());
			ps.setString(8, UserType.FEATURED_COURSE_PROVIDER.getDefaultValue());
			ps.setString(9, UserType.TUTOR.getDefaultValue());
			ps.setInt(10, ApplicationStatus.ACTIVE.getStatusValue());
			ps.setString(11, adminData.getUsername());
			rowInserted = ps.executeUpdate();

			if (rowInserted > 0) {
				rowInserted = 1;
			} else {
				rowInserted = 0;
			}

		} catch (SQLException e) {
			log.error("loginDataUpdate(): SQLexception" + e.toString());
			throw e;
		} catch (Exception ex) {
			log.error("loginDataUpdate(): Exception" + ex.toString());
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
		
		return 0;
	}

	//Incorrect logging attempts, update the DB
	@Override
	public int update(Object object) throws SQLException, Exception {
		Connection conn = null;
		String query = "UPDATE CAMPUS.ADMIN SET ATTEMPTS = ?,  LASTATTEMPTTIME = ?, MODON= ? WHERE CODE = ? ";
		PreparedStatement ps = null;
		int rowInserted = -1;

		try {
			Admin adminData = (Admin) object;
			conn = ConnectionManager.getConnection();

			// modification date
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date modDate = new java.sql.Date(utilDate.getTime());
			// attempt timestamp
			long time = System.currentTimeMillis();
			java.sql.Timestamp attemptTime = new java.sql.Timestamp(time);

			ps = conn.prepareStatement(query);
			ps.setInt(1, adminData.getAttempts());
			ps.setTimestamp(2, attemptTime);
			ps.setDate(3, modDate);
			ps.setInt(4, adminData.getAdminCode());

			rowInserted = ps.executeUpdate();

			if (rowInserted > 0) {
				rowInserted = 1;
			} else {
				rowInserted = 0;
			}

		} catch (SQLException e) {
			log.error("update(): SQLexception" + e.toString());
			throw e;
		} catch (Exception ex) {
			log.error("update(): Exception" + ex.toString());
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
	public Collection<Collection<String>> findById(Object object) throws SQLException, Exception {
		String encryptPassword = null;
		Collection<Collection<String>> dataBundel = new ArrayList<Collection<String>>();
		Collection<String> messageCollection = null;
		ArrayList<String> singleMessageList = null;

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String message = SystemMessage.NOTREGISTERD.message();

		ResultSet rs = null;
		String query = "SELECT ADMIN.CODE, ADMIN.NAME, ADMIN.USERNAME, ADMIN.PASSWORD, ADMIN.EMAIL, ADMIN.ISACTIVE, ADMIN.USERTYPE, USERTYPE.USERTYPESTRING, ADMIN.ATTEMPTS, DATEDIFF(MINUTE , SYSDATETIME() , ADMIN.LASTATTEMPTTIME) AS MinuteDiff FROM CAMPUS.ADMIN INNER JOIN CAMPUS.USERTYPE ON CAMPUS.ADMIN.USERTYPE = CAMPUS.USERTYPE.CODE WHERE USERNAME=? COLLATE Latin1_General_CS_AS AND ISACTIVE = ? OR EMAIL =?  AND ISACTIVE = ?";
		String code = "";
		String name = "";
		String userName = "";
		String password = "";
		String email = "";
		String userType = "";
		String userTypeString = "";
		boolean passwordMatch = false;
		int minitDiff = 0;
		int attempt = 0;
		try {
			final Admin admin = (Admin) object;

			// user input password encryption
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
				// match with user encryption password and db password.
				passwordMatch = encryptPassword.equals(password);

				code = rs.getString("CODE");
				name = rs.getString("NAME");
				userName = rs.getString("USERNAME");
				email = rs.getString("EMAIL");
				userType = rs.getString("USERTYPE");
				userTypeString = rs.getString("USERTYPESTRING");
				password = rs.getString("PASSWORD");
				minitDiff = rs.getInt("MinuteDiff");
				attempt = rs.getInt("ATTEMPTS");



					if ((check && passwordMatch) && (minitDiff >= 30 && attempt == 3) || (check && passwordMatch) && (attempt == 2) || (check && passwordMatch) && ( attempt == 1) || (check && passwordMatch) && ( attempt == 0)) {
						admin.setAdminCode(Integer.parseInt(code));
						admin.setName(name);
						admin.setUsername(userName);
						admin.setEmail(email);
						admin.setUserType(userType);
						admin.setUserTypeString(userTypeString);
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
						admin.setAdminCode(Integer.parseInt(code));
						admin.setValid(false);
						if(attempt == 3){
							message = SystemMessage.LOGGINATTEMPT3.message();
						}
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
