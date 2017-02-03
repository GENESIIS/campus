
package com.genesiis.campus.command;
/*
 * 20170203 DN c131-admin-manage-banner-upload-bann c
 * 				the CmdGetBannerPreRequisite.java class
 */
import java.sql.SQLException;

import com.genesiis.campus.entity.BannerDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;

/**
 * CmdGetBannerPreRequisit class meant to extract necessary 
 * the prerequisite information before
 * the Banner view is loaded.
 * @author dushantha DN
 * 
 *
 */
public class CmdGetBannerPreRequisite implements ICommand {

	private static final Logger log = Logger.getLogger(CmdGetBannerPreRequisite.class.getName());
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
	//try{
		//toDo level of responsibility abstractions
		// (1) get the advertiser related collection
		// (2) get the pages related collection
		Operation branchSelector = Operation.getOperation(helper.getCommandCode());
		switch(branchSelector){
		case DISPLAY_BANNER_MANAGER_ONLOAD_PAGE_DATA :
			ICrud bannerDao = new BannerDAO();
			
			// do how to get the advertisers and pages
			break;
		default:
			break;
		}

		return view;
		// (3) get the page specific collection 
		
		
		
		
		
//	} catch(SQLException sqle){
//		log.error("getBannerPreRequisites(IDataHelper helper, IView view):SQLException"+sqle.toString());
//		throw sqle;
//	}catch(Exception exp) {
//		log.error("getBannerPreRequisites(IDataHelper helper, IView view):Exception"+exp.toString());
//		throw exp;
//	}
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