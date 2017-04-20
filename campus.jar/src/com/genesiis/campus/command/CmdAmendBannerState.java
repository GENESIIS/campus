package com.genesiis.campus.command;
/*
 * 20170418 DN c86-admin-manage-banner-search-banner-dn Created the initial class CmdAmendBannerState.java
 * 20170419 DN c86-admin-manage-banner-search-banner-dn. The method execute() has been implemented.
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
	public IView execute(IDataHelper helper, IView view) throws Exception {
		if(this.getStateModifier() == null)
			this.setStateModifier(new BannerStateRole(helper));
		
		try{
			getStateModifier().updateBannerStatus();
	
		} catch (Exception exp) {
			log.error("execute(IDataHelper,IView) :Exception "+ exp.toString());
			throw exp;
		}
		
		return view;
	}

	/**
	 * getStateModifier() method returns the internal
	 * BannerStateRole instance
	 * @author dushantha DN
	 * @return BannerStateRole 
	 */
	public BannerStateRole getStateModifier() {
		return stateModifier;
	}

	/**
	 * Method sets the BannerStateRole
	 * @author dushantha DN
	 * @param stateModifier
	 */
	public void setStateModifier(BannerStateRole stateModifier) {
		this.stateModifier = stateModifier;
	}

}
