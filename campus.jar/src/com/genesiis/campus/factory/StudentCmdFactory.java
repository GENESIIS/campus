package com.genesiis.campus.factory;

//20161121 MM c5-corporate-training-landing-page - INIT - Initialised file
//20161122 MM c5-corporate-training-landing-page - Modified code to add map element 
//				and switch case item for LIST_STUDENT_DASHBOARD
//20161227 MM c5-corporate-training-landing-page - Modified code to add map element 
//				and switch case item for LIST_STUDENT_RECOMMENDED_PROGRAMMES

import com.genesiis.campus.command.CmdListStudentDashboardDetails;
import com.genesiis.campus.command.CmdListStudentRecommendedProgrammes;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class StudentCmdFactory implements ICmdFactory {
	static {
		map.put(Operation.LIST_STUDENT_DASHBOARD, new CmdListStudentDashboardDetails());
		map.put(Operation.LIST_STUDENT_RECOMMENDED_PROGRAMMES, new CmdListStudentRecommendedProgrammes());
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
			default:
				break;
		}		
		return command;
	}

}