package com.genesiis.campus.factory;
//20161123 PN c27-upload-user-image: INIT StudentCmdFactory.java
//20161128 DN C18-student-signup-without-using-third-party-application-dn add SIGN_UP_WO_THRD_PARTY
// 	to the class and bound with CmdSignUpWithoutThirdParty.java
//20161205 DN C18-student-signup-without-using-third-party-application-dn add DISPLAY_PREREQUISITE_DATA
//and bind CmdListPrereqSignUpWithoutThirdParty()
//20161205 DN C18-student-signup-without-using-third-party-application-dn DISPLAY_TOWN_DATA added

import com.genesiis.campus.command.CmdListPrereqSignUpWithoutThirdParty;
import com.genesiis.campus.command.CmdSignUpWithoutThirdParty;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class StudentCmdFactory implements ICmdFactory{
	private ICommand command = null;
	static {
	map.put(Operation.SIGN_UP_WO_THRD_PARTY, new CmdSignUpWithoutThirdParty());	
	map.put(Operation.DISPLAY_PREREQUISITE_DATA, new CmdListPrereqSignUpWithoutThirdParty());
	map.put(Operation.DISPLAY_TOWN_DATA,new CmdListPrereqSignUpWithoutThirdParty());
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case SIGN_UP_WO_THRD_PARTY:
			command = map.get(o);
			break;
		case DISPLAY_PREREQUISITE_DATA:
			command = map.get(o);
			break;
		case DISPLAY_TOWN_DATA:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}
}