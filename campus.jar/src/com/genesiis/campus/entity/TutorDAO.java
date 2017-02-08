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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;
import org.apache.log4j.Logger;

public class TutorDAO implements ICrud {

	static Logger log = Logger.getLogger(TutorDAO.class.getName());

	@Override
	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Save tutor details in Database
	 * 
	 * @author Chathuri, Chinthaka
	 * @param object
	 *            : Tutor object of Object type
	 * @return int number of success/fail status
	 */
/*	@Override
	public int add(Object object) throws SQLException, Exception {
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		int status = -1;
		
		StringBuilder queryBuilder = new StringBuilder("INSERT INTO [CAMPUS].[TUTOR] (USERNAME, PASSWORD, FIRSTNAME, ");
		queryBuilder.append("MIDDLENAME, LASTNAME, GENDER, EMAIL, ");
		queryBuilder.append("LANDPHONECOUNTRYCODE, LANDPHONEAREACODE,LANDPHONENUMBER,MOBILEPHONECOUNTRYCODE,MOBILEPHONENETWORKCODE");
		queryBuilder.append(",MOBILEPHONENUMBER,DESCRIPTION, EXPERIENCE,WEBLINK,FACEBOOKURL,TWITTERURL,MYSPACEURL,LINKEDINURL,INSTAGRAMURL,");
		queryBuilder.append("VIBERNUMBER,WHATSAPPNUMBER,ISAPPROVED,TUTORSTATUS, ADDRESS1,ADDRESS2,ADDRESS3,TOWN,USERTYPE");
		queryBuilder.append(",CRTON,CRTBY,MODON, MODBY ) ");
		queryBuilder.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,GETDATE(),?, GETDATE(), ?);");
						
		try {			
			final Tutor tutor = (Tutor) object;
			conn = ConnectionManager.getConnection();			

			Encryptable passwordEncryptor = new TripleDesEncryptor(tutor.getPassword());
			
			preparedStatement = conn.prepareStatement(queryBuilder.toString());
			preparedStatement.setString(1, tutor.getUsername());			
			preparedStatement.setString(2, passwordEncryptor.encryptSensitiveDataToString());
			preparedStatement.setString(3, tutor.getFirstName());
			preparedStatement.setString(4, tutor.getMiddleName());
			preparedStatement.setString(5, tutor.getLastName());
			preparedStatement.setString(6, tutor.getGender());
			
			preparedStatement.setString(7, tutor.getEmailAddress());
			preparedStatement.setString(8, tutor.getLandCountryCode());
			preparedStatement.setString(9, tutor.getLandAreaCode());
			preparedStatement.setString(10, tutor.getLandNumber());
			preparedStatement.setString(11, tutor.getMobileCountryCode());
			preparedStatement.setString(12, tutor.getMobileNetworkCode());
			preparedStatement.setString(13, tutor.getMobileNumber());
			preparedStatement.setString(14, tutor.getDescription());
			preparedStatement.setString(15, tutor.getExperience());
			preparedStatement.setString(16, tutor.getWebLink());
			preparedStatement.setString(17, tutor.getFacebookLink());
			preparedStatement.setString(18, tutor.getTwitterNumber());
			preparedStatement.setString(19, tutor.getMySpaceId()); 
			preparedStatement.setString(20, tutor.getLinkedInLink());
			preparedStatement.setString(21, tutor.getInstagramId());
			preparedStatement.setString(22, tutor.getViberNumber());
			preparedStatement.setString(23, tutor.getWhatsAppId());
			preparedStatement.setBoolean(24, tutor.getIsApproved()); 
			preparedStatement.setInt(25, tutor.getTutorStatus()); 
			preparedStatement.setString(26, tutor.getAddressLine1());
			preparedStatement.setString(27, tutor.getAddressLine2());
			preparedStatement.setString(28, tutor.getAddressLine3());
			preparedStatement.setString(29, tutor.getTown());
			preparedStatement.setInt(30, tutor.getUsertype());
			preparedStatement.setString(31, tutor.getCrtBy());
			preparedStatement.setString(32, tutor.getModBy());
			status = preparedStatement.executeUpdate();

		} catch (ClassCastException cce) {
			log.error("add(): ClassCastException " + cce.toString());
			throw cce;
		} catch (SQLException exception) {
			log.error("add(): SQLException " + exception.toString());
			throw exception;
		} catch (Exception exception) {
			log.error("add(): Exception " + exception.toString());
			throw exception;
		} finally {
			DaoHelper.cleanup(conn, preparedStatement, null);
		}

		return status;
	}*/

