package com.genesiis.campus.factory;

//20170221 PN CAM-48: INIT AdminCmdFactory.java. This will redirect servlet request into course provider image upload usecase' command classes.

import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class AdminCmdFactory implements ICmdFactory {
	private ICommand command = null;
	static {
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case GET_CP_IMAGE:
			command = map.get(o);
			break;
		case UPLOAD_CP_IMAGE:
			command = map.get(o);
			break;
		case GET_CP_IMAGE_DETAILS:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}