package com.genesiis.campus.entity;

//20161025 CM c13-Display course details INIT ModuleDAO.java
//20161025 CM c13-Display course details Modified findById() method

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;

public class ModuleDAO implements ICrud{

	static Logger log = Logger.getLogger(ModuleDAO.class.getName());
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

	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		final Collection<Collection<String>> moduleDetails = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			final Programme programme = (Programme) code;
		
			

			conn = ConnectionManager.getConnection();

			String query = "Select m.*,p.*,s.* from  [CAMPUS].PROGRAMME p inner join  [CAMPUS].SEMESTER s on s.programme = p.code inner join [CAMPUS].MODULE"
					+ " m on m.semester = s.code where p.CODE=? ";
			preparedStatement = conn.prepareStatement(query.toString());
			preparedStatement.setInt(1, programme.getCode());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleModuleDetails = new ArrayList<String>();
			// default semester value name as "default"
				
				singleModuleDetails.add(rs.getString(2));
				singleModuleDetails.add(rs.getString(4));
				singleModuleDetails.add(rs.getString(5));
				singleModuleDetails.add(rs.getString(6));
				singleModuleDetails.add(rs.getString(37));
				
//				singleSemeterDetail.add(rs.getString("description"));
//				singleSemeterDetail.add(rs.getString("tutoredBy"));
				moduleDetails.add(singleModuleDetails);

				
			}
		} catch (Exception exception) {
			log.error("findById(Object code):  exception"
					+ exception.toString());
			throw exception;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return moduleDetails;
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
