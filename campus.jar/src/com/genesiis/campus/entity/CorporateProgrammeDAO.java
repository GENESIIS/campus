package com.genesiis.campus.entity;

//20161026 MM c5-corporate-training-landing-page INIT CorporateProgrammeDAO.java
//20161026 MM c5-corporate-training-landing-page Removed setting of unneeded 
// 				field data when fetching data in findById()
//20161028 MM c5-corporate-training-landing-page Corrected query result processing
// 				code to remove accessing invalid fields

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
			
//			String query = "SELECT p.*, cp.LOGOIMAGEPATH, ct.NAME AS CLASSTYPENAME, t.CODE AS TOWNCODE, "
//					+ "t.NAME AS TOWNNAME FROM ("
//					+ "SELECT TOP 3 NEWID() as dummy, a.CODE FROM ("
//					+ "SELECT p.CODE FROM [CAMPUS].[PROGRAMME] p WHERE p.CATEGORY = 3 "
//					+ "AND p.PROGRAMMESTATUS = 1 AND GETDATE() < p.EXPIRYDATE) a ORDER BY NEWID()"
//					+ ") b "
//					+ "JOIN [CAMPUS].[PROGRAMME] p ON (p.CODE = b.CODE) "
//					+ "JOIN [CAMPUS].[COURSEPROVIDER] cp ON (p.COURSEPROVIDER = cp.CODE "
//					+ "AND COURSEPROVIDERSTATUS = 1 AND GETDATE() < cp.EXPIRATIONDATE) "
//					+ "JOIN [CAMPUS].[CLASSTYPE] ct ON (ct.CODE = p.CLASSTYPE AND ct.ISACTIVE = 1) "
//					+ "LEFT JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME AND pt.ISACTIVE = 1) "
//					+ "LEFT JOIN [CAMPUS].[TOWN] t ON (pt.TOWN = t.CODE)";
			
			String query = "SELECT p.*, cp.SHORTNAME, cp.UNIQUEPREFIX, cp.NAME AS COURSEPROVIDERNAME, cp.LOGOIMAGEPATH, "
					+ "ct.NAME AS CLASSTYPENAME, t.CODE AS TOWNCODE, t.NAME AS TOWNNAME FROM ("
					+ "SELECT TOP 10 NEWID() as dummy, a.CODE FROM ("
					+ "SELECT p.CODE FROM [CAMPUS].[PROGRAMME] p "
					+ "WHERE p.CATEGORY = ? AND p.PROGRAMMESTATUS = ?) a ORDER BY NEWID()"
					+ ") b "
					+ "JOIN [CAMPUS].[PROGRAMME] p ON (p.CODE = b.CODE) "
					+ "JOIN [CAMPUS].[COURSEPROVIDER] cp ON (p.COURSEPROVIDER = cp.CODE) "
					+ "JOIN [CAMPUS].[CLASSTYPE] ct ON (ct.CODE = p.CLASSTYPE AND ct.ISACTIVE = ?) "
					+ "LEFT JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME AND pt.ISACTIVE = ?) "
					+ "LEFT JOIN [CAMPUS].[TOWN] t ON (pt.TOWN = t.CODE) ORDER BY p.CODE";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
//			ps.setInt(1, 10);
			ps.setInt(1, categoryCode);
			ps.setInt(2, 1);
			ps.setInt(3, 1);
			ps.setInt(4, 1);
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
			singleProgramme.add(rs.getString("LOGOIMAGEPATH"));
			singleProgramme.add(rs.getString("CLASSTYPENAME"));
			singleProgramme.add(rs.getString("SHORTNAME"));
			singleProgramme.add(rs.getString("COURSEPROVIDERNAME"));
			singleProgramme.add(rs.getString("TOWNCODE"));
			singleProgramme.add(rs.getString("TOWNNAME"));
			singleProgramme.add(rs.getString("UNIQUEPREFIX"));
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
