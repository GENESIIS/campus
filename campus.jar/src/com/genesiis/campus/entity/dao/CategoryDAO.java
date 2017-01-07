package com.genesiis.campus.entity.dao;

//DJ 20161111 c17-provider-criteria-based-filter-search-MP-dj created CategoryDAO.java

import com.genesiis.campus.entity.ICrud;
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


public class CategoryDAO  implements ICrud{
	
	static org.apache.log4j.Logger log = Logger.getLogger(CategoryDAO.class.getName());

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
	 * Get all category details
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

}
