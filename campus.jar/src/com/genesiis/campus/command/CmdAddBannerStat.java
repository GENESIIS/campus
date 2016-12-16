//20161118 MM c2-integrate-google-banners INIT - Initialised CmdAddBannerStat class
//20161118 MM c2-integrate-google-banners INIT - Implemented execute() method
//20161123 MM c2-integrate-google-banners INIT - Added JavaDoc comment
//20161217 MM c2-integrate-google-banners Changed logger level to 'error' in logging 
//				statements in catch clauses

package com.genesiis.campus.command;

import com.genesiis.campus.entity.BannerStatDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.BannerStat;
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

public class CmdAddBannerStat implements ICommand {

	static Logger Log = Logger.getLogger(CmdAddBannerStat.class.getName());

	/**
	 * Handles adding of a banner statistic (which results in the increase in
	 * view-count of a banner), according to parameters sent from client.
	 * 
	 * @param IDataHelper
	 *            That wraps an object of type HttpServletRequest and gives
	 *            access to attributes and parameters set on that
	 *            HttpServletRequest object.
	 *             
	 * @param IView In actual terms, a View object. It is not used in the method. 
	 * 
	 * @return An IView type of object that contains the data to be sent to the
	 *         client. Nothing is set to that 
	 * 			object as no particular set of data needs to be returned 
	 * 			in Collection<Collection<String>> format as a result of 
	 * 			inserting to the BannerStat table. There is however
	 * 			a String set as the value for an attribute named "operationStatus" 
	 * 			via IDataHelper typed object, that indicates whether
	 * 			the insert operation succeeded or not, that the client will receive. 
	 */

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		List<String> msgList = new ArrayList<String>();

		try {
			String bannerCodeStr = helper.getParameter("banner");
			if (bannerCodeStr != null && !bannerCodeStr.isEmpty()) {
				if (Validator.isNumber(bannerCodeStr)) {

					int bannerCode = Integer.parseInt(bannerCodeStr);

					String callerPageStr = helper.getParameter("callerPage");
					if (callerPageStr != null && !callerPageStr.isEmpty()) {

						BannerStat bannerStat = new BannerStat();
						bannerStat.setBanner(bannerCode);
						bannerStat.setCallerPage(callerPageStr);

						String createdBy = "SYSTEM";
						bannerStat.setCrtBy(createdBy);

						BannerStatDAO bannerDao = new BannerStatDAO();
						int insertStatus = bannerDao.add(bannerStat);

						String operationStatus = insertStatus > 0 ? "SUCCESS"
								: "FAILURE";
						helper.setAttribute("operationStatus", operationStatus);

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
