package com.genesiis.campus.entity;

//20161121 CM c36-add-tutor-information INIT TutorDAO.java
//20161121 CM c36-add-tutor-information Modified add()method. 
//20161221 CW c36-add-tutor-details Removed findById() method.
//20161221 CW c36-add-tutor-details Modified add() method & added Password Encryption. 
//20161223 CW c36-add-tutor-details Modified add() method & added StringBuilder.
//20170106 CW c36-add-tutor-details Added isAvailableUserName() method 
//20170110 CW c36-add-tutor-details Modified add() method - add tutor crtBy & modBy using getter methods 
//20170111 CW c36-add-tutor-details removed isAvailableUserName() method 
//20170124 CW c36-add-tutor-details modified getAll() method according to the 201701201215 DJ crev modification request.
//20170125 CW c36-add-tutor-details add validateUsernameEmailFields() method.
//20170130 CW c36-add-tutor-details modified validateUsernameEmailFields() method
//20170130 CW c36-add-tutor-information re-organise the import statements.

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

	/**
	 * Save tutor details in Database
	 * 
	 * @author Chathuri, Chinthaka
	 * @param object
	 *            : Tutor object of Object type
	 * @return int number of success/fail status
	 */
	@Override
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
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns all the town details in Database
	 * 
	 * @author Chinthaka
	 * 
	 * @return Returns all the tutor details from a collection of collection
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException,	Exception {
		
		final Collection<Collection<String>> allTutorList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM [CAMPUS].[TUTOR]";

			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleTutorList = new ArrayList<String>();
				singleTutorList.add(rs.getString("CODE"));
				singleTutorList.add(rs.getString("USERNAME"));
				singleTutorList.add(rs.getString("PASSWORD"));
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
				singleTutorList.add(rs.getString("ISAPPROVED"));
				singleTutorList.add(rs.getString("TUTORSTATUS"));
				singleTutorList.add(rs.getString("ADDRESS1"));
				singleTutorList.add(rs.getString("ADDRESS2"));
				singleTutorList.add(rs.getString("ADDRESS3"));
				singleTutorList.add(rs.getString("TOWN"));
				singleTutorList.add(rs.getString("USERTYPE"));
				singleTutorList.add(rs.getString("CRTON"));
				singleTutorList.add(rs.getString("CRTBY"));
				singleTutorList.add(rs.getString("MODON"));
				singleTutorList.add(rs.getString("MODBY"));
				allTutorList.add(singleTutorList);
			}
		} catch (SQLException sqlException) {
			log.info("getAll(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll(): Exception " + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		return allTutorList;
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
