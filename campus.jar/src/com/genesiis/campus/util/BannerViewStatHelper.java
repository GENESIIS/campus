// 20170330 MM c117-display-banners-record-viewcount-back-end - INIT class act as a singleton
// 20170331 MM c117-display-banners-record-viewcount-back-end - Extended updateBannerViewCount(...) to persist bannerViewStat data
// 20170331 MM c117-display-banners-record-viewcount-back-end - Modifying updateBannerViewCount(...) method to construct a collection 
//				of BannerViewStat objects to pass to DAO method (WIP)
// 20170401 MM c117-display-banners-record-viewcount-back-end - Re-factored updateBannerViewCount(...) method; changed bannerToViewCountResolver 
//				static variable to contain a List<String> against an Integer; code now creates list of BannerViewStat objects and passes it 
//				to be persisted when threshold is reached
// 20170403 MM c117-display-banners-record-viewcount-back-end - Now using findById(Object) method of BannerViewStatDAO to fetch existing stat records
//				for banners of newly received banner stat update requests
// 20170406 MM c117-display-banners-record-viewcount-back-end - Existing banner view stats are now updated and new ones are inserted 
// 20170407 MM c117-display-banners-record-viewcount-back-end - Added test code to track issue with an NPE 

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

	static Logger log = Logger.getLogger(BannerViewStatHelper.class.getName());

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

		if (BannerViewStatHelper.viewCount == 100) {
			if (BannerViewStatHelper.viewCount % 100 == 0) {
				log.info("Reached >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>." + BannerViewStatHelper.viewCount + ". Flushing banner stats");	
			}
			// flush current view counts to the DB
			flushBannerViewStats();
		}
	}

	private void flushBannerViewStats() throws Exception {
		
		Map<Integer, List<String>> bannerViewCountResolver = BannerViewStatHelper.getBannerToViewCountResolver();

//		List<BannerViewStat> viewStatInstances = new ArrayList<BannerViewStat>();
		List<String> bannerViewCountDetails = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<Integer> bannerCodes = new ArrayList<Integer>(bannerViewCountResolver.keySet());
		
		BannerViewStatDAO bannerViewStatDao = new BannerViewStatDAO();
		try {
			Collection<Collection<String>> bannerStatCollection = bannerViewStatDao.findById(bannerCodes);
			List<BannerViewStat> bannerViewStatsToUpdate = new ArrayList<BannerViewStat>();
			List<BannerViewStat> bannerViewStatsToInsert = new ArrayList<BannerViewStat>();
			
			// Persist banner counts
			for (Integer banCode : bannerViewCountResolver.keySet()) {
				boolean areStatsAlreadyAvailable = false; 
				for (Collection<String> bannerStatRecord : bannerStatCollection) {
					int index = 0;
					Integer bannerCodeFromStats = null;
					
					for (String field : bannerStatRecord) {
						bannerCodeFromStats = Integer.valueOf(field);					
						break;
					}			
					
					if (banCode.equals(bannerCodeFromStats)) {					
						areStatsAlreadyAvailable = true;
						
						BannerViewStat bannerViewStat = new BannerViewStat();
						bannerViewStat.setBanner(banCode);
						bannerViewStat.setViewCount(Integer.parseInt(bannerViewCountDetails.get(0)));
						Date lastViewDateForBanner = null;
						try {
							lastViewDateForBanner = formatter.parse(bannerViewCountDetails.get(1));
						} catch (ParseException pe) {
							log.error("flushBannerViewStats() : ParseException " + pe.toString());
						} finally {
							lastViewDateForBanner = new Date(0L);
						}
						
						bannerViewStat.setLastViewDate(new java.sql.Date(lastViewDateForBanner.getTime()));
						bannerViewStat.setLastViewTime(new java.sql.Time(lastViewDateForBanner.getTime()));
	
						bannerViewStat.setModBy("SYSTEM");
	
						bannerViewStatsToUpdate.add(bannerViewStat);					
					} 
				}
				
				if (!areStatsAlreadyAvailable) {
					
					BannerViewStat bannerViewStat = new BannerViewStat();
	
					bannerViewStat.setBanner(banCode);
					bannerViewStat.setViewCount(Integer.parseInt(bannerViewCountDetails.get(0)));
					Date lastViewDateForBanner = null;
					try {
						lastViewDateForBanner = formatter.parse(bannerViewCountDetails.get(1));
					} catch (ParseException pe) {
						log.error("flushBannerViewStats() : ParseException " + pe.toString());
					} finally {
						lastViewDateForBanner = new Date(0L);
					}
					bannerViewStat.setLastViewDate(new java.sql.Date(lastViewDateForBanner.getTime()));
					bannerViewStat.setLastViewTime(new java.sql.Time(lastViewDateForBanner.getTime()));
	
					bannerViewStat.setCrtBy("SYSTEM");
	
					bannerViewStatsToInsert.add(bannerViewStat);
				}
				
			}

			// Persist banner counts
			persistBannerCounts(bannerViewStatsToUpdate, bannerViewStatsToInsert);
			
		} catch (SQLException sqle) {
			log.error("flushBannerViewStats() : SQLException " + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			log.error("flushBannerViewStats() : Exception " + e.toString());
			throw e;
		}
	}

	private int persistBannerCounts(List<BannerViewStat> bannerViewStatsToUpdate, List<BannerViewStat> bannerViewStatsToInsert) throws Exception {
		BannerViewStatDAO bannerViewStatDao = new BannerViewStatDAO();
		
		int updateStatus = 0;
		int insertStatus = 0;
		
		if (bannerViewStatsToUpdate != null && !bannerViewStatsToUpdate.isEmpty()) {
			try {
				updateStatus = bannerViewStatDao.update(bannerViewStatsToUpdate);		
			} catch (SQLException sqle) {
				log.error("persistBannerCounts(List<BannerViewStat>, List<BannerViewStat>) : SQLException " + sqle.toString());
				throw sqle;
				
			} catch (Exception e) {
				log.error("persistBannerCounts(List<BannerViewStat>, List<BannerViewStat>) : Exception " + e.toString());
				throw e;
				
			} finally {
				if (updateStatus > 0) {
					log.info("Stat data for new banners successfully saved in DB!");
				} else {
					log.error("The attempt to save view-stat-data for new banners to DB was unsuccessful!");
				}
			}
		}
		
		if (bannerViewStatsToInsert != null && !bannerViewStatsToInsert.isEmpty()) {
			try {
				insertStatus = bannerViewStatDao.add(bannerViewStatsToInsert);				
			} catch (SQLException sqle) {
				log.error("persistBannerCounts(List<BannerViewStat>, List<BannerViewStat>) : SQLException " + sqle.toString());
				throw sqle;
				
			} catch (Exception e) {
				log.error("persistBannerCounts(List<BannerViewStat>, List<BannerViewStat>) : Exception " + e.toString());
				throw e;
				
			} finally {
				if (insertStatus > 0) {
					log.info("Stat data for new banners successfully saved in DB!");
				} else {
					log.error("The attempt to save view-stat-data for new banners to DB was unsuccessful!");
				}
			}
		}
		
		// Clear elements in bannerToViewCountResolver
		clearBannerViewStats();
		
		return updateStatus;
	}

	private void clearBannerViewStats() {
		// Clear bannerToViewCountResolver
		BannerViewStatHelper.getBannerToViewCountResolver().clear();
		BannerViewStatHelper.viewCount = 0;
	}
}
