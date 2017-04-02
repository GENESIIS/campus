// 20170330 MM c117-display-banners-record-viewcount-back-end INIT class and implemented add(Object) method
// 20170331 MM c117-display-banners-record-viewcount-back-end Altered implementation of add(Object) method so it performs a batch operation 
// 20170401 MM c117-display-banners-record-viewcount-back-end Implemented update(Object) method and changed add(Object) to remove dealing with callerPage parameter 

package com.genesiis.campus.entity;

import com.genesiis.campus.entity.model.BannerViewStat;
import com.genesiis.campus.util.ConnectionManager;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BannerViewStatDAO implements ICrud {

	static Logger Log = Logger.getLogger(BannerViewStatDAO.class.getName());

	@Override
	public int add(Object object) throws SQLException, Exception {
		
		PreparedStatement ps = null;
		Connection con = null; 
		int totalNumOfRecordsAffected = 0;
		
		try {			
			List<BannerViewStat> bannerViewStatCollection = (ArrayList<BannerViewStat>) object; 
			
			String query = "INSERT INTO [campus].[BANNERVIEWSTAT] (BANNER, VIEWCOUNT, LASTVIEWDATE, LASTVIEWTIME, CRTBY) VALUES (?, ?, ?, ?, ?)";
			
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
						
			for(BannerViewStat bvs : bannerViewStatCollection) {
				
				ps.setInt(1, bvs.getBanner());
				ps.setInt(2, bvs.getViewCount());
				ps.setDate(3, bvs.getLastViewDate());			
				ps.setTime(4, bvs.getLastViewTime());			
				ps.setString(5, bvs.getCrtBy());
				
			    ps.addBatch();
			}			
			
			 int[] affectedRecords = ps.executeBatch();	
			 
			 for (int numOfRecordsAffected : affectedRecords) {
				 totalNumOfRecordsAffected += numOfRecordsAffected;
			 }
			
		} catch (SQLException sqle) {
			Log.info("add(Object): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			Log.info("add(Object): Exception:" + ex.toString());
			throw ex;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}	
		
		return totalNumOfRecordsAffected;
	}

	@Override
	public int update(Object object) throws SQLException, Exception {
		
		PreparedStatement ps = null;
		Connection con = null; 
		int totalNumOfRecordsAffected = 0;
		
		try {			
			List<BannerViewStat> bannerViewStatCollection = (ArrayList<BannerViewStat>) object; 
			
			String query = "UPDATE [campus].[BANNERVIEWSTAT] SET VIEWCOUNT = ?, LASTVIEWDATE = ?, LASTVIEWTIME = ?, MODBY = ? WHERE BANNER = ?";
			
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
						
			for(BannerViewStat bvs : bannerViewStatCollection) {
				
				ps.setInt(1, bvs.getViewCount());
				ps.setDate(2, bvs.getLastViewDate());			
				ps.setTime(3, bvs.getLastViewTime());			
				ps.setString(4, bvs.getModBy());
				ps.setInt(5, bvs.getBanner());
				
			    ps.addBatch();
			}			
			
			 int[] affectedRecords = ps.executeBatch();	
			 
			 for (int numOfRecordsAffected : affectedRecords) {
				 totalNumOfRecordsAffected += numOfRecordsAffected;
			 }
			
		} catch (SQLException sqle) {
			Log.info("update(Object): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			Log.info("update(Object): Exception:" + ex.toString());
			throw ex;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}	
		
		return totalNumOfRecordsAffected;
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
