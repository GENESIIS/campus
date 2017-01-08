package com.genesiis.campus.entity.dao;

//DJ 20170108 c6-list-available-institutes-on-the-view created ProgrammeDAO.java
//DJ 20170108 c6-list-available-institutes-on-the-view Implemented findMajorsByMajorCodes() and findLevelsByLevelCodes()

import com.genesiis.campus.entity.ProgrammeICrud;
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
import java.util.Set;

/**The class  {@code ProgrammeDAO} is a form of DAO class.
 * The Interface {@code ProgrammeDAO} has precise control over programme dao level manipulations. 
 *  @author dumani DJ   
 */
public class ProgrammeDAO implements ProgrammeICrud{
	
	static org.apache.log4j.Logger log = Logger.getLogger(ProgrammeDAO.class.getName());

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

	@Override
	public Collection<Collection<String>> getAllMajors() throws SQLException,
			Exception {
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

	@Override
	public Collection<Collection<String>> getAllLevels() throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Collection<String>> getAllSemesters()
			throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Collection<String>> getAllProgrammeInterests()
			throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Get all major list details
	 * @param 
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
			String query = "SELECT [CODE],[NAME],[DESCRIPTION] FROM [CAMPUS].[MAJOR] WHERE [ISACTIVE] = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleMajorList = new ArrayList<String>();
				singleMajorList.add(rs.getString("CODE"));
				singleMajorList.add(rs.getString("NAME"));
				singleMajorList.add(rs.getString("DESCRIPTION"));

				final Collection<String> singleMajorCollection = singleMajorList;
				allMajorList.add(singleMajorCollection);
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
		
		return allMajorList;
	}
	
	/**
	 * Get all Level details
	 * @param 
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
			String sql="SELECT LEVEL.CODE AS LEVELCODE , LEVEL.NAME AS LEVELNAME FROM [CAMPUS].LEVEL LEVEL WHERE LEVEL.ISACTIVE=? ";
			
			stmt=conn.prepareStatement(sql.toString());
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			rs=stmt.executeQuery();
			
			while (rs.next()) {				
				final ArrayList<String> singleLevel = new ArrayList<String>();
				singleLevel.add(rs.getString("LEVELCODE"));				
				singleLevel.add(rs.getString("LEVELNAME"));				
				allLevelList.add(singleLevel);
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
		
		return allLevelList;
	}

}
