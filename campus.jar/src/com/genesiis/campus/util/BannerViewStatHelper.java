// 20170330 MM c117-display-banners-record-viewcount-back-end - INIT class act as a singleton
// 20170331 MM c117-display-banners-record-viewcount-back-end - Extended updateBannerViewCount(...) to persist bannerViewStat data
// 20170331 MM c117-display-banners-record-viewcount-back-end - Modifying updateBannerViewCount(...) method to construct a collection 
//				of BannerViewStat objects to pass to DAO method (WIP)
// 20170401 MM c117-display-banners-record-viewcount-back-end - Re-factored updateBannerViewCount(...) method; changed bannerToViewCountResolver 
//				static variable to contain a List<String> against an Integer; code now creates list of BannerViewStat objects and passes it 
//				to be persisted when threshold is reached
// 20170403 MM c117-display-banners-record-viewcount-back-end - Now using findById(Object) method of BannerViewStatDAO to fetch existing stat records
//				for banners of newly received banner stat update requests
// 20170403 MM c117-display-banners-record-viewcount-back-end - Made corrections to handle exceptions 

package com.genesiis.campus.util;

import com.genesiis.campus.entity.BannerStatDAO;
import com.genesiis.campus.entity.BannerViewStatDAO;
import com.genesiis.campus.entity.model.BannerStat;
import com.genesiis.campus.entity.model.BannerViewStat;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Time;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BannerViewStatHelper {

	private static BannerViewStatHelper bannerViewStatHelper;
	private static Map<Integer, List<String>> bannerToViewCountResolver;
	private static int viewCount;

	static org.apache.log4j.Logger log = Logger.getLogger(BannerViewStatHelper.class.getName());

	private static Map<Integer, List<String>> getBannerToViewCountResolver() {
		return BannerViewStatHelper.bannerToViewCountResolver;
	}

	/**
	 * Constructor made private to prevent it being called from outside this
	 * class
	 */
	private BannerViewStatHelper() {
		BannerViewStatHelper.bannerToViewCountResolver = new HashMap<Integer, List<String>>();
		BannerViewStatHelper.viewCount = 0;
	}

	/**
	 * Creates a new instance of BannerViewStatHelper if not one is available
	 * already and returns it.
	 * 
	 * @return The singleton BannerViewStatHelper
	 */
	public static BannerViewStatHelper getInstance() {
		if (BannerViewStatHelper.bannerViewStatHelper == null) {
			BannerViewStatHelper.bannerViewStatHelper = new BannerViewStatHelper();
		}
		return BannerViewStatHelper.bannerViewStatHelper;
	}

	public void updateBannerViewCount(int bannerCode) throws Exception {
		// update the banner view count
		Map<Integer, List<String>> bannerViewCountResolver = BannerViewStatHelper.getBannerToViewCountResolver();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		if (bannerViewCountResolver != null) {
			List<String> bannerViewCountDetails = bannerViewCountResolver.get(bannerCode);

			Date today = new Date();
			java.sql.Date currentDate = new java.sql.Date(today.getTime());
			String currentDateStr = formatter.format(currentDate);

			java.sql.Time currentTime = new java.sql.Time(today.getTime());
			String currentTimeStr = currentTime.toString();

			if (bannerViewCountDetails == null) {
				bannerViewCountDetails = new ArrayList<String>();

				bannerViewCountDetails.add(String.valueOf(1));
				bannerViewCountDetails.add(currentDateStr);
				bannerViewCountDetails.add(currentTimeStr);

				bannerViewCountResolver.put(bannerCode, bannerViewCountDetails);
			} else {
				int currentViewCount = Integer.parseInt(bannerViewCountDetails.get(0));
				bannerViewCountDetails.set(0, String.valueOf(++currentViewCount));
				bannerViewCountDetails.set(1, currentDateStr);
				bannerViewCountDetails.set(2, currentTimeStr);
			}

			BannerViewStatHelper.viewCount++;
		}

		if (BannerViewStatHelper.viewCount >= 100) {
			// flush current view counts to the DB
			flushBannerViewStats();
		}
	}

	private void flushBannerViewStats() throws Exception {
		
		Map<Integer, List<String>> bannerViewCountResolver = BannerViewStatHelper.getBannerToViewCountResolver();

		List<BannerViewStat> viewStatInstances = new ArrayList<BannerViewStat>();
		List<String> bannerViewCountDetails = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<Integer> bannerCodes = new ArrayList<Integer>();

		try {
			// Persist banner counts
			for (Integer banCode : bannerViewCountResolver.keySet()) {
				
				bannerCodes.add(banCode);
				
				bannerViewCountDetails = bannerViewCountResolver.get(banCode);
				if (bannerViewCountDetails != null) {
	
					BannerViewStat bannerViewStat = new BannerViewStat();
	
					bannerViewStat.setBanner(banCode);
					bannerViewStat.setViewCount(Integer.parseInt(bannerViewCountDetails.get(0)));
	
					Date lastViewDateForBanner = formatter.parse(bannerViewCountDetails.get(1));
					bannerViewStat.setLastViewDate(new java.sql.Date(lastViewDateForBanner.getTime()));
					bannerViewStat.setLastViewTime(new java.sql.Time(lastViewDateForBanner.getTime()));
	
					bannerViewStat.setCrtBy("SYSTEM");
	
					viewStatInstances.add(bannerViewStat);
				}
			}
	
			BannerViewStatDAO bannerViewStatDao = new BannerViewStatDAO();
			Collection<Collection<String>> bannerStatCollection = bannerViewStatDao.findById(bannerCodes);
	
			// Persist banner counts
			persistBannerCounts(viewStatInstances);
		
		} catch (ParseException pe) {
			log.error("flushBannerViewStats(): ParseException: " + pe.toString());
			throw pe;
		} catch (SQLException sqle) {
			log.error("flushBannerViewStats(): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			log.error("flushBannerViewStats(): Exception: " + e.toString());
			throw e;
		}
	}

	private int persistBannerCounts(List<BannerViewStat> viewStatInstances) throws Exception {
		BannerViewStatDAO bannerViewStatDao = new BannerViewStatDAO();

		int insertStatus = 0;

		try {
			insertStatus = bannerViewStatDao.add(viewStatInstances);

		} catch (SQLException sqle) {
			log.error("execute(IDataHelper, IView) : SQLException " + sqle.toString());
			throw sqle;

		} catch (Exception e) {
			log.error("execute(IDataHelper, IView) : Exception " + e.toString());
			throw e;

		} finally {
			if (insertStatus > 0) {
				log.info("Banner view stat data was successfully flushed to DB!");
			} else {
				log.error("The attempt to flush banner view stat data to DB was unsuccessful!");
			}
		}

		// Clear elements in bannerToViewCountResolver
		clearBannerViewStats();
		
		return insertStatus;
	}

	private void clearBannerViewStats() {
		// Clear bannerToViewCountResolver
		BannerViewStatHelper.getBannerToViewCountResolver().clear();
		BannerViewStatHelper.viewCount = 0;
	}
}
