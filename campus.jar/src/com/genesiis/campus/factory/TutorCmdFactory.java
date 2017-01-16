package com.genesiis.campus.factory;

//20161121 CM c36-add-tutor-information INIT TutorCmdFactory.java
//20161121 CM c36-add-tutor-information Modified getCommand() method. 
//20161122 CM c36-add-tutor-information Modified getCommand() method. 
//20161123 CM c36-add-tutor-information Modified getCommand() method. 
//20170123 CM c36-add-tutor-information add getCommand() add LOAD_TUTOR_WITH_DUMMY_DATA element. 
//20170123 CM c36-add-tutor-information removed getCommand() add LOAD_TUTOR_WITH_DUMMY_DATA element. 

import com.genesiis.campus.command.CmdAddTutorProfile;
import com.genesiis.campus.command.CmdCheckUsername;
import com.genesiis.campus.command.CmdLoadCountry;
import com.genesiis.campus.command.CmdLoadTownDetails;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class TutorCmdFactory implements ICmdFactory {
	static {
		map.put(Operation.ADD_TUTOR_PROFILE_DETAILS, new CmdAddTutorProfile());
		map.put(Operation.LIST_TOWN_DATA, new CmdLoadTownDetails());
		map.put(Operation.LIST_COUNTRY_DATA, new CmdLoadCountry());
		map.put(Operation.CHECK_USERNAME, new CmdCheckUsername());
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
		case LIST_TOWN_DATA:
			command = map.get(o);
			break;
		case LIST_COUNTRY_DATA:
			command = map.get(o);
			break;
		case CHECK_USERNAME:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;

	}

}
