
package com.genesiis.campus.command;
/*
 * 20170203 DN c131-admin-manage-banner-upload-bann created the initial version of 
 * 				the CmdGetBannerPreRequisite.java class
 */
import java.sql.SQLException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

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
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		// implement a method this method has all the logics
		
		
		
		return null;
	}




private IView getBannerPreRequisites(IDataHelper helper, IView view) throws SQLException,Exception {
//	try{
//		
		return view;
//		
//	} catch(SQLException sqle){
//		log.error("getBannerPreRequisites(IDataHelper helper, IView view):SQLException"+sqle.toString());
//		throw sqle;
//	}catch(Exception exp) {
//		log.error("getBannerPreRequisites(IDataHelper helper, IView view):Exception"+exp.toString());
//		throw exp;
//	}
}



}