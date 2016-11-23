//20161003 MM c2-integrate-google-banners GetGoogleAdverts.java created
//20161116 MM c2-integrate-google-banners Renamed to CmdGetGoogleAdverts.java
//20161123 MM c2-integrate-google-banners Added JavaDoc comment

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

/**
 * This is a dummy command class that was developed to demonstrate that after
 * the processing of a client request that is received into a command class,
 * when the programme-control traverses framework classes, any banners
 * applicable to the JSP that is related to the current operation 
 * (as mapped in Operation enum) will automatically get loaded
 * and displayed on the page. This is to be excluded during integration.
 * 
 * @author miyuru
 * 
 */
public class CmdLoadBannerPage implements ICommand {

	static Logger Log = Logger.getLogger(CmdLoadBannerPage.class.getName());

	/**
	 * Executes business logic and returns an object of type IView that wraps data 
	 * needed to be displayed
	 * @param IDataHelper
	 * @param IView
	 * 
	 * @return IView 
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		return view;
	}
}
