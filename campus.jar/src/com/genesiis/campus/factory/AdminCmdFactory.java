package com.genesiis.campus.factory;

//20170202 DJ c138-add-basic-programme-MP-dj Initiate AdminCmdFactory.java

import com.genesiis.campus.command.CmdListProgrammeDetails;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;


public class AdminCmdFactory implements ICmdFactory {
	
	private ICommand command = null;
	static {	
		map.put(Operation.LIST_PROGRAMME_ADD_VIEW, new CmdListProgrammeDetails());		
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case LIST_PROGRAMME_ADD_VIEW:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}


}
