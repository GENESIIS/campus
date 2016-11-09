package com.genesiis.campus.factory;

//20161027 CM c9-make inquiry for institute INIT InstituteCmdFactory.java 
//20161109 CM c9-make inquiry for institute Modified getCommand()

import com.genesiis.campus.command.CmdSendInstituteInquiry;
import com.genesiis.campus.command.CmdViewCourseProvider;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class InstituteCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {
		map.put(Operation.SEND_INSTITUTE_INQUIRY, new CmdSendInstituteInquiry());
		map.put(Operation.VIEW_INSTITUTE_INQUIRY_DETAILS,
				new CmdViewCourseProvider());

	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {

		case SEND_INSTITUTE_INQUIRY:
			command = map.get(o);
			break;
		case VIEW_INSTITUTE_INQUIRY_DETAILS:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}
}
