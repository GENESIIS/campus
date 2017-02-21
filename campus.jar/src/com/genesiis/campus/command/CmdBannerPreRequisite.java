
package com.genesiis.campus.command;
/*
 * 20170203 DN c131-admin-manage-banner-upload-banner-image-dn created
 * 				 the CmdGetBannerPreRequisite.java class
 * 20170213 DN c131-admin-manage-banner-upload-banner-image-dn renamed 
 * 				the class to CmdBannerPreRequisite.java
 * 20170221 DN c131-admin-manage-banner-upload-banner-image-dn refactor  getBannerPreRequisites() 
 * 			   DISPLAY_BANNER_MANAGER_ONLOAD_PAGE_DATA  case option to call AdvertiserDAO#getAll()
 * 			  instead of FeaturedCourseProviderDAO#getAll()
 * 		
 */


import com.genesiis.campus.entity.AdvertiserDAO;
import com.genesiis.campus.entity.BannerDAO;
import com.genesiis.campus.entity.BannerSlotDAO;
import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.PageWithBannersDAO;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * CmdBannerPreRequisite class meant to extract necessary 
 * the prerequisite information before
 * the Banner view is loaded.
 * @author dushantha DN
 * 
 *
 */
public class CmdBannerPreRequisite implements ICommand {

	private static final Logger log = Logger.getLogger(CmdBannerPreRequisite.class.getName());
	private int successCode =0;
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		try{
			
			return getBannerPreRequisites(helper,view);
			
		} catch(SQLException sqle){
			log.error("getBannerPreRequisites(IDataHelper helper, IView view):SQLException"+sqle.toString());
			throw sqle;
		}catch(Exception exp) {
			log.error("getBannerPreRequisites(IDataHelper helper, IView view):Exception"+exp.toString());
			throw exp;
		}
	}



private IView getBannerPreRequisites(IDataHelper helper, IView view) throws SQLException,Exception {
	try{
	
		Operation branchSelector = Operation.getOperation(helper.getCommandCode());
		switch(branchSelector){
		case DISPLAY_BANNER_MANAGER_ONLOAD_PAGE_DATA :
			//get featured course provider Collection wrapper
			
			ICrud registeredActivatedAdverTiser = new AdvertiserDAO();
			view.setCollection(registeredActivatedAdverTiser.getAll());
			
			// get the pages Collection wrapper
			ICrud PagesWithBannersDAO = new  PageWithBannersDAO();
			helper.setAttribute("bannerPages", PagesWithBannersDAO.getAll());
			break;
		case UPLOAD_BANNER_SLOT_ON_BANNER_MANAGER_PAGE :
			
			String pagecode = helper.getParameter("selectedCodeOfThePage");
			// get the page slot assign to a particular page
			ICrud pageSlotDAO= new BannerSlotDAO();
			view.setCollection(pageSlotDAO.findById(pagecode));
			break;
		default:
			break;
		}
		return view;
		
	} catch(SQLException sqle){
		log.error("getBannerPreRequisites(IDataHelper helper, IView view):SQLException"+sqle.toString());
		throw sqle;
	}catch(Exception exp) {
		log.error("getBannerPreRequisites(IDataHelper helper, IView view):Exception"+exp.toString());
		throw exp;
	}
}



/*
 * getters and setters 
 */


public int getSuccessCode() {
	return successCode;
}

public void setSuccessCode(int successCode) {
	this.successCode = successCode;
}


}