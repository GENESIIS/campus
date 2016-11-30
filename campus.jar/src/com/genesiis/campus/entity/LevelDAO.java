package com.genesiis.campus.entity;

//DJ 20161115 c17-provider-criteria-based-filter-search-MP-dj created LevelDAO.java
//DJ 20161118 c17-provider-criteria-based-filter-search-MP-dj created getAll() method
//DJ 20161125 c17-provider-criteria-based-filter-search-MP-dj Implement findLevelsByLevelCodes() method

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;


public class LevelDAO  implements ICrud{
	static org.apache.log4j.Logger log = Logger.getLogger(LevelDAO.class.getName());

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
	/**
	 * Get all Level details
	 * @param 
	 * @author DJ
	 * @return Collection 
	 */

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Collection<Collection<String>> allLevelList=new ArrayList<Collection<String>>();
		try {
			conn=ConnectionManager.getConnection();
			String sql="SELECT LEVEL.CODE AS LEVELCODE , LEVEL.NAME AS LEVELNAME FROM [CAMPUS].LEVEL LEVEL WHERE LEVEL.ISACTIVE=1 ";
			
			stmt=conn.prepareStatement(sql.toString());
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
	 * Get all education level list details by level code set
	 * @param levelCodeSet
	 * @author DJ
	 * @return Collection 
	 */
	public Collection<Collection<String>> findLevelsByLevelCodes(Set<Integer> levelCodeSet)  throws SQLException,Exception{
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Collection<Collection<String>> allLevelList=new ArrayList<Collection<String>>();
		try {
			conn=ConnectionManager.getConnection();			
			final StringBuilder sb =new StringBuilder("SELECT LEVEL.CODE AS LEVELCODE , LEVEL.NAME AS LEVELNAME FROM [CAMPUS].LEVEL LEVEL  ");
			sb.append(" WHERE LEVEL.ISACTIVE=1 AND LEVEL.CODE IN (");
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


}
