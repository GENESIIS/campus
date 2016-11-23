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

package com.genesiis.campus.validation;

import com.genesiis.campus.entity.BannerAndAdvertDAO;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.util.IDataHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

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

		Map<String, Collection<Collection<String>>> slotNameToContentMap = new LinkedHashMap<String, Collection<Collection<String>>>();
		String googleAdvertCode = "<script async src=\"//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js\"></script>"
				+ "<!-- topjobs_responsive -->"
				+ "<ins class=\"adsbygoogle\""
				+ "style=\"display:block\""
				+ "data-ad-client=\"ca-pub-1285561228927367\""
				+ "data-ad-slot=\"7928574691\""
				+ "data-ad-format=\"auto\"></ins>"
				+ "<script>"
				+ "(adsbygoogle = window.adsbygoogle || []).push({});"
				+ "</script>";

		String[] pagUrlSections = pageName.split("/");
		if (pagUrlSections != null && pagUrlSections.length > 0) {
			pageName = pagUrlSections[pagUrlSections.length - 1];
		}

		if (pageName != null && !pageName.isEmpty()) {
			BannerAndAdvertDAO bannerAndAdvertDao = new BannerAndAdvertDAO();

			try {
				Collection<Collection<String>> bannerCollection = bannerAndAdvertDao
						.findById(pageName);

				// Get banner logo path from SystemConfig enum
				String bannerPath = SystemConfig.BANNER_PATH.getValue1();

				Map<String, List<Collection<String>>> pageSlotCodeToBannerRecordsMap = new LinkedHashMap<String, List<Collection<String>>>();
				Map<String, String> pageSlotCodeToNameMap = new LinkedHashMap<String, String>();

				for (Collection<String> record : bannerCollection) {
					int count = 0;
					String pageSlotCodeStr = "";
					String pageSlotName = "";
					for (String field : record) {

						if (count == 0) {
							pageSlotCodeStr = field;
						}

						if (count == 1) {
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
				
				for (String pageSlotCode : pageSlotCodeSet) {					
					
					listOfRecords = pageSlotCodeToBannerRecordsMap.get(pageSlotCode);
					
					boolean areBannerRecordsAvaialbleForTheSlot = true;
					
					outer:
					for (Collection<String> singleRecord : listOfRecords) {
						int count = 0;
						for (String field : singleRecord) {
							if (count == 2 || // banner code
									count == 11 || // imagepath
									count == 5 || // displayduration
									count == 7) { // url
								
								if (field == null) {
									areBannerRecordsAvaialbleForTheSlot = false;
									break outer;
								}
							}
							count++;
						}
					}
					
					if (areBannerRecordsAvaialbleForTheSlot) { 
						// banners are available; assign banner records to the attribute whose name is the slot name					
						helper.setAttribute(
								pageSlotCodeToNameMap.get(pageSlotCode),
								pageSlotCodeToBannerRecordsMap.get(pageSlotCode));
					} else {	
						// banners are not available; assign google banner advert code to the attribute whose name is the slot name							
						helper.setAttribute(
								pageSlotCodeToNameMap.get(pageSlotCode),
								googleAdvertCode);
					}
				}
				helper.setAttribute("bannerPath", bannerPath);

			} catch (SQLException sqle) {
				Log.info("getBannerData(Operation): SQLException: "
						+ sqle.toString());
				throw sqle;
			} catch (Exception e) {
				Log.info("getBannerData(Operation): Exception: " + e.toString());
				throw e;
			}
		}
	}
}
