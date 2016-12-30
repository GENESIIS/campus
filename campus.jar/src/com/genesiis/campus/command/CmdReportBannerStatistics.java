package com.genesiis.campus.command;

//DJ 20161206 c52-report-banner-statistics-MP-dj created CmdReportBannerStatistics.java
//DJ 20161231 c52-report-banner-statistics-MP-dj Implement  generateReportResults() method

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.genesiis.campus.entity.BannerDAO;
import com.genesiis.campus.entity.BannerStatDAO;
import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.PageDAO;
import com.genesiis.campus.entity.PageSlotDAO;
import com.genesiis.campus.entity.model.BannerStat;
import com.genesiis.campus.entity.model.BannerStatSearchDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UtilityHelper;

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
			
			switch (Operation.getOperation(commandString)) {
			case SEARCH_VIEW_BANNER_STATISTICS:
				final Collection<Collection<String>> pageDetails = new PageDAO().getAll();
		        iView.setCollection(pageDetails);
				break;
				
			case LIST_PAGE_WISE_PAGESLOTS:
				String pageCode = helper.getParameter("pageCode");
				final Collection<Collection<String>> pageSlotDetails = new PageSlotDAO().findById(pageCode);
				helper.setAttribute("pageSlots", pageSlotDetails);					
				break;
				
			/*case LIST_PAGESLOT_WISE_BANNER:
				String pageSlotCode = helper.getParameter("pageSlotCode");
				final Collection<Collection<String>> bannerDetails = new BannerDAO().findById(pageSlotCode);
				helper.setAttribute("bannerDetails", bannerDetails);
				break;*/
				
			case LIST_PAGESLOT_WISE_ADVERTISER:
				String pageSlotCode = helper.getParameter("pageSlotCode");
				final Collection<Collection<String>> advertiserDetails = new BannerDAO().findById(pageSlotCode);
				helper.setAttribute("advertiserDetails", advertiserDetails);	
				break;
				
			case REPORT_BANNER_STATISTICS:				
				generateReportResults(helper);				
				break;

			default:
				break;
			}

			/*if (commandString != null && commandString.equalsIgnoreCase(Operation.SEARCH_VIEW_BANNER_STATISTICS.getCommandString())) {
				
				final Collection<Collection<String>> pageDetails = new PageDAO().getAll();
		        iView.setCollection(pageDetails);

			}else if(commandString!=null && commandString.equalsIgnoreCase(Operation.LIST_PAGE_WISE_PAGESLOTS.getCommandString())){
				
				String pageCode = helper.getParameter("pageCode");
				final Collection<Collection<String>> pageSlotDetails = new PageSlotDAO().findById(pageCode);
				helper.setAttribute("pageSlots", pageSlotDetails);				
			}
			else if(commandString!=null && commandString.equalsIgnoreCase(Operation.LIST_PAGESLOT_WISE_BANNER.getCommandString())){
				
				String pageSlotCode = helper.getParameter("pageSlotCode");
				final Collection<Collection<String>> bannerDetails = new BannerDAO().findById(pageSlotCode);
				helper.setAttribute("bannerDetails", bannerDetails);	
				
			}
			else if(commandString!=null && commandString.equalsIgnoreCase(Operation.LIST_PAGESLOT_WISE_ADVERTISER.getCommandString())){
				
				String pageSlotCode = helper.getParameter("pageSlotCode");
				final Collection<Collection<String>> advertiserDetails = new BannerDAO().findById(pageSlotCode);
				helper.setAttribute("advertiserDetails", advertiserDetails);	
				
			}
			else if(commandString!=null && commandString.equalsIgnoreCase(Operation.REPORT_BANNER_STATISTICS.getCommandString())){
				final BannerStat bannerStat=new BannerStat();
				final Collection<Collection<String>> bannerDetails = new BannerStatDAO().findById(bannerStat);
				helper.setAttribute("bannerStatDetails", bannerDetails);
				
			}*/

		}  catch (Exception exception) {
			log.error("execute() : Exception " + exception);
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}
		return iView;
	}
	
	/** Identify input search parameters and retrieve particular  banner statistics result set according to search criteria.
	 * @author DJ
	 * @param helper
	 * @throws Exception
	 */
	private void generateReportResults(IDataHelper helper) throws Exception {
		String pageSlotCodeString = helper.getParameter("pageSlotCode");
		String fromDateString = helper.getParameter("fromDate");
		String toDateString = helper.getParameter("toDate");
		int pageSlot = 0;
		try {
			if (UtilityHelper.isNotEmpty(pageSlotCodeString)) {
				if (UtilityHelper.isInteger(pageSlotCodeString)) {
					pageSlot = Integer.parseInt(pageSlotCodeString);
				}
			}
			final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			final BannerStatSearchDTO searchDTO = new BannerStatSearchDTO();
			searchDTO.setPageSlotCode(pageSlot);
			try {
				if (UtilityHelper.isNotEmpty(fromDateString)) {
					searchDTO.setFromDate(df.parse((fromDateString)));
				}
				if (UtilityHelper.isNotEmpty(toDateString)) {
					searchDTO.setToDate(df.parse((toDateString)));
				}

			} catch (ParseException parseException) {
				log.error("execute() : ParseException "
						+ parseException.toString());
				throw parseException;
			}

			final BannerStat bannerStat = new BannerStat();
			final Collection<Collection<String>> bannerStatDetails = new BannerStatDAO()
					.findById(searchDTO);
			helper.setAttribute("bannerStatDetails", bannerStatDetails);
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception.toString());
			throw exception;
		}
	}

}
