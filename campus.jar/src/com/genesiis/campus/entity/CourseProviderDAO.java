package com.genesiis.campus.entity;

//20161122 JH c39-add-course-provider CourseProviderDAO created
//20161123 JH c39-add-course-provider add method code modified
//20161128 JH c39-add-course-provider add method code modified

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.util.ConnectionManager;
import com.sun.org.apache.bcel.internal.generic.CPInstruction;

public class CourseProviderDAO implements ICrud{
	
	static org.apache.log4j.Logger log = Logger.getLogger(CourseProviderDAO.class.getName());

	/**
	 * add method used to create a new featured course provider record. 
	 * @author JH
	 * @param object type of hash map
	 * @return int to indicate success or fail
	 */
	@Override
	public int add(Object object) throws SQLException, Exception {
	
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		int status = 0;
		
		try{
			HashMap map = new HashMap();
			
			CourseProvider courseProvider = new CourseProvider();
			CourseProviderAccount courseProviderAccount = new CourseProviderAccount();
			
			courseProvider =(CourseProvider) map.get("provider");
			courseProviderAccount = (CourseProviderAccount) map.get("account");
			
			
			
			conn = ConnectionManager.getConnection();
			
			String query = "INSERT INTO [CAMPUS].[COURSEPROVIDER](UNIQUEPREFIX ,SHORTNAME, NAME, DESCRIPTION, GENERALEMAIL,"
			+" COURSEINQUIRYEMAIL, LANDPHONECOUNTRYCODE, LANDPHONEAREACODE ,LANDPHONENO ,LANDPHONE2NO ,"
			+" FAXNO ,MOBILEPHONECOUNTRYCODE, MOBILEPHONENETWORKCODE, MOBILEPHONENO, HEADERIMAGEPATH,"
			+" LOGOIMAGEPATH, SPECIALITY ,WEBLINK, FACEBOOKURL, TWITTERURL, MYSPACEURL , LINKEDINURL, INSTAGRAMURL ,"
            +" VIBERNUMBER, WHATSAPPNUMBER, EXPIRATIONDATE, ADDRESS1, ADDRESS2, ADDRESS3, ACCOUNTTYPE, "
            +" HEADOFFICETOWN, ISTUTORRELATED, ISADMINALLOWED, COURSEPROVIDERSTATUS, COURSEPROVIDERTYPE, "
            +" PRINCIPAL, TUTOR, CRTON, CRTBY, MODON, MODBY )"
            +" VALUES ( ?, ?, ? , ? , ?, ?, ? , ?, ?, ?, "
            +" ? ,?, ?, ? , ? , ?, ?, ? , ?, ?, ?, ?, ?, "
            +" ?, ?, ? , ? , ?, ?, ? , ?, ?, ?,?, ?, ?, ?, getDate(), ?, getDate(),? )";
			
			
			String query2 ="";
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setString(1, courseProvider.getUniquePrefix());
			preparedStatement.setString(2, courseProvider.getShortName());
			preparedStatement.setString(3, courseProvider.getName());
			preparedStatement.setString(4, courseProvider.getDescription());
			preparedStatement.setString(5, courseProvider.getGeneralEmail());
			preparedStatement.setString(6, courseProvider.getCourseInquiryEmail());
			preparedStatement.setString(7, courseProvider.getLandPhoneCountryCode());
			preparedStatement.setString(8, courseProvider.getLandPhoneAreaCode());
			preparedStatement.setString(9, courseProvider.getLandPhoneNo());
			preparedStatement.setString(10, courseProvider.getLandPhpneNo2());
			preparedStatement.setString(11, courseProvider.getFaxNo());
			preparedStatement.setString(12, courseProvider.getMobilePhoneCountryCode());
			preparedStatement.setString(13, courseProvider.getMobilePhoneNetworkCode());
			preparedStatement.setString(14, courseProvider.getMobilePhoneNumber());
			preparedStatement.setString(15, courseProvider.getHeaderImagePath());
			preparedStatement.setString(16, courseProvider.getLogoImagePath());
			preparedStatement.setString(17, courseProvider.getSpeciality());
			preparedStatement.setString(18, courseProvider.getWeblink());
			preparedStatement.setString(19, courseProvider.getFacebookURL());
			preparedStatement.setString(20, courseProvider.getTwitterURL());
			preparedStatement.setString(21, courseProvider.getMyspaceURL());
			preparedStatement.setString(22, courseProvider.getLinkedinURL());
			preparedStatement.setString(23, courseProvider.getInstagramURL());
			preparedStatement.setString(24, courseProvider.getViberNumber());
			preparedStatement.setString(25, courseProvider.getWhatsappNumber());
			preparedStatement.setDate(26, courseProvider.getExpirationDate());
			preparedStatement.setString(27, courseProvider.getAddress1());
			preparedStatement.setString(28, courseProvider.getAddress2());
			preparedStatement.setString(29, courseProvider.getAddress3());
			preparedStatement.setInt(30, courseProvider.getAccountType());
			preparedStatement.setInt(31, courseProvider.getHeadOffice());
			preparedStatement.setBoolean(32, courseProvider.isTutorRelated());
			preparedStatement.setBoolean(33, courseProvider.isAdminAllowed());
			preparedStatement.setInt(34, courseProvider.getCourseProviderStatus());
			preparedStatement.setInt(35, courseProvider.getCourseProviderType());
			preparedStatement.setInt(36, courseProvider.getPrincipal());
			preparedStatement.setInt(37, courseProvider.getTutor());
			preparedStatement.setString(38, "admin");
			preparedStatement.setString(39, "admin");
			
			
			status = preparedStatement.executeUpdate();
			
			
		}catch(SQLException sqlException){
			log.error("add method SQL Exception " + sqlException.toString());
			throw sqlException;
			
		}catch (Exception exception) {
			log.error("add method Exception " + exception.toString());
			throw exception;
		}finally{
			
		}
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
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
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

}
