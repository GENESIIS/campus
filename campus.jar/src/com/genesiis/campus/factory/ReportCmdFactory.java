package com.genesiis.campus.factory;

//20161206 DJ c52-report-banner-statistics-MP-dj by inserting SEARCH_VIEW_BANNER_STATISTICS entry to the map
//20161210 DJ c52-report-banner-statistics-MP-dj by inserting REPORT_BANNER_STATISTICS entry to the map
//20161211 DJ c52-report-banner-statistics-MP-dj by inserting LIST_PAGE_WISE_PAGESLOTS entry to the map
//20161211 DJ c52-report-banner-statistics-MP-dj by inserting LIST_PAGESLOT_WISE_BANNER entry to the map

import com.genesiis.campus.command.CmdReportBannerStatistics;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class ReportCmdFactory implements ICmdFactory{
	
	private ICommand command = null;
	static {
		map.put(Operation.SEARCH_VIEW_BANNER_STATISTICS, new CmdReportBannerStatistics());
		map.put(Operation.REPORT_BANNER_STATISTICS, new CmdReportBannerStatistics());
		map.put(Operation.LIST_PAGE_WISE_PAGESLOTS, new CmdReportBannerStatistics());
		map.put(Operation.LIST_PAGESLOT_WISE_BANNER, new CmdReportBannerStatistics());
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
		case LIST_PAGESLOT_WISE_BANNER:
			command = map.get(o);
			break;		
		default:
			break;
		}
		return command;
	}

}
