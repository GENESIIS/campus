package com.genesiis.campus.command;

//20161209 JH c39-add-course-provider CmdListCourseProviderRegisterPage.java created
//20161220 JH c39-add-course-provider execute method coding: added switch case

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.Country2DAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TownDAO;
import com.genesiis.campus.factory.ICmdFactory;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Operation;

public class CmdListCourseProviderRegisterPage implements ICommand {
	static org.apache.log4j.Logger log = Logger
			.getLogger(CmdListCourseProviderRegisterPage.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		Collection<Collection<String>> preRequisteColleWrapper = new ArrayList<Collection<String>>();

		try {

			Operation op = Operation.getOperation(helper.getCommandCode());
			switch (op) {
			case LIST_PROVIDER_REGISTRATION_PAGE:
				ICrud country2Dao = new Country2DAO();
				preRequisteColleWrapper = country2Dao.getAll();
				break;

			case DISPLAY_TOWN_DATA:

				ICrud townDao = new TownDAO();
				preRequisteColleWrapper = townDao.findById(Integer
						.parseInt(helper.getParameter("country")));

				break;
			default:
				Collection<String> signUpdata = new ArrayList<String>();
				signUpdata.add(null);
				preRequisteColleWrapper.add(signUpdata);

				break;
			}

		} catch (SQLException sqlException) {
			log.error("execute: SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception exception) {
			log.error("execute: Exception " + exception.toString());
			throw exception;
		}

		view.setCollection(preRequisteColleWrapper);

		return view;
	}

}
