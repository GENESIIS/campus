package com.genesiis.campus.factory;
//20161123 AS C19-student-login-without-using-third-party-application-test-as LoginCmdFactory class created.
import com.genesiis.campus.command.CmdStudentLogin;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class LoginCmdFactory implements ICmdFactory{

	private ICommand command = null;
	static {	
		map.put(Operation.STUDENT_LOGIN, new CmdStudentLogin());	
	}
	
	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		
		case STUDENT_LOGIN:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
