package com.genesiis.campus.entity;

//20161121 MM c25-student-login-create-dashboard-MP-mm INIT - Initialised file
//20161122 MM c25-student-login-create-dashboard-MP-mm Added code to retrieve more columns from the result set
//20161122 MM c25-student-login-create-dashboard-MP-mm Fixed logger class import issue
//20161205 PN c26-add-student-details: update(Connection con, Object object) method to update student personal details. 
//20161208 PN CAM-26: add-student-details: modified findById() method exception handling logger messages.
//20161214 PN CAM-28: findById() method modified. SQL query and data list.
//20160103 PN CAM-28: added JDBC property closing statements to the finally block.
//20170105 PN CAM-28: edit user information: modified DAO method coding modified with improved connection property management.
//20170105 PN CAM-28: edit user information: modified update(Object object, Connection con) method coding.
//20170106 PN CAM-28: improved Connection property handeling inside finally{} block. 
//20170106 PN CAM-28: SQL query modified to takeISACTIVE status from ApplicationStatus ENUM. 
//20170106 PN CAM-28: Object casting code moved into try{} block in applicable methods().
//20170106 PN CAM-28: SQL query modified in findById(Object code) method.
//20170109 PN CAM-28: Added character replacement for replace ',' in findById() method.
//20170309 PN CAM-150: DAO methods are modified to access AddressLine1, AddressLine2 and AddressLine3 values separately.


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;

import org.apache.log4j.Logger;

public class StudentDAO implements ICrud {

	static Logger Log = Logger.getLogger(StudentDAO.class.getName());

