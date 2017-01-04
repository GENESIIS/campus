package com.genesiis.campus.command;

//DJ 20161206 c52-report-banner-statistics-MP-dj created CmdReportBannerStatistics.java
//DJ 20161231 c52-report-banner-statistics-MP-dj Implement  generateReportResults() method
//DJ 20170104 c52-report-banner-statistics-MP-dj Implement  isReportBannerStatValidate() method

import com.genesiis.campus.entity.BannerDAO;
import com.genesiis.campus.entity.BannerStatDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.PageDAO;
import com.genesiis.campus.entity.PageSlotDAO;
import com.genesiis.campus.entity.model.BannerStatSearchDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CmdReportBannerStatistics implements ICommand {

	static Logger log = Logger.getLogger(CmdReportBannerStatistics.class
			.getName());
	
	SystemMessage systemMessage = SystemMessage.UNKNOWN;

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
		List<String> msgList = new ArrayList<String>();
		
		String pageSlotCodeString = helper.getParameter("pageSlotCode");
		String bannerProviderCodeString = helper.getParameter("bannerProviderCode");
		
		
		try {
			final BannerStatSearchDTO searchDTO = new BannerStatSearchDTO();
			
			
			if (UtilityHelper.isNotEmpty(pageSlotCodeString)) {
				searchDTO.setPageSlotCode(Integer.valueOf(pageSlotCodeString));
			}
			if (UtilityHelper.isNotEmpty(bannerProviderCodeString)) {
				searchDTO.setBannerProviderCode(Integer.valueOf(bannerProviderCodeString));
			}
			
			if(isReportBannerStatValidate(searchDTO,helper,msgList )){
				final Collection<Collection<String>> bannerStatDetails = new BannerStatDAO().findById(searchDTO);
				helper.setAttribute("bannerStatDetails", bannerStatDetails);				
			}else{
				helper.setAttribute("message", msgList);
			}
		
		} catch (Exception exception) {
			log.error("generateReportResults() : Exception " + exception.toString());
			throw exception;
		}
	}

	private boolean isReportBannerStatValidate(BannerStatSearchDTO searchDTO,
			IDataHelper helper, List<String> msgList)throws Exception{
		String pageCodeString = helper.getParameter("pageCode");
		String fromDateString = helper.getParameter("fromDate");
		String toDateString = helper.getParameter("toDate");
		
		if (UtilityHelper.isNotEmpty(pageCodeString)) {
			searchDTO.setPageCode(Integer.valueOf(pageCodeString));
		}else{
			msgList.add(SystemMessage.INVALIDPAGESELECTION.message());
			return false;
		}
		
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		
		try {
			if (UtilityHelper.isNotEmpty(fromDateString)) {
				searchDTO.setFromDate(df.parse((fromDateString)));
			} else  {
				msgList.add(SystemMessage.INVALIDENDDATE.message());
				return false;
			}
			if (UtilityHelper.isNotEmpty(toDateString)) {
				searchDTO.setToDate(df.parse((toDateString)));
			} else {
				msgList.add(SystemMessage.INVALIDENDDATE.message());
				return false;
			}			

		} catch (ParseException parseException) {
			log.error("generateReportResults() : ParseException "
					+ parseException.toString());
			throw parseException;
		}
		
		return true;
	}	

}
