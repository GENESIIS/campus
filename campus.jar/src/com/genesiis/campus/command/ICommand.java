package com.genesiis.campus.command;

//20161024 DN c10-contacting-us-page created the initial version of the ICommand.java
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import java.sql.SQLException;


public interface ICommand {
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception;
}