package com.genesiis.campus.entity.dao;

//20170111 DJ c52-report-banner-statistics-MP-dj Initiated BannerDAOImpl.java

import com.genesiis.campus.entity.BannerICrud;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class BannerDAOImpl implements BannerICrud{

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
	 * Retrieve Page details(page code and page name) result set
	 * 
	 * @author DJ
	 * @return Collection
	 */
	@Override
	public Collection<Collection<String>> getAllPages() throws SQLException,
			Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		final Collection<Collection<String>> pageList = new ArrayList<Collection<String>>();
		try {
			conn=ConnectionManager.getConnection();
			String sql="SELECT PAGE.CODE AS PAGECODE, PAGE.NAME AS PAGENAME FROM [CAMPUS].[PAGE] PAGE WHERE PAGE.ISACTIVE=?";
			
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, ApplicationStatus.ACTIVE.getStatusValue());
			resultSet= stmt.executeQuery();	
			while (resultSet.next()) {
				final ArrayList<String> singlePageList = new ArrayList<String>();
				singlePageList.add(resultSet.getString("PAGECODE"));
				singlePageList.add(resultSet.getString("PAGENAME"));				
				pageList.add(singlePageList);
			}
		
		} catch (SQLException sqlException) {
			log.info("getAll() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return pageList;
	}

	@Override
	public Collection<Collection<String>> getAllPageSlotByPageCode(int pageCode)
			throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Collection<String>> getBannerByPageSlotCode(
			int pageSlotCode) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
