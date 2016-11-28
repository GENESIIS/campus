package com.genesiis.campus.command;

import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.CountryDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

//20161122 CM c36-add-tutor-information INIT CmdLoadTownDetails.java
//20161122 CM c36-add-tutor-information Modified execute()method. 

public class CmdLoadCountry implements ICommand {
	static Logger log = Logger.getLogger(CmdLoadCountry.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		final CountryDAO countryDAO = new CountryDAO();
		try {

			Collection<Collection<String>> countryCollection = countryDAO
					.getAll();
			view.setCollection(countryCollection);

		} catch (Exception exception) {
			log.error("execute() : Exception " + exception.toString());
			throw exception;
		}
		return view;
	}
}
