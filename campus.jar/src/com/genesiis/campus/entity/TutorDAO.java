package com.genesiis.campus.entity;

//20161121 CM c36-add-tutor-information INIT TutorDAO.java
//20161121 CM c36-add-tutor-information Modified add()method. 
//20161220 CW c36-add-tutor-information Modified findById()method.
//20161221 CW c36-add-tutor-details Removed findById() method.
//20161221 CW c36-add-tutor-details Modified add() method & added Password Encryption. 
//20161222 CW c38-view-update-tutor-profile added country name & Town Name detail calling to findById. 
//20161223 CW c36-add-tutor-details Modified add() method & added StringBuilder.
//20161227 CW c38-view-update-tutor-profile modified update() method
//20161229 CW c38-view-update-tutor-profile modified update() method
//20170106 CW c36-add-tutor-details Added isAvailableUserName() method 
//20170110 CW c36-add-tutor-details Modified add() method - add tutor crtBy & modBy using getter methods 
//20170111 CW c36-add-tutor-details removed isAvailableUserName() method 
//20170124 CW c36-add-tutor-details modified getAll() method according to the 201701201215 DJ crev modification request.
//20170125 CW c36-add-tutor-details add validateUsernameEmailFields() method.
//20170130 CW c36-add-tutor-details modified validateUsernameEmailFields() method
//20170130 CW c36-add-tutor-information re-organise the import statements.
//20170207 CW c38-view-update-tutor-profile removed add() method from update coding 
//20170208 CW c38-view-update-tutor-profile modified findById()
//20170208 CW c38-view-update-tutor-profile removed some testing codes in findById() method
//20170209 CW c38-view-update-tutor-profile modified findById method & removed multiple database calls
//20170214 CW c38-view-update-tutor-profile modified update() method
//20170216 CW c38-view-update-tutor-profile Add class comment modified findById(), update() methods.
//20170223 CW c36-add-tutor-information re-organise the import statements.
//20170227 CW c37-tutor-update-tutor-profile-cw add Password & confirm Password from old CAM-38
//20170227 CW c37-tutor-update-tutor-profile-cw removed un wanted commented lines
//20170227 CW c37-tutor-update-tutor-profile-cw modified update method to create the query dynamically & update password if available
//20170228 CW c37-tutor-update-tutor-profile-cw modified update method & changed the variable declaration position
//20170228 CW c37-tutor-update-tutor-profile-cw modified update method update method to fix The index 30 is out of range.
//20170301 CW c37-tutor-update-tutor-profile-cw modified update method to fix query error
//20170306 CW c37-tutor-update-tutor-profile-cw modified update method & removed password encryption
//20170306 CW c37-tutor-update-tutor-profile-cw removed getListOfUsernameEmail
				// add validateUsernameEmailFields() method from 
//20170306 CW c37-tutor-update-tutor-profile-cw removed un wanted space from getListOfUsernameEmail()
//20170307 CW c37-tutor-update-tutor-profile-cw modified update() method query to query by code in where clause.
//20170418 CW c158-send-email-tutor-employment-confirmation-cw removed password selecting from findById
				// add getListOfEmailToSendEmploymentRequest method
//20170420 CW c158-send-email-tutor-employment-confirmation-cw add gender into getListOfEmailToSendEmploymentRequest method
//20170421 CW c159-courseprovider-accept-tutor-request-cw Add getTutorsListOfCourseprovider method
//20170424 CW c159-courseprovider-accept-tutor-request-cw modify getTutorsListOfCourseprovider method to get the gender correctly
//20170425 CW c159-courseprovider-accept-tutor-request-cw modify getTutorsListOfCourseprovider method query to decode VARIFICATIONSTATUS
//20170425 CW c159-courseprovider-accept-tutor-request-cw modify getTutorsListOfCourseprovider method & changed VARIFICATIONSTATUS into CONFIRMATIONSTATUS

import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * this class used to manage the town related data 
 * further it implements ICrud interface
 * @author CM
 */
public class TutorDAO implements ICrud {

	static Logger log = Logger.getLogger(TutorDAO.class.getName());

