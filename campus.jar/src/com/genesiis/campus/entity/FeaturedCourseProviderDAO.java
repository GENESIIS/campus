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
//20170125 JH c39-add-course-provider assign course provider address details from course provider entity for preparedStatement3
//20170201 JH c39-add-course-provider arranged imports according to the style guide
//20170202 JH c39-add-course-provider query string account modified: select user type code selected inside the insert query
//20170202 JH c39-add-course-provider removed repeating statements used to set parameters to preparedStatement
//20170203 JH c39-add-course-provider mx fixes: modified the error log statement
//20170207 JH c141-add-course-provider-issue-improvements DAO class query refactor to implement stored procedure wip
//20170209 JH c141-add-course-provider-issue-improvements removed unwanted prepared statement variables and imports
//20170221 JH c141-add-course-provider-issue-improvements add(): added method comments and finally block modified to use DaoHelper.cleanup()
//20170222 JH c141-add-course-provider-issue-improvements add(): queries updated to match stored procedure changes: check user type before inserting data

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.entity.model.CourseProviderTown;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.AccountType;
import com.genesiis.campus.validation.UserType;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;

import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * FeaturedCourseProviderDAO class used to handle basic operations of the featured
 * course provider
 * @author JH
 *
 */
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
		
		CallableStatement callableStatement = null;
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

			/*
			 * procedureCallMainBranch will insert data of a course provider main branch. 
			 * This will insert data into course provider table, course provider account table
			 * and course provider town table. 
			 * 
			 * This will not insert data into principal column
			 */
			String procedureCallMainBranch = "{call campus.add_featured_provider_main_branch(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			/*
			 * procedureCallMainBranch will creates a course provider sub branch account.
			 * This will insert data into course provider table, course provider account table
			 * and course provider town table
			 * 
			 * This will insert data into principal column
			 */
			String procedureCallSubBranch = "{call campus.add_featured_provider_sub_branch(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			//check whether the course provider has a head office or not
			if(courseProvider.getPrincipal()==0){//does not have a head office
				callableStatement = conn.prepareCall(procedureCallMainBranch);
				
			}
			if (courseProvider.getPrincipal() != 0) {// has a head office
				callableStatement = conn.prepareCall(procedureCallSubBranch);

				callableStatement.setInt(48, courseProvider.getPrincipal());
			}

			// set common parameter values to the callable statement
			Encryptable passwordEncryptor = new TripleDesEncryptor(
					courseProviderAccount.getPassword());

			callableStatement.setString(1, courseProvider.getUniquePrefix());
			callableStatement.setString(2, courseProvider.getShortName());
			callableStatement.setString(3, courseProvider.getName());
			callableStatement.setString(4, courseProvider.getDescription());
			callableStatement.setString(5, courseProvider.getGeneralEmail());
			callableStatement.setString(6, courseProvider.getCourseInquiryEmail());
			callableStatement.setString(7, courseProvider.getLandPhoneCountryCode());
			callableStatement.setString(8, courseProvider.getLandPhoneAreaCode());
			callableStatement.setString(9, courseProvider.getLandPhoneNo());
			callableStatement.setString(10, courseProvider.getLandPhpneNo2());
			callableStatement.setString(11, courseProvider.getFaxNo());
			callableStatement.setString(12, courseProvider.getMobilePhoneNetworkCode());
			callableStatement.setString(13, courseProvider.getMobilePhoneNumber());
			callableStatement.setString(14, courseProvider.getSpeciality());
			callableStatement.setString(15, courseProvider.getWeblink());
			callableStatement.setString(16, courseProvider.getFacebookURL());
			callableStatement.setString(17, courseProvider.getTwitterURL());
			callableStatement.setString(18, courseProvider.getMyspaceURL());
			callableStatement.setString(19, courseProvider.getLinkedinURL());
			callableStatement.setString(20, courseProvider.getInstagramURL());
			callableStatement.setString(21, courseProvider.getViberNumber());
			callableStatement.setString(22, courseProvider.getWhatsappNumber());
			callableStatement.setDate(23, courseProvider.getExpirationDate());
			callableStatement.setString(24, courseProvider.getAddress1());
			callableStatement.setString(25, courseProvider.getAddress2());
			callableStatement.setString(26, courseProvider.getAddress3());
			callableStatement.setInt(27, courseProvider.getAccountType());
			callableStatement.setBoolean(28, courseProvider.isTutorRelated());
			callableStatement.setBoolean(29,  courseProvider.isAdminAllowed());
			callableStatement.setInt(30, courseProvider.getCourseProviderStatus());
			callableStatement.setInt(31, courseProvider.getCourseProviderType());
			callableStatement.setString(32, courseProvider.getCrtBy());
			callableStatement.setString(33, courseProviderAccount.getName());
			callableStatement.setString(34, courseProviderAccount.getUsername());
			callableStatement.setString(35,  passwordEncryptor.encryptSensitiveDataToString());
			callableStatement.setString(36, courseProviderAccount.getEmail());
			callableStatement.setString(37, courseProviderAccount.getDescription());
			callableStatement.setBoolean(38, courseProviderAccount.isActive());
			callableStatement.setString(39, UserType.FEATURED_COURSE_PROVIDER.getUserType());
			callableStatement.setString(40, courseProviderAccount.getContactNumber());
			callableStatement.setBoolean(41, courseProviderTown.isActive());
			callableStatement.setLong(42, courseProviderTown.getTown());
			callableStatement.setString(43, courseProviderTown.getContactNumber());
			callableStatement.setBoolean(44, courseProviderTown.isActive());
			callableStatement.setInt(45, courseProvider.getHeadOffice());
			callableStatement.setInt(46, 0);
			callableStatement.registerOutParameter(47, java.sql.Types.INTEGER);

			callableStatement.executeUpdate();
			
			generatedKey = callableStatement.getInt(47);			

			conn.commit();

		} catch (SQLException sqlException) {
			log.error("add SQL Exception " + sqlException.toString());
			conn.rollback();
			throw sqlException;

		} catch (Exception exception) {
			log.error("add Exception " + exception.toString());
			conn.rollback();
			throw exception;
		} finally {
			conn.setAutoCommit(true);
			DaoHelper.cleanup(conn, callableStatement, rs);
			
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