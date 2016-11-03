package com.genesiis.campus.util;

//20161025 PN c11-criteria-based-filter-search INIT QueryBuildingHelper.java to implement query building helper method. 
//20161027 PN c11-criteria-based-filter-search modified assignMapData() method and extractFromJason() method. - WIP
//20161102 PN c11-criteria-based-filter-search changed dynamicQuery() methods' string concatenation order.
//20161103 PN c11-criteria-based-filter-search modified dynamicQuery() methods' string concatenation order.

import java.util.HashMap;
import java.util.Map;

import com.genesiis.campus.command.CmdGetSearchData;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

/**
 * @author pabodha This class is to get help on building dynamic SQL queries.
 */
public class QueryBuildingHelper {
	static Logger log = Logger.getLogger(QueryBuildingHelper.class.getName());

	/**
	 * To create dynamic query according to the incoming data
	 * This is only for concatenate 
	 * @author pabodha
	 * @param queryMap
	 * @param mainQuery
	 * @return String finalQuery
	 */
	public String dynamicQuery(Map<String, String[]> queryMap, String mainQuery) {
		String query = "";
		Map<String, String[]> map = queryMap;
		String var = "";
		int count = 0;
		if (!map.isEmpty()) {
			for (String key : map.keySet()) {
				String[] value = map.get(key);
				String criteria = key + " IN (";
				String attributes = String.join(",", value) + ")";
				count++;
				criteria = criteria.concat(attributes);
				if (count != map.size()) {
					criteria = criteria.concat(" AND ");
				}
				var = var + criteria;
			}
			query = mainQuery.concat(var);
		}
		return query;
	}

	/**
	 * @author pabodha
	 * @param qString
	 * @return map with criteria and value array
	 */
	public Map<String, String[]> assignMapData(String qString) {
		Map<String, String[]> map = new HashMap<String, String[]>();

		String[] criteriaValueSet = qString.split("&");
		for (int i = 0; i < criteriaValueSet.length; i++) {
			String var = criteriaValueSet[i];
			String[] data = var.split("=");
			if (data.length == 2) {
				String criteria = data[0].trim();
				String valueSet = data[1].trim();

				String[] valuesToArray = valueSet.split(",");
				map.put(criteria, valuesToArray);
			}
		}

		return map;
	}

	/**
	 * @author pabodha
	 * @param data
	 * @return Object extracted from passed json object.
	 */
	public String extractFromJason(String data) {
		Gson gson = new Gson();
		String queryString = "";
		try {
			queryString = gson.fromJson(data, String.class);
		} catch (Exception e) {
			log.info("ExtractFromgson - Exception " + e);
		}
		return queryString;
	}
}
