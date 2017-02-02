package com.genesiis.campus.factory;
//20161123 AS C19-student-login-without-using-third-party-application-test-as LoginCmdFactory class created.
//20170113 AS C21 student-logout-clear-session-details-update-logout-data-as CmdStudentLogout command added.
//20170202 AS C22 forgot password CmdEmailVarification command added. 
import com.genesiis.campus.command.CmdEmailVarification;
import com.genesiis.campus.command.CmdStudentLogin;
import com.genesiis.campus.command.CmdStudentLogout;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class LoginCmdFactory implements ICmdFactory{

	private ICommand command = null;
	static {	
		map.put(Operation.STUDENT_LOGIN, new CmdStudentLogin());	
		map.put(Operation.STUDENT_LOGOUT, new CmdStudentLogout());
		map.put(Operation.EMAIL_VERIFICATION, new CmdEmailVarification());
	}
	
	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		
		case STUDENT_LOGIN:
			command = map.get(o);
			break;
		case STUDENT_LOGOUT:
			command = map.get(o);
			break;
		case EMAIL_VERIFICATION:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
