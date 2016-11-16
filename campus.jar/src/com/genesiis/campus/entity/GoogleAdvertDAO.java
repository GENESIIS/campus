//20161103 MM c2-integrate-google-banners GoogleAdvertDAO.java created
//20161116 MM c2-integrate-google-banners Implemented findById() method

package com.genesiis.campus.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.util.ConnectionManager;

public class GoogleAdvertDAO implements ICrud {

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
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		
		final Collection<Collection<String>> corporateProgrammeList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Banner programme = (Banner) code;
			int bannerCode = programme.getCode();
			
			String query = "SELECT * FROM BANNER WHERE TYPE = 'BANNER' AND CODE = ?";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, bannerCode);
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
			singleProgramme.add(rs.getString("CODE")); //0
			singleProgramme.add(rs.getString("EXPIRATIONDATE")); //1
			singleProgramme.add(rs.getString("PAGE")); //2
			singleProgramme.add(rs.getString("TYPE")); //3
			singleProgramme.add(rs.getString("DISPLAYDURATION")); //4
			singleProgramme.add(rs.getString("LINKTYPE")); //5
			singleProgramme.add(rs.getString("URL")); //6
			singleProgramme.add(rs.getString("BANNERSTATUS")); //7
			singleProgramme.add(rs.getString("ISACTIVE")); //8
			singleProgramme.add(rs.getString("ADVERTISER")); //9
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

}
