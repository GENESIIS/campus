package com.genesiis.campus.util;

//20170103 PN CAM-137: INIT CategoryCache singleton class to cache and get category list.

import com.genesiis.campus.entity.CategoryDAO;
import com.genesiis.campus.entity.ICrud;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;

public class CategoryCache {
	private static CategoryCache instance;
	private Collection<Collection<String>> defaultCategories = null;
	private static Logger logger = Logger.getLogger(CategoryCache.class);

	/**
	 * make the constructor private so that this class cannot be instantiated
	 **/
	private CategoryCache() {
	}

	/**
	 * return the instance only available
	 **/
	public static CategoryCache getInstance() {
		if (instance == null) {
			instance = new CategoryCache();
		}
		return instance;
	}

	/**
	 * get the default functional areas. These are not mapped to an agent
	 **/
	public Collection<Collection<String>> getDefaultCategories() throws SQLException, Exception {
		ICrud categories = new CategoryDAO();
		if (defaultCategories == null) {
			defaultCategories = categories.getAll();
		}
		return defaultCategories;
	}
}
