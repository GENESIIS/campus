package com.genesiis.campus.entity.dao;

//DJ 20170108 c6-list-available-institutes-on-the-view created ProgrammeDAO.java
//DJ 20170108 c6-list-available-institutes-on-the-view Implemented findMajorsByMajorCodes() and findLevelsByLevelCodes()
//DJ 20170203 c138-add-basic-programme Implemented getAllMajors() method.
//DJ 20170203 c138-add-basic-programme Implemented getAllLevels() method.
//DJ 20170203 c138-add-basic-programme Implemented getAllClassTypes() method.
//DJ 20170207 c138-add-basic-programme Initiated addProgrammeDetails() method.

import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.model.ProgrammeDTO;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**The class  {@code ProgrammeDAO} is a form of DAO class.
 * The Interface {@code ProgrammeDAO} has precise control over programme dao level manipulations. 
 *  @author dumani DJ   
 */
public class ProgrammeDAOImpl implements ProgrammeICrud{
	
	static Logger log = Logger.getLogger(ProgrammeDAOImpl.class.getName());

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

	@Override
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}	

	/**
	 * Get all active category details	  
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
				allMajorList.add(singleMajorList);
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
	 * Get all active major list details 
	 * @author DJ
	 * @return Collection MajorCode,MajorName
	 */
	@Override
	public Collection<Collection<String>> getAllMajors() throws SQLException,
			Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Collection<Collection<String>> allMajorList=new ArrayList<Collection<String>>();
		try {
			conn = ConnectionManager.getConnection();
			String sb = "SELECT MAJOR.[CODE] AS MAJORCODE,MAJOR.[NAME] AS MAJORNAME FROM [CAMPUS].[MAJOR] MAJOR  WHERE MAJOR.ISACTIVE = ?";	
			stmt = conn.prepareStatement(sb);
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleMajorList = new ArrayList<String>();
				singleMajorList.add(rs.getString("MAJORCODE"));
				singleMajorList.add(rs.getString("MAJORNAME"));				
				allMajorList.add(singleMajorList);
			}
		} catch (SQLException sqlException) {
			log.info("getAllMajors() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAllMajors() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		
		return allMajorList;
	}
	/**
	 * Get all Active  education level list details.	 
	 * @author DJ
	 * @return Collection LevelCode,LevelName
	 */
	@Override
	public Collection<Collection<String>> getAllLevels() throws SQLException,
			Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Collection<Collection<String>> allLevelList=new ArrayList<Collection<String>>();
		try {
			conn=ConnectionManager.getConnection();			
			String sql="SELECT LEVEL.CODE AS LEVELCODE , LEVEL.NAME AS LEVELNAME FROM [CAMPUS].LEVEL LEVEL  WHERE LEVEL.ISACTIVE=? ";			
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
		    rs=stmt.executeQuery();
			
			while (rs.next()) {				
				final ArrayList<String> singleLevel = new ArrayList<String>();
				singleLevel.add(rs.getString("LEVELCODE"));				
				singleLevel.add(rs.getString("LEVELNAME"));				
				allLevelList.add(singleLevel);
			}
		} catch (SQLException sqlException) {
			log.info("getAllLevels() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAllLevels() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}		
		return allLevelList;
	}
	
	/**
	 * Get all Active  education class Type list details.	 
	 * @author DJ
	 * @return Collection ClassTypeCode,ClassTypeName
	 */

	@Override
	public Collection<Collection<String>> getAllClassTypes()
			throws SQLException, Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Collection<Collection<String>> allClassTypeList=new ArrayList<Collection<String>>();
		try {
			conn=ConnectionManager.getConnection();			
			String sql="SELECT CLASSTYPE.CODE AS CLASSTYPECODE , CLASSTYPE.NAME AS CLASSTYPENAME FROM [CAMPUS].CLASSTYPE CLASSTYPE  WHERE CLASSTYPE.ISACTIVE=? ";			
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
		    rs=stmt.executeQuery();
			
			while (rs.next()) {				
				final ArrayList<String> singleClassType = new ArrayList<String>();
				singleClassType.add(rs.getString("CLASSTYPECODE"));				
				singleClassType.add(rs.getString("CLASSTYPENAME"));				
				allClassTypeList.add(singleClassType);
			}
		} catch (SQLException sqlException) {
			log.info("getAllClassTypes() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAllClassTypes() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}		
		return allClassTypeList;
	}

	@Override
	public int addProgrammeDetails(final ProgrammeDTO programmeDTO) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder("INSERT INTO [CAMPUS].[PROGRAMME]");
			sb.append("([NAME]	 ,[EMAIL]  ,[DESCRIPTION], [DURATION] , [ENTRYREQUIREMENTS], [COUNSELORNAME], [COUNSELORPHONE] ,[DISPLAYSTARTDATE],[EXPIRYDATE],");
			sb.append(" [PROGRAMMESTATUS] ,[COURSEPROVIDER] ,[MAJOR],[CATEGORY],[LEVEL],[CLASSTYPE] ,[CRTON] ,[CRTBY],[MODON] ,[MODBY]");
			sb.append(" VALUES ( ");
			sb.append(" ? , ? , ? , ? ,? , ? ,? , ? ,? , ? ,? , ? ,? , ? ,? , ? ,? , ? ,?");
			sb.append(" ) ");

			stmt=conn.prepareStatement(sb.toString());
			stmt.setString(1, programmeDTO.getName());
			stmt.setString(2, programmeDTO.getEmail());
			stmt.setString(3, programmeDTO.getDescription());
			stmt.setString(4, programmeDTO.getDuration());
			stmt.setString(5, programmeDTO.getEntryRequiremtns());
			stmt.setString(6, programmeDTO.getCounselerName());
			stmt.setString(7, programmeDTO.getCounselerPhone());
			stmt.setDate(8, new java.sql.Date(programmeDTO.getDisplayDatrtDate().getTime()));
			stmt.setDate(9, new java.sql.Date(programmeDTO.getExpirationDate().getTime()));
			stmt.setInt(10, programmeDTO.getProgrammeStatus());
			stmt.setInt(11, programmeDTO.getCourseProvider());
			stmt.setInt(12, programmeDTO.getMajor());
			stmt.setInt(13, programmeDTO.getCategory());
			stmt.setInt(14, programmeDTO.getLevel());
			stmt.setInt(15, programmeDTO.getClassType());
			stmt.setDate(16, new java.sql.Date(programmeDTO.getCrtOn().getTime()));
			stmt.setString(17, programmeDTO.getCrtBy());
			stmt.setDate(18,  new java.sql.Date(programmeDTO.getModOn().getTime()));
			stmt.setString(19, programmeDTO.getModBy());
			
			rs = stmt.executeQuery();

		} catch (SQLException sqlException) {
			log.info("addProgrammeDetails() sqlException"
					+ sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("addProgrammeDetails() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		return 0;
	}

}
