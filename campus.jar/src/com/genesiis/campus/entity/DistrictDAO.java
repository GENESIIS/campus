package com.genesiis.campus.entity;

//20161029 PN c11-criteria-based-filter-search implemented getAll() method for retrieve existing details
//         PN c11-criteria-based-filter-search modified sql query inside getAll() method. 
//20161102 PN c11-criteria-based-filter-search getAll() method implemented.
//20170104 PN CAM-116: added JDBC connection property close statements into finally blocks.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;

public class DistrictDAO implements ICrud{
	static Logger log = Logger.getLogger(DistrictDAO.class.getName());
	
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
		int instituteCode = (Integer) code;
		final Collection<Collection<String>> allDistrictList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT DISTINCT d.[CODE],d.[PROVINCE],d.[NAME] FROM [CAMPUS].[DISTRICT] d "
					+ "JOIN [CAMPUS].[TOWN] t ON d.CODE = t.DISTRICT "
					+ "JOIN [CAMPUS].[PROGRAMMETOWN] pt ON t.CODE = pt.TOWN "
					+ "JOIN [CAMPUS].[PROGRAMME] p ON pt.PROGRAMME = p.CODE "
					+ "WHERE p.COURSEPROVIDER = ?;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, instituteCode);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleDistrictList = new ArrayList<String>();
				singleDistrictList.add(rs.getString("CODE"));
				singleDistrictList.add(rs.getString("PROVINCE"));
				singleDistrictList.add(rs.getString("NAME"));

				final Collection<String> singleDistrictCollection = singleDistrictList;
				allDistrictList.add(singleDistrictCollection);
			}
		} catch (SQLException sqlException) {
			log.error("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("getAll(): E " + e.toString());
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allDistrictList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		final Collection<Collection<String>> allDistrictList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[PROVINCE],[NAME] FROM [CAMPUS].[DISTRICT] WHERE CODE NOT IN (-1,31);";

			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleDistrictList = new ArrayList<String>();
				singleDistrictList.add(rs.getString("CODE"));
				singleDistrictList.add(rs.getString("PROVINCE"));
				singleDistrictList.add(rs.getString("NAME"));

				final Collection<String> singleDistrictCollection = singleDistrictList;
				allDistrictList.add(singleDistrictCollection);
			}
		} catch (SQLException sqlException) {
			log.error("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("getAll(): E " + e.toString());
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allDistrictList;
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
