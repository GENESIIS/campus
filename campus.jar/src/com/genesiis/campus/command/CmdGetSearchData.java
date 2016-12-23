package com.genesiis.campus.command;

//20161025 PN c11-criteria-based-filter-search INIT CmdGetSearchData.java
//			  c11-criteria-based-filter-search implementing execute() method.
//20161027 PN c11-criteria-based-filter-search modified execute() method for test LuceneAPI and QueryBuildingHelper.java class
//20161102 PN c11-criteria-based-filter-search modified execute() method get searchdata and pass it to ProgramDAO.
//20161103 PN c11-criteria-based-filter-search modified execute() method to load getAll() method and findById() method accordingly.

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.genesiis.campus.entity.CategoryDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SearchedProgrammeDAO;
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

		ICrud programmeDAO = new SearchedProgrammeDAO(); 
		Collection<Collection<String>> programmeCollection = null;
		String searchData =  helper.getParameter("searchData");
		
		try {
			//If:the instituteCode is set
			if ((searchData != null) && ((!searchData.isEmpty()))) {
				programmeCollection = programmeDAO.findById(searchData);
			
			//else:the instituteCode is not set at the beginning of the page loading
			} else {
				programmeCollection = programmeDAO.getAll();
			}
			
			if (programmeCollection != null) {
				view.setCollection(programmeCollection);
			}

		} catch (SQLException sqle) {
			log.error("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			log.error("execute() : e" + e.toString());
			throw e;
		}
		return view;
	}
}
