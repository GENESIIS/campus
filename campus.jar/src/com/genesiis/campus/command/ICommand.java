package com.genesiis.campus.command;

//20161024 DN c10-contacting-us-page created the initial version of the ICommand.java


import java.sql.SQLException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;


public interface ICommand {
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception;
}