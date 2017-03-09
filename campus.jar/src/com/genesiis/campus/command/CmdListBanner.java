package com.genesiis.campus.command;
/*
 * 20170309 DN c81-admin-manage-banner-add-and-view-banner-dn  created the 
 *  initial Command class CmdListBanner.java.
 */


import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ListBanner;
import com.genesiis.campus.util.BannerDisplayingInflator;
import com.genesiis.campus.util.IDataHelper;

import org.jboss.logging.Logger;

import java.sql.SQLException;

/**
 * CmdListBanner class encapsulates the command related
 * members (methods and fields) to display banners 
 * @author dushantha DN
 *
 */
public class CmdListBanner implements ICommand {
	
	private static final Logger log = Logger.getLogger(CmdListBanner.class.getName());
	private ListBanner displayFacilitator ; 
	
	
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		this.setDisplayFacilitator(new ListBanner(helper));
		
		try {
			view.setCollection(displayFacilitator.getBanners());
			
		} catch (SQLException sqle) {
			log.error("execute(IDataHelper,Iview) : SQLException "+sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("execute(IDataHelper,Iview) :Exception"+exp.toString());
			throw exp;
		}		
		return view;		
	}
	
	
	/**
	 * getDisplayFacilitator() retrieve the ListBanner
	 * instance 
	 * @author dushantha DN
	 * @return ListBanner
	 */
	public ListBanner getDisplayFacilitator() {
		return displayFacilitator;
	}

	/**
	 * setDisplayFacilitator() facilitate setting the ListBanner
	 * field
	 * @author dushantha DN
	 * @param displayFacilitator: ListBanner
	 */
	public void setDisplayFacilitator(ListBanner displayFacilitator) {
		this.displayFacilitator = displayFacilitator;
	}

	
	
	
}
