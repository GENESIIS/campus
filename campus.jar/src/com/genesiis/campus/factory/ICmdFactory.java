package com.genesiis.campus.factory;
//20161024 DN created ICmdFactory.java
//20170202 JH c134-admin-list-new-tutor-requests arranged imports according to the style guide document

import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

import java.util.HashMap;

public interface ICmdFactory {
	static HashMap<Operation, ICommand> map = new HashMap<Operation, ICommand>();

	abstract ICommand getCommand(String command);
}