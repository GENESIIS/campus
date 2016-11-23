package com.genesiis.campus.factory;

//20161123 PN c11-criteria-based-filter-search INIT StudentCmdFactory.java

import com.genesiis.campus.command.CmdGetProfileImg;
import com.genesiis.campus.command.CmdUploadProfileImg;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class StudentCmdFactory implements ICmdFactory{
	private ICommand command = null;
	static {	
		map.put(Operation.UPLOAD_USER_PROFILE, new CmdUploadProfileImg());
		map.put(Operation.GET_USER_PROFILE, new CmdGetProfileImg());	
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case UPLOAD_USER_PROFILE:
			command = map.get(o);
			break;
		case GET_USER_PROFILE:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}
}
