package com.genesiis.campus.entity;
//DJ 20161026 c6-list-available-institutes-on-the-view created InstituteProviderDAO.java
//DJ 20161028 c6-list-available-institutes-on-the-view created findById()
//DJ 20161030 c6-list-available-institutes-on-the-view refactored query to identified get all institutes 
//DJ 20161031 c6-list-available-institutes-on-the-view rename the class name as  CourseProviderDAO.java
//DJ 20161031 c6-list-available-institutes-on-the-view create findTopViewedProviders()
//DJ 20161103 c6-list-available-institutes-on-the-view Implemented findTopViewedProviders()

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
				CourseProvider cp = (CourseProvider) code;
				categoryCode = cp.getCategory();
				isGetAll=cp.isGetAll();
			}			
			
			final StringBuilder sb = new StringBuilder("SELECT DISTINCT PROV.CODE, PROV.UNIQUEPREFIX , PROV.NAME ");
			sb.append("FROM [CAMPUS].COURSEPROVIDER PROV  INNER JOIN [CAMPUS].PROGRAMME PROG  on  PROV.CODE=PROG.COURSEPROVIDER ");
			sb.append("INNER JOIN [CAMPUS].CATEGORY CAT ON PROG.CATEGORY=CAT.CODE WHERE ");
			sb.append("PROG.CATEGORY=CAT.CODE AND PROV.COURSEPROVIDERSTATUS=1  AND PROG.PROGRAMMESTATUS=1  AND CAT.ISACTIVE=1 ");			
			if(!isGetAll){
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
			conn=ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder("SELECT  DISTINCT TOP 10 cp.CODE , CP.UNIQUEPREFIX  FROM ( ");
			sb.append(" SELECT  TOP 1000  PROV.CODE AS PROVIDERCODE,COUNT(*) AS HITCOUNT,PROG.CODE PROGRAMMCODE,PROG.NAME,  PROV.NAME AS PROVIDERNAME, PROV.UNIQUEPREFIX, PROG.CATEGORY");
			sb.append(" FROM [CAMPUS].COURSEPROVIDER PROV  INNER JOIN [CAMPUS].PROGRAMME PROG  ON  PROV.CODE=PROG.COURSEPROVIDER ");
			sb.append(" INNER JOIN [CAMPUS].CATEGORY CAT ON PROG.CATEGORY=CAT.CODE INNER JOIN [CAMPUS].PROGRAMMESTAT PSTAT ON PROG.CODE= PSTAT.PROGRAMME ");
			sb.append("  WHERE PROG.CATEGORY=CAT.CODE AND PROV.COURSEPROVIDERSTATUS=1 " );
			//sb.append(" AND PROG.PROGRAMMESTATUS=1 ")
			sb.append("  AND CAT.ISACTIVE=1  ");
			//sb.append(" AND CAT.CODE=3  ");
			sb.append(" GROUP BY PROG.CODE,PROG.NAME,PROV.CODE,PROV.NAME,PROV.UNIQUEPREFIX, PROG.CATEGORY ");
			sb.append("  ORDER BY HITCOUNT desc");
			sb.append(" ) NEWTABLE ");
			sb.append(" JOIN [CAMPUS].[COURSEPROVIDER] cp ON (cp.CODE = NEWTABLE.PROVIDERCODE) ");			
			
			stmt = conn.prepareStatement(sb.toString());			
			//stmt.setInt(1, 1);	
			
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

}
