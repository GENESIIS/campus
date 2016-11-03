//20161003 MM c2-integrate-google-banners GetGoogleAdverts.java created

package com.genesiis.campus.command;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import java.sql.SQLException;

public class GetGoogleAdverts implements ICommand {

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		
		return view;
	}

}
