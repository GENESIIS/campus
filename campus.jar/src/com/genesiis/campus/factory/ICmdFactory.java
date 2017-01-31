package com.genesiis.campus.factory;
//20161024 DN c10-contacting-us create the initial version of ICmdFactory.java


import java.util.HashMap;

import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public interface ICmdFactory {
	static HashMap<Operation, ICommand> map = new HashMap<Operation, ICommand>();

	abstract ICommand getCommand(String command);
}