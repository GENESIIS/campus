package com.genesiis.campus.entity;

//20161129 JH c39-add-course-provider OneOffCourseProviderDAO.java DAO class created 
//20161130 JH c39-add-course-provider add method coding wip
//20161202 JH c39-add-course-provider add method code modified
//20161206 JH c39-add-course-provider add missing parameters and remove static values
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.genesiis.campus.command.CmdAddOneOffProvider;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.entity.model.CourseProviderTown;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.AccountType;

public class OneOffCourseProviderDAO implements ICrud{
	
	static Logger log = Logger.getLogger(OneOffCourseProviderDAO.class.getName());

	/**
	 * add method used to create a new one off course provider record. 
	 * @author JH
	 * @param object type of hash map
	 * @return int to indicate success or fail
	 */
	@Override
	public int add(Object object) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		ResultSet rs = null;
		int status = 0;
		int generatedKey = 0;
		
		try{
			Map map = (HashMap) object;
			
			CourseProvider courseProvider  = (CourseProvider) map.get("provider");
			CourseProviderTown courseProviderTown = (CourseProviderTown) map.get("town");

			conn = ConnectionManager.getConnection();
			conn.setAutoCommit(false);
			
			/**
			 * provider query used to insert data into course provider table. 
			 */
//			String provider = "INSERT INTO [CAMPUS].[COURSEPROVIDER](UNIQUEPREFIX ,SHORTNAME, NAME, DESCRIPTION, GENERALEMAIL,"
//			+" COURSEINQUIRYEMAIL, LANDPHONECOUNTRYCODE, LANDPHONEAREACODE ,LANDPHONENO ,LANDPHONE2NO ,"
//			+" FAXNO ,MOBILEPHONECOUNTRYCODE, MOBILEPHONENETWORKCODE, MOBILEPHONENO, SPECIALITY ,WEBLINK, FACEBOOKURL, TWITTERURL, MYSPACEURL , LINKEDINURL, INSTAGRAMURL ,"
//            +" VIBERNUMBER, WHATSAPPNUMBER, EXPIRATIONDATE, ADDRESS1, ADDRESS2, ADDRESS3, ACCOUNTTYPE, "
//            +" HEADOFFICETOWN, ISTUTORRELATED, ISADMINALLOWED, COURSEPROVIDERSTATUS, COURSEPROVIDERTYPE, "
//            +" PRINCIPAL, TUTOR, CRTON, CRTBY, MODON, MODBY )"
//            +" VALUES ( ?, ?, ? , ? , ?, ?, ? , ?, ?, ?, "
//            +" ? ,?, ?, ? , ? , ?, ?, ? , ?, ?, ?, ?, ?, "
//            +" ?, ?, ? , ? , ?, ?, ? , ?, ?, ?,?, ?, getDate(), ?, getDate(),? )";
			
			StringBuilder stringBuilder = new StringBuilder("INSERT INTO [CAMPUS].[COURSEPROVIDER]");
			stringBuilder.append("(UNIQUEPREFIX ,SHORTNAME, NAME, DESCRIPTION, GENERALEMAIL,COURSEINQUIRYEMAIL, LANDPHONECOUNTRYCODE, LANDPHONEAREACODE, LANDPHONENO ,");
			stringBuilder.append("LANDPHONE2NO ,FAXNO ,MOBILEPHONECOUNTRYCODE, MOBILEPHONENETWORKCODE, MOBILEPHONENO, SPECIALITY ,WEBLINK,FACEBOOKURL, TWITTERURL, MYSPACEURL , ");
			stringBuilder.append("LINKEDINURL, INSTAGRAMURL ,VIBERNUMBER, WHATSAPPNUMBER, EXPIRATIONDATE, ADDRESS1, ADDRESS2, ADDRESS3, ACCOUNTTYPE, ");
			stringBuilder.append("ISTUTORRELATED, ISADMINALLOWED, COURSEPROVIDERSTATUS, COURSEPROVIDERTYPE,PRINCIPAL, CRTON, CRTBY, MODON, MODBY )");
			stringBuilder.append("VALUES ( ?, ?, ? , ? , ?, ?, ? , ?, ?, ?,? ,?, ?, ? , ? , ?, ?, ? , ?, ?, ?, ?,");
			stringBuilder.append("?, ?, ? , ? , ?, ?, ? , ?, ?, ?, ?, getDate(), ?, getDate(),? )");
			
			String provider = stringBuilder.toString();
			
			String town = "INSERT INTO [CAMPUS].[COURSEPROVIDERTOWN](ISACTIVE, COURSEPROVIDER, TOWN, CRTON, CRTBY, MODON, MODBY)"
					+ " VALUES (?, ?, ?, getDate(), ?, getDate(), ?)";
			
			preparedStatement = conn.prepareStatement(provider,
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			//set course provider details to provider query
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
			preparedStatement.setString(15, courseProvider.getSpeciality());
			preparedStatement.setString(16, courseProvider.getWeblink());
			preparedStatement.setString(17, courseProvider.getFacebookURL());
			preparedStatement.setString(18, courseProvider.getTwitterURL());
			preparedStatement.setString(19, courseProvider.getMyspaceURL());
			preparedStatement.setString(20, courseProvider.getLinkedinURL());
			preparedStatement.setString(21, courseProvider.getInstagramURL());
			preparedStatement.setString(22, courseProvider.getViberNumber());
			preparedStatement.setString(23,courseProvider.getWhatsappNumber());
			preparedStatement.setDate(24, courseProvider.getExpirationDate());
			preparedStatement.setString(25, courseProvider.getAddress1());
			preparedStatement.setString(26, courseProvider.getAddress2());
			preparedStatement.setString(27, courseProvider.getAddress3());
			preparedStatement.setInt(28, courseProvider.getAccountType());
		//	preparedStatement.setInt(29, courseProvider.getHeadOffice());
			preparedStatement.setBoolean(29, courseProvider.isTutorRelated());
			preparedStatement.setBoolean(30, courseProvider.isAdminAllowed());
			preparedStatement.setInt(31,courseProvider.getCourseProviderStatus());
			preparedStatement.setInt(32, courseProvider.getCourseProviderType());
			preparedStatement.setInt(33, courseProvider.getPrincipal());
			preparedStatement.setString(34, courseProvider.getCrtBy());
			preparedStatement.setString(35, courseProvider.getModBy());
			
			
			//set course provider town details
			preparedStatement2 = conn.prepareStatement(town);
			preparedStatement2.setBoolean(1, courseProviderTown.isActive());
			preparedStatement2.setInt(3, courseProviderTown.getTown());
			preparedStatement2.setString(4, courseProviderTown.getCrtBy());
			preparedStatement2.setString(5, courseProviderTown.getModBy());
			
			status = preparedStatement.executeUpdate();
			log.info(".........." + status);

			rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				generatedKey = rs.getInt(1);
				log.info(">>>>>>>>>>>>>>>    " + generatedKey);

				if (status > 0) {
					preparedStatement2.setInt(2, generatedKey);
					status = preparedStatement2.executeUpdate();
				}

			}

			conn.commit();
			
		}catch(SQLException sqlException){
			log.error("add method SQL Exception " + sqlException.toString());
			conn.rollback();
			throw sqlException;
			
		}catch (Exception exception) {
			log.error("add method Exception " + exception.toString());
			conn.rollback();
			throw exception;
		}finally{
			if(conn != null){
				conn.setAutoCommit(true);
				conn.close();
			}
			if(preparedStatement != null){
				preparedStatement.close();
			}if(rs != null){
				rs.close();
			}
		}
		return generatedKey;
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
