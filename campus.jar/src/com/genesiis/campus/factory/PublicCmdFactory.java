package com.genesiis.campus.factory;

//20161025 PN c11-criteria-based-filter-search INIT PublicCmdFactory.java
//20170302 JH c96-public-list-all-tutors added case PUBLIC_LIST_ALL_TUTORS

import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class PublicCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {	
	
	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		
		case PUBLIC_LIST_ALL_TUTORS:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
