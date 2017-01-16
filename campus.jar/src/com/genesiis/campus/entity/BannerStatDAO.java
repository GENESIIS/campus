//20161118 MM c2-integrate-google-banners INIT - Initialised file
//20161118 MM c2-integrate-google-banners Implemented add(Object) method
//20161123 MM c2-integrate-google-banners Added JavaDoc comment
//20161128 MM c2-integrate-google-banners Corrected argument sent to Logger.getLogger()
//20161214 MM c2-integrate-google-banners Changed a column name from IMAGEPATH to 
//				statements in catch clauses
//20161216 MM c2-integrate-google-banners Changed logger level to 'error' in logging 
//				statements in catch clauses

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

	static Logger Log = Logger.getLogger(BannerStatDAO.class.getName());

	/**
	 * Inserts a single record into the BANNERSTAT table in the DB with the code
	 * of the related banner as received as a client parameter
	 * 
	 * @param Object
	 *            A BannerStat object, used to extract data on the banner code,
	 *            caller page and name of the system user under whose name this
	 *            record will be created
	 * 
	 * @return int A primitive int that represents how many records that were
	 *         added to the db. If less than 0, indicates that the insertion
	 *         process failed before being completed.
	 */

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
			Log.error("add(): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			Log.error("add(): Exception:" + ex.toString());
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
}