	@Override
	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object) throws SQLException, Exception {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int isUpdated = 0;

		try {
			Student student = (Student) object;
			conn = ConnectionManager.getConnection();
			String query ="UPDATE [CAMPUS].[STUDENT] SET [FIRSTNAME] = ?, [MIDDLENAME] = ?, "
					+ "[LASTNAME] = ?, [DATEOFBIRTH] = ?, [GENDER] = ?, [EMAIL] = ?, [LANDPHONECOUNTRYCODE] = ?, "
					+ "[LANDPHONENO] = ?, [MOBILEPHONECOUNTRYCODE] = ?, [MOBILEPHONENETWORKCODE] = ?, [MOBILEPHONENO] = ?, "
					+ "[DESCRIPTION] = ?, [FACEBOOKURL] = ?, [TWITTERURL] = ?, [MYSPACEURL] = ?, [LINKEDINURL] = ?, "
					+ "[INSTAGRAMURL] = ?, [VIBERNUMBER] = ?, [WHATSAPPNUMBER] = ?, [ADDRESS1] = ?, [ADDRESS2] = ?, [ADDRESS3] = ?, [TOWN] = ?, "
					+ "[MODON]=(getdate()), [MODBY] = ? "
					+ "WHERE [CODE] = ?;";

			stmt = conn.prepareStatement(query);	
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getMiddleName());
			stmt.setString(3, student.getLastName());
			stmt.setDate(4, student.getDateOfBirth());
			stmt.setInt(5, student.getGender());
			stmt.setString(6, student.getEmail());
			stmt.setString(7, student.getLandPhoneCountryCode());
			stmt.setString(8, student.getLandPhoneNo());
			stmt.setString(9, student.getLandPhoneCountryCode());
			stmt.setString(10, student.getMobilePhoneNo().substring(0, 3));
			stmt.setString(11, student.getMobilePhoneNo().substring(3, student.getMobilePhoneNo().length()-1));
			stmt.setString(12, student.getDescription());
			stmt.setString(13, student.getFacebookUrl());
			stmt.setString(14, student.getTwitterUrl());
			stmt.setString(15, student.getMySpaceUrl());
			stmt.setString(16, student.getLinkedInUrl());
			stmt.setString(17, student.getInstagramUrl());
			stmt.setString(18, student.getViberNumber());
			stmt.setString(19, student.getWhatsAppNumber());
			stmt.setString(20, student.getAddress1());
			stmt.setString(21, student.getAddress2());
			stmt.setString(22, student.getAddress3());
			stmt.setString(23, student.getTown());
			stmt.setString(24, student.getModBy());
			stmt.setInt(25, student.getCode());					
			isUpdated = stmt.executeUpdate();
		} catch (SQLException sqlException) {
			Log.error("update(Object object): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			Log.error("update(Object object): E " + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return isUpdated;
	}

	@Override
	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		final Collection<Collection<String>> studentDetailsCollectionList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Student student = (Student) code;
			int studentCode = student.getCode();

			String query = "SELECT st.[CODE],[USERNAME],[PASSWORD],[INDEXNO],[FIRSTNAME],[MIDDLENAME],[LASTNAME],[DATEOFBIRTH],[GENDER],"
					+ "[EMAIL],[TYPE],[LANDPHONECOUNTRYCODE],[LANDPHONEAREACODE],[LANDPHONENO],[MOBILEPHONECOUNTRYCODE],[MOBILEPHONENETWORKCODE],"
					+ "[MOBILEPHONENO],[DESCRIPTION],[FACEBOOKURL],[TWITTERURL],[MYSPACEURL],[LINKEDINURL],[INSTAGRAMURL],[VIBERNUMBER],[WHATSAPPNUMBER],"
					+ "[ADDRESS1],[ADDRESS2],[ADDRESS3],[TOWN],[ACCOUNTTYPE], co.[NAME]  AS [COUNTRYNAME], tw.[NAME]  AS [TOWNNAME] "
					+ "FROM [CAMPUS].[STUDENT] st "
					+ "JOIN [CAMPUS].[COUNTRY2] co ON st.[LANDPHONECOUNTRYCODE] = co.[CODE] "
					+ "JOIN [CAMPUS].[TOWN] tw ON st.[TOWN] = tw.[CODE] WHERE st.[CODE] = ?;";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, studentCode);
			rs = ps.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleList = new ArrayList<String>();
				singleList.add(rs.getString("CODE"));
				singleList.add(rs.getString("USERNAME"));
				singleList.add(rs.getString("PASSWORD"));
				singleList.add(rs.getString("INDEXNO"));
				singleList.add(rs.getString("FIRSTNAME"));
				singleList.add(rs.getString("MIDDLENAME"));
				singleList.add(rs.getString("LASTNAME"));
				singleList.add(rs.getString("DATEOFBIRTH"));
				singleList.add(rs.getString("GENDER"));
				singleList.add(rs.getString("EMAIL"));
				singleList.add(rs.getString("TYPE"));
				singleList.add(rs.getString("LANDPHONECOUNTRYCODE"));
				singleList.add(rs.getString("LANDPHONEAREACODE"));
				singleList.add(rs.getString("LANDPHONENO"));
				singleList.add(rs.getString("MOBILEPHONECOUNTRYCODE"));
				singleList.add(rs.getString("MOBILEPHONENETWORKCODE"));
				singleList.add(rs.getString("MOBILEPHONENO"));
				singleList.add(rs.getString("DESCRIPTION"));
				singleList.add(rs.getString("FACEBOOKURL"));
				singleList.add(rs.getString("TWITTERURL"));
				singleList.add(rs.getString("MYSPACEURL"));
				singleList.add(rs.getString("LINKEDINURL"));
				singleList.add(rs.getString("INSTAGRAMURL"));
				singleList.add(rs.getString("VIBERNUMBER"));
				singleList.add(rs.getString("WHATSAPPNUMBER"));
				singleList.add(rs.getString("ADDRESS1"));
				singleList.add(rs.getString("ADDRESS2"));
				singleList.add(rs.getString("ADDRESS3"));
				singleList.add(rs.getString("TOWN"));
				singleList.add(rs.getString("ACCOUNTTYPE"));
				singleList.add(rs.getString("COUNTRYNAME"));
				singleList.add(rs.getString("TOWNNAME"));
				final Collection<String> singleCollection = singleList;
				studentDetailsCollectionList.add(singleCollection);
			}

		} catch (SQLException sqlException) {
			Log.error("findById(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			Log.error("findById(): E " + e.toString());
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return studentDetailsCollectionList;
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
	public int update(Object object, Connection con) throws SQLException,
			Exception {	
		Connection conn = null;
		PreparedStatement stmt = null;
		int isUpdated = 0;

		try {
			Student student = (Student) object;
			conn = con;
			String query ="UPDATE [CAMPUS].[STUDENT] SET [FIRSTNAME] = ?, [MIDDLENAME] = ?, "
					+ "[LASTNAME] = ?, [DATEOFBIRTH] = ?, [GENDER] = ?, [EMAIL] = ?, [LANDPHONECOUNTRYCODE] = ?, "
					+ "[LANDPHONENO] = ?, [MOBILEPHONECOUNTRYCODE] = ?, [MOBILEPHONENETWORKCODE] = ?, [MOBILEPHONENO] = ?, "
					+ "[DESCRIPTION] = ?, [FACEBOOKURL] = ?, [TWITTERURL] = ?, [MYSPACEURL] = ?, [LINKEDINURL] = ?, "
					+ "[INSTAGRAMURL] = ?, [VIBERNUMBER] = ?, [WHATSAPPNUMBER] = ?, [ADDRESS1] = ?, [ADDRESS2] = ?, [ADDRESS3] = ?, [TOWN] = ?, "
					+ "[MODON]=(getdate()), [MODBY] = ? "
					+ "WHERE [CODE] = ?;";

			stmt = conn.prepareStatement(query);	
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getMiddleName());
			stmt.setString(3, student.getLastName());
			stmt.setDate(4, student.getDateOfBirth());
			stmt.setInt(5, student.getGender());
			stmt.setString(6, student.getEmail());
			stmt.setString(7, student.getLandPhoneCountryCode());
			stmt.setString(8, student.getLandPhoneNo());
			stmt.setString(9, student.getLandPhoneCountryCode());
			
			//This will be change later once a proper way confirmed to check the phone number, and network code.
			if(student.getMobilePhoneNo().length() > 4){
				stmt.setString(10, student.getMobilePhoneNo().substring(0, 3));
				stmt.setString(11, student.getMobilePhoneNo().substring(3, student.getMobilePhoneNo().length()-1));
			}else{
				stmt.setString(10, student.getMobilePhoneNo());
				stmt.setString(11, student.getMobilePhoneNo());
			}
			stmt.setString(12, student.getDescription());
			stmt.setString(13, student.getFacebookUrl());
			stmt.setString(14, student.getTwitterUrl());
			stmt.setString(15, student.getMySpaceUrl());
			stmt.setString(16, student.getLinkedInUrl());
			stmt.setString(17, student.getInstagramUrl());
			stmt.setString(18, student.getViberNumber());
			stmt.setString(19, student.getWhatsAppNumber());
			stmt.setString(20, student.getAddress1());
			stmt.setString(21, student.getAddress2());
			stmt.setString(22, student.getAddress3());
			stmt.setString(23, student.getTown());
			stmt.setString(24, student.getModBy());
			stmt.setInt(25, student.getCode());				
			isUpdated = stmt.executeUpdate();
		} catch (SQLException sqlException) {
			Log.error("update(Object object): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			Log.error("update(Object object): E " + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return isUpdated;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}