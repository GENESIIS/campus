package com.genesiis.campus.command;

//20161122 CM c36-add-tutor-information INIT CmdLoadTownDetails.java
//20161122 CM c36-add-tutor-information Modified execute()method. 
//20170130 CW c36-add-tutor-information re-organise the import statements.

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TownDAO;
import com.genesiis.campus.util.IDataHelper;
import org.apache.log4j.Logger;

public class CmdLoadTownDetails implements ICommand {

	static Logger log = Logger.getLogger(CmdLoadTownDetails.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		final TownDAO townDAO = new TownDAO();
		try {

			Collection<Collection<String>> townCollection = townDAO.getAll();
			view.setCollection(townCollection);
			
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception.toString());
			throw exception;
		}
		return view;
	}

}
