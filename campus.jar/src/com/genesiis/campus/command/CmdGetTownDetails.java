package com.genesiis.campus.command;

//20161205 PN c26-add-student-details: INIT CmdGetTownDetails.java class. implement execute() to get towns over given country code.
//20161228 PN CAM-26: added try-catch() block to the execute() method.

import java.sql.SQLException;
import java.util.Collection;
import org.apache.log4j.Logger;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TownDAO;
import com.genesiis.campus.util.IDataHelper;

public class CmdGetTownDetails implements ICommand {
	static Logger log = Logger.getLogger(CmdGetTownDetails.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {

		ICrud townDao = new TownDAO();
		
		try {
			Collection<Collection<String>> townCollection = townDao
					.findById(Integer.parseInt(helper.getParameter("country")));
			view.setCollection(townCollection);
		} catch (SQLException sqle) {
			log.error("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			log.error("execute() : e" + e.toString());
			throw e;
		}
		return view;
	}

}
