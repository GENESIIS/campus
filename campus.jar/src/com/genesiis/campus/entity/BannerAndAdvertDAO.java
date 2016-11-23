//20161103 MM c2-integrate-google-banners GoogleAdvertDAO.java created
//20161116 MM c2-integrate-google-banners Implemented findById() method
//20161123 MM c2-integrate-google-banners Added JavaDoc comment
//20161123 MM c2-integrate-google-banners Changed query in findById() so that even  
// 				pageSlots that do not have banners stored for them are fetched. 

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

	/**
	 * Fetches and returns data about the set of banners applicable for a JSP
	 * page.
	 * 
	 * @param Object
	 *            Declared to be of type Object, but actually expected to be
	 *            String, this parameter is expected to indicate the name of the
	 *            JSP page as stored in the PAGE table in the DB. This parameter
	 *            is used to check if there are currently any Banners set for
	 *            that page via PAGESLOT table.
	 * 
	 * @return Collection<Collection<String>> A Collection of String Collections
	 *         where each element in the outer Collection represents a single
	 *         result row as returned in query results. Each element in the
	 *         inner String collection represents a field in a single
	 *         result-row.
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {

		final Collection<Collection<String>> bannerList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String pageUrl = (String) code;

			StringBuilder query = new StringBuilder("SELECT b.*, ps.CODE AS PAGESLOTCODE, ps.NAME AS PAGESLOTNAME ");
			query.append("FROM [campus].[PAGE] p ");
			query.append("JOIN [campus].[PAGESLOT] ps ON (p.CODE = ps.PAGE AND p.NAME = ? AND ps.ISACTIVE = ?) ");
			query.append("LEFT JOIN [campus].[BANNER] b ON (b.PAGESLOT = ps.CODE AND b.BANNERSTATUS = ?)");

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query.toString());
			ps.setString(1, pageUrl);
			ps.setInt(2, 1);
			ps.setInt(3, 1);
			ResultSet rs = ps.executeQuery();

			retrieveBannerList(rs, bannerList);

		} catch (ClassCastException cce) {
			Log.info("findById(Object): ClassCastException: " + cce.toString());
			throw new IllegalArgumentException(
					"The argument passed is not of expected type (Programme)!");
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

	private void retrieveBannerList(ResultSet rs,
			Collection<Collection<String>> bannerCollection)
			throws SQLException {

		while (rs.next()) {
			final ArrayList<String> singleBanner = new ArrayList<String>();
			singleBanner.add(rs.getString("PAGESLOTCODE")); // 0
			singleBanner.add(rs.getString("PAGESLOTNAME")); // 1
			singleBanner.add(rs.getString("CODE")); // 2
			singleBanner.add(rs.getString("EXPIRATIONDATE")); // 3
			singleBanner.add(rs.getString("TYPE")); // 4
			singleBanner.add(rs.getString("DISPLAYDURATION")); // 5
			singleBanner.add(rs.getString("LINKTYPE")); // 6
			singleBanner.add(rs.getString("URL")); // 7
			singleBanner.add(rs.getString("BANNERSTATUS")); // 8
			singleBanner.add(rs.getString("ISACTIVE")); // 9
			singleBanner.add(rs.getString("ADVERTISER")); // 10
			singleBanner.add(rs.getString("IMAGEPATH")); // 11
			final Collection<String> singleBannerCollection = singleBanner;
			bannerCollection.add(singleBannerCollection);
		}
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
}
