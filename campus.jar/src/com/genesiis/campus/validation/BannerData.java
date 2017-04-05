//20161116 MM c2-integrate-google-banners-MP INIT class
//20161117 MM c2-integrate-google-banners-MP Implemented getBannerData() method
//20161117 MM c2-integrate-google-banners-MP Modified getBannerData() method to fetch and 
//				dynamically assign bannerData collections to attributes
//20161117 MM c2-integrate-google-banners-MP Modified getBannerData() method to use use the 
// 				SystemConfig enum to get the banner path
//20161123 MM c2-integrate-google-banners-MP Removed redundant code of creating 
//				an ArrayList object, and added JavaDoc comment
//20161123 MM c2-integrate-google-banners-MP Changed implementation of setBannerDetails() 
//				so that when there are no banner records for a pageSlot, Google advert code
//				assigned to the relevant request attribute to be sent to JSP
//20161124 MM c2-integrate-google-banners-MP Made the operation more automated by adding 
//				code to set the currently loaded JSP as an attribute named "callerPage" 
//				to be used by the Ajax call in the front-end when sending the banner-stat-update 
//				request
//20161128 MM c2-integrate-google-banners-MP Modified code to suit the renaming of 
//				BannerAndAdvertDAO to BannerDAO. Disabled the execution of code that 
//				checks and assigns Google advert script code to be loaded into a banner slot
//				when no banners are present there. 
//20161206 MM c2-integrate-google-banners-MP Modified code to consider the omission of fetching
//				BANNERSTATUS in the DAO class, and to make maintenance easier by accessing 
//				indexes where fetched values are present via variables
//20161217 MM c2-integrate-google-banners Changed logger level to 'error' in logging 
//				statements in catch clauses
//20161217 MM c2-integrate-google-banners Cleaned up imports
//20170405 PN CAM-137: removed exception throw statement from the setBannerDetails() method and logged the full error.

package com.genesiis.campus.validation;

