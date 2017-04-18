package com.genesiis.campus.command;
/*
 * 20170418 DN c86-admin-manage-banner-search-banner-dn Created the initial class CmdAmendBannerState.java
 */

import com.genesiis.campus.entity.BannerStateRole;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.jboss.logging.Logger;

import java.sql.SQLException;

/**
 * CmdAmendBannerState class bears the responsibility of issueing command to alter <br>
 * the Banner status. Currently the banner can be activated or deactivated<br>
 *  which is the same as inactive state.
 * @author dushantha
 *
 */
public class CmdAmendBannerState implements ICommand {

	private static final Logger log = Logger.getLogger(CmdAmendBannerState.class.getName());
	private BannerStateRole stateModifier= null;
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		
		
		
		return null;
	}

}
