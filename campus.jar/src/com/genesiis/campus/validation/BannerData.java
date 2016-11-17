//20161116 MM c2-integrate-google-banners INIT class
//20161117 MM c2-integrate-google-banners Implemented getBannerData() method

package com.genesiis.campus.validation;

import com.genesiis.campus.entity.BannerAndAdvertDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class BannerData {

	static Logger Log = Logger.getLogger(BannerData.class.getName());
	
	public static Map<String, Collection<Collection <String>>> getBannerData(Operation op) throws Exception {
		
		Map<String, Collection<Collection <String>>> slotNameToContentMap = 
				new LinkedHashMap<String, Collection<Collection <String>>>();
		
		if (op != null) {
			String pageUrl = op.getPageURL();
			String[] pagUrlSections = pageUrl.split("/");
			String pageName = "";
			if (pagUrlSections != null && pagUrlSections.length > 0) {
				pageName = pagUrlSections[pagUrlSections.length - 1];
			}
			
			if (pageName != null && !pageName.isEmpty()){
				BannerAndAdvertDAO bannerAndAdvertDao = new BannerAndAdvertDAO();
				
				try {
					Collection<Collection <String>> bannerCollection = bannerAndAdvertDao.findById(pageName);
					
					slotNameToContentMap.put("Slot_1", bannerCollection);
					
				} catch (SQLException sqle) {
					Log.info("getBannerData(Operation): SQLException: " + sqle.toString());
					throw sqle;
				} catch (Exception e) {
					Log.info("getBannerData(Operation): Exception: " + e.toString());
					throw e;
				}
			}
		}
		
		return slotNameToContentMap;
	}
}
