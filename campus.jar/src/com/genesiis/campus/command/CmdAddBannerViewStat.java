//20170330 MM c117-display-banners-record-viewcount-back-end - INIT class; implemented execute(IDataHelper, IView) method

package com.genesiis.campus.command;

import com.genesiis.campus.entity.BannerStatDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.BannerStat;
import com.genesiis.campus.util.BannerViewStatHelper;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.BannerData;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class CmdAddBannerViewStat implements ICommand {

	static Logger Log = Logger.getLogger(CmdAddBannerStat.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {		

		try {
			String bannerCodeStr = helper.getParameter("banner");			
			List<String> msgList = new ArrayList<String>();
			
			if (bannerCodeStr != null && !bannerCodeStr.isEmpty()) {
				
				if (Validator.isNumber(bannerCodeStr)) {

					int bannerCode = Integer.parseInt(bannerCodeStr);	

					String callerPageStr = helper.getParameter("callerPage");
					if (callerPageStr != null && !callerPageStr.isEmpty()) {

						BannerViewStatHelper bannerViewStatHelper = BannerViewStatHelper.getInstance();
						bannerViewStatHelper.updateBannerViewCount(bannerCode);		
						
//
//						String operationStatus = insertStatus > 0 ? "SUCCESS"
//								: "FAILURE";
//						helper.setAttribute("operationStatus", operationStatus);
						helper.setAttribute("operationStatus", 1);
						

					} else {
						msgList.add("Either no or empty value has been provided for callerPage parameter!");
					}// end of if (callerPageStr != null &&
						// !callerPageStr.isEmpty())
				} else {
					msgList.add("The value provided for banner parameter is invalid!");
				}// end of if (Validator.isNumber(bannerCodeStr))
			} else {
				msgList.add("No or empty value has been provided for banner parameter!");
			}// end of if (bannerCodeStr != null && !bannerCodeStr.isEmpty())

		} catch (NumberFormatException nfe) {
			Log.error("execute(IDataHelper, IView) : NumberFormatException "
					+ nfe.toString());
			throw nfe;
		} catch (IllegalArgumentException iae) {
			Log.error("execute(IDataHelper, IView) : IllegalArgumentException "
					+ iae.toString());
			throw iae;
		} catch (Exception e) {
			Log.error("execute(IDataHelper, IView) : Exception " + e.toString());
			throw e;
		} finally {
			helper.setAttribute("messages", msgList);
		}

		return view;
	}
}
