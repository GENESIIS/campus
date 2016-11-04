package com.genesiis.campus.factory;

//20161104 MM c5-corporate-training-landing-page - Modified code to accommodate the name-change of CmdListCategoryProgrammes.java class

import com.genesiis.campus.command.CmdListCategoryProgrammes;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class PublicCmdFactory implements ICmdFactory {
	static {
		map.put(Operation.LIST_CORPORATE_TRAINING_PROGRAMMES, new CmdListCategoryProgrammes());
	}

	@Override
	public ICommand getCommand(String cco) {
		ICommand command = null;
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
			case LIST_CORPORATE_TRAINING_PROGRAMMES:
				command = map.get(o);
				break;
			default:
				break;
		}		
		return command;
	}

}