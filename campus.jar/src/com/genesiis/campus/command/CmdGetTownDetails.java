package com.genesiis.campus.command;

//20161205 PN c26-add-student-details: INIT CmdGetTownDetails.java class. implement execute() to get towns over given country code.

import java.sql.SQLException;
import java.util.Collection;


import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TownDAO;
import com.genesiis.campus.util.IDataHelper;

public class CmdGetTownDetails implements ICommand{

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		
		ICrud townDao = new TownDAO();
		
		Collection<Collection<String>> townCollection = townDao.findById(Integer.parseInt(helper.getParameter("country")));
		view.setCollection(townCollection);

		return view;
	}

}
