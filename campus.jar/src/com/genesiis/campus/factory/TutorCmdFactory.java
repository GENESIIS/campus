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
//20170207 CW c38-view-update-tutor-profile removed ADD_TUTOR_PROFILE_DETAILS element
//20170227 CW c37-tutor-update-tutor-profile-cw add VIEW_TUTOR_UPDATE_TUTOR_DETAILS & TUTOR_UPDATE_TUTOR element
//20170303 CW c37-tutor-update-tutor-profile-cw add CHECK_PASSWORDS
//20170308 CW c147-tutor-reset-password-cw Class copied from c37-tutor-update-tutor-profile-cw & removed unwanted commands
				//c147 added CHECK_EMAIL command & TUTOR_EMAIL_VERIFICATION command.

import com.genesiis.campus.command.CmdCheckEmail;
import com.genesiis.campus.command.CmdTutorEmailVerification;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class TutorCmdFactory implements ICmdFactory {
	static {
		map.put(Operation.CHECK_EMAIL, new CmdCheckEmail());
		map.put(Operation.TUTOR_EMAIL_VERIFICATION, new CmdTutorEmailVerification());
	}
	
	@Override
	public ICommand getCommand(String cco) {
		ICommand command = null;
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case CHECK_EMAIL:
			command = map.get(o);
			break;
		case TUTOR_EMAIL_VERIFICATION:
			command = map.get(o);	
			break;
		default:
			break;
		}
		return command;

	}

}