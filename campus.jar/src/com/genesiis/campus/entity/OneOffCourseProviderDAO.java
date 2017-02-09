package com.genesiis.campus.entity;

//20161129 JH c39-add-course-provider OneOffCourseProviderDAO.java DAO class created 
//20161130 JH c39-add-course-provider add method coding wip
//20161202 JH c39-add-course-provider add method code modified
//20161206 JH c39-add-course-provider add missing parameters and remove static values
//20170103 JH c39-add-course-provider added queries to insert course provider town data
//20170103 JH c39-add-course-provider town query changed due to course provider town table changes
//20170117 JH c39-add-course-provider implemented DaoHelper class to close resources
//20170201 JH c39-add-course-provider arranged imports according to the style guide
//20170203 JH c39-add-course-provider mx fixes: modified the error log statement
//20170209 JH c141-add-course-provider-issue-improvements code refactore to implement stored procedure call

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.entity.model.CourseProviderTown;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.AccountType;
import com.genesiis.campus.validation.UserType;

import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
		ResultSet rs = null;
		int status = 0;
		int generatedKey = 0;
		CallableStatement callableStatement = null;
		
		try{
			Map map = (HashMap) object;		
			CourseProvider courseProvider  = (CourseProvider) map.get("provider");
			CourseProviderTown courseProviderTown = (CourseProviderTown) map.get("town");

			conn = ConnectionManager.getConnection();
			conn.setAutoCommit(false);
			
			/**
			 * provider query used to insert data into course provider table. 
			 */
			
			String procedureCall = "{call campus.add_featured_provider_main_branch(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			callableStatement = conn.prepareCall(procedureCall);

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
			callableStatement.setBoolean(33, courseProviderTown.isActive());
			callableStatement.setLong(34, courseProviderTown.getTown());
			callableStatement.setString(35, courseProviderTown.getContactNumber());
			callableStatement.setBoolean(36, courseProviderTown.isActive());
			callableStatement.setInt(37, courseProvider.getHeadOffice());
			callableStatement.registerOutParameter(38, java.sql.Types.INTEGER);

			callableStatement.executeUpdate();
			
			generatedKey = callableStatement.getInt(46);
			
			conn.commit();
			
		}catch(SQLException sqlException){
			log.error("add SQL Exception " + sqlException.toString());
			conn.rollback();
			throw sqlException;
			
		}catch (Exception exception) {
			log.error("add Exception " + exception.toString());
			conn.rollback();
			throw exception;
		}finally {
			conn.setAutoCommit(true);
			DaoHelper.closeResultSet(rs);
			DaoHelper.closeStatement(callableStatement);
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
