//20161118 MM c2-integrate-google-banners INIT - Initialised file
//20161118 MM c2-integrate-google-banners INIT - Implemented add(Object) method

package com.genesiis.campus.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.model.BannerStat;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;

public class BannerStatDAO implements ICrud {
	
	static Logger Log = Logger.getLogger(BannerAndAdvertDAO.class.getName());

	@Override
	public int add(Object object) throws SQLException, Exception {
		BannerStat bannerStat = (BannerStat) object;
		PreparedStatement ps = null;
		Connection connection = null;
		
		String query = "INSERT INTO [campus].[BANNERSTAT] (CALLERPAGE, BANNER, CRTBY) VALUES (?, ?, ?)";
		int result = -1; 
		
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, bannerStat.getCallerPage());
			ps.setInt(2, bannerStat.getBanner());
			ps.setString(3, bannerStat.getCrtBy());
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
