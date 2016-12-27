package com.genesiis.campus.entity;

//DJ 20161206 c52-report-banner-statistics-MP-dj created BannerStatDAO.java
//DJ 20161206 c52-report-banner-statistics-MP-dj Initiate findById() method

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.UtilityHelper;

public class BannerStatDAO implements ICrud {
	
	static org.apache.log4j.Logger log = Logger.getLogger(BannerStatDAO.class.getName());

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
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet =null;
		Collection<Collection<String>> allBannerStatsList = new ArrayList<Collection<String>>();
		int bannerCode=0;
		if(UtilityHelper.isNotEmptyObject(code)){
			bannerCode=Integer.valueOf((String) code);			
		}
		try {
			
			
			conn=ConnectionManager.getConnection();			
			final StringBuilder sb=new StringBuilder("SELECT * FROM [CAMPUS].BANNERSTAT BANNERSTAT WHERE BANNERSTAT.BANNER= ");
			
			stmt=conn.prepareStatement(sb.toString());
			stmt.setInt(1, bannerCode);			
			resultSet=stmt.executeQuery();
			while (resultSet.next()) {
				final ArrayList<String> bannerList = new ArrayList<String>();
				bannerList.add(resultSet.getString("BANNERCODE"));								
				bannerList.add(resultSet.getString("IMAGENAME"));								
				allBannerStatsList.add(bannerList);
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
		return allBannerStatsList;
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

}
