package com.genesiis.campus.entity;

//20161121 CM c36-add-tutor-information INIT TutorDAO.java
//20161121 CM c36-add-tutor-information Modified add()method. 
//20161221 CW c36-add-tutor-details Removed findById() method.
//20161221 CW c36-add-tutor-details Modified add() method & added Password Encryption. 
//20161223 CW c36-add-tutor-details Modified add() method & added StringBuilder.
//20170106 CW c36-add-tutor-details Added isAvailableUserName() method

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;
import com.genesiis.campus.validation.AccountType;
import com.genesiis.campus.validation.ApplicationStatus;

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
			preparedStatement.setString(22, tutor.getViber());
			preparedStatement.setString(23, tutor.getWhatsAppId());
			preparedStatement.setBoolean(24, tutor.getIsApproved()); 
			preparedStatement.setInt(25, tutor.getTutorStatus()); 
			preparedStatement.setString(26, tutor.getAddressLine1());
			preparedStatement.setString(27, tutor.getAddressLine2());
			preparedStatement.setString(28, tutor.getAddressLine3());
			preparedStatement.setString(29, tutor.getTown());
			preparedStatement.setInt(30, tutor.getUsertype());
			preparedStatement.setString(31, "chathuri");
			preparedStatement.setString(32, "chathuri");
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
	 * Check the entered username is already available in the database
	 * 
	 * @author Chinthaka
	 * @param Requested username
	 * @return boolean - returns true if Requested username is not used by any tutor
	 */
	
	public boolean isAvailableUserName(String userName) throws SQLException, Exception {
		boolean valid = false;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try { 
			System.out.println("isAvailableUserName userName ="+userName+"AAAA");
			conn = ConnectionManager.getConnection();
			//String query = "SELECT COUNT([USERNAME]) userCount FROM [CAMPUS].[TUTOR] WHERE USERNAME=?";
			String query = "SELECT [USERNAME] userCount FROM [CAMPUS].[TUTOR] WHERE USERNAME=?";

		    System.out.println(" @1 "); 
			stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			rs = stmt.executeQuery();

			if (!rs.isBeforeFirst() ) {    
			    System.out.println("No data"); 
			} 
			
			System.out.println("isAvailableUserName count = " + rs.getInt("userCount"));
			while (rs.next()) {
				System.out.println("ttttttttt");
				if (rs.getInt("userCount") == 0 ){
					valid = true;
					System.out.println("valid = " + valid);
				}
			}
		} catch (SQLException sqlException) {
			log.info("isAvailableUserName(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("isAvailableUserName(): Exception " + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		
		return valid;
	}

}
