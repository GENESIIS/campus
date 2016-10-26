package com.genesiis.campus.factory;

//20161026 DN c10-contacting-us-page amended by inserting CONTACT_US_PUBLC entry to the map

import com.genesiis.campus.command.CmdGenerateEmail;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class PublicCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {	
		map.put(Operation.CONTACT_US_PUBLC, new CmdGenerateEmail());	
	}
	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		
		case CONTACT_US_PUBLC:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
