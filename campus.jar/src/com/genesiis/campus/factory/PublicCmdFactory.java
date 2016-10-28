package com.genesiis.campus.factory;

//20161025 PN c11-criteria-based-filter-search INIT PublicCmdFactory.java
//20161028 PN c11-criteria-based-filter-search: added LIST_CATEGORY_DATA attribute

import com.genesiis.campus.command.CmdGetSearchData;
import com.genesiis.campus.command.CmdListCategories;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;

public class PublicCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {	
		map.put(Operation.GET_SEARCH_DATA, new CmdGetSearchData());	
		map.put(Operation.LIST_CATEGORY_DATA, new CmdListCategories());	
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		
		case GET_SEARCH_DATA:
			command = map.get(o);
			break;
		case LIST_CATEGORY_DATA:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
