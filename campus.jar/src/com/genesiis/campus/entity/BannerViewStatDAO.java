// 20170330 MM c117-display-banners-record-viewcount-back-end INIT class and implemented add(Object) method

package com.genesiis.campus.entity;

import com.genesiis.campus.entity.model.BannerViewStat;
import com.genesiis.campus.util.ConnectionManager;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class BannerViewStatDAO implements ICrud {

	static Logger Log = Logger.getLogger(BannerViewStatDAO.class.getName());


	@Override
	public int add(Object object) throws SQLException, Exception {
		BannerViewStat bannerViewStat = (BannerViewStat) object;
		PreparedStatement ps = null;
		Connection connection = null;
		
		String query = "INSERT INTO [campus].[BANNERVIEWSTAT] (CALLERPAGE, VIEWDATE, VIEWTIME, BANNER, CRTBY) VALUES (?, ?, ?, ?, ?)";
		int result = -1; 
		
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, bannerViewStat.getCallerPage());
			ps.setDate(2, bannerViewStat.getViewDate());
			ps.setTime(3, bannerViewStat.getViewTime());			
			ps.setInt(4, bannerViewStat.getBanner());
			ps.setString(5, bannerViewStat.getCrtBy());
			result = ps.executeUpdate();	
		} catch (SQLException sqle) {
			Log.info("add(): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			Log.info("add(): Exception:" + ex.toString());
			throw ex;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
		return result;
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
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
