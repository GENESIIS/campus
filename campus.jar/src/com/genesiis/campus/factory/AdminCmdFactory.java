package com.genesiis.campus.factory;

//20170117 JH c133-admin-list-tutors added LIST_TUTORS
//20170130 JH c134-admin-list-new-tutor-requests LIST_NEW_TUTOR_REQUESTS

import com.genesiis.campus.command.CmdAdminListTutors;
import com.genesiis.campus.command.CmdListTutorRequests;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class AdminCmdFactory implements ICmdFactory{


	private ICommand command = null;
	static {	
		map.put(Operation.LIST_TUTORS, new CmdAdminListTutors());
		map.put(Operation.LIST_NEW_TUTOR_REQUESTS, new CmdListTutorRequests());
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case LIST_TUTORS:
			command = map.get(o);
			break;
		case LIST_NEW_TUTOR_REQUESTS:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
