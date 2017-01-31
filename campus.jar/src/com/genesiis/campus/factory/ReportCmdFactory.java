package com.genesiis.campus.factory;

//20161205 DJ c51-report-courses-by-course-provider-MP-dj Implemented ReportCmdFactory
//20161205 DJ c51-report-courses-by-course-provider-MP-dj by inserting SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER entry to the map
//20161205 DJ c51-report-courses-by-course-provider-MP-dj by inserting REPORT_COURSES_BY_COURSE_PROVIDER entry to the map


import com.genesiis.campus.command.CmdReportBannerStatistics;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class ReportCmdFactory implements ICmdFactory{
	
	private ICommand command = null;
	static {	
		
		map.put(Operation.SEARCH_VIEW_BANNER_STATISTICS, new CmdReportBannerStatistics());
		map.put(Operation.REPORT_BANNER_STATISTICS, new CmdReportBannerStatistics());
		map.put(Operation.LIST_PAGE_WISE_PAGESLOTS, new CmdReportBannerStatistics());
		map.put(Operation.LIST_PAGESLOT_WISE_ADVERTISER, new CmdReportBannerStatistics());
	}
	
	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {		
		case SEARCH_VIEW_BANNER_STATISTICS:
			command = map.get(o);
			break;		
		case REPORT_BANNER_STATISTICS:
			command = map.get(o);
			break;		
		case LIST_PAGE_WISE_PAGESLOTS:
			command = map.get(o);
			break;		
		case LIST_PAGESLOT_WISE_ADVERTISER:
			command = map.get(o);
			break;		
		default:
			break;
		}
		return command;
	}

}
