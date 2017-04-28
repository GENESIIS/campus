package com.genesiis.campus.factory;

//20170202 DJ c138-add-basic-programme-MP-dj Initiate AdminCmdFactory.java
//20170206 DJ c138-add-basic-programme-MP-dj Add  ADD_PROGRAMME_DETAILS.
//20170221 DJ c145-add-enhanced-programme Add LIST_COURSE_PROVIDER_TOWN enum property.
//20170428 DJ c145-add-enhanced-programme Add ADD_SEMESTER_DETAILS enum property.
//20170428 DJ c145-add-enhanced-programme Add ADD_MODULE_DETAILS enum property.

import com.genesiis.campus.command.CmdHandleModuleDetails;
import com.genesiis.campus.command.CmdHandleProgrammeDetails;
import com.genesiis.campus.command.CmdHandleSemesterDetails;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;


public class AdminCmdFactory implements ICmdFactory {
	
	private ICommand command = null;
	static {	
		map.put(Operation.LIST_PROGRAMME_ADD_VIEW, new CmdHandleProgrammeDetails());		
		map.put(Operation.ADD_PROGRAMME_DETAILS, new CmdHandleProgrammeDetails());		
		map.put(Operation.LIST_COURSE_PROVIDER_TOWN, new CmdHandleProgrammeDetails());		
		map.put(Operation.ADD_SEMESTER_DETAILS, new CmdHandleSemesterDetails());		
		map.put(Operation.ADD_MODULE_DETAILS, new CmdHandleModuleDetails());		
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case LIST_PROGRAMME_ADD_VIEW:
			command = map.get(o);
			break;
		case ADD_PROGRAMME_DETAILS:
			command = map.get(o);
			break;
		case ADD_SEMESTER_DETAILS:
			command = map.get(o);
			break;
		case ADD_MODULE_DETAILS:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}


}
