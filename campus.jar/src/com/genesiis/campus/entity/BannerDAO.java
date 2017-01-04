package com.genesiis.campus.entity;

//DJ 20161206 c52-report-banner-statistics-MP-dj created BannerDAO.java
//DJ 20161206 c52-report-banner-statistics-MP-dj Initiate getAll() method
//DJ 20161211 c52-report-banner-statistics-MP-dj Implement findById() method

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

public class BannerDAO  implements ICrud{
	
	static org.apache.log4j.Logger log = Logger.getLogger(BannerDAO.class.getName());

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
	 * Retrieve Page Slot wise banners result set
	 * 
	 * @param 
	 * @author DJ
	 * @return Collection
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		final Collection<Collection<String>> allBannerList = new ArrayList<Collection<String>>();
		int pageSlotCode=0;
		if(UtilityHelper.isNotEmptyObject(code)){
			pageSlotCode=Integer.valueOf((String) code);			
		}
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
			log.info("findById() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return allBannerList;
	}

	@Override
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
