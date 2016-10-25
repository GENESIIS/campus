package com.genesiis.campus.factory;

//20161025 PN c11-criteria-based-filter-search INIT PublicCmdFactory.java

import com.genesiis.campus.command.CmdGetSearchData;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;

public class PublicCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {	
		map.put(Operation.GET_SEARCH_DATA, new CmdGetSearchData());	
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		
		case GET_SEARCH_DATA:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
