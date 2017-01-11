package com.genesiis.campus.entity.dao;

//20170111 DJ c52-report-banner-statistics-MP-dj Initiated BannerDAOImpl.java
//20170111 DJ c52-report-banner-statistics-MP-dj Implemented getAllPages() , getAllPageSlotByPageCode() methods.
//20170111 DJ c52-report-banner-statistics-MP-dj Implemented getBannerProviderByPageSlotCode() methods.


import com.genesiis.campus.entity.BannerICrud;
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

public class BannerDAOImpl implements BannerICrud{
	static Logger log = Logger.getLogger(BannerDAOImpl.class.getName());
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
			log.info("getAllPages() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAllPages() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return pageList;
	}
	
	/**
	 * Retrieve active Page Slots result set by page code.
	 * @param pageCode page code number
	 * @author DJ
	 * @return Collection
	 */
	@Override
	public Collection<Collection<String>> getAllPageSlotByPageCode(int pageCode)
			throws SQLException, Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		final Collection<Collection<String>> pageSlotList = new ArrayList<Collection<String>>();		       
		try {			
			conn=ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder(
					"SELECT SLOT.CODE AS PAGESLOTCODE , SLOT.NAME AS PAGESLOTNAME FROM [CAMPUS].[PAGESLOT] SLOT WHERE");
			sb.append(" SLOT.PAGE= ? AND SLOT.ISACTIVE= ?");
			stmt=conn.prepareStatement(sb.toString());
			stmt.setInt(1, pageCode);
			stmt.setInt(2, ApplicationStatus.ACTIVE.getStatusValue());
			resultSet= stmt.executeQuery();	
			while (resultSet.next()) {
				final ArrayList<String> singleSlot = new ArrayList<String>();
				singleSlot.add(resultSet.getString("PAGESLOTCODE"));
				singleSlot.add(resultSet.getString("PAGESLOTNAME"));				
				pageSlotList.add(singleSlot);
			}			

		} catch (SQLException sqlException) {
			log.info("getAllPageSlotByPageCode() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAllPageSlotByPageCode() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return pageSlotList;
	}
	
	/**
	 * Retrieve Active banner providers by Page Slot.
	 * 
	 * @param pageSlotCode page Slot Code number
	 * @author DJ
	 * @return Collection
	 */
	@Override
	public Collection<Collection<String>> getBannerProviderByPageSlotCode(int pageSlotCode) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		final Collection<Collection<String>> allBannerList = new ArrayList<Collection<String>>();				
		try {			
			conn = ConnectionManager.getConnection();
            final StringBuilder sb = new StringBuilder("SELECT DISTINCT ADVERTISER.CODE ADVERTISERCODE, ADVERTISER.NAME ADVERTISERNAME ");
            sb.append("FROM [CAMPUS].[BANNER] BANNER INNER JOIN CAMPUS.ADVERTISER ADVERTISER ON BANNER.ADVERTISER=ADVERTISER.CODE WHERE BANNER.PAGESLOT= ? AND BANNER.ISACTIVE= ?");
			
			stmt=conn.prepareStatement(sb.toString());
			stmt.setInt(1, pageSlotCode);
			stmt.setInt(2, ApplicationStatus.ACTIVE.getStatusValue());
			resultSet= stmt.executeQuery();	
			while (resultSet.next()) {
				final ArrayList<String> bannerList = new ArrayList<String>();
				bannerList.add(resultSet.getString("ADVERTISERCODE"));								
				bannerList.add(resultSet.getString("ADVERTISERNAME"));								
				allBannerList.add(bannerList);
			}	

		} catch (SQLException sqlException) {
			log.info("getBannerProviderByPageSlotCode() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getBannerProviderByPageSlotCode() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return allBannerList;
	}

	

}
