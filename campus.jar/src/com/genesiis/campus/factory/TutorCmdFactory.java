package com.genesiis.campus.factory;

//20161121 CM c36-add-tutor-information INIT TutorCmdFactory.java
//20170308 CW c147-tutor-reset-password-cw Class copied from c37-tutor-update-tutor-profile-cw & removed unwanted commands
				//c147 added CHECK_EMAIL command & TUTOR_EMAIL_VERIFICATION command.
//20170308 CW c147-tutor-reset-password-cw removed CHECK_EMAIL command
//20170313 CW c148-tutor-verify-hashcode-reset-password-cw add TUTOR_HASH_VERIFICATION command
//20170313 CW c148-tutor-verify-hashcode-reset-password-cw modified TUTOR_HASH_VERIFICATION details in getCommand method
//20170314 CW c148-tutor-verify-hashcode-reset-password-cw add TUTOR_RESET_PASSWORD command & modified getCommand method accordingly

import com.genesiis.campus.command.CmdTutorEmailVerification;
import com.genesiis.campus.command.CmdTutorHashVerification;
import com.genesiis.campus.command.CmdTutorPasswordChange;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class TutorCmdFactory implements ICmdFactory {
	static {
		map.put(Operation.TUTOR_EMAIL_VERIFICATION, new CmdTutorEmailVerification());
		map.put(Operation.TUTOR_HASH_VERIFICATION, new CmdTutorHashVerification());
		map.put(Operation.TUTOR_RESET_PASSWORD, new CmdTutorPasswordChange());
	}
	
	@Override
	public ICommand getCommand(String cco) {
		ICommand command = null;
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case TUTOR_EMAIL_VERIFICATION:
			command = map.get(o);	
			break;
		case TUTOR_HASH_VERIFICATION:
			command = map.get(o);	
			break;
		case TUTOR_RESET_PASSWORD:
			command = map.get(o);	
			break;
		default:
			break;
		}
		return command;

	}

}