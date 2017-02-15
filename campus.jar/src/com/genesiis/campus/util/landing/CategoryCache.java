package com.genesiis.campus.util.landing;

//20170215 PN CAM-137: init CategoryCache Singleton class to return categories associated with the landing page, from the cache.

import com.genesiis.campus.entity.CategoryDAO;
import com.genesiis.campus.entity.ICrud;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CategoryCache {
	private static Logger log = Logger.getLogger(CategoryCache.class);
	private static CategoryCache categoryList;
	private static Collection<Collection<String>> collection = null;

	/**
	 * Create private constructor
	 */
	private CategoryCache() {
		log.info("CategoryCache: calling private constructor.");
	}

	/**
	 * Create a static method to get instance of the CategoryCache class.
	 * 
	 * @return CategoryCache object
	 */
	public static CategoryCache getInstance() {
		if (CategoryCache.categoryList == null) {
			CategoryCache.categoryList = new CategoryCache();
			log.info("CategoryCache: creating new object. ");
		}
		log.info("CategoryCache: return existing object.");
		return CategoryCache.categoryList;
	}

	/**
	 * This method will return
	 * 
	 * @return Collection<Collection<String>> object
	 */
	public Collection<Collection<String>> getDefaultCategories() throws SQLException {
		try {
			if (collection == null) {
				ICrud dao = new CategoryDAO();
				collection = dao.getAll();
			}
		} catch (SQLException e) {
			log.error("CategoryCache: SQLE: " + e.toString());
		} catch (Exception e) {
			log.error("CategoryCache: E: " + e.toString());
		}

		return collection;
	}
}
