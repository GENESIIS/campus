package com.genesiis.campus.command;

/**
 * 20170424 DN c88-admin-manage-advertiser-add-new-advertiser-dn created the initial 
 * 				class CmdAddAdvertiserPrerequisiteData.java class.
 * 20170425 DN c88-admin-manage-advertiser-add-new-advertiser-dn. made the log attribute a private field
 */


import com.genesiis.campus.entity.AddAdvertiserPrerequisiteData;
import com.genesiis.campus.entity.Country2DAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TownDAO;
import com.genesiis.campus.entity.UserTypeDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.UserType;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * CmdAddAdvertiserPrerequisiteData
 * Retrieves the initial data to be displayed on page onload <br>
 * of the page where the admin adds a new advertiser.
 * @author dushantha DN.
 *
 */
public class CmdAddAdvertiserPrerequisiteData implements ICommand {
	
	private static Logger log = Logger.getLogger(CmdAddAdvertiserPrerequisiteData.class.getName());
	private AddAdvertiserPrerequisiteData pagePrerequiaiteData = null;
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
	try{
		 this.setPagePrerequiaiteData(new AddAdvertiserPrerequisiteData(helper));
		 view.setCollection(pagePrerequiaiteData.getAddAdvertiserPageOnLoadData());
		 	log.info("execute(IDataHelper,IView): --> executed and completed");
		} catch(Exception exp) {
			log.error("execute: Exception "+exp.toString());
			throw exp;
		}
		return view;
}
	
	/**
	 *getPagePrerequiaiteData method returns the AddAdvertiserPrerequiaiteData <br>
	 *field value to the caller.
	 * @return AddAdvertiserPrerequiaiteData
	 */
	public AddAdvertiserPrerequisiteData getPagePrerequiaiteData() {
		return pagePrerequiaiteData;
	}
	
	/**
	 * setPagePrerequiaiteData sets the field value <br>
	 * from the callers argument
	 * @param pagePrerequiaiteData: AddAdvertiserPrerequiaiteData type
	 * which handles and provides a means to manage the prerequisite data for the <br>
	 * page where the advertiser is added.
	 */
	public void setPagePrerequiaiteData(
			AddAdvertiserPrerequisiteData pagePrerequiaiteData) {
		this.pagePrerequiaiteData = pagePrerequiaiteData;
	}

	
	
	
}
