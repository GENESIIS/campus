package com.genesiis.campus.factory;

//20161026 DN c10-contacting-us-page amended by inserting CONTACT_US_PUBLC entry to the map
//20161031 DJ c6-list-available-institutes-on-the-view amended by inserting LIST_TOP_COURSE_PROVIDERS entry to the map
//20161103 DJ c6-list-available-institutes-on-the-view amended by inserting LIST_ALL_COURSE_PROVIDERS entry to the map


import com.genesiis.campus.command.CmdListMoreCourseProviders;
import com.genesiis.campus.command.CmdListTopCourseProviders;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class PublicCmdFactory implements ICmdFactory {

	private ICommand command = null;
	static {				
		map.put(Operation.LIST_TOP_COURSE_PROVIDERS, new CmdListTopCourseProviders());
		map.put(Operation.LIST_ALL_COURSE_PROVIDERS, new CmdListMoreCourseProviders());		  
	}
	
	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {		
		case LIST_TOP_COURSE_PROVIDERS:
			command = map.get(o);
			break;
		case LIST_ALL_COURSE_PROVIDERS:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}
