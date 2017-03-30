// 20170330 MM c117-display-banners-record-viewcount-back-end INIT class act as a singleton

package com.genesiis.campus.util;

import java.util.HashMap;
import java.util.Map;

import com.genesiis.campus.entity.BannerStatDAO;
import com.genesiis.campus.entity.model.BannerStat;

public class BannerViewStatHelper {
	
	private static BannerViewStatHelper bannerViewStatHelper;
	private static Map<Integer, Integer> bannerToViewCountResolver; 
	
	
	private static Map<Integer, Integer> getBannerToViewCountResolver() {
		return BannerViewStatHelper.bannerToViewCountResolver;
	}

	/**
	 * Constructor made private to prevent it being called from outside this class
	 */
	private BannerViewStatHelper() {
		BannerViewStatHelper.bannerToViewCountResolver = new HashMap<Integer, Integer>();
	} 
	
	/**
	 * Creates a new instance of BannerViewStatHelper if not one is available already and returns it. 	 * 
	 * 
	 * @return The singleton BannerViewStatHelper 
	 */
	public static BannerViewStatHelper getInstance() {
		if (BannerViewStatHelper.bannerViewStatHelper == null) {
			BannerViewStatHelper.bannerViewStatHelper = new BannerViewStatHelper();
		}
		return BannerViewStatHelper.bannerViewStatHelper;
	}
	
	public void updateBannerViewCount(int bannerCode) {
		// update the banner view count
		
		Map<Integer, Integer> bannerViewCountResolver = BannerViewStatHelper.getBannerToViewCountResolver();
		
		if (bannerViewCountResolver != null) {
			Integer viewCount = bannerViewCountResolver.get(bannerCode);
			if (viewCount == null) {
				bannerViewCountResolver.put(bannerCode, 1);
			} else {
				bannerViewCountResolver.put(bannerCode, viewCount + 1);
			}
		}
		
		int viewCounts = 0;
		// See the total of the view counts of the banners
		for (Integer banner : bannerViewCountResolver.keySet()) {
			int count = bannerViewCountResolver.get(banner);			
			viewCounts += count;
		} 
		
		if (viewCounts >= 100) {
			// Persist banner counts
			

			BannerStat bannerStat = new BannerStat();
			bannerStat.setBanner(bannerCode);

			String createdBy = "SYSTEM";
			bannerStat.setCrtBy(createdBy);

			BannerStatDAO bannerDao = new BannerStatDAO();
//			int insertStatus = bannerDao.add(bannerStat);
			
			// Clear elements in bannerToViewCountResolver
			
		}
	}
	
	public void clearBannerViewCounts() {
		// flush current view counts to the DB	
	}
	
}
