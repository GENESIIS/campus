package com.genesiis.campus.entity;

//20161026 MM c5-corporate-training-landing-page INIT CorporateProgrammeDAO.java
//20161026 MM c5-corporate-training-landing-page Removed setting of unneeded 
// 				field data when fetching data in findById()
//20161028 MM c5-corporate-training-landing-page Corrected query result processing
// 				code to remove accessing invalid fields
//20161104 MM Added code to DAO method to retrieve level and major names for programmes

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

public class CategoryProgrammeDAO implements ICrud {
	static Logger Log = Logger.getLogger(CategoryProgrammeDAO.class.getName());

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
			
			String query = "SELECT p.*, cp.SHORTNAME, cp.UNIQUEPREFIX, cp.NAME AS COURSEPROVIDERNAME, cp.LOGOIMAGEPATH, "
					+ "ct.NAME AS CLASSTYPENAME, m.NAME AS MAJORNAME, l.NAME AS LEVELNAME, "
					+ "t.CODE AS TOWNCODE, t.NAME AS TOWNNAME FROM ("
					+ "SELECT TOP 10 NEWID() as dummy, a.CODE FROM ("
					+ "SELECT p.CODE FROM [CAMPUS].[PROGRAMME] p "
					+ "WHERE p.CATEGORY = ? AND p.PROGRAMMESTATUS = ?) a ORDER BY NEWID()"
					+ ") b "
					+ "JOIN [CAMPUS].[PROGRAMME] p ON (p.CODE = b.CODE) "
					+ "JOIN [CAMPUS].[COURSEPROVIDER] cp ON (p.COURSEPROVIDER = cp.CODE) "
					+ "JOIN [CAMPUS].[CLASSTYPE] ct ON (ct.CODE = p.CLASSTYPE AND ct.ISACTIVE = ?) "
					+ "JOIN [CAMPUS].[MAJOR] m ON (m.CODE = p.MAJOR AND m.ISACTIVE = ?) "
					+ "JOIN [CAMPUS].[LEVEL] l ON (l.CODE = p.LEVEL AND l.ISACTIVE = ?) "
					+ "LEFT JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME AND pt.ISACTIVE = ?) "
					+ "LEFT JOIN [CAMPUS].[TOWN] t ON (pt.TOWN = t.CODE) ORDER BY p.CODE";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, categoryCode);
			ps.setInt(2, 1);
			ps.setInt(3, 1);
			ps.setInt(4, 1);
			ps.setInt(5, 1);
			ps.setInt(6, 1);
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
			singleProgramme.add(rs.getString("NAME")); //1
			singleProgramme.add(rs.getString("EMAIL")); //2
			singleProgramme.add(rs.getString("IMAGE")); //3
			singleProgramme.add(rs.getString("DESCRIPTION")); //4
			singleProgramme.add(rs.getString("DURATION")); //5
			singleProgramme.add(rs.getString("ENTRYREQUIREMENTS")); //6
			singleProgramme.add(rs.getString("COUNSELORNAME")); //7
			singleProgramme.add(rs.getString("COUNSELORPHONE")); //8
			singleProgramme.add(rs.getString("DISPLAYSTARTDATE")); //9
			singleProgramme.add(rs.getString("PROGRAMMESTATUS")); //10
			singleProgramme.add(rs.getString("COURSEPROVIDER")); //11
			singleProgramme.add(rs.getString("MAJOR")); //12
			singleProgramme.add(rs.getString("CATEGORY")); //13
			singleProgramme.add(rs.getString("LEVEL")); //14
			singleProgramme.add(rs.getString("CLASSTYPE")); //15
			singleProgramme.add(rs.getString("LOGOIMAGEPATH")); //16
			singleProgramme.add(rs.getString("CLASSTYPENAME")); //17
			singleProgramme.add(rs.getString("SHORTNAME")); //18
			singleProgramme.add(rs.getString("COURSEPROVIDERNAME")); //19
			singleProgramme.add(rs.getString("TOWNCODE")); //20
			singleProgramme.add(rs.getString("TOWNNAME")); //21
			singleProgramme.add(rs.getString("UNIQUEPREFIX")); //22
			singleProgramme.add(rs.getString("MAJORNAME")); //23
			singleProgramme.add(rs.getString("LEVELNAME")); //24
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
