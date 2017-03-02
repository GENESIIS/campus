package com.genesiis.campus.util.landing;

//20170215 PN CAM-137: init CategoryCache Singleton class to return categories associated with the landing page, from the cache.
//20170216 PN CAM-137: implemented getDefaultCategories(String code) method will return category list from the cache for the given code.
	
import com.genesiis.campus.entity.CategoryDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.model.Category;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CategoryCache {
	private static Logger log = Logger.getLogger(CategoryCache.class);
	private static CategoryCache categoryList;
	private static Collection<Collection<String>> collection = null;
	private static HashMap<String, Collection<Collection<String>>> catMap = new HashMap();
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
	 * This method will return category list from the cache.
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
	
	/**
	 * This method will return category list from the cache for the given code.
	 * @param code
	 * @return Collection<Collection<String>> which contains category details over given code.
	 * @throws SQLException
	 */
	public Collection<Collection<String>> getDefaultCategories(String code) throws SQLException {
		Collection<Collection<String>> categoryDetails = new ArrayList<Collection<String>>();
		ICrud dao = new CategoryDAO();
		Category category = new Category();
		
		try {
			category.setCode(Integer.parseInt(code));
			// Check if the category code existing in category map.
			if (catMap.containsKey(code)) {
				// get category details against category code from map.
				categoryDetails = catMap.get(code);
				// if value is not null, return it
				if (categoryDetails != null) {
					return categoryDetails;
				} else {
					// else update the value into category map and return
					categoryDetails = dao.findById(category);
					catMap.put(code, categoryDetails);
					return categoryDetails;
				}
			} else {
				// if category code is valid and not in map, add the value into category map and return
				categoryDetails = dao.findById(code);
				catMap.put(code, categoryDetails);
				return categoryDetails;
			}
		} catch (SQLException e) {
			log.error("CategoryCache: SQLE: " + e.toString());
		} catch (Exception e) {
			log.error("CategoryCache: E: " + e.toString());
		}

		return categoryDetails;
	}
}
