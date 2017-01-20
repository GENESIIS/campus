package com.genesiis.campus.entity;

//20161122 JH c39-add-course-provider CourseProviderDAO created
//20161123 JH c39-add-course-provider add method coding wip
//20161128 JH c39-add-course-provider add method coding wip
//20161129 JH c39-add-course-provider add method coding wip
//20161129 JH c39-add-course-provider CourseProviderDAO class renamed as FeaturedCourseProviderDAO
//20161202 JH c39-add-course-provider add method modified
//20161208 JH c39-add-course-provider error fixed and code ReFactored
//20161209 JH c39-add-course-provider findById method modified: removed query used to get userType
//20161219 JH c39-add-course-provider code review modifications: use generics 
//20161219 JH c39-add-course-provider fixed error in prepared statement
//20161229 JH c39-add-course-provider added queries to insert data into course provider town table
//20170102 JH c39-add-course-provider code modified to fix number format exception in course provider town entity
//20170103 JH c39-add-course-provider town query changed due to course provider town table changes
//20170117 JH c39-add-course-provider implemented DaoHelper class to close resources
//20170118 JH c39-add-course-provider qa modifications: fixed description not added in course provider account

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.entity.model.CourseProviderTown;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.AccountType;
import com.genesiis.campus.validation.UserType;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;

public class FeaturedCourseProviderDAO implements ICrud {

	static Logger log = Logger.getLogger(FeaturedCourseProviderDAO.class
			.getName());

