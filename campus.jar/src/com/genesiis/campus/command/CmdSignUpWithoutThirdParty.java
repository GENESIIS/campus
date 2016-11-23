package com.genesiis.campus.command;

import java.sql.SQLException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

public class CmdSignUpWithoutThirdParty implements ICommand{

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		return null;
	}

}
