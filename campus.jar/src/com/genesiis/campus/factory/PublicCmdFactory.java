//20161116 MM c2-integrate-google-banners Added map element with key LIST_GOOGLE_ADVERTS enum
//20161116 MM c2-integrate-google-banners Changed map element with key LIST_GOOGLE_ADVERTS to LOAD_BANNER_PAGE
//20161217 MM c2-integrate-google-banners Removed map element and case block related to LOAD_BANNER_PAGE, 
//				that was there for testing purposes during development of the issue
//20170208 MM c111-display-banners-on-jsp-load - Added map element and case clause for Operation.LIST_BANNERS enum
//20170401 MM c117-display-banners-record-viewcount-back-end - Added map element and switch case clause for Operation.ADD_BANNER_VIEW_STAT enum

package com.genesiis.campus.factory;

import com.genesiis.campus.command.CmdAddBannerStat;
import com.genesiis.campus.command.CmdAddBannerViewStat;
import com.genesiis.campus.command.CmdListBannersOnAsyncRequest;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class PublicCmdFactory implements ICmdFactory {
	static {
		map.put(Operation.ADD_BANNER_STAT, new CmdAddBannerStat());
		map.put(Operation.LIST_BANNERS, new CmdListBannersOnAsyncRequest());
		map.put(Operation.ADD_BANNER_VIEW_STAT, new CmdAddBannerViewStat());
	}

	@Override
	public ICommand getCommand(String cco) {
		ICommand command = null;
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
			case ADD_BANNER_STAT:
				command = map.get(o);
				break;
			case LIST_BANNERS:
				command = map.get(o);
				break;
			case ADD_BANNER_VIEW_STAT:
				command = map.get(o);
				break;
			default:
				break;
		}		
		return command;
	}

}