	@Override
	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Updates the Tutor Details
	 * 
	 * @author Chinthaka
	 * 
	 * @param Object code - A Tutor Object with tutor code
	 * 
	 * @return Returns an int value depending on the database executeUpdate() method
	 */
	@Override
	public int update(Object object) throws SQLException, Exception {

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		int status = -1;
		
		try {
			final Tutor tutor = (Tutor) object;
			
			StringBuilder queryBuilder = new StringBuilder("UPDATE [CAMPUS].[TUTOR] SET FIRSTNAME = ? , MIDDLENAME = ? , LASTNAME = ? , GENDER = ? , ");
			queryBuilder.append("EMAIL = ? , LANDPHONECOUNTRYCODE = ? , LANDPHONEAREACODE = ? , LANDPHONENUMBER = ? , MOBILEPHONECOUNTRYCODE = ? ,");
			queryBuilder.append("MOBILEPHONENETWORKCODE = ? , MOBILEPHONENUMBER = ? ,DESCRIPTION = ? , EXPERIENCE = ? , WEBLINK = ? , ");		
			queryBuilder.append("FACEBOOKURL = ? , TWITTERURL = ? , MYSPACEURL = ? , LINKEDINURL = ? , INSTAGRAMURL = ? ,");
			queryBuilder.append("VIBERNUMBER = ? , WHATSAPPNUMBER = ? , ISAPPROVED = ? , TUTORSTATUS = ? , ADDRESS1 = ? , ");
			queryBuilder.append("ADDRESS2 = ? , ADDRESS3 = ? , TOWN = ? , USERTYPE = ? , MODON = GETDATE() , ");
			queryBuilder.append("MODBY = ? ");	
			
			if(!Validator.isEmptyOrHavingSpace(tutor.getPassword())){
				queryBuilder.append(", PASSWORD = ? ");	
				queryBuilder.append("WHERE CODE = ?;");
			}else{
				queryBuilder.append("WHERE CODE = ?;");
			}
					
			conn = ConnectionManager.getConnection();			

			preparedStatement = conn.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, tutor.getFirstName());
			preparedStatement.setString(2, tutor.getMiddleName());
			preparedStatement.setString(3, tutor.getLastName());
			
			preparedStatement.setString(4, tutor.getGender());
			
			preparedStatement.setString(5, tutor.getEmailAddress());
			preparedStatement.setString(6, tutor.getLandCountryCode());
			preparedStatement.setString(7, tutor.getLandAreaCode());
			preparedStatement.setString(8, tutor.getLandNumber());
			preparedStatement.setString(9, tutor.getMobileCountryCode());
			
			preparedStatement.setString(10, tutor.getMobileNetworkCode());
			preparedStatement.setString(11, tutor.getMobileNumber());
			preparedStatement.setString(12, tutor.getDescription());
			preparedStatement.setString(13, tutor.getExperience());
			preparedStatement.setString(14, tutor.getWebLink());
		
			preparedStatement.setString(15, tutor.getFacebookLink());
			preparedStatement.setString(16, tutor.getTwitterNumber());
			preparedStatement.setString(17, tutor.getMySpaceId()); 
			preparedStatement.setString(18, tutor.getLinkedInLink());
			preparedStatement.setString(19, tutor.getInstagramId());
		
			preparedStatement.setString(20, tutor.getViberNumber());
			preparedStatement.setString(21, tutor.getWhatsAppId());
			
			preparedStatement.setBoolean(22, tutor.getIsApproved()); 
			preparedStatement.setInt(23, tutor.getTutorStatus());			
			preparedStatement.setString(24, tutor.getAddressLine1());
			
			preparedStatement.setString(25, tutor.getAddressLine2());
			preparedStatement.setString(26, tutor.getAddressLine3());
			preparedStatement.setString(27, tutor.getTown());
			preparedStatement.setInt(28, tutor.getUsertype());		
			preparedStatement.setString(29, tutor.getModBy());			
			
			if(!Validator.isEmptyOrHavingSpace(tutor.getPassword())){
				Encryptable passwordEncryptor = new TripleDesEncryptor(tutor.getPassword());
				preparedStatement.setString(30, tutor.getPassword());
				preparedStatement.setInt(31, tutor.getCode());
			}else{
				preparedStatement.setInt(30, tutor.getCode());
			}
			
			status = preparedStatement.executeUpdate();

		} catch (ClassCastException cce) {
			log.error("update(): ClassCastException " + cce.toString());
			throw cce;
		} catch (SQLException exception) {
			log.error("update(): SQLException " + exception.toString());
			throw exception;
		} catch (Exception exception) {
			log.error("update(): Exception " + exception.toString());
			throw exception;
		} finally {
			DaoHelper.cleanup(conn, preparedStatement, null);
		}