	/**
	 * add method used to create a new featured course provider record.
	 * 
	 * @author JH
	 * @param object
	 *            type of hash map
	 * @return int to indicate success or fail
	 */
	@Override
	public int add(Object object) throws SQLException, Exception {

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		ResultSet rs = null;
		int status = 0;
		int generatedKey = 0;

		try {
			Map map = (HashMap) object;

			CourseProvider courseProvider  = (CourseProvider) map.get("provider");
			CourseProviderAccount courseProviderAccount = (CourseProviderAccount) map.get("account");
			CourseProviderTown courseProviderTown = (CourseProviderTown) map.get("town");

			conn = ConnectionManager.getConnection();
			conn.setAutoCommit(false);

			/**
			 * provider query used to insert data into course provider table.
			 * depending on whether the account is a head office or a branch under a head office, 
			 * the query should be chosen (between branchCourseProvider and mainCourseProvider)
			 */
	
			StringBuilder branchCourseProviderStringBuilder = new StringBuilder("INSERT INTO [CAMPUS].[COURSEPROVIDER]");
			branchCourseProviderStringBuilder.append("(UNIQUEPREFIX ,SHORTNAME, NAME, DESCRIPTION, GENERALEMAIL, COURSEINQUIRYEMAIL, LANDPHONECOUNTRYCODE, LANDPHONEAREACODE, LANDPHONENO ,");
			branchCourseProviderStringBuilder.append("LANDPHONE2NO ,FAXNO ,MOBILEPHONECOUNTRYCODE, MOBILEPHONENETWORKCODE, MOBILEPHONENO, SPECIALITY ,WEBLINK,FACEBOOKURL, TWITTERURL, MYSPACEURL , ");
			branchCourseProviderStringBuilder.append("LINKEDINURL, INSTAGRAMURL ,VIBERNUMBER, WHATSAPPNUMBER, EXPIRATIONDATE, ADDRESS1, ADDRESS2, ADDRESS3, ACCOUNTTYPE,");
			branchCourseProviderStringBuilder.append("ISTUTORRELATED, ISADMINALLOWED, COURSEPROVIDERSTATUS, COURSEPROVIDERTYPE, PRINCIPAL, CRTON, CRTBY, MODON, MODBY )");
			branchCourseProviderStringBuilder.append("VALUES ( ?, ?, ? , ? , ?, ?, ? , ?, ?, ?,? ,?, ?, ? , ? , ?, ?, ? , ?, ?, ?, ?,");
			branchCourseProviderStringBuilder.append("?, ?, ? , ?, ? , ?, ?, ? , ?, ?, ?, ?, getDate(), ?, getDate(),? )");
			
			String branchCourseProvider = branchCourseProviderStringBuilder.toString();

			StringBuilder mainCourseProviderStringBuilder = new StringBuilder("INSERT INTO [CAMPUS].[COURSEPROVIDER]");
			mainCourseProviderStringBuilder.append("(UNIQUEPREFIX ,SHORTNAME, NAME, DESCRIPTION, GENERALEMAIL,COURSEINQUIRYEMAIL, LANDPHONECOUNTRYCODE, LANDPHONEAREACODE, LANDPHONENO ,");
			mainCourseProviderStringBuilder.append("LANDPHONE2NO ,FAXNO ,MOBILEPHONECOUNTRYCODE, MOBILEPHONENETWORKCODE, MOBILEPHONENO, SPECIALITY ,WEBLINK,FACEBOOKURL, TWITTERURL, MYSPACEURL , ");
			mainCourseProviderStringBuilder.append("LINKEDINURL, INSTAGRAMURL ,VIBERNUMBER, WHATSAPPNUMBER, EXPIRATIONDATE, ADDRESS1, ADDRESS2, ADDRESS3, ACCOUNTTYPE,");
			mainCourseProviderStringBuilder.append("ISTUTORRELATED, ISADMINALLOWED, COURSEPROVIDERSTATUS, COURSEPROVIDERTYPE,CRTON, CRTBY, MODON, MODBY )");
			mainCourseProviderStringBuilder.append("VALUES ( ?, ?, ? , ? , ?, ?, ? , ?, ?, ?, ?, ?, ? , ? , ?, ?, ? , ?, ?, ?, ?,");
			mainCourseProviderStringBuilder.append("?, ?, ? , ?, ? , ?, ?, ? , ?, ?, ?, getDate(), ?, getDate(),? )");
			
			String mainCourseProvider = mainCourseProviderStringBuilder.toString();
			
			
			/**
			 * account query used to insert data into course provider account
			 * table. Takes course provider code generated in 'provider' query
			 * as an input to this query.
			 */

			String account = "INSERT INTO [CAMPUS].[COURSEPROVIDERACCOUNT](NAME, USERNAME, PASSWORD, EMAIL, DESCRIPTION, ISACTIVE, COURSEPROVIDER,"
					+ " USERTYPE ,CRTON, CRTBY, MODON, MODBY) VALUES(  ?, ?, ?, ?, ?, ?, ?, ?, getDate(), ?, getDate(), ?)";
			
			String town = "INSERT INTO [CAMPUS].[COURSEPROVIDERTOWN](ISACTIVE, COURSEPROVIDER, TOWN, ADDRESS1, ADDRESS2, ADDRESS3, CRTON, CRTBY, MODON, MODBY)"
					+ " VALUES (?, ?, ?, ?, ?, ?, getDate(), ?, getDate(), ?)";

			
			//check whether the course provider has a head office or not
			if(courseProvider.getPrincipal()==0){//does not have a head office
				preparedStatement = conn.prepareStatement(mainCourseProvider,
						PreparedStatement.RETURN_GENERATED_KEYS);
				
				//set course provider basic details
				preparedStatement.setString(1, courseProvider.getUniquePrefix());
				preparedStatement.setString(2, courseProvider.getShortName());
				preparedStatement.setString(3, courseProvider.getName());
				preparedStatement.setString(4, courseProvider.getDescription());
				preparedStatement.setString(5, courseProvider.getGeneralEmail());
				preparedStatement.setString(6,courseProvider.getCourseInquiryEmail());
				preparedStatement.setString(7,courseProvider.getLandPhoneCountryCode());
				preparedStatement.setString(8,courseProvider.getLandPhoneAreaCode());
				preparedStatement.setString(9, courseProvider.getLandPhoneNo());
				preparedStatement.setString(10, courseProvider.getLandPhpneNo2());
				preparedStatement.setString(11, courseProvider.getFaxNo());
				preparedStatement.setString(12,courseProvider.getMobilePhoneCountryCode());
				preparedStatement.setString(13,courseProvider.getMobilePhoneNetworkCode());
				preparedStatement.setString(14,courseProvider.getMobilePhoneNumber());
				preparedStatement.setString(15, courseProvider.getSpeciality());
				preparedStatement.setString(16, courseProvider.getWeblink());
				preparedStatement.setString(17, courseProvider.getFacebookURL());
				preparedStatement.setString(18, courseProvider.getTwitterURL());
				preparedStatement.setString(19, courseProvider.getMyspaceURL());
				preparedStatement.setString(20, courseProvider.getLinkedinURL());
				preparedStatement.setString(21, courseProvider.getInstagramURL());
				preparedStatement.setString(22, courseProvider.getViberNumber());
				preparedStatement.setString(23, courseProvider.getWhatsappNumber());
				preparedStatement.setDate(24, courseProvider.getExpirationDate());
				preparedStatement.setString(25, courseProvider.getAddress1());
				preparedStatement.setString(26, courseProvider.getAddress2());
				preparedStatement.setString(27, courseProvider.getAddress3());
				preparedStatement.setInt(28, courseProvider.getAccountType());
				preparedStatement.setBoolean(29, courseProvider.isTutorRelated());
				preparedStatement.setBoolean(30, courseProvider.isAdminAllowed());
				preparedStatement.setInt(31,courseProvider.getCourseProviderStatus());
				preparedStatement.setInt(32, courseProvider.getCourseProviderType());
				preparedStatement.setString(33, courseProvider.getCrtBy());
				preparedStatement.setString(34, courseProvider.getModBy());

			}if(courseProvider.getPrincipal() !=0){//has a head office 
				preparedStatement = conn.prepareStatement(mainCourseProvider,
						PreparedStatement.RETURN_GENERATED_KEYS);
				
				//set course provider basic details
				preparedStatement.setString(1, courseProvider.getUniquePrefix());
				preparedStatement.setString(2, courseProvider.getShortName());
				preparedStatement.setString(3, courseProvider.getName());
				preparedStatement.setString(4, courseProvider.getDescription());
				preparedStatement.setString(5, courseProvider.getGeneralEmail());
				preparedStatement.setString(6,courseProvider.getCourseInquiryEmail());
				preparedStatement.setString(7,courseProvider.getLandPhoneCountryCode());
				preparedStatement.setString(8,courseProvider.getLandPhoneAreaCode());
				preparedStatement.setString(9, courseProvider.getLandPhoneNo());
				preparedStatement.setString(10, courseProvider.getLandPhpneNo2());
				preparedStatement.setString(11, courseProvider.getFaxNo());
				preparedStatement.setString(12,courseProvider.getMobilePhoneCountryCode());
				preparedStatement.setString(13,courseProvider.getMobilePhoneNetworkCode());
				preparedStatement.setString(14,courseProvider.getMobilePhoneNumber());
				preparedStatement.setString(15, courseProvider.getSpeciality());
				preparedStatement.setString(16, courseProvider.getWeblink());
				preparedStatement.setString(17, courseProvider.getFacebookURL());
				preparedStatement.setString(18, courseProvider.getTwitterURL());
				preparedStatement.setString(19, courseProvider.getMyspaceURL());
				preparedStatement.setString(20, courseProvider.getLinkedinURL());
				preparedStatement.setString(21, courseProvider.getInstagramURL());
				preparedStatement.setString(22, courseProvider.getViberNumber());
				preparedStatement.setString(23, courseProvider.getWhatsappNumber());
				preparedStatement.setDate(24, courseProvider.getExpirationDate());
				preparedStatement.setString(25, courseProvider.getAddress1());
				preparedStatement.setString(26, courseProvider.getAddress2());
				preparedStatement.setString(27, courseProvider.getAddress3());
				preparedStatement.setInt(28, courseProvider.getAccountType());
				preparedStatement.setBoolean(29, courseProvider.isTutorRelated());
				preparedStatement.setBoolean(30, courseProvider.isAdminAllowed());
				preparedStatement.setInt(31,courseProvider.getCourseProviderStatus());
				preparedStatement.setInt(32, courseProvider.getCourseProviderType());
				preparedStatement.setInt(33, courseProvider.getPrincipal());
				preparedStatement.setString(34, courseProvider.getCrtBy());
				preparedStatement.setString(35, courseProvider.getModBy());

			}


			/**
			 * checks for course provider head office code. if code = 0, means
			 * this course provider is a head office. Therefore we have to assign null
			 * for that value
			 * 
			 */

			// set course provider account details
			preparedStatement2 = conn.prepareStatement(account);
			preparedStatement2.setString(1, courseProviderAccount.getName());
			preparedStatement2.setString(2, courseProviderAccount.getUsername());
			Encryptable passwordEncryptor = new TripleDesEncryptor(courseProviderAccount.getPassword());			
			preparedStatement2.setString(3, passwordEncryptor.encryptSensitiveDataToString());
			preparedStatement2.setString(4, courseProviderAccount.getEmail());
			preparedStatement2.setString(5, courseProviderAccount.getDescription());
			preparedStatement2.setBoolean(6, courseProviderAccount.isActive());
			preparedStatement2.setInt(8, courseProviderAccount.getUserType());
			preparedStatement2.setString(9, courseProviderAccount.getCrtBy());
			preparedStatement2.setString(10, courseProviderAccount.getModBy());
			
			
			//set course provider town details
			//set course provider town details
			preparedStatement3 = conn.prepareStatement(town);
			preparedStatement3.setBoolean(1, courseProviderTown.isActive());
			preparedStatement3.setLong(3, courseProviderTown.getTown());
			preparedStatement3.setString(4, courseProviderTown.getAddress1());
			preparedStatement3.setString(5, courseProviderTown.getAddress2());
			preparedStatement3.setString(6, courseProviderTown.getAddress3());
			preparedStatement3.setString(7, courseProviderTown.getCrtBy());
			preparedStatement3.setString(8, courseProviderTown.getModBy());

			status = preparedStatement.executeUpdate();

			rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				generatedKey = rs.getInt(1);
				status = 1;
				
				preparedStatement2.setInt(7, generatedKey);

				status = preparedStatement2.executeUpdate();
				
				if(status > 0){
					
					preparedStatement3.setInt(2, generatedKey);
					status = preparedStatement3.executeUpdate();
				}
			}

			conn.commit();

		} catch (SQLException sqlException) {
			log.error("add method SQL Exception " + sqlException.toString());
			conn.rollback();
			throw sqlException;

		} catch (Exception exception) {
			log.error("add method Exception " + exception.toString());
			conn.rollback();
			throw exception;
		} finally {
			conn.setAutoCommit(true);
			DaoHelper.closeResultSet(rs);
			DaoHelper.closeStatement(preparedStatement3);
			DaoHelper.closeStatement(preparedStatement2);
			DaoHelper.closeStatement(preparedStatement);
			DaoHelper.closeConnection(conn);
			
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
