package com.genesiis.campus.entity;

//20161123 AS C19-student-login-without-using-third-party-application-test-as StudentLoginDAO class created.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.log4j.Logger;

import com.genesiis.campus.command.CmdStudentLogin;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;

public class StudentLoginDAO implements ICrud {
	private int code;
	private String username;
	private String userKey;
	private String password;
	private String indexNo;
	private String firstName;
	private String middleName;
	private String lastName;
	private java.sql.Date dateOfBirth;
	private int gender;
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
		String encryptedPasswordDb;
		String decryptedPassword = null;

		PreparedStatement preparedStatement = null;
		String message = "Sorry, you are not a registered user! Please sign up first";
		final Student student = (Student) data;

		String query = "SELECT CODE, USERNAME, PASSWORD, INDEXNO, FIRSTNAME, MIDDLENAME, LASTNAME, DATEOFBIRTH, GENDER, EMAIL, TYPE, IMAGEPATH, LANDPHONECOUNTRYCODE, LANDPHONEAREACODE, LANDPHONENO, MOBILEPHONECOUNTRYCODE, MOBILEPHONENETWORKCODE, MOBILEPHONENO, DESCRIPTION, FACEBOOKURL, TWITTERURL, MYSPACEURL, LINKEDINURL, INSTAGRAMURL, VIBERNUMBER, WHATSAPPNUMBER, ADDRESS1, ADDRESS2, ADDRESS3, TOWN, ACCOUNTTYPE, LASTLOGGEDINUSERAGENT, LASTLOGGEDINSESSIONID, LASTLOGGEDINDATE, LASTLOGGEDINTIME, LASTLOGGEDINIPADDRESS, LASTLOGGEDOUTDATE, LASTLOGGEDOUTTIME, LASTLOGINAUTHENTICATEDBY FROM CAMPUS.STUDENT  WHERE USERNAME= ? OR EMAIL =? AND STUDENT.ISACTIVE = 1 ";
		try {
			log.info(student.getEmail() + "" + student.getPassword());
			Encryptable passwordEncryptor = new TripleDesEncryptor(student
					.getPassword().trim());
			encryptPassword = passwordEncryptor.encryptSensitiveDataToString();
			log.info(encryptPassword);

			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, student.getUsername());
			preparedStatement.setString(2, student.getEmail());

			ResultSet rs = preparedStatement.executeQuery();
			boolean check = rs.next();

			if (!check) {
				message = "not valid user ";
			} else if (check) {
				code = rs.getInt("CODE");
				username = rs.getString("USERNAME");
				
				
				student.setUsername(rs.getString(2));
				student.setIndexNo(rs.getString(4));
				student.setFirstName(rs.getString(5));
				
				
				final ArrayList<String> singleStudent = new ArrayList<String>();
				
				singleStudent.add(rs.getString("CODE")); // 0
				singleStudent.add(rs.getString("USERNAME")); // 1
				encryptedPasswordDb = rs.getString("PASSWORD").trim();
				singleStudent.add(encryptedPasswordDb); // 2
				singleStudent.add(rs.getString("INDEXNO")); // 3
				singleStudent.add(rs.getString("FIRSTNAME")); // 4
				singleStudent.add(rs.getString("MIDDLENAME")); // 5
				singleStudent.add(rs.getString("LASTNAME")); // 6
				singleStudent.add(rs.getString("DATEOFBIRTH")); // 7
				singleStudent.add(rs.getString("GENDER")); // 8
				singleStudent.add(rs.getString("EMAIL")); // 9
				singleStudent.add(rs.getString("TYPE")); // 10
				singleStudent.add(rs.getString("IMAGEPATH")); // 11
				singleStudent.add(rs.getString("LANDPHONECOUNTRYCODE")); // 12
				singleStudent.add(rs.getString("LANDPHONEAREACODE")); // 13
				singleStudent.add(rs.getString("LANDPHONENO")); // 14
				singleStudent.add(rs.getString("MOBILEPHONECOUNTRYCODE")); // 15
				singleStudent.add(rs.getString("MOBILEPHONEAREACODE")); // 16
				singleStudent.add(rs.getString("MOBILEPHONENO")); // 17
				singleStudent.add(rs.getString("DESCRIPTION")); // 18
				singleStudent.add(rs.getString("FACEBOOKURL")); // 19
				singleStudent.add(rs.getString("TWITTERURL")); // 20
				singleStudent.add(rs.getString("MYSPACEURL")); // 21
				singleStudent.add(rs.getString("LINKEDINURL")); // 22
				singleStudent.add(rs.getString("INSTAGRAMURL")); // 23
				singleStudent.add(rs.getString("VIBERNUMBER")); // 24
				singleStudent.add(rs.getString("WHATSAPPNUMBER")); // 24
				singleStudent.add(rs.getString("ADDRESS1")); // 24
				singleStudent.add(rs.getString("ADDRESS2")); // 24
				singleStudent.add(rs.getString("ADDRESS3")); // 24
				singleStudent.add(rs.getString("TOWN")); // 24
				singleStudent.add(rs.getString("ACCOUNTTYPE")); // 24
				singleStudent.add(rs.getString("LASTLOGGEDINUSERAGENT")); // 24
				singleStudent.add(rs.getString("LASTLOGGEDINSESSIONID")); // 24
				singleStudent.add(rs.getString("LASTLOGGEDINDATE")); // 24
				singleStudent.add(rs.getString("LASTLOGGEDINTIME")); // 24
				singleStudent.add(rs.getString("LASTLOGGEDINIPADDRESS")); // 24
				singleStudent.add(rs.getString("LASTLOGGEDOUTDATE")); // 24
				singleStudent.add(rs.getString("LASTLOGGEDOUTTIME")); // 24
				singleStudent.add(rs.getString("LASTLOGINAUTHENTICATEDBY")); // 24
				singleStudent.add(rs.getString("ISACTIVE")); // 24
				
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

}
