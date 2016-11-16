//20161003 MM c2-integrate-google-banners GetGoogleAdverts.java created
//20161116 MM c2-integrate-google-banners Renamed to CmdGetGoogleAdverts.java

package com.genesiis.campus.command;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import java.sql.SQLException;

public class CmdGetGoogleAdverts implements ICommand {

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		
		return view;
	}

}
