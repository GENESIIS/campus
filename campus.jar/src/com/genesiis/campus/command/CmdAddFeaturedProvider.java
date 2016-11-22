package com.genesiis.campus.command;

//20161122 JH c39-add-course-provider CmdAddFeaturedProvider.java command class created
//20161122 JH c39-add-course-provider implemented ICommand class

import java.sql.SQLException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

public class CmdAddFeaturedProvider implements ICommand{

	/**
	 * execute method used to handle the request related to featured course provider 
	 * registration. 
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
