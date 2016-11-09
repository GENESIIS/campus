package com.genesiis.campus.entity;
//DJ 20161026 c6-list-available-institutes-on-the-view created InstituteProviderDAO.java
//DJ 20161028 c6-list-available-institutes-on-the-view created findById()
//DJ 20161030 c6-list-available-institutes-on-the-view refactored query to identified get all institutes 
//DJ 20161031 c6-list-available-institutes-on-the-view rename the class name as  CourseProviderDAO.java
//DJ 20161031 c6-list-available-institutes-on-the-view create findTopViewedProviders()
//DJ 20161103 c6-list-available-institutes-on-the-view Implemented findTopViewedProviders()
//DJ 20161103 c6-list-available-institutes-on-the-view adjust the findTopViewedProviders() to support dynamic category code
//DJ 20161103 c6-list-available-institutes-on-the-view create findTopRatedProviders()
//DJ 20161109 c6-list-available-institutes-on-the-view Implemented findTopRatedProviders() query
//DJ 20161109 c6-list-available-institutes-on-the-view refactored query in  findTopViewedProviders() method


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.UtilityHelper;

public class CourseProviderDAO implements ICrud{
	
	static org.apache.log4j.Logger log = Logger.getLogger(CourseProviderDAO.class.getName());

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
	 * Find Institutes
	 * @param category code
	 * @author DJ
	 * @return Collection 
	 */

	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement  stmt = null;	
		final Collection<Collection<String>> allInstitutesList = new ArrayList<Collection<String>>();
		
