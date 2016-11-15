package com.genesiis.campus.entity;

//20161029 PN c11-criteria-based-filter-search implemented getAll() method for retrieve existing details
//20161102 PN c11-criteria-based-filter-search implementing findById() method to retrieve data according to the criteria.
//20161103 PN c11-criteria-based-filter-search modified getAll() method and findById() method by changing the SQL query.
//20161115 PN c1-campus-landing-page added functional comments to the methods. formatted the error logs.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.IQueryBuilder;
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

	/**
	 * @author pabodha
	 * @param code - String with searched criteria values.
	 * @return Collection<Collection<String>>: contains all the available
	 *         programs in DB, according to the given criteria.
	 */
	@Override
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		String searchData = (String) code;

		Collection<Collection<String>> allProgrammeList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		//This method to be changed with the PROGRAMSTATUS once defined it in the DML.
		try {
			IQueryBuilder qbh = new QueryBuildingHelper();
			Map<String, String[]> queryMap = qbh.assignMapData(qbh
					.extractFromJason(searchData));

			String[] districtCode = {};
			String tempquery = "";

			if (!queryMap.isEmpty()) {
				districtCode = queryMap.get("DISTRICT");
				queryMap.remove("DISTRICT");
								
				
				String subQuery = " AND d.CODE = ? ";
				if ((districtCode == null) || (districtCode.length == 0)) {
					tempquery = qbh.dynamicQuery(queryMap, "");
				} else {
					tempquery = qbh.dynamicQuery(queryMap, subQuery);
				}

				conn = ConnectionManager.getConnection();
				String query = "SELECT p.[CODE], p.[NAME], p.[IMAGE], p.[DESCRIPTION], p.[DISPLAYSTARTDATE], cp.[NAME] as [PROVIDER], cp.[UNIQUEPREFIX]"
						+ "FROM [CAMPUS].[PROGRAMME] p "
						+ "JOIN [CAMPUS].[PROGRAMMETOWN] pt ON p.CODE = pt.PROGRAMME "
						+ "JOIN [CAMPUS].[TOWN] t ON t.CODE = pt.TOWN "
						+ "JOIN [CAMPUS].[DISTRICT] d ON d.CODE = t.DISTRICT "
						+ "JOIN [CAMPUS].[COURSEPROVIDER] cp ON cp.CODE = p.COURSEPROVIDER "
						+ "WHERE p.PROGRAMMESTATUS = 1 "
						+ tempquery + ";";

				stmt = conn.prepareStatement(query);
				if (districtCode != null) {
					stmt.setInt(1, Integer.parseInt(districtCode[0]));
				}
							
				final ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					final ArrayList<String> singleProgrammeList = new ArrayList<String>();
					singleProgrammeList.add(rs.getString("CODE"));
					singleProgrammeList.add(rs.getString("NAME"));
					singleProgrammeList.add(rs.getString("DESCRIPTION"));
					singleProgrammeList.add(rs.getString("DISPLAYSTARTDATE"));
					singleProgrammeList.add(rs.getString("PROVIDER"));
					singleProgrammeList.add(rs.getString("UNIQUEPREFIX"));
					singleProgrammeList.add(rs.getString("IMAGE"));
					
					final Collection<String> singleProgrammeCollection = singleProgrammeList;
					allProgrammeList.add(singleProgrammeCollection);
				}
			}else{
				allProgrammeList = getAll();	
			}
		} catch (SQLException sqlException) {
			log.error("findById(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("findById(): E " + e.toString());
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

	/**
	 * @author pabodha
	 * @return Collection<Collection<String>>: contains all the available
	 *         programs in DB.
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		final Collection<Collection<String>> allProgrammeList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = " SELECT p.[CODE], p.[NAME], p.[IMAGE], p.[DESCRIPTION], p.[DISPLAYSTARTDATE], cp.[NAME] as [PROVIDER], cp.[UNIQUEPREFIX] "
					+ "FROM [CAMPUS].[PROGRAMME] p "
					+ "JOIN [CAMPUS].[COURSEPROVIDER] cp ON cp.CODE = p.COURSEPROVIDER "
					+ "WHERE p.PROGRAMMESTATUS = 1";

			stmt = conn.prepareStatement(query);
			final ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleProgrammeList = new ArrayList<String>();
				singleProgrammeList.add(rs.getString("CODE"));
				singleProgrammeList.add(rs.getString("NAME"));
				singleProgrammeList.add(rs.getString("DESCRIPTION"));
				singleProgrammeList.add(rs.getString("DISPLAYSTARTDATE"));
				singleProgrammeList.add(rs.getString("PROVIDER"));
				singleProgrammeList.add(rs.getString("UNIQUEPREFIX"));
				singleProgrammeList.add(rs.getString("IMAGE"));

				final Collection<String> singleProgrammeCollection = singleProgrammeList;
				allProgrammeList.add(singleProgrammeCollection);
			}
		} catch (SQLException sqlException) {
			log.error("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("getAll(): E " + e.toString());
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
