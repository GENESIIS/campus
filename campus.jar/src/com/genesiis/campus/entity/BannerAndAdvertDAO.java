//20161103 MM c2-integrate-google-banners GoogleAdvertDAO.java created
//20161116 MM c2-integrate-google-banners Implemented findById() method

package com.genesiis.campus.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;

public class BannerAndAdvertDAO implements ICrud {
	
	static Logger Log = Logger.getLogger(BannerAndAdvertDAO.class.getName());

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
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		
		final Collection<Collection<String>> bannerList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String pageUrl = (String) code;
			
			String query = "SELECT b.*, ps.CODE AS PAGESLOTCODE, ps.NAME AS PAGESLOTNAME "
					+ "FROM [campus].[PAGE] p "
					+ "JOIN [campus].[PAGESLOT] ps ON (p.CODE = ps.PAGE AND p.NAME = ?) "
					+ "JOIN [campus].[BANNER] b ON (b.PAGESLOT = ps.CODE AND b.BANNERSTATUS = ?)";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, pageUrl);
			ps.setInt(2, 1);
			ResultSet rs = ps.executeQuery();
			
			retrieveBannerList(rs, bannerList);

		} catch (ClassCastException cce) {
			Log.info("findById(Object): ClassCastException: " + cce.toString());
			throw new IllegalArgumentException("The argument passed is not of expected type (Programme)!");
		} catch (SQLException sqle) {
			Log.info("findById(Object): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			Log.info("findById(Object): Exception: " + e.toString());
			throw e;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return bannerList;
	}
	
	private void retrieveBannerList(ResultSet rs, Collection<Collection<String>> bannerCollection) throws SQLException {

		while (rs.next()) {
			final ArrayList<String> singleBanner = new ArrayList<String>();
			singleBanner.add(rs.getString("PAGESLOTCODE")); //0
			singleBanner.add(rs.getString("PAGESLOTNAME")); //1
			singleBanner.add(rs.getString("CODE")); //2
			singleBanner.add(rs.getString("EXPIRATIONDATE")); //3
			singleBanner.add(rs.getString("TYPE")); //4
			singleBanner.add(rs.getString("DISPLAYDURATION")); //5
			singleBanner.add(rs.getString("LINKTYPE")); //6
			singleBanner.add(rs.getString("URL")); //7
			singleBanner.add(rs.getString("BANNERSTATUS")); //8
			singleBanner.add(rs.getString("ISACTIVE")); //9
			singleBanner.add(rs.getString("ADVERTISER")); //10
			singleBanner.add(rs.getString("IMAGEPATH")); //11
			final Collection<String> singleBannerCollection = singleBanner;
			bannerCollection.add(singleBannerCollection);
		}
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
