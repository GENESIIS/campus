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
//DJ 20161115 c6-list-available-institutes-on-the-view refactored getCourseProviderResultSet() method and finally clause
//DJ 20161122 c6-list-available-institutes-on-the-view mx-fixes findTopViewedProviders()/findTopRatedProviders method 
//DJ 20161117 c17-provider-criteria-based-filter-search Initiate findFilterdCourseProviders() method
//DJ 20161117 c17-provider-criteria-based-filter-search Implement findFilterdCourseProviders() method
//DJ 20161123 c17-provider-criteria-based-filter-search findFilterdCourseProviders()-add course provider type list to the query
//DJ 20161124 c17-provider-criteria-based-filter-search findFilterdCourseProviders()-reform the query to support  multiples in clauses
//DJ 20161124 c17-provider-criteria-based-filter-search Implemented getCategoryWiseTypes() method
//DJ 20161202 c17-provider-criteria-based-filter-search Add new ApplicationStatus mechanism



import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderResultDTO;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;
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
	 * Get category wise course providers
	 * which only have programmes
	 * @param category code
	 * @author DJ
	 * @return Collection 
	 */

	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement  stmt = null;	
		ResultSet resultSet =null;
		Collection<Collection<String>> allProviderList = new ArrayList<Collection<String>>();
		
		try {
			conn=ConnectionManager.getConnection();
			int categoryCode=0;
			if(UtilityHelper.isNotEmptyObject(code)){
				final CourseProvider cp = (CourseProvider) code;
				categoryCode = cp.getCategory();
				}			
			//categorystatus=1 and courseproviderstatus=1 ; this can be change in future.
			final StringBuilder sb = new StringBuilder("SELECT DISTINCT PROV.CODE AS CPCODE, PROV.UNIQUEPREFIX  ");
			sb.append("FROM [CAMPUS].COURSEPROVIDER PROV  INNER JOIN [CAMPUS].PROGRAMME PROG  ON  PROV.CODE=PROG.COURSEPROVIDER ");
			sb.append("INNER JOIN [CAMPUS].CATEGORY CAT ON PROG.CATEGORY=CAT.CODE WHERE ");
			sb.append("PROG.CATEGORY=CAT.CODE AND PROV.COURSEPROVIDERSTATUS=? ");			
			// sb.append("AND PROG.PROGRAMMESTATUS=1 ");
			sb.append("AND CAT.ISACTIVE=? AND CAT.CODE=?");			
			 
			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			stmt.setInt(2, ApplicationStatus.ACTIVE.getStatusValue());
			stmt.setInt(3, categoryCode);
			resultSet= stmt.executeQuery();
			allProviderList=getCourseProviderResultSet(resultSet, allProviderList);
			
		} catch (SQLException sqlException) {
			log.info("findById() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return allProviderList;
	}
	
	/**
	 * Get all types of active course providers
	 * @param 
	 * @author DJ
	 * @return Collection 
	 */

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet =null;
		Collection<Collection<String>> allProviderList = new ArrayList<Collection<String>>();

		try {
			conn = ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder("SELECT PROV.CODE AS CPCODE , PROV.UNIQUEPREFIX  FROM [CAMPUS].COURSEPROVIDER PROV WHERE PROV.COURSEPROVIDERSTATUS = ? ");

			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			resultSet= stmt.executeQuery();
			allProviderList=getCourseProviderResultSet(resultSet, allProviderList);

		} catch (SQLException sqlException) {
			log.info("findById() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return allProviderList;
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

	public Collection<Collection<String>> findTopViewedProviders(CourseProvider provider) throws SQLException,Exception{
		Connection conn = null;
		PreparedStatement  stmt = null;
		ResultSet resultSet =null;
		 Collection<Collection<String>> allProviderList = new ArrayList<Collection<String>>();
		
		try {			
			CourseProvider cProvider=new CourseProvider();
			if(UtilityHelper.isNotEmptyObject(provider)){
				cProvider = (CourseProvider) provider;								
			}else{
				return allProviderList;
			}	
			conn=ConnectionManager.getConnection();	
			final StringBuilder sb = new StringBuilder(" SELECT TOP 10 SUM(NEWTABLE.HITCOUNT) AS TOTAL , PROVIDER.CODE  AS CPCODE, PROVIDER.UNIQUEPREFIX AS UNIQUEPREFIX   FROM (");
			sb.append(" SELECT COUNT(*) AS HITCOUNT,	PROG.CODE AS PROGRAMMECODE ,PROG.NAME AS PROGRAMMENAME, PROG.COURSEPROVIDER  ");
			sb.append(" FROM  [CAMPUS].PROGRAMMESTAT PSTAT");
			sb.append(" INNER JOIN [CAMPUS].PROGRAMME PROG ON PSTAT.PROGRAMME=PROG.CODE ");
			sb.append(" INNER JOIN [CAMPUS].CATEGORY CAT ON PROG.CATEGORY=CAT.CODE ");
			if (cProvider.getCategory()>0) {
				sb.append(" AND CAT.CODE = ");	
				sb.append(cProvider.getCategory());
			}
			sb.append(" GROUP BY PROG.CODE,PROG.NAME,PROG.COURSEPROVIDER ) NEWTABLE ");
			sb.append(" INNER JOIN  [CAMPUS].[COURSEPROVIDER] PROVIDER ON NEWTABLE.COURSEPROVIDER=PROVIDER.CODE AND PROVIDER.COURSEPROVIDERSTATUS=  ");
			sb.append(cProvider.getCourseProviderStatus());
			sb.append(" GROUP BY PROVIDER.CODE ,PROVIDER.UNIQUEPREFIX ORDER BY TOTAL DESC ");
			
			stmt = conn.prepareStatement(sb.toString());			
				
			log.debug("SQL : " + sb.toString());
			resultSet= stmt.executeQuery();
			allProviderList=getCourseProviderResultSet(resultSet, allProviderList);
			
		} catch (SQLException sqlException) {
			log.info("findTopViewedProviders() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findTopViewedProviders() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return allProviderList;
	}
	
	/**
	 * Find Top Rated Providers
	 * @param CourseProvider DTO
	 * @author DJ
	 * @return Collection 
	 */

	public Collection<Collection<String>> findTopRatedProviders(CourseProvider provider) throws SQLException,Exception{
		
		Connection conn = null;
		PreparedStatement  stmt = null;
		ResultSet resultSet =null;
		Collection<Collection<String>> allProviderList = new ArrayList<Collection<String>>();
		
		try {			
			CourseProvider cProvider=new CourseProvider();			
			if(UtilityHelper.isNotEmptyObject(provider)){
				cProvider = (CourseProvider) provider;
			}else{
				return allProviderList;
			}	
			conn=ConnectionManager.getConnection();			
			
			final StringBuilder sb = new StringBuilder(" SELECT TOP 10 AVG(ISNULL(NEWTABLE.PROGAVERAGE,0)) AS CPAVERAGE , PROVIDER.CODE AS CPCODE ,PROVIDER.UNIQUEPREFIX AS UNIQUEPREFIX  FROM ( ");
			sb.append(" SELECT AVG(ISNULL(RAT.RATINGVALUE,0)) AS PROGAVERAGE ,PROG.CODE AS PROGRAMMECODE, PROG.COURSEPROVIDER AS CPCODE ");
			sb.append(" FROM  [CAMPUS].RATING RAT INNER JOIN [CAMPUS].PROGRAMME PROG  ON  RAT.PROGRAMME=PROG.CODE");
			sb.append(" INNER JOIN [CAMPUS].CATEGORY CAT ON PROG.CATEGORY=CAT.CODE AND CAT.ISACTIVE= ");
			sb.append(ApplicationStatus.ACTIVE.getStatusValue());
			if (cProvider.getCategory()>0) {
				sb.append(" AND CAT.CODE= ");	
				sb.append(cProvider.getCategory());
			}
			sb.append(" GROUP BY PROG.CODE,PROG.COURSEPROVIDER,CAT.CODE) NEWTABLE");
			sb.append(" INNER JOIN [CAMPUS].COURSEPROVIDER PROVIDER ON NEWTABLE.CPCODE=PROVIDER.CODE AND PROVIDER.COURSEPROVIDERSTATUS = ");
			sb.append(cProvider.getCourseProviderStatus());
			sb.append(" GROUP BY PROVIDER.CODE,PROVIDER.UNIQUEPREFIX ORDER BY CPAVERAGE DESC");

			stmt = conn.prepareStatement(sb.toString());			
			
			resultSet= stmt.executeQuery();
			allProviderList=getCourseProviderResultSet(resultSet,allProviderList);			
			
		} catch (SQLException sqlException) {
			log.info("findTopRatedProviders() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findTopRatedProviders() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return allProviderList;		
	}
	
	/**
	 * @param rs
	 * @param allProviderList
	 * @author DJ
	 * @return Collection
	 * @throws SQLException
	 */
	public  Collection<Collection<String>> getCourseProviderResultSet(ResultSet rs, Collection<Collection<String>> allProviderList)throws SQLException ,Exception{
		while (rs.next()) {				
			final ArrayList<String> singleProvider = new ArrayList<String>();
			singleProvider.add(rs.getString("CPCODE"));				
			singleProvider.add(rs.getString("UNIQUEPREFIX"));			
			allProviderList.add(singleProvider);
		}
		return allProviderList;
		
	}
	
	/**
	 * Find filter search course providers
	 * @param CourseProviderSearchDTO 
	 * @author DJ
	 * @return Collection 
	 */

	public Collection<Collection<String>> findFilterdCourseProviders(CourseProviderSearchDTO providerSearchDTO ) throws SQLException,Exception{		
		Connection conn = null;
		PreparedStatement  stmt = null;
		ResultSet resultSet =null;
		Collection<Collection<String>> allProviderList = new ArrayList<Collection<String>>();		
		try {
			int categoryCode=0;
			boolean isGetAll=false;
			CourseProviderSearchDTO searchDTO =null;
			//TODO: Should set filter values
			if(UtilityHelper.isNotEmptyObject(providerSearchDTO)){
				searchDTO = (CourseProviderSearchDTO) providerSearchDTO;
				categoryCode = searchDTO.getCategory();				
			}else{
				return allProviderList;
			}			
			conn = ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder(" SELECT  DISTINCT PROVIDER.CODE AS CPCODE , PROVIDER.UNIQUEPREFIX AS UNIQUEPREFIX ,PROVIDER.LOGOIMAGEPATH AS LOGOPATH  FROM [CAMPUS].COURSEPROVIDER PROVIDER ");
			sb.append(" INNER JOIN [CAMPUS].PROGRAMME PROG ON PROVIDER.CODE=PROG.COURSEPROVIDER");
			sb.append(" INNER JOIN [CAMPUS].COURSEPROVIDERTOWN CPTOWN ON PROVIDER.CODE=CPTOWN.COURSEPROVIDER");
			sb.append(" INNER JOIN [CAMPUS].[TOWN] TOWN ON TOWN.CODE = CPTOWN.TOWN ");
			sb.append(" INNER JOIN [CAMPUS].[DISTRICT] DISTRICT ON DISTRICT.CODE = TOWN.DISTRICT");
			sb.append(" AND PROVIDER.COURSEPROVIDERSTATUS= ");
			sb.append( ApplicationStatus.ACTIVE.getStatusValue());
					
			if (searchDTO.getCategoryList() != null && !searchDTO.getCategoryList().isEmpty()) {
				sb.append(" AND PROG.CATEGORY in ( ");
				boolean doneOne = false;
				for(Integer code: searchDTO.getCategoryList()){
					if(doneOne){
			            sb.append(", ");
			        }
					sb.append(code);
					doneOne = true;
				}
				sb.append(" ) ");
			}		
			if (searchDTO.getCpTypeList() != null && !searchDTO.getCpTypeList().isEmpty()) {
				sb.append(" AND PROVIDER.COURSEPROVIDERTYPE in ( ");
				boolean doneOne = false;
				for(Integer code: searchDTO.getCpTypeList()){
					if(doneOne){
			            sb.append(", ");
			        }
					sb.append(code);
					doneOne = true;
				}
				sb.append(" ) ");
			}
			if (searchDTO.getLevelList()!=null && !searchDTO.getLevelList().isEmpty() ) {
				sb.append(" AND PROG.LEVEL in ( ");
				boolean doneOne = false;
				for(Integer code: searchDTO.getLevelList()){
					if(doneOne){
			            sb.append(", ");
			        }
					sb.append(code);
					doneOne = true;
				}
				sb.append(" ) ");
			}
			if (searchDTO.getMajorList()!=null && !searchDTO.getMajorList().isEmpty() ) {
				sb.append(" AND PROG.MAJOR in ( ");
				boolean doneOne = false;
				for(Integer code: searchDTO.getMajorList()){
					if(doneOne){
						sb.append(", ");
					}
					sb.append(code);
					doneOne = true;
				}
				sb.append(" ) ");
			}			
			if (searchDTO.getDistrict() > 0) {
				sb.append(" AND DISTRICT.CODE=? ");
			}

			stmt = conn.prepareStatement(sb.toString());
		
			if (searchDTO.getDistrict() > 0) {
				stmt.setInt(1, searchDTO.getDistrict());			
			}			
			resultSet= stmt.executeQuery();
			allProviderList=getCourseProviderResultSet(resultSet,allProviderList);
			
		} catch (SQLException sqlException) {
			log.info("findFilterdCourseProviders() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findFilterdCourseProviders() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		
		return allProviderList;
		
	}

	/**
	 * Find category wise course provider types, majors,levels
	 * @param Integer 
	 * @author DJ
	 * @return CourseProviderResultDTO 
	 */
	public List<CourseProviderResultDTO> getCategoryWiseTypes(Integer categoryCode) throws SQLException,Exception{
		Connection conn = null;
		PreparedStatement  stmt = null;
		ResultSet rs =null;		
		final List<CourseProviderResultDTO> dtos=new ArrayList<CourseProviderResultDTO>();
		try {
			conn=ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder("SELECT CP.COURSEPROVIDERTYPE AS COURSEPROVIDERTYPE, PROG.MAJOR AS MAJOR,PROG.LEVEL AS LEVEL ,PROG.CATEGORY FROM [CAMPUS].COURSEPROVIDER CP ");
			sb.append(" INNER JOIN [CAMPUS].PROGRAMME PROG ON CP.CODE=PROG.COURSEPROVIDER WHERE PROG.CATEGORY=? "); 
			
            stmt = conn.prepareStatement(sb.toString());
            stmt.setInt(1, categoryCode);
            rs= stmt.executeQuery();
            while (rs.next()) {				
    			final CourseProviderResultDTO reusltDTO = new CourseProviderResultDTO();
    			reusltDTO.setCourserProviderType(rs.getInt("COURSEPROVIDERTYPE"));
    			reusltDTO.setMajor(rs.getInt("MAJOR"));				
    			reusltDTO.setLevel(rs.getInt("LEVEL"));    			
    			dtos.add(reusltDTO);
    		}    		
            
		} catch (SQLException sqlException) {
			log.info("getCategoryWiseTypes() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getCategoryWiseTypes() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}		
		return dtos;
	}

}
