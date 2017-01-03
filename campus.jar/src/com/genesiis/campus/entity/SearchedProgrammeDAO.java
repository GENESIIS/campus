package com.genesiis.campus.entity;

//20161029 PN c11-criteria-based-filter-search implemented getAll() method for retrieve existing details

//20161102 PN c11-criteria-based-filter-search implementing findById() method to retrieve data according to the criteria.
//20161103 PN c11-criteria-based-filter-search modified getAll() method and findById() method by changing the SQL query.
//20161124 PN c11-criteria-based-filter-search modified getAll() method and findById() method by changing the SQL query by selecting CP code.
//20161222 CAM-116: PN renamed: campus.jar/src/com/genesiis/campus/entity/ProgrammeDAO.java -> campus.jar/src/com/genesiis/campus/entity/SearchedProgrammeDAO.java
//         CAM-116: PN Modified the SQL query inside getAll() method and findById() method.
//20161223 CAM-116: PN Modified Collection<Collection<String>> findById(Object code) by providing two SQL queries to pass data in different cases.
//		   CAM-116: PN Modified SQL queries inside findById(Object code) and getAll() method by adding GROUP BY clause.
//20170104 PN CAM-116: added JDBC connection property close statements into finally blocks.

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

public class SearchedProgrammeDAO implements ICrud {
	static Logger log = Logger.getLogger(SearchedProgrammeDAO.class.getName());

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

		Collection<Collection<String>> allProgrammeList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query1 = "SELECT p.[CODE] ,p.[NAME] ,CAST(p.[DESCRIPTION] as NVARCHAR(max)) AS [DESCRIPTION] ,p.[DURATION] ,p.[ENTRYREQUIREMENTS] ,p.[COUNSELORNAME] ,"
				+ "p.[COUNSELORPHONE] ,p.[DISPLAYSTARTDATE] ,p.[EXPIRYDATE] ,p.[PROGRAMMESTATUS] ,p.[COURSEPROVIDER] ,p.[MAJOR] ,p.[CATEGORY] ,"
				+ "p.[LEVEL] ,p.[CLASSTYPE], cp.[NAME] as [PROVIDER], cp.[UNIQUEPREFIX], cp.[CODE] as [CPCODE], cp.[WEBLINK] , ISNULL(MIN(itk.[FEE]),0.00) as COST "
				+ "FROM [CAMPUS].[PROGRAMME] p " + "JOIN [CAMPUS].[PROGRAMMETOWN] pt ON p.CODE = pt.PROGRAMME "
				+ "JOIN [CAMPUS].[TOWN] t ON t.CODE = pt.TOWN " + "JOIN [CAMPUS].[DISTRICT] d ON d.CODE = t.DISTRICT "
				+ "JOIN [CAMPUS].[COURSEPROVIDER] cp ON cp.CODE = p.COURSEPROVIDER " + "WHERE p.PROGRAMMESTATUS = 1 ";

		String query2 = "SELECT p.[CODE] ,p.[NAME] ,CAST(p.[DESCRIPTION] as NVARCHAR(max)) AS [DESCRIPTION] ,p.[DURATION] ,p.[ENTRYREQUIREMENTS] ,p.[COUNSELORNAME] ,"
				+ "p.[COUNSELORPHONE] ,p.[DISPLAYSTARTDATE] ,p.[EXPIRYDATE] ,p.[PROGRAMMESTATUS] ,p.[COURSEPROVIDER] ,p.[MAJOR] ,p.[CATEGORY] ,"
				+ "p.[LEVEL] ,p.[CLASSTYPE], cp.[NAME] as [PROVIDER], cp.[UNIQUEPREFIX], cp.[CODE] as [CPCODE] , cp.[WEBLINK] , ISNULL(MIN(itk.[FEE]),0.00) as COST "
				+ "FROM [CAMPUS].[PROGRAMME] p " + "JOIN [CAMPUS].[COURSEPROVIDER] cp ON cp.CODE = p.COURSEPROVIDER "
				+ "LEFT OUTER JOIN [CAMPUS].[INTAKE] itk ON itk.[PROGRAMME]=p.[CODE]" + "WHERE p.PROGRAMMESTATUS = 1";

