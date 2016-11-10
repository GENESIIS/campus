package com.genesiis.campus.util;

//20161104 PN c11-criteria-based-filter-search INIT IQueryBuilder.java interface to define methods to query building helper

import java.util.Map;

public interface IQueryBuilder {
	String dynamicQuery(Map<String, String[]> queryMap, String mainQuery);
	Map<String, String[]> assignMapData(String qString);
	String extractFromJason(String data);
}
