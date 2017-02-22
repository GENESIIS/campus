package com.genesiis.campus.entity.dao;

//DJ 20170108 c6-list-available-institutes-on-the-view created ProgrammeDAO.java
//DJ 20170108 c6-list-available-institutes-on-the-view Implemented findMajorsByMajorCodes() and findLevelsByLevelCodes()
//DJ 20170117 c51-report-courses-by-course-provider-MP-dj Implemented getReportAllCourseProviders().

import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.model.ProgrammeSearchDTO;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**The class  {@code ProgrammeDAOImpl} is a form of DAO class.
 * The Interface {@code ProgrammeDAOImpl} has precise control over programme dao level manipulations. 
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
	 * Retrieve Programme details against course provider.Developer able to re use the query by adding conditions to the query builder by setting
	 * parameters to ProgrammeSearchDTO
	 * @param programmeDTO ProgrammeSearchDTO
	 * @author dumani DJ
	 * @return Collection of strings
	 */
	@Override
	public Collection<Collection<String>> getProgrammesForReport(final ProgrammeSearchDTO programmeSearchDTO) throws SQLException,
			Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		final Collection<Collection<String>> programmeList = new ArrayList<Collection<String>>();
		try {			
			conn = ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder("SELECT PROG.CODE AS PROGCODE, PROG.NAME AS PROGNAME ,  PROG.DESCRIPTION AS PROGDESCRIPTION ,PROV.NAME AS CPNAME , ");
			sb.append(" PROG.PROGRAMMESTATUS AS PROSTATUS, PROG.DISPLAYSTARTDATE AS PROGSTARTDATE, PROG.EXPIRYDATE AS PROGEXPIRYDATE ");
			sb.append(" FROM [CAMPUS].PROGRAMME PROG INNER JOIN  [CAMPUS].COURSEPROVIDER PROV ON PROG.COURSEPROVIDER=PROV.CODE WHERE 1=1 ");
			if (programmeSearchDTO.getProviderStatus() >= 0) {
				sb.append(" AND  PROV.COURSEPROVIDERSTATUS =  ");
				sb.append(programmeSearchDTO.getProviderStatus());
			}
			if (programmeSearchDTO.getCourseProvider() > 0) {
				sb.append(" AND PROG.COURSEPROVIDER =  ");
				sb.append(programmeSearchDTO.getCourseProvider());
			}
			if (programmeSearchDTO.getDisplayStartDate() != null	&& programmeSearchDTO.getDisplayStartDate().getTime() > 0) {
				sb.append("AND PROG.DISPLAYSTARTDATE >= ' ");
				sb.append(new java.sql.Date(programmeSearchDTO.getDisplayStartDate().getTime()));
				sb.append(" ' ");
			}
			if (programmeSearchDTO.getExpiryDate() != null	&& programmeSearchDTO.getExpiryDate().getTime() > 0) {
				sb.append("AND PROG.EXPIRYDATE <= '");
				sb.append(new java.sql.Date(programmeSearchDTO.getExpiryDate().getTime()));
				sb.append(" ' ");
			}
			if (programmeSearchDTO.getProgrammeStatus() >=0) {
				sb.append(" AND PROG.PROGRAMMESTATUS =  ");
				sb.append(programmeSearchDTO.getProgrammeStatus());
			}

			stmt = conn.prepareStatement(sb.toString());
			resultSet= stmt.executeQuery();			
			while (resultSet.next()) {
				final ArrayList<String> singleProgramme = new ArrayList<String>();
				singleProgramme.add(resultSet.getString("PROGCODE"));
				singleProgramme.add(resultSet.getString("PROGNAME"));				
				singleProgramme.add(resultSet.getString("PROGDESCRIPTION"));				
				singleProgramme.add(resultSet.getString("CPNAME"));				
				singleProgramme.add(ApplicationStatus.getApplicationStatus(resultSet.getInt("PROSTATUS")));				
				singleProgramme.add(resultSet.getString("PROGSTARTDATE"));				
				singleProgramme.add(resultSet.getString("PROGEXPIRYDATE"));				
				programmeList.add(singleProgramme);
			}

		} catch (SQLException sqlException) {
			log.info("findById() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return programmeList;
	}

	

}
