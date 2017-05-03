package com.genesiis.campus.command;
/*
 * 20170426 DN c88-admin-manage-advertiser-add-new-advertiser-dn. The Class  CmdAdminMangeAdvertiser.java has been created.
 * 20170427 DN c88-admin-manage-advertiser-add-new-advertiser-dn. getters and setters are implemented.
 * 				try and catch block is implemented for the execute().
 */
import java.sql.SQLException;

import com.genesiis.campus.entity.AdvertiserFacilitator;
import com.genesiis.campus.entity.GeneralPurposeDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;


/**
 * The CmdAdminMangeAdvertiser class.
 * @author dushantha
 */

public class CmdAdminMangeAdvertiser implements ICommand {

	
	private static Logger log = Logger.getLogger(CmdAdminMangeAdvertiser.class.getName());
	private AdvertiserFacilitator advertiser =null;
	
	/* 
	 * @see com.genesiis.campus.command.ICommand#execute(com.genesiis.campus.util.IDataHelper, com.genesiis.campus.entity.IView)
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws Exception {
		try{
			setAdvertiser(new AdvertiserFacilitator(helper));
			view.setCollection(advertiser.processAdvertiser());
			
		} catch (Exception exp){
			log.error("execute() : Exception: "+ exp.toString());
			throw exp;
		}
		return view;
	}
	
	/**
	 * Gets the advertiser.
	 * @return the advertiser
	 */
	public AdvertiserFacilitator getAdvertiser() {
		return advertiser;
	}
	
	/**
	 * Sets the advertiser.
	 * @param advertiser the new advertiser
	 */
	public void setAdvertiser(AdvertiserFacilitator advertiser) {
		this.advertiser = advertiser;
	}

}
