//20161003 MM c2-integrate-google-banners GetGoogleAdverts.java created
//20161116 MM c2-integrate-google-banners Renamed to CmdGetGoogleAdverts.java

package com.genesiis.campus.command;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.BannerData;
import com.genesiis.campus.validation.Operation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

public class CmdLoadBannerPage implements ICommand {

	static Logger Log = Logger.getLogger(CmdLoadBannerPage.class.getName());
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		
		BannerData.setBannerDetails(helper, helper.getResultPage(helper.getCommandCode()));
		
		return view;
	}
}
