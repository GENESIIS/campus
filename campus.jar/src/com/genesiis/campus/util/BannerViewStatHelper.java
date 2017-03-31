// 20170330 MM c117-display-banners-record-viewcount-back-end INIT class act as a singleton
// 20170331 MM c117-display-banners-record-viewcount-back-end Extended updateBannerViewCount(...) to persist bannerViewStat data
// 20170331 MM c117-display-banners-record-viewcount-back-end Modifying updateBannerViewCount(...) method to construct a collection 
//				of BannerViewStat objects to pass to DAO method (WIP)

package com.genesiis.campus.util;

import com.genesiis.campus.entity.BannerStatDAO;
import com.genesiis.campus.entity.BannerViewStatDAO;
import com.genesiis.campus.entity.model.BannerStat;
import com.genesiis.campus.entity.model.BannerViewStat;

import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BannerViewStatHelper {
	
	private static BannerViewStatHelper bannerViewStatHelper;
	private static Map<Integer, List<String>> bannerToViewCountResolver; 
	private static int viewCount;

	static Logger Log = Logger.getLogger(BannerViewStatHelper.class.getName());
	
	
	private static Map<Integer, List<String>> getBannerToViewCountResolver() {
		return BannerViewStatHelper.bannerToViewCountResolver;
	}

	/**
	 * Constructor made private to prevent it being called from outside this class
	 */
	private BannerViewStatHelper() {
		BannerViewStatHelper.bannerToViewCountResolver = new HashMap<Integer, List<String>>();
		BannerViewStatHelper.viewCount = 0;
	} 
	
	/**
	 * Creates a new instance of BannerViewStatHelper if not one is available already and returns it. 
	 * 
	 * @return The singleton BannerViewStatHelper 
	 */
	public static BannerViewStatHelper getInstance() {
		if (BannerViewStatHelper.bannerViewStatHelper == null) {
			BannerViewStatHelper.bannerViewStatHelper = new BannerViewStatHelper();
		}
		return BannerViewStatHelper.bannerViewStatHelper;
	}
	
	public void updateBannerViewCount(int bannerCode, String callerPage) throws Exception {
		// update the banner view count		
		Map<Integer, List<String>> bannerViewCountResolver = BannerViewStatHelper.getBannerToViewCountResolver();
		
		if (bannerViewCountResolver != null) {
			List<String> bannerViewCountDetails = bannerViewCountResolver.get(bannerCode);
			if (bannerViewCountDetails == null) {
				bannerViewCountDetails = new ArrayList<String>();
				
				bannerViewCountDetails.add(String.valueOf(bannerCode));				
				bannerViewCountResolver.put(bannerCode, bannerViewCountDetails);
			} else {
//				bannerViewCountResolver.put(bannerCode, viewCount + 1);
			}
			
			BannerViewStatHelper.viewCount++; 
		}
		
		if (BannerViewStatHelper.viewCount >= 100) {
			// Persist banner counts			
			List<BannerViewStat> viewStatInstances = new ArrayList<BannerViewStat>();

			for (Integer banCode : bannerViewCountResolver.keySet()) {
				Integer viewCountForBanner = bannerViewCountResolver.get(banCode);
				
				BannerViewStat bannerViewStat = new BannerViewStat();
				bannerViewStat.setBanner(banCode);
				bannerViewStat.setViewCount(viewCountForBanner);
				bannerViewStat.setCallerPage(callerPage);
				bannerViewStat.setCrtBy("SYSTEM");
			}

			BannerViewStatDAO bannerViewStatDao = new BannerViewStatDAO();
			
			int insertStatus = 0;
			try {
				insertStatus = bannerViewStatDao.add(viewStatInstances);
				
			} catch (SQLException sqle) {
				Log.error("execute(IDataHelper, IView) : SQLException "
						+ sqle.toString());
				throw sqle;
				
			} catch (Exception e) {
				Log.error("execute(IDataHelper, IView) : Exception "
						+ e.toString());
				throw e;
			}
			
			// Clear elements in bannerToViewCountResolver
			
		}
	}
	
	public void clearBannerViewCounts() {
		// flush current view counts to the DB	
	}
	
}
