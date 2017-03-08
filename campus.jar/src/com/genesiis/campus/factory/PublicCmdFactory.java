package com.genesiis.campus.factory;

//20161026 DN c10-contacting-us-page amended by inserting CONTACT_US_PUBLC entry to the map
//20170308 AS c142 removed session validation method 

import javax.servlet.http.HttpSession;

import com.genesiis.campus.command.CmdListCategories;
import com.genesiis.campus.command.ICommand;

import com.genesiis.campus.validation.Operation;

public class PublicCmdFactory implements ICmdFactory {

	private ICommand command = null;
	HttpSession session;
	static {	
		
		map.put(Operation.LIST_CATEGORY_DATA, new CmdListCategories());

	}

	@Override
	public ICommand getCommand(String cco) {
	
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		
		case LIST_CATEGORY_DATA:
			command = map.get(o);
			break;

		default:
			break;
		}
		return command;
	}


}