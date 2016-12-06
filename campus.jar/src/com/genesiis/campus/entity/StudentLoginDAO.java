package com.genesiis.campus.entity;

//20161123 AS C19-student-login-without-using-third-party-application-test-as StudentLoginDAO class created.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.command.CmdStudentLogin;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;
import com.genesiis.campus.validation.SystemMessage;


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
	private String imagePath;
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
	private String accountType;
	private String lastLoggedInUserAgent;
	private String lastLoggedInSessionid;
	private String lastLoggedInDate;
	private String lastLoggedInTime;
	private String lastLoggedInIpAddress;
	private String lastLoggedOutDate;
	private String lastLoggedOutTime;
	private String lastLoginAuthenticatedBy;
	boolean isActive;
	static  Logger log = Logger.getLogger(StudentLoginDAO.class.getName());

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
		String encryptedPasswordDb;
		String decryptedPassword = null;
		Collection<Collection<String>> studentList = null;
		PreparedStatement preparedStatement = null;
		String message = "Sorry, you are not a registered user! Please sign up first";
		final Student student = (Student) data;

		String query = "SELECT CODE, USERNAME, PASSWORD, INDEXNO, FIRSTNAME, MIDDLENAME, LASTNAME, DATEOFBIRTH, GENDER, EMAIL, TYPE, IMAGEPATH, LANDPHONECOUNTRYCODE, LANDPHONEAREACODE, LANDPHONENO, MOBILEPHONECOUNTRYCODE, MOBILEPHONENETWORKCODE, MOBILEPHONENO, DESCRIPTION, FACEBOOKURL, TWITTERURL, MYSPACEURL, LINKEDINURL, INSTAGRAMURL, VIBERNUMBER, WHATSAPPNUMBER, ADDRESS1, ADDRESS2, ADDRESS3, TOWN, ACCOUNTTYPE, LASTLOGGEDINUSERAGENT, LASTLOGGEDINSESSIONID, LASTLOGGEDINDATE, LASTLOGGEDINTIME, LASTLOGGEDINIPADDRESS, LASTLOGGEDOUTDATE, LASTLOGGEDOUTTIME, LASTLOGINAUTHENTICATEDBY, ISACTIVE FROM CAMPUS.STUDENT  WHERE USERNAME= ? OR EMAIL =? AND ISACTIVE = 1 ";
		try {
			log.info(student.getEmail() + "" + student.getPassword());
			Encryptable passwordEncryptor = new TripleDesEncryptor(student.getPassword().trim());
			encryptPassword = passwordEncryptor.encryptSensitiveDataToString().trim();
			log.info(encryptPassword);

			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, student.getUsername());
			preparedStatement.setString(2, student.getEmail());

			ResultSet rs = preparedStatement.executeQuery();
			boolean check = rs.next();

			if (!check) {
				message = "not valid user ";
				student.setValid(false);
			} else if (check) {
				
				log.info("ohhh yeah......");
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
				imagePath = rs.getString("IMAGEPATH");
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
				student.setImagePath(imagePath);
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

				singleStudent.add(code);
				singleStudent.add(username); // 0
				singleStudent.add(rs.getString("USERNAME")); // 1
			//	encryptedPasswordDb = rs.getString("PASSWORD").trim();
			//	singleStudent.add(encryptedPasswordDb); // 2
				singleStudent.add(indexNo); // 3
				singleStudent.add(firstName); // 4
				singleStudent.add(middleName); // 5
				singleStudent.add(lastName); // 6
				singleStudent.add(dateOfBirth.toString()); // 7
				singleStudent.add(gender); // 8
				singleStudent.add(email); // 9
				singleStudent.add(type); // 10
				singleStudent.add(imagePath); // 11
				singleStudent.add(landPhoneCountryCode); // 12
				singleStudent.add(landPhoneAreaCode); // 13
				singleStudent.add(landPhoneNo); // 14
				singleStudent.add(mobilePhoneCountryCode); // 15
				singleStudent.add(mobilePhoneNetworkCode); // 16
				singleStudent.add(mobilePhoneNo); // 17
				singleStudent.add(description); // 18
				singleStudent.add(facebookUrl); // 19
				singleStudent.add(twitterUrl); // 20
				singleStudent.add(mySpaceUrl); // 21
				singleStudent.add(linkedInUrl); // 22
				singleStudent.add(instagramUrl); // 23
				singleStudent.add(viberNumber); // 24
				singleStudent.add(whatsAppNumber); // 24
				singleStudent.add(address1); // 24
				singleStudent.add(address2); // 24
				singleStudent.add(address3); // 24
				singleStudent.add(town); // 24
				singleStudent.add(accountType); // 24
				singleStudent.add(lastLoggedInUserAgent); // 24
				singleStudent.add(lastLoggedInSessionid); // 24
				singleStudent.add(lastLoggedInDate); // 24
				singleStudent.add(lastLoggedInTime); // 24
				singleStudent.add(lastLoggedInIpAddress); // 24
				singleStudent.add(lastLoggedOutDate); // 24
				singleStudent.add(lastLoggedOutTime); // 24
				singleStudent.add(lastLoginAuthenticatedBy); // 24

		 
				final Collection<String> singleStudentCollection = singleStudent;
				studentList.add(singleStudentCollection);
				
				if(encryptPassword.equals(encryptedPasswordDb)){
					log.info("password match  :D");
					student.setValid(true);
				
				
					
				}else{
					student.setValid(false);
					message = "not valid user ";
					log.info("password not match :(");
				}
				log.info(encryptedPasswordDb);
				log.info(encryptPassword);
				log.info(firstName + lastName);
			}

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
	
	//public privilageHandling(){}

}
