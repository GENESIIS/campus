//20161116 MM c2-integrate-google-banners Added map element with key LIST_GOOGLE_ADVERTS enum

package com.genesiis.campus.factory;

import com.genesiis.campus.command.CmdGetGoogleAdverts;
import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.validation.Operation;

public class PublicCmdFactory implements ICmdFactory {
	static {
		map.put(Operation.LIST_GOOGLE_ADVERTS, new CmdGetGoogleAdverts());
	}

	@Override
	public ICommand getCommand(String cco) {
		ICommand command = null;
		Operation o = Operation.BAD_OPERATION;
		o = Operation.getOperation(cco);
		switch (o) {
			case LIST_GOOGLE_ADVERTS:
				command = map.get(o);
				break;
			default:
				break;
		}		
		return command;
	}

}