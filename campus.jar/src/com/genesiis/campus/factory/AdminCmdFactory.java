package com.genesiis.campus.factory;

//20161122 JH c39-add-course-provider AdminCmdFactory.java command factory class created
//20161122 JH c39-add-course-provider implemented ICmdFactory
//20161122 JH c39-add-course-provider added new map for ADD_FEATURED_COURSE_PROVIDER

import com.genesiis.campus.command.CmdAddFeaturedProvider;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class AdminCmdFactory implements ICmdFactory{


	private ICommand command = null;
	static {	
		map.put(Operation.ADD_FEATURED_COURSE_PROVIDER, new CmdAddFeaturedProvider());

	}

	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case ADD_FEATURED_COURSE_PROVIDER:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
