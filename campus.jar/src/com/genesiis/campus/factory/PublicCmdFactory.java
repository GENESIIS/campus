package com.genesiis.campus.factory;

//20161104 MM c5-corporate-training-landing-page - Modified code to accommodate the name-change of CmdListCategoryProgrammes.java class
//20161109 JH c7-higher-education-landing-page-mp added new attribute for LIST_CATEGORY_DATA

import com.genesiis.campus.command.CmdListCategoryProgrammes;
import com.genesiis.campus.command.CmdListCategories;
import com.genesiis.campus.command.CmdListCategoryLandingPage;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.command.CmdListCategoryLandingPage;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class PublicCmdFactory implements ICmdFactory {
	static {
		map.put(Operation.LIST_CATEGORY_PROGRAMMES, new CmdListCategoryProgrammes());
		map.put(Operation.LIST_CATEGORY_LANDING_PAGE, new CmdListCategoryLandingPage());
		map.put(Operation.LIST_CATEGORY_DATA, new CmdListCategories());
	}

	@Override
	public ICommand getCommand(String cco) {
		ICommand command = null;
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
			case LIST_CATEGORY_PROGRAMMES:
				command = map.get(o);
				break;
			case LIST_CATEGORY_LANDING_PAGE:
				command = map.get(o);
				break;
			case LIST_CATEGORY_DATA:
				command = map.get(o);
				break;
			default:
				break;
		}		
		return command;
	}

}
