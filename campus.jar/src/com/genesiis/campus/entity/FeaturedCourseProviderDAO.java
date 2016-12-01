package com.genesiis.campus.entity;

//20161122 JH c39-add-course-provider CourseProviderDAO created
//20161123 JH c39-add-course-provider add method coding wip
//20161128 JH c39-add-course-provider add method coding wip
//20161129 JH c39-add-course-provider add method coding wip
//20161129 JH c39-add-course-provider CourseProviderDAO class renamed as FeaturedCourseProviderDAO

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.AccountType;

public class FeaturedCourseProviderDAO implements ICrud{
	
	static Logger log = Logger.getLogger(FeaturedCourseProviderDAO.class.getName());

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
		PreparedStatement preparedStatement2 = null;
		int status = 0;
		int generatedKey = 0;
		
		try{
			HashMap map = new HashMap();
			
			CourseProvider courseProvider = new CourseProvider();
			CourseProviderAccount courseProviderAccount = new CourseProviderAccount();
			
			courseProvider =(CourseProvider) map.get("provider");
			courseProviderAccount = (CourseProviderAccount) map.get("account");
			
			
			
			conn = ConnectionManager.getConnection();
			conn.setAutoCommit(false);
			
			/**
			 * provider query used to insert data into course provider table. 
			 */
			String provider = "INSERT INTO [CAMPUS].[COURSEPROVIDER](UNIQUEPREFIX ,SHORTNAME, NAME, DESCRIPTION, GENERALEMAIL,"
			+" COURSEINQUIRYEMAIL, LANDPHONECOUNTRYCODE, LANDPHONEAREACODE ,LANDPHONENO ,LANDPHONE2NO ,"
			+" FAXNO ,MOBILEPHONECOUNTRYCODE, MOBILEPHONENETWORKCODE, MOBILEPHONENO, SPECIALITY ,WEBLINK, FACEBOOKURL, TWITTERURL, MYSPACEURL , LINKEDINURL, INSTAGRAMURL ,"
            +" VIBERNUMBER, WHATSAPPNUMBER, EXPIRATIONDATE, ADDRESS1, ADDRESS2, ADDRESS3, ACCOUNTTYPE, "
            +" HEADOFFICETOWN, ISTUTORRELATED, ISADMINALLOWED, COURSEPROVIDERSTATUS, COURSEPROVIDERTYPE, "
            +" PRINCIPAL, TUTOR, CRTON, CRTBY, MODON, MODBY )"
            +" VALUES ( ?, ?, ? , ? , ?, ?, ? , ?, ?, ?, "
            +" ? ,?, ?, ? , ? , ?, ?, ? , ?, ?, ?, ?, ?, "
            +" ?, ?, ? , ? , ?, ?, ? , ?, ?, ?,?, ?, getDate(), ?, getDate(),? )";
			
			/**
			 * account query used to insert data into course provider account
			 * table. Takes course provider code generated in 'provider' query
			 * as an input to this query.
			 */
			
			String account = "INSERT INTO [CAMPUS].[COURSEPROVIDERACCOUNT](NAME, USERNAME, PASSWORD, DESCRIPTION, ISACTIVE, COURSEPROVIDER,"
					+ " USERTYPE ,CRTON, CRTBY, MODON, MODBY) VALUES(  ?, ?, ?, ?, ?, ?, ?, getDate(), ?, getDate(), ?)";

			preparedStatement = conn.prepareStatement(provider,
					PreparedStatement.RETURN_GENERATED_KEYS);

			Date d1 = new Date();
			java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
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
			preparedStatement.setString(12,courseProvider.getMobilePhoneCountryCode());
			preparedStatement.setString(13, courseProvider.getMobilePhoneNetworkCode());
			preparedStatement.setString(14, courseProvider.getMobilePhoneNumber());
			preparedStatement.setString(15,courseProvider.getSpeciality());
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
			preparedStatement.setInt(28, AccountType.FEATURED_COURSE_PROVIDER.getTypeValue());
			preparedStatement.setInt(29, courseProvider.getHeadOffice());
			preparedStatement.setBoolean(30, courseProvider.isTutorRelated());
			preparedStatement.setBoolean(31, courseProvider.isAdminAllowed());
			preparedStatement.setBoolean(32, false);
			preparedStatement.setBoolean(33, true);
			preparedStatement.setInt(34, courseProvider.getCourseProviderType());
			preparedStatement.setInt(35, courseProvider.getPrincipal());
			preparedStatement.setInt(36, 1);
			preparedStatement.setInt(37, 0);
			preparedStatement.setString(38, "djfkdj");
			preparedStatement.setString(39, "djfkdj");
			
			preparedStatement2 = conn.prepareStatement(account);
			preparedStatement2.setString(1, "Mr. Perera");
			preparedStatement2.setString(2, "Perera");
			preparedStatement2.setString(3, "123");
			preparedStatement2.setString(4, "user description");
			preparedStatement2.setBoolean(5, true);
			//preparedStatement2.setInt(6, courseProviderAccount.getCourseProvider());
			preparedStatement2.setInt(7, 1);
			preparedStatement2.setString(8, "admin");
			preparedStatement2.setString(9, "admin");
			
			status = preparedStatement.executeUpdate();
			log.info(".........." + status);
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			
			if(rs.next()){
				generatedKey = rs.getInt(1);
				status = 1;
				log.info(">>>>>>>>>>>>>>>    " + generatedKey);
				preparedStatement2.setInt(6, generatedKey);
				
			status = preparedStatement2.executeUpdate();
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
			}
			if(preparedStatement2 != null) {
				preparedStatement2.close();
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
