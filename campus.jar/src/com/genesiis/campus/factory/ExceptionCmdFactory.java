package com.genesiis.campus.factory;

//20170111 PN CAM-72 INIT ExceptionCmdFactory class.

import com.genesiis.campus.command.CmdErrorHandeling;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class ExceptionCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {
		map.put(Operation.ERROR_HANDELING_COMMAND, new CmdErrorHandeling());
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.getOperation(null);
		o = Operation.getOperation(cco);
		switch (o) {
		case ERROR_HANDELING_COMMAND:
			command = map.get(o);
			break;
		default:
			break;	
		}
		return command;
	}

}
