package com.genesiis.campus.factory;

//20161104 MM c5-corporate-training-landing-page - Modified code to accommodate the name-change of CmdListCategoryProgrammes.java class

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
	}

	@Override
	public ICommand getCommand(String cco) {
		map.put(Operation.LIST_CATEGORY_DATA, new CmdListCategories());
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
			default:
				break;
		case LIST_CATEGORY_DATA:
			command = map.get(o);
			break;
		}		
		return command;
	}

}
