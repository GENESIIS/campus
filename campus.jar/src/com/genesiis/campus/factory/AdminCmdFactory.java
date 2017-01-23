package com.genesiis.campus.factory;

//20170123 JH c135-admin-tutor-keyword-search added SEARCH_TUTORS

import com.genesiis.campus.command.CmdAdminListTutors;
import com.genesiis.campus.command.CmdAdminSearchTutors;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class AdminCmdFactory implements ICmdFactory{


	private ICommand command = null;
	static {	
		map.put(Operation.LIST_TUTORS, new CmdAdminListTutors());
		map.put(Operation.SEARCH_TUTORS, new CmdAdminSearchTutors());
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case LIST_TUTORS:
			command = map.get(o);
			break;
		case SEARCH_TUTORS:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
