package com.genesiis.campus.factory;

//20161206 DJ c52-report-banner-statistics-MP-dj by inserting SEARCH_VIEW_BANNER_STATISTICS entry to the map

import com.genesiis.campus.command.CmdReportBannerStatistics;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class ReportCmdFactory implements ICmdFactory{
	
	private ICommand command = null;
	static {
		map.put(Operation.SEARCH_VIEW_BANNER_STATISTICS, new CmdReportBannerStatistics());
	}
	
	@Override
	public ICommand getCommand(String cco) {
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
		case SEARCH_VIEW_BANNER_STATISTICS:
			command = map.get(o);
			break;		
		default:
			break;
		}
		return command;
	}

}
