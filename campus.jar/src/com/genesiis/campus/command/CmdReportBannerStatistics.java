package com.genesiis.campus.command;

//DJ 20161206 c52-report-banner-statistics-MP-dj created CmdReportBannerStatistics.java

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.BannerDAO;
import com.genesiis.campus.entity.BannerStatDAO;
import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.PageDAO;
import com.genesiis.campus.entity.PageSlotDAO;
import com.genesiis.campus.entity.model.BannerStat;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

public class CmdReportBannerStatistics implements ICommand {

	static Logger log = Logger.getLogger(CmdReportBannerStatistics.class
			.getName());

	/**
	 * @author DJ
	 * @param helper
	 * @param view
	 * @return
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {

		SystemMessage systemMessage = SystemMessage.UNKNOWN;
		try {
			String commandString = helper.getParameter("CCO");

			if (commandString != null && commandString.equalsIgnoreCase(Operation.SEARCH_VIEW_BANNER_STATISTICS.getCommandString())) {
				
				final Collection<Collection<String>> pageDetails = new PageDAO().getAll();
		        iView.setCollection(pageDetails);

			}else if(commandString!=null && commandString.equalsIgnoreCase(Operation.LIST_PAGE_WISE_PAGESLOTS.getCommandString())){
				
				String pageCode = helper.getParameter("pageCode");
				final Collection<Collection<String>> pageSlotDetails = new PageSlotDAO().findById(pageCode);
				helper.setAttribute("pageSlots", pageSlotDetails);				
			}
			/*else if(commandString!=null && commandString.equalsIgnoreCase(Operation.LIST_PAGESLOT_WISE_BANNER.getCommandString())){
				
				String pageSlotCode = helper.getParameter("pageSlotCode");
				final Collection<Collection<String>> bannerDetails = new BannerDAO().findById(pageSlotCode);
				helper.setAttribute("bannerDetails", bannerDetails);	
				
			}*/
			else if(commandString!=null && commandString.equalsIgnoreCase(Operation.LIST_PAGESLOT_WISE_ADVERTISER.getCommandString())){
				
				String pageSlotCode = helper.getParameter("pageSlotCode");
				final Collection<Collection<String>> advertiserDetails = new BannerDAO().findById(pageSlotCode);
				helper.setAttribute("advertiserDetails", advertiserDetails);	
				
			}
			else if(commandString!=null && commandString.equalsIgnoreCase(Operation.REPORT_BANNER_STATISTICS.getCommandString())){
				final BannerStat bannerStat=new BannerStat();
				final Collection<Collection<String>> bannerDetails = new BannerStatDAO().findById(bannerStat);
				helper.setAttribute("bannerStatDetails", bannerDetails);
				
			}

		} catch (SQLException sql) {
			log.error("execute() : Exception " + sql);
			systemMessage = SystemMessage.ERROR;
			throw sql;
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}
		return iView;
	}

}
