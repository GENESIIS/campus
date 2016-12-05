package com.genesiis.campus.factory;

//20161127 DJ c51-report-courses-by-course-provider-MP-dj by inserting SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER entry to the map
//20161127 DJ c51-report-courses-by-course-provider-MP-dj by inserting REPORT_COURSES_BY_COURSE_PROVIDER entry to the map

import com.genesiis.campus.command.CmdReportGeneration;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class PublicCmdFactory implements ICmdFactory {

	@Override
	public ICommand getCommand(String command) {
		// TODO Auto-generated method stub
		return null;
	}

	/*private ICommand command = null;
	static {
		map.put(Operation.SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER, new CmdReportGeneration());
		map.put(Operation.REPORT_COURSES_BY_COURSE_PROVIDER, new CmdReportGeneration());
		
	}
	
	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER:
			command = map.get(o);
			break;
		case REPORT_COURSES_BY_COURSE_PROVIDER:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}*/

}
