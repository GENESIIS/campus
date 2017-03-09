package com.genesiis.campus.factory;

//20161121 MM c25-student-create-dashboard-MP - INIT - Initialised file
//20161122 MM c25-student-create-dashboard-MP - Modified code to add map element 
//				and switch case item for LIST_STUDENT_DASHBOARD
//20161227 MM c25-student-create-dashboard-MP - Modified code to add map element 
//				and switch case item for LIST_STUDENT_RECOMMENDED_PROGRAMMES
//20161229 MM c25-student-create-dashboard-MP - Modified code to add map element 
//				and switch case item for LIST_STUDENT_RECENT_ACTIVITIES

import com.genesiis.campus.command.CmdListStudentDashboardDetails;
import com.genesiis.campus.command.CmdListStudentRecommendedProgrammes;
import com.genesiis.campus.command.CmdTestAjaxAndFormData;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class StudentCmdFactory implements ICmdFactory {
	static {
		map.put(Operation.LIST_STUDENT_DASHBOARD, new CmdListStudentDashboardDetails());
		map.put(Operation.LIST_STUDENT_RECOMMENDED_PROGRAMMES, new CmdListStudentRecommendedProgrammes());
		map.put(Operation.TEST_AJAX, new CmdTestAjaxAndFormData());
		map.put(Operation.TEST_FORM, new CmdTestAjaxAndFormData());
	}

	@Override
	public ICommand getCommand(String cco) {
		ICommand command = null;
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
			case LIST_STUDENT_DASHBOARD:
				command = map.get(o);
				break;
			case LIST_STUDENT_RECOMMENDED_PROGRAMMES:
				command = map.get(o);
				break;
			case TEST_AJAX:
				command = map.get(o);
				break;
			case TEST_FORM:
				command = map.get(o);
				break;
			default:
				break;
		}		
		return command;
	}

}