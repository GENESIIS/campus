package com.genesiis.campus.command;

//20161025 PN c11-criteria-based-filter-search INIT CmdGetSearchData.java
//			  c11-criteria-based-filter-search implementing execute() method.
//20161027 PN c11-criteria-based-filter-search modified execute() method for test LuceneAPI and QueryBuildingHelper.java class

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.LuceneDemo;
import com.genesiis.campus.util.LuceneTest;
import com.genesiis.campus.util.QueryBuildingHelper;

import org.apache.log4j.Logger;

public class CmdGetSearchData implements ICommand {
	static Logger log = Logger.getLogger(CmdGetSearchData.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String searchData = helper.getParameter("jsonData");
		QueryBuildingHelper qbh = new QueryBuildingHelper();
		String query = qbh.dynamicQuery(qbh.assignMapData(qbh.extractFromJason(searchData)), "");
		System.out.println(query);
		
//		

//		LuceneTest obj = new LuceneTest();
//
//		/** creating index */
//		obj.createIndex();

//		/** searching simple keyword */
//		System.out.println("==================searching simple keyword===========================");
//		obj.search("1");
//
//		/** searching using wild card */
//		System.out.println("==================searching using wild card Admin*===========================");
//		obj.search("1");
//		
//		/** searching using wild card */
//		System.out.println("==================searching using wild card *Admin*===========================");
//		obj.search("*Admin*");
//		
//		/** searching using wild card */
//		System.out.println("==================searching using wild card *Admin===========================");
//		obj.search("*Admin");

//		/** searching using logical OR operator */
//		System.out.println("==================searching using logical OR operator===========================");
//		obj.search("Admin OR User AND 1");
//
//		/** searching using logical AND operator */
//		System.out.println("==================searching using logical AND operator===========================");
//		obj.search("Admin AND 1");
		
//		LuceneDemo luceneDemo = new LuceneDemo();		
//		//create Lucene index
//		luceneDemo.createLuceneIndex();
//		//create IndexSearcher
//		luceneDemo.createIndexSearcher();
//		luceneDemo.termQueryExample();
//		luceneDemo.prefixQueryExample();
//		luceneDemo.booleanQueryExample();
//		luceneDemo.phraseQueryExample();
//		luceneDemo.wildCardQueryExample();
//		luceneDemo.fuzzyQueryExample();
//		luceneDemo.queryParserExample();
//		luceneDemo.fieldBoostFactorExample();
//		luceneDemo.sortBySenderExample();
//		luceneDemo.filterExample();
//		luceneDemo.deletDocumentFromIndex();
//		return null;
		return view;
	}



}
