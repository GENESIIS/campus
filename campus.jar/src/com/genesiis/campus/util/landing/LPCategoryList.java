package com.genesiis.campus.util.landing;

//20170215 PN CAM-137: init LPCategoryList Singleton class to return categories associated with the landing page, from the cache.

import com.genesiis.campus.entity.CategoryDAO;
import com.genesiis.campus.entity.ICrud;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class LPCategoryList {
	private static Logger log = Logger.getLogger(LPCategoryList.class);
	private static LPCategoryList categoryList;
	private static Collection<Collection<String>> collection = null;

	/**
	 * Create private constructor
	 */
	private LPCategoryList() {
		log.info("LPCategoryList: calling private constructor.");
	}

	/**
	 * Create a static method to get instance of the LPCategoryList class.
	 * 
	 * @return LPCategoryList object
	 */
	public static LPCategoryList getInstance() {
		if (LPCategoryList.categoryList == null) {
			LPCategoryList.categoryList = new LPCategoryList();
			log.info("LPCategoryList: creating new object. ");
		}
		log.info("LPCategoryList: return existing object.");
		return LPCategoryList.categoryList;
	}

	/**
	 * This method will return
	 * 
	 * @return Collection<Collection<String>> object
	 */
	public Collection<Collection<String>> getCategoryList() throws SQLException {
		try {
			if (collection == null) {
				ICrud dao = new CategoryDAO();
				collection = dao.getAll();
			}
		} catch (SQLException e) {
			log.error("LPCategoryList: SQLE: " + e.toString());
		} catch (Exception e) {
			log.error("LPCategoryList: E: " + e.toString());
		}

		return collection;
	}
}