		try {
			conn=ConnectionManager.getConnection();
			int categoryCode=0;
			boolean isGetAll=false;
			
			if(UtilityHelper.isNotEmptyObject(code)){
				final CourseProvider cp = (CourseProvider) code;
				categoryCode = cp.getCategory();
				isGetAll=cp.isGetAll();
			}			
			
			final StringBuilder sb = new StringBuilder("SELECT DISTINCT PROV.CODE, PROV.UNIQUEPREFIX , PROV.NAME ");
			sb.append("FROM [CAMPUS].COURSEPROVIDER PROV  INNER JOIN [CAMPUS].PROGRAMME PROG  ON  PROV.CODE=PROG.COURSEPROVIDER ");
			sb.append("INNER JOIN [CAMPUS].CATEGORY CAT ON PROG.CATEGORY=CAT.CODE WHERE ");
			sb.append("PROG.CATEGORY=CAT.CODE AND PROV.COURSEPROVIDERSTATUS=1 ");
			// sb.append("AND PROG.PROGRAMMESTATUS=1 ");
			sb.append("AND CAT.ISACTIVE=1 ");
			if (!isGetAll) {
				sb.append(" AND CAT.CODE=? ");
			}
			 
			stmt = conn.prepareStatement(sb.toString());
			if(!isGetAll){
				stmt.setInt(1, categoryCode);	
			}
			final ResultSet rs = stmt.executeQuery();
			while (rs.next()) {				
				final ArrayList<String> singleInstitute = new ArrayList<String>();
				singleInstitute.add(rs.getString("CODE"));
				singleInstitute.add(rs.getString("NAME"));
				singleInstitute.add(rs.getString("UNIQUEPREFIX"));
				allInstitutesList.add(singleInstitute);
			}
			
		} catch (SQLException sqlException) {
			log.info("findById() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById() Exception" + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allInstitutesList;
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

	@Override
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Find Top Viewed Providers
	 * @param CourseProvider DTO
	 * @author DJ
	 * @return Collection 
	 */

	public Collection<Collection<String>> findTopViewedProviders(CourseProvider provider) throws SQLException{
		Connection conn = null;
		PreparedStatement  stmt = null;	
		final Collection<Collection<String>> allProviderList = new ArrayList<Collection<String>>();
		
		try {
			int categoryCode=0;
			boolean isGetAll=false;
			if(UtilityHelper.isNotEmptyObject(provider)){
				CourseProvider cp = (CourseProvider) provider;
				categoryCode = cp.getCategory();
				isGetAll=cp.isGetAll();
			}	
			conn=ConnectionManager.getConnection();
			/*final StringBuilder sb = new StringBuilder("SELECT  DISTINCT TOP 10 cp.CODE , CP.UNIQUEPREFIX  FROM ( ");
			sb.append(" SELECT  TOP 1000  PROV.CODE AS PROVIDERCODE,COUNT(*) AS HITCOUNT,PROG.CODE PROGRAMMCODE,PROG.NAME,  PROV.NAME AS PROVIDERNAME, PROV.UNIQUEPREFIX, PROG.CATEGORY");
			sb.append(" FROM [CAMPUS].COURSEPROVIDER PROV  INNER JOIN [CAMPUS].PROGRAMME PROG  ON  PROV.CODE=PROG.COURSEPROVIDER ");
			sb.append(" INNER JOIN [CAMPUS].CATEGORY CAT ON PROG.CATEGORY=CAT.CODE INNER JOIN [CAMPUS].PROGRAMMESTAT PSTAT ON PROG.CODE= PSTAT.PROGRAMME ");
			sb.append(" WHERE PROG.CATEGORY=CAT.CODE AND PROV.COURSEPROVIDERSTATUS=1 ");
			// sb.append(" AND PROG.PROGRAMMESTATUS=1 ")
			sb.append(" AND CAT.ISACTIVE=1  ");
			if (!isGetAll) {
				sb.append(" AND CAT.CODE= ? ");
			}
			sb.append(" GROUP BY PROG.CODE,PROG.NAME,PROV.CODE,PROV.NAME,PROV.UNIQUEPREFIX, PROG.CATEGORY ");
			sb.append(" ORDER BY HITCOUNT desc");
			sb.append(" ) NEWTABLE ");
			sb.append(" JOIN [CAMPUS].[COURSEPROVIDER] cp ON (cp.CODE = NEWTABLE.PROVIDERCODE) ");	*/
			
			final StringBuilder sb = new StringBuilder(" SELECT TOP 10 SUM(NEWTABLE.HITCOUNT) AS TOTAL , PROVIDER.CODE  AS CPCODE, PROVIDER.SHORTNAME AS CPSHORTNAME   FROM (");
			sb.append(" SELECT COUNT(*) AS HITCOUNT,	PROG.CODE AS PROGRAMMECODE ,PROG.NAME AS PROGRAMMENAME, PROG.COURSEPROVIDER  ");
			sb.append(" FROM  [CAMPUS].PROGRAMMESTAT PSTAT");
			sb.append(" LEFT OUTER JOIN [CAMPUS].PROGRAMME PROG ON PSTAT.PROGRAMME=PROG.CODE ");
			sb.append(" INNER JOIN [CAMPUS].CATEGORY CAT ON PROG.CATEGORY=CAT.CODE ");
			if (!isGetAll) {
				sb.append(" AND CAT.CODE= ? ");
			}
			sb.append(" GROUP BY PROG.CODE,PROG.NAME,PROG.COURSEPROVIDER ) NEWTABLE ");
			sb.append(" LEFT OUTER JOIN  [CAMPUS].[COURSEPROVIDER] PROVIDER ON NEWTABLE.COURSEPROVIDER=PROVIDER.CODE AND PROVIDER.COURSEPROVIDERSTATUS=1 ");
			sb.append(" GROUP BY PROVIDER.CODE ,PROVIDER.SHORTNAME ORDER BY TOTAL DESC ");
			
			stmt = conn.prepareStatement(sb.toString());			
			if(!isGetAll){
				stmt.setInt(1, categoryCode);	
			}	
			log.debug("SQL : " + sb.toString());
			final ResultSet rs = stmt.executeQuery();
			while (rs.next()) {				
				final ArrayList<String> singleProvider = new ArrayList<String>();
				singleProvider.add(rs.getString("CODE"));				
				singleProvider.add(rs.getString("UNIQUEPREFIX"));
				allProviderList.add(singleProvider);
			}
			
		} catch (SQLException sqlException) {
			log.info("findTopViewedProviders() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findTopViewedProviders() Exception" + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allProviderList;
	}
	
	/**
	 * Find Top Rated Providers
	 * @param CourseProvider DTO
	 * @author DJ
	 * @return Collection 
	 */

	public Collection<Collection<String>> findTopRatedProviders(CourseProvider provider) throws SQLException{
		
		Connection conn = null;
		PreparedStatement  stmt = null;	
		final Collection<Collection<String>> allProviderList = new ArrayList<Collection<String>>();
		
		try {
			int categoryCode=0;
			boolean isGetAll=false;
			if(UtilityHelper.isNotEmptyObject(provider)){
				CourseProvider cp = (CourseProvider) provider;
				categoryCode = cp.getCategory();
				isGetAll=cp.isGetAll();
			}	
			conn=ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder(" SELECT TOP 10 AVG(ISNULL(NEWTABLE.PROGAVERAGE,0)) AS CPAVERAGE , PROVIDER.CODE AS CPCODE ,PROVIDER.SHORTNAME AS CPSHORTNAME FROM ( ");
			sb.append(" SELECT AVG(ISNULL(RAT.RATINGVALUE,0)) AS PROGAVERAGE ,PROG.CODE AS PROGRAMMECODE, PROG.COURSEPROVIDER AS CPCODE ");
			sb.append(" FROM  [CAMPUS].RATING RAT INNER JOIN [CAMPUS].PROGRAMME PROG  ON  RAT.PROGRAMME=PROG.CODE");
			sb.append(" INNER JOIN [CAMPUS].CATEGORY CAT ON PROG.CATEGORY=CAT.CODE AND CAT.ISACTIVE=1 ");
			if (!isGetAll) {
				sb.append(" AND CAT.CODE=?");
			}
			sb.append(" GROUP BY PROG.CODE,PROG.COURSEPROVIDER,CAT.CODE) NEWTABLE");
			sb.append(" LEFT JOIN [CAMPUS].COURSEPROVIDER PROVIDER ON NEWTABLE.CPCODE=PROVIDER.CODE AND PROVIDER.COURSEPROVIDERSTATUS=1");
			sb.append(" GROUP BY PROVIDER.CODE,PROVIDER.SHORTNAME  ORDER BY CPAVERAGE DESC");

			stmt = conn.prepareStatement(sb.toString());			
			if(!isGetAll){
				stmt.setInt(1, categoryCode);	
			}	
			
			final ResultSet rs = stmt.executeQuery();
			while (rs.next()) {				
				final ArrayList<String> singleProvider = new ArrayList<String>();
				singleProvider.add(rs.getString("CPCODE"));				
				singleProvider.add(rs.getString("CPSHORTNAME"));
				allProviderList.add(singleProvider);
			}
			
		} catch (SQLException sqlException) {
			log.info("findTopRatedProviders() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findTopRatedProviders() Exception" + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allProviderList;		
	}

}
