package com.genesiis.campus.entity;

//20161029 PN c11-criteria-based-filter-search implemented getAll() method for retrieve existing details
//20161102 PN c11-criteria-based-filter-search implementing findById() method to retrieve data according to the criteria.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.QueryBuildingHelper;

public class ProgrammeDAO implements ICrud {
	static Logger log = Logger.getLogger(ProgrammeDAO.class.getName());

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
		String searchData = (String) code;
		log.info("searchData" + searchData);

		final Collection<Collection<String>> allProgrammeList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		//This method to be changed with the PROGRAMSTATUS once defined it in the DML.
		try {
			QueryBuildingHelper qbh = new QueryBuildingHelper();
			Map<String, String[]> queryMap = qbh.assignMapData(qbh.extractFromJason(searchData));
			String[] districtCode = queryMap.get("DISTRICT");
			String subQuery = " AND d.CODE = ? ";
			String tempquery = "";
			if (districtCode.length == 0) {
				tempquery = qbh.dynamicQuery(queryMap, " AND ");
			} else {
				tempquery = qbh.dynamicQuery(queryMap, subQuery);
			}
			log.info("tempquery " + tempquery);

			conn = ConnectionManager.getConnection();
			String query = "SELECT p.* FROM [CAMPUS].[PROGRAMME] p "
					+ "JOIN [CAMPUS].[PROGRAMMETOWN] pt ON p.CODE = pt.PROGRAMME "
					+ "JOIN [CAMPUS].[TOWN] t ON t.CODE = pt.TOWN "
					+ "JOIN [CAMPUS].[DISTRICT] d ON d.CODE = t.DISTRICT WHERE p.PROGRAMMESTATUS = 1 "
					+ searchData + ";";
			log.info(query);

			// stmt = conn.prepareStatement(query);
			// final ResultSet rs = stmt.executeQuery();
			//
			// while (rs.next()) {
			// final ArrayList<String> singleProgrammeList = new
			// ArrayList<String>();
			// singleProgrammeList.add(rs.getString("CODE"));
			// singleProgrammeList.add(rs.getString("NAME"));
			// singleProgrammeList.add(rs.getString("DESCRIPTION"));
			//
			// final Collection<String> singleProgrammeCollection =
			// singleProgrammeList;
			// allProgrammeList.add(singleProgrammeCollection);
			// }
		} catch (SQLException sqlException) {
			log.info("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll(): E " + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allProgrammeList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		final Collection<Collection<String>> allProgrammeList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT * FROM [CAMPUS].[Programme] WHERE [ISACTIVE] = 1;";

			stmt = conn.prepareStatement(query);
			final ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleProgrammeList = new ArrayList<String>();
				singleProgrammeList.add(rs.getString("CODE"));
				singleProgrammeList.add(rs.getString("NAME"));
				singleProgrammeList.add(rs.getString("DESCRIPTION"));

				final Collection<String> singleProgrammeCollection = singleProgrammeList;
				allProgrammeList.add(singleProgrammeCollection);
			}
		} catch (SQLException sqlException) {
			log.info("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll(): E " + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allProgrammeList;
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
