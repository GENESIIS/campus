package com.genesiis.campus.factory;

//20161026 DN c10-contacting-us-page amended by inserting CONTACT_US_PUBLC entry to the map
//20161026 JH c7-higher-education-landing-page amended LIST_HIGHER_EDUCATION_PROGRAMMES to the map

import com.genesiis.campus.command.CmdListHigherEducationProgrammes;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class PublicCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {	
		map.put(Operation.LIST_HIGHER_EDUCATION_PROGRAMMES, new CmdListHigherEducationProgrammes() );	
	}
	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		
		case LIST_HIGHER_EDUCATION_PROGRAMMES:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}