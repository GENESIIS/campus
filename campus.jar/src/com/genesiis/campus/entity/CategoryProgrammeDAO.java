package com.genesiis.campus.entity;

//20161026 MM c5-corporate-training-landing-page INIT CorporateProgrammeDAO.java
//20161026 MM c5-corporate-training-landing-page Removed setting of unneeded 
// 				field data when fetching data in findById()
//20161028 MM c5-corporate-training-landing-page Corrected query result processing
// 				code to remove accessing invalid fields
//20161104 MM c5-corporate-training-landing-page Added code to DAO method to retrieve level and 
//				major names for programmes
//20161104 MM c5-corporate-training-landing-page-MP Changed query in findById() to remove TOP clause
//20161126 MM c5-corporate-training-landing-page-MP Changed query and column-data-extraction-code 
//				to omit logoImagePath, since courseProvider image details are now not taken from DB
//20161126 MM c5-corporate-training-landing-page-MP Restructured query to remove the now unnecessary 
//				sub-query and added EXPIRYDATE related condition to fetch non-expired programmes
//20161127 MM c5-corporate-training-landing-page-MP Modified code to retrieve AccountType of 
//				courseProvider and use AccountType enum to set the proper constant name to result list
//20161127 MM c5-corporate-training-landing-page-MP Modified code to use ApplicationStatus enum 
//				to set arguments to status related parameters, and to consider DISPLAYSTARTDATE in query
//20161127 MM c5-corporate-training-landing-page-MP Removed unused java.sql.Date object.
//20170102 PN CAM-112: added ResultSet close statement into finally blocks in DAO methods.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.AccountType;
import com.genesiis.campus.validation.ApplicationStatus;

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
		ResultSet rs = null;
		try {
			Programme programme = (Programme) code;
			int categoryCode = programme.getCategory();
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());
			
			String query = "SELECT p.*, cp.SHORTNAME, cp.UNIQUEPREFIX, cp.NAME AS COURSEPROVIDERNAME, "
					+ "cp.ACCOUNTTYPE, ct.NAME AS CLASSTYPENAME, m.NAME AS MAJORNAME, l.NAME AS LEVELNAME, "
					+ "t.CODE AS TOWNCODE, t.NAME AS TOWNNAME "
					+ "FROM [CAMPUS].[PROGRAMME] p JOIN [CAMPUS].[COURSEPROVIDER] cp "
					+ "ON (p.COURSEPROVIDER = cp.CODE AND p.CATEGORY = ? AND p.PROGRAMMESTATUS = ? AND " //1, 2, 
					+ "p.DISPLAYSTARTDATE <= ? AND p.EXPIRYDATE > ?) " //3, 4
					+ "JOIN [CAMPUS].[CLASSTYPE] ct ON (ct.CODE = p.CLASSTYPE AND ct.ISACTIVE = ?) " //5
					+ "JOIN [CAMPUS].[MAJOR] m ON (m.CODE = p.MAJOR AND m.ISACTIVE = ?) " //6
					+ "JOIN [CAMPUS].[LEVEL] l ON (l.CODE = p.LEVEL AND l.ISACTIVE = ?) " //7
					+ "LEFT JOIN [CAMPUS].[PROGRAMMETOWN] pt ON (p.CODE = pt.PROGRAMME AND pt.ISACTIVE = ?) " //8
					+ "LEFT JOIN [CAMPUS].[TOWN] t ON (pt.TOWN = t.CODE) ORDER BY NEWID()";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, categoryCode);
			ps.setInt(2, ApplicationStatus.ACTIVE.getStatusValue());
			ps.setDate(3, today);
			ps.setDate(4, today);
			ps.setInt(5, ApplicationStatus.ACTIVE.getStatusValue());
			ps.setInt(6, ApplicationStatus.ACTIVE.getStatusValue());
			ps.setInt(7, ApplicationStatus.ACTIVE.getStatusValue());
			ps.setInt(8, ApplicationStatus.ACTIVE.getStatusValue());
			rs = ps.executeQuery();
			
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
			if (rs != null) {
				rs.close();
			}
		}
		return corporateProgrammeList;
	}
	
	private void retrieveProgrammesFromResultSet(ResultSet rs, Collection<Collection<String>> deptList) throws SQLException {

		int accountTypeValue = -1;
		AccountType accountType = null;
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
			singleProgramme.add(rs.getString("CLASSTYPENAME")); //16
			singleProgramme.add(rs.getString("SHORTNAME")); //17
			singleProgramme.add(rs.getString("COURSEPROVIDERNAME")); //18
			singleProgramme.add(rs.getString("TOWNCODE")); //19
			singleProgramme.add(rs.getString("TOWNNAME")); //20
			singleProgramme.add(rs.getString("UNIQUEPREFIX")); //21
			singleProgramme.add(rs.getString("MAJORNAME")); //22
			singleProgramme.add(rs.getString("LEVELNAME")); //23
			
			accountTypeValue = rs.getInt("ACCOUNTTYPE");
			accountType = AccountType.getAccountTypeByTypeValue(accountTypeValue);

			String accountTypeStr = "";
			if (accountType != null) {
				accountTypeStr = accountType.name();
			}
			singleProgramme.add(accountTypeStr); //24
			
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

	@Override
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
