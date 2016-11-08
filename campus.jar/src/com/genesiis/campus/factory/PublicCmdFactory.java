package com.genesiis.campus.factory;

//20161108 JH c7-higher-education-landing-page-mp added PublicCmdFactory.java

import com.genesiis.campus.command.CmdListCategoryLandingPage;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;

public class PublicCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {	
		map.put(Operation.LIST_CATEGORY_LANDING_PAGE, new CmdListCategoryLandingPage());

	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case LIST_CATEGORY_LANDING_PAGE:
			command = map.get(o);
			break;

		default:
			break;
		}
		return command;
	}

}
