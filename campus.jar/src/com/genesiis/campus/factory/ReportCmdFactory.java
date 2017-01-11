package com.genesiis.campus.factory;

//20161205 DJ c51-report-courses-by-course-provider-MP-dj Implemented ReportCmdFactory
//20161205 DJ c51-report-courses-by-course-provider-MP-dj by inserting SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER entry to the map
//20161205 DJ c51-report-courses-by-course-provider-MP-dj by inserting REPORT_COURSES_BY_COURSE_PROVIDER entry to the map

import com.genesiis.campus.command.CmdReportRegisteredStudents;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;
/**
 * The class {@code ReportCmdFactory} is a form of command factory class created for the purpose of manipulating report generation 
 * commands for campus application. 
 * @author dumani DJ
 *
*/
public class ReportCmdFactory implements ICmdFactory{
	
	private ICommand command = null;
	static {		
		map.put(Operation.SEARCH_VIEW_REGISTERED_STUDENTS, new CmdReportRegisteredStudents());
		map.put(Operation.REPORT_REGISTERED_STUDENTS, new CmdReportRegisteredStudents());
		
	}
	
	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {		
		case SEARCH_VIEW_REGISTERED_STUDENTS:
			command = map.get(o);
			break;
		case REPORT_REGISTERED_STUDENTS:
			command = map.get(o);
			break;
		default:
			break;
		}
		return command;
	}

}