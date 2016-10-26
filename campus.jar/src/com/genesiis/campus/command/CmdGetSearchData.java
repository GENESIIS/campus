package com.genesiis.campus.command;

//20161025 PN c11-criteria-based-filter-search INIT CmdGetSearchData.java
//			  c11-criteria-based-filter-search implementing execute() method.

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.LuceneTest;
import com.genesiis.campus.util.QueryBuildingHelper;

import org.apache.log4j.Logger;

public class CmdGetSearchData implements ICommand {
	static Logger log = Logger.getLogger(CmdGetSearchData.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
//		String searchData = helper.getParameter("jsonData");
//		QueryBuildingHelper qbh = new QueryBuildingHelper();
//		String query = qbh.dynamicQuery(qbh.assignMapData((String) qbh.extractFromJason(searchData)), "");
//		

		LuceneTest obj = new LuceneTest();

		/** creating index */
		obj.createIndex();

		/** searching simple keyword */
		System.out.println("==================searching simple keyword===========================");
		obj.search("vishal");

		/** searching using wild card */
		System.out.println("==================searching using wild card===========================");
		obj.search("neh*");

		/** searching using logical OR operator */
		System.out.println("==================searching using logical OR operator===========================");
		obj.search("vishal OR neha");

		/** searching using logical AND operator */
		System.out.println("==================searching using logical AND operator===========================");
		obj.search("vishal AND neha");
		
		
		return null;
	}



}
