package com.genesiis.campus.entity;

//20161123 AS C19-student-login-without-using-third-party-application-test-as StudentLoginDAO class created.
//20161214 AS C19-student-login-without-using-third-party-application-test-as findbyId method modified .
//20170130 AS CAM-21 code review issues fixing 
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class StudentLoginDAO implements ICrud {
	private String code;
	private String username;
	private String userKey;
	private String password;
	private String indexNo;
	private String firstName;
	private String middleName;
	private String lastName;
	private java.sql.Date dateOfBirth;
	private String gender;
	private String email;
	private String type;
	private String landPhoneCountryCode;
	private String landPhoneAreaCode;
	private String landPhoneNo;
	private String mobilePhoneCountryCode;
	private String mobilePhoneNetworkCode;
	private String mobilePhoneNo;
	private String description;
	private String facebookUrl;
	private String twitterUrl;
	private String mySpaceUrl;
	private String linkedInUrl;
	private String instagramUrl;
	private String viberNumber;
	private String whatsAppNumber;
	private String address1;
	private String address2;
	private String address3;
	private String town;
	private String userType;
	private String accountType;
	private String lastLoggedInUserAgent;
	private String lastLoggedInSessionid;
	private String lastLoggedInDate;
	private String lastLoggedInTime;
	private String lastLoggedInIpAddress;
	private String lastLoggedOutDate;
	private String lastLoggedOutTime;
	private String lastLoginAuthenticatedBy;
	private boolean isActive;

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

	/**
	 * logout data updating
	 * 
	 * @author anuradha
	 * @param student
	 *            object
	 * @return int
	 * @throws SQLException
	 * @throws Exception
	 */
	public static int logoutDataUpdate(Object object) throws SQLException,
			Exception {
		Connection conn = null;
		String query = "UPDATE CAMPUS.STUDENT SET  LASTLOGGEDOUTDATE=?, LASTLOGGEDOUTTIME=?  WHERE CODE=? ";
		PreparedStatement ps = null;

		int rowInserted = -1;
		try {
			Student student = (Student) object;
			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);

			ps.setString(1, student.getLastLoggedOutDate());
			ps.setString(2, student.getLastLoggedOutTime());
			ps.setInt(3, student.getCode());
			rowInserted = ps.executeUpdate();

			if (rowInserted > 0) {
				rowInserted = 1;
			} else {
				rowInserted = 0;
			}
		} catch (SQLException e) {
			log.info("logoutDataUpdate(): SQLexception" + e.toString());
			throw e;
		} catch (Exception ex) {
			log.info("logoutDataUpdate(): Exception" + ex.toString());
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

	/**
	 * login data updating
	 * 
	 * @author anuradha
	 * @param student
	 *            object
	 * @return int
	 * @throws SQLException
	 * @throws Exception
	 */

	public static int loginDataUpdate(Object object) throws SQLException,
			Exception {
		Connection conn = null;
		String query = "UPDATE CAMPUS.STUDENT SET LASTLOGGEDINUSERAGENT= ?, LASTLOGGEDINSESSIONID= ?, LASTLOGGEDINDATE=?, LASTLOGGEDINTIME=?, LASTLOGGEDINIPADDRESS= ?  WHERE CODE=? ";
		PreparedStatement ps = null;
		
		int rowInserted = -1;

		try {
			Student student = (Student) object;
			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, student.getLastLoggedInUserAgent());
			ps.setString(2, student.getLastLoggedInSessionid());
			ps.setString(3, student.getLastLoggedInDate());
			ps.setString(4, student.getLastLoggedInTime());
			ps.setString(5, student.getLastLoggedInIpAddress());
			ps.setInt(6, student.getCode());
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
	public Collection<Collection<String>> findById(Object data)
			throws SQLException, Exception {
		Collection<Collection<String>> dataCollection = new ArrayList<Collection<String>>();
		Connection conn = null;
		String stringPassword = null;
		String encryptPassword = null;
		String encryptedPasswordDb;
		String decryptedPassword = null;
		Collection<Collection<String>> studentList = new ArrayList<Collection<String>>();
		Collection<String> privilegeCollection = new ArrayList<String>();
		Collection<String> messageIview = null;
		ArrayList<String> singleMessageList = null;
		PreparedStatement preparedStatement = null;
		String message = SystemMessage.NOTREGISTERD.message();

		ResultSet rs = null;
		String query = "SELECT CODE, USERNAME, PASSWORD, INDEXNO, FIRSTNAME, MIDDLENAME, LASTNAME, DATEOFBIRTH, GENDER, EMAIL, TYPE, LANDPHONECOUNTRYCODE, LANDPHONEAREACODE, LANDPHONENO, MOBILEPHONECOUNTRYCODE, MOBILEPHONENETWORKCODE, MOBILEPHONENO, DESCRIPTION, FACEBOOKURL, TWITTERURL, MYSPACEURL, LINKEDINURL, INSTAGRAMURL, VIBERNUMBER, WHATSAPPNUMBER, ADDRESS1, ADDRESS2, ADDRESS3, TOWN, USERTYPE, ACCOUNTTYPE, LASTLOGGEDINUSERAGENT, LASTLOGGEDINSESSIONID, LASTLOGGEDINDATE, LASTLOGGEDINTIME, LASTLOGGEDINIPADDRESS, LASTLOGGEDOUTDATE, LASTLOGGEDOUTTIME, LASTLOGINAUTHENTICATEDBY, ISACTIVE FROM CAMPUS.STUDENT  WHERE USERNAME= ? AND ISACTIVE = ? OR EMAIL =? AND ISACTIVE = ? ";
		try {
			final Student student = (Student) data;
			Encryptable passwordEncryptor = new TripleDesEncryptor(student
					.getPassword().trim());
			encryptPassword = passwordEncryptor.encryptSensitiveDataToString()
					.trim();

			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, student.getUsername());
			preparedStatement
			.setString(2, Integer.toString(ApplicationStatus.ACTIVE
					.getStatusValue()));
			preparedStatement.setString(3, student.getEmail());
			preparedStatement
					.setString(4, Integer.toString(ApplicationStatus.ACTIVE
							.getStatusValue()));
			rs = preparedStatement.executeQuery();
			boolean check = rs.next();

			if (check) {

				code = rs.getString("CODE");
				username = rs.getString("USERNAME");
				encryptedPasswordDb = rs.getString("PASSWORD");
				indexNo = rs.getString("INDEXNO");
				firstName = rs.getString("FIRSTNAME");
				middleName = rs.getString("MIDDLENAME");
				lastName = rs.getString("LASTNAME");
				dateOfBirth = rs.getDate("DATEOFBIRTH");
				gender = rs.getString("GENDER");
				email = rs.getString("EMAIL");
				type = rs.getString("TYPE");

				landPhoneCountryCode = rs.getString("LANDPHONECOUNTRYCODE");
				landPhoneAreaCode = rs.getString("LANDPHONEAREACODE");
				landPhoneNo = rs.getString("LANDPHONENO");
				mobilePhoneCountryCode = rs.getString("MOBILEPHONECOUNTRYCODE");
				mobilePhoneNetworkCode = rs.getString("MOBILEPHONENETWORKCODE");
				mobilePhoneNo = rs.getString("MOBILEPHONENO");
				description = rs.getString("DESCRIPTION");
				facebookUrl = rs.getString("FACEBOOKURL");
				twitterUrl = rs.getString("TWITTERURL");
				mySpaceUrl = rs.getString("MYSPACEURL");
				linkedInUrl = rs.getString("LINKEDINURL");
				instagramUrl = rs.getString("INSTAGRAMURL");
				viberNumber = rs.getString("VIBERNUMBER");
				whatsAppNumber = rs.getString("WHATSAPPNUMBER");
				address1 = rs.getString("ADDRESS1");
				address2 = rs.getString("ADDRESS2");
				address3 = rs.getString("ADDRESS3");
				town = rs.getString("TOWN");
				userType = rs.getString("USERTYPE");
				accountType = rs.getString("ACCOUNTTYPE");
				lastLoggedInUserAgent = rs.getString("LASTLOGGEDINUSERAGENT");
				lastLoggedInSessionid = rs.getString("LASTLOGGEDINSESSIONID");
				lastLoggedInDate = rs.getString("LASTLOGGEDINDATE");
				lastLoggedInTime = rs.getString("LASTLOGGEDINTIME");
				lastLoggedInIpAddress = rs.getString("LASTLOGGEDINIPADDRESS");
				lastLoggedOutDate = rs.getString("LASTLOGGEDOUTDATE");
				lastLoggedOutTime = rs.getString("LASTLOGGEDOUTTIME");
				lastLoginAuthenticatedBy = rs
						.getString("LASTLOGINAUTHENTICATEDBY");
				isActive = rs.getBoolean("ISACTIVE");

				student.setCode(Integer.parseInt(code));
				student.setUsername(username);
				student.setIndexNo(indexNo);
				student.setFirstName(firstName);
				student.setMiddleName(middleName);
				student.setLastName(lastName);
				student.setDateOfBirth(dateOfBirth);
				student.setGender(Integer.parseInt(gender));
				student.setEmail(email);
				student.setType(type);
				student.setLandPhoneCountryCode(landPhoneCountryCode);
				student.setLandPhoneAreaCode(landPhoneAreaCode);
				student.setLandPhoneNo(landPhoneNo);
				student.setMobilePhoneCountryCode(mobilePhoneCountryCode);
				student.setMobilePhoneNetworkCode(mobilePhoneNetworkCode);
				student.setMobilePhoneNo(mobilePhoneNo);
				student.setDescription(description);
				student.setFacebookUrl(facebookUrl);
				student.setTwitterUrl(twitterUrl);
				student.setMySpaceUrl(mySpaceUrl);
				student.setLinkedInUrl(linkedInUrl);
				student.setInstagramUrl(instagramUrl);
				student.setViberNumber(viberNumber);
				student.setWhatsAppNumber(whatsAppNumber);
				student.setAddress1(address1);
				student.setAddress2(address2);
				student.setAddress3(address3);
				student.setTown(town);
				student.setUserType(userType);
				student.setAccountType(accountType);
				student.setLastLoggedInUserAgent(lastLoggedInUserAgent);
				student.setLastLoggedInSessionid(lastLoggedInSessionid);
				student.setLastLoggedInDate(lastLoggedInDate);
				student.setLastLoggedInTime(lastLoggedInTime);
				student.setLastLoggedInIpAddress(lastLoggedInIpAddress);
				student.setLastLoggedOutDate(lastLoggedOutDate);
				student.setLastLoggedOutTime(lastLoggedOutTime);
				student.setLastLoginAuthenticatedBy(lastLoginAuthenticatedBy);
				student.setActive(isActive);

				final ArrayList<String> singleStudent = new ArrayList<String>();
				final Collection<String> singleStudentCollection = singleStudent;

				singleStudent.add(code); // 0
				singleStudent.add(username); // 1
				singleStudent.add(indexNo); // 2
				singleStudent.add(firstName); // 3
				singleStudent.add(middleName); // 4
				singleStudent.add(lastName); // 5
				singleStudent.add(dateOfBirth.toString()); // 6
				singleStudent.add(gender); // 7
				singleStudent.add(email); // 8
				singleStudent.add(type); // 9

				singleStudent.add(landPhoneCountryCode); // 10
				singleStudent.add(landPhoneAreaCode); // 11
				singleStudent.add(landPhoneNo); // 12
				singleStudent.add(mobilePhoneCountryCode); // 13
				singleStudent.add(mobilePhoneNetworkCode); // 14
				singleStudent.add(mobilePhoneNo); // 15
				singleStudent.add(description); // 16
				singleStudent.add(facebookUrl); // 17
				singleStudent.add(twitterUrl); // 18
				singleStudent.add(mySpaceUrl); // 19
				singleStudent.add(linkedInUrl); // 20
				singleStudent.add(instagramUrl); // 21
				singleStudent.add(viberNumber); // 22
				singleStudent.add(whatsAppNumber); // 23
				singleStudent.add(address1); // 24
				singleStudent.add(address2); // 25
				singleStudent.add(address3); // 26
				singleStudent.add(town); // 27
				singleStudent.add(userType); // 28
				singleStudent.add(accountType); // 29
				singleStudent.add(lastLoggedInUserAgent); // 30
				singleStudent.add(lastLoggedInSessionid); // 31
				singleStudent.add(lastLoggedInDate); // 32
				singleStudent.add(lastLoggedInTime); // 33
				singleStudent.add(lastLoggedInIpAddress); // 34
				singleStudent.add(lastLoggedOutDate); // 35
				singleStudent.add(lastLoggedOutTime); // 36
				singleStudent.add(lastLoginAuthenticatedBy); // 37
				rs.close();
				if (encryptPassword.equals(encryptedPasswordDb)) {

					student.setValid(true);
					studentList.add(singleStudentCollection);

					StudentPrivilegeDAO sp = new StudentPrivilegeDAO();
					privilegeCollection = sp.studentPrivilege(student);
					studentList.add(privilegeCollection);
					message = SystemMessage.VALIDUSER.message();

				} else {
					student.setValid(false);
					message = SystemMessage.INVALIDPASSWORD.message();
					int MAX_ATTEMPTS;
					// for (MAX_ATTEMPTS =0; MAX_ATTEMPTS < 3; ){
					// log.info("max attempts : "+MAX_ATTEMPTS);
					// }
					// MAX_ATTEMPTS ++;

				}

			} else {
				message = SystemMessage.INVALIDUSERNAME.message();
				student.setValid(false);
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

		messageIview = (Collection<String>) singleMessageList;

		studentList.add(messageIview);
		return studentList;
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
