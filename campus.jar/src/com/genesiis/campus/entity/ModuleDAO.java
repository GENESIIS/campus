package com.genesiis.campus.entity;

//20161025 CM c13-Display course details INIT ModuleDAO.java
//20161025 CM c13-Display course details Modified findById() method

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Module;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.entity.model.Semester;
import com.genesiis.campus.util.ConnectionManager;

public class ModuleDAO implements ICrud {

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
		PreparedStatement preparedStatement2 = null;
		try {
			conn = ConnectionManager.getConnection();

			final Programme programme = (Programme) code;
			final Semester semester=new Semester();
			String querySemester = "SELECT * FROM [CAMPUS].[SEMESTER] WHERE PROGRAMME = ?";
			preparedStatement = conn.prepareStatement(querySemester.toString());
			preparedStatement.setInt(1, programme.getCode());
			ResultSet rsSem = preparedStatement.executeQuery();
//			HashMap<String, ArrayList<String>> map=new HashMap();
			while (rsSem.next()) {
				
				semester.setCode(rsSem.getInt("Code"));
				
				String query = "Select m.*,p.*,s.* from  [CAMPUS].PROGRAMME p inner join  [CAMPUS].SEMESTER s on s.programme = p.code inner join [CAMPUS].MODULE"
						+ " m on m.semester = s.code where p.CODE=? and s.code=?";
				preparedStatement2 = conn.prepareStatement(query.toString());
				preparedStatement2.setInt(1, programme.getCode());
				preparedStatement2.setInt(2, semester.getCode());
				ResultSet rs = preparedStatement2.executeQuery();

				while (rs.next()) {
					final ArrayList<String> singleModuleDetails = new ArrayList<String>();
					// default semester value name as "default"

					singleModuleDetails.add(rs.getString(1));
					singleModuleDetails.add(rs.getString(2));
					singleModuleDetails.add(rs.getString(3));
					singleModuleDetails.add(rs.getString(4));
					singleModuleDetails.add(rs.getString(5));

					// singleSemeterDetail.add(rs.getString("description"));
					// singleSemeterDetail.add(rs.getString("tutoredBy"));
					moduleDetails.add(singleModuleDetails);
					
				}
//				map.put(semester.getCode(), moduleDetails);
				return moduleDetails;
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
