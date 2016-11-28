package com.genesiis.campus.entity;

//20161121 CM c36-add-tutor-information INIT TutorDAO.java
//20161121 CM c36-add-tutor-information Modified add()method. 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.AccountType;
import com.genesiis.campus.validation.ApplicationStatus;

public class TutorDAO implements ICrud {

	static Logger log = Logger.getLogger(TutorDAO.class.getName());

	/**
	 * Save tutor details in Database
	 * 
	 * @author Chathuri
	 * @param object
	 *            : Tutor object of Object type
	 * @return int number of success/fail status
	 */
	@Override
	public int add(Object object) throws SQLException, Exception {
		String query = "INSERT INTO [CAMPUS].[TUTOR] (USERNAME, PASSWORD, FIRSTNAME, "
				+ "MIDDLENAME, LASTNAME, GENDER, EMAIL, IMAGEPATH, "
				+ "LANDPHONECOUNTRYCODE, LANDPHONEAREACODE,LANDPHONENUMBER,MOBILEPHONECOUNTRYCODE,MOBILEPHONENETWORKCODE"
				+ ",MOBILEPHONENUMBER,DESCRIPTION, EXPERIENCE,WEBLINK,FACEBOOKURL,TWITTERURL,MYSPACEURL,LINKEDINURL,INSTAGRAMURL,"
				+ "VIBERNUMBER,WHATSAPPNUMBER,ISAPPROVED,ISACTIVE, ADDRESS1,ADDRESS2,ADDRESS3,TOWN,USERTYPE"
				+ ",CRTON,CRTBY,MODON, MODBY ) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,GETDATE(),?, GETDATE(), ?)";
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		
		int status = -1;		
		try {			
			final Tutor tutor = (Tutor) object;
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, tutor.getUsername());
			preparedStatement.setString(2, tutor.getPassword());
			preparedStatement.setString(3, tutor.getFirstName());
			preparedStatement.setString(4, tutor.getMiddleName());
			preparedStatement.setString(5, tutor.getLastName());
			preparedStatement.setString(6, tutor.getGender());
			preparedStatement.setString(7, tutor.getEmailAddress());
			preparedStatement.setString(8, tutor.getImagePath());
			preparedStatement.setString(9, tutor.getLandCountryCode());
			preparedStatement.setString(10, tutor.getLandAreaCode());
			preparedStatement.setString(11, tutor.getLandNumber());
			preparedStatement.setString(12, tutor.getMobileCountryCode());
			preparedStatement.setString(13, tutor.getMobileNetworkCode());
			preparedStatement.setString(14, tutor.getMobileNumber());
			preparedStatement.setString(15, tutor.getDescription());
			preparedStatement.setString(16, tutor.getExperience());
			preparedStatement.setString(17, tutor.getWebLink());
			preparedStatement.setString(18, tutor.getFacebookLink());
			preparedStatement.setString(19, tutor.getTwitterNumber());
			preparedStatement.setString(20, tutor.getMySpaceId()); 
			preparedStatement.setString(21, tutor.getLinkedInLink());
			preparedStatement.setString(22, tutor.getInstagramId());
			preparedStatement.setString(23, tutor.getViber());
			preparedStatement.setString(24, tutor.getWhatsAppId());
			preparedStatement.setInt(25, ApplicationStatus.ACTIVE.getStatusValue());
			preparedStatement.setInt(26, tutor.getIsApproved());
			preparedStatement.setString(27, tutor.getAddressLine1());
			preparedStatement.setString(28, tutor.getAddressLine2());
			preparedStatement.setString(29, tutor.getAddressLine3());
			preparedStatement.setString(30, tutor.getTown());
			preparedStatement.setInt(31, tutor.getUsertype());
			preparedStatement.setString(32, "chathuri");
			preparedStatement.setString(33, "chathuri");
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
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
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


	/**
	 * Returns the username in Database
	 * 
	 * @author Chathuri, Chinthaka
	 * 
	 * @return Returns the username from a collection of collection
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		final Collection<Collection<String>> allTownList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Tutor tutor = (Tutor) code; 
			conn = ConnectionManager.getConnection();
			String query = "SELECT [USERNAME] FROM [CAMPUS].[TUTOR] WHERE USERNAME=?";

			stmt = conn.prepareStatement(query);
			stmt.setString(1, tutor.getUsername());
			final ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleTownList = new ArrayList<String>();
				singleTownList.add(rs.getString("USERNAME"));

				final Collection<String> singleTownCollection = singleTownList;
				allTownList.add(singleTownCollection);
			}
		} catch (ClassCastException cce) {
			log.error("add(): ClassCastException " + cce.toString());
			throw cce;
		} catch (SQLException sqlException) {
			log.info("getAll(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll(): Exception " + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allTownList;

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