		String groupByQuery = "GROUP BY p.[CODE] ,p.[NAME] ,CAST(p.[DESCRIPTION] as NVARCHAR(max)) ,p.[DURATION] ,"
				+ "p.[ENTRYREQUIREMENTS] ,p.[COUNSELORNAME] ,p.[COUNSELORPHONE] ,p.[DISPLAYSTARTDATE] ,p.[EXPIRYDATE] ,"
				+ "p.[PROGRAMMESTATUS] ,p.[COURSEPROVIDER] ,p.[MAJOR] ,p.[CATEGORY] ,p.[LEVEL] ,p.[CLASSTYPE], cp.[NAME], "
				+ "cp.[UNIQUEPREFIX], cp.[CODE] , cp.[WEBLINK];";

		String query = "";

		// This method to be changed with the PROGRAMSTATUS once defined it in
		// the DML.
		try {
			IQueryBuilder qbh = new QueryBuildingHelper();
			Map<String, String[]> queryMap = qbh.assignMapData(qbh.extractFromJason(searchData));

			String[] districtCode = {};
			String tempquery = "";

			if (!queryMap.isEmpty()) {
				districtCode = queryMap.get("DISTRICT");
				queryMap.remove("DISTRICT");

				String subQuery = " AND d.CODE = ? ";
				if ((districtCode == null) || (districtCode.length == 0)) {
					tempquery = qbh.dynamicQuery(queryMap, "");
					query = query2 + tempquery + groupByQuery;
				} else {
					tempquery = qbh.dynamicQuery(queryMap, subQuery);
					query = query1 + tempquery + groupByQuery;
				}

				conn = ConnectionManager.getConnection();

				stmt = conn.prepareStatement(query);
				if (districtCode != null) {
					stmt.setInt(1, Integer.parseInt(districtCode[0]));
				}

				rs = stmt.executeQuery();

				while (rs.next()) {
					final ArrayList<String> singleProgrammeList = new ArrayList<String>();
					singleProgrammeList.add(rs.getString("CODE"));
					singleProgrammeList.add(rs.getString("NAME").replaceAll(",", "##"));
					String description = getSubDescription(rs.getString("DESCRIPTION")).replaceAll(",", "##");				
					singleProgrammeList.add(description);
					singleProgrammeList.add(rs.getString("DURATION"));
					singleProgrammeList.add(rs.getString("ENTRYREQUIREMENTS").replaceAll(",", "##"));
					singleProgrammeList.add(rs.getString("COUNSELORNAME"));
					singleProgrammeList.add(rs.getString("DISPLAYSTARTDATE"));
					singleProgrammeList.add(rs.getString("EXPIRYDATE"));
					singleProgrammeList.add(rs.getString("PROGRAMMESTATUS"));
					singleProgrammeList.add(rs.getString("COURSEPROVIDER").replaceAll(",", "##"));
					singleProgrammeList.add(rs.getString("MAJOR"));
					singleProgrammeList.add(rs.getString("CATEGORY"));
					singleProgrammeList.add(rs.getString("LEVEL"));
					singleProgrammeList.add(rs.getString("CLASSTYPE"));
					singleProgrammeList.add(rs.getString("PROVIDER").replaceAll(",", "##"));
					singleProgrammeList.add(rs.getString("UNIQUEPREFIX"));
					singleProgrammeList.add(rs.getString("CPCODE"));
					singleProgrammeList.add(rs.getString("WEBLINK"));
					singleProgrammeList.add(formatDecimal(rs.getString("COST")));
					final Collection<String> singleProgrammeCollection = singleProgrammeList;
					allProgrammeList.add(singleProgrammeCollection);
				}
			} else {
				allProgrammeList = getAll();
			}
		} catch (SQLException sqlException) {
			log.error("findById(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("findById(): E " + e.toString());
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
		return allProgrammeList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		final Collection<Collection<String>> allProgrammeList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT p.[CODE] ,p.[NAME] , CAST(p.[DESCRIPTION] as NVARCHAR(max)) AS [DESCRIPTION] ,p.[DURATION] ,p.[ENTRYREQUIREMENTS] ,p.[COUNSELORNAME] ,"
					+ "p.[COUNSELORPHONE] ,p.[DISPLAYSTARTDATE] ,p.[EXPIRYDATE] ,p.[PROGRAMMESTATUS] ,p.[COURSEPROVIDER] ,p.[MAJOR] ,p.[CATEGORY] ,"
					+ "p.[LEVEL] ,p.[CLASSTYPE], cp.[NAME] as [PROVIDER], cp.[UNIQUEPREFIX], cp.[CODE] as [CPCODE] , cp.[WEBLINK] , ISNULL(MIN(itk.[FEE]),0.00) as COST "
					+ "FROM [CAMPUS].[PROGRAMME] p "
					+ "JOIN [CAMPUS].[COURSEPROVIDER] cp ON cp.CODE = p.COURSEPROVIDER "
					+ "LEFT OUTER JOIN [CAMPUS].[INTAKE] itk ON itk.[PROGRAMME]=p.[CODE] "
					+ "WHERE p.PROGRAMMESTATUS = 1 "
					+ "GROUP BY p.[CODE] ,p.[NAME] ,CAST(p.[DESCRIPTION] as NVARCHAR(max)) ,p.[DURATION] ,p.[ENTRYREQUIREMENTS] ,p.[COUNSELORNAME] ,"
					+ "p.[COUNSELORPHONE] ,p.[DISPLAYSTARTDATE] ,p.[EXPIRYDATE] ,p.[PROGRAMMESTATUS] ,p.[COURSEPROVIDER] ,p.[MAJOR] ,p.[CATEGORY] ,"
					+ "p.[LEVEL] ,p.[CLASSTYPE], cp.[NAME], cp.[UNIQUEPREFIX], cp.[CODE] , cp.[WEBLINK]; ";

			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleProgrammeList = new ArrayList<String>();
				singleProgrammeList.add(rs.getString("CODE"));
				singleProgrammeList.add(rs.getString("NAME").replaceAll(",", "##"));		
				String description = getSubDescription(rs.getString("DESCRIPTION")).replaceAll(",", "##");				
				singleProgrammeList.add(description);
				singleProgrammeList.add(rs.getString("DURATION"));
				singleProgrammeList.add(rs.getString("ENTRYREQUIREMENTS").replaceAll(",", "##"));
				singleProgrammeList.add(rs.getString("COUNSELORNAME"));
				singleProgrammeList.add(rs.getString("DISPLAYSTARTDATE"));
				singleProgrammeList.add(rs.getString("EXPIRYDATE"));
				singleProgrammeList.add(rs.getString("PROGRAMMESTATUS"));
				singleProgrammeList.add(rs.getString("COURSEPROVIDER").replaceAll(",", "##"));
				singleProgrammeList.add(rs.getString("MAJOR"));
				singleProgrammeList.add(rs.getString("CATEGORY"));
				singleProgrammeList.add(rs.getString("LEVEL"));
				singleProgrammeList.add(rs.getString("CLASSTYPE"));
				singleProgrammeList.add(rs.getString("PROVIDER").replaceAll(",", "##"));
				singleProgrammeList.add(rs.getString("UNIQUEPREFIX"));
				singleProgrammeList.add(rs.getString("CPCODE"));
				singleProgrammeList.add(rs.getString("WEBLINK"));
				singleProgrammeList.add(formatDecimal(rs.getString("COST")));
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
	
	/**
	 * @param description
	 * @return
	 */
	private String getSubDescription(String description){
		if(description.length() > 500){
			return description.substring(0, 500)+" ... ";
		}
		return description;
	}

	/**
	 * @param number
	 * @return
	 */
	public String formatDecimal(String number) {
		  float num = Float.parseFloat(number);	
		  float epsilon = 0.004f; // 4 tenths of a cent
		  if (Math.abs(Math.round(num) - num) < epsilon) {
		     return String.format("%10.0f", num); // sdb
		  } else {
		     return String.format("%10.2f", num); // dj_segfault
		  }
	}
}
