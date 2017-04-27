package com.genesiis.campus.command;
/*
 * 20170426 DN c88-admin-manage-advertiser-add-new-advertiser-dn. The Class  CmdAdminMangeAdvertiser.java has been created.
 */
import java.sql.SQLException;

import com.genesiis.campus.entity.AdvertiserFacilitator;
import com.genesiis.campus.entity.GeneralPurposeDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

/**
 * The CmdAdminMangeAdvertiser class
 * 
 * @author dushantha
 *
 */

public class CmdAdminMangeAdvertiser implements ICommand {

	private static Logger log = Logger.getLogger(CmdAdminMangeAdvertiser.class.getName());
	private AdvertiserFacilitator advertiser =null;
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		
		
		return null;
	}

}
