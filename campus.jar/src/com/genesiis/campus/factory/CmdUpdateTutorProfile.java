package com.genesiis.campus.factory;

import java.sql.SQLException;

import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

public class CmdUpdateTutorProfile implements ICommand {

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// TODO Auto-generated method stub
		System.out.println("........test..............");
		return null;
	}

}