		return status;
		
	}

	@Override
	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Returns the Tutor Details
	 * 
	 * @author Chinthaka
	 * 
	 * @param Object code - A Tutor Object with tutor code
	 * 
	 * @return Returns the Tutor Details in Database for a given Tutor Code from a collection of collection
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)	throws SQLException, Exception {
		
		final Collection<Collection<String>> allTutorList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int countryCode = 0;
		double townCode = 0;
		String countryName = null;
		String townName = null;
					
		StringBuilder queryBuilder = new StringBuilder("SELECT T.CODE, T.USERNAME, T.FIRSTNAME, T.MIDDLENAME, T.LASTNAME, T.GENDER, T.EMAIL, T.LANDPHONECOUNTRYCODE, ");
		queryBuilder.append("T.LANDPHONEAREACODE, T.LANDPHONENUMBER, T.MOBILEPHONECOUNTRYCODE, T.MOBILEPHONENETWORKCODE, T.MOBILEPHONENUMBER, T.DESCRIPTION, T.EXPERIENCE, ");
		queryBuilder.append("T.WEBLINK, T.FACEBOOKURL, T.TWITTERURL, T.MYSPACEURL, T.LINKEDINURL, T.INSTAGRAMURL, T.VIBERNUMBER, T.WHATSAPPNUMBER, T.ADDRESS1, T.ADDRESS2, ");		
		queryBuilder.append("T.ADDRESS3, T.TOWN,  T.USERTYPE, T.ISAPPROVED, T.TUTORSTATUS, TOWN.NAME AS TOWNNAME, C.NAME AS COUNTRYNAME ");
		queryBuilder.append("FROM [CAMPUS].[TUTOR] T ");
		queryBuilder.append("JOIN [CAMPUS].[COUNTRY2] C ON C.CODE = T.LANDPHONECOUNTRYCODE ");
		queryBuilder.append("JOIN [CAMPUS].[TOWN] TOWN ON TOWN.CODE = T.TOWN ");
		queryBuilder.append("WHERE T.CODE = ?;");
			
		try {
			
			Tutor tutor = (Tutor) code; 
			conn = ConnectionManager.getConnection();			
			
			stmt = conn.prepareStatement(queryBuilder.toString());
			stmt.setInt(1, tutor.getCode());
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleTutorList = new ArrayList<String>();		
				
				Encryptable passwordEncryptor = new TripleDesEncryptor();
								
				singleTutorList.add(rs.getString("CODE"));
				singleTutorList.add(rs.getString("USERNAME"));
				singleTutorList.add(rs.getString("FIRSTNAME"));
				singleTutorList.add(rs.getString("MIDDLENAME"));
				singleTutorList.add(rs.getString("LASTNAME"));
				singleTutorList.add(rs.getString("GENDER"));
				singleTutorList.add(rs.getString("EMAIL"));
				singleTutorList.add(rs.getString("LANDPHONECOUNTRYCODE"));
				singleTutorList.add(rs.getString("LANDPHONEAREACODE"));
				singleTutorList.add(rs.getString("LANDPHONENUMBER"));
				singleTutorList.add(rs.getString("MOBILEPHONECOUNTRYCODE"));
				singleTutorList.add(rs.getString("MOBILEPHONENETWORKCODE"));
				singleTutorList.add(rs.getString("MOBILEPHONENUMBER"));
				singleTutorList.add(rs.getString("DESCRIPTION").trim());
				singleTutorList.add(rs.getString("EXPERIENCE").trim());
				singleTutorList.add(rs.getString("WEBLINK"));
				singleTutorList.add(rs.getString("FACEBOOKURL"));
				singleTutorList.add(rs.getString("TWITTERURL"));
				singleTutorList.add(rs.getString("MYSPACEURL"));
				singleTutorList.add(rs.getString("LINKEDINURL"));
				singleTutorList.add(rs.getString("INSTAGRAMURL"));
				singleTutorList.add(rs.getString("VIBERNUMBER"));
				singleTutorList.add(rs.getString("WHATSAPPNUMBER"));
				singleTutorList.add(rs.getString("ADDRESS1"));
				singleTutorList.add(rs.getString("ADDRESS2"));
				singleTutorList.add(rs.getString("ADDRESS3"));
				singleTutorList.add(rs.getString("TOWNNAME"));
				singleTutorList.add(rs.getString("TOWN"));				
				singleTutorList.add(rs.getString("USERTYPE"));
				singleTutorList.add(rs.getString("COUNTRYNAME"));
				singleTutorList.add(rs.getString("ISAPPROVED"));
				singleTutorList.add(rs.getString("TUTORSTATUS"));

				allTutorList.add(singleTutorList);
			}
		} catch (ClassCastException cce) {
			log.error("findById(): ClassCastException " + cce.toString());
			throw cce;
		} catch (SQLException sqlException) {
			log.info("findById(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById(): Exception " + e.toString());
			throw e;
		} finally {			
			DaoHelper.cleanup(conn, stmt, rs);
		}
		return allTutorList;

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
	
	/**
	 * Check the email & username given with already entered username & email in the database 
	 * @author Chinthaka 
	 * @return Returns 1 if the username is available in the database, returns 2 if the email is available & 
	 * 				returns 0 if both are not used to create a tutor profile.
	 */
	public static int validateUsernameEmailFields(String username, String email) throws SQLException, Exception {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [USERNAME], [EMAIL] FROM [CAMPUS].[TUTOR] WHERE USERNAME=? OR EMAIL=?";

			stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, email);
			rs = stmt.executeQuery();

			while (rs.next()) {
				if(rs.getString("USERNAME").equals(username)){
					return 1;
				}else if(rs.getString("EMAIL").equals(email)){
					return 2;
				}
			}
		} catch (SQLException sqlException) {
			log.info("validateUsernameEmailFields(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("validateUsernameEmailFields(): Exception " + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		return 0;
	}
	
	/**
	 * @param String values containing username & email entered by the user
	 * @author Chinthaka 
	 * @return all the matching email & username list already entered into the database for the given username & email
	 */
	public static Collection<Collection<String>> getListOfUsernameEmail(String username, String email) throws SQLException, Exception{

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final Collection<Collection<String>> allUsernameEmailList = new ArrayList<Collection<String>>();
		
		try{
			conn = ConnectionManager.getConnection();
			String query = "SELECT [USERNAME], [EMAIL] FROM [CAMPUS].[TUTOR] WHERE USERNAME=? OR EMAIL=?";

			stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, email);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				final ArrayList<String> usernameEmailList = new ArrayList<String>();
				usernameEmailList.add(rs.getString("USERNAME"));
				usernameEmailList.add(rs.getString("EMAIL"));
				allUsernameEmailList.add(usernameEmailList);
			}
			
		} catch (SQLException sqlException) {
			log.info("getListOfUsernameEmail(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getListOfUsernameEmail(): Exception " + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		
		return allUsernameEmailList;
	}
	

	/**
	 * This method is used to get the list of course provider email addresses with the tutor name & email of systemconfig table to BCC the email
	 * @param String values containing courseProviderCode, systemConfigCode for system email & tutorCode 
	 * @author Chinthaka 
	 * @return all the matching email list of course providers to send the email with tutor name & system email from sysconfig table
	 */
	public static Collection<Collection<String>> getListOfEmailToSendEmploymentRequest(String courseProviderCode, String systemConfigCode, String tutorCode) throws SQLException, Exception{

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final Collection<Collection<String>> allTutorCpEmailList = new ArrayList<Collection<String>>();
		
		try{
			conn = ConnectionManager.getConnection();
			
			String query = "SELECT T.FIRSTNAME +' '+ T.LASTNAME NAME, T.GENDER GENDER , CPA.EMAIL EMAIL, SC.VALUE1 SYSEMAIL FROM CAMPUS.COURSEPROVIDERACCOUNT CPA, CAMPUS.SYSTEMCONFIG SC, CAMPUS.TUTOR T "
					+ "WHERE CPA.COURSEPROVIDER = ? AND SC.SYSTEMCONFIGCODE = ? AND T.CODE = ?";

			stmt = conn.prepareStatement(query);
			stmt.setString(1, courseProviderCode);
			stmt.setString(2, systemConfigCode);
			stmt.setString(3, tutorCode);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				final ArrayList<String> TutorCpEmailList = new ArrayList<String>();
				TutorCpEmailList.add(rs.getString("NAME"));
				TutorCpEmailList.add(rs.getString("GENDER"));
				TutorCpEmailList.add(rs.getString("EMAIL"));
				TutorCpEmailList.add(rs.getString("SYSEMAIL"));
				allTutorCpEmailList.add(TutorCpEmailList);
			}
			
		} catch (SQLException sqlException) {
			log.info("getListOfEmailToSendEmploymentRequest(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getListOfEmailToSendEmploymentRequest(): Exception " + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		
		return allTutorCpEmailList;
	}

	/**
	 * Returns the Tutors list for given course provider.
	 * @author Chinthaka 
	 * @return Returns a collection of collection consists the Tutors list of given course provider
	 */
	public Collection<Collection<String>> getTutorsListOfCourseprovider(String courseProviderCode) throws SQLException, Exception {
		
		final Collection<Collection<String>> allEmploymentTutorsList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
									
		StringBuilder queryBuilder = new StringBuilder("SELECT T.CODE TUTORCODE, T.FIRSTNAME +' '+ T.MIDDLENAME +' '+ T.LASTNAME NAME, "); 
		queryBuilder.append("CASE T.GENDER WHEN 1 then 'Male' WHEN 2 then 'Female' END GENDER, ");
		queryBuilder.append("T.EMAIL, T.LANDPHONECOUNTRYCODE + T.LANDPHONEAREACODE + T.LANDPHONENUMBER LANDNUMBER, ");
		queryBuilder.append("T.MOBILEPHONECOUNTRYCODE + T.MOBILEPHONENETWORKCODE + T.MOBILEPHONENUMBER MOBILENUMBER, ");
		queryBuilder.append("EMP.CODE EMPCODE, EMP.COURSEPROVIDER CPCODE, ");
		queryBuilder.append("CASE EMP.CONFIRMATIONSTATUS WHEN 0 then 'Inactive' WHEN 1 then 'Active' WHEN 2 then 'Pending' WHEN 4 then 'Expired' WHEN -1 then 'Undefined' END CONFIRMSTATUS ");
		queryBuilder.append("FROM CAMPUS.TUTOR T ");
		queryBuilder.append("INNER JOIN CAMPUS.EMPLOYMENT EMP ON T.CODE = EMP.TUTOR ");
		queryBuilder.append("WHERE EMP.COURSEPROVIDER =  ? ORDER BY T.CODE DESC");
		
		try {	
			
			conn = ConnectionManager.getConnection();						
			stmt = conn.prepareStatement(queryBuilder.toString());
			stmt.setString(1, courseProviderCode);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleEmploymentTutorsList = new ArrayList<String>();		
												
				singleEmploymentTutorsList.add(rs.getString("TUTORCODE"));
				singleEmploymentTutorsList.add(rs.getString("NAME"));
				singleEmploymentTutorsList.add(rs.getString("GENDER"));
				singleEmploymentTutorsList.add(rs.getString("EMAIL"));
				singleEmploymentTutorsList.add(rs.getString("LANDNUMBER"));
				singleEmploymentTutorsList.add(rs.getString("MOBILENUMBER"));
				singleEmploymentTutorsList.add(rs.getString("EMPCODE"));
				singleEmploymentTutorsList.add(rs.getString("CONFIRMSTATUS"));
				singleEmploymentTutorsList.add(rs.getString("CPCODE"));

				allEmploymentTutorsList.add(singleEmploymentTutorsList);
			}		
		} catch (SQLException sqlException) {
			log.info("getTutorsListOfCourseprovider(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getTutorsListOfCourseprovider(): Exception " + e.toString());
			throw e;
		} finally {			
			DaoHelper.cleanup(conn, stmt, rs);
		}
		return allEmploymentTutorsList;
	}
}
