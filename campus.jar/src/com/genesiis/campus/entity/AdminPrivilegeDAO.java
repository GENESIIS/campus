package com.genesiis.campus.entity;
//20170405 AS c23-admin-login-logout-function-as - AdminPrivilegeDAO created 
//20170405 AS c23-admin-login-logout-function-as - adminPrivileges()  coding WIP

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.model.Admin;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.ApplicationStatus;



public class AdminPrivilegeDAO implements ICrud{

	@Override
	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
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
	
	/**
	 * 
	 * @param code
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	
	public Collection<String> adminPrivileges(Object code) throws SQLException, Exception{
		
		Connection conn = null;
		String query = "SELECT INTERFACE.URL , BUTTONACTION.ACTION FROM CAMPUS.USERTYPE INNER JOIN CAMPUS.PRIVILEGE ON USERTYPE.CODE = PRIVILEGE.USERTYPE INNER JOIN CAMPUS.INTERFACE ON PRIVILEGE.INTERFACE = INTERFACE.CODE INNER JOIN CAMPUS.BUTTONACTION ON BUTTONACTION.INTERFACE = INTERFACE.CODE WHERE BUTTONACTION.ISACTIVE=? AND INTERFACE.ISACTIVE=? AND PRIVILEGE.ISACTIVE=? AND USERTYPESTRING =?";
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<String> privilegeList = null;
		try {
			Admin admin  = (Admin) code;
			conn = ConnectionManager.getConnection();
			preparedStatement =conn.prepareStatement(query); 
			
			preparedStatement.setString(1, Integer.toString(ApplicationStatus.ACTIVE.getStatusValue()));
			preparedStatement.setString(2, Integer.toString(ApplicationStatus.ACTIVE.getStatusValue()));
			preparedStatement.setString(3, Integer.toString(ApplicationStatus.ACTIVE.getStatusValue()));
			preparedStatement.setString(4, admin.getUserTypeString());

			rs = preparedStatement.executeQuery();

			privilegeList = new ArrayList<String>();
			while (rs.next()) {

				privilegeList.add(rs.getString(1));
				privilegeList.add(rs.getString(2));
			}

		} catch (SQLException e) {
			log.info("adminPrivileges SQLException : " + e);
			throw e;

		} catch (Exception e) {
			log.info("adminPrivileges Exception : " + e);
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

		return privilegeList;
	}
	

	@Override
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
