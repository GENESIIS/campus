package com.genesiis.campus.factory;

//20161024 DN created ICmdFactory.java
//20170203 133-admin-list-tutors arranged imports according to the style guide

import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

import java.util.HashMap;

public interface ICmdFactory {
	static HashMap<Operation, ICommand> map = new HashMap<Operation, ICommand>();

	abstract ICommand getCommand(String command);
}