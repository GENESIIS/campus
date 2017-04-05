package com.genesiis.campus.entity;
//20170405 AS c23-admin-login-logout-function-as - AdminPrivilegeDAO created 

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;



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
		
		
		return null;
		
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