import com.genesiis.campus.entity.BannerDAO;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BannerData {

	static Logger Log = Logger.getLogger(BannerData.class.getName());

	/**
	 * This gets the details of the banners for the particular JSP page
	 * indicated by the "pageName" parameter and sets attributes to the
	 * HttpServletrRequest object - that is wrapped in DataHelper object 
	 * (passed as parameter "helper") - data related to banners that should 
	 * be shown on the page. Attributes will be set in such a way that a 
	 * List containing data on multiple banners will be assigned as the value
	 * for an attribute whose name will be the name of the page slot in that 
	 * page that those banners have been set against in the DB
	 *   
	 * 
	 * @param IDataHelper That wraps an object of type HttpServletRequest and gives
	 *            access to attributes and parameters set on that
	 *            HttpServletRequest object. 
	 *            
	 * @param String Is expected to indicate the page name for which banner 
	 * 			information needs to be fetched for  
	 * 
	 * @throws Exception
	 */

	public static void setBannerDetails(IDataHelper helper, String pageName)
			throws Exception {
		
//		DO NOT REMOVE THE SET OF COMMENTED LINES BELOW! THEY SPECIY AND ASSIGN THE GOOGLE BANNER CODE.
//		COMMENTED-OUT TO CURRENTLY DISABLE THE FUNCTIONALITY	
//		String googleAdvertCode = "<script async src=\"//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js\"></script>"
//				+ "<!-- topjobs_responsive -->"
//				+ "<ins class=\"adsbygoogle\""
//				+ "style=\"display:block\""
//				+ "data-ad-client=\"ca-pub-1285561228927367\""
//				+ "data-ad-slot=\"7928574691\""
//				+ "data-ad-format=\"auto\"></ins>"
//				+ "<script>"
//				+ "(adsbygoogle = window.adsbygoogle || []).push({});"
//				+ "</script>";

		String[] pagUrlSections = pageName.split("/");
		if (pagUrlSections != null && pagUrlSections.length > 0) {
			pageName = pagUrlSections[pagUrlSections.length - 1];
		}

		if (pageName != null && !pageName.isEmpty()) {
			BannerDAO bannerDao = new BannerDAO();

			try {
				Collection<Collection<String>> bannerCollection = bannerDao
						.findById(pageName);

				// Get banner logo path from SystemConfig enum
				String bannerPath = SystemConfig.BANNER_PATH.getValue1();

				Map<String, List<Collection<String>>> pageSlotCodeToBannerRecordsMap = new LinkedHashMap<String, List<Collection<String>>>();
				Map<String, String> pageSlotCodeToNameMap = new LinkedHashMap<String, String>();

				final int indexOfPageSlotCode = 0;
				final int indexOfPageSlotName = 1;
				
				for (Collection<String> record : bannerCollection) {
					int count = 0;
					String pageSlotCodeStr = "";
					String pageSlotName = "";
					for (String field : record) {

						if (count == indexOfPageSlotCode) {
							pageSlotCodeStr = field;
						}

						if (count == indexOfPageSlotName) {
							pageSlotName = field;

							List<Collection<String>> bannerRecordList = pageSlotCodeToBannerRecordsMap
									.get(pageSlotCodeStr);
							String slotName = pageSlotCodeToNameMap
									.get(pageSlotCodeStr);

							if (bannerRecordList == null) {
								bannerRecordList = new ArrayList<Collection<String>>();
								pageSlotCodeToBannerRecordsMap.put(
										pageSlotCodeStr, bannerRecordList);
								pageSlotCodeToNameMap.put(pageSlotCodeStr,
										pageSlotName);
							}

							bannerRecordList.add(record);
							break;
						}

						count++;
					}
				}

				Set<String> pageSlotCodeSet = pageSlotCodeToBannerRecordsMap
						.keySet();


				List<Collection<String>> listOfRecords = null;
				final int indexOfBannerCode = 2;
				final int indexOfImage = 10;
				final int indexOfDisplayDuration = 5;
				final int indexOfUrl = 7;
				
				for (String pageSlotCode : pageSlotCodeSet) {					
					
					listOfRecords = pageSlotCodeToBannerRecordsMap.get(pageSlotCode);
					
					boolean areBannerRecordsAvailableForTheSlot = true;
					
					outer:
					for (Collection<String> singleRecord : listOfRecords) {
						int count = 0;
						for (String field : singleRecord) {
							if (count == indexOfBannerCode || // banner code
									count == indexOfImage || // image
									count == indexOfDisplayDuration || // displayduration
									count == indexOfUrl) { // url
								
								if (field == null) {
									areBannerRecordsAvailableForTheSlot = false;
									break outer;
								}
							}
							count++;
						}
					}
					
					if (areBannerRecordsAvailableForTheSlot) { 
						// banners are available; assign banner records to the attribute whose name is the slot name					
						helper.setAttribute(
								pageSlotCodeToNameMap.get(pageSlotCode),
								pageSlotCodeToBannerRecordsMap.get(pageSlotCode));
					} 
//	DO NOT REMOVE THE SET OF COMMENTED LINES BELOW! THEY HANDLE THE ASSIGNMENT OF GOOGLE ADVERT CODE WHEN NO BANNRS ARE PRESENT.
//	COMMENTED-OUT TO CURRENTLY DISABLE THE FUNCTIONALITY					
//					else {	
//						// banners are not available; assign google banner advert code to the attribute whose name is the slot name							
//						helper.setAttribute(
//								pageSlotCodeToNameMap.get(pageSlotCode),
//								googleAdvertCode);
//					}
				}
				helper.setAttribute("bannerPath", bannerPath);
				helper.setAttribute("callerPage", pageName);

			} catch (SQLException sqle) {
				Log.error("getBannerData(Operation): SQLException: "
						+ sqle);
			} catch (Exception e) {
				Log.error("getBannerData(Operation): Exception: " + e);
			}
		}
	}
}
