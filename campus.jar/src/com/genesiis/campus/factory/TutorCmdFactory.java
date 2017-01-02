package com.genesiis.campus.factory;

//20161121 CM c36-add-tutor-information INIT TutorCmdFactory.java
//20161121 CM c36-add-tutor-information Modified getCommand() method. 
//20161122 CM c36-add-tutor-information Modified getCommand() method. 
//20161123 CM c36-add-tutor-information Modified getCommand() method. 


import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class TutorCmdFactory implements ICmdFactory {
	static {
	
		//map.put(Operation.LIST_TOWN_DATA, new Object());
		
	}

	@Override
	public ICommand getCommand(String cco) {
		ICommand command = null;
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case UPLOAD_EXISTING_PROFILE_PIC:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;

	}

}