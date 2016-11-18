//20161116 MM c2-integrate-google-banners Added map element with key LIST_GOOGLE_ADVERTS enum
//20161116 MM c2-integrate-google-banners Changed map element with key LIST_GOOGLE_ADVERTS to LOAD_BANNER_PAGE

package com.genesiis.campus.factory;

import com.genesiis.campus.command.CmdAddBannerStat;
import com.genesiis.campus.command.CmdLoadBannerPage;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class PublicCmdFactory implements ICmdFactory {
	static {
		map.put(Operation.LOAD_BANNER_PAGE, new CmdLoadBannerPage());
		map.put(Operation.ADD_BANNER_STAT, new CmdAddBannerStat());
	}

	@Override
	public ICommand getCommand(String cco) {
		ICommand command = null;
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
			case LOAD_BANNER_PAGE:
				command = map.get(o);
				break;
			case ADD_BANNER_STAT:
				command = map.get(o);
				break;
			default:
				break;
		}		
		return command;
	}

}