package com.genesiis.campus.entity.dao;

//DJ 20170108 c6-list-available-institutes-on-the-view created ProgrammeDAO.java
//DJ 20170108 c6-list-available-institutes-on-the-view Implemented findMajorsByMajorCodes() and findLevelsByLevelCodes()
//DJ 20170118 c124-general-filter-search-programme Implemented wildCardSearchOnProgrammes().
//DJ 20170122 c124-general-filter-search-programme Implemented wildCardSearchOnProgrammes(final ProgrammeSearchDTO searchDTO).

import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.model.ProgrammeSearchDTO;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**The class  {@code ProgrammeDAO} is a form of DAO class.
* The Interface {@code ProgrammeDAO} has precise control over programme dao level manipulations. 
*  @author dumani DJ   
*/
public class ProgrammeDAOImpl implements ProgrammeICrud{
	
	static org.apache.log4j.Logger log = Logger.getLogger(ProgrammeDAOImpl.class.getName());

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
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
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

	/**
	 * Get all category details
	 * @param 
	 * @author dumani DJ
	 * @return Collection 
	 */
	@Override
	public Collection<Collection<String>> getAllCategories()
			throws SQLException, Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Collection<Collection<String>> allCategoryList=new ArrayList<Collection<String>>();
		try {
			conn=ConnectionManager.getConnection();
			String sql="SELECT CAT.CODE AS CATEGORYCODE , CAT.NAME AS CATEGORYNAME FROM [CAMPUS].CATEGORY CAT WHERE CAT.ISACTIVE=? ";
			
			stmt=conn.prepareStatement(sql.toString());
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			rs=stmt.executeQuery();
			
			while (rs.next()) {				
				final ArrayList<String> singleCategory = new ArrayList<String>();
				singleCategory.add(rs.getString("CATEGORYCODE"));				
				singleCategory.add(rs.getString("CATEGORYNAME"));				
				allCategoryList.add(singleCategory);
			}
		} catch (SQLException sqlException) {
			log.info("getAll() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		
		return allCategoryList;
	}	
	/**
	 * Get all major list details by major code set
	 * @param majorCodeSet
	 * @author DJ
	 * @return Collection 
	 */
	@Override
	public Collection<Collection<String>> findMajorsByMajorCodes(Set<Integer> majorCodeSet) throws SQLException, Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Collection<Collection<String>> allMajorList=new ArrayList<Collection<String>>();
		try {
			conn = ConnectionManager.getConnection();
			final StringBuilder sb =new StringBuilder(" SELECT MAJOR.[CODE] AS MAJORCODE,MAJOR.[NAME] AS MAJORNAME FROM [CAMPUS].[MAJOR] MAJOR ");
			sb.append(" WHERE MAJOR.ISACTIVE = ? AND MAJOR.CODE IN( " );
			boolean doneOne = false;
			for (Integer code : majorCodeSet) {
				if (doneOne) {
					sb.append(", ");
				}
				sb.append(code);
				doneOne = true;
			}
			sb.append(")" );

			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleMajorList = new ArrayList<String>();
				singleMajorList.add(rs.getString("MAJORCODE"));
				singleMajorList.add(rs.getString("MAJORNAME"));
				final Collection<String> singleMajorCollection = singleMajorList;
				allMajorList.add(singleMajorCollection);
			}
		} catch (SQLException sqlException) {
			log.info("findMajorsByMajorCodes() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findMajorsByMajorCodes() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		
		return allMajorList;
	}
	
	/**
	 * Get all education level list details by level code set
	 * @param levelCodeSet
	 * @author DJ
	 * @return Collection 
	 */
	@Override
	public Collection<Collection<String>> findLevelsByLevelCodes(Set<Integer> levelCodeSet) throws SQLException, Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Collection<Collection<String>> allLevelList=new ArrayList<Collection<String>>();
		try {
			conn=ConnectionManager.getConnection();			
			final StringBuilder sb =new StringBuilder("SELECT LEVEL.CODE AS LEVELCODE , LEVEL.NAME AS LEVELNAME FROM [CAMPUS].LEVEL LEVEL  ");
			sb.append(" WHERE LEVEL.ISACTIVE=? AND LEVEL.CODE IN (");
			boolean doneOne = false;
			for (Integer code : levelCodeSet) {
				if (doneOne) {
					sb.append(", ");
				}
				sb.append(code);
				doneOne = true;
			}
			sb.append(")" );
			stmt=conn.prepareStatement(sb.toString());
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
		    rs=stmt.executeQuery();
			
			while (rs.next()) {				
				final ArrayList<String> singleLevel = new ArrayList<String>();
				singleLevel.add(rs.getString("LEVELCODE"));				
				singleLevel.add(rs.getString("LEVELNAME"));				
				allLevelList.add(singleLevel);
			}
		} catch (SQLException sqlException) {
			log.info("findLevelsByLevelCodes() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findLevelsByLevelCodes() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		
		return allLevelList;
	}

	/**
	 * Get programme code set related to input key word in general filter search.
	 * @param keyWord Input string
	 * @author DJ dumani
	 * @return programmeCodeSet  Integer set
	 */
	@Override
	public Set<Integer> wildCardSearchOnProgrammes(String keyWord)throws SQLException, Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Set<Integer> programmeCodeSet=new HashSet<Integer>();		
		try {
			conn=ConnectionManager.getConnection();			
			final StringBuilder sb =new StringBuilder("SELECT PROG.CODE AS PROGCODE  FROM CAMPUS.PROGRAMME PROG WHERE ");
			sb.append(" ( PROG.NAME LIKE ?");
			sb.append(" OR PROG.DESCRIPTION LIKE ?");
			sb.append(" OR PROG.EMAIL LIKE ? )");			
			sb.append(" AND PROGRAMMESTATUS = ? ");			
			
			stmt=conn.prepareStatement(sb.toString());
			stmt.setString(1, keyWord);
			stmt.setString(2, keyWord);
			stmt.setString(3, keyWord);
			stmt.setInt(4, ApplicationStatus.ACTIVE.getStatusValue());
		    rs=stmt.executeQuery();
		    while (rs.next()) {				
		    	programmeCodeSet.add(Integer.valueOf(rs.getString("PROGCODE")));				
			}
		} catch (SQLException sqlException) {
			log.info("wildCardSearchOnProgrammes() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("wildCardSearchOnProgrammes() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}		
		return programmeCodeSet;	
	}
	
	/**
	 * Get programme list  related to input key word in general filter search.
	 * This is related to getAll() in SearchedProgrammeDAO.java to manage consistency.Select parameters are same in particular method. 
	 * @param searchDTO ProgrammeSearchDTO
	 * @author DJ dumani
	 * @return allProgrammeList Collection<Collection<String>>
	 */
	public Collection<Collection<String>> wildCardSearchOnProgrammes(final ProgrammeSearchDTO searchDTO)throws SQLException, Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Collection<Collection<String>> allProgrammeList=new ArrayList<Collection<String>>();
		
		try {
			conn=ConnectionManager.getConnection();			
			/*final StringBuilder sb =new StringBuilder("SELECT PROG.CODE AS PROGCODE  FROM CAMPUS.PROGRAMME PROG WHERE ");
			sb.append(" ( PROG.NAME LIKE ?");
			sb.append(" OR PROG.DESCRIPTION LIKE ?");
			sb.append(" OR PROG.EMAIL LIKE ? )");			
			sb.append(" AND PROGRAMMESTATUS = ? ");*/	
			final StringBuilder sb =new StringBuilder("SELECT PROG.CODE, PROG.NAME, CAST(PROG.[DESCRIPTION] as NVARCHAR(max)) AS [DESCRIPTION],PROG.DURATION  ,PROG.[ENTRYREQUIREMENTS] ,PROG.[COUNSELORNAME] ,");
			sb.append(" PROG.[COUNSELORPHONE] ,PROG.[DISPLAYSTARTDATE] ,PROG.[EXPIRYDATE] ,PROG.[PROGRAMMESTATUS] ,PROG.[COURSEPROVIDER] ,PROG.[MAJOR] ,PROG.[CATEGORY],PROG.[LEVEL] ,PROG.[CLASSTYPE], ");
			sb.append(" PROVIDER.[NAME] as PROVIDER, PROVIDER.[UNIQUEPREFIX],PROVIDER.[CODE] as [CPCODE] , PROVIDER.[WEBLINK],ISNULL(MIN(INTAKE.[FEE]),0.00) as COST");
			sb.append(" FROM CAMPUS.PROGRAMME PROG ");
			sb.append(" INNER JOIN  CAMPUS.COURSEPROVIDER PROVIDER  ON PROG.COURSEPROVIDER=PROVIDER.CODE");
			sb.append(" LEFT JOIN [CAMPUS].[INTAKE] INTAKE ON INTAKE.PROGRAMME=PROG.CODE");
			sb.append(" WHERE 1=1");
			if(searchDTO.getProgrammeStatus()>=0){
				sb.append(" AND  PROG.PROGRAMMESTATUS = ").append(searchDTO.getProgrammeStatus());
			}
			if(searchDTO.getKeyWordString()!=null && !searchDTO.getKeyWordString().isEmpty() ){
				sb.append(" AND ( PROG.NAME LIKE ?");
				sb.append(" OR PROG.DESCRIPTION LIKE ?");
				sb.append(" OR PROG.EMAIL LIKE ? )");
				
			}
			sb.append(" GROUP BY PROG.[CODE] ,PROG.[NAME] ,CAST(PROG.[DESCRIPTION] as NVARCHAR(max)) ,PROG.[DURATION] ,PROG.[ENTRYREQUIREMENTS] ,PROG.[COUNSELORNAME] , ");
			sb.append(" PROG.[COUNSELORPHONE] ,PROG.[DISPLAYSTARTDATE] ,PROG.[EXPIRYDATE] ,PROG.[PROGRAMMESTATUS] ,PROG.[COURSEPROVIDER] ,PROG.[MAJOR] ,PROG.[CATEGORY] ,");
			sb.append(" PROG.[LEVEL] ,PROG.[CLASSTYPE], PROVIDER.[NAME], PROVIDER.[UNIQUEPREFIX], PROVIDER.[CODE] , PROVIDER.[WEBLINK]");
			
			stmt=conn.prepareStatement(sb.toString());
			if (searchDTO.getKeyWordString() != null
					&& !searchDTO.getKeyWordString().isEmpty()) {
				stmt.setString(1, searchDTO.getKeyWordString());
				stmt.setString(2, searchDTO.getKeyWordString());
				stmt.setString(3, searchDTO.getKeyWordString());
			}
		    rs=stmt.executeQuery();		    
		    
		    while (rs.next()) {
				final ArrayList<String> singleProgrammeList = new ArrayList<String>();
				singleProgrammeList.add(rs.getString("CODE"));
				singleProgrammeList.add(rs.getString("NAME").replaceAll(",", "##"));		
				String description = DaoHelper.getSubDescription(rs.getString("DESCRIPTION")).replaceAll(",", "##");				
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
				//singleProgrammeList.add(formatDecimal(rs.getString("COST")));				
				allProgrammeList.add(singleProgrammeList);
			}
		} catch (SQLException sqlException) {
			log.info("wildCardSearchOnProgrammes() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("wildCardSearchOnProgrammes() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}		
		return allProgrammeList;	
	}

}
