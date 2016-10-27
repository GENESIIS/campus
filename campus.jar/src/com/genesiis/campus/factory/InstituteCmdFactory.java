package com.genesiis.campus.factory;

//20161027 AS C8-inquiry-form-for-course add SEND_COURSE_INQUIRY to the map

import com.genesiis.campus.command.CmdGenerateEmail;
import com.genesiis.campus.command.CmdSendCourseInquiry;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class InstituteCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {	
		map.put(Operation.SEND_COURSE_INQUIRY, new CmdSendCourseInquiry());	
	}
	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		
		case SEND_COURSE_INQUIRY:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
