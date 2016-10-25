package com.genesiis.campus.command;

//20161025 PN c11-criteria-based-filter-search INIT CmdGetSearchData.java
//			  c11-criteria-based-filter-search implementing execute() method.

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdGetSearchData implements ICommand {
	static org.apache.log4j.Logger log = Logger
			.getLogger(CmdGetSearchData.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String searchData = helper.getParameter("jsonData");
		log.info("jsonData " + searchData);
		log.info("dynamicQuery " + dynamicQuery());
		return null;
	}

	public String dynamicQuery() {
		String query = "";

		String query2 = "SELECT * FROM PROGRAMME WHERE ";

		Map<String, int[]> map = new HashMap<String, int[]>();
		map.put("LEVEL", new int[] { 1, 2, 3, 3 });
		String var = "";
		for (String key : map.keySet()) {
			int[] value = map.get(key);
			String criteria = key + " IN (";
			String csv = String.join(",", new String[] { "1", "2" }) + ")"; // a,b

			// StringBuilder result = new StringBuilder();
			// for(int val : map.get(key)) {
			// result.append(Integer.toString((val)));
			// result.append(",");
			// }
			// return result.length() > 0 ? result.substring(0, result.length()
			// - 1): "";
			criteria = criteria.concat(csv);
			criteria = criteria.concat(" AND ");
			var = criteria;
		}
		return query2;
	}

}
