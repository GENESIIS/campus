package com.genesiis.campus.factory;

//20161121 CM c36-add-tutor-information INIT TutorCmdFactory.java
//20161121 CM c36-add-tutor-information Modified getCommand() method. 
//20161122 CM c36-add-tutor-information Modified getCommand() method. 
//20161123 CM c36-add-tutor-information Modified getCommand() method. 
//20170116 CM c36-add-tutor-information add getCommand() add LOAD_TUTOR_WITH_DUMMY_DATA element. 
//20170116 CM c36-add-tutor-information removed getCommand() add LOAD_TUTOR_WITH_DUMMY_DATA element. 
//20170117 CM c36-add-tutor-information Modified getCommand() method. 
//20170116 CM c36-add-tutor-information removed CmdCheckUsername element.
//20170207 CW c38-view-update-tutor-profile removed CmdCheckUsername element.

import com.genesiis.campus.command.CmdCheckEmail;
import com.genesiis.campus.command.CmdLoadCountry;
import com.genesiis.campus.command.CmdLoadTownDetails;
import com.genesiis.campus.command.CmdUpdateTutorProfile;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.command.CmdViewTutorProfile;

public class TutorCmdFactory implements ICmdFactory {
	static {
		map.put(Operation.LIST_TOWN_DATA, new CmdLoadTownDetails());
		map.put(Operation.LIST_COUNTRY_DATA, new CmdLoadCountry());
		map.put(Operation.VIEW_TUTOR_DETAILS, new CmdViewTutorProfile());
		map.put(Operation.UPDATE_TUTOR, new CmdUpdateTutorProfile());
		map.put(Operation.CHECK_EMAIL, new CmdCheckEmail());
	}

	@Override
	public ICommand getCommand(String cco) {
		ICommand command = null;
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		/*case ADD_TUTOR_PROFILE_DETAILS:
			command = map.get(o);
			break;*/
		case LIST_TOWN_DATA:
			command = map.get(o);
			break;
		case LIST_COUNTRY_DATA:
			command = map.get(o);
			break;
		case VIEW_TUTOR_DETAILS:
			command = map.get(o);
			break;
		case UPDATE_TUTOR:
			command = map.get(o);
			break;
		case CHECK_EMAIL:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;

	}

}
