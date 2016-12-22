package com.genesiis.campus.factory;

//20161025 PN c11-criteria-based-filter-search INIT PublicCmdFactory.java
//20161028 PN c11-criteria-based-filter-search: added LIST_CATEGORY_DATA attribute
//20161029 PN c11-criteria-based-filter-search: added LIST_LEVEL_DATA,LIST_TOWN_DATA,LIST_MAJOR_DATA,LIST_DISTRICT_DATA attributes
//20161101 PN c11-criteria-based-filter-search: added LIST_INSTITUTE_DATA attribute.
//20161116 MM c2-integrate-google-banners Added map element with key LIST_GOOGLE_ADVERTS enum
//20161116 MM c2-integrate-google-banners Changed map element with key LIST_GOOGLE_ADVERTS to LOAD_BANNER_PAGE
//20161217 MM c2-integrate-google-banners Removed map element and case block related to LOAD_BANNER_PAGE, 
//				that was there for testing purposes during development of the issue
//20161026 DN c10-contacting-us-page amended by inserting CONTACT_US_PUBLC entry to the map

import com.genesiis.campus.command.CmdListCategories;
import com.genesiis.campus.command.CmdAddBannerStat;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;
import org.apache.log4j.Logger;
import com.genesiis.campus.command.CmdGenerateEmail;

public class PublicCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {

		map.put(Operation.LIST_CATEGORY_DATA, new CmdListCategories());
		map.put(Operation.ADD_BANNER_STAT, new CmdAddBannerStat());
		map.put(Operation.CONTACT_US_PUBLC, new CmdGenerateEmail());
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
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
		default:
			break;
		}
		return command;
	}

}
