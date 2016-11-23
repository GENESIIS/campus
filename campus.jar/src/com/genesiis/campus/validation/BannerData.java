//20161116 MM c2-integrate-google-banners-MP INIT class
//20161117 MM c2-integrate-google-banners-MP Implemented getBannerData() method
//20161117 MM c2-integrate-google-banners-MP Modified getBannerData() method to fetch and 
//				dynamically assign bannerData collections to attributes
//20161117 MM c2-integrate-google-banners-MP Modified getBannerData() method to use use the 
// 				SystemConfig enum to get the banner path
//20161123 MM c2-integrate-google-banners-MP Removed redundant code of creating 
//				an ArrayList object, and added JavaDoc comment

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
				List<Collection<String>> listOfRecords = new ArrayList<Collection<String>>();

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

				for (String pageSlotCode : pageSlotCodeSet) {
					helper.setAttribute(
							pageSlotCodeToNameMap.get(pageSlotCode),
							pageSlotCodeToBannerRecordsMap.get(pageSlotCode));
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
