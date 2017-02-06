package com.genesiis.campus.factory;

//20161122 JH c39-add-course-provider AdminCmdFactory.java command factory class created
//20161122 JH c39-add-course-provider implemented ICmdFactory
//20170117 JH c133-admin-list-tutors added LIST_TUTORS

import com.genesiis.campus.command.CmdAdminListTutors;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class AdminCmdFactory implements ICmdFactory{


	private ICommand command = null;
	static {	
		map.put(Operation.LIST_TUTORS, new CmdAdminListTutors());
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case LIST_TUTORS:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
