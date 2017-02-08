//20170208 MM c111-display-banners-on-jsp-load - INIT class; implemented execute(IDataHelper, IView)

package com.genesiis.campus.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.genesiis.campus.entity.BannerStatDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.BannerStat;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.BannerData;
import com.genesiis.campus.validation.Validator;
import com.sun.istack.internal.logging.Logger;

public class CmdListBannersOnAsyncRequest implements ICommand {

	static Logger Log = Logger.getLogger(CmdListBannersOnAsyncRequest.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		List<String> msgList = new ArrayList<String>();

		try {
			String pageName = helper.getParameter("pageName");
			if (pageName != null && !pageName.isEmpty()) {
				BannerData.setBannerDetails(helper, pageName);
				
			} else {
				msgList.add("No or empty value has been provided for pageName parameter!");
			}// end of if (pageName != null && !pageName.isEmpty())
			
		} catch (Exception e) {
			Log.error("execute(IDataHelper, IView) : Exception " + e.toString());
			throw e;
		} finally {
			helper.setAttribute("messages", msgList);
		}

		return view;
	}
}
