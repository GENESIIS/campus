package com.genesiis.campus.entity;

//20161025 JH c7-list-higher-education-courses data access object class HigherEducationProgrammeDAO.java created
//20161025 JH c7-list-higher-education-courses implement unimplemented methods
//20161025 JH c7-list-higher-education-courses findById method modified

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;

public class HigherEducationProgrammeDAO implements ICrud {
	
	static org.apache.log4j.Logger log = Logger.getLogger(HigherEducationProgrammeDAO.class.getName());

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
	 * findById method is used to get all programmes under the higher education 
	 * category. 
	 * @param code
	 * @return string collection of programmes
	 * @author JH
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		int status = 0;
		String returnMessage = "";
		
		String getAllQuery = "SELECT * FROM [CAMPUS].[PROGRAMME ] WHERE CATEGORY = ?";
				
		try{
			conn = ConnectionManager.getConnection();
			preparedStatement.setInt(1, 1);
			
			
		}catch (SQLException exception) {
			log.error("getAll() sql exception" + exception.toString());
			throw exception;

		} catch (Exception exception) {
			log.error("getAll() " + exception.toString());
			throw exception;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			conn.close();

		}
		return null;
	}
	
	
	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {

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
