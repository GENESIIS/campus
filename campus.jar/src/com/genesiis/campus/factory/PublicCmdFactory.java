package com.genesiis.campus.factory;

//20161025 PN c11-criteria-based-filter-search INIT PublicCmdFactory.java
//20161026 DN c10-contacting-us-page amended by inserting CONTACT_US_PUBLC entry to the map
//20161028 PN c11-criteria-based-filter-search: added LIST_CATEGORY_DATA attribute
//20161029 PN c11-criteria-based-filter-search: added LIST_LEVEL_DATA,LIST_TOWN_DATA,LIST_MAJOR_DATA,LIST_DISTRICT_DATA attributes
//20161101 PN c11-criteria-based-filter-search: added LIST_INSTITUTE_DATA attribute.
//20161116 MM c2-integrate-google-banners Added map element with key LIST_GOOGLE_ADVERTS enum
//20161116 MM c2-integrate-google-banners Changed map element with key LIST_GOOGLE_ADVERTS to LOAD_BANNER_PAGE
//20161217 MM c2-integrate-google-banners Removed map element and case block related to LOAD_BANNER_PAGE, 
//				that was there for testing purposes during development of the issue
//20161104 MM c5-corporate-training-landing-page - Modified code to accommodate the name-change of CmdListCategoryProgrammes.java class
//20161109 JH c7-higher-education-landing-page-mp added new attribute for LIST_CATEGORY_DATA
//20161031 DJ c6-list-available-institutes-on-the-view amended by inserting LIST_TOP_COURSE_PROVIDERS entry to the map
//20161103 DJ c6-list-available-institutes-on-the-view amended by inserting LIST_ALL_COURSE_PROVIDERS entry to the map
//20161121 DJ c17-provider-criteria-based-filter-search by inserting LIST_FILTER_SEARCH_COURSE_PROVIDERS entry to the map
//20161124 DJ c17-provider-criteria-based-filter-search by inserting LIST_FILTER_SEARCH_CATEGORY_TYPES entry to the map

import com.genesiis.campus.command.CmdListCategories;
import com.genesiis.campus.command.CmdAddBannerStat;

import com.genesiis.campus.command.CmdCourseProviderFilterSearch;
import com.genesiis.campus.command.CmdListCategoryTypes;
import com.genesiis.campus.command.CmdListMoreCourseProviders;
import com.genesiis.campus.command.CmdListTopCourseProviders;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.command.CmdListCategoryProgrammes;
import com.genesiis.campus.command.CmdListCategoryLandingPage;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.command.CmdGenerateEmail;

import org.apache.log4j.Logger;

public class PublicCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {
		map.put(Operation.LIST_CATEGORY_PROGRAMMES, new CmdListCategoryProgrammes());
		map.put(Operation.LIST_CATEGORY_LANDING_PAGE, new CmdListCategoryLandingPage());		
		map.put(Operation.LIST_CATEGORY_DATA, new CmdListCategories());
		map.put(Operation.ADD_BANNER_STAT, new CmdAddBannerStat());
		map.put(Operation.LIST_TOP_COURSE_PROVIDERS, new CmdListTopCourseProviders());
		map.put(Operation.LIST_ALL_COURSE_PROVIDERS, new CmdListMoreCourseProviders());
		map.put(Operation.LIST_FILTER_SEARCH_COURSE_PROVIDERS, new CmdCourseProviderFilterSearch());
		map.put(Operation.LIST_FILTER_SEARCH_CATEGORY_TYPES, new CmdListCategoryTypes());
		map.put(Operation.CONTACT_US_PUBLC, new CmdGenerateEmail());
	}
	
	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.getOperation(null);
		o = Operation.getOperation(cco);
		switch (o) {
		case LIST_CATEGORY_DATA:
			command = map.get(o);
			break;
		case ADD_BANNER_STAT:
			command = map.get(o);
			break;
		case CONTACT_US_PUBLC:
			command = map.get(o);
			break;		
		case LIST_CATEGORY_LANDING_PAGE:
			command = map.get(o);
			break;
		case LIST_CATEGORY_PROGRAMMES:
			command = map.get(o);
			break;
		case LIST_TOP_COURSE_PROVIDERS:
			command = map.get(o);
			break;
		case LIST_ALL_COURSE_PROVIDERS:
			command = map.get(o);
			break;
		case LIST_FILTER_SEARCH_COURSE_PROVIDERS:
			command = map.get(o);
			break;
		case LIST_FILTER_SEARCH_CATEGORY_TYPES:
			command = map.get(o);
			break;
		default:
			break;	
		}
		return command;
	}
}
