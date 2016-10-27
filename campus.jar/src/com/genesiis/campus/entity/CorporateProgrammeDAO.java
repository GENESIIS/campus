package com.genesiis.campus.entity;

//20161026 MM c5-corporate-training-landing-page INIT CorporateProgrammeDAO.java
//20161026 MM c5-corporate-training-landing-page Removed setting of unneeded 
// 				field data when fetching data in findById()

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;

public class CorporateProgrammeDAO implements ICrud {
	static Logger Log = Logger.getLogger(CorporateProgrammeDAO.class.getName());

	@Override
	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		
		final Collection<Collection<String>> corporateProgrammeList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Programme programme = (Programme) code;
			int categoryCode = programme.getCategory();
			
			String query = "SELECT * FROM [CAMPUS].[PROGRAMME] WHERE CATEGORY = ? AND PROGRAMMESTATUS = ? AND GETDATE() < EXPIRYDATE";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, categoryCode);
			ps.setInt(2, 1);
			ResultSet rs = ps.executeQuery();
			
			retrieveProgrammesFromResultSet(rs, corporateProgrammeList);

		} catch (ClassCastException cce) {
			Log.info("findById(Object): ClassCastException: " + cce.toString());
			throw new IllegalArgumentException("The argument passed is not of expected type (Programme)!");
		} catch (SQLException sqle) {
			Log.info("findById(Object): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			Log.info("findById(Object): Exception: " + e.toString());
			throw e;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return corporateProgrammeList;
	}
	
	private void retrieveProgrammesFromResultSet(ResultSet rs, Collection<Collection<String>> deptList) throws SQLException {

		while (rs.next()) {
			final ArrayList<String> singleProgramme = new ArrayList<String>();
			singleProgramme.add(rs.getString("CODE"));
			singleProgramme.add(rs.getString("NAME"));
			singleProgramme.add(rs.getString("EMAIL"));
			singleProgramme.add(rs.getString("IMAGE"));
			singleProgramme.add(rs.getString("ISACTIVE"));
			singleProgramme.add(rs.getString("DESCRIPTION"));
			singleProgramme.add(rs.getString("DURATION"));
			singleProgramme.add(rs.getString("ENTRYREQUIREMENTS"));
			singleProgramme.add(rs.getString("COUNSELORNAME"));
			singleProgramme.add(rs.getString("COUNSELORPHONE"));
			singleProgramme.add(rs.getString("DISPLAYSTARTDATE"));
			singleProgramme.add(rs.getString("PROGRAMMESTATUS"));
			singleProgramme.add(rs.getString("COURSEPROVIDER"));
			singleProgramme.add(rs.getString("MAJOR"));
			singleProgramme.add(rs.getString("CATEGORY"));
			singleProgramme.add(rs.getString("LEVEL"));
			singleProgramme.add(rs.getString("CLASSTYPE"));
			final Collection<String> singleProgrammeCollection = singleProgramme;
			deptList.add(singleProgrammeCollection);
		}
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

}