	@Override
	public int update(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		int status = -1;
		System.out.println("need to modify com.genesiis.campus.entity.TutorDAO.update(Object) CREV");
		StringBuilder queryBuilder = new StringBuilder("UPDATE [CAMPUS].[TUTOR] SET PASSWORD = ? , FIRSTNAME = ? , MIDDLENAME = ? , LASTNAME = ? , GENDER = ? , ");
		queryBuilder.append("EMAIL = ? , LANDPHONECOUNTRYCODE = ? , LANDPHONEAREACODE = ? , LANDPHONENUMBER = ? , MOBILEPHONECOUNTRYCODE = ? ,");
		queryBuilder.append("MOBILEPHONENETWORKCODE = ? , MOBILEPHONENUMBER = ? ,DESCRIPTION = ? , EXPERIENCE = ? , WEBLINK = ? , ");		
		queryBuilder.append("FACEBOOKURL = ? , TWITTERURL = ? , MYSPACEURL = ? , LINKEDINURL = ? , INSTAGRAMURL = ? ,");
		queryBuilder.append("VIBERNUMBER = ? , WHATSAPPNUMBER = ? , ISAPPROVED = ? , TUTORSTATUS = ? , ADDRESS1 = ? , ");
		queryBuilder.append("ADDRESS2 = ? , ADDRESS3 = ? , TOWN = ? , USERTYPE = ? , MODON = GETDATE() , ");
		queryBuilder.append("MODBY = ? ");
		queryBuilder.append("WHERE USERNAME = ?;");
				
		try {			
			final Tutor tutor = (Tutor) object;
			conn = ConnectionManager.getConnection();			

		//	Encryptable passwordEncryptor = new TripleDesEncryptor(tutor.getPassword());
			
			preparedStatement = conn.prepareStatement(queryBuilder.toString());
			//preparedStatement.setString(1, tutor.getUsername());			
			//preparedStatement.setString(1, passwordEncryptor.encryptSensitiveDataToString()); // password encryptor removed
			preparedStatement.setString(1, tutor.getPassword());
			preparedStatement.setString(2, tutor.getFirstName());
			preparedStatement.setString(3, tutor.getMiddleName());
			preparedStatement.setString(4, tutor.getLastName());
			
			preparedStatement.setString(5, tutor.getGender());
			
			preparedStatement.setString(6, tutor.getEmailAddress());
			preparedStatement.setString(7, tutor.getLandCountryCode());
			preparedStatement.setString(8, tutor.getLandAreaCode());
			preparedStatement.setString(9, tutor.getLandNumber());
			preparedStatement.setString(10, tutor.getMobileCountryCode());
			
			preparedStatement.setString(11, tutor.getMobileNetworkCode());
			preparedStatement.setString(12, tutor.getMobileNumber());
			preparedStatement.setString(13, tutor.getDescription());
			preparedStatement.setString(14, tutor.getExperience());
			preparedStatement.setString(15, tutor.getWebLink());
		
			preparedStatement.setString(16, tutor.getFacebookLink());
			preparedStatement.setString(17, tutor.getTwitterNumber());
			preparedStatement.setString(18, tutor.getMySpaceId()); 
			preparedStatement.setString(19, tutor.getLinkedInLink());
			preparedStatement.setString(20, tutor.getInstagramId());
		
			preparedStatement.setString(21, tutor.getViberNumber());
			preparedStatement.setString(22, tutor.getWhatsAppId());
			
			preparedStatement.setBoolean(23, tutor.getIsApproved()); 
		//	preparedStatement.setInt(25, ApplicationStatus.INACTIVE.getStatusValue()); // after table modified this should be Pending
			preparedStatement.setInt(24, tutor.getTutorStatus());			
			preparedStatement.setString(25, tutor.getAddressLine1());
			
			preparedStatement.setString(26, tutor.getAddressLine2());
			preparedStatement.setString(27, tutor.getAddressLine3());
			preparedStatement.setString(28, tutor.getTown());
			preparedStatement.setInt(29, tutor.getUsertype());		
			preparedStatement.setString(30, "chathuri");
			
			preparedStatement.setString(31, tutor.getUsername());
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
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		final Collection<Collection<String>> allTutorList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int countryCode = 0;
		double townCode = 0;
		String countryName = null;
		String townName = null;
		
		try {
			Tutor tutor = (Tutor) code; 
			conn = ConnectionManager.getConnection();
			String query = "SELECT * FROM [CAMPUS].[TUTOR] WHERE CODE=?";
/*
 * 
SELECT TUTOR.CODE, TUTOR.USERNAME, TUTOR.PASSWORD, TUTOR.FIRSTNAME, TUTOR.MIDDLENAME, TUTOR.LASTNAME, TUTOR.GENDER, TUTOR.EMAIL, TUTOR.LANDPHONECOUNTRYCODE, TUTOR.LANDPHONEAREACODE, TUTOR.LANDPHONENUMBER, TUTOR.MOBILEPHONECOUNTRYCODE, 
TUTOR.MOBILEPHONENETWORKCODE, TUTOR.MOBILEPHONENUMBER, TUTOR.DESCRIPTION, TUTOR.EXPERIENCE, TUTOR.WEBLINK, TUTOR.FACEBOOKURL, TUTOR.TWITTERURL, TUTOR.MYSPACEURL, TUTOR.LINKEDINURL, TUTOR.INSTAGRAMURL, 
TUTOR.VIBERNUMBER, TUTOR.WHATSAPPNUMBER, TUTOR.ADDRESS1, TUTOR.ADDRESS2, TUTOR.ADDRESS3, TUTOR.TOWN,  TUTOR.USERTYPE, TUTOR.ISAPPROVED, TUTOR.TUTORSTATUS, TOWN.NAME, COUNTRY2.NAME
FROM TUTOR, COUNTRY2, TOWN


"SELECT * FROM [CAMPUS].[TUTOR] WHERE CODE=?";

COUNTRY2
TOWN

SELECT *, COUNTRY2.NAME FROM TUTOR, COUNTRY2, TOWN
WHERE TUTOR.LANDPHONECOUNTRYCODE = COUNTRY2.CODE
AND TUTOR.TOWN = TOWN.CODE
AND TUTOR.CODE = ?



SELECT TUTOR.CODE, TUTOR.USERNAME, TUTOR.PASSWORD, TUTOR.FIRSTNAME, TUTOR.MIDDLENAME, TUTOR.LASTNAME, TUTOR.GENDER, TUTOR.EMAIL, TUTOR.LANDPHONECOUNTRYCODE, TUTOR.LANDPHONEAREACODE, TUTOR.LANDPHONENUMBER, TUTOR.MOBILEPHONECOUNTRYCODE, COUNTRY2.NAME
FROM TUTOR, COUNTRY2


 * 
 */
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, tutor.getCode());
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleTutorList = new ArrayList<String>();		
				
				Encryptable passwordEncryptor = new TripleDesEncryptor();

								
				singleTutorList.add(rs.getString("CODE"));
				singleTutorList.add(rs.getString("USERNAME"));
				//singleTutorList.add(passwordEncryptor.decryptSensitiveDataToString(rs.getString("PASSWORD"))); // commented until password Encryption error fixed
				singleTutorList.add("PASSWORD");
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
				singleTutorList.add(rs.getString("DESCRIPTION"));
				singleTutorList.add(rs.getString("EXPERIENCE"));
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
				
				if (rs.getString("TOWN") != null) {
					countryCode = Integer.parseInt(rs.getString("LANDPHONECOUNTRYCODE"));
					TownDAO country = new TownDAO();
					//townName = country.findById(countryCode);
					townName = "x";
				}
				
				singleTutorList.add(townName);
				singleTutorList.add(rs.getString("TOWN"));				
								
				singleTutorList.add(rs.getString("USERTYPE"));
								
				if (rs.getString("LANDPHONECOUNTRYCODE") != null) {
					countryCode = Integer.parseInt(rs.getString("LANDPHONECOUNTRYCODE"));
					CountryDAO country = new CountryDAO();
					System.out.println("Need to change the com.genesiis.campus.entity.TutorDAO.findById(Object) after MX fixes done ");
					countryName = country.findCountryByCode(countryCode);
				}			

				singleTutorList.add(countryName);
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
	public static int validateUsernameEmailFields(String username, String email) throws SQLException,	Exception {
		
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

}
