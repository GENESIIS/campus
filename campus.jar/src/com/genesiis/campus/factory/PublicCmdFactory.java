package com.genesiis.campus.factory;

//20161025 CM c13-Display course details INIT PublicCmdFactory.java
//20161025 CM c13-Display course details Modified getCommand() method

import com.genesiis.campus.command.CmdAddTutorProfile;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;


public class PublicCmdFactory  implements ICmdFactory{

	static {
		map.put(Operation.ADD_TUTOR_PROFILE_DETAILS, new CmdAddTutorProfile());
	}
	@Override
	public ICommand getCommand(String cco) {
		ICommand command = null;
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case ADD_TUTOR_PROFILE_DETAILS:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;

	}

}
